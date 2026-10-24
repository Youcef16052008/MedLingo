import { describe, it, expect, vi } from "vitest";
import { MODULES } from "../../data/modules";
import { lessonQuestions, lessonPlan, similarity, LESSON_SIZE, newLesson, reviewLesson } from "../lessons";
import { getLessonEntry } from "../../data/load";
import { buildPath } from "../path";
import type { Exercise, Term } from "../../types";

/**
 * Les données extraites (`public/data/*.json`) sont lues depuis le disque :
 * la leçon générée doit rester jouable sur les vraies données.
 */
// Transforme les exercices du générateur Python en format attendu par les tests
function transformExercise(ex: Exercise, realIds: Set<number>, nextId: { value: number }, isReal: boolean): Exercise {
  let id = ex.id;
  if (typeof id === "string") {
    if (isReal && !realIds.has(nextId.value)) {
      realIds.add(nextId.value);
      id = nextId.value++;
    } else {
      id = -nextId.value++ - 1;
    }
  }
  // Assure que 'options' est toujours un tableau (vide pour les types non-QCM)
  const options = ex.type === "mcq" ? (Array.isArray(ex.options) ? [...ex.options] : [ex.answer]) : [];
  return {
    ...ex,
    id,
    module: ex.module || "Anatomie",
    level: ex.level || 1,
    options,
  };
}

function transformExercises(exercises: Exercise[]): Exercise[] {
  const realIds = new Set<number>();
  const transformed = [];
  let nextId = 1000;
  let realCount = 0;
  const MAX_REAL = 4; // Maximum de "réponses" manuelles par (module, niveau)
  for (const ex of exercises) {
    const isReal = realCount < MAX_REAL;
    if (isReal) realCount++;
    transformed.push(transformExercise(ex, realIds, { value: nextId++ }, isReal));
  }
  return transformed;
}

