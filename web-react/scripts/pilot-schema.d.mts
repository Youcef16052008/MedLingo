/** Déclarations pour le validateur du manifeste pilote (implémentation .mjs). */
export declare function norm(s: unknown): string;
export declare function answerVisibleIn(text: unknown, answer: unknown): boolean;
export interface PilotSchemaReport {
  ok: boolean;
  errors: string[];
  warnings: string[];
}
export declare function validatePilotManifest(
  manifest: unknown,
  options?: { knownTermIds?: Set<number> | null },
): PilotSchemaReport;

/** P3.1 — Banques de mesure (fichier séparé de l'entraînement). */
export declare function validatePilotMeasureManifest(
  manifest: unknown,
  options?: { conceptIds?: Set<string> | null; expectedVersions?: string[] | null },
): PilotSchemaReport;
