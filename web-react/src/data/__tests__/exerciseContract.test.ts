import { describe, it, expect } from "vitest";
import { readFileSync } from "node:fs";
import { join, dirname } from "node:path";
import { fileURLToPath } from "node:url";
import type { Exercise } from "../../types";
import { RENDERABLE_EXERCISE_TYPES } from "../../types";

const __dirname = dirname(fileURLToPath(import.meta.url));

/**
 * BUG-003 (régression) — contrat corpus ↔ UI.
 *
 * Le corpus régénéré (13 000 exercices) introduisait le type "gap" absent de
 * `ExerciseType` et de tout renderer de QuizScreen : question vide, bouton
 * Vérifier désactivé à vie, 67/90 leçons touchées dont la 1ʳᵉ d'un nouvel
 * utilisateur. Ce contrat garantit que CHAQUE type servi est rendable —
 * indépendamment de la version du fichier (112 committée ou 13k régénérée).
 */
const exercises = JSON.parse(
  readFileSync(join(__dirname, "..", "..", "..", "public", "data", "exercises.json"), "utf-8")
) as Exercise[];

const renderable = new Set<string>(RENDERABLE_EXERCISE_TYPES);

describe("contrat corpus d'exercices ↔ renderers QuizScreen", () => {
  it("le corpus n'est pas vide et ses ids sont uniques", () => {
    expect(exercises.length).toBeGreaterThan(0);
    const ids = exercises.map((e) => String(e.id));
    expect(new Set(ids).size, "ids dupliqués").toBe(ids.length);
  });

  it("chaque type servi est rendable par l'UI (BUG-003)", () => {
    const served = new Set(exercises.map((e) => e.type));
    const unrenderable = [...served].filter((t) => !renderable.has(t));
    expect(
      unrenderable,
      `types sans renderer : ${unrenderable.join(", ")} — implémenter le renderer ou changer le générateur`
    ).toEqual([]);
  });

  it("les types à saisie libre ont une réponse ; les types à options ont des options", () => {
    const textInput = new Set(["fill_blank", "gap", "pair"]);
    const withOptions = new Set(["mcq", "matching", "sentence_order"]);
    for (const ex of exercises) {
      if (textInput.has(ex.type)) {
        expect(String(ex.answer ?? "").trim(), `${ex.id} (${ex.type})`).not.toBe("");
      }
      if (withOptions.has(ex.type)) {
        expect(Array.isArray(ex.options) && ex.options.length > 0, `${ex.id} (${ex.type})`).toBe(true);
      }
    }
  });

  it("chaque exercice porte un intitulé dans au moins une langue", () => {
    for (const ex of exercises) {
      const hasAsk = [ex.qEn, ex.qFr, ex.qAr].some((q) => String(q ?? "").trim().length > 0);
      expect(hasAsk, `${ex.id} (${ex.type})`).toBe(true);
    }
  });
});
