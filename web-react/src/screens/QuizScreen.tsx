import { useEffect, useRef, useState } from "react";
import { useT } from "../i18n/useT";
import type { TKey } from "../i18n";
import { update } from "../domain/store";
import { getExercises } from "../data/load";
import { addXp, touchStreak, calculateGemsForLesson } from "../domain/gamification";
import { lessonQuestions } from "../domain/lessons";
import { lessonKey } from "../domain/path";
import { modLabel } from "../data/modules";
import { earnChest } from "../domain/chests";
import { claimTrophies, type TrophyId } from "../domain/trophies";
import { fillScore } from "../domain/scoring";
import { shuffle, norm } from "../lib/utils";
import { SessionTop } from "../components/SessionTop";
import { RewardPopup, type RewardData } from "../components/RewardPopup";
import { useDialog } from "../components/DialogProvider";
import { useToast } from "../components/ToastProvider";
import { navigate } from "../lib/router";
import type { Exercise, Lang } from "../types";

export const QUIZ_CAP = 10;
const LEVEL_KEYS: TKey[] = ["lvl1", "lvl2", "lvl3", "lvl4", "lvl5", "lvl6"];

/** Cadre de session lue dans le hash : `#/quiz?module=anat&level=3`, `#/quiz?mode=exam`. */
function frameFromHash(): { module?: string; level: number; mode?: string } {
  if (typeof window === "undefined") return { level: 0 };
  const query = window.location.hash.split("?")[1] ?? "";
  const p = new URLSearchParams(query);
  const raw = Number(p.get("level") ?? 0);
  return {
    module: p.get("module") ?? undefined,
    level: Number.isFinite(raw) && raw >= 1 && raw <= 6 ? raw : 0,
    mode: p.get("mode") ?? undefined,
  };
}

const pick = <T,>(lang: Lang, ar: T, en: T, fr: T): T =>
  lang === "ar" ? ar : lang === "en" ? en : fr;

const qText = (ex: Exercise, lang: Lang) => pick(lang, ex.qAr || ex.qEn, ex.qEn, ex.qFr || ex.qEn);
const expText = (ex: Exercise, lang: Lang) =>
  pick(lang, ex.expAr || ex.expEn, ex.expEn, ex.expFr || ex.expEn);
const ctxText = (ex: Exercise, lang: Lang) =>
  pick(lang, ex.ctxAr || ex.ctxEn, ex.ctxEn, ex.ctxFr || ex.ctxEn);

interface Word {
  w: string;
  used: boolean;
}
interface Pair {
  l: string;
  r: string;
}

interface QuizState {
  phase: "start" | "play" | "done";
  level: number;
  /** Module de la leçon cadrée (`?module=`), vide en quiz libre. */
  moduleId: string;
  list: Exercise[];
  i: number;
  correctN: number;
  gems: number;
  xp: number;
  /* état transitoire de la question courante */
  opts: string[];
  answerIdx: number;
  sel: number | null;
  checked: boolean;
  lastOk: boolean;
  fillVal: string;
  bank: Word[];
  wordSel: number[];
  pairs: Pair[];
  lefts: string[];
  rights: string[];
  matchDone: Record<string, string>;
  matchPick: string | null;
  mistakesQ: number;
  alreadyResult: boolean;
}

const IDLE: QuizState = {
  phase: "start",
  level: 0,
  moduleId: "",
  list: [],
  i: 0,
  correctN: 0,
  gems: 0,
  xp: 0,
  opts: [],
  answerIdx: -1,
  sel: null,
  checked: false,
  lastOk: false,
  fillVal: "",
  bank: [],
  wordSel: [],
  pairs: [],
  lefts: [],
  rights: [],
  matchDone: {},
  matchPick: null,
  mistakesQ: 0,
  alreadyResult: false,
};

