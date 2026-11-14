// @vitest-environment jsdom
import { describe, it, expect, beforeEach, vi } from "vitest";
import {
  emptyMeasureState,
  loadMeasureState,
  saveMeasureState,
  upsertMeasureSession,
  exportMeasureJson,
  exportMeasureCsv,
  importMeasureJson,
  applyMeasureImport,
  restoreMeasureBackup,
  resetMeasureState,
  MEASURE_STORAGE_KEY,
  MEASURE_BACKUP_KEY,
} from "../measureStorage";
import { initMeasureSession, recordMeasureAnswer, advanceMeasure, interruptMeasure } from "../measure";
import type { MeasureItem } from "../measure";

/**
 * P3.2 — persistance de mesure : namespace SÉPARÉ de l'entraînement, export
 * JSON/CSV avec les données exigées (temps, versions, itemId, résultat, aides,
 * durée, état terminé/interrompu), pseudonyme sans donnée personnelle,
 * séance sans item traité ≠ faux score 0 dans le CSV, import validé,
 * reset de mesure sans toucher l'entraînement.
 */

const CONTENT_VERSION = "0.1.0";
const NOW = 1_000_000;

const item = (id: string): MeasureItem => ({
  id,
  conceptId: "c1",
  version: "A",
  dimension: "recognize",
  kind: "mcq",
  prompt: { en: "p", fr: "p", ar: "p" },
  options: ["Heart", "Lung"],
  answer: "Heart",
  source: "defEn",
});

function stateWithSessions() {
  let s = initMeasureSession("T0", ["c1", "c2"], CONTENT_VERSION, NOW);
  s = recordMeasureAnswer(s, item("c1-rec-A"), "Heart", NOW + 100, NOW);
  s = recordMeasureAnswer(s, item("c1-recal-A"), "nope", NOW + 200, NOW + 50);
  s = advanceMeasure(s, [item("c1-rec-A"), item("c1-recal-A"), item("c2-rec-A")], NOW + 200);
  s = interruptMeasure(s, NOW + 300);
  let st = { ...emptyMeasureState(NOW), pseudonym: "participant-01" };
  st = upsertMeasureSession(st, s);
  // Une séance DÉMARRÉE sans aucun item traité (exigence CSV : jamais un 0).
  const empty = initMeasureSession("T1", ["c1"], CONTENT_VERSION, NOW + 400);
  st = upsertMeasureSession(st, empty);
  return { state: st, session: s };
}

describe("mesure — chargement défensif", () => {
  beforeEach(() => localStorage.clear());

  it("clé absente → état vide sans warning", () => {
    const r = loadMeasureState(NOW);
    expect(r.warning).toBeNull();
    expect(r.state.sessions).toEqual([]);
  });

  it("JSON corrompu → état vide + warning explicite (pas de throw)", () => {
    localStorage.setItem(MEASURE_STORAGE_KEY, "{oops");
    const r = loadMeasureState(NOW);
    expect(r.warning).toBe("pilotDataCorrupted");
    expect(r.state.sessions).toEqual([]);
  });

  it("sauvegarde → rechargement conserve pseudonyme et séances", () => {
    const { state } = stateWithSessions();
    expect(saveMeasureState(state).ok).toBe(true);
    const r = loadMeasureState(NOW + 1);
    expect(r.warning).toBeNull();
    expect(r.state.pseudonym).toBe("participant-01");
    expect(r.state.sessions).toHaveLength(2);
    expect(r.state.sessions[0].results).toHaveLength(2);
  });

  it("quota dépassé → échec explicite", () => {
    const setItem = vi.spyOn(Storage.prototype, "setItem").mockImplementation(() => {
      throw new DOMException("full", "QuotaExceededError");
    });
    expect(saveMeasureState(emptyMeasureState(NOW)).error).toBe("pilotStorageQuota");
    setItem.mockRestore();
  });
});

