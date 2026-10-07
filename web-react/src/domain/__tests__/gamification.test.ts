import { describe, it, expect } from "vitest";
import {
  addXp,
  touchStreak,
  goalProgress,
  buyFreeze,
  todayKey,
  last7Days,
  calculateGemsForLesson,
  FREEZE_COST_GEMS,
} from "../gamification";

const NOW = 1_700_000_000_000; // 2023-11-14T22:13:20Z
const DAY = 86_400_000;

function state(overrides: Record<string, unknown> = {}) {
  return {
    xp: 0,
    xpToday: 0,
    goalDate: "",
    dailyGoal: 20,
    weeklyXp: {} as Record<string, number>,
    streak: 0,
    lastStudy: "",
    freezeCount: 0,
    gems: 0,
    ...overrides,
  };
}

describe("série (streak)", () => {
  it("premier jour d'étude → série à 1", () => {
    const s = state();
    touchStreak(s, NOW);
    expect(s.streak).toBe(1);
    expect(s.lastStudy).toBe(todayKey(NOW));
  });

  it("un second appel le même jour ne change rien", () => {
    const s = state({ streak: 3, lastStudy: todayKey(NOW) });
    touchStreak(s, NOW);
    expect(s.streak).toBe(3);
  });

  it("le lendemain la série s'incrémente", () => {
    const s = state({ streak: 3, lastStudy: todayKey(NOW - DAY) });
    touchStreak(s, NOW);
    expect(s.streak).toBe(4);
  });

  it("après une journée d'absence la série repart à 1", () => {
    const s = state({ streak: 9, lastStudy: todayKey(NOW - 3 * DAY) });
    touchStreak(s, NOW);
    expect(s.streak).toBe(1);
  });

  it("avec une congélation l'absence ne casse pas la série", () => {
    const s = state({ streak: 9, lastStudy: todayKey(NOW - 3 * DAY), freezeCount: 2 });
    const out = touchStreak(s, NOW);
    expect(out.freezeUsed).toBe(true);
    expect(s.streak).toBe(9);
    expect(s.freezeCount).toBe(1);
    expect(s.lastStudy).toBe(todayKey(NOW));
  });

  it("la congélation ne s'applique qu'une fois par absence", () => {
    const s = state({ streak: 4, lastStudy: todayKey(NOW - 5 * DAY), freezeCount: 1 });
    expect(touchStreak(s, NOW).freezeUsed).toBe(true);
    expect(s.freezeCount).toBe(0);
    expect(s.streak).toBe(4);

    // absence suivante, sans congélation : la série casse
    s.lastStudy = todayKey(NOW - 5 * DAY);
    const second = touchStreak(s, NOW);
    expect(second.freezeUsed).toBe(false);
    expect(s.streak).toBe(1);
  });

  it("une base sans lastStudy adopte le jour sans casser la série (migration)", () => {
    const s = state({ streak: 12, lastStudy: "" });
    touchStreak(s, NOW);
    expect(s.streak).toBe(12);
    expect(s.lastStudy).toBe(todayKey(NOW));
  });

  it("une base sans lastStudy ni série (nouvel utilisateur) ouvre à 1", () => {
    const s = state({ streak: 0, lastStudy: "" });
    touchStreak(s, NOW);
    expect(s.streak).toBe(1);
  });
});

describe("objectif quotidien (addXp)", () => {
  it("accumule dans la journée", () => {
    const s = state({ goalDate: todayKey(NOW) });
    addXp(s, 10, NOW);
    addXp(s, 5, NOW);
    expect(s.xp).toBe(15);
    expect(s.xpToday).toBe(15);
  });

  it("remet xpToday à zéro au changement de jour", () => {
    const s = state({ goalDate: todayKey(NOW - DAY), xpToday: 45 });
    addXp(s, 10, NOW);
    expect(s.xpToday).toBe(10);
    expect(s.goalDate).toBe(todayKey(NOW));
    expect(s.xp).toBe(10);
  });

  it("signale l'atteinte de l'objectif une seule fois", () => {
    const s = state();
    expect(addXp(s, 15, NOW).goalHit).toBe(false);
    expect(addXp(s, 10, NOW).goalHit).toBe(true);
    expect(addXp(s, 10, NOW).goalHit).toBe(false);
    expect(s.xpToday).toBe(35);
  });

  it("l'XP alimente la fenêtre hebdomadaire sans purge en place", () => {
    // Pas de purge dans addXp : le calcul du classement final de la ligue a
    // besoin de la semaine close complète. La remise à zéro est faite par
    // rollover() au changement de semaine.
    const s = state({ weeklyXp: { "2020-01-01": 500 } });
    addXp(s, 30, NOW);
    expect(s.weeklyXp[todayKey(NOW)]).toBe(30);
    expect(s.weeklyXp["2020-01-01"]).toBe(500);
  });

  it("touchStreak est appelé en même temps que l'XP", () => {
    const s = state({ streak: 7, lastStudy: todayKey(NOW - DAY) });
    addXp(s, 10, NOW);
    expect(s.streak).toBe(8);
  });
});

describe("goalProgress", () => {
  it("calcule le pourcentage", () => {
    const s = state({ goalDate: todayKey(NOW), xpToday: 10, dailyGoal: 20 });
    expect(goalProgress(s, NOW)).toEqual({ today: 10, goal: 20, pct: 50, done: false });
  });

  it("affiche 0 dès que le jour a changé", () => {
    const s = state({ goalDate: todayKey(NOW - DAY), xpToday: 40, dailyGoal: 20 });
    expect(goalProgress(s, NOW)).toEqual({ today: 0, goal: 20, pct: 0, done: false });
  });

  it("tronque le pourcentage comme l'entier Kotlin (pas d'arrondi)", () => {
    // 2 * 100 / 30 = 6.66… → Kotlin 6, Math.round donnerait 7
    const s = state({ goalDate: todayKey(NOW), xpToday: 2, dailyGoal: 30 });
    expect(goalProgress(s, NOW).pct).toBe(6);
  });
});

describe("gemmes de leçon (calculateGemsForLesson)", () => {
  it("10 gemmes pour une leçon réussie à ≥ 50 %", () => {
    expect(calculateGemsForLesson(5, 10)).toBe(10);
    expect(calculateGemsForLesson(7, 10)).toBe(10);
  });

  it("20 gemmes pour une leçon parfaite", () => {
    expect(calculateGemsForLesson(10, 10)).toBe(20);
  });

  it("0 gemme sous 50 % (anti-farming)", () => {
    expect(calculateGemsForLesson(4, 10)).toBe(0);
    expect(calculateGemsForLesson(0, 10)).toBe(0);
  });

  it("0 pour une session vide", () => {
    expect(calculateGemsForLesson(0, 0)).toBe(0);
  });
});

describe("congélations", () => {
  it("achète une congélation pour 200 💎", () => {
    const s = state({ gems: 250 });
    expect(buyFreeze(s)).toBe(true);
    expect(s.gems).toBe(50);
    expect(s.freezeCount).toBe(1);
  });

  it("refuse si les gemmes manquent", () => {
    const s = state({ gems: 199 });
    expect(buyFreeze(s)).toBe(false);
    expect(s.gems).toBe(199);
    expect(s.freezeCount).toBe(0);
    expect(FREEZE_COST_GEMS).toBe(200);
  });
});

describe("last7Days", () => {
  it("retourne 7 jour clés du plus ancien au plus récent", () => {
    const days = last7Days(NOW);
    expect(days).toHaveLength(7);
    expect(days[6]).toBe(todayKey(NOW));
    expect(days[0]).toBe(todayKey(NOW - 6 * DAY));
  });
});
