/**
 * Trophées — 12 réalisations affichées en grille sur l'écran « Moi ».
 * `evaluate` est pur ; `claimTrophies` persiste les nouveaux gains (`trophies[id] = ISO`)
 * afin de déclencher la pop-up de récompense.
 */
import type { AppState } from "./store";

export type TrophyId =
  | "first_lesson"
  | "first_perfect"
  | "streak_7"
  | "streak_30"
  | "xp_1000"
  | "xp_5000"
  | "cards_100"
  | "quizzes_10"
  | "goal_hit_7"
  | "module_master"
  | "league_promoted"
  | "chests_5";

export interface Trophy {
  id: TrophyId;
  icon: string;
  titleKey: string;
  descKey: string;
}

export const TROPHIES: Trophy[] = [
  { id: "first_lesson", icon: "🌱", titleKey: "trophy_first_lesson", descKey: "trophy_first_lesson_d" },
  { id: "first_perfect", icon: "💯", titleKey: "trophy_first_perfect", descKey: "trophy_first_perfect_d" },
  { id: "streak_7", icon: "🔥", titleKey: "trophy_streak_7", descKey: "trophy_streak_7_d" },
  { id: "streak_30", icon: "🌋", titleKey: "trophy_streak_30", descKey: "trophy_streak_30_d" },
  { id: "xp_1000", icon: "⚡", titleKey: "trophy_xp_1000", descKey: "trophy_xp_1000_d" },
  { id: "xp_5000", icon: "🚀", titleKey: "trophy_xp_5000", descKey: "trophy_xp_5000_d" },
  { id: "cards_100", icon: "🗂️", titleKey: "trophy_cards_100", descKey: "trophy_cards_100_d" },
  { id: "quizzes_10", icon: "🧠", titleKey: "trophy_quizzes_10", descKey: "trophy_quizzes_10_d" },
  { id: "goal_hit_7", icon: "🎯", titleKey: "trophy_goal_hit_7", descKey: "trophy_goal_hit_7_d" },
  { id: "module_master", icon: "👑", titleKey: "trophy_module_master", descKey: "trophy_module_master_d" },
  { id: "league_promoted", icon: "📈", titleKey: "trophy_league_promoted", descKey: "trophy_league_promoted_d" },
  { id: "chests_5", icon: "📦", titleKey: "trophy_chests_5", descKey: "trophy_chests_5_d" },
];

export const trophyById = (id: TrophyId): Trophy =>
  TROPHIES.find((t) => t.id === id) ?? TROPHIES[0];

/** Jours de la semaine de ligue en cours ayant atteint l'objectif quotidien. */
function goalDaysReached(s: AppState): number {
  let n = 0;
  for (const [key, value] of Object.entries(s.weeklyXp)) {
    if (s.league.weekStart && key < s.league.weekStart) continue;
    if (value >= s.dailyGoal) n += 1;
  }
  return n;
}

/** Tous les trophées satisfaits par l'état courant (pur). */
export function evaluate(s: AppState): TrophyId[] {
  const best = Object.values(s.moduleBest);
  const won: TrophyId[] = [];

  // « Première leçon » = une leçon du parcours terminée (lessonBest renseigné),
  // pas n'importe quel quiz libre (quizDone) — B37.
  if (Object.keys(s.lessonBest).length >= 1) won.push("first_lesson");
  // « Sans faute » = 100 % sur une leçon du parcours OU sur un quiz libre
  // (pseudo-clé `moduleBest.free`, F9 — atteignable depuis le hub de pratique).
  if (best.some((v) => v >= 100)) won.push("first_perfect");
  if (s.streak >= 7) won.push("streak_7");
  if (s.streak >= 30) won.push("streak_30");
  if (s.xp >= 1000) won.push("xp_1000");
  if (s.xp >= 5000) won.push("xp_5000");
  if (s.flashReviewed >= 100) won.push("cards_100");
  if (s.quizDone >= 10) won.push("quizzes_10");
  if (goalDaysReached(s) >= 7) won.push("goal_hit_7");
  // « Maître de module » : un VRAI module ≥ 90 % — la clé pseudo-module
  // `free` (quiz libre, F9) ne compte pas.
  if (Object.entries(s.moduleBest).some(([id, v]) => id !== "free" && v >= 90))
    won.push("module_master");
  if (s.league.tier !== "bronze") won.push("league_promoted");
  if (s.chestsOpened >= 5) won.push("chests_5");

  return won;
}

/**
 * Persiste les trophées nouvellement gagnés (horodatage en ms) et retourne
 * uniquement ceux-ci — à afficher dans la pop-up de récompense.
 */
export function claimTrophies(s: AppState, now: number): TrophyId[] {
  const fresh = evaluate(s).filter((id) => !s.trophies[id]);
  for (const id of fresh) s.trophies[id] = now;
  return fresh;
}
