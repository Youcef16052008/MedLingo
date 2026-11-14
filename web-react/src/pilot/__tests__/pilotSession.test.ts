import { describe, it, expect } from "vitest";
import {
  advance,
  evaluateAnswer,
  initSession,
  recordAnswer,
  resumeSession,
  totalSteps,
  currentStepNumber,
  useHelp,
} from "../session";
import type { PilotManifest } from "../types";

/**
 * P2.2 — moteur de séance pur : horloge injectée, idempotence (double clic /
 * double soumission), séparation premier essai / avec aide / erreur, reprise
 * validée contre le manifeste, invalidation au changement de version.
 */

const concept = (id: string, exIds: string[]): any => ({
  id,
  unit: 1,
  order: 1,
  labelEn: id,
  hints: { fr: "aide", ar: "مساعدة" },
  languageObjective: "test",
  definition: { en: "d", fr: "d", ar: "d" },
  selectionNote: "n",
  status: "draft",
  contentVersion: "1.0.0",
  reviews: { linguistic: { status: "pending" }, medical: { status: "pending" } },
  exercises: exIds.map((eid, k) => ({
    id: eid,
    conceptId: id,
    objective: (["recognize", "understand", "recall"] as const)[k % 3],
    role: k === 0 ? "assessment" : "practice",
    kind: k === 2 ? "free_input" : "mcq",
    prompt: { en: "p", fr: "p", ar: "p" },
    options: k === 2 ? undefined : ["Heart", "Lung", "Liver", "Kidney"],
    answer: "Heart",
    acceptedVariants: k === 2 ? ["heart"] : undefined,
    explanation: { en: "e", fr: "e", ar: "e" },
  })),
});

const manifest: PilotManifest = {
  schemaVersion: 1,
  id: "pilot-anatomy",
  contentVersion: "1.0.0",
  generatedAt: "2026-11-11T00:00:00.000Z",
  generatedFrom: "test",
  concepts: [concept("c1", ["c1-a", "c1-b", "c1-c"]), concept("c2", ["c2-a", "c2-b", "c2-c"])],
};

const T0 = 1_000_000;
const T1 = T0 + 5_000;

describe("session — initialisation et progression", () => {
  it("ouvre une séance limitée à 3 concepts, étapes comptées", () => {
    const s = initSession(manifest.concepts, "1.0.0", T0);
    expect(s.conceptIds).toEqual(["c1", "c2"]);
    expect(s.finished).toBe(false);
    expect(totalSteps(s, manifest)).toBe(6);
    expect(currentStepNumber(s, manifest)).toBe(1);
  });

  it("avance exercice → exercice → concept → fin, après réponse", () => {
    let s = initSession(manifest.concepts, "1.0.0", T0);
    // advance sans réponse : no-op (garde answeredExerciseId)
    expect(advance(s, manifest, T1)).toBe(s);
    s = recordAnswer(s, manifest.concepts[0].exercises[0], "Heart", T1);
    s = advance(s, manifest, T1);
    expect(currentStepNumber(s, manifest)).toBe(2);
    s = recordAnswer(s, manifest.concepts[0].exercises[1], "Lung", T1);
    s = advance(s, manifest, T1);
    s = recordAnswer(s, manifest.concepts[0].exercises[2], "Heart", T1);
    s = advance(s, manifest, T1);
    expect(s.i).toBe(1);
    expect(s.exIdx).toBe(0);
    s = recordAnswer(s, manifest.concepts[1].exercises[0], "Heart", T1);
    s = advance(s, manifest, T1);
    s = recordAnswer(s, manifest.concepts[1].exercises[1], "Heart", T1);
    s = advance(s, manifest, T1);
    s = recordAnswer(s, manifest.concepts[1].exercises[2], "heart", T1);
    s = advance(s, manifest, T1);
    expect(s.finished).toBe(true);
  });
});

