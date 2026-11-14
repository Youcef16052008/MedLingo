#!/usr/bin/env node
/**
 * P1.1 + P1.2 — Construit le manifeste éditorial du pilote Anatomie.
 *
 * Entrée : public/data/terms.json + SÉLECTION MANUELLE ci-dessous (30 ids,
 * 10 par unité, avec justification à faire examiner par les relecteurs).
 * Sortie : data/pilot-anatomy.v1.json — TOUT EN `draft`, relectures `pending`.
 *
 * Templates d'exercices SANS FUITE (leçon P0.3) : chaque builder vérifie que
 * la réponse n'apparaît pas verbatim (mot entier, insensible aux accents)
 * dans la consigne produite ; sinon bascule sur un vrai trou `______`.
 * La validation finale est faite par scripts/pilot-schema.mjs (check:pilot).
 *
 * Usage : node scripts/build_pilot_manifest.mjs
 */

import { readFileSync, writeFileSync, mkdirSync } from "node:fs";
import { join, dirname } from "node:path";
import { fileURLToPath } from "node:url";
import { answerVisibleIn, norm } from "./pilot-schema.mjs";

const here = dirname(fileURLToPath(import.meta.url));
const ROOT = join(here, "..");
const terms = JSON.parse(readFileSync(join(ROOT, "public", "data", "terms.json"), "utf8"));
const termById = new Map(terms.map((t) => [t.id, t]));

const CONTENT_VERSION = "0.1.0";
const generatedAt = `${new Date().toISOString().slice(0, 10)}T00:00:00.000Z`;

/**
 * Sélection P1.2 — 30 concepts, 10 par unité. Chaque note est une HYPOTHÈSE
 * de fréquence/utilité destinée À ÊTRE EXAMINÉE par les relecteurs humains ;
 * rien ici ne constitue une validation.
 */
