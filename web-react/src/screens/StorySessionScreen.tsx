import { useEffect, useRef, useState } from "react";
import { useT } from "../i18n/useT";
import { useAppState, update, startStorySession, saveStoryProgress, claimStoryReward } from "../domain/store";
import { storyById } from "../learning/stories";
import {
  initSession,
  sessionReducer,
  currentQuestion,
  currentDrill,
  duePhrases,
  scheduleFromSession,
  scheduleReview,
  sessionSummary,
  type SessionState,
  type Action,
} from "../learning/session";
import type { StoryQuestion, DrillItem } from "../learning/types";
import { speak, stopSpeak, canSpeak } from "../lib/speech";
import { SessionTop } from "../components/SessionTop";
import { useDialog } from "../components/DialogProvider";

/**
 * Séance « histoire » — boucle du guide d'intégration §3 :
 * révision → écoute (3 passages) → questions → transformation → reformulation
 * → transfert → bilan. La narration lit toujours l'ANGLAIS (langue cible),
 * même si l'interface est en arabe ; aucune avance automatique sur délai :
 * chaque étape attend une action de l'étudiant.
 */

/** Étiquettes d'étape pour la barre de progression. */
const STEP_ORDER = [
  "REVIEW",
  "LISTEN_DISCOVER",
  "LISTEN_WITH_HELP",
  "LISTEN_CHECK",
  "CIRCLING",
  "PATTERN_PRACTICE",
  "RETELL",
  "TRANSFER_AND_SUMMARY",
] as const;

function stepIndex(step: string): number {
  return Math.max(0, STEP_ORDER.indexOf(step as never));
}

interface Props {
  storyId: string;
  onQuit: () => void;
}

