/**
 * P3.2 — Persistance et EXPORT de la mesure du pilote Anatomie.
 *
 * - Namespace PROPRE ET SÉPARÉ de l'entraînement : clé
 *   `medlingo_pilot_anatomy_measure_v1`. Le reset de mesure ne touche jamais
 *   la progression d'entraînement (testé), et réciproquement : la mesure
 *   n'est jamais réinjectée dans SM-2 (aucun import de module de progression).
 * - Pseudonyme : identifiant choisi/éditable pour le pilote, SANS email, nom
 *   ni donnée patient ; il n'y a AUCUNE donnée personnelle dans l'export.
 * - Export JSON versionné + CSV lisible. La transmission est MANUELLE et le
 *   consentement est demandé À L'ÉCRAN avant tout partage — rien n'est envoyé
 *   automatiquement (pas de télémétrie).
 * - Données exportées (P3.2) : temps de mesure (T0/T1/T2), version
 *   contenu/test, itemId, résultat, aides autorisées (aucune pendant la
 *   mesure : champ explicite `aidsUsed: false`), durée observée, état
 *   terminé/interrompu, délai réel T1→T2.
 * - Corrompu / quota / schéma inconnu : messages explicites, jamais un
 *   « enregistré » mensonger.
 */
import type { MeasureSessionState, MeasureTime } from "./measure";
import { MEASURE_SCHEMA_VERSION, measureScore } from "./measure";

export const MEASURE_STORAGE_KEY = "medlingo_pilot_anatomy_measure_v1";
export const MEASURE_BACKUP_KEY = "medlingo_pilot_anatomy_measure_v1_backup";

export interface MeasurePersistedState {
  schemaVersion: 1;
  updatedAt: number;
  /** Identifiant pseudonyme du participant — éditable, jamais déduit de données personnelles. */
  pseudonym: string;
  /** Toutes les séances de mesure (finies OU interrompues) — l'historique est conservé. */
  sessions: MeasureSessionState[];
}

export interface MeasureLoadResult {
  state: MeasurePersistedState;
  /** Clé i18n du warning (corrompu/schéma) ou null. */
  warning: string | null;
}

export function emptyMeasureState(now: number): MeasurePersistedState {
  return { schemaVersion: 1, updatedAt: now, pseudonym: "", sessions: [] };
}

function coerceTime(v: unknown): MeasureTime | null {
  return v === "T0" || v === "T1" || v === "T2" ? v : null;
}

function coerceSessions(v: unknown, now: number): MeasureSessionState[] {
  if (!Array.isArray(v)) return [];
  const out: MeasureSessionState[] = [];
  for (const raw of v) {
    if (!raw || typeof raw !== "object") continue;
    const s = raw as Partial<MeasureSessionState>;
    const time = coerceTime(s.time);
    if (!time || s.schemaVersion !== MEASURE_SCHEMA_VERSION) continue;
    if (typeof s.id !== "string" || !Array.isArray(s.conceptIds)) continue;
    out.push({
      schemaVersion: MEASURE_SCHEMA_VERSION,
      id: s.id,
      time,
      version: typeof s.version === "string" ? s.version : "",
      contentVersion: typeof s.contentVersion === "string" ? s.contentVersion : "",
      conceptIds: s.conceptIds.filter((x): x is string => typeof x === "string"),
      startedAt: typeof s.startedAt === "number" ? s.startedAt : now,
      updatedAt: typeof s.updatedAt === "number" ? s.updatedAt : now,
      i: typeof s.i === "number" && s.i >= 0 ? s.i : 0,
      results: Array.isArray(s.results)
        ? s.results
            .filter(
              (r): r is NonNullable<MeasureSessionState["results"][number]> =>
                !!r && typeof r === "object" && typeof (r as { itemId?: unknown }).itemId === "string",
            )
            .map((r) => ({
              itemId: r.itemId,
              conceptId: typeof r.conceptId === "string" ? r.conceptId : "",
              dimension: r.dimension,
              correct: r.correct === true || r.correct === false ? r.correct : null,
              invalidatedReason: typeof r.invalidatedReason === "string" ? r.invalidatedReason : null,
              answeredAt: typeof r.answeredAt === "number" ? r.answeredAt : null,
              durationMs: typeof r.durationMs === "number" ? r.durationMs : null,
            }))
        : [],
      answeredItemId: typeof s.answeredItemId === "string" ? s.answeredItemId : null,
      finished: s.finished === true,
      interrupted: s.interrupted === true,
      actualDelayFromT1Ms: typeof s.actualDelayFromT1Ms === "number" ? s.actualDelayFromT1Ms : null,
    });
  }
  return out;
}