describe("session — verdicts (P1.3)", () => {
  it("premier essai correct sans aide → first_try (autonome)", () => {
    let s = initSession(manifest.concepts, "1.0.0", T0);
    s = recordAnswer(s, manifest.concepts[0].exercises[0], "Heart", T1);
    expect(s.firstResults["c1-a"]).toBe("first_try");
    expect(s.autonomous).toContain("c1-a");
    expect(s.errors).not.toContain("c1-a");
  });

  it("aide consultée → JAMAIS first_try (l'aide ne fabrique pas de réussite autonome)", () => {
    let s = initSession(manifest.concepts, "1.0.0", T0);
    s = useHelp(s, "c1-a", T1);
    s = recordAnswer(s, manifest.concepts[0].exercises[0], "Heart", T1);
    expect(s.firstResults["c1-a"]).toBe("helped");
    expect(s.autonomous).not.toContain("c1-a");
    expect(s.helped).toContain("c1-a");
  });

  it("erreur → error ; la variante acceptée passe en saisie libre", () => {
    let s = initSession(manifest.concepts, "1.0.0", T0);
    s = recordAnswer(s, manifest.concepts[0].exercises[0], "Lung", T1);
    expect(s.firstResults["c1-a"]).toBe("error");
    expect(s.errors).toContain("c1-a");
    // acceptedVariants ("heart") acceptée — casing normalisé via fillScore.
    expect(evaluateAnswer(manifest.concepts[0].exercises[2], " heart ")).toBe(true);
  });

  it("double soumission = même état (idempotent)", () => {
    let s = initSession(manifest.concepts, "1.0.0", T0);
    s = recordAnswer(s, manifest.concepts[0].exercises[0], "Lung", T1);
    const afterFirst = recordAnswer(s, manifest.concepts[0].exercises[0], "Heart", T1 + 1);
    expect(afterFirst).toBe(s); // pas d'avancement sans advance()
    expect((s.attempts["c1-a"] ?? 0)).toBe(1);
  });

  it("le nombre de tentatives cumule les essais ratés", () => {
    let s = initSession(manifest.concepts, "1.0.0", T0);
    s = recordAnswer(s, manifest.concepts[0].exercises[0], "Lung", T1);
    s = advance(s, manifest, T1);
    s = recordAnswer(s, manifest.concepts[0].exercises[1], "Heart", T1);
    s = advance(s, manifest, T1);
    s = recordAnswer(s, manifest.concepts[0].exercises[2], "wrong", T1);
    s = advance(s, manifest, T1);
    // Les tentatives par exercice restent visibles dans attempts.
    expect(s.attempts["c1-a"]).toBe(1);
    expect(s.attempts["c1-c"]).toBe(1);
  });
});

describe("session — reprise (P2.3)", () => {
  it("reprend une séance en cours sur le même manifeste", () => {
    const s0 = initSession(manifest.concepts, "1.0.0", T0);
    const s1 = recordAnswer(s0, manifest.concepts[0].exercises[0], "Heart", T1);
    const resumed = resumeSession(JSON.parse(JSON.stringify(s1)), manifest, T1 + 9);
    expect(resumed).not.toBeNull();
    expect(resumed!.id).toBe(s1.id);
    expect(resumed!.updatedAt).toBe(T1 + 9);
  });

  it(" invalide la séance au changement de contentVersion (jamais un silence)", () => {
    const s0 = initSession(manifest.concepts, "1.0.0", T0);
    const changed: PilotManifest = { ...manifest, contentVersion: "2.0.0" };
    expect(resumeSession(s0, changed, T1)).toBeNull();
  });

  it("invalide la séance si un concept a disparu du manifeste", () => {
    const s0 = initSession(manifest.concepts, "1.0.0", T0);
    const trimmed: PilotManifest = { ...manifest, concepts: [manifest.concepts[0]] };
    expect(resumeSession(s0, trimmed, T1)).toBeNull();
  });

  it("invalide une séance de schéma inconnu ou finie", () => {
    const s0 = { ...initSession(manifest.concepts, "1.0.0", T0), schemaVersion: 2 };
    expect(resumeSession(s0, manifest, T1)).toBeNull();
    const fin = { ...initSession(manifest.concepts, "1.0.0", T0), finished: true };
    expect(resumeSession(fin, manifest, T1)).toBeNull();
  });
});
