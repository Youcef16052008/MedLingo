/**
 * Moteur de séance « histoire » — guide §6.2.
 *
 * Machine à états pure : reçoit une action, renvoie le nouvel état. Elle ne
 * déclenche jamais l'audio, ne lit pas l'horloge ni ne change d'écran —
 * l'interface effectue ces opérations et transmet les événements réels.
 * Les mêmes règles seront portées en Kotlin : tout changement de comportement
 * ici doit être répercuté (scénarios de test JSON partagés à terme).
 */

import type { Story, StoryQuestion, DrillItem } from "./types";
import { sm2Next, isDue, type Sched } from "../domain/sm2";

/** Étapes de la boucle, dans l'ordre (§6.2 du guide). */
export const STORY_STEPS = [
  "REVIEW",
  "LISTEN_DISCOVER",
  "LISTEN_WITH_HELP",
  "LISTEN_CHECK",
  "CIRCLING",
  "PATTERN_PRACTICE",
  "RETELL",
  "TRANSFER_AND_SUMMARY",
] as const;

export type Step = (typeof STORY_STEPS)[number];

export interface SessionStats {
  /** Ids de questions réussies au premier essai, sans aide ni révélation. */
  firstTryIds: string[];
  /** Ids de questions ratées au premier essai ou révélées sans réponse. */
  mistakeIds: string[];
  /** Ids de questions où une aide (traduction, révélation) a été demandée. */
  helpedIds: string[];
  /** Ids d'expressions marquées difficiles (question ratée). */
  difficultPhraseIds: string[];
}

export interface SessionState {
  storyId: string;
  contentVersion: number;
  step: Step;
  /** Bloc REVIEW : expressions dues à réviser au début de séance. */
  reviewIds: string[];
  reviewIndex: number;
  /** Blocs de questions (circling puis transfert) — curseur partagé. */
  qIndex: number;
  drillIndex: number;
  /** Question courante : modèle révélé, dernier verdict, tentatives. */
  revealed: boolean;
  lastCorrect: boolean | null;
  attemptsOnCurrent: number;
  stats: SessionStats;
  retellAttempted: boolean;
  transferTaskAttempted: boolean;
  /** TRANSFER_AND_SUMMARY : questions terminées → tâche de production finale. */
  transferTaskPending: boolean;
  finished: boolean;
}

export type Action =
  | { type: "ADVANCE" }
  | { type: "SUBMIT_ANSWER"; value: string }
  | { type: "SUBMIT_DRILL"; value: string }
  | { type: "REVEAL" }
  | { type: "HELP" }
  | { type: "RETELL_DONE"; attempted: boolean }
  | { type: "TRANSFER_TASK_DONE"; attempted: boolean };

/**
 * Normalisation des réponses courtes : casse, ponctuation finale, espaces,
 * apostrophes typographiques. Les négations et les nombres sont préservés —
 * ne jamais transformer une réponse pour la faire passer (§9 du guide).
 */
export function normalizeAnswer(s: string): string {
  return s
    .toLowerCase()
    .replace(/[\u2019\u02BC]/g, "'")
    .replace(/[.,!?;:]+/g, " ")
    .replace(/\s+/g, " ")
    .trim();
}

/** Égalité stricte (après normalisation) avec une des réponses acceptées. */
export function checkAccepted(userAnswer: string, accepted: readonly string[]): boolean {
  const user = normalizeAnswer(userAnswer);
  if (!user) return false;
  return accepted.some((a) => normalizeAnswer(a) === user);
}

export interface InitOptions {
  /** Expressions dues (SM-2 d'expressions) — vide si rien à réviser. */
  duePhraseIds?: string[];
}

export function initSession(story: Story, opts: InitOptions = {}): SessionState {
  const due = (opts.duePhraseIds ?? []).filter((id) =>
    story.targetPhrases.some((p) => p.id === id)
  );
  return {
    storyId: story.storyId,
    contentVersion: story.contentVersion,
    step: due.length > 0 ? "REVIEW" : "LISTEN_DISCOVER",
    reviewIds: due,
    reviewIndex: 0,
    qIndex: 0,
    drillIndex: 0,
    revealed: false,
    lastCorrect: null,
    attemptsOnCurrent: 0,
    stats: { firstTryIds: [], mistakeIds: [], helpedIds: [], difficultPhraseIds: [] },
    retellAttempted: false,
    transferTaskAttempted: false,
    transferTaskPending: false,
    finished: false,
  };
}

