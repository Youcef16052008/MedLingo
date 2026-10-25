import { describe, it, expect } from "vitest";
import { goalStreakOf, todayKey, yesterdayKey } from "../gamification";
import { cadenceBonusChest, CADENCE_GOAL_DAYS } from "../chests";

const NOW = new Date("2026-10-25T15:00:00").getTime();
const DAY = 86_400_000;

/** État minimal : historique hebdo = {clé jour: xp}. */
function state(weekXp: Record<string, number>, dailyGoal = 20) {
  return { weeklyXp: weekXp, dailyGoal, goalDate: todayKey(NOW), xpToday: 0 };
}

describe("goalStreakOf (WS4 levier 3 — cadence d'objectifs)", () => {
  it("compte les journées-objectif consécutives jusqu'à aujourd'hui", () => {
    const s = state({
      [todayKey(NOW)]: 20,
      [yesterdayKey(NOW)]: 25,
      [todayKey(NOW - 2 * DAY)]: 30,
    });
    expect(goalStreakOf(s, NOW)).toBe(3);
  });

  it("s'arrête au premier jour sous l'objectif", () => {
    const s = state({
      [todayKey(NOW)]: 20,
      [yesterdayKey(NOW)]: 10, // sous l'objectif → coupe
      [todayKey(NOW - 2 * DAY)]: 30,
    });
    expect(goalStreakOf(s, NOW)).toBe(1);
  });

  it("aujourd'hui pas encore atteint → compte depuis hier", () => {
    const s = state({ [yesterdayKey(NOW)]: 20, [todayKey(NOW - 2 * DAY)]: 20 });
    expect(goalStreakOf(s, NOW)).toBe(2);
  });

  it("zéro si ni aujourd'hui ni hier atteints", () => {
    expect(goalStreakOf(state({ [todayKey(NOW - 2 * DAY)]: 50 }), NOW)).toBe(0);
  });

  it("dailyGoal 0 (désactivé) → jamais de série", () => {
    expect(goalStreakOf(state({ [todayKey(NOW)]: 999 }, 0), NOW)).toBe(0);
  });
});

describe("cadenceBonusChest (coffre tous les 3 jours d'affilée)", () => {
  it("déclenche à 3, 6, 9… pas à 1, 2, 4, 5", () => {
    expect(CADENCE_GOAL_DAYS).toBe(3);
    expect(cadenceBonusChest(0)).toBe(false);
    expect(cadenceBonusChest(1)).toBe(false);
    expect(cadenceBonusChest(2)).toBe(false);
    expect(cadenceBonusChest(3)).toBe(true);
    expect(cadenceBonusChest(5)).toBe(false);
    expect(cadenceBonusChest(6)).toBe(true);
  });
});
