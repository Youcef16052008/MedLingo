#!/usr/bin/env node
/**
 * P3.1 — Construit les banques de MESURE du pilote Anatomie.
 *
 * Entrée : data/pilot-anatomy.v1.json (concepts déjà sélectionnés, P1.2)
 *          + public/data/terms.json (définitions FR/EN).
 * Sortie : data/pilot-anatomy-measure.v1.json — fichier SÉPARÉ du manifeste
 *          d'entraînement : un écran d'entraînement ne peut pas servir les
 *          items de mesure (ids disjoints, vérifié par test), et la mesure
 *          n'est jamais réinjectée dans la progression d'entraînement.
 *
 * Versions parallèles (P3.1) : A (T0), B (T1 — consigne différente, vocabulaire
 * ciblé comparable), C (T2 — version parallèle de T1). Le mapping temps→version
 * est FIXÉ ICI et reproduit dans src/pilot/measure.ts — ne pas les changer
 * séparément.
 *
 * LIMITATION ASSUMÉE (aucun contenu inventé) : la dimension « compréhension en
 * phrase nouvelle » exige des phrases NON TRAVAILLÉES composées humainement —
 * elle n'est PAS générée ici (le moteur la supporte ; la banque ne la contient
 * pas encore). Les phrases existantes (définitions/exemples) sont déjà servies
 * à l'entraînement : les réutiliser feindrait mesurer une compréhension neuve.
 *
 * Usage : node scripts/build_pilot_measure.mjs
 */

import { readFileSync, writeFileSync, mkdirSync } from "node:fs";
import { join, dirname } from "node:path";
import { fileURLToPath } from "node:url";
import { answerVisibleIn } from "./pilot-schema.mjs";

const here = dirname(fileURLToPath(import.meta.url));
const ROOT = join(here, "..");
const training = JSON.parse(readFileSync(join(ROOT, "data", "pilot-anatomy.v1.json"), "utf8"));
const terms = JSON.parse(readFileSync(join(ROOT, "public", "data", "terms.json"), "utf8"));
const termById = new Map(terms.map((t) => [t.id, t]));

const VERSIONS = ["A", "B", "C"];

/** Dérivation déterministe (FNV-1a) — même pattern que le manifeste d'entraînement. */
function hash01(s) {
  let h = 0x811c9dc5;
  for (let i = 0; i < s.length; i++) {
    h ^= s.charCodeAt(i);
    h = Math.imul(h, 0x01000193) >>> 0;
  }
  return (h >>> 0) / 0x100000000;
}

function shuffleDeterministic(list, key) {
  return [...list]
    .map((item, i) => ({ item, k: hash01(`${key}:${i}:${item}`) }))
    .sort((a, b) => a.k - b.k)
    .map((x) => x.item);
}

const MCQ = {
  defEn: (x) => ({
    en: `Which term matches this definition: '${x}'?`,
    fr: `Quel terme correspond à cette définition : '${x}' ?`,
    ar: `أي مصطلح يوافق هذا التعريف: '${x}'؟`,
  }),
  defFr: (x) => ({
    en: `Which term matches this French description: '${x}'?`,
    fr: `Quel terme correspond à cette description française : '${x}' ?`,
    ar: `أي مصطلح يوافق هذا الوصف الفرنسي: '${x}'؟`,
  }),
  blankDef: (x) => ({
    en: `Fill in the blank with the correct term: '${x}'`,
    fr: `Complétez par le terme correct : '${x}'`,
    ar: `أكمل الفراغ بالمصطلح الصحيح: '${x}'`,
  }),
};

const FREE = {
  fr: (x) => ({
    en: `Type the English term for: '${x}'`,
    fr: `Écrivez le terme anglais pour : '${x}'`,
    ar: `اكتب المصطلح الإنجليزي لـ: '${x}'`,
  }),
  defEn: (x) => ({
    en: `Type the term that matches this definition: '${x}'`,
    fr: `Écrivez le terme qui correspond à cette définition : '${x}'`,
    ar: `اكتب المصطلح الذي يوافق هذا التعريف: '${x}'`,
  }),
};

function blanked(text, term) {
  if (!text) return null;
  return text.replace(new RegExp(term.replace(/[.*+?^${}()|[\]\\]/g, "\\$&"), "gi"), "______");
}

/**
 * Item de reconnaissance (MCQ) pour une version :
 *  - A : définition EN → QCM ;
 *  - B : description FR → QCM (consigne différente, vocabulaire ciblé
 *        comparable — exigence P3.1 pour T1) ;
 *  - C : définition EN → QCM, ordre des distracteurs RE-SEMÉ (surface
 *        différente de A, difficulté comparable).
 * Fallback anti-fuite (P0.3) : si la source contient le terme verbatim, vrai
 * trou `______` à la place.
 */
