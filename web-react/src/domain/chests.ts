/**
 * Caisse de récompense : tirage pondéré, `rand` injecté → testable.
 * Gagnée à la fin d'un quiz (toujours) ou d'une révision (≥ 10 cartes).
 */
import { addXp, type XpState } from "./gamification";

export type ChestKind = "xp" | "gems" | "freeze";

export interface ChestReward {
  kind: ChestKind;
  amount: number;
}

interface ChestRow extends ChestReward {
  weight: number;
}

/** Poids en %, total 100. */
export const CHEST_TABLE: ChestRow[] = [
  { kind: "xp", amount: 20, weight: 30 },
  { kind: "gems", amount: 30, weight: 30 },
  { kind: "gems", amount: 50, weight: 20 },
  { kind: "freeze", amount: 1, weight: 15 },
  { kind: "gems", amount: 100, weight: 5 },
];

/** Ouvre une caisse : `rand()` ∈ [0,1) pondère la table. */
export function openChest(rand: () => number): ChestReward {
  const roll = rand() * CHEST_TABLE.reduce((sum, r) => sum + r.weight, 0);
  let acc = 0;
  for (const row of CHEST_TABLE) {
    acc += row.weight;
    if (roll < acc) return { kind: row.kind, amount: row.amount };
  }
  const last = CHEST_TABLE[CHEST_TABLE.length - 1];
  return { kind: last.kind, amount: last.amount };
}

/**
 * Déclenche l'attribution d'une caisse en fin de session.
 * `quiz` → toujours ; `flash` → seulement si ≥ 10 cartes revues (`score`).
 * Retourne `true` si une caisse a été ajoutée à `pendingChests`.
 */
export function earnChest(
  s: { pendingChests: number },
  kind: "quiz" | "flash",
  score: number
): boolean {
  const earned = kind === "quiz" ? score >= 0 : score >= 10;
  if (earned) s.pendingChests += 1;
  return earned;
}

/** Applique une récompense de caisse à l'état. */
export function applyChestReward(
  s: XpState & { gems: number },
  reward: ChestReward,
  now: number
): void {
  if (reward.kind === "gems") s.gems += reward.amount;
  else if (reward.kind === "freeze") s.freezeCount += reward.amount;
  else addXp(s, reward.amount, now); // xp : mêmes règles (jour, ligue, série)
}
