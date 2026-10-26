/**
 * Parcours « module → chapitre → leçons » : 1 unité = 1 module, avec chapitres
 * et leçons triées de facile à dur.
 * Déblocage : une leçon est ouverte dès que la précédente est validée.
 */
import { MODULES, type Module } from "../data/modules";

export type NodeState = "locked" | "available" | "done";

export interface PathNode {
  moduleId: string;
  chapterId: string;
  lessonId: string;
  level: number;          // 1..6 (facile → dur)
  state: NodeState;
  score: number;
  isReview: boolean;
  isBoss: boolean;
}

export interface PathUnit {
  moduleId: string;
  module: Module;
  completed: number;
  nodes: PathNode[];
  chapters: PathChapter[];
}

export interface PathChapter {
  chapterId: string;
  lessons: PathNode[];
}
/** Construit les unités du parcours (module → chapitres → leçons). */
export function buildPath(
  lessonBest: Record<string, number> = {},
  moduleBest: Record<string, number> = {},
  modules = MODULES
): PathUnit[] {
  return modules.map((module) => {
    const nodes: PathNode[] = [];
    let previousDone = false;

    for (let level = 1; level <= LEVELS_PER_UNIT; level++) {
      const lessonScore = lessonBest[lessonKey(module.id, level)];
      const score = lessonScore ?? moduleBest[module.id] ?? 0;

      let state: NodeState;
      if ((lessonScore ?? 0) >= UNLOCK_SCORE) {
        state = "done";
      } else if (level === 1) {
        // La première leçon d'une unité est toujours ouverte (même avec score < 70).
        state = "available";
      } else {
        state = previousDone ? "available" : "locked";
      }

      nodes.push({
        moduleId: module.id,
        chapterId: "path",
        lessonId: lessonKey(module.id, level),
        level,
        state,
        score,
        isReview: false,
        isBoss: level === LEVELS_PER_UNIT,
      });
      previousDone = state === "done";
    }

    return {
      moduleId: module.id,
      module,
      completed: nodes.filter((n) => n.state === "done").length,
      nodes,
      chapters: [{ chapterId: "root", lessons: nodes }],
    };
  });
}
/** Première leçon jouable d'un chapitre (nœud « pulse »). */
export function nextLesson(unit: PathUnit, chapterIndex: number): PathNode | undefined {
  const chapter = unit.chapters[chapterIndex];
  if (!chapter) return undefined;
  return chapter.lessons.find((n) => n.state === "available") ?? chapter.lessons[0];
}
/** Leçon actuelle d'un chapitre (pour navigation arrière/avant). */
export function currentLesson(
  unit: PathUnit,
  chapterIndex: number,
  lessonIndex: number
): PathNode | undefined {
  const chapter = unit.chapters[chapterIndex];
  if (!chapter) return undefined;
  return chapter.lessons[lessonIndex];
}
/** Passe à la leçon suivante dans le chapitre courant. */
export function nextChapterLesson(
  unit: PathUnit,
  chapterIndex: number,
  lessonIndex: number
): PathNode | undefined {
  const chapter = unit.chapters[chapterIndex];
  if (!chapter) return undefined;
  const next = lessonIndex + 1;
  if (next < chapter.lessons.length) return chapter.lessons[next];
  return undefined;
}
/** Passe au chapitre précédent. */
export function prevChapter(unit: PathUnit, chapterIndex: number): PathChapter | undefined {
  if (chapterIndex <= 0) return undefined;
  return unit.chapters[chapterIndex - 1];
}

/** Genere une clé unique pour une leçon à partir de moduleId et niveau. */
export function lessonKey(moduleId: string, level: number): string {
  return `${moduleId}:${level}`;
}

/** Nombre de leçons par unité. */
export const LEVELS_PER_UNIT = 6;

/** Score minimal pour débloquer une leçon. */
export const UNLOCK_SCORE = 70;