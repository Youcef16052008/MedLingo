// @vitest-environment jsdom
import { describe, it, expect, beforeEach, vi } from "vitest";
import {
  emptyPilotState,
  loadPilotState,
  savePilotState,
  mergeExerciseStat,
  exportPilotJson,
  importPilotJson,
  applyImportedState,
  resetPilotState,
  restoreBackup,
  PILOT_STORAGE_KEY,
  PILOT_BACKUP_KEY,
} from "../storage";
import { initSession, recordAnswer } from "../session";
import type { PilotManifest } from "../types";

/**
 * P2.3 — persistance isolée : namespace propre, données corrompues → message
 * explicite (jamais un throw), import validé + sauvegarde, reset distinct du
 * store global, changement de version du contenu géré explicitement.
 */

const manifest: PilotManifest = {
  schemaVersion: 1,
  id: "pilot-anatomy",
  contentVersion: "1.0.0",
  generatedAt: "2026-11-11T00:00:00.000Z",
  generatedFrom: "test",
  concepts: [
    {
      id: "c1",
      unit: 1,
      order: 1,
      labelEn: "Heart",
      hints: { fr: "Cœur", ar: "القلب" },
      languageObjective: "t",
      definition: { en: "d", fr: "d", ar: "d" },
      selectionNote: "n",
      status: "draft",
      contentVersion: "1.0.0",
      reviews: { linguistic: { status: "pending" }, medical: { status: "pending" } },
      exercises: [
        {
          id: "c1-a",
          conceptId: "c1",
          objective: "recognize",
          role: "assessment",
          kind: "mcq",
          prompt: { en: "p", fr: "p", ar: "p" },
          options: ["Heart", "Lung"],
          answer: "Heart",
          explanation: { en: "e", fr: "e", ar: "e" },
        },
      ],
    },
  ],
};

const NOW = 1_000_000;

function sessionWithAnswer() {
  let s = initSession(manifest.concepts, "1.0.0", NOW);
  s = recordAnswer(s, manifest.concepts[0].exercises[0], "Heart", NOW + 1);
  return { s, ex: manifest.concepts[0].exercises[0] };
}

describe("storage — chargement défensif", () => {
  beforeEach(() => {
    localStorage.clear();
  });

  it("clé absente → état vide, sans warning", () => {
    const r = loadPilotState(manifest, NOW);
    expect(r.warning).toBeNull();
    expect(r.state.exerciseStats).toEqual({});
  });

  it("JSON corrompu → état vide + warning explicite (pas de throw)", () => {
    localStorage.setItem(PILOT_STORAGE_KEY, "{not json");
    const r = loadPilotState(manifest, NOW);
    expect(r.state).toEqual(emptyPilotState(NOW));
    expect(r.warning).toBe("pilotDataCorrupted");
  });

  it("schéma inconnu → warning pilotSchemaChanged (pas de migration silencieuse)", () => {
    localStorage.setItem(PILOT_STORAGE_KEY, JSON.stringify({ schemaVersion: 99 }));
    const r = loadPilotState(manifest, NOW);
    expect(r.warning).toBe("pilotSchemaChanged");
    expect(r.state.exerciseStats).toEqual({});
  });

  it("session d'une autre version de contenu → invalidée au chargement", () => {
    const stale = {
      schemaVersion: 1,
      updatedAt: NOW,
      contentVersion: "0.9.0",
      lastSession: initSession(manifest.concepts, "0.9.0", NOW),
      exerciseStats: {},
    };
    localStorage.setItem(PILOT_STORAGE_KEY, JSON.stringify(stale));
    const r = loadPilotState(manifest, NOW);
    expect(r.warning).toBeNull();
    expect(r.state.lastSession).toBeNull();
  });

  it("sauvegarde puis rechargement conserve les stats et la séance", () => {
    const { s, ex } = sessionWithAnswer();
    let st = emptyPilotState(NOW);
    st = mergeExerciseStat(
      st,
      { conceptId: "c1", exerciseId: ex.id, attempts: 1, firstResult: "first_try", helped: false, lastAt: NOW + 1 },
      NOW + 1,
    );
    st = { ...st, contentVersion: "1.0.0", lastSession: s };
    expect(savePilotState(st).ok).toBe(true);
    const r = loadPilotState(manifest, NOW + 2);
    expect(r.warning).toBeNull();
    expect(r.state.exerciseStats[ex.id].firstResult).toBe("first_try");
    expect(r.state.lastSession?.id).toBe(s.id);
  });

  it("quota dépassé → échec explicite, pas de « ok » mensonger", () => {
    const setItem = vi.spyOn(Storage.prototype, "setItem").mockImplementation(() => {
      throw new DOMException("full", "QuotaExceededError");
    });
    const r = savePilotState(emptyPilotState(NOW));
    expect(r.ok).toBe(false);
    expect(r.error).toBe("pilotStorageQuota");
    setItem.mockRestore();
  });
});

