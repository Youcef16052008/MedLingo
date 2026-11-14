/**
 * P2.3 — Persistance ISOLÉE du pilote Anatomie.
 *
 * - Namespace et schemaVersion PROPRES : clé `medlingo_pilot_anatomy_v1`,
 *   distincte du store global (`medlingo_web_v1_*`). Les préférences globales
 *   de langue/thème restent dans le store global — on ne les duplique pas.
 * - Enregistré : contentVersion, sessionId, conceptId, exerciseId, horodatage,
 *   premier résultat, aides, tentatives et état de séance.
 * - Horloge TOUJOURS injectée ; aucun calcul de date localisé (les horodatages
 *   sont des epoch ms ; l'affichage des jours est de la responsabilité de
 *   l'écran, via Intl sur un number, jamais une comparaison de chaînes).
 * - Export/import VALIDÉ ; confirmation avant remplacement (écran) ;
 *   sauvegarde précédente conservée dans une clé `_backup` avant import.
 * - Reset pilote distinct du reset global (clé dédiée).
 * - Données corrompues / quota / stockage indisponible : message explicite via
 *   le retour de fonction ; on n'annonce JAMAIS une sauvegarde qui a échoué.
 * - Les ids pilote sont TEXTUELS : ce module ne transmet jamais ces ids à un
 *   contrat numérique (pas de SM-2 ici ; « révisions proposées » est dérivé
 *   des erreurs, pas écrit dans la file de révision globale).
 */
import type { PilotFirstResult } from "./session";
import type { PilotSessionState } from "./session";
import type { PilotManifest } from "./types";

export const PILOT_STORAGE_KEY = "medlingo_pilot_anatomy_v1";
export const PILOT_BACKUP_KEY = "medlingo_pilot_anatomy_v1_backup";
export const PILOT_STATE_SCHEMA_VERSION = 1;

/** Statistiques agrégées par exercice (fields exigés par le plan P2.3). */
export interface PilotExerciseStat {
  conceptId: string;
  exerciseId: string;
  attempts: number;
  firstResult: PilotFirstResult;
  helped: boolean;
  lastAt: number;
}

export interface PilotPersistedState {
  schemaVersion: 1;
  updatedAt: number;
  /** Version de contenu avec laquelle ces stats ont été produites. */
  contentVersion: string | null;
  /** Dernière séance (état de séance pour reprise). */
  lastSession: PilotSessionState | null;
  /** Statistiques par exercice — clés TEXTUELLES, jamais numériques. */
  exerciseStats: Record<string, PilotExerciseStat>;
}

export interface LoadResult {
  state: PilotPersistedState;
  /** Message explicite si les données ont été jugées corrompues/rejetées. */
  warning: string | null;
}

export function emptyPilotState(now: number): PilotPersistedState {
  return {
    schemaVersion: PILOT_STATE_SCHEMA_VERSION,
    updatedAt: now,
    contentVersion: null,
    lastSession: null,
    exerciseStats: {},
  };
}

/* ---------- revivification défensive (pattern store.ts, réduit) ---------- */

function coerceRecordStringNumber(v: unknown): Record<string, number> | null {
  if (!v || typeof v !== "object" || Array.isArray(v)) return null;
  const out: Record<string, number> = {};
  for (const [k, n] of Object.entries(v as Record<string, unknown>)) {
    if (typeof n === "number" && Number.isFinite(n)) out[k] = n;
  }
  return out;
}

function coerceSession(raw: unknown, manifest: PilotManifest, now: number): PilotSessionState | null {
  if (!raw || typeof raw !== "object") return null;
  const s = raw as Partial<PilotSessionState>;
  if (s.schemaVersion !== 1 || typeof s.id !== "string") return null;
  if (s.contentVersion !== manifest.contentVersion) return null; // changement de version → invalidé
  if (s.finished === true) return s as PilotSessionState;
  const ids = Array.isArray(s.conceptIds) ? s.conceptIds.filter((x): x is string => typeof x === "string") : [];
  if (ids.length === 0) return null;
  const known = new Set(manifest.concepts.map((c) => c.id));
  if (!ids.every((id) => known.has(id))) return null;
  return {
    schemaVersion: 1,
    id: s.id,
    startedAt: typeof s.startedAt === "number" ? s.startedAt : now,
    updatedAt: now,
    contentVersion: s.contentVersion,
    conceptIds: ids,
    i: typeof s.i === "number" && s.i >= 0 && s.i < ids.length ? s.i : 0,
    exIdx: typeof s.exIdx === "number" && s.exIdx >= 0 ? s.exIdx : 0,
    attempts: coerceRecordStringNumber(s.attempts) ?? {},
    firstResults:
      s.firstResults && typeof s.firstResults === "object" ? (s.firstResults as Record<string, PilotFirstResult>) : {},
    autonomous: Array.isArray(s.autonomous) ? s.autonomous.filter((x): x is string => typeof x === "string") : [],
    helped: Array.isArray(s.helped) ? s.helped.filter((x): x is string => typeof x === "string") : [],
    errors: Array.isArray(s.errors) ? s.errors.filter((x): x is string => typeof x === "string") : [],
    answeredExerciseId: typeof s.answeredExerciseId === "string" ? s.answeredExerciseId : null,
    finished: false,
  };
}