const SELECTION = [
  // ——— Unité 1 : parties du corps
  { unit: 1, order: 1, slug: "head", legacyRef: 3958, variants: [],
    note: "Hypothèse à examiner : « head » apparaît dans des plaintes très fréquentes (headache, head injury) ; indispensable en triage." },
  { unit: 1, order: 2, slug: "neck", legacyRef: 3959, variants: [],
    note: "Hypothèse à examiner : « neck » est central en traumatologie et douleur cervicale ; hautement utile en anamnèse." },
  { unit: 1, order: 3, slug: "chest", legacyRef: 3960, variants: [],
    note: "Hypothèse à examiner : « chest » (chest pain) est une des plaintes les plus fréquentes aux urgences." },
  { unit: 1, order: 4, slug: "abdomen", legacyRef: 105, variants: [],
    note: "Hypothèse à examiner : « abdomen » structure toute l'anamnèse digestive ; terme identique FR/EN, d'où un rappel par définition." },
  { unit: 1, order: 5, slug: "pelvis", legacyRef: 3986, variants: [],
    note: "Hypothèse à examiner : « pelvis » fréquent en gynéco-urologie et traumatologie ; le FR « Bassin / Pelvis » cite le terme EN." },
  { unit: 1, order: 6, slug: "back", legacyRef: 3962, variants: [],
    note: "Hypothèse à examiner : « back » (back pain) est une plainte très répandue et socialement coûteuse." },
  { unit: 1, order: 7, slug: "skull", legacyRef: 245, variants: [],
    note: "Hypothèse à examiner : « skull » indispensable pour les traumatismes crâniens et l'imagerie." },
  { unit: 1, order: 8, slug: "spine", legacyRef: 3984, variants: ["Backbone"],
    note: "Hypothèse à examiner : « spine » couvre rachialgie et imagerie ; variante « Backbone » acceptée explicitement." },
  { unit: 1, order: 9, slug: "hand", legacyRef: 3967, variants: [],
    note: "Hypothèse à examiner : « hand » fréquent en traumatologie de la main, très utilisable en pratique." },
  { unit: 1, order: 10, slug: "foot", legacyRef: 3974, variants: [],
    note: "Hypothèse à examiner : « foot » (diabète, podologie) très fréquent en consultation." },

  // ——— Unité 2 : organes principaux
  { unit: 2, order: 1, slug: "heart", legacyRef: 1, variants: [],
    note: "Hypothèse à examiner : « heart » est l'organe le plus cité en clinique (insuffisance, IDM) ; id terms.json #1." },
  { unit: 2, order: 2, slug: "lung", legacyRef: 343, variants: [],
    note: "Hypothèse à examiner : « lung » central en pneumologie ; le pluriel « lungs » est admis en usage." },
  { unit: 2, order: 3, slug: "brain", legacyRef: 190, variants: [],
    note: "Hypothèse à examiner : « brain » indispensable en neurologie et neurotraumatologie." },
  { unit: 2, order: 4, slug: "liver", legacyRef: 93, variants: [],
    note: "Hypothèse à examiner : « liver » fréquent (hépatologie, bilan hépatique) ; définition non ambiguë." },
  { unit: 2, order: 5, slug: "stomach", legacyRef: 82, variants: [],
    note: "Hypothèse à examiner : « stomach » très utilisé en gastro-entérologie courante." },
  { unit: 2, order: 6, slug: "kidney", legacyRef: 384, variants: [],
    note: "Hypothèse à examiner : « kidney » central en néphrologie ; à distinguer du pluriel « kidneys »." },
  { unit: 2, order: 7, slug: "intestine", legacyRef: 3994, variants: [],
    note: "Hypothèse à examiner : « intestine » générique et utile avant l'apprentissage small/large bowel." },
  { unit: 2, order: 8, slug: "bladder", legacyRef: 3997, variants: [],
    note: "Hypothèse à examiner : « bladder » fréquent en urologie et rétention urinaire." },
  { unit: 2, order: 9, slug: "spleen", legacyRef: 242, variants: [],
    note: "Hypothèse à examiner : « spleen » utile en hématologie et traumatisme abdominal." },
  { unit: 2, order: 10, slug: "pancreas", legacyRef: 96, variants: [],
    note: "Hypothèse à examiner : « pancreas » indispensable (pancréatite, diabète)." },

  // ——— Unité 3 : localisation anatomique
  { unit: 3, order: 1, slug: "superior", legacyRef: 113, variants: ["Cranial"],
    note: "Hypothèse à examiner : « superior » structure tout repère anatomique ; cadre enseigné explicitement (P1.2, localisation)." },
  { unit: 3, order: 2, slug: "inferior", legacyRef: 114, variants: ["Caudal"],
    note: "Hypothèse à examiner : « inferior » est l'opposé systématique de « superior » ; enseignés ensemble." },
  { unit: 3, order: 3, slug: "anterior", legacyRef: 111, variants: ["Ventral"],
    note: "Hypothèse à examiner : « anterior » très fréquent (face antérieure) ; variante « Ventral » acceptée." },
  { unit: 3, order: 4, slug: "posterior", legacyRef: 112, variants: ["Dorsal"],
    note: "Hypothèse à examiner : « posterior » fréquent (face postérieure) ; variante « Dorsal » acceptée." },
  { unit: 3, order: 5, slug: "medial", legacyRef: 115, variants: [],
    note: "Hypothèse à examiner : « medial » vs « lateral » est LE repère médian ; pair enseigné ensemble." },
  { unit: 3, order: 6, slug: "lateral", legacyRef: 116, variants: [],
    note: "Hypothèse à examiner : « lateral » (côté) ; ambigu sans contexte — l'exemple fournit TOUJOURS un contexte." },
  { unit: 3, order: 7, slug: "proximal", legacyRef: 117, variants: [],
    note: "Hypothèse à examiner : « proximal » propre aux membres ; le FR « Proximal » est identique → rappel par définition." },
  { unit: 3, order: 8, slug: "distal", legacyRef: 118, variants: [],
    note: "Hypothèse à examiner : « distal » opposé de « proximal » ; FR identique → rappel par définition." },
  { unit: 3, order: 9, slug: "superficial", legacyRef: 119, variants: [],
    note: "Hypothèse à examiner : « superficial » courant en dermatologie et plaies ; quasi-cognate mais orthographe différente." },
  { unit: 3, order: 10, slug: "deep", legacyRef: 120, variants: [],
    note: "Hypothèse à examiner : « deep » opposé de « superficial » ; monosyllabe haute fréquence." },
];

const UNIT_TITLES = { 1: "Parties du corps", 2: "Organes principaux", 3: "Localisation anatomique" };

const pendingReview = () => ({ status: "pending" });

// Dérivation déterministe (FNV-1a) pour l'ordre des distracteurs — jamais
// Math.random : deux exécutions produisent le même manifeste.
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

