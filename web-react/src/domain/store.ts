import { useSyncExternalStore } from "react";
import type { Lang } from "../types";
import type { Sched } from "./sm2";
import { DAILY_GOAL_OPTIONS, todayKey, addXp, touchStreak } from "./gamification";
import type { Tier } from "./leagues";
import type { LeagueState } from "./leagues";
import { STORY_STEPS, type Step, type SessionStats, type SessionState } from "../learning/session";

/**
 * Store applicatif unique (pattern subscribe + `useSyncExternalStore`).
 *
 * La persistance est localStorage — équivalent web de la base Room côté Android.
 * Aucune fonction ici ne lit l'horloge murale : `now` est passé par l'appelant,
 * ce qui garde les effets de temps reproductibles en test.
 */

export interface AppState {
  lang: Lang;
  gems: number;
  streak: number;
  lastStudy: string;
  xp: number;
  /** XP gagné aujourd'hui (objet de gamification quotidien). */
  xpToday: number;
  /** Jour civil (`todayKey`) auquel appartient `xpToday`. */
  goalDate: string;
  /** Objectif quotidien en XP : 20 | 50 | 100. */
  dailyGoal: number;
  /** Congélations de série restantes (achat 200 💎). */
  freezeCount: number;
  /** XP des 7 derniers jours, indexé par jour clé → pour la ligue hebdo. */
  weeklyXp: Record<string, number>;
  /** Caisse(s) gagnée(s) et non encore ouverte(s). */
  pendingChests: number;
  /** Nombre total de caisses ouvertes (trophée). */
  chestsOpened: number;
  /** Meilleur % par module (affichage d'unité, trophée `module_master`). */
  moduleBest: Record<string, number>;
  /** Meilleur % par leçon, clé `lessonKey(moduleId, level)` → déblocage du parcours. */
  lessonBest: Record<string, number>;
  /** Trophées obtenus : id → horodatage de déblocage. */
  trophies: Record<string, number>;
  /** État de la ligue hebdomadaire. */
  league: LeagueState;
  answered: number;
  correct: number;
  flashReviewed: number;
  quizDone: number;
  sm2: Record<string, Sched>;
  introSeen: boolean;
  dataVersion: string;
  /** Progrès des leçons (par lessonId). */
  lessonProgress: Record<string, LessonProgress>;
  /** File de révision ("Revisions en attente"). */
  reviewQueue: ReviewQueueItem[];
  /** Heart manager par leçon. */
  heartManager: Record<string, { hearts: number; mistakes: number }>;
  /** Séances « histoire » : progression reprise + récompense idempotente. */
  storyProgress: Record<string, StoryProgress>;
  /**
   * Mémoire d'expressions (SM-2) — espace de clés SÉPARÉ de `sm2` :
   * les ids (`en:have-headache:v1`) ne doivent jamais entrer en collision
   * avec les `termId` numériques des flashcards (§9.3 du guide).
   */
  exprSm2: Record<string, Sched>;
}

/** Progrès d'une séance « histoire » (reprise après interruption). */
export interface StoryProgress {
  storyId: string;
  contentVersion: number;
  step: Step;
  qIndex: number;
  drillIndex: number;
  reviewIndex: number;
  stats: SessionStats;
  /** Bloc REVIEW : phraseId → retenu/oublié (fusion dans la planification finale). */
  reviewRatings: Record<string, boolean>;
  retellAttempted: boolean;
  transferTaskAttempted: boolean;
  /** Questions de transfert terminées, tâche de production en cours. */
  transferTaskPending: boolean;
  savedAt: number;
  /** 0 = séance non terminée. */
  completedAt: number;
  /** Récompense de fin déjà attribuée pour cette exécution (anti double XP). */
  rewardClaimed: boolean;
}

/** Progrès d'une leçon (stocké par `lessonId`). */
export interface LessonProgress {
  lessonId: string;
  score: number;
  heartsRemain: number;
  mistakes: number;
  completedAt: number;
  missedExIds: number[];
}

