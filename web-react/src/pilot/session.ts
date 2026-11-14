/**
 * P2.2 — Moteur de séance du pilote Anatomie (pur, sans store ni React).
 *
 * Référence de style : `src/learning/session.ts` (histoires). Mêmes principes :
 * - état sérialisable, horloge INJECTÉE (`now`) — jamais `Date.now()` ici ;
 * - verdict idempotent (double clic / double soumission = même état) ;
 * - séparation exigée par le plan P1.3 : « réussi au premier essai »,
 *   « avec aide », « après correction » (erreurs) ;
 * - une aide NE transforme PAS la réponse en réussite autonome : dès que
 *   l'aide est consultée, la meilleure issue possible est « avec aide » ;
 * - les ids pilote sont TEXTUELS (`anatomy-u1-01-heart`) — ils ne franchissent
 *   JAMAIS un contrat numérique (SM-2, Room). Le scoring réutilise UNIQUEMENT
 *   les fonctions pures `normalizeAnswer`/`fillScore` de `src/domain/scoring.ts`.
 *
 * Séance courte (plan P2.2) : nombre d'étapes affiché par l'écran
 * (3 exercices par concept × concepts de la file). Interrompre = quitter :
 * l'état est persisté par `storage.ts` et repris par `resumeSession`.
 */
import { fillScore, normalizeAnswer } from "../domain/scoring";
import type { PilotConcept, PilotExercise, PilotManifest } from "./types";

export type PilotFirstResult = "first_try" | "helped" | "error";

export interface PilotSessionState {
  schemaVersion: 1;
  id: string;
  startedAt: number;
  updatedAt: number;
  /** Version de contenu à laquelle la séance a été ouverte. */
  contentVersion: string;
  /** File de concepts (ordre de la séance). */
  conceptIds: string[];
  /** Index du concept courant dans la file. */
  i: number;
  /** Index de l'exercice courant dans le concept courant. */
  exIdx: number;
  /** exerciseId → nombre total de tentatives (y compris ratées). */
  attempts: Record<string, number>;
  /** exerciseId → PREMIER résultat (avant toute correction). */
  firstResults: Record<string, PilotFirstResult>;
  /** exerciseIds corrects sans aucune aide (réussites autonomes). */
  autonomous: string[];
  /** exerciseIds dont l'aide a été consultée avant la réponse. */
  helped: string[];
  /** exerciseIds ratés au moins une fois (→ « révisions proposées »). */
  errors: string[];
  /** Id de l'exercice en attente de correction (garde anti double-soumission). */
  answeredExerciseId: string | null;
  finished: boolean;
}

/** Nombre d'exercices par concept tel que produits par le manifeste v1. */
export const EXERCISES_PER_CONCEPT = 3;

/** Séance courte : au plus 3 concepts (9 exercices) par séance. */
export const CONCEPTS_PER_SESSION = 3;

export function exerciseOf(concept: PilotConcept, exIdx: number): PilotExercise | undefined {
  return concept.exercises[exIdx];
}

/** Génère un id de séance lisible et unique-en-pratique (horodatage + aléa court). */
export function newSessionId(now: number): string {
  const rand = Math.floor(Math.random() * 0xffff).toString(16).padStart(4, "0");
  return `pilot-${new Date(now).toISOString().slice(0, 10)}-${rand}`;
}

/**
 * Ouvre une séance sur une file de concepts PUBLISHABLES (le verrou est
 * appliqué en amont par `isConceptPublishable` — le moteur ne re-décide pas).
 */
export function initSession(
  concepts: PilotConcept[],
  contentVersion: string,
  now: number,
  sessionId = newSessionId(now),
): PilotSessionState {
  const conceptIds = concepts.slice(0, CONCEPTS_PER_SESSION).map((c) => c.id);
  return {
    schemaVersion: 1,
    id: sessionId,
    startedAt: now,
    updatedAt: now,
    contentVersion,
    conceptIds,
    i: 0,
    exIdx: 0,
    attempts: {},
    firstResults: {},
    autonomous: [],
    helped: [],
    errors: [],
    answeredExerciseId: null,
    finished: conceptIds.length === 0,
  };
}

/**
 * Reprise : la séance n'est reprise que si sa version de contenu correspond
 * AU manifeste courant (changement de version = séance invalidée, jamais un
 * silence) et que ses concepts existent encore. Retourne `null` sinon.
 */