/** Prépare l'état d'une question (options mélangées, banque de mots, paires). */
function setupQuestion(ex: Exercise): Partial<QuizState> {
  const base: Partial<QuizState> = {
    sel: null,
    checked: false,
    lastOk: false,
    fillVal: "",
    wordSel: [],
    matchDone: {},
    matchPick: null,
    mistakesQ: 0,
    alreadyResult: false,
    opts: [],
    answerIdx: -1,
    bank: [],
    pairs: [],
    lefts: [],
    rights: [],
  };

  if (ex.type === "mcq" || ex.type === "reading" || ex.type === "clinical_case") {
    const opts = shuffle(ex.options);
    base.opts = opts;
    base.answerIdx = opts.findIndex((o) => norm(o) === norm(ex.answer));
  } else if (ex.type === "sentence_order") {
    base.bank = shuffle(ex.options).map((w) => ({ w, used: false }));
  } else if (ex.type === "matching") {
    const pairs: Pair[] = ex.options.map((s) => {
      const idx = s.indexOf(":");
      return { l: s.slice(0, idx).trim(), r: s.slice(idx + 1).trim() };
    });
    base.pairs = pairs;
    base.lefts = shuffle(pairs.map((p) => p.l));
    base.rights = shuffle(pairs.map((p) => p.r));
  }
  return base;
}

function evalAnswer(state: QuizState, ex: Exercise): boolean {
  if (ex.type === "mcq" || ex.type === "reading" || ex.type === "clinical_case") {
    return state.sel === state.answerIdx;
  }
  // fill_blank : géré par `fillScore` (crédit partiel 0.5) dans `check()`.
  if (ex.type === "sentence_order") {
    const joined = state.wordSel
      .map((i) => state.bank[i].w)
      .join(" ")
      .replace(/\s+/g, " ")
      .trim();
    return joined === ex.answer.replace(/\s+/g, " ").trim();
  }
  return false;
}

