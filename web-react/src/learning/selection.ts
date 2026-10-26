/**
 * Sélection de la séance du jour — RISK-004.
 *
 * Priorité déterministe :
 *   1. séance interrompue (reprise) ;
 *   2. histoire avec le plus d'expressions échues (SM-2 d'expressions) ;
 *   3. histoire jamais commencée (première du fichier) ;
 *   4. histoire terminée depuis le plus longtemps (rotation).
 */

import type { Story } from "./types";
import { isDue, type Sched } from "../domain/sm2";

export interface StorySelectionInput {
  stories: Story[];
  exprSm2: Record<string, Sched>;
  /** Sous-ensemble de `StoryProgress` suffisant pour choisir. */
  progress: Record<string, { completedAt: number; savedAt: number }>;
  now: number;
}

function dueCount(story: Story, exprSm2: Record<string, Sched>, now: number): number {
  return story.targetPhrases.filter((p) => isDue(exprSm2[p.id], now)).length;
}

export function pickTodayStory(input: StorySelectionInput): Story | undefined {
  const { stories, exprSm2, progress, now } = input;
  if (stories.length === 0) return undefined;

  // 1. Reprise : une séance ouverte prime sur tout.
  const resumable = stories.find((s) => {
    const p = progress[s.storyId];
    return !!p && p.completedAt === 0 && p.savedAt > 0;
  });
  if (resumable) return resumable;

  // 2. Révisions dues : l'histoire qui en a le plus repasse en premier.
  let best: Story | undefined;
  let bestDue = 0;
  for (const s of stories) {
    const due = dueCount(s, exprSm2, now);
    if (due > bestDue) {
      best = s;
      bestDue = due;
    }
  }
  if (best) return best;

  // 3. Jamais commencée : la première du fichier (ordre éditorial).
  const fresh = stories.find((s) => !progress[s.storyId]);
  if (fresh) return fresh;

  // 4. Rotation : la terminée depuis le plus longtemps.
  return [...stories].sort(
    (a, b) => (progress[a.storyId]?.completedAt ?? 0) - (progress[b.storyId]?.completedAt ?? 0)
  )[0];
}
