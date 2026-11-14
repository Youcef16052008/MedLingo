import { useMemo, useRef, useState } from "react";
import { useT } from "../i18n/useT";
import type { TKey } from "../i18n";
import { useDialog } from "../components/DialogProvider";
import { useToast } from "../components/ToastProvider";
import manifestJson from "../../data/pilot-anatomy.v1.json";
import {
  isConceptPublishable,
  validatePilotManifest,
  type PilotValidationReport,
} from "../pilot/content";
import type { PilotConcept, PilotExercise, PilotManifest } from "../pilot/types";
import {
  advance,
  currentStepNumber,
  initSession,
  recordAnswer,
  resumeSession,
  totalSteps,
  useHelp as useSessionHelp,
  type PilotSessionState,
} from "../pilot/session";
import {
  applyImportedState,
  emptyPilotState,
  exportPilotJson,
  importPilotJson,
  loadPilotState,
  mergeExerciseStat,
  resetPilotState,
  savePilotState,
  type LoadResult,
  type PilotPersistedState,
} from "../pilot/storage";

/**
 * P2.1/P2.2 — Écran du pilote Anatomie.
 *
 * - Accueil : objectif, durée indicative, aides de langue, AUCUNE promesse
 *   clinique. Le manifeste est validé À L'EXÉCUTION ; erreur explicite +
 *   bouton Réessayer (reprise possible).
 * - Verrou de publication au chargement : seuls les concepts `approved`
 *   (les deux relectures sur la version courante) alimentent une séance
 *   réelle — une URL directe ne contourne pas le verrou. Les brouillons ne
 *   sont visibles qu'en aperçu développeur explicite (`?dev=1` dans le hash),
 *   avec bandeau permanent.
 * - Séance courte : nombre d'étapes affiché ; interruption = quitter, la
 *   séance est persistée et reprenable.
 * - Le moteur historique de quiz n'est pas touché ; aucune récompense de
 *   l'app principale n'est distribuée ici.
 */
const manifest = manifestJson as unknown as PilotManifest;

/** Aperçu développeur : opt-in explicite via le hash (`#/pilot?dev=1`). */
function devPreviewRequested(): boolean {
  if (typeof window === "undefined") return false;
  return window.location.hash.includes("dev=1");
}

