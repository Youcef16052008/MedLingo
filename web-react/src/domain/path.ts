/**
 * Parcours « chemin vert » : 1 unité = 1 module = 6 leçons (L1 → L6, L6 = BOSS).
 * Déblocage : une leçon est ouverte dès que la précédente est validée à ≥ 70 %.
 */
import { MODULES, type Module } from "../data/modules";

export type NodeState = "locked" | "available" | "done";

export interface PathNode {
  moduleId: string;
  level: number;
  state: NodeState;
  score: number;
  isBoss: boolean;
}

export interface PathUnit {
  module: Module;
  nodes: PathNode[];
  /** Nombre de leçons validées (≥ 70 %). */
  completed: number;
}

/** Seuil de validation d'une leçon (%). */
export const UNLOCK_SCORE = 70;
export const LEVELS_PER_UNIT = 6;

/** Clé de stockage d'une leçon dans `AppState.lessonBest`. */
export function lessonKey(moduleId: string, level: number): string {
  return `${moduleId}:${level}`;
}

/**
 * Construit les unités du parcours.
 * `lessonBest` : clé `module:level` → meilleur score.
 * `moduleBest` : module → meilleur score (non requis pour le déblocage, affichage seul).
 */
export function buildPath(
  lessonBest: Record<string, number> = {},
  moduleBest: Record<string, number> = {},
  modules: Module[] = MODULES
): PathUnit[] {
  return modules.map((module) => {
    const nodes: PathNode[] = [];
    let previousDone = false;

    for (let level = 1; level <= LEVELS_PER_UNIT; level++) {
      const lessonScore = lessonBest[lessonKey(module.id, level)];
      const score = lessonScore ?? moduleBest[module.id] ?? 0;
      let state: NodeState;
      // Le déblocage regarde uniquement la leçon : `moduleBest` ne sert qu'à l'affichage
      // (une leçon à ≥ 70 % ne valide pas les autres leçons du module).
      if ((lessonScore ?? 0) >= UNLOCK_SCORE) state = "done";
      else if (level === 1) state = "available"; // la première leçon d'une unité est toujours ouverte
      else state = previousDone ? "available" : "locked";

      nodes.push({ moduleId: module.id, level, state, score, isBoss: level === LEVELS_PER_UNIT });
      previousDone = state === "done";
    }

    return {
      module,
      nodes,
      completed: nodes.filter((n) => n.state === "done").length,
    };
  });
}

/** Première leçon jouable d'une unité (le nœud « pulse » du parcours). */
export function nextLesson(unit: PathUnit): PathNode | undefined {
  return unit.nodes.find((n) => n.state === "available");
}