function distractFor(conceptId, unit, label, peersByUnit) {
  const sameUnit = peersByUnit.get(unit).filter((l) => l !== label);
  const otherUnits = [...peersByUnit.get(1), ...peersByUnit.get(2), ...peersByUnit.get(3)].filter(
    (l) => l !== label && !sameUnit.includes(l),
  );
  const pick = [...shuffleDeterministic(sameUnit, `${conceptId}-a`), ...shuffleDeterministic(otherUnits, `${conceptId}-b`)];
  return pick.slice(0, 3);
}

function slugify(s) {
  return s.toLowerCase().replace(/[^a-z0-9]+/g, "-").replace(/^-|-$/g, "");
}

const CONS = {
  recognize: (x) => ({
    en: `Which term matches this definition: '${x}'?`,
    fr: `Quel terme correspond à cette définition : '${x}' ?`,
    ar: `أي مصطلح يوافق هذا التعريف: '${x}'؟`,
  }),
  blank: (x) => ({
    en: `Fill in the blank with the correct term: '${x}'`,
    fr: `Complétez par le terme correct : '${x}'`,
    ar: `أكمل الفراغ بالمصطلح الصحيح: '${x}'`,
  }),
  context: (x) => ({
    en: `Which term is described here: '${x}'?`,
    fr: `Quel terme est décrit ici : '${x}' ?`,
    ar: `أي مصطلح يوصف هنا: '${x}'؟`,
  }),
  recallFr: (x) => ({
    en: `Type the English term for: '${x}'`,
    fr: `Écrivez le terme anglais pour : '${x}'`,
    ar: `اكتب المصطلح الإنجليزي لـ: '${x}'`,
  }),
  recallDef: (x) => ({
    en: `Type the term that matches this definition: '${x}'`,
    fr: `Écrivez le terme qui correspond à cette définition : '${x}'`,
    ar: `اكتب المصطلح الذي يوافق هذا التعريف: '${x}'`,
  }),
};

function blanked(text, term) {
  if (!text) return null;
  return text.replace(new RegExp(term.replace(/[.*+?^${}()|[\]\\]/g, "\\$&"), "gi"), "______");
}

function buildExercises(conceptId, unit, term, peersByUnit) {
  const en = String(term.en);
  const fr = String(term.fr);
  const defEn = String(term.defEn ?? "");
  const defFr = String(term.defFr ?? "");
  const exEn = String(term.exEn ?? "");
  const explanation = { en: defEn, fr: defFr, ar: String(term.defAr ?? "") };
  const options = shuffleDeterministic([en, ...distractFor(conceptId, unit, en, peersByUnit)], `${conceptId}-opts`);
  const paths = [];

  // 1) recognition — définition → QCM (évaluation).
  let recPrompt;
  if (answerVisibleIn(defEn, en)) {
    recPrompt = CONS.blank(blanked(defEn, en));
    paths.push("rec:blank-def");
  } else {
    recPrompt = CONS.recognize(defEn);
    paths.push("rec:def");
  }
  const rec = {
    id: `${conceptId}-rec`,
    conceptId,
    objective: "recognize",
    role: "assessment",
    kind: "mcq",
    prompt: recPrompt,
    options,
    answer: en,
    explanation,
  };

  // 2) understanding — contexte (entraînement) : exemple si possible, sinon
  //    description FR (contexte linguistique), sinon trou dans la définition.
  let undPrompt;
  if (exEn && answerVisibleIn(exEn, en)) {
    undPrompt = CONS.blank(blanked(exEn, en));
    paths.push("und:blank-ex");
  } else if (!answerVisibleIn(defFr, en)) {
    undPrompt = CONS.context(defFr);
    paths.push("und:ctx-fr");
  } else if (exEn) {
    // L'exemple existe mais ne contient pas le terme : description EN sans fuite.
    undPrompt = CONS.context(exEn);
    paths.push("und:ctx-en");
  } else {
    undPrompt = CONS.blank(blanked(defEn, en));
    paths.push("und:blank-def");
  }
  const und = {
    id: `${conceptId}-und`,
    conceptId,
    objective: "understand",
    role: "practice",
    kind: "mcq",
    prompt: undPrompt,
    options: shuffleDeterministic(options, `${conceptId}-und-opts`),
    answer: en,
    explanation,
  };

  // 3) recall — saisie libre : traduction si sans fuite, sinon définition.
  let recPromptFree;
  if (!answerVisibleIn(fr, en)) {
    recPromptFree = CONS.recallFr(fr);
    paths.push("recall:fr");
  } else {
    recPromptFree = CONS.recallDef(defEn);
    paths.push("recall:def");
  }
  if (answerVisibleIn(recPromptFree.en, en) || answerVisibleIn(recPromptFree.fr, en) || answerVisibleIn(recPromptFree.ar, en)) {
    throw new Error(`fuite résiduelle sur ${conceptId} (${paths.join(", ")}) — builder à corriger`);
  }
  const rec2 = {
    id: `${conceptId}-recal`,
    conceptId,
    objective: "recall",
    role: "practice",
    kind: "free_input",
    prompt: recPromptFree,
    answer: en,
    explanation,
  };

  return { exercises: [rec, und, rec2], paths };
}

