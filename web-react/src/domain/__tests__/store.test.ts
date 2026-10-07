import { describe, it, expect } from "vitest";
import { makeDefaults, revive } from "../store";

const NOW = 1_700_000_000_000;

describe("revive", () => {
  it("retourne les défauts si rien n'est stocké", () => {
    expect(revive(null, NOW)).toEqual(makeDefaults(NOW));
  });

  it("retombe sur les défauts si le store est corrompu", () => {
    expect(revive("{pas du json", NOW)).toEqual(makeDefaults(NOW));
  });

  it("conserve un historique écrit avec le numérotage courant", () => {
    const raw = JSON.stringify({
      ...makeDefaults(NOW),
      sm2: { 42: { rep: 3, ef: 2.5, iv: 10, due: NOW } },
    });
    const state = revive(raw, NOW);
    expect(Object.keys(state.sm2)).toEqual(["42"]);
    expect(state.sm2["42"].rep).toBe(3);
  });

  it("purge l'historique quand le numérotage des termes a changé", () => {
    // L'ancien site numérotait ses propres ids : le 42 n'est plus le même terme.
    const raw = JSON.stringify({
      ...makeDefaults(NOW),
      sm2: { 42: { rep: 3, ef: 2.5, iv: 10, due: NOW } },
      dataVersion: "ancien-numerotage",
    });
    expect(revive(raw, NOW).sm2).toEqual({});
  });

  it("conserve les statistiques du joueur même quand l'historique est purgé", () => {
    const raw = JSON.stringify({
      ...makeDefaults(NOW),
      gems: 42,
      streak: 9,
      xp: 1200,
      lang: "ar",
      introSeen: true,
      sm2: { 42: { rep: 3, ef: 2.5, iv: 10, due: NOW } },
      dataVersion: "ancien-numerotage",
    });
    const state = revive(raw, NOW);
    expect(state.sm2).toEqual({});
    expect(state.gems).toBe(42);
    expect(state.streak).toBe(9);
    expect(state.xp).toBe(1200);
    expect(state.lang).toBe("ar");
    expect(state.introSeen).toBe(true);
  });

  it("estampe la version courante pour ne pas purger deux fois", () => {
    const first = revive(JSON.stringify({ ...makeDefaults(NOW), sm2: {} }), NOW);
    expect(first.dataVersion).toBe(makeDefaults(NOW).dataVersion);
    expect(revive(JSON.stringify(first), NOW).dataVersion).toBe(first.dataVersion);
  });

  it("reprend un store v1 contenant des cœurs sans les conserver", () => {
    // Ancien format : hearts/heartsTs écrits, nouveaux champs absents.
    const raw = JSON.stringify({
      lang: "fr",
      gems: 30,
      hearts: 2,
      heartsTs: NOW - 1_000,
      streak: 4,
      lastStudy: "2023-11-13",
      xp: 640,
      answered: 50,
      correct: 40,
      flashReviewed: 12,
      flashSuccess: 9,
      quizDone: 3,
      sm2: { 7: { rep: 2, ef: 2.4, iv: 4, due: NOW } },
      introSeen: true,
      dataVersion: "android-ids-v1",
    });
    const state = revive(raw, NOW);

    expect(state).not.toHaveProperty("hearts");
    expect(state).not.toHaveProperty("heartsTs");
    expect(JSON.stringify(state)).not.toContain("hearts");
    expect(state).not.toHaveProperty("flashSuccess");

    // la progression est intégralement conservée
    expect(state.xp).toBe(640);
    expect(state.streak).toBe(4);
    expect(state.gems).toBe(30);
    expect(Object.keys(state.sm2)).toEqual(["7"]);

    // les nouveaux champs ont leurs défauts
    const defaults = makeDefaults(NOW);
    expect(state.xpToday).toBe(0);
    expect(state.dailyGoal).toBe(defaults.dailyGoal);
    expect(state.freezeCount).toBe(0);
    expect(state.weeklyXp).toEqual({});
    expect(state.pendingChests).toBe(0);
    expect(state.chestsOpened).toBe(0);
    expect(state.moduleBest).toEqual({});
    expect(state.lessonBest).toEqual({});
    expect(state.trophies).toEqual({});
    expect(state.league).toEqual(defaults.league);
  });
});
