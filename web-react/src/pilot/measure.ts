/**
 * P3.1 — Moteur de MESURE du pilote Anatomie (pur, sans store ni React).
 *
 * Principes imposés par le plan :
 * - Trois temps : T0 (niveau initial, sans feedback correctif), T1 (après
 *   parcours, consignes différentes, vocabulaire comparable), T2 (J+7,
 *   version parallèle). Le mapping temps→version est FIXE et partagé avec
 *   scripts/build_pilot_measure.mjs — ne pas le changer d'un seul côté.
 * - AUCUN feedback correctif pendant la mesure : `recordAnswer` n'enregistre
 *   que le résultat ; l'écran n'affiche jamais « correct/faux » ni la réponse.
 * - Score objectif = correct / items valides effectivement PRÉSENTÉS ET
 *   TRAITÉS, dénominateur explicite, PAR DIMENSION. Un item ambigu est
 *   INVALIDÉ et TRACÉ (raison + horodatage), jamais retiré discrètement.
 * - L'ABSENCE de test n'est JAMAIS un score nul : une session non commencée
 *   n'existe pas, une session sans item traité ne produit pas de score.
 * - Le délai réel T1→T2 est enregistré (epoch ms) — on ne prétend JAMAIS que
 *   le test a eu lieu exactement au septième jour.
 * - Les ids de mesure sont TEXTUELS et disjoints des exercices
 *   d'entraînement (garde testée) ; rien ici n'entre dans SM-2 ni dans la
 *   file de révision : ce module n'importe aucun module de progression.
 */
import { fillScore, normalizeAnswer } from "../domain/scoring";

export const MEASURE_SCHEMA_VERSION = 1;

/** Mapping fixe temps → version parallèle (aligné sur build_pilot_measure.mjs). */
export const TIME_TO_VERSION: Record<MeasureTime, string> = { T0: "A", T1: "B", T2: "C" };

export type MeasureTime = "T0" | "T1" | "T2";

/** Dimensions du plan P3.1. Les banques v1 couvrent recognize+recall ;
 *  comprehend (phrase nouvelle) et production/real_use (grille humaine /
 *  auto-déclaration) restent à construire côté contenu/protocole. */
export type MeasureDimension = "recognize" | "recall" | "comprehend" | "production" | "real_use";

export type MeasureItemKind = "mcq" | "free_input";

export interface PilotLocalizedText {
  en: string;
  fr: string;
  ar: string;
}

export interface MeasureItem {
  /** Unique dans TOUT le manifeste de mesure — disjoint des ids d'entraînement. */
  id: string;
  conceptId: string;
  version: string;
  dimension: MeasureDimension;
  kind: MeasureItemKind;
  prompt: PilotLocalizedText;
  options?: string[];
  answer: string;
  acceptedVariants?: string[];
  /** Provenance de la consigne (defEn/defFr/fr) — comparabilité à relire. */
  source: string;
}

export interface MeasureBank {
  conceptId: string;
  version: string;
  items: MeasureItem[];
}

export interface MeasureManifest {
  schemaVersion: 1;
  id: string;
  contentVersion: string;
  generatedAt: string;
  generatedFrom: string;
  versions: string[];
  banks: MeasureBank[];
}

export interface MeasureItemResult {
  itemId: string;
  conceptId: string;
  dimension: MeasureDimension;
  /** null = présenté mais non traité (interruption) — jamais compté 0. */
  correct: boolean | null;
  /** Item ambigu/invalide : raison tracée, exclu du dénominateur. */
  invalidatedReason: string | null;
  answeredAt: number | null;
  /** Durée observée de réponse (ms) — passive, aucun chrono stressant. */
  durationMs: number | null;
}

export interface MeasureSessionState {
  schemaVersion: 1;
  id: string;
  time: MeasureTime;
  /** Version parallèle réellement servie. */
  version: string;
  contentVersion: string;
  conceptIds: string[];
  startedAt: number;
  updatedAt: number;
  i: number;
  results: MeasureItemResult[];
  /** id de l'item en attente d'enregistrement (garde anti double-soumission). */
  answeredItemId: string | null;
  finished: boolean;
  /** Début interrompu avant la fin (état consigné, jamais un score nul). */
  interrupted: boolean;
  /** Pour T2 : délai RÉEL observé depuis T1 (ms), null si T1 introuvable. */
  actualDelayFromT1Ms: number | null;
}