function main() {
  // Distracteurs : libellés EN des concepts sélectionnés, groupés par unité.
  const peersByUnit = new Map([
    [1, []],
    [2, []],
    [3, []],
  ]);
  for (const s of SELECTION) {
    const t = termById.get(s.legacyRef);
    if (!t) throw new Error(`legacyRef ${s.legacyRef} (${s.slug}) introuvable dans terms.json`);
    peersByUnit.get(s.unit).push(String(t.en));
  }

  const concepts = [];
  const report = [];
  for (const s of SELECTION) {
    const t = termById.get(s.legacyRef);
    const conceptId = `anatomy-u${s.unit}-${String(s.order).padStart(2, "0")}-${s.slug}`;
    const { exercises, paths } = buildExercises(conceptId, s.unit, t, peersByUnit);
    // Variantes explicites : éclatement du label parenthésé (P1.1).
    const variants = [...s.variants];
    const paren = [...String(t.en).matchAll(/\(([^)]+)\)/g)].map((m) => m[1].trim());
    for (const p of paren) if (!variants.includes(p)) variants.push(p);
    concepts.push({
      id: conceptId,
      unit: s.unit,
      order: s.order,
      legacyRef: t.id,
      labelEn: String(t.en),
      hints: { fr: String(t.fr), ar: String(t.ar) },
      languageObjective: `Unité « ${UNIT_TITLES[s.unit]} » : reconnaître et produire le terme EN « ${t.en} » à partir d'une définition simple, le distinguer des termes voisins de la même unité.`,
      definition: { en: String(t.defEn ?? ""), fr: String(t.defFr ?? ""), ar: String(t.defAr ?? "") },
      example: t.exEn ? { en: String(t.exEn), fr: String(t.exFr ?? t.exEn), ar: String(t.exAr ?? t.exEn) } : undefined,
      acceptedVariants: variants.length > 0 ? variants : undefined,
      selectionNote: s.note,
      status: "draft",
      contentVersion: CONTENT_VERSION,
      reviews: { linguistic: pendingReview(), medical: pendingReview() },
      exercises,
    });
    report.push(`${conceptId} ← term #${t.id} « ${t.en} » [${paths.join(", ")}]`);
  }

  const manifest = {
    schemaVersion: 1,
    id: "pilot-anatomy",
    contentVersion: CONTENT_VERSION,
    generatedAt,
    generatedFrom:
      "scripts/build_pilot_manifest.mjs ← public/data/terms.json (sélection manuelle de 30 legacyRef, P1.2) — contenu ENTIERMENT draft, relectures humaines pending",
    concepts,
  };

  const outDir = join(ROOT, "data");
  mkdirSync(outDir, { recursive: true });
  const outPath = join(outDir, "pilot-anatomy.v1.json");
  writeFileSync(outPath, JSON.stringify(manifest, null, 2) + "\n", "utf8");

  console.log(`Manifeste écrit : ${outPath}`);
  console.log(`Concepts : ${concepts.length} (draft), exercices : ${concepts.reduce((n, c) => n + c.exercises.length, 0)}`);
  const byPath = {};
  for (const r of report) for (const p of r.match(/\[(.+)\]/)[1].split(", ")) byPath[p] = (byPath[p] ?? 0) + 1;
  console.log("Chemins de templates :", JSON.stringify(byPath, null, 1));
  if (process.argv.includes("--verbose")) for (const r of report) console.log("  " + r);
}

main();