/** Question courante selon l'étape (circling ou transfert). */
export function currentQuestion(state: SessionState, story: Story): StoryQuestion | undefined {
  if (state.step === "CIRCLING") return story.questions[state.qIndex];
  if (state.step === "TRANSFER_AND_SUMMARY" && !state.transferTaskPending)
    return story.transfer.questions[state.qIndex];
  return undefined;
}

export function currentDrill(state: SessionState, story: Story): DrillItem | undefined {
  if (state.step === "PATTERN_PRACTICE") return story.drill.items[state.drillIndex];
  return undefined;
}

function recordVerdict(state: SessionState, id: string, correct: boolean): SessionStats {
  const stats = {
    firstTryIds: [...state.stats.firstTryIds],
    mistakeIds: [...state.stats.mistakeIds],
    helpedIds: [...state.stats.helpedIds],
    difficultPhraseIds: [...state.stats.difficultPhraseIds],
  };
  const firstAttempt = state.attemptsOnCurrent === 0;
  if (firstAttempt) {
    if (correct) stats.firstTryIds.push(id);
    else stats.mistakeIds.push(id);
  }
  return stats;
}

function markPhrasesDifficult(stats: SessionStats, phraseIds: readonly string[] | undefined): void {
  for (const id of phraseIds ?? []) {
    if (!stats.difficultPhraseIds.includes(id)) stats.difficultPhraseIds.push(id);
  }
}

/**
 * Réduit l'état. Toute action invalide pour l'étape courante est ignorée
 * (renvoie l'état tel quel) : l'UI ne peut pas sauter d'étape ni rejouer
 * une question déjà validée.
 */
export function sessionReducer(state: SessionState, action: Action, story: Story): SessionState {
  switch (action.type) {
    case "ADVANCE": {
      let s = { ...state };
      switch (state.step) {
        case "REVIEW":
          if (s.reviewIndex + 1 < s.reviewIds.length) {
            s.reviewIndex += 1;
            return s;
          }
          s.step = "LISTEN_DISCOVER";
          return s;
        case "LISTEN_DISCOVER":
          s.step = "LISTEN_WITH_HELP";
          return s;
        case "LISTEN_WITH_HELP":
          s.step = "LISTEN_CHECK";
          return s;
        case "LISTEN_CHECK":
          s.step = "CIRCLING";
          s.qIndex = 0;
          s.revealed = false;
          s.lastCorrect = null;
          s.attemptsOnCurrent = 0;
          return s;
        case "CIRCLING":
          if (s.qIndex + 1 < story.questions.length) {
            s.qIndex += 1;
            s.revealed = false;
            s.lastCorrect = null;
            s.attemptsOnCurrent = 0;
            return s;
          }
          s.step = "PATTERN_PRACTICE";
          s.drillIndex = 0;
          s.revealed = false;
          s.lastCorrect = null;
          s.attemptsOnCurrent = 0;
          return s;
        case "PATTERN_PRACTICE":
          if (s.drillIndex + 1 < story.drill.items.length) {
            s.drillIndex += 1;
            s.revealed = false;
            s.lastCorrect = null;
            s.attemptsOnCurrent = 0;
            return s;
          }
          s.step = "RETELL";
          return s;
        case "RETELL":
          s.step = "TRANSFER_AND_SUMMARY";
          s.qIndex = 0;
          s.revealed = false;
          s.lastCorrect = null;
          s.attemptsOnCurrent = 0;
          return s;
        case "TRANSFER_AND_SUMMARY":
          // ADVANCE depuis le bilan final ne rejoue pas la séance.
          if (s.qIndex + 1 < story.transfer.questions.length) {
            s.qIndex += 1;
            s.revealed = false;
            s.lastCorrect = null;
            s.attemptsOnCurrent = 0;
            return s;
          }
          if (!s.transferTaskPending) {
            // Toutes les questions de transfert sont passées : tâche de
            // production finale avant le bilan.
            s.transferTaskPending = true;
            s.revealed = false;
            s.lastCorrect = null;
            return s;
          }
          s.finished = true;
          return s;
      }
      return s;
    }

    case "SUBMIT_ANSWER": {
      const q = currentQuestion(state, story);
      if (!q || state.lastCorrect === true) return state;
      const correct = checkAccepted(action.value, q.accepted);
      const stats = recordVerdict(state, q.id, correct);
      if (!correct && state.attemptsOnCurrent === 0) markPhrasesDifficult(stats, q.phraseIds);
      return {
        ...state,
        stats,
        revealed: true,
        lastCorrect: correct,
        attemptsOnCurrent: state.attemptsOnCurrent + 1,
      };
    }

    case "SUBMIT_DRILL": {
      const d = currentDrill(state, story);
      if (!d || state.lastCorrect === true) return state;
      const correct = checkAccepted(action.value, d.accepted);
      const stats = recordVerdict(state, d.id, correct);
      return {
        ...state,
        stats,
        revealed: true,
        lastCorrect: correct,
        attemptsOnCurrent: state.attemptsOnCurrent + 1,
      };
    }

    case "REVEAL": {
      const q = currentQuestion(state, story) ?? currentDrill(state, story);
      if (!q || state.revealed) return state;
      // « Je ne sais pas encore » : activité réalisée avec aide — jamais une
      // réussite sans aide (retirée de firstTry si une tentative avait
      // réussie avant révélation, impossible ici) ni une erreur de
      // compréhension si aucune tentative n'a eu lieu.
      const stats = { ...state.stats, helpedIds: [...state.stats.helpedIds] };
      if (!stats.helpedIds.includes(q.id)) stats.helpedIds.push(q.id);
      return { ...state, stats, revealed: true, lastCorrect: null, attemptsOnCurrent: state.attemptsOnCurrent + 1 };
    }

    case "HELP": {
      const q = currentQuestion(state, story);
      const d = currentDrill(state, story);
      const id = q?.id ?? d?.id;
      if (!id) return state;
      if (state.stats.helpedIds.includes(id)) return state;
      // L'aide retire la question des « réussies sans aide » si elle y était :
      // une réussite après traduction affichée n'est pas une réussite sans aide.
      const stats = {
        ...state.stats,
        firstTryIds: state.stats.firstTryIds.filter((x) => x !== id),
        helpedIds: [...state.stats.helpedIds, id],
      };
      return { ...state, stats };
    }

    case "RETELL_DONE":
      return { ...state, retellAttempted: state.retellAttempted || action.attempted };

    case "TRANSFER_TASK_DONE":
      return { ...state, transferTaskAttempted: state.transferTaskAttempted || action.attempted };
  }
}

