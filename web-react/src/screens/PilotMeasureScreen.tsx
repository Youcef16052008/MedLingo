import { useEffect, useMemo, useRef, useState } from "react";
import { useT } from "../i18n/useT";
import { useDialog } from "../components/DialogProvider";
import { useToast } from "../components/ToastProvider";
import trainingJson from "../../data/pilot-anatomy.v1.json";
import measureJson from "../../data/pilot-anatomy-measure.v1.json";
import {
  isConceptPublishable,
  validatePilotManifest,
  validatePilotMeasureManifest,
  type PilotValidationReport,
} from "../pilot/content";
import type { PilotManifest } from "../pilot/types";
import {
  advanceMeasure,
  currentMeasureItem,
  finishedMeasureAt,
  initMeasureSession,
  interruptMeasure,
  measureItemsFor,
  measureTimeBlocked,
  recordMeasureAnswer,
  resumeMeasureSession,
  type MeasureItem,
  type MeasureManifest,
  type MeasureTime,
} from "../pilot/measure";
import {
  emptyMeasureState,
  exportMeasureCsv,
  exportMeasureJson,
  loadMeasureState,
  resetMeasureState,
  saveMeasureState,
  upsertMeasureSession,
  type MeasurePersistedState,
} from "../pilot/measureStorage";

/**
 * P3.1/P3.2 — Écran de MESURE du pilote Anatomie (`#/pilot/mesure`).
 *
 * - Séparé de l'écran d'entraînement : AUCUN item de mesure ne peut être
 *   servi par l'entraînement (fichier + ids distincts, garde testée) et
 *   réciproquement — la mesure n'écrit jamais dans la progression.
 * - AUCUN feedback correctif pendant la mesure : après enregistrement,
 *   l'écran passe à l'item suivant sans dire « correct/faux » ni montrer
 *   la réponse. Aucun score n'est affiché pendant le cycle (le résumé
 *   après coup pourrait contaminer T1/T2) — les données partent à l'export.
 * - Aucun chrono stressant : la durée de réponse est observée passivement.
 * - Le même verrou de publication que l'entraînement s'applique : seuls les
 *   concepts approuvés (deux relectures sur la version courante) sont
 *   mesurables en parcours réel ; les drafts restent en aperçu développeur.
 */
const training = trainingJson as unknown as PilotManifest;
const measure = measureJson as unknown as MeasureManifest;

function devPreviewRequested(): boolean {
  if (typeof window === "undefined") return false;
  return window.location.hash.includes("dev=1");
}

function download(name: string, content: string, mime: string) {
  const blob = new Blob([content], { type: mime });
  const url = URL.createObjectURL(blob);
  const a = document.createElement("a");
  a.href = url;
  a.download = name;
  a.click();
  URL.revokeObjectURL(url);
}