function coerceExerciseStats(v: unknown): Record<string, PilotExerciseStat> {
  if (!v || typeof v !== "object" || Array.isArray(v)) return {};
  const out: Record<string, PilotExerciseStat> = {};
  for (const [k, raw] of Object.entries(v as Record<string, unknown>)) {
    if (!raw || typeof raw !== "object") continue;
    const s = raw as Partial<PilotExerciseStat>;
    if (typeof s.conceptId !== "string" || typeof s.exerciseId !== "string") continue;
    if (s.firstResult !== "first_try" && s.firstResult !== "helped" && s.firstResult !== "error") continue;
    out[k] = {
      conceptId: s.conceptId,
      exerciseId: s.exerciseId,
      attempts: typeof s.attempts === "number" && Number.isFinite(s.attempts) ? s.attempts : 1,
      firstResult: s.firstResult,
      helped: s.helped === true,
      lastAt: typeof s.lastAt === "number" ? s.lastAt : 0,
    };
  }
  return out;
}

/** Charge l'état persisté. Ne JAMAIS throw : retourne un état sain + warning. */
export function loadPilotState(manifest: PilotManifest, now: number): LoadResult {
  let raw: string | null = null;
  try {
    raw = localStorage.getItem(PILOT_STORAGE_KEY);
  } catch {
    return { state: emptyPilotState(now), warning: "pilotStorageUnavailable" };
  }
  if (!raw) return { state: emptyPilotState(now), warning: null };
  let parsed: unknown;
  try {
    parsed = JSON.parse(raw);
  } catch {
    return { state: emptyPilotState(now), warning: "pilotDataCorrupted" };
  }
  if (!parsed || typeof parsed !== "object") {
    return { state: emptyPilotState(now), warning: "pilotDataCorrupted" };
  }
  const p = parsed as Record<string, unknown>;
  if (p.schemaVersion !== PILOT_STATE_SCHEMA_VERSION) {
    // Évolution de schéma : pas de migration silencieuse pour le pilote v1.
    return { state: emptyPilotState(now), warning: "pilotSchemaChanged" };
  }
  const state: PilotPersistedState = {
    schemaVersion: PILOT_STATE_SCHEMA_VERSION,
    updatedAt: typeof p.updatedAt === "number" ? p.updatedAt : now,
    contentVersion: typeof p.contentVersion === "string" ? p.contentVersion : null,
    lastSession: coerceSession(p.lastSession, manifest, now),
    exerciseStats: coerceExerciseStats(p.exerciseStats),
  };
  return { state, warning: null };
}

/** Sauvegarde. Retourne un code d'erreur explicite — jamais de promesse mensongère. */
export function savePilotState(state: PilotPersistedState): { ok: boolean; error: string | null } {
  try {
    localStorage.setItem(PILOT_STORAGE_KEY, JSON.stringify(state));
    return { ok: true, error: null };
  } catch (e) {
    // Quota dépassé ou stockage indisponible : message distinct, pas de « ok ».
    const quota = e instanceof DOMException && (e.name === "QuotaExceededError" || e.code === 22);
    return { ok: false, error: quota ? "pilotStorageQuota" : "pilotStorageUnavailable" };
  }
}

/**
 * Fusionne le résultat d'un exercice dans les stats persistées (appelé par
 * l'écran après `recordAnswer`). Toutes les données exigées par P2.3 y sont.
 */
export function mergeExerciseStat(
  state: PilotPersistedState,
  stat: PilotExerciseStat,
  now: number,
): PilotPersistedState {
  const prev = state.exerciseStats[stat.exerciseId];
  return {
    ...state,
    updatedAt: now,
    exerciseStats: {
      ...state.exerciseStats,
      [stat.exerciseId]: prev
        ? { ...prev, attempts: prev.attempts + stat.attempts, lastAt: stat.lastAt }
        : stat,
    },
  };
}