describe("mesure — export P3.2", () => {
  beforeEach(() => localStorage.clear());

  it("JSON versionné avec les données exigées (temps, versions, itemId, résultat, aides, durée, état)", () => {
    const { state, session } = stateWithSessions();
    const parsed = JSON.parse(exportMeasureJson(state, CONTENT_VERSION));
    expect(parsed.kind).toBe("medlingo-pilot-anatomy-measure");
    expect(parsed.schemaVersion).toBe(1);
    expect(parsed.contentVersion).toBe(CONTENT_VERSION);
    expect(parsed.pseudonym).toBe("participant-01");
    expect(parsed.aidsUsedDuringMeasure).toBe(false); // aides interdites pendant la mesure
    const raw = parsed.sessions.find((s: { id: string }) => s.id === session.id);
    expect(raw.time).toBe("T0");
    expect(raw.version).toBe("A");
    expect(raw.interrupted).toBe(true);
    const res = raw.results[0];
    expect(res.itemId).toBe("c1-rec-A");
    expect(res.correct).toBe(true);
    expect(res.durationMs).toBe(100);
    expect(res.dimension).toBe("recognize");
  });

  it("CSV : en-tête, une ligne par item ; séance sans item traité ≠ score 0", () => {
    const { state } = stateWithSessions();
    const csv = exportMeasureCsv(state, CONTENT_VERSION);
    const lines = csv.trim().split("\n");
    expect(lines[0]).toContain("pseudonym,contentVersion,sessionId,time,testVersion");
    // 2 items traités (séance T0) + 1 ligne de séance vide (T1) + en-tête.
    expect(lines).toHaveLength(4);
    const t1line = lines.find((l) => l.includes(",T1,"));
    expect(t1line).toBeTruthy();
    // La ligne T1 vide : itemId et correct VIDES — pas de « 0 » fabriqué.
    const cells = t1line!.split(",");
    expect(cells[8]).toBe("");
    expect(cells[11]).toBe("");
    // Ligne traitée incorrecte : « 0 » explicite.
    const wrong = lines.find((l) => l.includes("c1-recal-A"));
    expect(wheel(wrong!)).toBe("0");
  });

  function wheel(line: string): string {
    // colonne correct = 12ᵉ champ (index 11), avec echappement à vérifier.
    return line.split(",")[11];
  }

  it("CSV : échappement des virgules et guillemets dans les cellules", () => {
    let st = { ...emptyMeasureState(NOW), pseudonym: 'par,ti"cipant' };
    let s = initMeasureSession("T0", ["c1"], CONTENT_VERSION, NOW);
    s = recordMeasureAnswer(s, item("c1-rec-A"), "Heart", NOW + 100, NOW);
    st = upsertMeasureSession(st, s);
    const csv = exportMeasureCsv(st, CONTENT_VERSION);
    expect(csv).toContain('"par,ti""cipant"');
  });

  it("import : version différente REFUSÉE (mesure non comparable)", () => {
    const { state } = stateWithSessions();
    const json = exportMeasureJson(state, CONTENT_VERSION);
    expect(importMeasureJson(json, CONTENT_VERSION, NOW).ok).toBe(true);
    const bad = importMeasureJson(json, "9.9.9", NOW);
    expect(bad.ok).toBe(false);
    if (!bad.ok) expect(bad.error).toBe("pilotImportVersion");
    expect(importMeasureJson("nope", CONTENT_VERSION, NOW)).toMatchObject({ ok: false, error: "pilotImportInvalid" });
  });

  it("applyMeasureImport : backup préalable ; échec de backup = refus", () => {
    const { state } = stateWithSessions();
    const imported = { ...emptyMeasureState(NOW), pseudonym: "p2" };
    expect(applyMeasureImport(state, imported).ok).toBe(true);
    expect(JSON.parse(localStorage.getItem(MEASURE_BACKUP_KEY)!).pseudonym).toBe("participant-01");
    const setItem = vi.spyOn(Storage.prototype, "setItem").mockImplementation(() => {
      throw new DOMException("full", "QuotaExceededError");
    });
    expect(applyMeasureImport(state, imported).error).toBe("pilotStorageQuota");
    setItem.mockRestore();
  });

  it("restoreMeasureBackup restitue l'état d'avant import", () => {
    localStorage.setItem(MEASURE_BACKUP_KEY, JSON.stringify({ ...emptyMeasureState(NOW), pseudonym: "avant" }));
    const r = restoreMeasureBackup(NOW);
    expect(r.warning).toBeNull();
    expect(r.state.pseudonym).toBe("avant");
    // Pas de sauvegarde restante → message explicite, jamais un silence.
    localStorage.removeItem(MEASURE_BACKUP_KEY);
    expect(restoreMeasureBackup(NOW + 1).warning).toBe("pilotNoBackup");
  });
});

describe("mesure — isolation vis-à-vis de l'entraînement", () => {
  beforeEach(() => localStorage.clear());

  it("reset de mesure : les clés d'ENTRAÎNEMENT ne sont pas touchées", () => {
    localStorage.setItem("medlingo_pilot_anatomy_v1", '{"training":true}');
    localStorage.setItem("medlingo_pilot_anatomy_v1_backup", '{"training":true}');
    localStorage.setItem(MEASURE_STORAGE_KEY, "{}");
    expect(resetMeasureState().ok).toBe(true);
    expect(localStorage.getItem(MEASURE_STORAGE_KEY)).toBeNull();
    expect(localStorage.getItem("medlingo_pilot_anatomy_v1")).toBe('{"training":true}');
    expect(localStorage.getItem("medlingo_pilot_anatomy_v1_backup")).toBe('{"training":true}');
  });

  it("clé de mesure distincte de la clé d'entraînement (séparation structurelle)", () => {
    expect(MEASURE_STORAGE_KEY).toBe("medlingo_pilot_anatomy_measure_v1");
    expect(MEASURE_STORAGE_KEY).not.toBe("medlingo_pilot_anatomy_v1");
  });
});
