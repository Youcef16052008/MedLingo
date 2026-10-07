import { describe, it, expect, vi } from "vitest";
import { MODULES } from "../../data/modules";
import { lessonQuestions, lessonPlan, similarity, LESSON_SIZE } from "../lessons";
import type { Exercise, Term } from "../../types";

/**
 * Les données extraites (`public/data/*.json`) sont lues depuis le disque :
 * la leçon générée doit rester jouable sur les vraies données.
 */
vi.mock("../../data/load", async () => {
  const { readFileSync } = await import("node:fs");
  const terms = JSON.parse(readFileSync("public/data/terms.json", "utf8")) as Term[];
  const exercises = JSON.parse(readFileSync("public/data/exercises.json", "utf8")) as Exercise[];
  const byFr = (id: string) => MODULES.find((m) => m.id === id)?.fr ?? "__inconnu__";
  return {
    loadOnce: () => Promise.resolve(),
    isLoaded: () => true,
    getTerms: () => terms,
    getExercises: () => exercises,
    termById: () => undefined,
    termsOfModule: (id: string) => terms.filter((t) => t.module === byFr(id)),
  };
});

describe("générateur de leçons", () => {
  it("déterministe : même (module, niveau) → même leçon", () => {
    expect(lessonQuestions("anat", 1)).toEqual(lessonQuestions("anat", 1));
    expect(lessonQuestions("physio", 5)).toEqual(lessonQuestions("physio", 5));
  });

  // Génération des 90 leçons : calcul lourd, marge explicite face au défaut 5 s.
  it("8 questions par leçon, ids uniques", () => {
    for (const mod of MODULES) {
      for (let level = 1; level <= 6; level++) {
        const lesson = lessonQuestions(mod.id, level);
        expect(lesson, `${mod.id} L${level}`).toHaveLength(LESSON_SIZE);
        expect(new Set(lesson.map((e) => e.id)).size).toBe(LESSON_SIZE);
      }
    }
  }, 30_000);

  it("les 90 leçons du parcours sont générables", () => {
    let count = 0;
    for (const mod of MODULES) {
      for (let level = 1; level <= 6; level++) {
        if (lessonQuestions(mod.id, level).length === LESSON_SIZE) count += 1;
      }
    }
    expect(count).toBe(MODULES.length * 6);
  }, 30_000);

  it("tout (réponses, distracteurs) vient du module", () => {
    for (const ex of lessonQuestions("anat", 3)) {
      expect(ex.module).toBe("Anatomie");
      expect(ex.options.length).toBeGreaterThanOrEqual(4);
      expect(ex.options.map((o) => o.trim().toLowerCase()).join("|"))
        .not.toMatch(/\|\|/); // pas d'option vide
      if (ex.type === "mcq" || ex.type === "reading" || ex.type === "clinical_case") {
        expect(ex.options).toContain(ex.answer);
        expect(new Set(ex.options.map((o) => o.trim().toLowerCase())).size).toBe(ex.options.length);
      }
    }
  });

  it("aucune option en double dans les QCM", () => {
    for (const moduleId of MODULES.map((m) => m.id)) {
      for (const ex of lessonQuestions(moduleId, 1)) {
        const norms = ex.options.map((o) => o.trim().toLowerCase());
        expect(new Set(norms).size).toBe(norms.length);
      }
    }
  });

  it("les exercices réels sont réutilisés en priorité (max 4)", () => {
    const lesson = lessonQuestions("anat", 1);
    const real = lesson.filter((e) => e.id > 0);
    expect(real.length).toBeGreaterThan(0);
    expect(real.length).toBeLessThanOrEqual(4);
    expect(real.every((e) => e.module === "Anatomie" && e.level === 1)).toBe(true);
  });

  it("les kinds du plan respectent le niveau", () => {
    expect(lessonPlan(1, 4)).toEqual(["en_fr", "en_ar", "fr_en", "en_fr"]);
    expect(lessonPlan(6, 4)).toEqual(["clinical", "fr_en", "clinical", "fr_en"]);
    expect(lessonPlan(4, 3)).toEqual(["def", "match", "etym"]);
  });

  it("difficulté croissante : distracteurs proches en fin de leçon", () => {
    const lesson = lessonQuestions("biochim", 2);
    // Seules les questions générées appliquent la rampe de difficulté
    // (les exercices réels en tête de leçon sont fixes).
    const generated = lesson.filter((e) => e.id < 0 && e.type === "mcq");
    expect(generated.length).toBeGreaterThanOrEqual(4);

    const distractorSim = (ex: Exercise): number => {
      const distract = ex.options.filter((o) => o !== ex.answer);
      return Math.max(...distract.map((o) => similarity(ex.answer, o)));
    };
    const mean = (exs: Exercise[]) =>
      exs.reduce((s, e) => s + distractorSim(e), 0) / exs.length;

    const half = Math.floor(generated.length / 2);
    const early = mean(generated.slice(0, half));
    const late = mean(generated.slice(generated.length - half));
    // fin de leçon : distracteurs nettement plus proches de la réponse attendue
    expect(late).toBeGreaterThan(early);
  });

  it("module/niveau inconnus → leçon vide", () => {
    expect(lessonQuestions("inconnu", 1)).toEqual([]);
    expect(lessonQuestions("anat", 0)).toEqual([]);
    expect(lessonQuestions("anat", 7)).toEqual([]);
  });

  it("XP générés selon la table Android : L1..L6 → 10/15/20/25/30/35", () => {
    for (const moduleId of MODULES.map((m) => m.id)) {
      for (let level = 1; level <= 6; level++) {
        const expected = 10 + (level - 1) * 5;
        const generated = lessonQuestions(moduleId, level).filter((e) => e.id < 0);
        expect(generated.length, `${moduleId} L${level}`).toBeGreaterThan(0);
        for (const ex of generated) expect(ex.points).toBe(expected);
      }
    }
  }, 30_000);
});

describe("similarity", () => {
  it("identique → 1, sans rapport → proche de 0", () => {
    expect(similarity("patella", "patella")).toBe(1);
    expect(similarity("patella", "scapula")).toBeGreaterThan(0);
    expect(similarity("patella", "scapula")).toBeLessThan(1);
  });
});
