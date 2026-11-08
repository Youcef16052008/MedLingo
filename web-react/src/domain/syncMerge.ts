/**
 * Fusion de progression sync — R-07 / R-08 (2026-11-08).
 *
 * Le stockage distant (`user_progress.data`, jsonb) reçoit un blob dérivé du
 * store local. Plutôt qu'un last-write-wins sur le blob entier (qui écrase les
 * cartes SM-2 / scores édités sur l'autre appareil), on fusionne **carte par
 * carte** : aucune régression possible sur une carte, un score, un trophée.
 *
 * Règles (déterministes, symétriques — merge(a,b) === merge(b,a)) :
 * - compteurs monotonement croissants (xp, streak, answered, correct…) : max
 * - meilleur score par clé (lessonBest, moduleBest) : max
 * - SM-2 par carte : rep le plus avancé gagne ; à égalité, `due` le plus
 *   ancien (revoir plus tôt) gagne — jamais de régression de révision
 * - progressions (lessonProgress, storyProgress) : le plus récent gagne
 * - trophées : le timestamp le plus ancien gagne (premier déblocage)
 * - weeklyXp par jour : max
 * - file de révision : union par lessonId (la plus longue gagne)
 * - lang / dailyGoal / introSeen : préférence locale (device-specific)
 */

import type { AppState, LessonProgress, ReviewQueueItem, StoryProgress } from "./store";
import type { Sched } from "./sm2";

/** Sous-ensemble du store qui voyage dans le cloud. */
export type SyncBlob = Pick<
  AppState,
  | "xp"
  | "streak"
  | "lastStudy"
  | "gems"
  | "xpToday"
  | "goalDate"
  | "freezeCount"
  | "pendingChests"
  | "chestsOpened"
  | "weeklyXp"
  | "moduleBest"
  | "lessonBest"
  | "trophies"
  | "answered"
  | "correct"
  | "flashReviewed"
  | "quizDone"
  | "sm2"
  | "exprSm2"
  | "lessonProgress"
  | "reviewQueue"
  | "storyProgress"
>;

function maxNum(a: number, b: number): number {
  return Number.isFinite(a) && Number.isFinite(b) ? Math.max(a, b) : Number.isFinite(a) ? a : b;
}

function maxMap(a: Record<string, number>, b: Record<string, number>): Record<string, number> {
  const out: Record<string, number> = { ...a };
  for (const [k, v] of Object.entries(b)) out[k] = k in out ? maxNum(out[k], v) : v;
  return out;
}

function laterDate(a: string, b: string): string {
  return new Date(a).getTime() >= new Date(b).getTime() ? a : b;
}

/** SM-2 : rep le plus avancé gagne ; égalité → due le plus ancien. */
export function mergeSched(a: Sched, b: Sched): Sched {
  if (a.rep !== b.rep) return a.rep > b.rep ? a : b;
  return a.due <= b.due ? a : b;
}

function mergeSm2(
  a: Record<string, Sched>,
  b: Record<string, Sched>
): Record<string, Sched> {
  const out: Record<string, Sched> = { ...a };
  for (const [k, v] of Object.entries(b)) out[k] = k in out ? mergeSched(out[k], v) : v;
  return out;
}

/** Leçon : score le plus haut gagne ; à égalité la plus récente. */
function mergeLessonProgress(a: LessonProgress, b: LessonProgress): LessonProgress {
  if (a.score !== b.score) return a.score > b.score ? a : b;
  return (a.completedAt || 0) >= (b.completedAt || 0) ? a : b;
}

function mergeRecord<T>(
  a: Record<string, T>,
  b: Record<string, T>,
  pick: (x: T, y: T) => T
): Record<string, T> {
  const out: Record<string, T> = { ...a };
  for (const [k, v] of Object.entries(b)) out[k] = k in out ? pick(out[k], v) : v;
  return out;
}

/** Histoire : la session la plus récente gagne ; une session terminée l'emporte. */
function mergeStoryProgress(a: StoryProgress, b: StoryProgress): StoryProgress {
  const aDone = (a.completedAt || 0) > 0;
  const bDone = (b.completedAt || 0) > 0;
  if (aDone !== bDone) return aDone ? a : b;
  return (a.savedAt || 0) >= (b.savedAt || 0) ? a : b;
}

/** File de révision : union par lessonId, la plus longue gagne. */
function mergeQueue(a: ReviewQueueItem[], b: ReviewQueueItem[]): ReviewQueueItem[] {
  const byId = new Map<string, ReviewQueueItem>();
  for (const item of [...a, ...b]) {
    const prev = byId.get(item.lessonId);
    if (!prev || item.exIds.length > prev.exIds.length) byId.set(item.lessonId, item);
  }
  return [...byId.values()];
}

/**
 * Fusion symétrique de deux blobs. Les champs absents d'un côté (blob distant
 * d'une ancienne version) sont considérés comme neutres : on garde l'autre.
 */