export function resumeSession(
  raw: unknown,
  manifest: PilotManifest,
  now: number,
): PilotSessionState | null {
  const s = raw as PilotSessionState | null;
  if (!s || typeof s !== "object") return null;
  if (s.schemaVersion !== 1) return null;
  if (s.contentVersion !== manifest.contentVersion) return null;
  if (s.finished) return null;
  if (!Array.isArray(s.conceptIds) || s.conceptIds.length === 0) return null;
  const known = new Set(manifest.concepts.map((c) => c.id));
  // Un concept disparu du manifeste invalide la reprise (l'ordre de la file
  // ne peut pas être reconstruit de façon fiable).
  if (!s.conceptIds.every((id) => known.has(id))) return null;
  return { ...s, updatedAt: now };
}

/** Le verdict d'une comparaison réponse attendue (et variantes) vs saisie. */
export function evaluateAnswer(exercise: PilotExercise, rawInput: string): boolean {
  if (exercise.kind === "mcq") {
    return normalizeAnswer(rawInput) === normalizeAnswer(exercise.answer);
  }
  const candidates = [exercise.answer, ...(exercise.acceptedVariants ?? [])];
  return candidates.some((c) => fillScore(rawInput, c) === 1);
}

/**
 * Enregistre la réponse de l'exercice courant. IDEMPOTENT : si l'exercice a
 * déjà été corrigé et pas encore avancé, un second appel renvoie l'état
 * inchangé (double clic / double soumission).
 *
 * Classification du premier résultat :
 *  - aide consultée sur cet exercice → `helped` (jamais `first_try`) ;
 *  - sinon correct → `first_try` ; sinon → `error` (et « après correction » :
 *    l'exercice peut être re-attempté via la révision proposée, hors moteur).
 */
export function recordAnswer(
  state: PilotSessionState,
  exercise: PilotExercise,
  rawInput: string,
  now: number,
): PilotSessionState {
  if (state.answeredExerciseId === exercise.id) return state;
  const correct = evaluateAnswer(exercise, rawInput);
  const wasHelped = state.helped.includes(exercise.id);
  const firstResult: PilotFirstResult = correct ? (wasHelped ? "helped" : "first_try") : "error";

  return {
    ...state,
    updatedAt: now,
    answeredExerciseId: exercise.id,
    attempts: { ...state.attempts, [exercise.id]: (state.attempts[exercise.id] ?? 0) + 1 },
    firstResults: { ...state.firstResults, [exercise.id]: firstResult },
    autonomous: correct && !wasHelped ? [...new Set([...state.autonomous, exercise.id])] : state.autonomous,
    helped: wasHelped ? [...new Set([...state.helped, exercise.id])] : state.helped,
    errors: correct ? state.errors.filter((id) => id !== exercise.id) : [...new Set([...state.errors, exercise.id])],
  };
}

/** L'aide est consultée AVANT de répondre : la réponse ne sera jamais « autonome ». */
export function useHelp(state: PilotSessionState, exerciseId: string, now: number): PilotSessionState {
  if (state.answeredExerciseId === exerciseId) return state;
  if (state.helped.includes(exerciseId)) return state;
  return { ...state, helped: [...state.helped, exerciseId], updatedAt: now };
}

/** Avance d'un exercice (clic « Continuer » après feedback). */
export function advance(
  state: PilotSessionState,
  manifest: PilotManifest,
  now: number,
): PilotSessionState {
  if (state.answeredExerciseId === null) return state; // il faut d'abord répondre
  const concept = manifest.concepts.find((c) => c.id === state.conceptIds[state.i]);
  if (!concept) return { ...state, finished: true, updatedAt: now };

  if (state.exIdx + 1 < concept.exercises.length) {
    return { ...state, exIdx: state.exIdx + 1, answeredExerciseId: null, updatedAt: now };
  }
  if (state.i + 1 < state.conceptIds.length) {
    return { ...state, i: state.i + 1, exIdx: 0, answeredExerciseId: null, updatedAt: now };
  }
  return { ...state, finished: true, answeredExerciseId: null, updatedAt: now };
}

/** Nombre total d'étapes affichables de la séance (pour « étape X / Y »). */
export function totalSteps(state: PilotSessionState, manifest: PilotManifest): number {
  return state.conceptIds.reduce((n, id) => {
    const c = manifest.concepts.find((x) => x.id === id);
    return n + (c ? c.exercises.length : 0);
  }, 0);
}

/** Étape courante (1-based) pour l'affichage. */
export function currentStepNumber(state: PilotSessionState, manifest: PilotManifest): number {
  let n = 0;
  for (let k = 0; k < state.i; k++) {
    const c = manifest.concepts.find((x) => x.id === state.conceptIds[k]);
    n += c ? c.exercises.length : 0;
  }
  return n + state.exIdx + 1;
}
