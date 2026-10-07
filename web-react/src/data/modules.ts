/** Miroir de `InitialData.modulesList` (Kotlin) — 15 modules PCM1. */
import type { Lang } from "../types";

export interface Module {
  id: string;
  icon: string;
  fr: string;
  en: string;
  ar: string;
  color: string;
}

export const MODULES: Module[] = [
  { id: "anat", icon: "🦴", fr: "Anatomie", en: "Anatomy", ar: "علم التشريح البشري", color: "#1B5E20" },
  { id: "physio", icon: "❤️", fr: "Physiologie", en: "Physiology", ar: "علم وظائف الأعضاء", color: "#00695C" },
  { id: "biochim", icon: "🧬", fr: "Biochimie", en: "Biochemistry", ar: "الكيمياء الحيوية الطبية", color: "#1565C0" },
  { id: "histo", icon: "🔬", fr: "Histologie", en: "Histology", ar: "علم الأنسجة العام", color: "#6A1B9A" },
  { id: "biophys", icon: "🧪", fr: "Biophysique", en: "Biophysics", ar: "الفيزياء الحيوية الطبية", color: "#E65100" },
  { id: "genet", icon: "🧬", fr: "Génétique", en: "Medical Genetics", ar: "علم الوراثة الطبية", color: "#004D40" },
  { id: "termino", icon: "📙", fr: "Terminologie Médicale", en: "Medical Terminology", ar: "المصطلحات الطبية", color: "#E65100" },
  { id: "clinical_en", icon: "🩺", fr: "Anglais Médical", en: "Clinical English", ar: "الإنجليزية الطبية السريرية", color: "#00695C" },
  { id: "cytol", icon: "🧫", fr: "Cytologie", en: "Cytology", ar: "علم الأحياء الخلوية", color: "#00796B" },
  { id: "info_med", icon: "💻", fr: "Informatique Médicale", en: "Medical Informatics", ar: "المعلوماتية الطبية", color: "#1976D2" },
  { id: "embryo", icon: "👶", fr: "Embryologie", en: "Embryology", ar: "علم الأجنة البشرية", color: "#C2185B" },
  { id: "microbio", icon: "🦠", fr: "Microbiologie", en: "Microbiology", ar: "علم الأحياء الدقيقة", color: "#00897B" },
  { id: "pharmaco", icon: "💊", fr: "Pharmacologie", en: "Pharmacology", ar: "علم الأدوية", color: "#7B1FA2" },
  { id: "semio", icon: "🩺", fr: "Sémiologie Médicale", en: "Semiology", ar: "علم الأعراض والتشخيص", color: "#0288D1" },
  { id: "anapath", icon: "🫀", fr: "Anatomie Pathologique", en: "Pathology", ar: "علم الأمراض التشريحي", color: "#C2185B" },
];

export const modById = (id: string | undefined): Module | undefined =>
  MODULES.find((m) => m.id === id);

/** Nom de module localisé à partir de sa valeur française (celle stockée dans les données). */
export const modLabel = (frName: string, lang: Lang): string => {
  const m = MODULES.find((x) => x.fr === frName);
  return m ? m[lang] : frName;
};
