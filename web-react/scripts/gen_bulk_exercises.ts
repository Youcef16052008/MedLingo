#!/usr/bin/env node
/**
 * Génère les 13 512 exercices (3 par terme) en utilisant le générateur TypeScript existant
 * et écrit le résultat dans public/data/exercises.json.
 *
 * Usage: npx tsx scripts/gen_bulk_exercises.ts
 */

import { writeFileSync } from "fs";
import { join, dirname } from "path";
import { fileURLToPath } from "url";

const __filename = fileURLToPath(import.meta.url);
const __dirname = dirname(__filename);
const ROOT = join(__dirname, "..");
const EXERCISES_PATH = join(ROOT, "public", "data", "exercises.json");

// Import the bulk generator
import { generateBulkExercises, resetBulkCache } from "../src/domain/bulkExercises.ts";

async function main(): Promise<void> {
  console.log("Génération des exercices en masse (TypeScript)…");

  // Reset cache et generate
  resetBulkCache();

  // Warm le cache module par module (asynchrone)
  const { warmBulkCache } = await import("../src/domain/bulkExercises.ts");
  warmBulkCache();

  // Attend la fin du chauffage
  // (Le chargement est déjà fait, donc le cache est rempli)

  // Récupère le résultat
  const exercises = generateBulkExercises();

  console.log(`  ${exercises.length} exercices générés`);

  // Vérifie les types
  const types = new Set(exercises.map((e: { type: string }) => e.type));
  console.log(`  Types : ${Array.from(types).join(", ")}`);

  // Écrit le résultat
  writeFileSync(EXERCISES_PATH, JSON.stringify(exercises, null, 2));

  console.log(`✅ Écrit ${EXERCISES_PATH}`);
}

main().catch((err) => {
  console.error("Erreur :", err);
  process.exit(1);
});
