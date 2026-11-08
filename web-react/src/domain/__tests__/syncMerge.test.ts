import { describe, it, expect } from "vitest";
import {
  mergeSyncBlobs,
  mergeSched,
  toSyncBlob,
  applySyncBlob,
  type SyncBlob,
} from "../syncMerge";
import { makeDefaults } from "../store";
import type { Sched } from "../sm2";

function blob(over: Partial<SyncBlob> = {}): SyncBlob {
  return { ...toSyncBlob(makeDefaults(0)), ...over };
}

describe("syncMerge — fusion carte par carte (R-07/R-08)", () => {
  it("merge est symétrique : merge(a,b) === merge(b,a)", () => {
    const a = blob({
      xp: 120,
      sm2: { "1": { rep: 3, ef: 2.5, iv: 3, due: 1000 } },
      lessonBest: { L1: 80 },
      trophies: { t1: 500 },
    });
    const b = blob({
      xp: 90,
      sm2: { "1": { rep: 5, ef: 2.6, iv: 6, due: 2000 }, "2": { rep: 1, ef: 2.5, iv: 1, due: 0 } },
      lessonBest: { L1: 95, L2: 60 },
      trophies: { t1: 300, t2: 700 },
    });
    expect(mergeSyncBlobs(a, b)).toEqual(mergeSyncBlobs(b, a));
  });

  it("aucune régression de carte SM-2 lors d'une édition à deux appareils", () => {
    // Appareil A a avancé la carte 1 ; appareil B a avancé la carte 2.
    const local = blob({ sm2: { "1": { rep: 7, ef: 2.5, iv: 14, due: 5000 } } });
    const remote = blob({
      sm2: {
        "1": { rep: 2, ef: 2.5, iv: 2, due: 1000 },
        "2": { rep: 4, ef: 2.5, iv: 5, due: 3000 },
      },
    });
    const merged = mergeSyncBlobs(local, remote);
    expect(merged.sm2["1"].rep).toBe(7); // progrès local préservé
    expect(merged.sm2["2"].rep).toBe(4); // progrès distant récupéré
  });

  it("à rep égal, due le plus ancien gagne (revoir plus tôt)", () => {
    const a: Sched = { rep: 3, ef: 2.5, iv: 3, due: 9000 };
    const b: Sched = { rep: 3, ef: 2.5, iv: 3, due: 1000 };
    expect(mergeSched(a, b).due).toBe(1000);
    expect(mergeSched(b, a).due).toBe(1000);
  });

  it("les compteurs ne régressent jamais (max)", () => {
    const local = blob({ xp: 500, answered: 100, correct: 80, gems: 40 });
    const remote = blob({ xp: 300, answered: 120, correct: 70, gems: 60 });
    const merged = mergeSyncBlobs(local, remote);
    expect(merged.xp).toBe(500);
    expect(merged.answered).toBe(120);
    expect(merged.correct).toBe(80);
    expect(merged.gems).toBe(60);
  });

  it("les meilleurs scores par leçon/module prennent le max des deux côtés", () => {
    const local = blob({ lessonBest: { A: 60 }, moduleBest: { M1: 75 } });
    const remote = blob({ lessonBest: { A: 90, B: 50 }, moduleBest: { M1: 40, M2: 80 } });
    const merged = mergeSyncBlobs(local, remote);
    expect(merged.lessonBest).toEqual({ A: 90, B: 50 });
    expect(merged.moduleBest).toEqual({ M1: 75, M2: 80 });
  });

  it("un trophée débloqué des deux côtés garde le timestamp le plus ancien", () => {
    const local = blob({ trophies: { first_quiz: 999 } });
    const remote = blob({ trophies: { first_quiz: 111 } });
    expect(mergeSyncBlobs(local, remote).trophies.first_quiz).toBe(111);
  });

  it("la file de révision fait l'union des deux appareils", () => {
    const local = blob({
      reviewQueue: [{ lessonId: "L1", exIds: [1, 2], chapterId: "c", moduleId: "m", status: "unlocked", missedAt: 1 }],
    });
    const remote = blob({
      reviewQueue: [
        { lessonId: "L1", exIds: [1, 2, 3], chapterId: "c", moduleId: "m", status: "played", missedAt: 2 },
        { lessonId: "L2", exIds: [9], chapterId: "c", moduleId: "m", status: "unlocked", missedAt: 3 },
      ],
    });
    const merged = mergeSyncBlobs(local, remote);
    expect(merged.reviewQueue).toHaveLength(2);
    expect(merged.reviewQueue.find((q) => q.lessonId === "L1")!.exIds).toHaveLength(3);
  });

  it("une histoire terminée l'emporte sur une session en cours", () => {
    const local = blob({
      storyProgress: {
        s1: {
          storyId: "s1", contentVersion: 1, step: "LISTEN_DISCOVER", qIndex: 0, drillIndex: 0, reviewIndex: 0,
          stats: { asked: 0, correct: 0 } as any, reviewRatings: {}, retellAttempted: false,
          transferTaskAttempted: false, transferTaskPending: false, savedAt: 5000,
          completedAt: 0, rewardClaimed: false,
        },
      },
    });
    const remote = blob({
      storyProgress: {
        s1: {
          storyId: "s1", contentVersion: 1, step: "TRANSFER_AND_SUMMARY", qIndex: 5, drillIndex: 2, reviewIndex: 1,
          stats: { asked: 5, correct: 4 } as any, reviewRatings: {}, retellAttempted: true,
          transferTaskAttempted: true, transferTaskPending: false, savedAt: 1000,
          completedAt: 4000, rewardClaimed: true,
        },
      },
    });
    const merged = mergeSyncBlobs(local, remote);
    expect(merged.storyProgress.s1.completedAt).toBe(4000);
    expect(merged.storyProgress.s1.rewardClaimed).toBe(true);
  });

  it("un blob distant d'ancienne version (champs manquants) ne casse pas la fusion", () => {
    const local = blob({ xp: 100, sm2: { "1": { rep: 2, ef: 2.5, iv: 2, due: 0 } } });
    const merged = mergeSyncBlobs(local, {} as Partial<SyncBlob>);
    expect(merged.xp).toBe(100);
    expect(merged.sm2["1"].rep).toBe(2);
  });

  it("toSyncBlob → applySyncBlob est un round-trip fidèle du sous-ensemble sync", () => {
    const s = makeDefaults(1000);
    s.xp = 777;
    s.sm2 = { "42": { rep: 6, ef: 2.7, iv: 12, due: 8000 } };
    s.lessonBest = { L9: 88 };
    const blobOut = toSyncBlob(s);
    const target = makeDefaults(0);
    applySyncBlob(target, blobOut);
    expect(target.xp).toBe(777);
    expect(target.sm2["42"].rep).toBe(6);
    expect(target.lessonBest.L9).toBe(88);
    // les préférences device ne sont pas touchées
    expect(target.lang).toBe(makeDefaults(0).lang);
  });
});
