import { describe, it, expect } from "vitest";
import { readFileSync } from "node:fs";
import { join, dirname } from "node:path";
import { fileURLToPath } from "node:url";
import {
  TIME_TO_VERSION,
  advanceMeasure,
  currentMeasureItem,
  initMeasureSession,
  interruptMeasure,
  invalidateMeasureItem,
  measureItemsFor,
  measureScore,
  measureTimeBlocked,
  finishedMeasureAt,
  recordMeasureAnswer,
  resumeMeasureSession,
  type MeasureManifest,
} from "../measure";
import type { PilotManifest } from "../types";

/**
 * P3.1 — moteur de mesure : AUCUN feedback correctif (l'enregistrement ne
 * produit pas de verdict affichable), dénominateur explicite, item ambigu
 * invalidé et tracé, absence de test ≠ score nul, délai réel T1→T2 enregistré,
 * mapping fixe T0/T1/T2 → versions A/B/C.
 *
 * Garde structurelle (séparation stockage ≠ exposition) : les ids de mesure
 * du manifeste RÉEL sont disjoints des exercices d'entraînement.
 */

const here = dirname(fileURLToPath(import.meta.url));
const training = JSON.parse(
  readFileSync(join(here, "..", "..", "..", "data", "pilot-anatomy.v1.json"), "utf-8"),
) as PilotManifest;
const measure = JSON.parse(
  readFileSync(join(here, "..", "..", "..", "data", "pilot-anatomy-measure.v1.json"), "utf-8"),
) as MeasureManifest;

const T0 = 1_000_000;
const T1 = T0 + 60_000;

describe("banques de mesure — structure (garde anti-contamination)", () => {
  const trainingIds = new Set(training.concepts.flatMap((c) => c.exercises.map((e) => e.id)));
  const measureIds = measure.banks.flatMap((b) => b.items.map((i) => i.id));

  it("aucun id de mesure ne collide avec un exercice d'entraînement", () => {
    const overlap = measureIds.filter((id) => trainingIds.has(id));
    expect(overlap).toEqual([]);
  });

  it("chaque concept × version (A/B/C) a une banque ; mapping T0/T1/T2 fixe", () => {
    expect(TIME_TO_VERSION).toEqual({ T0: "A", T1: "B", T2: "C" });
    expect(measure.versions).toEqual(["A", "B", "C"]);
    for (const c of training.concepts) {
      for (const v of ["A", "B", "C"]) {
        const bank = measure.banks.find((b) => b.conceptId === c.id && b.version === v);
        expect(bank, `${c.id}/${v}`).toBeTruthy();
        const dims = new Set(bank!.items.map((i) => i.dimension));
        expect(dims.has("recognize")).toBe(true);
        expect(dims.has("recall")).toBe(true);
      }
    }
  });

  it("la même version de contenu est partagée entraînement/mesure", () => {
    expect(measure.contentVersion).toBe(training.contentVersion);
  });

  it("T1 utilise une consigne DIFFÉRENTE de T0 (source différente sur au moins un concept)", () => {
    // Version B tourne la reconnaissance sur la description FR et le rappel
    // sur la définition EN : les sources doivent différer d'A au global.
    const sources = (v: string) =>
      new Set(measure.banks.filter((b) => b.version === v).flatMap((b) => b.items.map((i) => i.source)));
    expect(sources("B")).not.toEqual(sources("A"));
  });
});