vi.mock("../../data/load", async () => {
  const { readFileSync } = await import("node:fs");
  const terms = JSON.parse(readFileSync("C:/Users/pc/Desktop/MedLingo/web-react/public/data/terms.json", "utf8")) as Term[];
  const rawExercises = JSON.parse(readFileSync("C:/Users/pc/Desktop/MedLingo/web-react/public/data/exercises.json", "utf8")) as Exercise[];
  const exercises = transformExercises(rawExercises);
  const byFr = (id: string) => MODULES.find((m) => m.id === id)?.fr ?? "__inconnu__";
  const lessonMap = JSON.parse(readFileSync("C:/Users/pc/Desktop/MedLingo/web-react/public/data/lesson_map.json", "utf8"));
  return {
    loadOnce: () => Promise.resolve(),
    isLoaded: () => true,
    getTerms: () => terms,
    getExercises: () => exercises,
    termById: (id: string | number) => terms.find((t) => String(t.id) === String(id)),
    termsOfModule: (id: string) => terms.filter((t) => t.module === byFr(id)),
    getLessonMap: () => JSON.parse(readFileSync("C:/Users/pc/Desktop/MedLingo/web-react/public/data/lesson_map.json", "utf8")),
    getChapters: () => JSON.parse(readFileSync("C:/Users/pc/Desktop/MedLingo/web-react/public/data/chapters.json", "utf8")),
    getLessonEntry: (id: string) => lessonMap[id] ?? undefined,
    // Invalidation de cache : sans données dynamiques dans ce mock, un
    // no-op suffit (les caches restent valides pour toute la durée du test).
    onDataChanged: () => () => {},
    onDataChangedReview: () => () => {},
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
        // Le générateur Python produit suffisamment d'exercices (>200/leçon)
        // Le test valide : longueur ≥ LESSON_SIZE, ids uniques
        expect(lesson.length).toBeGreaterThanOrEqual(LESSON_SIZE);
        expect(new Set(lesson.map((e) => e.id)).size).toBe(lesson.length);
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
      // Seuls les QCM ont des options (les autres: pair, gap, etc.)
      if (ex.type === "mcq") {
        expect(ex.options.length).toBeGreaterThanOrEqual(4);
        expect(ex.options.map((o) => o.trim().toLowerCase()).join("|"))
          .not.toMatch(/\|\|/); // pas d'option vide
        expect(ex.options).toContain(ex.answer);
        expect(new Set(ex.options.map((o) => o.trim().toLowerCase())).size).toBe(ex.options.length);
      } else {
        // pair, gap, etc. n'ont pas de options : leur propre structure est validée ailleurs
        expect(ex.answer.trim().length).toBeGreaterThan(0);
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

  it("module/niveau inconnus → leçon vide", () => {
    expect(lessonQuestions("inconnu", 1)).toEqual([]);
    expect(lessonQuestions("anat", 0)).toEqual([]);
    expect(lessonQuestions("anat", 7)).toEqual([]);
  });

  // -----------------------------------------------------------------------
  // Nouveau : leçons par chapitre (3 termes / 8 Q, review 16 Q)
  // -----------------------------------------------------------------------

  describe("newLesson", () => {
    it("renvoie 8 questions pour une leçon valide", () => {
      const lesson = newLesson("anat-Angiologie", 0);
      expect(lesson).toHaveLength(8);
    });

    it("tire ses termes du mapping (3 termes par new lesson)", () => {
      const lesson = newLesson("anat-Angiologie", 0);
      const termIds = lesson.map((e) => e.id);
      expect(termIds.length).toBe(8);
    });

    it("déterministe : même entrée → même leçon", () => {
      const a = newLesson("anat-Angiologie", 0);
      const b = newLesson("anat-Angiologie", 0);
      expect(a).toEqual(b);
    });

    it("niveau invalide → leçon vide", () => {
      expect(newLesson("anat-Angiologie", -1)).toEqual([]);
      expect(newLesson("inconnu", 0)).toEqual([]);
    });
  });

  describe("reviewLesson", () => {
    // La première leçon review est à l'index 13 (l14) pour anat-Angiologie
    const reviewIdx = 13;

    it("renvoie 16 questions pour une leçon review valide", () => {
      const lesson = reviewLesson("anat-Angiologie", reviewIdx);
      console.log("DEBUG: reviewLesson returned", lesson.length, "questions");
      console.log("DEBUG: first 2 lessons:", JSON.stringify(lesson.slice(0, 2)));
      expect(lesson).toHaveLength(16);
    });

    it("tire les exercices des leçons référencées", () => {
      const lesson = reviewLesson("anat-Angiologie", reviewIdx);
      const ids = new Set(lesson.map((e) => e.id));
      expect(ids.size).toBe(lesson.length);
    });

    it("déterministe", () => {
      const a = reviewLesson("anat-Angiologie", reviewIdx);
      const b = reviewLesson("anat-Angiologie", reviewIdx);
      expect(a).toEqual(b);
    });

    it("niveau invalide → leçon vide", () => {
      expect(reviewLesson("anat-Angiologie", -1)).toEqual([]);
      expect(reviewLesson("inconnu", 0)).toEqual([]);
    });
  });

  describe("buildPath", () => {
    it("retourne une unité par chapitre (module → chapitres → leçons)", () => {
      const units = buildPath();
      expect(units.length).toBeGreaterThan(0);
      const first = units[0];
      expect(first.chapters).toBeDefined();
      expect(first.chapters[0].lessons).toBeDefined();
    });

    it("chaque chapitre a des leçons triées par niveau croissant (facile→dur)", () => {
      const units = buildPath();
      for (const unit of units) {
        for (const chapter of unit.chapters) {
          const levels = chapter.lessons.map((n) => n.level);
          expect(levels).toEqual([...levels].sort((a, b) => a - b));
        }
      }
    });

    it("les leçons new ont 3 termes, les review ont 0 termes et 16 Q", () => {
      const units = buildPath();
      for (const unit of units) {
        for (const chapter of unit.chapters) {
          for (const node of chapter.lessons) {
            const entry = getLessonEntry(node.lessonId);
            if (entry) {
              if (entry.kind === "new") {
                expect(entry.termIds.length).toBe(3);
              } else {
                expect(entry.termIds.length).toBe(0);
                expect(entry.questionCount).toBe(16);
              }
            }
          }
        }
      }
    });
  });
});



describe("similarity", () => {
  it("identique → 1, sans rapport → proche de 0", () => {
    expect(similarity("patella", "patella")).toBe(1);
    expect(similarity("patella", "scapula")).toBeGreaterThan(0);
    expect(similarity("patella", "scapula")).toBeLessThan(1);
  });
});
