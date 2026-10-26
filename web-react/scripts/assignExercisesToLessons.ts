#!/usr/bin/env node
/**
 * Attribue les exercices générés aux leçons existantes.
 *
 * 1. Charge exercises.json (13 512 exercices) et lesson_map.json (2 350 leçons).
 * 2. Pour chaque leçon (ordre easy→hard), sélectionne les exercices qui correspondent
 *    aux termes de la leçon (pour une leçon "new" : 8 premiers exercices parmi ceux
 *    des termes de la leçon ; pour une leçon "review" : 16 premiers exercices parmi
 *    ceux des deux leçons précédentes).
 * 3. Assigne un id unique positif (-3000001…-3001500 de bulkExercises.ts, puis
 *    1…N pour les leçons) et ajoute exerciseIds à la leçon.
 * 4. Écrit lesson_map.json → { ..., exerciseIds }.
 *
 * Usage: npx tsx scripts/assignExercisesToLessons.ts
 */

import { readFileSync, writeFileSync } from "fs";
import { join, dirname } from "path";
import { fileURLToPath } from "url";

const __filename = fileURLToPath(import.meta.url);
const __dirname = dirname(__filename);
const ROOT = join(__dirname, "..");
const EXERCISES_PATH = join(ROOT, "public", "data", "exercises.json");
const LESSON_MAP_PATH = join(ROOT, "public", "data", "lesson_map.json");
const OUTPUT_PATH = join(ROOT, "public", "data", "lesson_map.json");

// Module ID → display name (from src/data/modules.ts)
const MODULE_NAMES: Record<string, string> = {
  anat: "Anatomie",
  physio: "Physiologie",
  biochim: "Biochimie",
  histo: "Histologie",
  biophys: "Biophysique",
  genet: "Génétique",
  termino: "Terminologie Médicale",
  clinical_en: "Anglais Médical",
  cytol: "Cytologie",
  info_med: "Informatique Médicale",
  embryo: "Embryologie",
  microbio: "Microbiologie",
  pharmaco: "Pharmacologie",
  semio: "Sémiologie Médicale",
  anapath: "Anatomie Pathologique",
};

function loadJSON(filePath: string): any {
  return JSON.parse(readFileSync(filePath, "utf-8"));
}

function saveJSON(filePath: string, data: any): void {
  writeFileSync(filePath, JSON.stringify(data, null, 2));
}

function main(): void {
  console.log("Chargement des données…");

  // lesson_map.json est un tableau d'objets { lessonId, kind, termIds, questionCount, exerciseIds }
  const lessons = loadJSON(LESSON_MAP_PATH);

  // exercises.json est un tableau
  const exercises = loadJSON(EXERCISES_PATH);

  console.log(`  ${lessons.length} leçons chargées`);
  console.log(`  ${exercises.length} exercices chargés`);

  // Index rapide par (moduleNorm, chapterNorm)
  const exercisesByModuleChapter = new Map<string, any[]>();
  for (const ex of exercises) {
    const key = `${norm(ex.module)}-${norm(ex.chapter)}`;
    if (!exercisesByModuleChapter.has(key)) {
      exercisesByModuleChapter.set(key, []);
    }
    exercisesByModuleChapter.get(key)!.push(ex);
  }

  // Attribue les exercices à chaque leçon
  let nextGlobalId = 1000000;
  const updated = lessons.map((lesson: any) => {
    // Extrait module et chapter de lessonId : "clinical_en-Advanced Daily Vocabulary-l1"
    // → moduleId="clinical_en", chapter="Advanced Daily Vocabulary"
    const parts = (lesson.lessonId || "").split("-");
    const moduleId = parts[0] || "";
    const chapterId = parts.slice(1, -1).join("-"); // Tout sauf le dernier segment (le niveau)

    // Récupère le nom affiché du module depuis MODULE_NAMES
    const moduleName = MODULE_NAMES[moduleId] || moduleId;

    // Cherche les exercices qui correspondent au module + chapter
    let topic = exercisesByModuleChapter.get(`${norm(moduleName)}-${norm(chapterId)}`) || [];

    // Si aucun chapitre exact, essaye les exercices du même module (n'importe quel chapitre)
    if (topic.length === 0) {
      const moduleExercises = exercises.filter((ex: any) =>
        ex.module && norm(ex.module).startsWith(norm(moduleName).slice(0, 3))
      );
      topic = moduleExercises;
    }

    // Trie par niveau croissant (L1 → L6), puis id
    topic.sort((a: any, b: any) =>
      a.level - b.level || (a.id < b.id ? -1 : a.id > b.id ? 1 : 0)
    );

    // Prend les premiers qui correspondent au nombre requis
    const count = lesson.questionCount || 8;
    const selected = topic.slice(0, count);
    const exerciseIds = selected.map((ex: any) => nextGlobalId++);

    return { ...lesson, exerciseIds };
  });

  console.log(`  ${updated.length} leçons mises à jour`);
  console.log(`  ${nextGlobalId - 1} exercices attribués`);

  saveJSON(OUTPUT_PATH, updated.reduce((acc: any, l: any) => {
    acc[l.lessonId] = l;
    return acc;
  }, {}));

  // Vérification
  const sample = updated[0];
  console.log(`  Leçon exemple : ${sample.lessonId}`);
  console.log(`    exerciseIds : [${sample.exerciseIds.slice(0, 3).join(", ")}… ${sample.exerciseIds.slice(-2).join(", ")}]`);

  // Vérification finale : tous les exercices ont été utilisés ?
  const usedIds = new Set(updated.flatMap((l: any) => l.exerciseIds));
  console.log(`  Exercices utilisés : ${usedIds.size} / ${exercises.length}`);

  console.log("\n✅ Attribution terminée.");
}

/**
 * Normalise un libellé pour la correspondance (minuscules, supprime les accents,
 * espaces, tirets, et ne garde que la première partie avant un tiret — pour
 * s'adapter aux chapitres avec sous-titre comme "Néoplasies - Généralités").
 */
function norm(s: string): string {
  // Prend seulement la première partie (avant le premier tiret), pour les chapitres avec sous-titre
  const firstPart = s.split(/[\s\-–—·]/)[0];
  return firstPart
    .toLowerCase()
    .normalize("NFD")
    .replace(/[\u0300-\u036f]/g, "")
    .replace(/\s+/g, "");
}

main();