describe("moteur — séance et enregistrement (sans feedback)", () => {
  const conceptIds = training.concepts.slice(0, 2).map((c) => c.id);
  const items = measureItemsFor(measure, conceptIds, "T0");
  expect(items.length).toBeGreaterThan(0);

  it("T0 : items servis avec la version A uniquement", () => {
    expect(items.every((i) => i.version === "A")).toBe(true);
  });

  it("enregistrement idempotent ; le résultat est stocké, jamais affiché", () => {
    let s = initMeasureSession("T0", conceptIds, training.contentVersion, T0);
    const it0 = items[0];
    s = recordMeasureAnswer(s, it0, it0.answer, T1, T0);
    const s2 = recordMeasureAnswer(s, it0, "autre", T1 + 1, T0);
    expect(s2).toBe(s); // double soumission = même état
    expect(s.results).toHaveLength(1);
    expect(s.results[0].correct).toBe(true);
    expect(s.results[0].durationMs).toBe(T1 - T0); // durée observée passive
  });

  it("réponse libre : variantes acceptées passent ; mauvaise réponse = faux", () => {
    let s = initMeasureSession("T0", conceptIds, training.contentVersion, T0);
    const recall = items.find((i) => i.dimension === "recall")!;
    s = recordMeasureAnswer(s, recall, "  mauvaise  ", T1, T0);
    expect(s.results[0].correct).toBe(false);
    // La réponse avec variante (casse/espaces normalisés) passe.
    s = initMeasureSession("T0", conceptIds, training.contentVersion, T0);
    s = recordMeasureAnswer(s, recall, recall.acceptedVariants?.[0] ?? recall.answer, T1, T0);
    expect(s.results[0].correct).toBe(true);
  });

  it("avance + interruption : état consigné, item non traité = correct null", () => {
    let s = initMeasureSession("T0", conceptIds, training.contentVersion, T0);
    const it0 = items[0];
    s = recordMeasureAnswer(s, it0, it0.answer, T1, T0);
    s = advanceMeasure(s, items, T1);
    expect(currentMeasureItem(s, items)!.id).toBe(items[1].id);
    // Interruption avant d'avoir traité l'item 2.
    s = interruptMeasure(s, T1 + 5);
    expect(s.interrupted).toBe(true);
    expect(s.finished).toBe(false);
    // L'item 1 traité garde son résultat ; rien n'est transformé en score.
    expect(measureScore(s).total.correct).toBe(1);
    expect(measureScore(s).total.presented).toBe(1);
  });

  it("item ambigu : invalidé ET tracé, exclu du dénominateur", () => {
    let s = initMeasureSession("T0", conceptIds, training.contentVersion, T0);
    const it0 = items[0];
    s = recordMeasureAnswer(s, it0, "x", T1, T0);
    s = invalidateMeasureItem(s, it0, "consigne ambiguë (retour participant)", T1 + 2);
    const sc = measureScore(s);
    expect(sc.total.invalidated).toBe(1);
    expect(sc.total.presented).toBe(0);
    expect(sc.total.hasScore).toBe(false); // sans item valide traité, PAS de score
    expect(s.results.find((r) => r.itemId === it0.id)!.invalidatedReason).toContain("ambiguë");
  });

  it("absence de test ≠ score nul : une séance vide ne produit pas de score", () => {
    const s = initMeasureSession("T0", conceptIds, training.contentVersion, T0);
    const sc = measureScore(s);
    expect(sc.total.hasScore).toBe(false);
    expect(sc.total.presented).toBe(0);
    expect(sc.total.correct).toBe(0); // numérique 0 mais hasScore=false : jamais affiché « 0 »
  });

  it("T2 : le délai RÉEL depuis T1 est enregistré (jamais prétendu = 7 jours)", () => {
    const t1 = initMeasureSession("T1", conceptIds, training.contentVersion, T0);
    t1.finished = true;
    const t2Start = T0 + 8 * 24 * 3600 * 1000; // 8 jours plus tard — le délai RÉEL
    const t2 = initMeasureSession("T2", conceptIds, training.contentVersion, t2Start, finishedMeasureAt([t1], "T1"));
    expect(t2.actualDelayFromT1Ms).toBe(8 * 24 * 3600 * 1000);
    // Sans T1 finie : délai null (on n'invente pas 7 jours).
    const t2b = initMeasureSession("T2", conceptIds, training.contentVersion, t2Start, null);
    expect(t2b.actualDelayFromT1Ms).toBeNull();
  });
});

describe("moteur — séquencement et reprise", () => {
  const conceptIds = training.concepts.slice(0, 2).map((c) => c.id);

  it("T1 bloqué sans T0 fini ; T2 bloqué sans T1 fini", () => {
    expect(measureTimeBlocked("T0", [])).toBeNull();
    expect(measureTimeBlocked("T1", [])).toBe("measureNeedT0");
    const t0 = { ...initMeasureSession("T0", conceptIds, training.contentVersion, T0), finished: true };
    expect(measureTimeBlocked("T1", [t0])).toBeNull();
    expect(measureTimeBlocked("T2", [t0])).toBe("measureNeedT1");
    const t1 = { ...initMeasureSession("T1", conceptIds, training.contentVersion, T0), finished: true };
    expect(measureTimeBlocked("T2", [t0, t1])).toBeNull();
  });

  it("reprise invalidée au changement de version de contenu (jamais un silence)", () => {
    const s = initMeasureSession("T0", conceptIds, training.contentVersion, T0);
    expect(resumeMeasureSession(s, measure, T1)).not.toBeNull();
    const other = { ...measure, contentVersion: "9.9.9" };
    expect(resumeMeasureSession(s, other, T1)).toBeNull();
    // Séance finie : pas de reprise.
    expect(resumeMeasureSession({ ...s, finished: true }, measure, T1)).toBeNull();
  });
});
