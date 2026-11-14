import { describe, it, expect, beforeEach } from "vitest";
import { moduleMastery, masteryOverview, recordModuleQuizBest, weakestTerms, weakChapters } from "../mastery";
import type { Sched } from "../sm2";
import { update, getState, resetProgress } from "../store";

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
    const m = moduleMastery(
      { moduleBest: {}, moduleId: "anat", sm2: {}, termIds: [1, 2, 3], dailyGoal: 20 },
      NOW
    );
    expect(m.force).toBe(0);
    expect(m.unseen).toBe(3);
    expect(m.due).toBe(0);
  });

  it("quiz seul (aucune carte) → force = quizBest du module", () => {
    // P0.2 : la clé est l'id du module, plus de clé globale `quiz`.
    const m = moduleMastery(
      { moduleBest: { anat: 80 }, moduleId: "anat", sm2: {}, termIds: [], dailyGoal: 20 },
      NOW
    );
    expect(m.force).toBe(80);
  });

  it("module absent de moduleBest → quizBest 0, force intacte", () => {
    const m = moduleMastery(
      { moduleBest: { physio: 50 }, moduleId: "anat", sm2: {}, termIds: [], dailyGoal: 20 },
      NOW
    );
    expect(m.quizBest).toBe(0);
    expect(m.force).toBe(0);
  });

  it("valeurs invalides bornées : >100 → 100, négatif → 0, NaN → 0", () => {
    const above = moduleMastery(
      { moduleBest: { anat: 150 }, moduleId: "anat", sm2: {}, termIds: [], dailyGoal: 20 },
      NOW
    );
    const below = moduleMastery(
      { moduleBest: { anat: -20 }, moduleId: "anat", sm2: {}, termIds: [], dailyGoal: 20 },
      NOW
    );
    const corrupt = moduleMastery(
      { moduleBest: { anat: Number.NaN }, moduleId: "anat", sm2: {}, termIds: [], dailyGoal: 20 },
      NOW
    );
    expect(above.quizBest).toBe(100);
    expect(below.quizBest).toBe(0);
    expect(corrupt.quizBest).toBe(0);
  });

  it("P0.2 : anat=80 et physio=20 → deux contributions quiz distinctes", () => {
    const moduleBest = { anat: 80, physio: 20 };
    const anat = moduleMastery({ moduleBest, moduleId: "anat", sm2: {}, termIds: [], dailyGoal: 20 }, NOW);
    const physio = moduleMastery({ moduleBest, moduleId: "physio", sm2: {}, termIds: [], dailyGoal: 20 }, NOW);
    expect(anat.quizBest).toBe(80);
    expect(physio.quizBest).toBe(20);
    // Réussir l'un ne modifie pas l'autre.
    const moduleBestAfter = { anat: 100, physio: 20 };
    const physioAfter = moduleMastery(
      { moduleBest: moduleBestAfter, moduleId: "physio", sm2: {}, termIds: [], dailyGoal: 20 },
      NOW
    );
    expect(physioAfter.quizBest).toBe(20);
  });

  it("cartes saines → force plus haute qu'avec des cartes en retard", () => {
    const healthy = moduleMastery(
      { moduleBest: { anat: 70 }, moduleId: "anat", sm2: { "1": card(), "2": card() }, termIds: [1, 2], dailyGoal: 20 },
      NOW
    );
    const overdue = moduleMastery(
      {
        moduleBest: { anat: 70 },
        moduleId: "anat",
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
        moduleBest: { anat: 100 },
        moduleId: "anat",
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

describe("masteryOverview", () => {
  it("P0.2 : lit le meilleur score de CHAQUE module, pas une clé globale", () => {
    const overview = masteryOverview(
      { anat: 80, physio: 20 },
      {},
      [
        { id: "anat", termIds: [] },
        { id: "physio", termIds: [] },
      ],
      NOW
    );
    const anat = overview.find((m) => m.id === "anat");
    const physio = overview.find((m) => m.id === "physio");
    expect(anat?.quizBest).toBe(80);
    expect(physio?.quizBest).toBe(20);
    expect(anat?.force).toBe(80);
    expect(physio?.force).toBe(20);
  });
});

describe("recordModuleQuizBest (écriture unique du quiz)", () => {
  it("garde le meilleur score existant (max), n'écrase pas par un score plus faible", () => {
    const draft: { moduleBest: Record<string, number> } = { moduleBest: { anat: 80 } };
    recordModuleQuizBest(draft, "anat", 45);
    expect(draft.moduleBest.anat).toBe(80);
    recordModuleQuizBest(draft, "anat", 90);
    expect(draft.moduleBest.anat).toBe(90);
    recordModuleQuizBest(draft, "physio", 20);
    expect(draft.moduleBest.physio).toBe(20);
    expect(draft.moduleBest.anat).toBe(90);
  });
});

describe("intégration P0.2 : fin de quiz (store) → calcul affiché", () => {
  beforeEach(() => {
    resetProgress(NOW);
  });

  it("une fin de quiz sur anat alimente le calcul sans toucher physio", () => {
    // État initial : physio a déjà un best de 20 (ancienne session).
    update((draft) => {
      recordModuleQuizBest(draft, "physio", 20);
    });

    // Fin de session quiz — même chemin d'écriture que QuizScreen.tsx
    // (update + recordModuleQuizBest), PAS un dictionnaire artificiel.
    update((draft) => {
      draft.quizDone += 1;
      recordModuleQuizBest(draft, "anat", 80);
    });

    const s = getState();
    const termsByModule = [
      { id: "anat", termIds: [] },
      { id: "physio", termIds: [] },
    ];
    const overview = masteryOverview(s.moduleBest, s.sm2 as Record<string, Sched>, termsByModule, NOW);
    const anat = overview.find((m) => m.id === "anat");
    const physio = overview.find((m) => m.id === "physio");
    expect(anat?.quizBest).toBe(80);
    expect(physio?.quizBest).toBe(20);
    // Le second quiz anat plus faible ne dégrade pas le best conservé.
    update((draft) => {
      recordModuleQuizBest(draft, "anat", 50);
    });
    const anatAfter = masteryOverview(
      getState().moduleBest,
      {},
      termsByModule,
      NOW
    ).find((m) => m.id === "anat");
    expect(anatAfter?.quizBest).toBe(80);
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
