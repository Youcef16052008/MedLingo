/**
 * SM-2 — port exact de `SpacedRepetitionAlgorithm.kt`
 * (et de l'implémentation web de référence dans `web/app.js`).
 *
 * `now` est injecté partout : aucune fonction du domaine ne lit l'horloge murale,
 * ce qui garde les échéances déterministes et testables.
 */

export interface Sched {
  rep: number;
  ef: number;
  iv: number;
  due: number;
}

export const DEFAULT_SCHED: Sched = { rep: 0, ef: 2.5, iv: 1, due: 0 };

const DAY_MS = 86_400_000;

export function sm2Next(quality: number, prev: Sched | undefined, now: number): Sched {
  const base = prev ?? DEFAULT_SCHED;
  let ef = base.ef;
  let rep: number;
  let iv: number;

  if (quality < 3) {
    rep = 0;
    iv = 1;
  } else {
    rep = base.rep + 1;
    if (rep === 1) iv = 1;
    else if (rep === 2) iv = 3;
    else if (rep === 3) iv = 7;
    else {
      const calc = Math.floor(base.iv * base.ef);
      iv = calc <= base.iv ? base.iv + 1 : calc;
    }
  }

  const qd = 5 - quality;
  ef = ef + (0.1 - qd * (0.08 + qd * 0.02));
  if (ef < 1.3) ef = 1.3;

  // Précision complète, comme `SpacedRepetitionAlgorithm.kt` : un arrondi à
  // 4 décimales dérive les intervalles `floor(iv * ef)` au fil des révisions.
  return { rep, ef, iv, due: now + iv * DAY_MS };
}

export function isDue(s: Sched | undefined, now: number): boolean {
  return !!s && s.due <= now;
}

/** Cartes dues sur une liste de scheds indexés par id d'exercice/terme. */
export function countDue(sm2: Record<string, Sched>, ids: number[], now: number): number {
  let n = 0;
  for (const id of ids) if (isDue(sm2[String(id)], now)) n++;
  return n;
}
