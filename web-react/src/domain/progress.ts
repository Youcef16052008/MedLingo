import { MODULES, type Module } from "../data/modules";
import { getTerms, termsOfModule } from "../data/load";
import { isDue, type Sched } from "./sm2";
import type { Term } from "../types";

export interface Progress {
  seen: number;
  total: number;
  pct: number;
}

export type Sm2Map = Record<string, Sched>;

export function moduleProgress(m: Module, sm2: Sm2Map): Progress {
  const list = termsOfModule(m.id);
  const seen = list.filter((x) => sm2[String(x.id)]).length;
  return {
    seen,
    total: list.length,
    pct: list.length ? Math.round((seen / list.length) * 100) : 0,
  };
}

export function dueTerms(list: Term[], sm2: Sm2Map, now: number): Term[] {
  return list
    .filter((x) => isDue(sm2[String(x.id)], now))
    .sort((a, b) => (sm2[String(a.id)]?.due ?? 0) - (sm2[String(b.id)]?.due ?? 0));
}

export function dueCount(list: Term[], sm2: Sm2Map, now: number): number {
  let n = 0;
  for (const x of list) if (isDue(sm2[String(x.id)], now)) n++;
  return n;
}

/** Module ayant le plus de cartes dues, sinon le moins avancé. */
export function pickContinueModule(sm2: Sm2Map, now: number): Module {
  let best: Module | null = null;
  let bestDue = -1;
  let bestProg = 2;

  for (const m of MODULES) {
    const list = termsOfModule(m.id);
    if (!list.length) continue;
    const d = dueCount(list, sm2, now);
    if (d > bestDue) {
      bestDue = d;
      best = m;
    }
    if (d === 0) {
      const seen = list.filter((x) => sm2[String(x.id)]).length / list.length;
      if (seen < bestProg) {
        bestProg = seen;
        if (bestDue === 0) best = m;
      }
    }
  }
  return best ?? MODULES[0];
}

export function totalDue(sm2: Sm2Map, now: number): number {
  return dueCount(getTerms(), sm2, now);
}