/** Élement de la file de révision ("Revisions en attente"). */
export interface ReviewQueueItem {
  lessonId: string;
  exIds: number[];
  chapterId: string;
  moduleId: string;
  status: "unlocked" | "played";
  missedAt: number;
}

/** Gestionnaire des coeurs (5 par leçon, restart à 0). */
export interface HeartManager {
  heartsLeft: number;
  onWrongAnswer(): boolean;
}

/** État global de l'application. */
export interface AppState {

}

/** Champs d'un ancien store qui ne sont plus écrits (cœurs et flashSuccess supprimés). */
const DROPPED_KEYS = ["hearts", "heartsTs", "flashSuccess"];

const STORAGE_KEY = "medlingo_web_v1";

/**
 * Les ids de termes web sont désormais ceux des seeds Android, et non un
 * numérotage propre au site.
 *
 * Un `sm2` enregistré avec l'ancien numérotage attribuerait l'historique de révision au
 * mauvais terme — une corruption silencieuse. Le jeu de données étant généré (jamais
 * versionné), l'ancien mapping est irrécupérable : on ne peut pas le reconstruire sans
 * risquer de décaler l'historique. On conserve donc les statistiques du joueur et on
 * jette uniquement ce qui est indexé par id.
 *
 * Incrémenter cette valeur pour purger `sm2` au prochain chargement.
 */
export const TERM_DATA_VERSION = "android-ids-v1";

function detectLang(): Lang {
  const nav =
    typeof navigator !== "undefined" && navigator.language
      ? navigator.language.toLowerCase()
      : "fr";
  if (nav.startsWith("ar")) return "ar";
  if (nav.startsWith("en")) return "en";
  return "fr";
}

export function makeDefaults(_now: number): AppState {
  return {
    lang: detectLang(),
    gems: 0,
    streak: 0,
    lastStudy: "",
    xp: 0,
    xpToday: 0,
    goalDate: "",
    dailyGoal: 20,
    freezeCount: 0,
    weeklyXp: {},
    pendingChests: 0,
    chestsOpened: 0,
    moduleBest: {},
    lessonBest: {},
    trophies: {},
    league: { tier: "bronze", weekStart: "", botSeed: 1 },
    answered: 0,
    correct: 0,
    flashReviewed: 0,
    quizDone: 0,
    sm2: {},
    introSeen: false,
    dataVersion: TERM_DATA_VERSION,
    lessonProgress: {},
    reviewQueue: [],
    heartManager: {},
    storyProgress: {},
    exprSm2: {},
  };
}

function storage(): Storage | null {
  try {
    return typeof localStorage !== "undefined" ? localStorage : null;
  } catch {
    return null; // stockage indisponible (navigateur privé, environnement de test)
  }
}

/** Persiste l'état — échec silencieux (quota dépassé, stockage plein). */
function persist(): void {
  try {
    storage()?.setItem(STORAGE_KEY, JSON.stringify(state));
  } catch {
    /* quota/accès refusé : l'état en mémoire reste la source de vérité */
  }
}

/* ---------- assainissement du store rechargé (B6) ---------- */

const LANGS: readonly Lang[] = ["ar", "en", "fr"];
const TIERS: readonly Tier[] = ["bronze", "silver", "gold"];

function coerceNum(v: unknown, fallback: number): number {
  return typeof v === "number" && Number.isFinite(v) ? v : fallback;
}

function coerceRecord(v: unknown): Record<string, unknown> {
  return v && typeof v === "object" && !Array.isArray(v) ? (v as Record<string, unknown>) : {};
}

/**
 * Tableau persisté : rejette tout le reste. BUG-001 — `reviewQueue` était
 * rechargé via `coerceRecord` (qui rejette les tableaux) puis étalé par
 * `[...state.reviewQueue]` dans `update()` : chaque update jetait `TypeError`
 * après rechargement, et plus aucune progression n'était sauvegardée.
 */
function coerceArray<T>(v: unknown, guard: (item: unknown) => boolean): T[] {
  return Array.isArray(v) ? (v.filter(guard) as T[]) : [];
}

