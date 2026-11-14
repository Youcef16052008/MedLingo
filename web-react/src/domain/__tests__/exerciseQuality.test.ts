import { describe, it, expect } from "vitest";
import { auditExercises } from "../../../scripts/exercise-quality.mjs";

/** Alias de lisibilité (le module .mjs est typé par exercise-quality.d.mts). */
const audit = auditExercises;

/**
 * P0.3 — tests du contrôle de qualité des exercices.
 *
 * Fixtures d'abord (règles unitaires), puis corpus réel :
 *  - le cas de fuite réel historique « Complete : 'def' → Ventricle » DOIT
 *    être détecté comme erreur certaine ;
 *  - un cognat légitime (FR « Cardiaque » → EN « Cardiac ») NE doit PAS être
 *    rejeté — une sous-chaîne commune ou un cognat ne suffit pas à condamner.
 */

const base = { level: 1, module: "Anatomie", chapter: "Angiologie", points: 10 };

describe("exercise-quality — règles certaines (fixtures)", () => {
  it("détecte la fuite réelle « … → Ventricle » (réponse après flèche)", () => {
    const leaked = {
      ...base,
      id: "Anatomie-Angiologie-l1-7",
      type: "gap",
      qEn: "Complete : 'Lower chamber of the heart that pumps blood out of the heart.' → Ventricle",
      qFr: "Complétez : 'Lower chamber of the heart that pumps blood out of the heart.' → Ventricule",
      qAr: "أكمل : 'Lower chamber of the heart that pumps blood out of the heart.' → البطين",
      answer: "Ventricle",
    };
    const r = audit([leaked]);
    expect(r.ok).toBe(false);
    const rules = r.certain.map((c) => c.rule);
    expect(rules).toContain("answer_after_arrow");
    // La fuite est détectée sur les trois langues.
    expect(r.certain.filter((c) => c.rule === "answer_after_arrow")).toHaveLength(3);
  });

  it("détecte une réponse copiée verbatim dans la question (fallback pair)", () => {
    const leaked = {
      ...base,
      id: "Biochimie-pH-l2-2844",
      type: "pair",
      qEn: "What is the medical term for : 'pH' ?",
      answer: "pH",
    };
    const r = audit([leaked]);
    expect(r.certain.some((c) => c.rule === "answer_copied_in_question")).toBe(true);
  });

  it("un cognat légitime n'est PAS rejeté (Cardiaque → Cardiac)", () => {
    const cognate = {
      ...base,
      id: "Anatomie-Cardio-l1-1",
      type: "mcq",
      qEn: "What is the medical English term for 'Cardiaque'?",
      qFr: "Quel est le terme médical anglais de 'Cardiaque' ?",
      qAr: "ما هو المصطلح الطبي الإنجليزي لـ 'Cardiaque'؟",
      options: ["Cardiac", "Pulmonary", "Renal", "Hepatic"],
      answer: "Cardiac",
    };
    const r = audit([cognate]);
    expect(r.certain).toEqual([]);
    // Le mot FR cité dans la question ressemble à la réponse — c'est
    // précisément ce qu'on NE condamne pas.
    expect(r.suspicious.filter((s) => s.rule === "answer_copied_in_question")).toHaveLength(0);
  });

  it("détecte un blanc annoncé mais absent", () => {
    const noBlank = {
      ...base,
      id: "Anatomie-Cardio-l1-2",
      type: "gap",
      qEn: "Fill in the blank: 'The heart pumps blood.'",
      qFr: "Complétez la phrase : 'Le cœur pompe le sang.'",
      qAr: "أكمل الفراغ: 'القلب يضخ الدم.'",
      answer: "heart",
    };
    const r = audit([noBlank]);
    expect(r.certain.some((c) => c.rule === "blank_missing")).toBe(true);
  });

  it("détecte des options invalides : réponse absente / doublon", () => {
    const noAnswer = {
      ...base,
      id: "m1-l1-1",
      type: "mcq",
      qEn: "Q", options: ["A", "B", "C", "D"], answer: "X",
    };
    const dup = {
      ...base,
      id: "m1-l1-2",
      type: "mcq",
      qEn: "Q", options: ["Heart", "heart", "Aorta", "Vein"], answer: "Heart",
    };
    const r = audit([noAnswer, dup]);
    expect(r.certain.filter((c) => c.rule === "invalid_options").length).toBeGreaterThanOrEqual(2);
  });

  it("détecte une réponse vide", () => {
    const r = audit([{ ...base, id: "m1-l1-3", type: "pair", qEn: "Q ?", answer: "" }]);
    expect(r.certain.some((c) => c.rule === "empty_answer")).toBe(true);
  });

  it("détecte une « définition » égale à la réponse", () => {
    const r = audit([
      { ...base, id: "m1-l1-4", type: "pair", qEn: "What is the medical term for : 'pH' ?", answer: "pH" },
    ]);
    expect(r.certain.some((c) => c.rule === "definition_equals_answer")).toBe(true);
  });

  it("respecte les contrats matching et sentence_order (pas de faux positif)", () => {
    const matching = {
      ...base,
      id: "m1-l1-5",
      type: "matching",
      qEn: "Match each term with its French translation.",
      options: ["Heart:Cœur", "Lung:Poumon", "Liver:Foie", "Kidney:Rein"],
      answer: "Heart:Cœur|Lung:Poumon|Liver:Foie|Kidney:Rein",
    };
    const order = {
      ...base,
      id: "m1-l1-6",
      type: "sentence_order",
      qEn: "Arrange the words.",
      options: ["the", "heart", "pumps", "blood"],
      answer: "the heart pumps blood",
    };
    const r = audit([matching, order]);
    expect(r.certain).toEqual([]);
  });

  it("un QCM dont la définition contient la réponse est une fuite, pas une suspicion", () => {
    const leaked = {
      ...base,
      id: "m1-l1-7",
      type: "mcq",
      qEn: "Which term is defined as: pH is the negative logarithm of hydrogen ion concentration ?",
      options: ["pH", "pOH", "pKa", "Ka"],
      answer: "pH",
    };
    const r = audit([leaked]);
    expect(r.certain.some((c) => c.rule === "answer_copied_in_question")).toBe(true);
  });
});

describe("exercise-quality — corpus réel (exercises.json)", () => {
  it("aucune erreur certaine sur le corpus régénéré", async () => {
    const { readFileSync } = await import("node:fs");
    const { join, dirname } = await import("node:path");
    const { fileURLToPath } = await import("node:url");
    const here = dirname(fileURLToPath(import.meta.url));
    const exercises = JSON.parse(
      readFileSync(join(here, "..", "..", "..", "public", "data", "exercises.json"), "utf-8")
    );
    const r = audit(exercises);
    expect(r.certain, JSON.stringify(r.certain.slice(0, 10), null, 2)).toEqual([]);
    expect(r.total).toBeGreaterThan(0);
  });
});
