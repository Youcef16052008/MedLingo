/** Shapes des données extraites des seeds Kotlin (scripts/extract.mjs). */

export interface Term {
  id: number;
  en: string;
  fr: string;
  ar: string;
  defEn: string;
  defFr: string;
  defAr: string;
  etym: string;
  pearl: string;
  mnemo: string;
  module: string;
  chapter: string;
  exEn: string;
  exFr: string;
  exAr: string;
  ipa: string;
}

export type ExerciseType =
  | "mcq"
  | "fill_blank"
  | "gap"
  | "sentence_order"
  | "matching"
  | "pair"
  | "reading"
  | "clinical_case";

/** Types réellement rendus par QuizScreen — contrat corpus ↔ UI (testé). */
export const RENDERABLE_EXERCISE_TYPES: readonly ExerciseType[] = [
  "mcq",
  "fill_blank",
  "gap",
  "sentence_order",
  "matching",
  "pair",
  "reading",
  "clinical_case",
];

export interface Exercise {
  /** Les corpus générés (v2, 13k) utilisent des ids textuels — leçon : ids négatifs synthétiques. */
  id: number | string;
  level: number;
  type: ExerciseType;
  module: string;
  chapter: string;
  qEn: string;
  qFr: string;
  qAr: string;
  options: string[];
  answer: string;
  expEn: string;
  expFr: string;
  expAr: string;
  points: number;
  ctxEn: string;
  ctxFr: string;
  ctxAr: string;
}

export type Lang = "fr" | "en" | "ar";