/** Map validée entrée par entrée — une entrée corrompue est écartée, pas castée. */
function coerceRecordMap<T>(v: unknown, guard: (item: unknown) => item is T): Record<string, T> {
  const out: Record<string, T> = {};
  for (const [k, item] of Object.entries(coerceRecord(v))) {
    if (guard(item)) out[k] = item;
  }
  return out;
}

function isFiniteNum(v: unknown): v is number {
  return typeof v === "number" && Number.isFinite(v);
}

function isReviewQueueItem(v: unknown): v is ReviewQueueItem {
  if (!v || typeof v !== "object") return false;
  const o = v as Record<string, unknown>;
  return (
    typeof o.lessonId === "string" &&
    typeof o.chapterId === "string" &&
    typeof o.moduleId === "string" &&
    (o.status === "unlocked" || o.status === "played") &&
    Array.isArray(o.exIds) &&
    isFiniteNum(o.missedAt)
  );
}

function isLessonProgress(v: unknown): v is LessonProgress {
  if (!v || typeof v !== "object") return false;
  const o = v as Record<string, unknown>;
  return (
    typeof o.lessonId === "string" &&
    isFiniteNum(o.score) &&
    isFiniteNum(o.heartsRemain) &&
    isFiniteNum(o.mistakes) &&
    isFiniteNum(o.completedAt) &&
    Array.isArray(o.missedExIds)
  );
}

/** Forme persistée par le sous-système cœurs : {hearts, mistakes} par leçon. */
function isHeartState(v: unknown): v is { hearts: number; mistakes: number } {
  if (!v || typeof v !== "object") return false;
  const o = v as Record<string, unknown>;
  return isFiniteNum(o.hearts) && isFiniteNum(o.mistakes);
}

function coerceNumMap(v: unknown): Record<string, number> {
  const out: Record<string, number> = {};
  for (const [k, n] of Object.entries(coerceRecord(v))) {
    if (typeof n === "number" && Number.isFinite(n)) out[k] = n;
  }
  return out;
}

/** Map phraseId → booléen (verdicts du bloc REVIEW). */
function coerceBoolMap(v: unknown): Record<string, boolean> {
  const out: Record<string, boolean> = {};
  for (const [k, b] of Object.entries(coerceRecord(v))) {
    if (typeof b === "boolean") out[k] = b;
  }
  return out;
}

/** Champs scolaires (SM-2) : structure minimale requise par `sm2Next`. */
function coerceSm2(v: unknown): Record<string, Sched> {
  const out: Record<string, Sched> = {};
  for (const [k, s] of Object.entries(coerceRecord(v))) {
    if (!s || typeof s !== "object") continue;
    const o = s as Record<string, unknown>;
    if (
      typeof o.rep === "number" &&
      typeof o.ef === "number" &&
      typeof o.iv === "number" &&
      typeof o.due === "number" &&
      Number.isFinite(o.ef) &&
      Number.isFinite(o.due)
    ) {
      out[k] = { rep: o.rep, ef: o.ef, iv: o.iv, due: o.due };
    }
  }
  return out;
}

/** Progression de séance « histoire » : entrées incomplètes écartées. */
function coerceStoryProgress(v: unknown): Record<string, StoryProgress> {
  const out: Record<string, StoryProgress> = {};
  for (const [k, p] of Object.entries(coerceRecord(v))) {
    if (!p || typeof p !== "object") continue;
    const o = p as Record<string, unknown>;
    if (typeof o.storyId !== "string" || !(STORY_STEPS as readonly string[]).includes(o.step as string)) continue;
    const stats =
      o.stats && typeof o.stats === "object"
        ? (o.stats as Record<string, unknown>)
        : {};
    out[k] = {
      storyId: o.storyId,
      contentVersion: coerceNum(o.contentVersion, 1),
      step: o.step as StoryProgress["step"],
      qIndex: coerceNum(o.qIndex, 0),
      drillIndex: coerceNum(o.drillIndex, 0),
      reviewIndex: coerceNum(o.reviewIndex, 0),
      stats: {
        firstTryIds: Array.isArray(stats.firstTryIds) ? stats.firstTryIds.map(String) : [],
        mistakeIds: Array.isArray(stats.mistakeIds) ? stats.mistakeIds.map(String) : [],
        helpedIds: Array.isArray(stats.helpedIds) ? stats.helpedIds.map(String) : [],
        difficultPhraseIds: Array.isArray(stats.difficultPhraseIds)
          ? stats.difficultPhraseIds.map(String)
          : [],
      },
      reviewRatings: coerceBoolMap(o.reviewRatings),
      retellAttempted: o.retellAttempted === true,
      transferTaskAttempted: o.transferTaskAttempted === true,
      transferTaskPending: o.transferTaskPending === true,
      savedAt: coerceNum(o.savedAt, 0),
      completedAt: coerceNum(o.completedAt, 0),
      rewardClaimed: o.rewardClaimed === true,
    };
  }
  return out;
}

