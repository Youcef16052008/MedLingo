// @vitest-environment jsdom
import { describe, it, expect, beforeAll } from "vitest";

/**
 * BUG-001 (régression) — le roundtrip réel persist → revive → update.
 *
 * `reviewQueue` est persisté comme TABLEAU ; il était rechargé via
 * `coerceRecord` (qui rejette les tableaux) → `{}` → `[...state.reviewQueue]`
 * jetait `TypeError` dans CHAQUE update() après un rechargement de page :
 * plus aucune progression n'était sauvegardée, silencieusement.
 *
 * IMPORTANT : AUCUN import statique de ../store ici — le module lit le
 * localStorage au chargement ; il doit être importé (dynamiquement) APRÈS
 * l'amorce du beforeAll, comme le ferait un vrai retour utilisateur.
 */

const NOW = 1_700_000_000_000;
const STORAGE_KEY = "medlingo_web_v1";

/** Un état minimal réaliste — revive() complète les champs manquants. */
function persistedState(): Record<string, unknown> {
  return {
    xp: 120,
    reviewQueue: [
      { lessonId: "anat:2", exIds: [1, 2], chapterId: "c1", moduleId: "anat", status: "unlocked", missedAt: NOW },
      { lessonId: "physio:1", exIds: [3], chapterId: "c2", moduleId: "physio", status: "played", missedAt: NOW },
    ],
  };
}

beforeAll(() => {
  localStorage.setItem(STORAGE_KEY, JSON.stringify(persistedState()));
});

describe("roundtrip persist → revive → update (BUG-001)", () => {
  it("revive() restitue la file de révision comme tableau", async () => {
    const { revive } = await import("../store");
    const state = revive(localStorage.getItem(STORAGE_KEY), NOW);
    expect(Array.isArray(state.reviewQueue)).toBe(true);
    expect(state.reviewQueue).toHaveLength(2);
  });

  it("update() ne jette pas après rechargement et sauvegarde (le vrai bug)", async () => {
    const { update, getState } = await import("../store");
    expect(() => update((d) => void (d.xp += 10))).not.toThrow();
    expect(getState().xp).toBe(130);
    expect(Array.isArray(getState().reviewQueue)).toBe(true);
    // La persistance a bien eu lieu (l'exception jetait AVANT persist()).
    const saved = JSON.parse(localStorage.getItem(STORAGE_KEY) ?? "{}");
    expect(saved.xp).toBe(130);
    expect(Array.isArray(saved.reviewQueue)).toBe(true);
  });

  it("une file corrompue (non-tableau) est ignorée proprement, pas castée", async () => {
    const { revive } = await import("../store");
    const raw = JSON.stringify({ ...persistedState(), reviewQueue: { "anat:2": true } });
    const state = revive(raw, NOW);
    expect(state.reviewQueue).toEqual([]);
  });

  it("les entrées de file invalides sont écartées une à une", async () => {
    const { revive } = await import("../store");
    const raw = JSON.stringify({
      ...persistedState(),
      reviewQueue: [...(persistedState().reviewQueue as unknown[]), { lessonId: 42 }, "poubelle", null],
    });
    const state = revive(raw, NOW);
    expect(state.reviewQueue).toHaveLength(2);
  });
});
