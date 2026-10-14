import type { Exercise, Term } from "../types";
import { MODULES } from "./modules";

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
const dataListeners = new Set<() => void>();

/** Abonne une invalidation de cache au changement de données. */
export function onDataChanged(fn: () => void): () => void {
  dataListeners.add(fn);
  return () => dataListeners.delete(fn);
}

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
  byModule = new Map(MODULES.map((m) => [m.id, data.filter((x) => x.module === m.fr)]));
  emitDataChanged();
}