/**
 * Reconstitue l'état depuis une chaîne brute. Isolé de `load()` pour être testable :
 * le store est instancié au chargement du module, ce qui le rend inatteignable depuis
 * un test.
 */
export function revive(raw: string | null, now: number): AppState {
  if (!raw) return makeDefaults(now);
  try {
    const parsed = JSON.parse(raw) as Partial<AppState> & Record<string, unknown> | null;
    if (parsed && typeof parsed === "object") {
      // Les champs retirés (cœurs) ne doivent pas survivre dans l'état ni être
      // réécrits en localStorage au prochain `update()`.
      for (const key of DROPPED_KEYS) delete parsed[key];

      const base = makeDefaults(now);
      const league = coerceRecord(parsed.league);
      const merged: AppState = {
        ...base,
        ...parsed,
        // Enums : valeur hors liste → défaut (store corrompu ou ancien schéma).
        lang: LANGS.includes(parsed.lang as Lang) ? (parsed.lang as Lang) : base.lang,
        dailyGoal: DAILY_GOAL_OPTIONS.includes(parsed.dailyGoal as (typeof DAILY_GOAL_OPTIONS)[number])
          ? (parsed.dailyGoal as number)
          : base.dailyGoal,
        introSeen: parsed.introSeen === true,
        // Nombres : NaN/Infinity/chaîne casseraient les calculs d'XP.
        gems: coerceNum(parsed.gems, base.gems),
        streak: coerceNum(parsed.streak, base.streak),
        xp: coerceNum(parsed.xp, base.xp),
        xpToday: coerceNum(parsed.xpToday, base.xpToday),
        freezeCount: coerceNum(parsed.freezeCount, base.freezeCount),
        pendingChests: coerceNum(parsed.pendingChests, base.pendingChests),
        chestsOpened: coerceNum(parsed.chestsOpened, base.chestsOpened),
        answered: coerceNum(parsed.answered, base.answered),
        correct: coerceNum(parsed.correct, base.correct),
        flashReviewed: coerceNum(parsed.flashReviewed, base.flashReviewed),
        quizDone: coerceNum(parsed.quizDone, base.quizDone),
        // Dates/textes : doivent rester des chaînes (comparaisons lexicographiques).
        lastStudy: typeof parsed.lastStudy === "string" ? parsed.lastStudy : base.lastStudy,
        goalDate: typeof parsed.goalDate === "string" ? parsed.goalDate : base.goalDate,
        // Maps : objets plats, entrées invalides écartées.
        sm2: coerceSm2(parsed.sm2),
        weeklyXp: coerceNumMap(parsed.weeklyXp),
        moduleBest: coerceNumMap(parsed.moduleBest),
        lessonBest: coerceNumMap(parsed.lessonBest),
        trophies: coerceNumMap(parsed.trophies),
        // Nouveaux : LessonProgress, ReviewQueue, HeartManager.
        // BUG-001 : reviewQueue est un TABLEAU — coerceArray, jamais coerceRecord
        // (qui rejette les tableaux et cassait tout update() après rechargement).
        // Les entrées invalides sont écartées, pas castées à l'aveugle.
        lessonProgress: coerceRecordMap(parsed.lessonProgress, isLessonProgress),
        reviewQueue: coerceArray(parsed.reviewQueue, isReviewQueueItem),
        heartManager: coerceRecordMap(parsed.heartManager, isHeartState),
        storyProgress: coerceStoryProgress(parsed.storyProgress),
        exprSm2: coerceSm2(parsed.exprSm2),
        league: {
          tier: TIERS.includes(league.tier as Tier) ? (league.tier as Tier) : base.league.tier,
          weekStart: typeof league.weekStart === "string" ? league.weekStart : base.league.weekStart,
          botSeed: coerceNum(league.botSeed, base.league.botSeed),
        },
      };
      // Historique indexé par id : sans correspondance avec le numérotage courant,
      // il ne veut plus rien dire. On le purge plutôt que de l'attribuer au hasard.
      if (parsed.dataVersion !== TERM_DATA_VERSION) merged.sm2 = {};
      merged.dataVersion = TERM_DATA_VERSION;
      return merged;
    }
  } catch {
    /* store corrompu → on repart des défauts */
  }
  return makeDefaults(now);
}

