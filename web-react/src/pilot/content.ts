/**
 * P1.1 — Façade typée du schéma éditorial pilote.
 *
 * La logique de validation vit dans `scripts/pilot-schema.mjs` (importable à
 * la fois par le CLI `node` et par vitest — pattern `exercise-quality.mjs`).
 * Ce module fournit les helpers typés utilisés par l'app (P2) et les tests.
 *
 * ⚠️ SANS import Node (`node:fs`/`node:path`) : ce module est importé par
 * l'écran pilote et doit rester bundleable côté navigateur. Le chargement du
 * manifeste se fait par import statique JSON à l'écran (précache implicite).
 */
import {
  validatePilotManifest as validateUntyped,
  validatePilotMeasureManifest as validateMeasureUntyped,
  answerVisibleIn as answerVisibleUntyped,
} from "../../scripts/pilot-schema.mjs";
import type {
  PilotConcept,
  PilotExercise,
  PilotManifest,
} from "./types";
import type { MeasureManifest } from "./measure";

export interface PilotReviewInput {
  knownTermIds?: Set<number>;
}

export interface PilotValidationReport {
  ok: boolean;
  errors: string[];
  warnings: string[];
}

export function validatePilotManifest(
  manifest: PilotManifest,
  input: PilotReviewInput = {},
): PilotValidationReport {
  return validateUntyped(manifest, input) as PilotValidationReport;
}

/**
 * P3.1 — Validation des banques de mesure (façade typée). Cross-référence
 * avec les concepts de l'entraînement : un item de mesure sur un concept
 * inconnu est une erreur bloquante.
 */
export function validatePilotMeasureManifest(
  measure: MeasureManifest,
  training: PilotManifest,
): PilotValidationReport {
  const conceptIds = new Set(training.concepts.map((c) => c.id));
  return validateMeasureUntyped(measure, { conceptIds, expectedVersions: ["A", "B", "C"] }) as PilotValidationReport;
}

/** La réponse apparaît-elle comme mot entier dans ce texte ? (fuite P0.3) */
export function answerVisibleIn(text: string, answer: string): boolean {
  return answerVisibleUntyped(text, answer);
}

/**
 * Verrou de publication pilote (miroir du verrou d'histoires P0.4) :
 * un concept n'est présentable que si les DEUX relectures humaines couvrent
 * la version courante ET que le statut éditorial est `approved`.
 * Aucune exception, pas de contournement URL : la décision appartient au
 * chargeur, jamais au contenu.
 */
export function isConceptPublishable(concept: PilotConcept): boolean {
  if (concept.status !== "approved") return false;
  const covers = (r: { status?: string; reviewedContentVersion?: string } | undefined) =>
    r?.status === "done" && r?.reviewedContentVersion === concept.contentVersion;
  return covers(concept.reviews?.linguistic) && covers(concept.reviews?.medical);
}

/** Exercices présentables d'un concept (le verrou s'applique en amont). */
export function presentableExercises(concept: PilotConcept): PilotExercise[] {
  if (!isConceptPublishable(concept)) return [];
  return concept.exercises;
}