/** Charge l'état de mesure. Ne JAMAIS throw : état sain + warning. */
export function loadMeasureState(now: number): MeasureLoadResult {
  let raw: string | null = null;
  try {
    raw = localStorage.getItem(MEASURE_STORAGE_KEY);
  } catch {
    return { state: emptyMeasureState(now), warning: "pilotStorageUnavailable" };
  }
  if (!raw) return { state: emptyMeasureState(now), warning: null };
  let parsed: unknown;
  try {
    parsed = JSON.parse(raw);
  } catch {
    return { state: emptyMeasureState(now), warning: "pilotDataCorrupted" };
  }
  if (!parsed || typeof parsed !== "object") return { state: emptyMeasureState(now), warning: "pilotDataCorrupted" };
  const p = parsed as Record<string, unknown>;
  if (p.schemaVersion !== 1) return { state: emptyMeasureState(now), warning: "pilotSchemaChanged" };
  return {
    state: {
      schemaVersion: 1,
      updatedAt: typeof p.updatedAt === "number" ? p.updatedAt : now,
      pseudonym: typeof p.pseudonym === "string" ? p.pseudonym : "",
      sessions: coerceSessions(p.sessions, now),
    },
    warning: null,
  };
}

/** Sauvegarde. Code d'erreur explicite — jamais de « ok » mensonger. */
export function saveMeasureState(state: MeasurePersistedState): { ok: boolean; error: string | null } {
  try {
    localStorage.setItem(MEASURE_STORAGE_KEY, JSON.stringify(state));
    return { ok: true, error: null };
  } catch (e) {
    const quota = e instanceof DOMException && (e.name === "QuotaExceededError" || e.code === 22);
    return { ok: false, error: quota ? "pilotStorageQuota" : "pilotStorageUnavailable" };
  }
}

/** Remplace/ajoute la séance (même id → remplacée), persiste. */
export function upsertMeasureSession(state: MeasurePersistedState, session: MeasureSessionState): MeasurePersistedState {
  const sessions = [...state.sessions.filter((s) => s.id !== session.id), session];
  return { ...state, updatedAt: session.updatedAt, sessions };
}

/* ------------------------------ export P3.2 ------------------------------ */

const MEASURE_EXPORT_KIND = "medlingo-pilot-anatomy-measure";

/** Export JSON versionné : pseudonyme + séances brutes (données exigées P3.2). */
export function exportMeasureJson(state: MeasurePersistedState, contentVersion: string): string {
  return JSON.stringify(
    {
      kind: MEASURE_EXPORT_KIND,
      schemaVersion: 1,
      exportedAt: new Date().toISOString(),
      contentVersion,
      pseudonym: state.pseudonym,
      /** Aides pendant la mesure : interdites par le protocole (champ explicite). */
      aidsUsedDuringMeasure: false,
      sessions: state.sessions,
    },
    null,
    2,
  );
}

/** Ligne CSV : une ligne PAR ITEM traité (dénominateur explicite par ligne). */
const CSV_HEADER = [
  "pseudonym",
  "contentVersion",
  "sessionId",
  "time",
  "testVersion",
  "startedAt",
  "state",
  "actualDelayFromT1Ms",
  "itemId",
  "conceptId",
  "dimension",
  "correct",
  "invalidatedReason",
  "durationMs",
] as const;