function load(): AppState {
  return revive(storage()?.getItem(STORAGE_KEY) ?? null, Date.now());
}

let state: AppState = load();
const listeners = new Set<() => void>();

function emit(): void {
  listeners.forEach((l) => l());
}

export function getState(): AppState {
  return state;
}

export function subscribe(fn: () => void): () => void {
  listeners.add(fn);
  return () => {
    listeners.delete(fn);
  };
}

/**
 * Applique une recette sur une copie, persiste et notifie.
 * Les structures mutables (SM-2, maps de progression, ligue) sont copiées :
 * les recettes ne modifient jamais l'objet rendu précédemment.
 */
export function update(recipe: (draft: AppState) => void): void {
  const draft: AppState = {
    ...state,
    sm2: { ...state.sm2 },
    weeklyXp: { ...state.weeklyXp },
    moduleBest: { ...state.moduleBest },
    lessonBest: { ...state.lessonBest },
    trophies: { ...state.trophies },
    lessonProgress: { ...state.lessonProgress },
    reviewQueue: [...state.reviewQueue],
    heartManager: { ...state.heartManager },
    storyProgress: { ...state.storyProgress },
    exprSm2: { ...state.exprSm2 },
    league: { ...state.league },
  };
  recipe(draft);
  state = draft;
  persist();
  emit();
}

/** Remet la progression à zéro en conservant la langue et l'intro vue. */
export function resetProgress(now: number): void {
  const keepLang = state.lang;
  const keepIntro = state.introSeen;
  state = { ...makeDefaults(now), lang: keepLang, introSeen: keepIntro };
  persist();
  emit();
}

export function useAppState(): AppState {
  return useSyncExternalStore(subscribe, getState, getState);
}

/* ------------------------------------------------------------------ */
/* Nouveau : parcours module -> chapitre -> leçons                       */
/* ------------------------------------------------------------------ */

/** Démarre une nouvelle leçon (initialise les coeurs à 5). */
export function startLesson(lessonId: string, _moduleId: string, _chapterId: string): void {
  update((draft) => {
    draft.lessonProgress[lessonId] = {
      lessonId,
      score: 0,
      heartsRemain: 5,
      mistakes: 0,
      completedAt: 0,
      missedExIds: [],
    };
    draft.heartManager[lessonId] = { hearts: 5, mistakes: 0 };
    draft.lastStudy = todayKey(new Date().getTime());
  });
}

/** Enregistre une réponse. Retourne `true` si la leçon doit redémarrer (0 coeurs). */
export function recordAnswer(lessonId: string, ok: boolean): boolean {
  update((draft) => {
    const progress = draft.lessonProgress[lessonId];
    if (!progress) return;
    const hm = draft.heartManager[lessonId];
    if (!hm) return;
    if (!ok) {
      hm.hearts -= 1;
      hm.mistakes += 1;
      progress.mistakes += 1;
      if (hm.hearts <= 0) {
        // Leçon redémarrée du début
        hm.hearts = 5;
        hm.mistakes = 0;
        progress.mistakes = 0;
        return true; // signaler restart
      }
      progress.heartsRemain = hm.hearts;
      progress.score = Math.max(0, (progress.score + (ok ? 20 : 0)) / 2);
    } else {
      hm.mistakes = 0;
      progress.score = Math.max(0, (progress.score + 20) / 2);
      if (progress.heartsRemain < hm.hearts) {
        progress.heartsRemain = hm.hearts;
      }
    }
  });
  return false;
}