describe("storage — export / import", () => {
  beforeEach(() => localStorage.clear());

  it("export → import : aller-retour fidèle", () => {
    const { s, ex } = sessionWithAnswer();
    let st = emptyPilotState(NOW);
    st = mergeExerciseStat(
      st,
      { conceptId: "c1", exerciseId: ex.id, attempts: 1, firstResult: "first_try", helped: false, lastAt: NOW },
      NOW,
    );
    st = { ...st, contentVersion: "1.0.0", lastSession: s };
    const json = exportPilotJson(st);
    const imp = importPilotJson(json, manifest, NOW);
    expect(imp.ok).toBe(true);
    if (imp.ok) {
      expect(imp.state.exerciseStats[ex.id]).toEqual(st.exerciseStats[ex.id]);
      expect(imp.state.lastSession?.id).toBe(s.id);
      expect(imp.note).toBeNull();
    }
  });

  it("import invalide / mauvais schéma → erreurs distinctes", () => {
    expect(importPilotJson("nope", manifest, NOW)).toMatchObject({ ok: false, error: "pilotImportInvalid" });
    expect(importPilotJson(JSON.stringify({ kind: "other" }), manifest, NOW)).toMatchObject({
      ok: false,
      error: "pilotImportInvalid",
    });
    const bad = { kind: "medlingo-pilot-anatomy-progress", state: { schemaVersion: 42 } };
    expect(importPilotJson(JSON.stringify(bad), manifest, NOW)).toMatchObject({
      ok: false,
      error: "pilotImportSchema",
    });
  });

  it("import d'une autre version de contenu → accepté mais séance invalidée + note", () => {
    const other = {
      kind: "medlingo-pilot-anatomy-progress",
      state: {
        schemaVersion: 1,
        updatedAt: NOW,
        contentVersion: "2.0.0",
        lastSession: initSession(manifest.concepts, "2.0.0", NOW),
        exerciseStats: { "c1-a": { conceptId: "c1", exerciseId: "c1-a", attempts: 3, firstResult: "error", helped: false, lastAt: NOW } },
      },
    };
    const imp = importPilotJson(JSON.stringify(other), manifest, NOW);
    expect(imp.ok).toBe(true);
    if (imp.ok) {
      expect(imp.note).toContain("2.0.0");
      expect(imp.state.lastSession).toBeNull();
      expect(imp.state.exerciseStats["c1-a"].attempts).toBe(3);
    }
  });

  it("applyImportedState : sauvegarde préalable, puis remplacement ; échec de backup = refus", () => {
    const current = emptyPilotState(NOW);
    const imported = { ...emptyPilotState(NOW), contentVersion: "1.0.0" };
    expect(applyImportedState(current, imported).ok).toBe(true);
    expect(JSON.parse(localStorage.getItem(PILOT_BACKUP_KEY)!)).toEqual(current);

    const setItem = vi.spyOn(Storage.prototype, "setItem").mockImplementation(() => {
      throw new DOMException("full", "QuotaExceededError");
    });
    const refused = applyImportedState(current, imported);
    expect(refused.ok).toBe(false);
    expect(refused.error).toBe("pilotStorageQuota");
    setItem.mockRestore();
  });

  it("restoreBackup restitue l'état d'avant import", () => {
    const before = { ...emptyPilotState(NOW), contentVersion: "1.0.0" };
    localStorage.setItem(PILOT_BACKUP_KEY, JSON.stringify(before));
    const r = restoreBackup(manifest, NOW);
    expect(r.warning).toBeNull();
    expect(r.state.contentVersion).toBe("1.0.0");
    expect(restoreBackup(manifest, NOW).warning).toBeNull();
  });

  it("reset pilote : ne touche qu'aux clés pilote", () => {
    localStorage.setItem("medlingo_web_v1_user", '{"keep":true}');
    localStorage.setItem(PILOT_STORAGE_KEY, "{}");
    localStorage.setItem(PILOT_BACKUP_KEY, "{}");
    expect(resetPilotState().ok).toBe(true);
    expect(localStorage.getItem(PILOT_STORAGE_KEY)).toBeNull();
    expect(localStorage.getItem(PILOT_BACKUP_KEY)).toBeNull();
    expect(localStorage.getItem("medlingo_web_v1_user")).toBe('{"keep":true}');
  });
});
