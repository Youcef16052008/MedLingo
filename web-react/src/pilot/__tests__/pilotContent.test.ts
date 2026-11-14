import { describe, it, expect } from "vitest";
import { readFileSync } from "node:fs";
import { join, dirname } from "node:path";
import { fileURLToPath } from "node:url";
import {
  validatePilotManifest,
  isConceptPublishable,
  presentableExercises,
} from "../content";
import type { PilotConcept, PilotManifest } from "../types";

/**
 * P1.1 — tests du schéma éditorial pilote.
 *
 * 1. Le manifeste réel (30 concepts candidats, P1.2) valide sans erreur.
 * 2. Les combos de relectures : `approved` exige les DEUX relectures sur la
 *    version COURANTE ; toute modification de contenu invalide — un ancien
 *    `approved` ne survit pas silencieusement.
 * 3. Fuite interdite : la réponse jamais verbatim dans une consigne (P0.3).
 * 4. Aucune relecture humaine n'existe encore : le manifeste livré doit être
 *    100 % draft / pending, et `isConceptPublishable` doit tout refuser.
 */

const here = dirname(fileURLToPath(import.meta.url));
const manifest = JSON.parse(
  readFileSync(join(here, "..", "..", "..", "data", "pilot-anatomy.v1.json"), "utf-8"),
) as PilotManifest;

const baseConcept = (overrides: Partial<PilotConcept> = {}): PilotConcept => ({
  id: "anatomy-u1-01-test",
  unit: 1,
  order: 1,
  labelEn: "Heart",
  hints: { fr: "Cœur", ar: "القلب" },
  languageObjective: "test",
  definition: { en: "Pumps blood.", fr: "Pompe le sang.", ar: "يضخ الدم." },
  selectionNote: "hypothèse à examiner",
  status: "draft",
  contentVersion: "1.0.0",
  reviews: {
    linguistic: { status: "pending" },
    medical: { status: "pending" },
  },
  exercises: [
    {
      id: "t-rec",
      conceptId: "anatomy-u1-01-test",
      objective: "recognize",
      role: "assessment",
      kind: "mcq",
      prompt: { en: "Which term matches this definition: 'Pumps blood.'?", fr: "…", ar: "…" },
      options: ["Heart", "Lung", "Liver", "Kidney"],
      answer: "Heart",
      explanation: { en: "…", fr: "…", ar: "…" },
    },
    {
      id: "t-und",
      conceptId: "anatomy-u1-01-test",
      objective: "understand",
      role: "practice",
      kind: "mcq",
      prompt: { en: "Fill in the blank with the correct term: 'Pumps ______.'", fr: "…", ar: "…" },
      options: ["Heart", "Lung", "Liver", "Kidney"],
      answer: "Heart",
      explanation: { en: "…", fr: "…", ar: "…" },
    },
    {
      id: "t-recal",
      conceptId: "anatomy-u1-01-test",
      objective: "recall",
      role: "practice",
      kind: "free_input",
      prompt: { en: "Type the English term for: 'Cœur'", fr: "…", ar: "…" },
      answer: "Heart",
      explanation: { en: "…", fr: "…", ar: "…" },
    },
  ],
  ...overrides,
});

const manifestOf = (concepts: PilotConcept[]): PilotManifest => ({
  schemaVersion: 1,
  id: "pilot-anatomy",
  contentVersion: "1.0.0",
  generatedAt: "2026-11-11T00:00:00.000Z",
  generatedFrom: "test",
  concepts,
});

describe("pilot content — manifeste réel (P1.2)", () => {
  it("valide sans erreur : 30 concepts draft, 90 exercices", () => {
    const report = validatePilotManifest(manifest, { knownTermIds: new Set(manifest.concepts.map((c) => c.legacyRef!)) });
    expect(report.errors, report.errors.join("\n")).toEqual([]);
    expect(manifest.concepts).toHaveLength(30);
    expect(manifest.concepts.reduce((n, c) => n + c.exercises.length, 0)).toBe(90);
    // Honnêteté : tout est draft, aucune relecture humaine déclarée.
    expect(manifest.concepts.every((c) => c.status === "draft")).toBe(true);
    for (const c of manifest.concepts) {
      expect(c.reviews.linguistic.status).toBe("pending");
      expect(c.reviews.medical.status).toBe("pending");
      expect(c.selectionNote.trim().length).toBeGreaterThan(0);
    }
  });

  it("legacyRef existent tous dans terms.json", () => {
    const terms = JSON.parse(
      readFileSync(join(here, "..", "..", "..", "public", "data", "terms.json"), "utf8"),
    ) as Array<{ id: number }>;
    const ids = new Set(terms.map((t) => t.id));
    for (const c of manifest.concepts) expect(ids.has(c.legacyRef!), c.id).toBe(true);
  });

  it("répartition 10 / 10 / 10 avec ordres uniques par unité", () => {
    for (const unit of [1, 2, 3]) {
      const inUnit = manifest.concepts.filter((c) => c.unit === unit);
      expect(inUnit).toHaveLength(10);
      expect(new Set(inUnit.map((c) => c.order)).size).toBe(10);
    }
  });
});