export function StorySessionScreen({ storyId, onQuit }: Props) {
  const { t, lang } = useT();
  const { open } = useDialog();
  const s = useAppState();
  const story = storyById(storyId);
  const [state, setState] = useState<SessionState | null>(null);
  const [input, setInput] = useState("");
  const [showText, setShowText] = useState(false);
  const [showHelp, setShowHelp] = useState(false);
  const [retellText, setRetellText] = useState("");
  const [retellShown, setRetellShown] = useState(false);
  const [transferShown, setTransferShown] = useState(false);
  const [summary, setSummary] = useState<{ xp: number; goalHit: boolean } | null>(null);
  const initialized = useRef(false);

  /* ---------- initialisation (une seule fois) ---------- */
  useEffect(() => {
    if (!story || initialized.current) return;
    initialized.current = true;
    const saved = s.storyProgress[story.storyId];
    const due = duePhrases(story, s.exprSm2, Date.now());
    if (
      saved &&
      saved.completedAt === 0 &&
      saved.contentVersion === story.contentVersion &&
      saved.savedAt > 0
    ) {
      // Reprise : le contenu a changé → la séance repart du début (honnêteté).
      setState({
        ...initSession(story, { duePhraseIds: due }),
        step: saved.step,
        qIndex: saved.qIndex,
        drillIndex: saved.drillIndex,
        reviewIndex: saved.reviewIndex,
        stats: saved.stats,
        retellAttempted: saved.retellAttempted,
        transferTaskAttempted: saved.transferTaskAttempted,
        transferTaskPending: saved.transferTaskPending,
      });
    } else {
      startStorySession(story.storyId, story.contentVersion, Date.now());
      setState(initSession(story, { duePhraseIds: due }));
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  /* ---------- arrêt de la narration à la sortie (guide §8 Android) ---------- */
  useEffect(() => {
    return () => stopSpeak();
  }, []);

  /* ---------- persistance de la progression à chaque étape ---------- */
  useEffect(() => {
    if (!state || !story || state.finished) return;
    saveStoryProgress(story.storyId, state, Date.now());
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [state]);

  const dispatch = (action: Action) => {
    if (!story || !state) return;
    setState((prev) => (prev ? sessionReducer(prev, action, story) : prev));
  };

  /* ---------- fin de séance : planification SM-2 + récompense ---------- */
  useEffect(() => {
    if (!state || !story || !state.finished || summary) return;
    const now = Date.now();
    const scheds = scheduleFromSession(state, story, s.exprSm2, now);
    update((draft) => {
      for (const [id, sched] of Object.entries(scheds)) draft.exprSm2[id] = sched;
    });
    // Idempotent : `rewardClaimed` est vérifié et posé dans la même recette.
    setSummary(claimStoryReward(story.storyId, now));
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [state?.finished]);

  const askExit = async () => {
    const r = await open({
      icon: "👋",
      title: t("quitTitle"),
      text: t("storyQuitResume"),
      actions: [
        { id: "stay", label: t("stayLbl") },
        { id: "quit", label: t("quitLbl"), cls: "danger" },
      ],
    });
    if (r === "quit") {
      stopSpeak();
      onQuit();
    }
  };

  const play = (text: string, slow = false) => speak(text, "en-US", slow ? 0.65 : 0.95);

  if (!story) {
    return (
      <div className="empty card" style={{ marginTop: 24 }}>
        <span className="empty-ico">⚠️</span>
        <h2>{t("invalidLesson")}</h2>
        <button type="button" className="btn block" onClick={onQuit}>
          {t("backHome")}
        </button>
      </div>
    );
  }

  if (!state) return <div className="skeleton" />;

  const title = story.title[lang] || story.title.fr;

  /* ================= BILAN FINAL ================= */
  if (state.finished && summary) {
    const sum = sessionSummary(state, story);
    return (
      <div className="card" style={{ textAlign: "center" }}>
        <div style={{ fontSize: 48, margin: "8px 0" }}>🎉</div>
        <h2>{t("storySummaryTitle")}</h2>
        <p className="muted">{title}</p>
        <div className="stat-grid" style={{ margin: "16px 0" }}>
          <div className="stat">
            <b>{sum.firstTry}/{sum.total}</b>
            <span className="tiny">{t("storySummaryFirstTry")}</span>
          </div>
          <div className="stat">
            <b>{sum.helped}</b>
            <span className="tiny">{t("storySummaryHelped")}</span>
          </div>
          <div className="stat">
            <b>{sum.mistakes}</b>
            <span className="tiny">{t("storySummaryMistakes")}</span>
          </div>
        </div>
        <p className="tiny" style={{ margin: "4px 0" }}>
          {t("storySummaryProduction")} : {sum.retellAttempted || sum.transferAttempted ? "✅" : "—"}
        </p>
        {sum.difficultPhrases.length > 0 && (
          <p className="tiny">
            {t("storySummaryPhrases")} : {sum.difficultPhrases.length}
          </p>
        )}
        {summary.xp > 0 && (
          <p className="tiny" style={{ marginTop: 8 }}>
            +{summary.xp} XP {summary.goalHit ? "🎯" : ""}
          </p>
        )}
        <button type="button" className="btn block" style={{ marginTop: 12 }} onClick={onQuit}>
          {t("homeLbl")}
        </button>
      </div>
    );
  }

  const step = state.step;
  const totalSteps = STEP_ORDER.length;
  const question = currentQuestion(state, story);
  const drill = currentDrill(state, story);

  /* ---------- corps commun d'une question (circling / transfert) ---------- */
  const questionBody = (q: StoryQuestion) => (
    <div className="card">
      <h3 style={{ direction: "ltr", textAlign: "left" }}>{q.q}</h3>
      {(q.qFr || q.qAr) && !showHelp && (
        <button
          type="button"
          className="btn ghost small"
          onClick={() => {
            setShowHelp(true);
            dispatch({ type: "HELP" });
          }}
        >
          💡 {t("storyShowHelp")}
        </button>
      )}
      {showHelp && (q.qFr || q.qAr) && (
        <p className="muted tiny">{lang === "ar" && q.qAr ? q.qAr : q.qFr || q.qAr}</p>
      )}
      {!state.revealed ? (
        <>
          <input
            className="fill-input"
            style={{ width: "100%", marginTop: 10 }}
            placeholder={t("storyYourAnswer")}
            value={input}
            onChange={(e) => setInput(e.target.value)}
            onKeyDown={(e) => {
              if (e.key === "Enter" && input.trim()) dispatch({ type: "SUBMIT_ANSWER", value: input });
            }}
          />
          <div style={{ display: "flex", gap: 8, marginTop: 10 }}>
            <button
              type="button"
              className="btn"
              disabled={!input.trim()}
              onClick={() => dispatch({ type: "SUBMIT_ANSWER", value: input })}
            >
              {t("storyCheck")}
            </button>
            <button type="button" className="btn ghost" onClick={() => dispatch({ type: "REVEAL" })}>
              {t("storyDontKnow")}
            </button>
          </div>
        </>
      ) : (
        <>
          {state.lastCorrect === true && (
            <p className="tiny" style={{ color: "var(--ok, green)" }}>✅ {t("storyCorrect")}</p>
          )}
          {state.lastCorrect === false && q.hint && (
            <p className="tiny" style={{ color: "var(--danger, red)" }}>⚠️ {q.hint[lang] || q.hint.fr}</p>
          )}
          <p className="muted tiny" style={{ marginTop: 6 }}>
            {t("storyModel")} · <b style={{ direction: "ltr", display: "inline-block" }}>{q.model}</b>
          </p>
          <button
            type="button"
            className="btn block"
            style={{ marginTop: 8 }}
            onClick={() => {
              setInput("");
              setShowHelp(false);
              dispatch({ type: "ADVANCE" });
            }}
          >
            {t("storyContinue")}
          </button>
        </>
      )}
    </div>
  );

  /* ---------- corps commun d'un drill ---------- */
  const drillBody = (d: DrillItem) => (
    <div className="card">
      {state.drillIndex === 0 && (
        <p className="muted tiny" style={{ direction: "ltr", textAlign: "left" }}>
          📘 {story.drill.explain[lang] || story.drill.explain.fr}
        </p>
      )}
      <h3 style={{ direction: "ltr", textAlign: "left" }}>{d.prompt}</h3>
      {!state.revealed ? (
        <>
          <input
            className="fill-input"
            style={{ width: "100%", marginTop: 10 }}
            placeholder={t("storyYourAnswer")}
            value={input}
            onChange={(e) => setInput(e.target.value)}
            onKeyDown={(e) => {
              if (e.key === "Enter" && input.trim()) dispatch({ type: "SUBMIT_DRILL", value: input });
            }}
          />
          <div style={{ display: "flex", gap: 8, marginTop: 10 }}>
            <button
              type="button"
              className="btn"
              disabled={!input.trim()}
              onClick={() => dispatch({ type: "SUBMIT_DRILL", value: input })}
            >
              {t("storyCheck")}
            </button>
            <button type="button" className="btn ghost" onClick={() => dispatch({ type: "REVEAL" })}>
              {t("storyDontKnow")}
            </button>
          </div>
        </>
      ) : (
        <>
          <p className="tiny" style={{ color: state.lastCorrect ? "var(--ok, green)" : "var(--danger, red)" }}>
            {state.lastCorrect ? `✅ ${t("storyCorrect")}` : "—"}
          </p>
          <p className="muted tiny">
            {t("storyModel")} · <b style={{ direction: "ltr", display: "inline-block" }}>{d.model}</b>
          </p>
          <button
            type="button"
            className="btn block"
            style={{ marginTop: 8 }}
            onClick={() => {
              setInput("");
              dispatch({ type: "ADVANCE" });
            }}
          >
            {t("storyContinue")}
          </button>
        </>
      )}
    </div>
  );

  /* ---------- production guidée (autoévaluée, sans micro) ---------- */
  const productionBody = (
    promptText: string,
    starters: string[],
    model: string,
    onDone: (attempted: boolean) => void,
    shown: boolean,
    setShown: (v: boolean) => void
  ) => (
    <div className="card">
      <p className="muted">{promptText}</p>
      <div style={{ display: "flex", gap: 6, flexWrap: "wrap", margin: "8px 0" }}>
        {starters.map((st) => (
          <span key={st} className="streak-pill" style={{ direction: "ltr" }}>
            {st}
          </span>
        ))}
      </div>
      {!shown ? (
        <>
          <textarea
            className="fill-input"
            style={{ width: "100%", minHeight: 70, direction: "ltr" }}
            placeholder={t("storyRetellPromptLabel")}
            value={retellText}
            onChange={(e) => setRetellText(e.target.value)}
          />
          <div style={{ display: "flex", gap: 8, marginTop: 10 }}>
            <button
              type="button"
              className="btn"
              disabled={!retellText.trim()}
              onClick={() => {
                onDone(true);
                setShown(true);
              }}
            >
              {t("storyIDidIt")}
            </button>
            <button
              type="button"
              className="btn ghost"
              onClick={() => {
                onDone(false);
                setShown(true);
              }}
            >
              {t("storySkip")}
            </button>
          </div>
        </>
      ) : (
        <>
          <p className="tiny">{t("storySelfReported")}</p>
          <p className="muted tiny">
            {t("storyModel")} · <b style={{ direction: "ltr", display: "inline-block" }}>{model}</b>
          </p>
          <button
            type="button"
            className="btn block"
            style={{ marginTop: 8 }}
            onClick={() => {
              setRetellText("");
              dispatch({ type: "ADVANCE" });
            }}
          >
            {t("storyContinue")}
          </button>
        </>
      )}
    </div>
  );

  /* ---------- écoute : segments avec lecture par phrase ---------- */
  const listenBody = (withHelp: boolean) => (
    <div className="card">
      {story.segments.map((seg, i) => (
        <div key={i} style={{ marginBottom: 10 }}>
          <div style={{ display: "flex", gap: 6 }}>
            <button type="button" className="btn small" onClick={() => play(seg.en)}>
              ▶ {t("storyPlay")}
            </button>
            <button type="button" className="btn ghost small" onClick={() => play(seg.en, true)}>
              🐢 {t("storySlow")}
            </button>
          </div>
          {(showText || withHelp) && (
            <p style={{ direction: "ltr", textAlign: "left", margin: "6px 0 0" }}>{seg.en}</p>
          )}
          {withHelp && <p className="muted tiny">{lang === "ar" ? seg.ar : seg.fr}</p>}
        </div>
      ))}
      {!withHelp && (
        <button type="button" className="btn ghost small" onClick={() => setShowText((v) => !v)}>
          {showText ? t("storyHideText") : t("storyShowText")}
        </button>
      )}
      <button
        type="button"
        className="btn block"
        style={{ marginTop: 10 }}
        onClick={() => dispatch({ type: "ADVANCE" })}
      >
        {t("storyContinue")}
      </button>
    </div>
  );

  let body: React.ReactNode = null;
  switch (step) {
    case "REVIEW": {
      const phraseId = state.reviewIds[state.reviewIndex];
      const phrase = story.targetPhrases.find((p) => p.id === phraseId);
      body = phrase ? (
        <div className="card">
          <h3 style={{ direction: "ltr", textAlign: "left" }}>{phrase.en}</h3>
          <p className="muted tiny">{lang === "ar" ? phrase.ar : phrase.fr}</p>
          <div style={{ display: "flex", gap: 8 }}>
            <button
              type="button"
              className="btn"
              onClick={() => {
                update((draft) => {
                  const scheds = scheduleReview(phrase.id, true, draft.exprSm2, Date.now());
                  for (const [k, v] of Object.entries(scheds)) draft.exprSm2[k] = v;
                });
                dispatch({ type: "ADVANCE" });
              }}
            >
              😀 {t("rate4")}
            </button>
            <button
              type="button"
              className="btn ghost"
              onClick={() => {
                update((draft) => {
                  const scheds = scheduleReview(phrase.id, false, draft.exprSm2, Date.now());
                  for (const [k, v] of Object.entries(scheds)) draft.exprSm2[k] = v;
                });
                dispatch({ type: "ADVANCE" });
              }}
            >
              😕 {t("rate1")}
            </button>
          </div>
        </div>
      ) : (
        <AutoAdvance onFire={() => dispatch({ type: "ADVANCE" })} />
      );
      break;
    }
    case "LISTEN_DISCOVER":
      body = listenBody(false);
      break;
    case "LISTEN_WITH_HELP":
      body = listenBody(true);
      break;
    case "LISTEN_CHECK":
      body = listenBody(false);
      break;
    case "CIRCLING":
      body = question ? questionBody(question) : <AutoAdvance onFire={() => dispatch({ type: "ADVANCE" })} />;
      break;
    case "PATTERN_PRACTICE":
      body = drill ? drillBody(drill) : <AutoAdvance onFire={() => dispatch({ type: "ADVANCE" })} />;
      break;
    case "RETELL":
      body = productionBody(
        story.retell.prompt[lang] || story.retell.prompt.fr,
        story.retell.starters,
        story.retell.model,
        (attempted) => dispatch({ type: "RETELL_DONE", attempted }),
        retellShown,
        setRetellShown
      );
      break;
    case "TRANSFER_AND_SUMMARY": {
      if (question) {
        body = (
          <>
            <div className="card">
              <p style={{ direction: "ltr", textAlign: "left" }}>{story.transfer.context.en}</p>
              {story.transfer.gloss && (
                <p className="muted tiny" style={{ direction: "ltr", textAlign: "left" }}>
                  💡 {story.transfer.gloss.en}
                </p>
              )}
            </div>
            {questionBody(question)}
          </>
        );
      } else {
        body = productionBody(
          story.transfer.task.prompt[lang] || story.transfer.task.prompt.fr,
          story.transfer.task.starters,
          story.transfer.task.model,
          (attempted) => dispatch({ type: "TRANSFER_TASK_DONE", attempted }),
          transferShown,
          setTransferShown
        );
      }
      break;
    }
  }

  const stepLbl = (() => {
    switch (step) {
      case "REVIEW": return t("storyStepReview");
      case "LISTEN_DISCOVER": return t("storyStepListen1");
      case "LISTEN_WITH_HELP": return t("storyStepListen2");
      case "LISTEN_CHECK": return t("storyStepListen3");
      case "CIRCLING": return t("storyStepQuestions");
      case "PATTERN_PRACTICE": return t("storyStepDrill");
      case "RETELL": return t("storyStepRetell");
      case "TRANSFER_AND_SUMMARY": return t("storyStepTransfer");
    }
  })();

  return (
    <>
      <SessionTop index={stepIndex(step)} total={totalSteps} onExit={askExit} />
      <div className="section-head" style={{ marginTop: 10 }}>
        <h2 style={{ fontSize: "1.05rem" }}>{title}</h2>
        <span className="tiny">{stepLbl}</span>
      </div>
      {!canSpeak() && (
        <p className="tiny card" style={{ borderLeft: "4px solid orange" }}>
          🔇 {t("storyTtsUnavailable")}
        </p>
      )}
      <p className="tiny muted" style={{ textAlign: "left", direction: "ltr" }}>
        🎯 {story.goal[lang] || story.goal.fr}
      </p>
      {body}
    </>
  );
}

/** Filet de sécurité : état incohérent (donnée incomplète) → avance auto. */
function AutoAdvance({ onFire }: { onFire: () => void }) {
  const fired = useRef(false);
  useEffect(() => {
    if (fired.current) return;
    fired.current = true;
    onFire();
  }, [onFire]);
  return null;
}
