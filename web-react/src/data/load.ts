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
