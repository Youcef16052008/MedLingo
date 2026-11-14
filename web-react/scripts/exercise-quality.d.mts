/** Déclarations pour l'auditeur de qualité des exercices (implémentation .mjs). */
export interface ExerciseQualityEntry {
  id: string;
  type: string;
  rule: string;
  lang?: string;
  detail: string;
}
export interface ExerciseQualityReport {
  certain: ExerciseQualityEntry[];
  suspicious: ExerciseQualityEntry[];
  summary: Record<string, number>;
  total: number;
  ok: boolean;
}
export declare function auditExercises(
  exercises: Array<Record<string, unknown>>,
): ExerciseQualityReport;