export function mergeSyncBlobs(local: SyncBlob, remote: Partial<SyncBlob>): SyncBlob {
  const r = remote as Partial<SyncBlob>;
  return {
    xp: r.xp !== undefined ? maxNum(local.xp, r.xp) : local.xp,
    streak: r.streak !== undefined ? maxNum(local.streak, r.streak) : local.streak,
    lastStudy:
      r.lastStudy !== undefined && r.lastStudy
        ? laterDate(local.lastStudy, r.lastStudy)
        : local.lastStudy,
    gems: r.gems !== undefined ? maxNum(local.gems, r.gems) : local.gems,
    xpToday:
      r.xpToday !== undefined && r.goalDate === local.goalDate
        ? maxNum(local.xpToday, r.xpToday)
        : local.xpToday,
    goalDate: local.goalDate,
    freezeCount:
      r.freezeCount !== undefined ? maxNum(local.freezeCount, r.freezeCount) : local.freezeCount,
    pendingChests:
      r.pendingChests !== undefined
        ? maxNum(local.pendingChests, r.pendingChests)
        : local.pendingChests,
    chestsOpened:
      r.chestsOpened !== undefined ? maxNum(local.chestsOpened, r.chestsOpened) : local.chestsOpened,
    weeklyXp: r.weeklyXp ? maxMap(local.weeklyXp, r.weeklyXp) : local.weeklyXp,
    moduleBest: r.moduleBest ? maxMap(local.moduleBest, r.moduleBest) : local.moduleBest,
    lessonBest: r.lessonBest ? maxMap(local.lessonBest, r.lessonBest) : local.lessonBest,
    // Trophées : le timestamp le plus ancien (premier déblocage) gagne.
    trophies: r.trophies
      ? mergeRecord(local.trophies, r.trophies, (x, y) => Math.min(x, y))
      : local.trophies,
    answered: r.answered !== undefined ? maxNum(local.answered, r.answered) : local.answered,
    correct: r.correct !== undefined ? maxNum(local.correct, r.correct) : local.correct,
    flashReviewed:
      r.flashReviewed !== undefined ? maxNum(local.flashReviewed, r.flashReviewed) : local.flashReviewed,
    quizDone: r.quizDone !== undefined ? maxNum(local.quizDone, r.quizDone) : local.quizDone,
    sm2: r.sm2 ? mergeSm2(local.sm2, r.sm2) : local.sm2,
    exprSm2: r.exprSm2 ? mergeSm2(local.exprSm2, r.exprSm2) : local.exprSm2,
    lessonProgress: r.lessonProgress
      ? mergeRecord(local.lessonProgress, r.lessonProgress, mergeLessonProgress)
      : local.lessonProgress,
    reviewQueue: r.reviewQueue ? mergeQueue(local.reviewQueue, r.reviewQueue) : local.reviewQueue,
    storyProgress: r.storyProgress
      ? mergeRecord(local.storyProgress, r.storyProgress, mergeStoryProgress)
      : local.storyProgress,
  };
}

/** Extrait le blob syncable de l'état courant. */
export function toSyncBlob(s: AppState): SyncBlob {
  return {
    xp: s.xp,
    streak: s.streak,
    lastStudy: s.lastStudy,
    gems: s.gems,
    xpToday: s.xpToday,
    goalDate: s.goalDate,
    freezeCount: s.freezeCount,
    pendingChests: s.pendingChests,
    chestsOpened: s.chestsOpened,
    weeklyXp: { ...s.weeklyXp },
    moduleBest: { ...s.moduleBest },
    lessonBest: { ...s.lessonBest },
    trophies: { ...s.trophies },
    answered: s.answered,
    correct: s.correct,
    flashReviewed: s.flashReviewed,
    quizDone: s.quizDone,
    sm2: { ...s.sm2 },
    exprSm2: { ...s.exprSm2 },
    lessonProgress: { ...s.lessonProgress },
    reviewQueue: [...s.reviewQueue],
    storyProgress: { ...s.storyProgress },
  };
}

/** Applique un blob fusionné sur le store local (sans toucher aux préférences device). */
export function applySyncBlob(s: AppState, blob: SyncBlob): void {
  s.xp = blob.xp;
  s.streak = blob.streak;
  s.lastStudy = blob.lastStudy;
  s.gems = blob.gems;
  s.xpToday = blob.xpToday;
  s.goalDate = blob.goalDate;
  s.freezeCount = blob.freezeCount;
  s.pendingChests = blob.pendingChests;
  s.chestsOpened = blob.chestsOpened;
  s.weeklyXp = blob.weeklyXp;
  s.moduleBest = blob.moduleBest;
  s.lessonBest = blob.lessonBest;
  s.trophies = blob.trophies;
  s.answered = blob.answered;
  s.correct = blob.correct;
  s.flashReviewed = blob.flashReviewed;
  s.quizDone = blob.quizDone;
  s.sm2 = blob.sm2;
  s.exprSm2 = blob.exprSm2;
  s.lessonProgress = blob.lessonProgress;
  s.reviewQueue = blob.reviewQueue;
  s.storyProgress = blob.storyProgress;
}