function recognizeItem(concept, version, peers) {
  const en = concept.labelEn;
  const term = termById.get(concept.legacyRef);
  if (!term) throw new Error(`legacyRef ${concept.legacyRef} (${concept.id}) introuvable`);
  const defEn = String(term.defEn ?? "");
  const defFr = String(term.defFr ?? "");

  let source = "defEn";
  let text = defEn;
  if (version === "B") {
    source = "defFr";
    text = defFr;
  }
  const visible = answerVisibleIn(text, en);
  const prompt = visible ? MCQ.blankDef(blanked(text, en)) : version === "B" ? MCQ.defFr(text) : MCQ.defEn(text);
  const path = `${source}${visible ? ":blank" : ""}`;

  // Distracteurs : mêmes pairs d'unité, ordre re-semé PAR VERSION (surface
  // différente, difficulté comparable).
  const options = shuffleDeterministic([en, ...peers], `${concept.id}-rec-${version}`);

  return {
    id: `${concept.id}-rec-${version}`,
    conceptId: concept.id,
    version,
    dimension: "recognize",
    kind: "mcq",
    prompt,
    options,
    answer: en,
    acceptedVariants: undefined,
    source,
  };
}

/**
 * Item de rappel SANS AIDE (saisie libre) pour une version :
 *  - A : FR → EN ;
 *  - B : définition EN → EN (rappel d'un autre ancrage, même terme) ;
 *  - C : FR → EN (parallèle de A).
 * Fallback anti-fuite identique à l'entraînement (P0.3).
 */
function recallItem(concept, version) {
  const en = concept.labelEn;
  const term = termById.get(concept.legacyRef);
  const fr = String(term?.fr ?? "");
  const defEn = String(term?.defEn ?? "");

  const preferDef = version === "B";
  const candidates = preferDef
    ? [
        { source: "defEn", text: defEn, tpl: FREE.defEn },
        { source: "fr", text: fr, tpl: FREE.fr },
      ]
    : [
        { source: "fr", text: fr, tpl: FREE.fr },
        { source: "defEn", text: defEn, tpl: FREE.defEn },
      ];
  let chosen = null;
  for (const c of candidates) {
    if (c.text && !answerVisibleIn(c.text, en)) {
      chosen = c;
      break;
    }
  }
  if (!chosen) {
    throw new Error(`aucune source sans fuite pour le rappel de ${concept.id} (version ${version})`);
  }
  return {
    id: `${concept.id}-recal-${version}`,
    conceptId: concept.id,
    version,
    dimension: "recall",
    kind: "free_input",
    prompt: chosen.tpl(chosen.text),
    answer: en,
    acceptedVariants: concept.acceptedVariants,
    source: chosen.source,
  };
}

function main() {
  const conceptIds = new Set(training.concepts.map((c) => c.id));
  if (conceptIds.size !== training.concepts.length) throw new Error("ids de concepts dupliqués dans le manifeste d'entraînement");

  // Distracteurs : libellés EN des concepts de la MÊME UNITÉ (pairs plausibles).
  const peersByUnit = new Map([
    [1, []],
    [2, []],
    [3, []],
  ]);
  for (const c of training.concepts) peersByUnit.get(c.unit).push(c.labelEn);

  const banks = [];
  const report = [];
  for (const c of training.concepts) {
    const sameUnitPeers = peersByUnit.get(c.unit).filter((l) => l !== c.labelEn);
    for (const v of VERSIONS) {
      const items = [recognizeItem(c, v, sameUnitPeers), recallItem(c, v)];
      banks.push({ conceptId: c.id, version: v, items });
      report.push(
        `${c.id}/${v} ← ${items.map((i) => `${i.dimension}(${i.source})`).join(", ")}`,
      );
    }
  }

  // Garde structurelle : les ids de mesure ne doivent PAS exister dans
  // l'entraînement (et inversement) — la séparation « stockage ≠ exposition ».
  const trainingIds = new Set(training.concepts.flatMap((c) => c.exercises.map((e) => e.id)));
  const measureIds = banks.flatMap((b) => b.items.map((i) => i.id));
  const overlap = measureIds.filter((id) => trainingIds.has(id));
  if (overlap.length > 0) throw new Error(`ids de mesure en collision avec l'entraînement : ${overlap.join(", ")}`);

  const manifest = {
    schemaVersion: 1,
    id: "pilot-anatomy-measure",
    contentVersion: training.contentVersion, // même version que le contenu relu
    generatedAt: training.generatedAt,
    generatedFrom:
      "scripts/build_pilot_measure.mjs ← data/pilot-anatomy.v1.json + public/data/terms.json — versions parallèles A/B/C (T0/T1/T2), 2 dimensions (reconnaissance, rappel). « Compréhension en phrase nouvelle » NON générée : phrases à composer humainement (Lot E). Contenu ENTIERMENT draft.",
    versions: VERSIONS,
    banks,
  };

  const outDir = join(ROOT, "data");
  mkdirSync(outDir, { recursive: true });
  const outPath = join(outDir, "pilot-anatomy-measure.v1.json");
  writeFileSync(outPath, JSON.stringify(manifest, null, 2) + "\n", "utf8");

  console.log(`Banques de mesure écrites : ${outPath}`);
  console.log(`Banques : ${banks.length} (${training.concepts.length} concepts × ${VERSIONS.length} versions), items : ${measureIds.length}`);
  const byDim = {};
  for (const b of banks) for (const i of b.items) byDim[`${i.dimension}:${i.source}`] = (byDim[`${i.dimension}:${i.source}`] ?? 0) + 1;
  console.log("Dimensions/sources :", JSON.stringify(byDim, null, 1));
  if (process.argv.includes("--verbose")) for (const r of report) console.log("  " + r);
}

main();