export function PilotScreen() {
  const { t, lang } = useT();
  const toast = useToast();
  const dialog = useDialog();
  const fileRef = useRef<HTMLInputElement>(null);
  const [reloadTick, setReloadTick] = useState(0);
  const [devPreview] = useState(devPreviewRequested);
  /** Les codes d'erreur du module storage SONT des clés i18n (pilot*). */
  const tKey = (k: string) => t(k as TKey);

  // ——— Manifeste : validation d'exécution (schéma, ids, verrous, fuites).
  const validation: PilotValidationReport = useMemo(() => {
    void reloadTick; // Réessayer re-valide.
    try {
      if (manifest.schemaVersion !== 1) {
        return { ok: false, errors: [`schemaVersion ${String(manifest.schemaVersion)} ≠ 1`], warnings: [] };
      }
      return validatePilotManifest(manifest);
    } catch (e) {
      return { ok: false, errors: [String(e)], warnings: [] };
    }
  }, [reloadTick]);

  // ——— Persistance isolée (chargement tolérant + message si corrompu).
  const [persisted, setPersisted] = useState<LoadResult>(() => loadPilotState(manifest, Date.now()));
  const state: PilotPersistedState = persisted.state;
  const persist = (next: PilotPersistedState): PilotPersistedState => {
    const saved = savePilotState(next);
    if (!saved.ok && saved.error) toast(t("pilotSaveFailed") + " " + tKey(saved.error));
    const shown: LoadResult = { state: next, warning: persisted.warning };
    setPersisted(shown);
    return next;
  };

  // ——— Séance en cours (composant) : démarrée, reprise ou nulle.
  const [session, setSession] = useState<PilotSessionState | null>(null);
  const resumable = useMemo(
    () => resumeSession(state.lastSession, manifest, Date.now()),
    [state.lastSession],
  );

  const approvedCount = manifest.concepts.filter(isConceptPublishable).length;
  const previewMode = devPreview && validation.ok;
  const pool = previewMode
    ? manifest.concepts
    : manifest.concepts.filter(isConceptPublishable);

  // Saisie de l'exercice courant (transitoire, jamais persistée).
  const [input, setInput] = useState("");

  function startSession(resume: PilotSessionState | null) {
    const s = resume ?? initSession(pool, manifest.contentVersion, Date.now());
    setSession(s);
    persist({ ...state, lastSession: s });
    setInput("");
  }

  function currentConcept(s: PilotSessionState): PilotConcept | undefined {
    return manifest.concepts.find((c) => c.id === s.conceptIds[s.i]);
  }

  function commitAnswer(ex: PilotExercise, s: PilotSessionState, raw: string) {
    const now = Date.now();
    const next = recordAnswer(s, ex, raw, now);
    if (next === s) return; // double soumission : idempotent
    const conceptId = s.conceptIds[s.i];
    const fr = next.firstResults[ex.id];
    const firstResult: "first_try" | "helped" | "error" =
      fr === "first_try" ? "first_try" : fr === "helped" ? "helped" : "error";
    const withStats = mergeExerciseStat(
      state,
      {
        conceptId,
        exerciseId: ex.id,
        attempts: (next.attempts[ex.id] ?? 1) - (state.exerciseStats[ex.id]?.attempts ?? 0),
        firstResult,
        helped: next.helped.includes(ex.id),
        lastAt: now,
      },
      now,
    );
    setSession(next);
    persist({ ...withStats, lastSession: next });
  }

  function continueSession() {
    if (!session) return;
    const next = advance(session, manifest, Date.now());
    setSession(next);
    persist({ ...state, lastSession: next });
    setInput("");
    if (next.finished) {
      toast(t("pilotNoMastery"));
    }
  }

  function doHelp(ex: PilotExercise, s: PilotSessionState) {
    const next = useSessionHelp(s, ex.id, Date.now());
    setSession(next);
    persist({ ...state, lastSession: next });
  }

  function doExport() {
    const blob = new Blob([exportPilotJson(state)], { type: "application/json" });
    const url = URL.createObjectURL(blob);
    const a = document.createElement("a");
    a.href = url;
    a.download = "medlingo-pilot-anatomy-progress.json";
    a.click();
    URL.revokeObjectURL(url);
  }

  function onImportFile(file: File) {
    file.text().then((text) => {
      const result = importPilotJson(text, manifest, Date.now());
      if (!result.ok) {
        toast(tKey(result.error));
        return;
      }
      dialog
        .open({
          title: t("pilotImport"),
          text: t("pilotImportConfirm"),
          actions: [
            { id: "yes", label: t("pilotContinue") },
            { id: "no", label: t("pilotRetry") },
          ],
        })
        .then((choice) => {
          if (choice !== "yes") return;
          const applied = applyImportedState(state, result.state);
          if (!applied.ok && applied.error) {
            toast(t("pilotSaveFailed") + " " + tKey(applied.error));
            return;
          }
          setPersisted(loadPilotState(manifest, Date.now()));
          setSession(null);
          if (result.note) toast(t("pilotVersionNote"));
          else toast(t("pilotImportedOk"));
        });
    });
  }

  function doReset() {
    dialog
      .open({
        title: t("pilotReset"),
        text: t("pilotResetConfirm"),
        actions: [
          { id: "yes", label: t("pilotReset") },
          { id: "no", label: t("pilotRetry") },
        ],
      })
      .then((choice) => {
        if (choice !== "yes") return;
        const r = resetPilotState();
        if (!r.ok && r.error) toast(t("pilotSaveFailed") + " " + tKey(r.error));
        else toast(t("pilotResetDone"));
        setPersisted({ state: emptyPilotState(Date.now()), warning: null });
        setSession(null);
      });
  }

  /* ——— Erreur manifeste : explicite + reprise possible. */
  if (!validation.ok) {
    return (
      <main className="view" dir={lang === "ar" ? "rtl" : "ltr"}>
        <h1>{t("pilotTitle")}</h1>
        <div className="card empty" role="alert">
          <p>{t("pilotManifestError")}</p>
          <pre style={{ whiteSpace: "pre-wrap" }}>{validation.errors.slice(0, 5).join("\n")}</pre>
          <button className="btn ghost" onClick={() => setReloadTick((n) => n + 1)}>
            {t("pilotRetry")}
          </button>
        </div>
      </main>
    );
  }

  /* ——— Séance. */
  if (session && !session.finished) {
    const concept = currentConcept(session);
    const ex = concept?.exercises[session.exIdx];
    if (!concept || !ex) {
      return (
        <main className="view">
          <div className="card empty">{t("pilotManifestError")}</div>
        </main>
      );
    }
    const answered = session.answeredExerciseId === ex.id;
    const wasHelped = session.helped.includes(ex.id);
    const verdict = answered ? session.firstResults[ex.id] : null;
    const total = totalSteps(session, manifest);
    const step = currentStepNumber(session, manifest);

    return (
      <main className="view" dir={lang === "ar" ? "rtl" : "ltr"}>
        <header>
          <button className="btn ghost" onClick={() => setSession(null)}>
            ←
          </button>
          <span>
            {t("pilotStepLbl")} {step} / {total}
          </span>
          {previewMode && <strong style={{ color: "var(--accent-2, darkorange)" }}>{t("pilotDevPreview")}</strong>}
        </header>
        <div className="card">
          {/* labelEn = terme anglais : toujours LTR, même en interface RTL (P2.4). */}
          <h2 dir="ltr" style={{ textAlign: "start" }}>{concept.labelEn}</h2>
          {/* Consigne localisée du manifeste (source P1.1). */}
          <p style={{ fontWeight: 600 }}>{ex.prompt[lang]}</p>

          {ex.kind === "mcq" ? (
            <div style={{ display: "grid", gap: 8 }}>
              {ex.options?.map((opt) => (
                <button
                  key={opt}
                  className="btn"
                  disabled={answered}
                  onClick={() => commitAnswer(ex, session, opt)}
                  dir="ltr"
                >
                  {opt}
                </button>
              ))}
            </div>
          ) : (
            <form
              onSubmit={(e) => {
                e.preventDefault();
                if (!answered) commitAnswer(ex, session, input);
              }}
            >
              <input
                type="text"
                value={input}
                onChange={(e) => setInput(e.target.value)}
                disabled={answered}
                aria-label={t("pilotTypeHere")}
                placeholder={t("pilotTypeHere")}
                style={{ width: "100%", padding: "10px 12px", fontSize: "1rem" }}
                dir="ltr"
              />
              <button className="btn primary" type="submit" disabled={answered}>
                {t("storyCheck")}
              </button>
            </form>
          )}

          {/* Aide de langue : révèle les aides FR/AR du concept — jamais avant
              la réponse comptée comme autonome. */}
          {!answered && (
            <div style={{ marginTop: 8 }}>
              <button className="btn ghost" onClick={() => doHelp(ex, session)}>
                {t("pilotHelp")}
              </button>
              {wasHelped && (
                <p role="note">
                  {t("pilotHelpNote")} ·{" "}
                  {/* Les aides FR/AR sont l'aide de langue du concept ; en
                      interface EN, l'aide affichée reste la traduction FR. */}
                  {concept.hints[lang === "en" ? "fr" : lang]}
                </p>
              )}
            </div>
          )}

          {/* Feedback : verdict EN TEXTE (jamais la couleur seule) + explication
              du sens (défin du manifeste, localisée). */}
          {answered && verdict && (
            <div role="status" style={{ marginTop: 12 }}>
              <p>
                <strong>
                  {verdict === "first_try" ? t("pilotCorrect") : verdict === "helped" ? t("pilotCorrect") + " · " + t("pilotHelp") : t("pilotWrong")}
                </strong>
              </p>
              {verdict !== "first_try" && <p dir="ltr" style={{ textAlign: "start" }}>{ex.answer}</p>}
              <p>{ex.explanation[lang]}</p>
              <button className="btn primary" onClick={continueSession}>
                {t("pilotContinue")}
              </button>
            </div>
          )}
        </div>
      </main>
    );
  }

  /* ——— Fin de séance : descriptif, sans promesse de maîtrise. */
  if (session?.finished) {
    const worked = session.conceptIds;
    const errorConceptIds = [
      ...new Set(
        session.errors
          .map((id) => manifest.concepts.find((c) => c.exercises.some((e) => e.id === id))?.id)
          .filter((x): x is string => Boolean(x)),
      ),
    ];
    return (
      <main className="view" dir={lang === "ar" ? "rtl" : "ltr"}>
        <h1>{t("pilotTitle")}</h1>
        <div className="card">
          <h2>{t("pilotConceptsWorked")}</h2>
          <ul>
            {worked.map((id) => (
              <li key={id}>{manifest.concepts.find((c) => c.id === id)?.labelEn ?? id}</li>
            ))}
          </ul>
          <p>
            <strong>{t("storySummaryFirstTry")} :</strong> {session.autonomous.length}
          </p>
          <p>
            <strong>{t("storySummaryHelped")} :</strong> {session.helped.length}
          </p>
          {errorConceptIds.length > 0 && (
            <div>
              <p>
                <strong>{t("pilotRevisions")} :</strong>
              </p>
              <ul>
                {errorConceptIds.map((id) => (
                  <li key={id}>{manifest.concepts.find((c) => c.id === id)?.labelEn ?? id}</li>
                ))}
              </ul>
            </div>
          )}
          <p role="note">{t("pilotNoMastery")}</p>
          <button className="btn primary" onClick={() => { setSession(null); persist({ ...state, lastSession: session }); }}>
            {t("pilotContinue")}
          </button>
        </div>
      </main>
    );
  }

  /* ——— Accueil. */
  const workedCount = Object.keys(state.exerciseStats).length;
  const storageWarning = persisted.warning ? tKey(persisted.warning) : null;

  return (
    <main className="view" dir={lang === "ar" ? "rtl" : "ltr"}>
      <h1>{t("pilotTitle")}</h1>

      {devPreview && (
        <div className="card" role="note" style={{ border: "2px dashed orange" }}>
          <strong>{t("pilotDevPreview")}</strong>
        </div>
      )}
      {storageWarning && (
        <div className="card empty" role="alert">
          {storageWarning}
        </div>
      )}

      <div className="card">
        <p>{t("pilotObjective")}</p>
        <p>{t("pilotDuration")}</p>
        <p>{t("pilotLangAids")}</p>
        <p role="note">{t("pilotNoClinic")}</p>
      </div>

      <div className="card">
        <h2>{t("pilotProgressTitle")}</h2>
        <p>
          {approvedCount} / {manifest.concepts.length} · {t("pilotReviewPending")}
        </p>
        <p>
          {workedCount} · {t("pilotConceptsWorked")}
        </p>
        {pool.length === 0 ? (
          <p role="alert">{t("pilotNothingApproved")}</p>
        ) : (
          <div style={{ display: "flex", gap: 8, flexWrap: "wrap" }}>
            {resumable && (
              <button className="btn primary" onClick={() => startSession(resumable)}>
                {t("pilotResume")}
              </button>
            )}
            <button className="btn primary" onClick={() => startSession(null)}>
              {t("pilotStart")}
            </button>
          </div>
        )}
      </div>

      <div className="card">
        <h2>{t("measureTitle")}</h2>
        <p>{t("measureIntro")}</p>
        {/* P3.1 — la mesure est un parcours SÉPARÉ : aucun item de mesure
            n'est servi par l'entraînement, et l'entraînement n'écrit jamais
            dans les données de mesure. */}
        <a className="btn" href="#/pilot/mesure">
          {t("measureOpen")}
        </a>
      </div>

      <div className="card">
        <div style={{ display: "flex", gap: 8, flexWrap: "wrap" }}>
          <button className="btn ghost" onClick={doExport}>
            {t("pilotExport")}
          </button>
          <button className="btn ghost" onClick={() => fileRef.current?.click()}>
            {t("pilotImport")}
          </button>
          <button className="btn danger" onClick={doReset}>
            {t("pilotReset")}
          </button>
        </div>
        <input
          ref={fileRef}
          type="file"
          accept="application/json"
          hidden
          onChange={(e) => {
            const f = e.target.files?.[0];
            if (f) onImportFile(f);
            e.target.value = "";
          }}
        />
      </div>
    </main>
  );
}
