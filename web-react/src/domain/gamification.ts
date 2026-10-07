/**
 * Gamification : série 🔥, objectif quotidien ⭐, congélations ❄️, gemmes 💎.
 * Port des règles du référentiel Android — horloge injectée (`now` en ms).
 * Les cœurs ❤️ ont été supprimés : les sessions n'ont plus de barrière d'erreur.
 */

/** Objectifs quotidiens proposés (XP). */
export const DAILY_GOAL_OPTIONS = [20, 50, 100] as const;
export type DailyGoal = (typeof DAILY_GOAL_OPTIONS)[number];

/** Prix d'une congélation de série. */
export const FREEZE_COST_GEMS = 200;

/** Gemmes d'une leçon terminée (miroir de `GemsManager`). */
export const GEMS_LESSON_COMPLETE = 10;
/** Bonus de leçon parfaite (100 %). */
export const GEMS_PERFECT_LESSON_BONUS = 10;

/**
 * Gemmes d'une leçon — miroir exact de `GemsManager.calculateGemsForLesson` :
 * < 50 % de réussite → 0 (anti-farming), sinon 10, + 10 si parfaite.
 */
export function calculateGemsForLesson(correctAnswers: number, totalQuestions: number): number {
  if (totalQuestions === 0) return 0;
  if (correctAnswers / totalQuestions < 0.5) return 0;
  return GEMS_LESSON_COMPLETE + (correctAnswers === totalQuestions ? GEMS_PERFECT_LESSON_BONUS : 0);
}

/** État minimal manipulé par `addXp`. */
export interface XpState {
  xp: number;
  xpToday: number;
  goalDate: string;
  dailyGoal: number;
  weeklyXp: Record<string, number>;
  streak: number;
  lastStudy: string;
  freezeCount: number;
}

/** État minimal manipulé par `touchStreak`. */
export interface StreakState {
  streak: number;
  lastStudy: string;
  freezeCount?: number;
}

export interface XpOutcome {
  gained: number;
  /** L'objectif quotidien vient d'être atteint (une seule fois par jour). */
  goalHit: boolean;
}

export interface StreakOutcome {
  streak: number;
  /** Une congélation a été consommée pour éviter la rupture de série. */
  freezeUsed: boolean;
}

export function todayKey(now: number): string {
  const d = new Date(now);
  const p = (n: number) => String(n).padStart(2, "0");
  return `${d.getFullYear()}-${p(d.getMonth() + 1)}-${p(d.getDate())}`;
}

/** Clés des 7 derniers jours (aujourd'hui inclus), du plus ancien au plus récent. */
export function last7Days(now: number): string[] {
  const out: string[] = [];
  for (let i = 6; i >= 0; i--) out.push(todayKey(now - i * 86_400_000));
  return out;
}

/**
 * Incrémente la série si l'utilisateur n'avait pas encore étudié aujourd'hui.
 * Écart d'un jour ou plus : une congélation consommée conserve la série,
 * sinon la série repart à 1.
 */
export function touchStreak(state: StreakState, now: number): StreakOutcome {
  const today = todayKey(now);
  if (state.lastStudy === today) return { streak: state.streak, freezeUsed: false };

  // Base sans `lastStudy` (migration v10) : on adopte le jour sans casser la
  // série existante ; un utilisateur tout neuf (série 0) démarre à 1.
  if (state.lastStudy === "") {
    if (state.streak <= 0) state.streak = 1;
    state.lastStudy = today;
    return { streak: state.streak, freezeUsed: false };
  }

  const yesterday = todayKey(now - 86_400_000);
  if (state.lastStudy === yesterday) {
    state.streak += 1;
    state.lastStudy = today;
    return { streak: state.streak, freezeUsed: false };
  }

  const frozen = (state.freezeCount ?? 0) > 0;
  if (frozen) {
    // Écart > 1 jour : la congélation comble le trou, la série reste telle quelle.
    state.freezeCount = (state.freezeCount ?? 0) - 1;
    state.lastStudy = today;
    return { streak: state.streak, freezeUsed: true };
  }

  state.streak = 1;
  state.lastStudy = today;
  return { streak: state.streak, freezeUsed: false };
}

/**
 * Ajoute de l'XP : total, du jour (reset au changement de jour civil) et
 * hebdomadaire (fenêtre glissante de 7 jours, pour la ligue).
 * touche aussi la série et signale l'atteinte de l'objectif quotidien.
 */
export function addXp(state: XpState, n: number, now: number): XpOutcome {
  const today = todayKey(now);
  const newDay = state.goalDate !== today;
  if (newDay) {
    state.xpToday = 0;
    state.goalDate = today;
  }

  const before = state.xpToday;
  state.xp += n;
  state.xpToday += n;
  state.weeklyXp[today] = (state.weeklyXp[today] ?? 0) + n;

  // Pas de purge ici : la ligue court du lundi au dimanche et le calcul du
  // classement final (rollover) a besoin de la semaine close complète. La
  // remise à zéro est faite par `rollover()` au changement de semaine.

  touchStreak(state, now);

  const goalHit = before < state.dailyGoal && state.xpToday >= state.dailyGoal;
  return { gained: n, goalHit };
}

/** Progression de l'objectif quotidien (0 si le jour a changé). */
export function goalProgress(
  s: Pick<XpState, "xpToday" | "goalDate" | "dailyGoal">,
  now: number
): { today: number; goal: number; pct: number; done: boolean } {
  const today = s.goalDate === todayKey(now) ? s.xpToday : 0;
  const goal = s.dailyGoal;
  // Troncature entière, comme `(today * 100) / goal` côté Kotlin.
  const pct = goal > 0 ? Math.min(100, Math.floor((today * 100) / goal)) : 0;
  return { today, goal, pct, done: today >= goal };
}

/** Achat d'une congélation : 200 💎. Retourne `false` si les gemmes manquent. */
export function buyFreeze(s: { gems: number; freezeCount: number }): boolean {
  if (s.gems < FREEZE_COST_GEMS) return false;
  s.gems -= FREEZE_COST_GEMS;
  s.freezeCount += 1;
  return true;
}
