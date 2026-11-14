#!/usr/bin/env node
/**
 * check:pilot — valide les manifestes du pilote Anatomie :
 *  - entraînement (P1.1) : data/pilot-anatomy.v1.json ;
 *  - mesure (P3.1) : data/pilot-anatomy-measure.v1.json — versions parallèles
 *    A/B/C (T0/T1/T2) et MÊME contentVersion que l'entraînement (toute
 *    modification de contenu invalide les banques de mesure : pas de
 *    survivance silencieuse, même règle que les concepts approuvés).
 * Bloquant : exit 1 sur toute erreur certaine. Exit 0 avec avertissements sinon.
 */
import { readFileSync, existsSync } from "node:fs";
import { join, dirname } from "node:path";
import { fileURLToPath } from "node:url";
import { validatePilotManifest, validatePilotMeasureManifest } from "./pilot-schema.mjs";

const here = dirname(fileURLToPath(import.meta.url));
const trainingPath = process.argv[2] ?? join(here, "..", "data", "pilot-anatomy.v1.json");
const measurePath = process.argv[3] ?? join(here, "..", "data", "pilot-anatomy-measure.v1.json");

const training = JSON.parse(readFileSync(trainingPath, "utf8"));
const terms = JSON.parse(readFileSync(join(here, "..", "public", "data", "terms.json"), "utf8"));
const knownTermIds = new Set(terms.map((t) => t.id));

console.log(
  `[check-pilot] entraînement : ${trainingPath} — ${training.concepts?.length ?? 0} concepts, contentVersion ${training.contentVersion}`,
);
const report = validatePilotManifest(training, { knownTermIds });

let measureReport = null;
if (existsSync(measurePath)) {
  const measure = JSON.parse(readFileSync(measurePath, "utf8"));
  const conceptIds = new Set(training.concepts.map((c) => c.id));
  console.log(
    `[check-pilot] mesure : ${measurePath} — ${measure.banks?.length ?? 0} banques, ${measure.versions?.join(", ") ?? "?"}`,
  );
  measureReport = validatePilotMeasureManifest(measure, {
    conceptIds,
    expectedVersions: ["A", "B", "C"],
  });
  if (measure.contentVersion !== training.contentVersion) {
    measureReport.errors.push(
      `contentVersion mesure ${JSON.stringify(measure.contentVersion)} ≠ entraînement ${JSON.stringify(training.contentVersion)} — banques à regénérer/revalider`,
    );
    measureReport.ok = false;
  }
} else {
  console.log(`[check-pilot] mesure : ${measurePath} absent — banques P3.1 non générées (documenter).`);
}

const warnings = [...report.warnings, ...(measureReport?.warnings ?? [])];
const errors = [...report.errors, ...(measureReport?.errors ?? [])];
for (const w of warnings) console.log(`  ~ AVERTISSEMENT : ${w}`);
if (errors.length > 0) {
  console.error(`[check-pilot] ERREURS CERTAINES (${errors.length}) :`);
  for (const e of errors) console.error(`  - ${e}`);
  console.error("[check-pilot] BLOQUANT.");
  process.exit(1);
}
console.log("[check-pilot] OK — schémas éditorial P1.1 et mesure P3.1 respectés.");
