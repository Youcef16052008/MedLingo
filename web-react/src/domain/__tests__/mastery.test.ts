import { describe, it, expect } from "vitest";
import { moduleMastery, weakestTerms, weakChapters } from "../mastery";
import type { Sched } from "../sm2";

const NOW = 1_700_000_000_000;
const DAY = 86_400_000;

const card = (over: Partial<Sched> = {}): Sched => ({
  rep: 3,
  ef: 2.5,
  iv: 10,
  due: NOW + 3 * DAY,
  ...over,
} as Sched);

describe("moduleMastery (WS5.1 — force unifiée)", () => {
  it("module jamais touché → force 0", () => {
    const m = moduleMastery({ moduleBest: {}, sm2: {}, termIds: [1, 2, 3], dailyGoal: 20 }, NOW);
    expect(m.force).toBe(0);
    expect(m.unseen).toBe(3);
    expect(m.due).toBe(0);
  });

  it("quiz seul (aucune carte) → force = quizBest", () => {
    const m = moduleMastery({ moduleBest: { quiz: 80 }, sm2: {}, termIds: [], dailyGoal: 20 }, NOW);
    expect(m.force).toBe(80);
  });

  it("cartes saines → force plus haute qu'avec des cartes en retard", () => {
    const healthy = moduleMastery(
      { moduleBest: { quiz: 70 }, sm2: { "1": card(), "2": card() }, termIds: [1, 2], dailyGoal: 20 },
      NOW
    );
    const overdue = moduleMastery(
      {
        moduleBest: { quiz: 70 },
        sm2: { "1": card({ due: NOW - 8 * DAY }), "2": card({ due: NOW - 8 * DAY }) },
        termIds: [1, 2],
        dailyGoal: 20,
      },
      NOW
    );
    expect(healthy.force).toBeGreaterThan(overdue.force);
    expect(overdue.due).toBe(2);
  });

  it("est borné 0–100 et compteur dues exact", () => {
    const m = moduleMastery(
      {
        moduleBest: { quiz: 100 },
        sm2: { "1": card({ due: NOW - DAY, ef: 3 }), "2": card({ due: NOW - 2 * DAY, ef: 3 }) },
        termIds: [1, 2],
        dailyGoal: 20,
      },
      NOW
    );
    expect(m.force).toBeLessThanOrEqual(100);
    expect(m.due).toBe(2);
    expect(m.started).toBe(2);
  });
});

describe("weakestTerms (WS5.2 — ordre de révision)", () => {
  it("échus (retard le plus ancien d'abord) → jamais vus → EF faibles", () => {
    const sm2: Record<string, Sched> = {
      "1": card({ due: NOW - DAY, ef: 2.5 }), // en retard
      "2": card({ due: NOW - 3 * DAY, ef: 2.5 }), // en retard + ancien
      "3": card({ due: NOW + 5 * DAY, ef: 1.9 }), // sain mais EF bas
      // 4 : absent → unseen
    };
    const out = weakestTerms([1, 2, 3, 4], sm2, NOW);
    expect(out[0]).toMatchObject({ termId: 2, reason: "due" });
    expect(out[1]).toMatchObject({ termId: 1, reason: "due" });
    expect(out[2]).toMatchObject({ termId: 4, reason: "unseen" });
    expect(out[3]).toMatchObject({ termId: 3, reason: "low_ef" });
  });

  it("respecte la limite", () => {
    const out = weakestTerms([1, 2, 3, 4], {}, NOW, 2);
    expect(out).toHaveLength(2);
    expect(out.every((t) => t.reason === "unseen")).toBe(true);
  });
});

describe("weakChapters (WS5.3 — renvoi EMD)", () => {
  it("compte et trie les chapitres ratés", () => {
    const out = weakChapters([
      { chapter: "Angiologie", module: "Anatomie" },
      { chapter: "Angiologie", module: "Anatomie" },
      { chapter: "Respiratoire", module: "Physiologie" },
    ]);
    expect(out[0]).toEqual({ chapter: "Angiologie", module: "Anatomie", count: 2 });
    expect(out[1].count).toBe(1);
  });
});