function csvCell(v: unknown): string {
  const s = v === null || v === undefined ? "" : String(v);
  return /[",\n;]/.test(s) ? `"${s.replace(/"/g, '""')}"` : s;
}

export function exportMeasureCsv(state: MeasurePersistedState, contentVersion: string): string {
  const lines = [CSV_HEADER.join(",")];
  for (const s of state.sessions) {
    const stateLabel = s.finished ? (s.interrupted ? "interrupted_finished" : "finished") : "in_progress";
    // Séance sans item traité : ligne de séance seule (JAMAIS un faux score 0).
    if (s.results.length === 0) {
      lines.push(
        [
          state.pseudonym,
          contentVersion,
          s.id,
          s.time,
          s.version,
          new Date(s.startedAt).toISOString(),
          stateLabel,
          s.actualDelayFromT1Ms ?? "",
          "", "", "", "", "", "",
        ]
          .map(csvCell)
          .join(","),
      );
      continue;
    }
    for (const r of s.results) {
      lines.push(
        [
          state.pseudonym,
          contentVersion,
          s.id,
          s.time,
          s.version,
          new Date(s.startedAt).toISOString(),
          stateLabel,
          s.actualDelayFromT1Ms ?? "",
          r.itemId,
          r.conceptId,
          r.dimension,
          r.invalidatedReason ? "invalid" : r.correct === null ? "unanswered" : r.correct ? "1" : "0",
          r.invalidatedReason ?? "",
          r.durationMs ?? "",
        ]
          .map(csvCell)
          .join(","),
      );
    }
  }
  return lines.join("\n") + "\n";
}

/** Résumé lisible (analyse locale, affichage écran) — dénominateurs explicites. */
export function measureSummary(state: MeasurePersistedState): Array<{
  time: MeasureTime;
  sessionId: string;
  startedAt: number;
  finished: boolean;
  interrupted: boolean;
  score: ReturnType<typeof measureScore>["total"];
  delayFromT1Ms: number | null;
}> {
  return state.sessions
    .filter((s) => s.finished)
    .map((s) => ({
      time: s.time,
      sessionId: s.id,
      startedAt: s.startedAt,
      finished: s.finished,
      interrupted: s.interrupted,
      score: measureScore(s).total,
      delayFromT1Ms: s.actualDelayFromT1Ms,
    }));
}

export type MeasureImportResult =
  | { ok: true; state: MeasurePersistedState; note: string | null }
  | { ok: false; error: "pilotImportInvalid" | "pilotImportSchema" | "pilotImportVersion" };

/** Import validé ; version de contenu différente = refusée (mesure non comparable). */
export function importMeasureJson(text: string, contentVersion: string, now: number): MeasureImportResult {
  let parsed: unknown;
  try {
    parsed = JSON.parse(text);
  } catch {
    return { ok: false, error: "pilotImportInvalid" };
  }
  if (!parsed || typeof parsed !== "object") return { ok: false, error: "pilotImportInvalid" };
  const outer = parsed as Record<string, unknown>;
  if (outer.kind !== MEASURE_EXPORT_KIND) return { ok: false, error: "pilotImportInvalid" };
  if (outer.schemaVersion !== 1) return { ok: false, error: "pilotImportSchema" };
  if (outer.contentVersion !== contentVersion) return { ok: false, error: "pilotImportVersion" };
  const imported: MeasurePersistedState = {
    schemaVersion: 1,
    updatedAt: now,
    pseudonym: typeof outer.pseudonym === "string" ? outer.pseudonym : "",
    sessions: coerceSessions(outer.sessions, now),
  };
  return { ok: true, state: imported, note: null };
}

/** Import avec sauvegarde préalable (confirmation décidée à l'écran). */
export function applyMeasureImport(
  current: MeasurePersistedState,
  imported: MeasurePersistedState,
): { ok: boolean; error: string | null } {
  try {
    localStorage.setItem(MEASURE_BACKUP_KEY, JSON.stringify(current));
  } catch (e) {
    const quota = e instanceof DOMException && (e.name === "QuotaExceededError" || e.code === 22);
    return { ok: false, error: quota ? "pilotStorageQuota" : "pilotStorageUnavailable" };
  }
  return saveMeasureState(imported);
}

/** Restaure la sauvegarde précédente après un import indésirable. */
export function restoreMeasureBackup(now: number): MeasureLoadResult {
  try {
    const raw = localStorage.getItem(MEASURE_BACKUP_KEY);
    if (!raw) return { state: emptyMeasureState(now), warning: "pilotNoBackup" };
    localStorage.setItem(MEASURE_STORAGE_KEY, raw);
  } catch {
    return { state: emptyMeasureState(now), warning: "pilotStorageUnavailable" };
  }
  return loadMeasureState(now);
}

/** Reset de la MESURE uniquement — progression d'entraînement intacte. */
export function resetMeasureState(): { ok: boolean; error: string | null } {
  try {
    localStorage.removeItem(MEASURE_STORAGE_KEY);
    localStorage.removeItem(MEASURE_BACKUP_KEY);
    return { ok: true, error: null };
  } catch {
    return { ok: false, error: "pilotStorageUnavailable" };
  }
}