/* ------------------------------------------------------------------ */
/* Mémoire d'expressions — SM-2 dans un espace séparé des termes       */
/* ------------------------------------------------------------------ */

export type ExprScheds = Record<string, Sched>;

/** Expressions dues maintenant (ids de l'histoire uniquement).
 *  Une expression jamais vue n'est pas « due » : elle sera découverte dans
 *  la séance, puis planifiée par `scheduleFromSession`. */
export function duePhrases(story: Story, sm2: ExprScheds, now: number): string[] {
  return story.targetPhrases.filter((p) => isDue(sm2[p.id], now)).map((p) => p.id);
}

/**
 * Planifie les expressions ciblées en fin de séance (§6.3 du guide) :
 * difficile → qualité 2 (revue demain), sinon 5. Les révisions du bloc
 * REVIEW ont déjà pu planifier certaines expressions : `prev` les conserve.
 */
export function scheduleFromSession(
  state: SessionState,
  story: Story,
  prev: ExprScheds,
  now: number
): ExprScheds {
  const out: ExprScheds = { ...prev };
  for (const p of story.targetPhrases) {
    const difficult = state.stats.difficultPhraseIds.includes(p.id);
    const quality = difficult ? 2 : 5;
    out[p.id] = sm2Next(quality, out[p.id], now);
  }
  return out;
}

/** Note une révision du bloc REVIEW (souvenu → 4, oublié → 2). */
export function scheduleReview(
  phraseId: string,
  remembered: boolean,
  prev: ExprScheds,
  now: number
): ExprScheds {
  return { ...prev, [phraseId]: sm2Next(remembered ? 4 : 2, prev[phraseId], now) };
}

/** Résumé de séance — l'affichage distingue compréhension, aide et production. */
export function sessionSummary(state: SessionState, story: Story) {
  const total =
    story.questions.length + story.drill.items.length + story.transfer.questions.length;
  return {
    total,
    firstTry: state.stats.firstTryIds.length,
    helped: state.stats.helpedIds.length,
    mistakes: state.stats.mistakeIds.length,
    difficultPhrases: state.stats.difficultPhraseIds,
    retellAttempted: state.retellAttempted,
    transferAttempted: state.transferTaskAttempted,
  };
}
