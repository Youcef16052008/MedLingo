/**
 * WS5.1 — moteur de maîtrise unifié (calcul PUR, testable).
 *
 * Fait converger les trois signaux déjà présents dans le store :
 *  - `moduleBest`  : meilleur score de quiz par module (0–100) — la maîtrise
 *    déclarée par les exercices ;
 *  - `sm2`         : cartes de révision espacée par terme — la mémoire ;
 *  - l'historique  : `answered`/`correct` — la pratique.
 *
 * Transparence (le différenciateur vs Duolingo) : l'étudiant voit POURQUOI —
 * force par module, cartes dues, termes les plus faibles. Prévisible et
 * auditable : mêmes entrées → mêmes sorties.
 */
import type { Sched } from "./sm2";
import { modById } from "../data/modules";

export interface MasteryInput {
  moduleBest: Record<string, number>;
  sm2: Record<string, Sched>;
  /** Termes du module (ids) — fourni par l'appelant depuis terms.json. */
  termIds: number[];
  dailyGoal: number;
}

export interface ModuleMastery {
  /** Force 0–100 : quiz 60 % + santé SM-2 40 %. */
  force: number;
  /** Meilleur score de quiz du module (0 si jamais tenté). */
  quizBest: number;
  /** Cartes SM-2 démarrées / termes du module. */
  started: number;
  total: number;
  /** Cartes échues à réviser maintenant. */
  due: number;
  /** Termes jamais révisés. */
  unseen: number;
}

/** Carte en retard : due ≤ maintenant. (Sched.due = timestamp ms.) */
function isDue(card: Sched | undefined, now: number): boolean {
  return !!card && card.due <= now;
}

/**
 * Force d'un module : le quiz pèse 60 % (best score), la mémoire SM-2 40 %
 * (couverture des termes pondérée par la santé des cartes). Un module jamais
 * touché → 0. Aucun terme dans le module → le quiz seul décide.
 */
export function moduleMastery(input: MasteryInput, now: number): ModuleMastery {
  const quizBest = Math.max(0, Math.min(100, input.moduleBest["quiz"] ?? 0));
  const total = input.termIds.length;

  let started = 0;
  let due = 0;
  let unseen = 0;
  let healthSum = 0;
  for (const id of input.termIds) {
    const card = input.sm2[String(id)];
    if (!card) {
      unseen += 1;
      continue;
    }
    started += 1;
    if (isDue(card, now)) due += 1;
    // Santé d'une carte : EF normalisé (1,3→3,0) + pénalité de retard.
    const efHealth = Math.max(0, Math.min(1, (card.ef - 1.3) / (3.0 - 1.3)));
    const overdue = isDue(card, now);
    const ageOverdue = overdue
      ? Math.min(1, (now - card.due) / (7 * 86_400_000))
      : 0;
    healthSum += efHealth * (1 - 0.5 * ageOverdue);
  }

  const sm2Health = total > 0 ? (started / total) * (started ? healthSum / started : 0) : null;
  const force = sm2Health === null ? quizBest : Math.round(0.6 * quizBest + 40 * sm2Health);

  return { force, quizBest, started, total, due, unseen };
}

/** Maîtrise par module pour la liste d'ids donnée (tri desc. de force). */
export function masteryOverview(
  moduleBest: Record<string, number>,
  sm2: Record<string, Sched>,
  termsByModule: Array<{ id: string; termIds: number[] }>,
  now: number
): Array<{ id: string } & ModuleMastery> {
  return termsByModule
    .map((m) => ({ id: m.id, ...moduleMastery({ moduleBest, sm2, termIds: m.termIds, dailyGoal: 20 }, now) }))
    .sort((a, b) => a.force - b.force);
}

/**
 * WS5.2 — les termes les plus FAIBLES d'un module, ordre de révision :
 * d'abord les échus (retard le plus ancien), puis les EF les plus bas,
 * puis les jamais vus (à découvrir). `limit` termes retournés.
 */
export function weakestTerms(
  termIds: number[],
  sm2: Record<string, Sched>,
  now: number,
  limit = 8
): Array<{ termId: number; reason: "due" | "low_ef" | "unseen"; dueAt?: number }> {
  const out: Array<{ termId: number; reason: "due" | "low_ef" | "unseen"; dueAt?: number }> = [];
  for (const id of termIds) {
    const card = sm2[String(id)];
    if (!card) {
      out.push({ termId: id, reason: "unseen" });
    } else if (card.due <= now) {
      out.push({ termId: id, reason: "due", dueAt: card.due });
    } else if (card.ef < 2.2) {
      out.push({ termId: id, reason: "low_ef" });
    }
  }
  // Échus d'abord (retard croissant), puis unseen, puis EF croissant.
  const rank = { due: 0, unseen: 1, low_ef: 2 } as const;
  return out
    .sort(
      (a, b) =>
        rank[a.reason] - rank[b.reason] ||
        (a.dueAt ?? 0) - (b.dueAt ?? 0) ||
        (sm2[String(a.termId)]?.ef ?? 0) - (sm2[String(b.termId)]?.ef ?? 0)
    )
    .slice(0, limit);
}

/** Renvoi aux chapitres (WS5.3) : chapitres des exercices ratés, comptés. */
export function weakChapters(wrong: Array<{ chapter: string; module: string }>): Array<{ chapter: string; module: string; count: number }> {
  const byKey = new Map<string, { chapter: string; module: string; count: number }>();
  for (const w of wrong) {
    const key = `${w.module}|${w.chapter}`;
    const hit = byKey.get(key);
    if (hit) hit.count += 1;
    else byKey.set(key, { chapter: w.chapter, module: w.module, count: 1 });
  }
  return [...byKey.values()].sort((a, b) => b.count - a.count);
}

/** Vérification défensive : l'id de module existe (évite les clés fantômes). */
export function isKnownModule(id: string): boolean {
  return modById(id) !== undefined;
}
