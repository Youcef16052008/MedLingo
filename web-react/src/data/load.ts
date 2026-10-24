/** Infos dérivées du corpus : chapitres + mapping leçons. */

import type { Term, Exercise } from "../types";
import { MODULES } from "./modules";
import type { Module } from "./modules";

// -----------------------------------------------------------------------
// Chargement des données de parcours (chapters.json, lesson_map.json)
// -----------------------------------------------------------------------

let chapters: ChapterInfo[] = [];
let lessonMap: LessonMap = {};
let chapterPromise: Promise<void> | null = null;

export interface ChapterInfo {
  id: string;
  moduleId: string;
  termIds: number[];
  modules: LessonEntry[];
  name: string;
}

export interface LessonEntry {
  id: string;
  kind: "new" | "review";
  level: number;
  termIds: number[];
  questionCount: number;
  reviewedLessonIds?: string[];
}

export interface LessonMap {
  [lessonId: string]: LessonEntry | LessonMapEntry;
}

export interface LessonMapEntry {
  lessonId: string;
  id: string;
  kind: "new" | "review";
  termIds: number[];
  questionCount: number;
  reviewedLessonIds?: string[];
}

export function loadLessonData(): Promise<void> {
  if (!chapterPromise) {
    chapterPromise = (async () => {
      console.log("[load.ts] loadLessonData: start fetch chapters.json");
      const [chaptersData, lessonMapData] = await Promise.all([
        fetchJson<ChapterInfo[]>("/data/chapters.json"),
        fetchJson<LessonMap>("/data/lesson_map.json"),
      ]);
      console.log("[load.ts] loadLessonData: fetch OK, chapters loaded:", chaptersData.length);
      chapters = chaptersData;
      lessonMap = lessonMapData;
      emitDataChangedReview();
    })().catch((e: unknown) => {
      console.error("[load.ts] loadLessonData: FETCH FAILED:", e);
      chapterPromise = null;
      throw e;
    });
  }
  return chapterPromise;
}

export function getChapters(): ChapterInfo[] {
  return chapters;
}

export function getLessonMap(): LessonMap {
  return lessonMap;
}

export function getLessonEntry(lessonId: string): LessonEntry | undefined {
  return lessonMap[lessonId] as LessonEntry | undefined;
}

// Re-abonnement pour invalider les caches de parcours
export const dataListenersReview = new Set<() => void>();

export function onDataChangedReview(fn: () => void): () => void {
  dataListenersReview.add(fn);
  return () => dataListenersReview.delete(fn);
}

function emitDataChangedReview(): void {
  for (const fn of dataListenersReview) fn();
}

// Invalidation partagée
const dataListeners = new Set<() => void>();

/** Abonne une invalidation de cache au changement de données. */
export function onDataChanged(fn: () => void): () => void {
  dataListeners.add(fn);
  return () => dataListeners.delete(fn);
}

onDataChanged(() => {
  // Cache des termes/exercices seulement ; le parcours (chapters/lessonMap)
  // est alimenté par `loadLessonData()` indépendamment et ne doit pas être
  // effacé quand le corpus source change.
  // chapters = [];
  // lessonMap = {};
  emitDataChangedReview();
});

/** Infos dérivées du corpus : chapitres + mapping leçons. */
/**
 * Chargement des données extraites des seeds Kotlin
 * (`npm run extract` régénère `public/data/*.json`).
 */

let terms: Term[] = [];
let exercises: Exercise[] = [];
let byId = new Map<number, Term>();
let byModule = new Map<string, Term[]>();
let promise: Promise<void> | null = null;

/**
 * Abonnés à prévenir quand les données sont (re)branchées.
 *
 * La génération du corpus d'exercices est mémoïsée (`bulkExercises.ts`) : si
 * `setTestData` remplace les termes sans invalider le cache, les tests — et
 * tout rechargement à chaud — liraient un corpus périmé. Chaque cache dérivé
 * s'abonne ici et se vide au changement de données.
 */
function emitDataChanged(): void {
  for (const fn of dataListeners) fn();
}

async function fetchJson<T>(path: string): Promise<T> {
  const res = await fetch(path);
  if (!res.ok) throw new Error(`${path} → HTTP ${res.status}`);
  return (await res.json()) as T;
}

export function loadOnce(): Promise<void> {
  if (!promise) {
    promise = (async () => {
      const [t, e] = await Promise.all([
        fetchJson<Term[]>("/data/terms.json"),
        fetchJson<Exercise[]>("/data/exercises.json"),
      ]);
      terms = t;
      exercises = e;
      byId = new Map(t.map((x) => [x.id, x]));
      byModule = new Map(MODULES.map((m) => [m.id, t.filter((x) => x.module === m.fr)]));
      emitDataChanged();
    })().catch((e: unknown) => {
      // Un échec ne doit pas rester caché dans la promesse : la prochaine
      // tentative (reload, reconnexion) doit pouvoir relancer le fetch (B26).
      promise = null;
      throw e;
    });
  }
  return promise;
}

export const getTerms = (): Term[] => terms;
export const getExercises = (): Exercise[] => exercises;
export const termById = (id: number): Term | undefined => byId.get(id);
export const termsOfModule = (moduleId: string): Term[] => byModule.get(moduleId) ?? [];

export const isLoaded = (): boolean => terms.length > 0;

/**
 * Branche les termes directement depuis la mémoire (sans fetch).
 *
 * Les tests Node ne peuvent pas `fetch("/data/terms.json")` (URL relative),
 * mais `termsOfModule` — utilisé par `generateBulkExercises` — doit être
 * fonctionnel. On reconstruit le même index que `loadOnce`.
 */
export function setTestData(data: Term[]): void {
  terms = data;
  byId = new Map(data.map((x) => [x.id, x]));
  byModule = new Map((MODULES as Module[]).map((m) => [m.id, data.filter((x) => x.module === m.fr)]));
  emitDataChanged();
}
