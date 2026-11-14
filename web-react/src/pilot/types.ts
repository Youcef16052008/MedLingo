/**
 * P1.1 — Schéma éditorial du pilote Anatomie.
 *
 * Chaque concept porte : id stable, référence historique facultative
 * (`legacyRef` = id de terme d'origine), unité et ordre, libellé EN + aides
 * FR/AR, objectif de langue, définition simple + exemple avec aides FR/AR,
 * statut draft/approved, version de contenu, relectures linguistique et
 * anatomique (statut, personne RÉELLE, date, version relue, références),
 * exercices d'entraînement séparés des évaluations, média facultatif avec
 * licence/attribution/provenance.
 *
 * Règle d'honnêteté (plan P1.2) : tout contenu produit automatiquement est
 * `draft`. `approved` n'existe que si les DEUX relectures humaines couvrent
 * la version courante — c'est imposé par `content.ts`, pas par l'honneur
 * des générateurs.
 */

export type PilotReviewStatus = "pending" | "done";

export interface PilotReview {
  status: PilotReviewStatus;
  /** Personne réelle — jamais générée. Requis si status === "done". */
  reviewer?: string;
  /** Date ISO de la relecture. Requis si status === "done". */
  date?: string;
  /** Version de contenu exacte relue. Requis si status === "done". */
  reviewedContentVersion?: string;
  /** Références vérifiables (sources anatomiques/linguistiques), facultatif. */
  references?: string[];
  notes?: string;
}

/** Matrice P1.3 : reconnaissance, compréhension en contexte, rappel. */
export type PilotExerciseObjective = "recognize" | "understand" | "recall";

/** Rôle : entraînement (practice) séparé de l'évaluation (assessment). */
export type PilotExerciseRole = "practice" | "assessment";

export type PilotExerciseKind = "mcq" | "free_input";

export interface PilotLocalizedText {
  en: string;
  fr: string;
  ar: string;
}

export interface PilotExercise {
  /** Unique dans TOUT le manifeste, stable. */
  id: string;
  conceptId: string;
  objective: PilotExerciseObjective;
  role: PilotExerciseRole;
  kind: PilotExerciseKind;
  /** Consignes localisées. */
  prompt: PilotLocalizedText;
  /** `mcq` uniquement — la réponse DOIT figurer dans les options. */
  options?: string[];
  /** Réponse attendue ; variantes légitimes listées explicitement. */
  answer: string;
  acceptedVariants?: string[];
  explanation: PilotLocalizedText;
}

export interface PilotMedia {
  /** Licence explicite — jamais « généré par IA » présenté comme référence validée. */
  license: string;
  attribution: string;
  source: string;
}

export type PilotConceptStatus = "draft" | "approved";

export interface PilotConcept {
  /** Stable, unique, lisible : `anatomy-u{unit}-{slug}`. */
  id: string;
  unit: 1 | 2 | 3;
  /** Ordre dans l'unité (1-based, unique). */
  order: number;
  /** Référence historique facultative : id du terme d'origine (terms.json). */
  legacyRef?: number;
  labelEn: string;
  /** Aides FR/AR. */
  hints: { fr: string; ar: string };
  languageObjective: string;
  definition: PilotLocalizedText;
  example?: PilotLocalizedText;
  /** Variantes acceptées explicites (ex. « Spine » / « Backbone »). */
  acceptedVariants?: string[];
  /** Justification fréquence/utilité — hypothèse À FAIRE EXAMINER (P1.2). */
  selectionNote: string;
  status: PilotConceptStatus;
  contentVersion: string;
  reviews: { linguistic: PilotReview; medical: PilotReview };
  exercises: PilotExercise[];
  media?: PilotMedia[];
}

export interface PilotManifest {
  schemaVersion: 1;
  /** Identifiant du pilote, stable : `pilot-anatomy`. */
  id: string;
  /** Version de contenu globale — toute modification de contenu la bump. */
  contentVersion: string;
  generatedAt: string;
  /** Provenance : script + entrée + seed, pour rejouer la génération. */
  generatedFrom: string;
  concepts: PilotConcept[];
}