export function PilotMeasureScreen() {
  const { t, lang } = useT();
  const toast = useToast();
  const dialog = useDialog();
  const [devPreview] = useState(devPreviewRequested);
  const [input, setInput] = useState("");
  const [pseudonymDraft, setPseudonymDraft] = useState<string | null>(null);

  // ——— Validation des DEUX manifestes (cohérence de version incluse).
  const validation: PilotValidationReport = useMemo(() => {
    try {
      if (training.schemaVersion !== 1 || measure.schemaVersion !== 1) {
        return { ok: false, errors: ["schemaVersion invalide"], warnings: [] };
      }
      if (measure.contentVersion !== training.contentVersion) {
        return {
          ok: false,
          errors: [
            `contentVersion mesure ${measure.contentVersion} ≠ entraînement ${training.contentVersion} — banques à regénérer`,
          ],
          warnings: [],
        };
      }
      const r1 = validatePilotManifest(training);
      const r2 = validatePilotMeasureManifest(measure, training);
      return { ok: r1.ok && r2.ok, errors: [...r1.errors, ...r2.errors], warnings: [...r1.warnings, ...r2.warnings] };
    } catch (e) {
      return { ok: false, errors: [String(e)], warnings: [] };
    }
  }, []);

  // ——— Persistance de mesure (namespace SÉPARÉ de l'entraînement).
  const [persisted, setPersisted] = useState<MeasurePersistedState>(() => loadMeasureState(Date.now()).state);
  const state = persisted;
  const tKey = (k: string) => t(k as never);

  const persist = (next: MeasurePersistedState) => {
    const saved = saveMeasureState(next);
    if (!saved.ok && saved.error) toast(t("measureSaveFailed") + " " + tKey(saved.error));
    setPersisted(next);
  };

  // ——— Concepts mesurables : MÊME verrou de publication que l'entraînement.
  const pool = useMemo(
    () => (devPreview ? training.concepts : training.concepts.filter(isConceptPublishable)),
    [devPreview],
  );

  // ——— Séance de mesure en cours : reprise de la dernière non finie.
  const session = useMemo(() => {
    const open = [...state.sessions].reverse().find((s) => !s.finished && !s.interrupted);
    return open ? resumeMeasureSession(open, measure, Date.now()) : null;
  }, [state.sessions]);

  const items = useMemo(
    () => (session ? measureItemsFor(measure, session.conceptIds, session.time) : []),
    [session],
  );
  const item = session ? currentMeasureItem(session, items) : null;

  // Durée de réponse observée passivement : horodatage de l'affichage de l'item.
  const shownAtRef = useRef<number>(Date.now());
  useEffect(() => {
    shownAtRef.current = Date.now();
  }, [session?.id, session?.i]);

  const blocked = (time: MeasureTime) => measureTimeBlocked(time, state.sessions);

  function start(time: MeasureTime) {
    const b = blocked(time);
    if (b) {
      toast(tKey(b));
      return;
    }
    if (pool.length === 0) {
      toast(t("measureNothingMeasurable"));
      return;
    }
    const s = initMeasureSession(
      time,
      pool.map((c) => c.id),
      training.contentVersion,
      Date.now(),
      finishedMeasureAt(state.sessions, "T1"),
    );
    persist(upsertMeasureSession(state, s));
    setInput("");
  }

  function record(it: MeasureItem, raw: string) {
    if (!session || session.answeredItemId === it.id) return;
    const next = recordMeasureAnswer(session, it, raw, Date.now(), shownAtRef.current);
    const advanced = advanceMeasure(next, items, Date.now());
    persist(upsertMeasureSession(state, advanced));
    setInput("");
  }

  function leave() {
    if (!session) return;
    const next = interruptMeasure(session, Date.now());
    persist(upsertMeasureSession(state, next));
    toast(t("measureInterrupted"));
  }

  function doExportJson() {
    if (state.sessions.length === 0) {
      toast(t("measureNothingToExport"));
      return;
    }
    download(
      "medlingo-pilot-anatomy-measure.json",
      exportMeasureJson(state, training.contentVersion),
      "application/json",
    );
  }

  function doExportCsv() {
    if (state.sessions.length === 0) {
      toast(t("measureNothingToExport"));
      return;
    }
    download("medlingo-pilot-anatomy-measure.csv", exportMeasureCsv(state, training.contentVersion), "text/csv");
  }

  function doReset() {
    dialog
      .open({
        title: t("measureReset"),
        text: t("measureResetConfirm"),
        actions: [
          { id: "yes", label: t("measureReset") },
          { id: "no", label: t("pilotRetry") },
        ],
      })
      .then((choice) => {
        if (choice !== "yes") return;
        const r = resetMeasureState();
        if (!r.ok && r.error) toast(t("measureSaveFailed") + " " + tKey(r.error));
        else toast(t("pilotResetDone"));
        setPersisted(emptyMeasureState(Date.now()));
      });
  }

  /* ——— Erreur manifeste : explicite + reprise possible. */
  if (!validation.ok) {
    return (
      <main className="view" dir={lang === "ar" ? "rtl" : "ltr"}>
        <h1>{t("measureTitle")}</h1>
        <div className="card empty" role="alert">
          <p>{t("pilotManifestError")}</p>
          <pre style={{ whiteSpace: "pre-wrap" }}>{validation.errors.slice(0, 5).join("\n")}</pre>
        </div>
      </main>
    );
  }

  /* ——— Séance de mesure : PAS de verdict, PAS de correction. */
  if (session && !session.finished) {
    const total = items.length;
    const step = session.i + 1;
    return (
      <main className="view" dir={lang === "ar" ? "rtl" : "ltr"}>
        <header>
          <button className="btn ghost" onClick={leave}>
            ←
          </button>
          <span>
            {session.time} · {t("measureItemLbl")} {step} / {total}
          </span>
          {devPreview && (
            <strong style={{ color: "var(--accent-2, darkorange)" }}>{t("pilotDevPreview")}</strong>
          )}
        </header>
        <div className="card">
          <p role="note">{t("measureNoFeedback")}</p>
          {item && (
            <>
              <p style={{ fontWeight: 600 }}>{item.prompt[lang]}</p>
              {item.kind === "mcq" ? (
                <div style={{ display: "grid", gap: 8 }}>
                  {item.options?.map((opt) => (
                    <button key={opt} className="btn ghost" onClick={() => record(item, opt)} dir="ltr">
                      {opt}
                    </button>
                  ))}
                </div>
              ) : (
                <form
                  onSubmit={(e) => {
                    e.preventDefault();
                    record(item, input);
                  }}
                >
                  <input
                    type="text"
                    value={input}
                    onChange={(e) => setInput(e.target.value)}
                    aria-label={t("pilotTypeHere")}
                    placeholder={t("pilotTypeHere")}
                    style={{ width: "100%", padding: "10px 12px", fontSize: "1rem" }}
                    dir="ltr"
                  />
                  <button className="btn primary" type="submit">
                    {t("measureRecord")}
                  </button>
                </form>
              )}
            </>
          )}
        </div>
      </main>
    );
  }

  /* ——— Accueil de la mesure. */
  const finishedTimes = (["T0", "T1", "T2"] as MeasureTime[]).filter((time) =>
    state.sessions.some((s) => s.time === time && s.finished && !s.interrupted),
  );
  const pseudonym = pseudonymDraft ?? state.pseudonym;
  const setPseudonym = (v: string) => {
    setPseudonymDraft(v);
    persist({ ...state, pseudonym: v.trim() });
  };

  return (
    <main className="view" dir={lang === "ar" ? "rtl" : "ltr"}>
      <h1>{t("measureTitle")}</h1>

      {devPreview && (
        <div className="card" role="note" style={{ border: "2px dashed orange" }}>
          <strong>{t("pilotDevPreview")}</strong>
        </div>
      )}

      <div className="card">
        <p>{t("measureIntro")}</p>
        <p role="note">{t("measureNoFeedback")}</p>
        <p role="note">{t("measureNoPersonalData")}</p>
      </div>

      <div className="card">
        <h2>{t("measurePseudonym")}</h2>
        <input
          type="text"
          value={pseudonym}
          onChange={(e) => setPseudonym(e.target.value)}
          placeholder={t("measurePseudonymPh")}
          maxLength={40}
          style={{ width: "100%", padding: "10px 12px", fontSize: "1rem" }}
        />
      </div>

      <div className="card">
        <h2>{t("measureTimes")}</h2>
        {pool.length === 0 && (
          <p role="alert">{t("measureNothingMeasurable")}</p>
        )}
        <ul style={{ listStyle: "none", padding: 0 }}>
          {(["T0", "T1", "T2"] as MeasureTime[]).map((time) => {
            const b = blocked(time);
            const done = finishedTimes.includes(time);
            return (
              <li key={time} style={{ margin: "8px 0", display: "flex", gap: 8, alignItems: "center" }}>
                <strong>{time}</strong>
                <span>{t(time === "T0" ? "measureT0Desc" : time === "T1" ? "measureT1Desc" : "measureT2Desc")}</span>
                {done ? (
                  <span role="status">{t("measureDoneShort")}</span>
                ) : pool.length === 0 ? null : b ? (
                  <span role="note">{tKey(b)}</span>
                ) : (
                  <button className="btn primary" onClick={() => start(time)}>
                    {t("measureStart")}
                  </button>
                )}
              </li>
            );
          })}
        </ul>
        {finishedTimes.length > 0 && <p>{finishedTimes.join(" · ")}</p>}
      </div>

      <div className="card">
        <h2>{t("pilotProgressTitle")}</h2>
        <p role="note">{t("measureConsentNote")}</p>
        <div style={{ display: "flex", gap: 8, flexWrap: "wrap" }}>
          <button className="btn ghost" onClick={doExportJson}>
            {t("measureExportJson")}
          </button>
          <button className="btn ghost" onClick={doExportCsv}>
            {t("measureExportCsv")}
          </button>
          <button className="btn danger" onClick={doReset}>
            {t("measureReset")}
          </button>
        </div>
      </div>
    </main>
  );
}
