import { describe, it, expect } from "vitest";
import { sm2Next, isDue, countDue, type Sched } from "../sm2";

const DAY = 86_400_000;
const NOW = 1_700_000_000_000;

describe("sm2Next — progression des intervalles", () => {
  it("première révision réussie → 1 jour", () => {
    const r = sm2Next(4, undefined, NOW);
    expect(r.rep).toBe(1);
    expect(r.iv).toBe(1);
    expect(r.due).toBe(NOW + DAY);
  });

  it("1 → 3 → 7 jours puis croissance par ease factor", () => {
    let s: Sched | undefined = undefined;
    s = sm2Next(4, s, NOW);
    expect(s.iv).toBe(1);
    s = sm2Next(4, s, NOW);
    expect(s.iv).toBe(3);
    s = sm2Next(4, s, NOW);
    expect(s.iv).toBe(7);
    const before4 = s;
    s = sm2Next(4, s, NOW);
    // l'intervalle est calculé avec l'EF d'origine, avant la mise à jour
    expect(s.iv).toBe(Math.floor(before4.iv * before4.ef));
    expect(s.ef).toBe(before4.ef); // q = 4 laisse l'EF inchangé

    // q = 5 augmente l'EF : le nouvel intervalle doit rester basé sur l'EF AVANT MAJ
    const before5 = s;
    s = sm2Next(5, s, NOW);
    expect(s.ef).toBeGreaterThan(before5.ef);
    expect(s.iv).toBe(Math.floor(before5.iv * before5.ef));
  });

  it("l'échec remet rep à 0 et l'intervalle à 1 jour", () => {
    const prev: Sched = { rep: 5, ef: 2.5, iv: 30, due: NOW };
    const r = sm2Next(1, prev, NOW);
    expect(r.rep).toBe(0);
    expect(r.iv).toBe(1);
    expect(r.due).toBe(NOW + DAY);
  });

  it("l'ease factor ne descend jamais sous 1.3", () => {
    let s: Sched = { rep: 0, ef: 1.3, iv: 1, due: NOW };
    for (let i = 0; i < 10; i++) s = sm2Next(1, s, NOW);
    expect(s.ef).toBe(1.3);
  });

  it("la date d'échéance est dérivée de l'horloge injectée", () => {
    const r = sm2Next(5, undefined, NOW);
    expect(r.due).toBe(NOW + r.iv * DAY);
    expect(r.due).toBeGreaterThanOrEqual(NOW);
  });

  it("l'ease factor garde sa précision complète (pas d'arrondi à 4 décimales)", () => {
    let s = sm2Next(4, undefined, NOW);
    for (const q of [4, 3, 5, 1, 2]) s = sm2Next(q, s, NOW);
    // Sans arrondi : 1.5999999999999999 — un toFixed(4) donnerait 1.6 et
    // ferait dériver floor(iv * ef) par rapport à Android.
    expect(s.ef).toBe(1.5999999999999999);
    expect(s.ef).not.toBe(1.6);
  });
});

describe("isDue / countDue", () => {
  it("une carte non étudiée n'est pas due", () => {
    expect(isDue(undefined, NOW)).toBe(false);
  });

  it("due avant l'échéance, pas due après", () => {
    const s: Sched = { rep: 1, ef: 2.5, iv: 3, due: NOW + 3 * DAY };
    expect(isDue(s, NOW)).toBe(false);
    expect(isDue(s, NOW + 3 * DAY)).toBe(true);
    expect(isDue(s, NOW + 4 * DAY)).toBe(true);
  });

  it("countDue compte uniquement les ids échus", () => {
    const sm2: Record<string, Sched> = {
      "1": { rep: 1, ef: 2.5, iv: 1, due: NOW - 1 },
      "2": { rep: 1, ef: 2.5, iv: 7, due: NOW + 7 * DAY },
      "3": { rep: 1, ef: 2.5, iv: 1, due: NOW },
    };
    expect(countDue(sm2, [1, 2, 3], NOW)).toBe(2);
    expect(countDue(sm2, [], NOW)).toBe(0);
  });
});
