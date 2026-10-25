/**
 * Modèle « histoire » du mode narration — guide
 * `docs/GUIDE_INTEGRATION_APPRENTISSAGE_MEDLINGO.md` §6.1.
 *
 * Source partagée Android/web : `app/src/main/assets/learning/stories.v1.json`
 * (copie identique servie par le web via `public/data/stories.v1.json`).
 * Ne pas dupliquer manuellement le contenu en Kotlin ou TS : toute évolution
 * passe par le JSON versionné.
 */

/** Texte localisé — consignes et aides, jamais la langue cible (anglais). */
export interface LText {
  en: string;
  fr: string;
  ar: string;
}

/** Une phrase du récit : anglais (cible) + aides FR/AR à la demande. */
export interface StorySegment {
  en: string;
  fr: string;
  ar: string;
}

/** Expression ciblée — unité de la mémoire d'expressions (SM-2 séparé). */
export interface TargetPhrase {
  /** Id stable partagé entre histoires (`en:have-headache:v1`). */
  id: string;
  en: string;
  fr: string;
  ar: string;
}

/**
 * Question de circling : même fait revisité sous plusieurs formes
 * (oui/non, alternative, ouverte). `accepted` contient des réponses
 * courtes valides — une réponse d'un mot suffit quand le sens est bon.
 */
export interface StoryQuestion {
  id: string;
  /** Question en anglais (langue cible). */
  q: string;
  /** Aide facultative : traduction de la question. */
  qFr?: string;
  qAr?: string;
  /** Réponses acceptées (comparaison normalisée, égalité stricte). */
  accepted: string[];
  /** Réponse modèle complète, révélée après la tentative. */
  model: string;
  /** Feedback affiché en cas d'erreur — préserve le fait critique. */
  hint?: LText;
  /** Expressions marquées difficiles si la question est ratée. */
  phraseIds?: string[];
}

/** Transformation guidée : un seul point de langue par séance. */
export interface DrillItem {
  id: string;
  /** Phrase à compléter (anglais, trou « … »). */
  prompt: string;
  accepted: string[];
  model: string;
}

export interface Drill {
  explain: LText;
  items: DrillItem[];
}

/** Production guidée (reformulation ou jeu de rôle sans micro). */
export interface RetellSpec {
  prompt: LText;
  starters: string[];
  model: string;
}

/** Transfert : mêmes structures, autre situation — empêche la récitation. */
export interface TransferSpec {
  context: LText;
  gloss?: LText;
  questions: StoryQuestion[];
  task: RetellSpec;
}

export interface Story {
  schemaVersion: number;
  storyId: string;
  contentVersion: number;
  title: LText;
  goal: LText;
  moduleIds: string[];
  relatedTermIds: number[];
  languageBand: string;
  publicationStatus: "draft" | "published";
  durationMin: number;
  review: { language: string; medical: string };
  /** Traçabilité éditoriale (ex. "generated:corpus-v1") — ignoré par les chargeurs. */
  provenance?: string;
  segments: StorySegment[];
  targetPhrases: TargetPhrase[];
  questions: StoryQuestion[];
  drill: Drill;
  retell: RetellSpec;
  transfer: TransferSpec;
}

export interface StoryFile {
  schemaVersion: number;
  stories: Story[];
}
