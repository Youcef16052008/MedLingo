import { describe, it, expect } from "vitest";
import { makeDefaults } from "../store";
import { TROPHIES, evaluate, claimTrophies, type TrophyId } from "../trophies";

const NOW = 1_700_000_000_000;

const state = (overrides: Record<string, unknown> = {}) => ({
  ...makeDefaults(NOW),
  ...overrides,
}) as ReturnType<typeof makeDefaults>;

describe("trophies", () => {
  it("définit 12 trophées avec clés i18n uniques", () => {
    expect(TROPHIES).toHaveLength(12);
    expect(new Set(TROPHIES.map((t) => t.id)).size).toBe(12);
    expect(new Set(TROPHIES.map((t) => t.titleKey)).size).toBe(12);
    expect(new Set(TROPHIES.map((t) => t.descKey)).size).toBe(12);
  });

  it("aucun trophée pour un joueur neuf", () => {
    expect(evaluate(state())).toEqual([]);
  });

  it("évalue chaque seuil", () => {
    const cases: [Record<string, unknown>, TrophyId][] = [
      [{ lessonBest: { "anat-1": 80 } }, "first_lesson"],
      [{ moduleBest: { anat: 100 } }, "first_perfect"],
      [{ streak: 7 }, "streak_7"],
      [{ streak: 30 }, "streak_30"],
      [{ xp: 1000 }, "xp_1000"],
      [{ xp: 5000 }, "xp_5000"],
      [{ flashReviewed: 100 }, "cards_100"],
      [{ quizDone: 10 }, "quizzes_10"],
      [{ moduleBest: { anat: 90 } }, "module_master"],
      [{ chestsOpened: 5 }, "chests_5"],
    ];
    for (const [overrides, id] of cases) {
      expect(evaluate(state(overrides))).toContain(id);
    }
  });

  it("F9 : quiz libre parfait (clé `free`) → first_perfect sans module_master", () => {
    const s = state({ moduleBest: { free: 100 } });
    expect(evaluate(s)).toContain("first_perfect");
    expect(evaluate(s)).not.toContain("module_master");
    // un vrai module ≥ 90 % débloque bien module_master
    expect(evaluate(state({ moduleBest: { anat: 90 } }))).toContain("module_master");
  });

  it("compte les jours d'objectif atteint sur la fenêtre hebdo", () => {
    const weeklyXp = { a: 25, b: 20, c: 30, d: 20, e: 50, f: 60, g: 100 };
    expect(evaluate(state({ weeklyXp, dailyGoal: 20 }))).toContain("goal_hit_7");
    expect(evaluate(state({ weeklyXp: { a: 25, b: 15 }, dailyGoal: 20 }))).not.toContain(
      "goal_hit_7"
    );
  });

  it("league_promoted dès le palier argent", () => {
    expect(evaluate(state({ league: { tier: "silver", weekStart: "", botSeed: 1 } }))).toContain(
      "league_promoted"
    );
    expect(evaluate(state())).not.toContain("league_promoted");
  });

  it("claimTrophies persiste et ne rend que les nouveaux", () => {
    const s = state({ streak: 12 });
    const first = claimTrophies(s, NOW);
    expect(first).toEqual(expect.arrayContaining(["streak_7"]));
    expect(s.trophies["streak_7"]).toBeTruthy();
    expect(s.trophies["xp_1000"]).toBeUndefined();

    // second appel : rien de nouveau
    expect(claimTrophies(s, NOW)).toEqual([]);
  });
});