export function QuizScreen() {
  const { t, lang } = useT();
  const { open } = useDialog();
  const toast = useToast();
  const [q, setQ] = useState<QuizState>(IDLE);
  const [reward, setReward] = useState<RewardData | null>(null);
  const goalHitRef = useRef(false);
  const started = useRef(false);
  // Anti-re-entrée : un double-clic sur « Suivant » ne doit ni sauter une
  // question ni créditer la session deux fois (B21/B22).
  const finishedRef = useRef(false);
  const advToRef = useRef(-1);

  const patch = (p: Partial<QuizState>) => setQ((prev) => ({ ...prev, ...p }));

  const begin = (level: number) => {
    const pool = level ? getExercises().filter((e) => e.level === level) : getExercises();
    const list = shuffle(pool).slice(0, QUIZ_CAP);
    if (!list.length) return false;
    goalHitRef.current = false;
    finishedRef.current = false;
    advToRef.current = -1;
    setQ({ ...IDLE, phase: "play", level, list, ...setupQuestion(list[0]) });
    return true;
  };

  /** Leçon cadrée par le parcours : 8 questions générées (module + niveau). */
  const beginLesson = (moduleId: string, level: number) => {
    const list = lessonQuestions(moduleId, level);
    if (!list.length) return false;
    goalHitRef.current = false;
    finishedRef.current = false;
    advToRef.current = -1;
    setQ({ ...IDLE, phase: "play", moduleId, level, list, ...setupQuestion(list[0]) });
    return true;
  };

  // Démarrage automatique quand on arrive avec un cadre (`?module=`, `?mode=exam`).
  // Un cadre invalide (module/niveau inconnu) affiche une erreur au lieu de
  // retomber silencieusement sur l'écran de choix de niveau (B34).
  useEffect(() => {
    if (started.current) return;
    started.current = true;
    const f = frameFromHash();
    if (f.module) {
      if (!f.level || !beginLesson(f.module, f.level)) {
        toast(t("invalidLesson"));
        navigate("quiz");
      }
    } else if (f.mode === "exam") {
      if (!begin(0)) {
        toast(t("invalidLesson"));
        navigate("quiz");
      }
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  /**
   * Enregistre le résultat. Effectuée HORS du updater `setQ` : React StrictMode
   * invoque les updaters deux fois en dev, ce qui aurait compté les gemmes 2×.
   *
   * Les gemmes ne sont pas créditées par bonne réponse : elles sont accordées
   * en fin de leçon via `calculateGemsForLesson` (10, +10 si parfaite, 0 si
   * < 50 % — règle `GemsManager` côté Android).
   *
   * `partialXp` : crédit partiel (fill_blank fuzzy, 0.5) — demi-XP sans gemme
   * ni point « correct », exactement comme `checkFill` côté Android.
   */
  const submit = (ok: boolean, partialXp = 0) => {
    if (q.phase !== "play" || q.alreadyResult) return;
    const ex = q.list[q.i];
    const now = Date.now();

    update((draft) => {
      draft.answered += 1;
      touchStreak(draft, now);
      if (ok) {
        draft.correct += 1;
        const out = addXp(draft, ex.points, now);
        if (out.goalHit) goalHitRef.current = true;
      } else if (partialXp > 0) {
        const out = addXp(draft, partialXp, now);
        if (out.goalHit) goalHitRef.current = true;
      }
    });

    setQ((prev) => ({
      ...prev,
      checked: true,
      lastOk: ok,
      alreadyResult: true,
      correctN: prev.correctN + (ok ? 1 : 0),
      xp: prev.xp + (ok ? ex.points : partialXp),
    }));
  };

  const check = () => {
    if (q.phase !== "play" || q.checked) return;
    const ex = q.list[q.i];
    if (ex.type === "fill_blank") {
      const score = fillScore(q.fillVal, ex.answer);
      submit(score === 1, score === 0.5 ? Math.floor(ex.points / 2) : 0);
      return;
    }
    submit(evalAnswer(q, ex));
  };

  /** Question suivante — la session se termine normalement, sans barrière de cœurs. */
  const next = () => {
    const i = q.i + 1;
    if (i >= q.list.length) {
      if (finishedRef.current) return;
      finishedRef.current = true;
      const total = q.list.length;
      const score = total ? Math.round((q.correctN / total) * 100) : 0;
      // Gemmes de leçon (10, +10 si parfaite, 0 si < 50 %) — en fin de session.
      const gems = calculateGemsForLesson(q.correctN, total);
      const now = Date.now();
      let trophies: TrophyId[] = [];
      update((draft) => {
        draft.quizDone += 1;
        if (q.moduleId) {
          const key = lessonKey(q.moduleId, q.level);
          draft.lessonBest[key] = Math.max(draft.lessonBest[key] ?? 0, score);
          draft.moduleBest[q.moduleId] = Math.max(draft.moduleBest[q.moduleId] ?? 0, score);
        } else if (score >= 100) {
          // F9 : quiz libre parfait → pseudo-clé `free` (ignorée par le
          // parcours, exclue de `module_master`) — débloque « Sans faute »
          // depuis le hub de pratique.
          draft.moduleBest.free = 100;
        }
        if (gems > 0) draft.gems += gems;
        earnChest(draft, "quiz", 0);
        trophies = claimTrophies(draft, now);
      });
      setReward({ xp: q.xp, gems, goalHit: goalHitRef.current, trophies });
      setQ((prev) => ({ ...prev, phase: "done", i, gems }));
    } else {
      if (advToRef.current === i) return;
      advToRef.current = i;
      setQ((prev) => ({ ...prev, i, ...setupQuestion(prev.list[i]) }));
    }
  };

  /* ---------- écran de choix de niveau ---------- */
  if (q.phase === "start") {
    const pool = q.level ? getExercises().filter((e) => e.level === q.level) : getExercises();
    const count = pool.length;
    return (
      <>
        <div className="section-head" style={{ marginTop: 0 }}>
          <div>
            <p className="eyebrow">📝 {t("quizSub")}</p>
            <h1>{t("quizTitle")}</h1>
          </div>
        </div>

        <div className="chip-row">
          <button
            type="button"
            className={`chip ${q.level === 0 ? "active" : ""}`}
            onClick={() => patch({ level: 0 })}
          >
            {t("levelAll")}
          </button>
          {[1, 2, 3, 4, 5, 6].map((n) => (
            <button
              key={n}
              type="button"
              className={`chip ${q.level === n ? "active" : ""}`}
              onClick={() => patch({ level: n })}
            >
              {n}. {t(LEVEL_KEYS[n - 1])}
            </button>
          ))}
        </div>

        <div className="card">
          <p className="muted" style={{ margin: "0 0 14px" }}>
            {count} {t("exCount")} · {Math.min(count, QUIZ_CAP)} {t("exCount")} par session · ⭐{" "}
            {t("statXp")} + 💎 {t("statGems")}
          </p>
          <button type="button" className="btn block" disabled={!count} onClick={() => begin(q.level)}>
            {t("startQuiz")} →
          </button>
        </div>
      </>
    );
  }

  /* ---------- récapitulatif ---------- */
  if (q.phase === "done") {
    const total = q.list.length;
    const score = total ? Math.round((q.correctN / total) * 100) : 0;
    const replay = () => {
      if (q.moduleId) beginLesson(q.moduleId, q.level);
      else begin(q.level);
    };
    return (
      <>
        {reward && <RewardPopup data={reward} onContinue={() => setReward(null)} />}
        <div className="summary card" style={{ marginTop: 24 }}>
          <div className="big-num">{score}%</div>
          <div className="big-label">
            {t("scoreLbl")} · {t("doneQuiz")}
          </div>
          <div className="summary-stats">
            <div className="stat-tile">
              <b>
                {q.correctN}/{total}
              </b>
              <span>✅ {t("correctLbl")}</span>
            </div>
            <div className="stat-tile">
              <b>+{q.xp}</b>
              <span>⭐ {t("statXp")}</span>
            </div>
            <div className="stat-tile">
              <b>+{q.gems}</b>
              <span>💎 {t("statGems")}</span>
            </div>
          </div>
          <a className="btn block" href={q.moduleId ? "#/path" : "#/practice"}>
            {t("homeLbl")}
          </a>
          <button
            type="button"
            className="btn ghost block"
            style={{ marginTop: 10 }}
            onClick={replay}
          >
            {t("replay")}
          </button>
        </div>
      </>
    );
  }

  /* ---------- question en cours ---------- */
  const ex = q.list[q.i];
  const isChoice = ex.type === "mcq" || ex.type === "reading" || ex.type === "clinical_case";
  const ctx = ex.type === "reading" || ex.type === "clinical_case" ? ctxText(ex, lang) : "";

  const askExit = async () => {
    const r = await open({
      icon: "👋",
      title: t("quitTitle"),
      text: t("quitText"),
      actions: [
        { id: "stay", label: t("stayLbl") },
        { id: "quit", label: t("quitLbl"), cls: "danger" },
      ],
    });
    if (r === "quit") {
      setQ(IDLE);
      // Quitter une leçon ramène au parcours, pas au choix de niveau (B9) ;
      // cela nettoie aussi la query `?module=…` du hash.
      navigate(q.moduleId ? "path" : "practice");
    }
  };

  let body: React.ReactNode;
  if (isChoice) {
    body = (
      <div className="options" id="opts">
        {q.opts.map((o, i) => (
          <button
            key={`${o}-${i}`}
            type="button"
            className={[
              "option",
              q.sel === i && !q.checked ? "selected" : "",
              q.checked && i === q.answerIdx ? "correct" : "",
              q.checked && i === q.sel && i !== q.answerIdx ? "wrong" : "",
            ]
              .filter(Boolean)
              .join(" ")}
            disabled={q.checked}
            onClick={() => patch({ sel: i })}
          >
            {o}
          </button>
        ))}
      </div>
    );
  } else if (ex.type === "fill_blank") {
    body = (
      <>
        <p className="tiny">{t("typeHint")}</p>
        <input
          className="fill-input"
          type="text"
          autoComplete="off"
          disabled={q.checked}
          value={q.fillVal}
          onChange={(e) => patch({ fillVal: e.target.value })}
          onKeyDown={(e) => {
            if (e.key === "Enter" && q.fillVal.trim() && !q.checked) check();
          }}
        />
      </>
    );
  } else if (ex.type === "sentence_order") {
    body = (
      <>
        <p className="tiny">{t("orderHint")}</p>
        <div className="wordbank" id="zone">
          {q.wordSel.length ? (
            q.wordSel.map((bi) => (
              <button
                key={`sel-${bi}`}
                type="button"
                className="word"
                disabled={q.checked}
                onClick={() =>
                  patch({
                    wordSel: q.wordSel.filter((x) => x !== bi),
                    bank: q.bank.map((b, idx) => (idx === bi ? { ...b, used: false } : b)),
                  })
                }
              >
                {q.bank[bi].w}
              </button>
            ))
          ) : (
            <span className="tiny" style={{ alignSelf: "center" }}>
              {t("orderHint")}
            </span>
          )}
        </div>
        <div className="word-bank" id="bank">
          {q.bank.map((b, i) => (
            <button
              key={`bank-${i}`}
              type="button"
              className="word"
              disabled={b.used || q.checked}
              onClick={() =>
                patch({
                  bank: q.bank.map((x, idx) => (idx === i ? { ...x, used: true } : x)),
                  wordSel: [...q.wordSel, i],
                })
              }
            >
              {b.w}
            </button>
          ))}
        </div>
      </>
    );
  } else {
    body = (
      <>
        <p className="tiny">{t("matchHint")}</p>
        <div className="match-grid">
          <div className="match-col" id="colL">
            {q.lefts.map((l) => (
              <button
                key={`l-${l}`}
                type="button"
                className={`match-item ${q.matchDone[l] ? "done" : ""} ${
                  q.matchPick === l ? "picked" : ""
                }`}
                disabled={!!q.matchDone[l] || q.checked}
                onClick={() => patch({ matchPick: q.matchPick === l ? null : l })}
              >
                {l}
              </button>
            ))}
          </div>
          <div className="match-col" id="colR">
            {q.rights.map((r) => {
              const doneLeft = Object.keys(q.matchDone).find((l) => q.matchDone[l] === r);
              return (
                <button
                  key={`r-${r}`}
                  type="button"
                  className={`match-item ${doneLeft ? "done" : ""}`}
                  disabled={!!doneLeft || q.checked}
                  onClick={() => {
                    if (!q.matchPick) return;
                    const pair = q.pairs.find((p) => p.l === q.matchPick);
                    if (pair && pair.r === r) {
                      const done = { ...q.matchDone, [q.matchPick]: pair.r };
                      if (Object.keys(done).length === q.pairs.length) {
                        patch({ matchDone: done, matchPick: null });
                        submit(q.mistakesQ === 0);
                      } else {
                        patch({ matchDone: done, matchPick: null });
                      }
                    } else {
                      patch({ mistakesQ: q.mistakesQ + 1, matchPick: null });
                    }
                  }}
                >
                  {r}
                </button>
              );
            })}
          </div>
        </div>
      </>
    );
  }

  const correctText = ex.type === "matching" ? ex.options.join("  |  ") : ex.answer;
  const exp = expText(ex, lang);
  const canCheck =
    ex.type === "fill_blank"
      ? !!q.fillVal.trim()
      : ex.type === "sentence_order"
        ? q.wordSel.length > 0
        : q.sel !== null;

  return (
    <>
      <SessionTop index={q.i} total={q.list.length} onExit={askExit} />
      {ctx && <div className="quiz-ctx">{ctx}</div>}
      <p className="eyebrow">
        {q.moduleId
          ? `${t("lessonLbl")} ${q.level} · ${modLabel(ex.module, lang)} · ⭐${ex.points}`
          : `${t("questionLbl")} · ${modLabel(ex.module, lang)} · ⭐${ex.points}`}
      </p>
      <div className="quiz-q">{qText(ex, lang)}</div>
      {body}

      {q.checked ? (
        <div className={`feedback ${q.lastOk ? "ok" : "ko"}`}>
          <div className="fb-title">
            {q.lastOk ? `✅ ${t("correctLbl")}` : `❌ ${t("wrongLbl")}`}
          </div>
          {!q.lastOk && (
            <div className="fb-exp">
              <b>{t("answerLbl")} :</b> {correctText}
            </div>
          )}
          {exp && (
            <div className="fb-exp" style={{ marginTop: 6 }}>
              <b>{t("explainLbl")} :</b> {exp}
            </div>
          )}
          <button type="button" className="btn block" onClick={next}>
            {t("nextLbl")} →
          </button>
        </div>
      ) : (
        ex.type !== "matching" && (
          <button
            type="button"
            className="btn block"
            style={{ marginTop: 16 }}
            disabled={!canCheck}
            onClick={check}
          >
            {t("checkLbl")}
          </button>
        )
      )}
    </>
  );
}
