/**
 * Chargeur des histoires (`public/data/stories.v1.json`).
 *
 * Miroir web du futur lecteur d'assets Android : même fichier, mêmes ids.
 * Un contenu `draft` n'est jamais servi aux étudiants (§6.1 du guide) —
 * le filtre s'applique ici comme il devra s'appliquer côté Android.
 */

import type { Story, StoryFile } from "./types";

/** Versions du fichier d'histoires comprises par ce client (RISK-008). */
export const SUPPORTED_STORIES_SCHEMA = 1;

let stories: Story[] = [];
let promise: Promise<void> | null = null;
let failed = false;
/** Le chargement a-t-il abouti (même vide : tout en draft) ou échoué ? */
let settled = false;

/** Valide la version du fichier ; une version inconnue est un échec franc,
 *  pas un contenu partiellement interprété. */
function ingest(raw: unknown): Story[] {
  const data = raw as StoryFile | null;
  if (!data || typeof data !== "object" || data.schemaVersion !== SUPPORTED_STORIES_SCHEMA) {
    throw new Error(
      `stories.v1.json : schemaVersion ${String((data as StoryFile | null)?.schemaVersion)} non supporté (attendu ${SUPPORTED_STORIES_SCHEMA})`
    );
  }
  return (data.stories ?? []).filter((s) => s.publicationStatus === "published");
}

async function fetchJson<T>(path: string): Promise<T> {
  const res = await fetch(path);
  if (!res.ok) throw new Error(`${path} → HTTP ${res.status}`);
  return (await res.json()) as T;
}

export function loadStories(): Promise<void> {
  if (!promise) {
    failed = false;
    settled = false;
    promise = (async () => {
      const data = await fetchJson<StoryFile>("/data/stories.v1.json");
      stories = ingest(data);
      settled = true;
    })().catch((e: unknown) => {
      // Échec définitif (hors-ligne sans cache, 404) : la carte « Ma séance »
      // reste masquée ; un lien direct affiche l'écran d'erreur honnête.
      promise = null;
      failed = true;
      settled = true;
      throw e;
    });
  }
  return promise;
}

/** Branche les histoires directement depuis la mémoire (tests Node).
 *  Lève sur version non supportée — même contrat que le chargement réseau. */
export function setStories(data: StoryFile): void {
  stories = ingest(data);
  failed = false;
  settled = true;
}

export const getStories = (): Story[] => stories;

export function storyById(id: string): Story | undefined {
  return stories.find((s) => s.storyId === id);
}

export const isStoryLoaded = (): boolean => stories.length > 0;

/** Le dernier chargement a-t-il échoué ? (distingue « pas encore chargé » d'« absent ») */
export const storiesFailed = (): boolean => failed;

/** Chargement terminé (réussi ou échoué) : au-delà, un id absent est réellement inconnu. */
export const storiesSettled = (): boolean => settled;