/* ------------------------------ export / import ------------------------------ */

/** Export JSON versionné (sérialisation locale, consentement à l'écran). */
export function exportPilotJson(state: PilotPersistedState): string {
  return JSON.stringify(
    {
      kind: "medlingo-pilot-anatomy-progress",
      exportedAt: new Date().toISOString(),
      state,
    },
    null,
    2,
  );
}

export type ImportResult =
  | { ok: true; state: PilotPersistedState; note: string | null }
  | { ok: false; error: "pilotImportInvalid" | "pilotImportSchema" };

/**
 * Importe un JSON d'export. Validation stricte ; la version de contenu
 * DIFFÉRENTE est acceptée mais signalée (`note`) — les stats par exerciseId
 * restent des ids textuels stables, la séance en cours EST INVALIDÉE (le
 * contenu a pu changer).
 */
export function importPilotJson(text: string, manifest: PilotManifest, now: number): ImportResult {
  let parsed: unknown;
  try {
    parsed = JSON.parse(text);
  } catch {
    return { ok: false, error: "pilotImportInvalid" };
  }
  if (!parsed || typeof parsed !== "object") return { ok: false, error: "pilotImportInvalid" };
  const outer = parsed as Record<string, unknown>;
  if (outer.kind !== "medlingo-pilot-anatomy-progress") return { ok: false, error: "pilotImportInvalid" };
  const inner = outer.state as Record<string, unknown> | undefined;
  if (!inner || typeof inner !== "object") return { ok: false, error: "pilotImportInvalid" };
  if (inner.schemaVersion !== PILOT_STATE_SCHEMA_VERSION) return { ok: false, error: "pilotImportSchema" };
  const contentVersion = typeof inner.contentVersion === "string" ? inner.contentVersion : null;
  if (contentVersion !== manifest.contentVersion) {
    // On importe quand même (les ids restent comparables) mais la séance est
    // jetée et l'écran affiche la note de version.
    const note = `pilotImportVersion:${contentVersion ?? "null"}`;
    const imported: PilotPersistedState = {
      schemaVersion: PILOT_STATE_SCHEMA_VERSION,
      updatedAt: now,
      contentVersion,
      lastSession: null,
      exerciseStats: coerceExerciseStats(inner.exerciseStats),
    };
    return { ok: true, state: imported, note };
  }
  const session = coerceSession(inner.lastSession, manifest, now);
  const imported: PilotPersistedState = {
    schemaVersion: PILOT_STATE_SCHEMA_VERSION,
    updatedAt: now,
    contentVersion,
    lastSession: session,
    exerciseStats: coerceExerciseStats(inner.exerciseStats),
  };
  return { ok: true, state: imported, note: null };
}

/**
 * Applique un import avec confirmation (décidée à l'écran) : écrit d'abord la
 * sauvegarde de l'état courant, puis le nouvel état. Si la sauvegarde échoue,
 * l'import est REFUSÉ (on ne remplace pas sans filet).
 */
export function applyImportedState(
  current: PilotPersistedState,
  imported: PilotPersistedState,
): { ok: boolean; error: string | null } {
  try {
    localStorage.setItem(PILOT_BACKUP_KEY, JSON.stringify(current));
  } catch (e) {
    const quota = e instanceof DOMException && (e.name === "QuotaExceededError" || e.code === 22);
    return { ok: false, error: quota ? "pilotStorageQuota" : "pilotStorageUnavailable" };
  }
  return savePilotState(imported);
}

/** Reset PILOTE uniquement — ne touche jamais au store global. */
export function resetPilotState(): { ok: boolean; error: string | null } {
  try {
    localStorage.removeItem(PILOT_STORAGE_KEY);
    localStorage.removeItem(PILOT_BACKUP_KEY);
    return { ok: true, error: null };
  } catch {
    return { ok: false, error: "pilotStorageUnavailable" };
  }
}

/** Restaure la sauvegarde précédente (après import indésirable). */
export function restoreBackup(manifest: PilotManifest, now: number): LoadResult {
  try {
    const raw = localStorage.getItem(PILOT_BACKUP_KEY);
    if (!raw) return { state: emptyPilotState(now), warning: "pilotNoBackup" };
    localStorage.setItem(PILOT_STORAGE_KEY, raw);
  } catch {
    return { state: emptyPilotState(now), warning: "pilotStorageUnavailable" };
  }
  return loadPilotState(manifest, now);
}
