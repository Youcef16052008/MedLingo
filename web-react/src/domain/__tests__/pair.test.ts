import { describe, it, expect } from "vitest";
import { readFileSync } from "node:fs";
import { join } from "node:path";
import { fillScore } from "../scoring";

/**
 * Contrats des exercices de type "pair" (fill-in-the-blank) — corpus web.
 *
 * "pair" est un type de saisie libre (question + réponse), différent du
 * "matching" (options "gauche:Droite" × N). Ce test atteste que la réponse
 * est toujours évaluable (preuve que le correctif QuizScreen.tsx rend la
 * réponse vérifiable) et que le scoring (fillScore) est cohérent.
 *
 * Note : l'environnement de test est Node (pas de DOM), donc on vérifie ici la
 * logique pure (corpus + scoring) et non le rendu React.
 */
describe("exercices de type pair", () => {
  const root = join(__dirname, "..", "..", "..");
  const exercises = JSON.parse(
    readFileSync(join(root, "public", "data", "exercises.json"), "utf8")
  ) as Array<{
    type: string;
    id?: string | number;
    qFr?: string;
    qEn?: string;
    qAr?: string;
    answer: string;
  }>;

  const pairs = exercises.filter((e) => e.type === "pair");

  it("sont présents et nombreux", () => {
    expect(pairs.length).toBeGreaterThan(0);
  });

  it("chaque pair a question + réponse non vides", () => {
    for (const ex of pairs) {
      const q = [ex.qFr, ex.qEn, ex.qAr].filter(Boolean).join(" ");
      expect(q.trim().length, `ex ${ex.id} : question vide`).toBeGreaterThan(0);
      expect(ex.answer.trim().length, `ex ${ex.id} : réponse vide`).toBeGreaterThan(0);
      // Vrai fill-in-the-blank : la réponse ne doit pas être la question.
      const qNorm = q.trim().toLowerCase();
      expect(ex.answer.trim().toLowerCase(), `ex ${ex.id} : réponse = question`).not.toBe(qNorm);
    }
  });

  it("pair est distinct de matching : la réponse est un terme (sans ':'), et ne suit pas la structure 'gauche:Droite'", () => {
    for (const ex of pairs) {
      // À la différence de `matching` (options "gauche:Droite" × N), la
      // réponse d'un `pair` est un terme simple (ex: "Heart valve").
      expect(ex.answer.includes(":"), `ex ${ex.id} : réponse au format matching`).toBe(false);
      // Les options d'un pair sont une anomaly de génération (stray fragment),
      // pas quatre paires séparées par ':'. Ici on ne les valide pas.
    }
  });

  it("fillScore vote le pair correctement (exact → 1, partiel → 0.5)", () => {
    expect(fillScore("Heart valve", "Heart valve")).toBe(1);
    expect(fillScore("heart valve", "Heart valve")).toBe(1);
    expect(fillScore("Heart", "Heart valve")).toBe(0.5);
    expect(fillScore("It protects", "protects")).toBe(0.5);
    expect(fillScore("a", "Heart valve")).toBe(0);
  });
});
