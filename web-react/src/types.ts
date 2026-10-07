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
  | "sentence_order"
  | "matching"
  | "reading"
  | "clinical_case";

export interface Exercise {
  id: number;
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
