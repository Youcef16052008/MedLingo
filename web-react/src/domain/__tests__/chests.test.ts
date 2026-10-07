import { describe, it, expect } from "vitest";
import { CHEST_TABLE, openChest, earnChest, applyChestReward } from "../chests";

const NOW = 1_700_000_000_000;

/** `rand` contrôlé : on choisit directement la tranche de poids. */
const randAt = (value: number) => () => value;

describe("caisses", () => {
  it("la table totalise 100 %", () => {
    expect(CHEST_TABLE.reduce((s, r) => s + r.weight, 0)).toBe(100);
  });

  it("ouvre la récompense correspondant à la tranche tirée", () => {
    expect(openChest(randAt(0))).toEqual({ kind: "xp", amount: 20 });
    expect(openChest(randAt(0.35))).toEqual({ kind: "gems", amount: 30 });
    expect(openChest(randAt(0.65))).toEqual({ kind: "gems", amount: 50 });
    expect(openChest(randAt(0.87))).toEqual({ kind: "freeze", amount: 1 });
    expect(openChest(randAt(0.99))).toEqual({ kind: "gems", amount: 100 });
  });

  it("toute la table est atteignable", () => {
    for (let i = 0; i < 1000; i++) {
      const r = openChest(randAt(i / 1000));
      expect(CHEST_TABLE.some((row) => row.kind === r.kind && row.amount === r.amount)).toBe(true);
    }
  });

  it("quiz → caisse toujours ; flash → seulement à 10 cartes", () => {
    expect(earnChest({ pendingChests: 0 }, "quiz", 0)).toBe(true);
    expect(earnChest({ pendingChests: 0 }, "flash", 9)).toBe(false);
    expect(earnChest({ pendingChests: 0 }, "flash", 10)).toBe(true);
    const s = { pendingChests: 1 };
    earnChest(s, "quiz", 5);
    expect(s.pendingChests).toBe(2);
  });

  it("applique chaque type de récompense", () => {
    const base = {
      xp: 0,
      xpToday: 0,
      goalDate: "",
      dailyGoal: 20,
      weeklyXp: {},
      streak: 0,
      lastStudy: "",
      freezeCount: 0,
      gems: 0,
    };
    const gems = { ...base, weeklyXp: {} as Record<string, number> };
    applyChestReward(gems, { kind: "gems", amount: 50 }, NOW);
    expect(gems.gems).toBe(50);

    const freeze = { ...base, weeklyXp: {} as Record<string, number> };
    applyChestReward(freeze, { kind: "freeze", amount: 1 }, NOW);
    expect(freeze.freezeCount).toBe(1);

    const xp = { ...base, weeklyXp: {} as Record<string, number> };
    applyChestReward(xp, { kind: "xp", amount: 20 }, NOW);
    expect(xp.xp).toBe(20);
    expect(xp.xpToday).toBe(20);
  });
});