/** Finalise la leçon. Retourne le feedback : "continue" | "choice" (continue/revision). */
export function endLesson(
  lessonId: string,
  score: number,
  missedExIds: number[]
): "continue" | "choice" {
  const wasFailed = missedExIds.length > 0;
  update((draft) => {
    const progress = draft.lessonProgress[lessonId];
    if (!progress) return;
    progress.score = Math.min(100, score);
    progress.missedExIds = missedExIds;
    progress.completedAt = Date.now();
  });
  return wasFailed ? "choice" : "continue";
}

/** Ajoute un élément à la file de révision ("Revisions en attente"). */
export function addToReviewQueue(item: Omit<ReviewQueueItem, "status" | "missedAt">): void {
  update((draft) => {
    draft.reviewQueue.push({
      ...item,
      status: "unlocked",
      missedAt: Date.now(),
    });
  });
}

/** Marque une leçon de la queue comme jouée. */
export function markReviewPlayed(lessonId: string): void {
  update((draft) => {
    const item = draft.reviewQueue.find((x) => x.lessonId === lessonId);
    if (item) item.status = "played";
  });
}

/* ------------------------------------------------------------------ */
/* Séances « histoire » — progression, reprise, récompense idempotente  */
/* ------------------------------------------------------------------ */

/** Récompense de participation d'une séance histoire (guide §9 du mode). */
export const STORY_SESSION_XP = 15;

/** Démarre une séance à zéro (écrase toute progression antérieure). */
export function startStorySession(storyId: string, contentVersion: number, now: number): void {
  update((draft) => {
    draft.storyProgress[storyId] = {
      storyId,
      contentVersion,
      step: "REVIEW",
      qIndex: 0,
      drillIndex: 0,
      reviewIndex: 0,
      stats: { firstTryIds: [], mistakeIds: [], helpedIds: [], difficultPhraseIds: [] },
      reviewRatings: {},
      retellAttempted: false,
      transferTaskAttempted: false,
      transferTaskPending: false,
      savedAt: now,
      completedAt: 0,
      rewardClaimed: false,
    };
  });
}

/** Persiste l'état du moteur pour la reprise après interruption. */
export function saveStoryProgress(storyId: string, s: SessionState, now: number): void {
  update((draft) => {
    const prev = draft.storyProgress[storyId];
    draft.storyProgress[storyId] = {
      storyId,
      contentVersion: s.contentVersion,
      step: s.step,
      qIndex: s.qIndex,
      drillIndex: s.drillIndex,
      reviewIndex: s.reviewIndex,
      stats: s.stats,
      reviewRatings: { ...s.reviewRatings },
      retellAttempted: s.retellAttempted,
      transferTaskAttempted: s.transferTaskAttempted,
      transferTaskPending: s.transferTaskPending,
      savedAt: now,
      completedAt: prev?.completedAt ?? 0,
      rewardClaimed: prev?.rewardClaimed ?? false,
    };
  });
}

/**
 * Récompense de fin de séance — idempotente : le drapeau `rewardClaimed` est
 * vérifié et posé dans la même recette, donc reprendre le bilan ou rejouer
 * l'effet (React StrictMode) n'attribue jamais deux fois les XP.
 * Retourne l'XP effectivement gagné (0 si déjà attribué).
 */
export function claimStoryReward(storyId: string, now: number): { xp: number; goalHit: boolean } {
  let result = { xp: 0, goalHit: false };
  update((draft) => {
    const p = draft.storyProgress[storyId];
    if (!p || p.rewardClaimed) return;
    p.rewardClaimed = true;
    p.completedAt = now;
    p.savedAt = now;
    const out = addXp(draft, STORY_SESSION_XP, now);
    touchStreak(draft, now);
    result = { xp: out.gained, goalHit: out.goalHit };
  });
  return result;
}