/** Séance de mesure courte : TOUS les concepts approuvés, 2 items chacun. */
export function measureItemsFor(
  manifest: MeasureManifest,
  conceptIds: string[],
  time: MeasureTime,
): MeasureItem[] {
  const version = TIME_TO_VERSION[time];
  const byConcept = new Map(manifest.banks.filter((b) => b.version === version).map((b) => [b.conceptId, b]));
  const items: MeasureItem[] = [];
  for (const cid of conceptIds) {
    const bank = byConcept.get(cid);
    if (bank) items.push(...bank.items);
  }
  return items;
}

export function newMeasureId(time: MeasureTime, now: number): string {
  const rand = Math.floor(Math.random() * 0xffff).toString(16).padStart(4, "0");
  return `measure-${time}-${new Date(now).toISOString().slice(0, 10)}-${rand}`;
}

export function initMeasureSession(
  time: MeasureTime,
  conceptIds: string[],
  contentVersion: string,
  now: number,
  /** Début d'une session T1/T2 antérieure finie, pour le délai réel. */
  previousFinishedAt: number | null = null,
): MeasureSessionState {
  const version = TIME_TO_VERSION[time];
  return {
    schemaVersion: MEASURE_SCHEMA_VERSION,
    id: newMeasureId(time, now),
    time,
    version,
    contentVersion,
    conceptIds,
    startedAt: now,
    updatedAt: now,
    i: 0,
    results: [],
    answeredItemId: null,
    finished: conceptIds.length === 0,
    interrupted: false,
    actualDelayFromT1Ms:
      time === "T2" && previousFinishedAt != null && previousFinishedAt <= now ? now - previousFinishedAt : null,
  };
}

/** Reprise : version de contenu identique exigée, sinon null (jamais un silence). */
export function resumeMeasureSession(
  raw: unknown,
  manifest: MeasureManifest,
  now: number,
): MeasureSessionState | null {
  const s = raw as MeasureSessionState | null;
  if (!s || typeof s !== "object") return null;
  if (s.schemaVersion !== MEASURE_SCHEMA_VERSION) return null;
  if (s.contentVersion !== manifest.contentVersion) return null;
  if (s.finished) return null;
  const version = TIME_TO_VERSION[s.time as MeasureTime];
  if (!version || s.version !== version) return null;
  if (!Array.isArray(s.conceptIds) || s.conceptIds.length === 0) return null;
  return { ...s, updatedAt: now };
}

export function evaluateMeasureItem(item: MeasureItem, rawInput: string): boolean {
  if (item.kind === "mcq") {
    return normalizeAnswer(rawInput) === normalizeAnswer(item.answer);
  }
  const candidates = [item.answer, ...(item.acceptedVariants ?? [])];
  return candidates.some((c) => fillScore(rawInput, c) === 1);
}

/**
 * Enregistre la réponse de l'item courant. IDEMPOTENT. Aucun verdict
 * retourné à l'écran : la mesure n'affiche pas de correction (P3.1).
 */
export function recordMeasureAnswer(
  state: MeasureSessionState,
  item: MeasureItem,
  rawInput: string,
  now: number,
  shownAt: number,
): MeasureSessionState {
  if (state.answeredItemId === item.id) return state;
  const result: MeasureItemResult = {
    itemId: item.id,
    conceptId: item.conceptId,
    dimension: item.dimension,
    correct: evaluateMeasureItem(item, rawInput),
    invalidatedReason: null,
    answeredAt: now,
    durationMs: Number.isFinite(shownAt) && shownAt <= now ? now - shownAt : null,
  };
  return {
    ...state,
    updatedAt: now,
    answeredItemId: item.id,
    results: [...state.results.filter((r) => r.itemId !== item.id), result],
  };
}

/**
 * Invalide un item ambigu : TRACÉ avec raison, exclu du dénominateur.
 * Jamais un retrait silencieux « pour améliorer les résultats ».
 */