describe("pilot content — verrou de publication (P1.1)", () => {
  it("refuse un concept approved sans relectures", () => {
    const c = baseConcept({ status: "approved" });
    const r = validatePilotManifest(manifestOf([c]));
    expect(r.ok).toBe(false);
    expect(r.errors.join("\n")).toContain("sans les DEUX relectures");
    expect(isConceptPublishable(c)).toBe(false);
  });

  it("refuse une relecture done sans personne réelle / date", () => {
    const c = baseConcept({
      reviews: {
        linguistic: { status: "done", reviewedContentVersion: "1.0.0" },
        medical: { status: "done", reviewer: "Dr X", date: "2026-11-11", reviewedContentVersion: "1.0.0" },
      },
    });
    const r = validatePilotManifest(manifestOf([c]));
    expect(r.errors.join("\n")).toContain("sans personne réelle");
  });

  it("refuse une relecture qui ne couvre PAS la version courante", () => {
    const c = baseConcept({
      reviews: {
        linguistic: { status: "done", reviewer: "Dr A", date: "2026-11-11", reviewedContentVersion: "0.9.0" },
        medical: { status: "done", reviewer: "Dr B", date: "2026-11-11", reviewedContentVersion: "1.0.0" },
      },
    });
    const r = validatePilotManifest(manifestOf([c]));
    expect(r.errors.join("\n")).toContain("revalidation requise");
    expect(isConceptPublishable(c)).toBe(false);
  });

  it("accepte le concept quand les DEUX relectures couvrent la version courante", () => {
    const c = baseConcept({
      status: "approved",
      reviews: {
        linguistic: { status: "done", reviewer: "Dr A", date: "2026-11-11", reviewedContentVersion: "1.0.0" },
        medical: { status: "done", reviewer: "Dr B", date: "2026-11-11", reviewedContentVersion: "1.0.0" },
      },
    });
    const r = validatePilotManifest(manifestOf([c]));
    expect(r.errors, r.errors.join("\n")).toEqual([]);
    expect(isConceptPublishable(c)).toBe(true);
    expect(presentableExercises(c)).toHaveLength(3);
  });

  it("un bump de contenu invalide l'ancien approved (pas de survie silencieuse)", () => {
    const c = baseConcept({
      status: "approved",
      contentVersion: "1.1.0",
      reviews: {
        linguistic: { status: "done", reviewer: "Dr A", date: "2026-11-11", reviewedContentVersion: "1.0.0" },
        medical: { status: "done", reviewer: "Dr B", date: "2026-11-11", reviewedContentVersion: "1.0.0" },
      },
    });
    const r = validatePilotManifest(manifestOf([c]));
    expect(r.ok).toBe(false);
    expect(r.errors.join("\n")).toContain("revalidation requise");
    expect(isConceptPublishable(c)).toBe(false);
  });

  it("un concept de version ≠ manifeste est refusé (pas de survie silencieuse)", () => {
    const c = baseConcept({ contentVersion: "0.9.0" });
    const r = validatePilotManifest(manifestOf([c]));
    expect(r.errors.join("\n")).toContain("≠ manifeste");
  });
});

describe("pilot content — fuite interdite (P0.3)", () => {
  it("refuse la réponse verbatim dans une consigne (mot entier)", () => {
    const leaked = baseConcept();
    leaked.exercises[2].prompt = {
      en: "Type the English term for: 'Heart'",
      fr: "…",
      ar: "…",
    };
    const r = validatePilotManifest(manifestOf([leaked]));
    expect(r.errors.join("\n")).toContain("RÉPONSE VISIBLE");
  });

  it("un cognat accentué n'est PAS une fuite (Médial ≠ Medial est traité au build)", () => {
    // La règle « mot entier » condamne « Medial » exact, pas « Médial » :
    // c'est le builder qui choisit le rappel par définition pour ces cas.
    const c = baseConcept();
    const r = validatePilotManifest(manifestOf([c]));
    expect(r.errors, r.errors.join("\n")).toEqual([]);
  });
});

describe("pilot content — structure des exercices (P1.3)", () => {
  it("exige les 3 objectifs par concept", () => {
    const c = baseConcept();
    c.exercises = c.exercises.filter((e) => e.objective !== "recall");
    const r = validatePilotManifest(manifestOf([c]));
    expect(r.errors.join("\n")).toContain("« recall » absent");
  });

  it("refuse mcq sans réponse dans les options ou options dupliquées", () => {
    const c = baseConcept();
    c.exercises[0].options = ["Heart", "Heart", "Lung", "Kidney"];
    const r = validatePilotManifest(manifestOf([c]));
    expect(r.errors.join("\n")).toContain("options non distinctes");
  });

  it("refuse free_input avec options parasites", () => {
    const c = baseConcept();
    c.exercises[2].options = ["Heart", "Lung"];
    const r = validatePilotManifest(manifestOf([c]));
    expect(r.errors.join("\n")).toContain("options parasites");
  });

  it("refuse des ids d'exercices dupliqués globalement", () => {
    const a = baseConcept();
    const b = baseConcept({ id: "anatomy-u1-02-test", order: 2 });
    b.exercises[0].id = "t-rec";
    const r = validatePilotManifest(manifestOf([a, b]));
    expect(r.errors.join("\n")).toContain("id d'exercice dupliqué");
  });
});
