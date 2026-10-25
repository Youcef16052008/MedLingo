import { describe, it, expect } from "vitest";

/**
 * WS2.1 — la boucle de révision LOT2 bout-en-bout (store seul, sans écran) :
 * leçon de chapitre → ratés → fin de leçon → file de révision → jouée.
 * Garantit aussi le contrat mixte number|string des ids (corpus v2).
 */
describe("boucle de révision (startLesson → endLesson → queue → played)", () => {
  it("une leçon ratée alimente la file puis la sort une fois rejouée", async () => {
    const m = await import("../store");
    m.resetProgress(Date.now());
    m.startLesson("anat-Angiologie-l1", "anat", "anat-Angiologie");
    const st = m.getState();
    expect(st.lessonProgress["anat-Angiologie-l1"]).toMatchObject({ score: 0, completedAt: 0 });

    // Ratés avec ids TEXTUELS du corpus v2 (« Anatomie-Angiologie-l1-7 »).
    const outcome = m.endLesson("anat-Angiologie-l1", 55, ["Anatomie-Angiologie-l1-7", 42]);
    expect(outcome).toBe("choice"); // des ratés → choix (revoir)
    expect(m.getState().lessonProgress["anat-Angiologie-l1"]).toMatchObject({ score: 55 });

    m.addToReviewQueue({
      lessonId: "anat-Angiologie-l1",
      exIds: ["Anatomie-Angiologie-l1-7", 42],
      chapterId: "anat-Angiologie",
      moduleId: "anat",
    });
    const queue = m.getState().reviewQueue;
    expect(queue).toHaveLength(1);
    expect(queue[0]).toMatchObject({ status: "unlocked", moduleId: "anat" });
    expect([...queue[0].exIds]).toEqual(["Anatomie-Angiologie-l1-7", 42]);

    m.markReviewPlayed("anat-Angiologie-l1");
    expect(m.getState().reviewQueue[0].status).toBe("played");
  });

  it("une leçon sans raté ne demande pas de relecture", async () => {
    const m = await import("../store");
    m.resetProgress(Date.now());
    m.startLesson("physio-L1", "physio", "physio-L1");
    expect(m.endLesson("physio-L1", 100, [])).toBe("continue");
  });
});