export function invalidateMeasureItem(
  state: MeasureSessionState,
  item: Pick<MeasureItem, "id" | "conceptId" | "dimension">,
  reason: string,
  now: number,
): MeasureSessionState {
  if (!reason.trim()) return state;
  const existing = state.results.find((r) => r.itemId === item.id);
  const result: MeasureItemResult = existing
    ? { ...existing, invalidatedReason: reason, correct: null, answeredAt: now }
    : {
        itemId: item.id,
        conceptId: item.conceptId,
        dimension: item.dimension,
        correct: null,
        invalidatedReason: reason,
        answeredAt: null,
        durationMs: null,
      };
  return {
    ...state,
    updatedAt: now,
    answeredItemId: state.answeredItemId === item.id ? null : state.answeredItemId,
    results: [...state.results.filter((r) => r.itemId !== item.id), result],
  };
}

/** Item courant, ou null si la séance a atteint la fin de sa file. */
export function currentMeasureItem(state: MeasureSessionState, items: MeasureItem[]): MeasureItem | null {
  return items[state.i] ?? null;
}

/** Avance après enregistrement ; marque l'interruption/fin explicitement. */
export function advanceMeasure(state: MeasureSessionState, items: MeasureItem[], now: number): MeasureSessionState {
  if (state.answeredItemId === null) return state;
  if (state.i + 1 < items.length) {
    return { ...state, i: state.i + 1, answeredItemId: null, updatedAt: now };
  }
  return { ...state, finished: true, answeredItemId: null, updatedAt: now };
}

/** Interrompt la séance (état consigné — jamais transformé en score). */
export function interruptMeasure(state: MeasureSessionState, now: number): MeasureSessionState {
  if (state.finished) return state;
  return { ...state, interrupted: true, updatedAt: now };
}

export interface MeasureDimensionScore {
  dimension: MeasureDimension;
  /** Items traités non invalidés — dénominateur EXPLICITE. */
  presented: number;
  correct: number;
  /** true seulement si presented > 0 : l'absence de test n'est pas un score nul. */
  hasScore: boolean;
}

export interface MeasureScore {
  dimensions: MeasureDimensionScore[];
  total: { presented: number; correct: number; hasScore: boolean; invalidated: number; unanswered: number };
}

/**
 * Score d'une séance : correct / items valides effectivement présentés et
 * traités, PAR DIMENSION, dénominateur explicite. Les items invalidés sont
 * comptés à part (tracés, hors dénominateur) ; les items présentés non
 * traités (interruption) sont comptés à part — ils n'inflatent ni le
 * numérateur ni un « 0 » artificiel.
 */
export function measureScore(state: MeasureSessionState): MeasureScore {
  const dims = new Map<MeasureDimension, MeasureDimensionScore>();
  for (const r of state.results) {
    if (r.invalidatedReason) continue; // tracé ailleurs, hors dénominateur
    if (r.correct === null) continue; // présenté non traité : compté dans total.unanswered
    const d = dims.get(r.dimension) ?? { dimension: r.dimension, presented: 0, correct: 0, hasScore: false };
    d.presented += 1;
    if (r.correct) d.correct += 1;
    d.hasScore = true;
    dims.set(r.dimension, d);
  }
  const invalidated = state.results.filter((r) => r.invalidatedReason).length;
  const unanswered = state.results.filter((r) => !r.invalidatedReason && r.correct === null).length;
  const tot = [...dims.values()].reduce(
    (acc, d) => ({ presented: acc.presented + d.presented, correct: acc.correct + d.correct }),
    { presented: 0, correct: 0 },
  );
  return {
    dimensions: [...dims.values()].sort((a, b) => a.dimension.localeCompare(b.dimension)),
    total: { ...tot, hasScore: tot.presented > 0, invalidated, unanswered },
  };
}

/**
 * Le temps de mesure est-il disponible ? T1 exige T0 fini, T2 exige T1 fini
 * (protocole P3.3). Retourne null si disponible, sinon la raison.
 */
export function measureTimeBlocked(
  time: MeasureTime,
  sessions: MeasureSessionState[],
): string | null {
  const finished = (t: MeasureTime) =>
    sessions.find((s) => s.time === t && s.finished && !s.interrupted) ?? null;
  if (time === "T0") return null;
  if (time === "T1") return finished("T0") ? null : "measureNeedT0";
  const t1 = finished("T1");
  return t1 ? null : "measureNeedT1";
}

/** Dernière séance FINIE d'un temps donné (pour le délai réel T1→T2). */
export function finishedMeasureAt(sessions: MeasureSessionState[], time: MeasureTime): number | null {
  const s = sessions.find((x) => x.time === time && x.finished && !x.interrupted);
  return s ? s.updatedAt : null;
}
