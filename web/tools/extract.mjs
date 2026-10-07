// Extracts MedicalTermEntity / ExerciseEntity data from the Kotlin seeds into JSON for the web PWA.
// Usage: node tools/extract.mjs   (run from the web/ directory)
//
// The Kotlin seeds live in `data/terms/` (one file per module); exercises stay in
// `data/initial/LearningExercisesData.kt`. Term ids are copied verbatim: the web app keys
// its spaced-repetition state, bookmarks and deep links on `term.id`, so a renumbered copy
// would silently diverge from the Android app's ids.
import { readFileSync, writeFileSync, mkdirSync, readdirSync } from "node:fs";
import { join, dirname } from "node:path";
import { fileURLToPath } from "node:url";

const __dirname = dirname(fileURLToPath(import.meta.url));
const SEEDS = join(__dirname, "..", "..", "app", "src", "main", "java", "com", "example", "data");
const TERMS_SRC = join(SEEDS, "terms");
const EXERCISES_SRC = join(SEEDS, "initial", "LearningExercisesData.kt");
const OUT = join(__dirname, "..", "data");

/** Scan a Kotlin constructor call body starting after the opening paren, respecting strings. */
function extractBlocks(content, ctorName) {
  const blocks = [];
  const needle = ctorName + "(";
  let idx = content.indexOf(needle);
  while (idx !== -1) {
    let i = idx + needle.length;
    let depth = 1;
    let inStr = false;
    let escaped = false;
    while (i < content.length && depth > 0) {
      const c = content[i];
      if (inStr) {
        if (escaped) escaped = false;
        else if (c === "\\") escaped = true;
        else if (c === '"') inStr = false;
      } else {
        if (c === '"') inStr = true;
        else if (c === "(" || c === "[") depth++;
        else if (c === ")" || c === "]") depth--;
      }
      i++;
    }
    blocks.push(content.slice(idx + needle.length, i - 1));
    idx = content.indexOf(needle, i);
  }
  return blocks;
}

/** Parse `key = "string" | number | true/false` pairs from a constructor body. */
function parseFields(block) {
  const fields = {};
  const re = /(\w+)\s*=\s*("(?:[^"\\]|\\.)*"|-?\d+(?:\.\d+)?|true|false)/g;
  let m;
  while ((m = re.exec(block)) !== null) {
    let v = m[2];
    if (v.startsWith('"')) {
      v = v.slice(1, -1)
        .replace(/\\n/g, "\n")
        .replace(/\\t/g, "\t")
        .replace(/\\"/g, '"')
        .replace(/\\\\/g, "\\")
        .replace(/\\\$/g, "$")
        .replace(/\\u([0-9a-fA-F]{4})/g, (_, h) => String.fromCharCode(parseInt(h, 16)));
    } else if (v === "true") v = true;
    else if (v === "false") v = false;
    else if (/^-?\d+$/.test(v)) v = parseInt(v, 10);
    else if (/^-?\d+\.\d+$/.test(v)) v = parseFloat(v);
    fields[m[1]] = v;
  }
  return fields;
}

const warnings = [];
const terms = [];

for (const file of readdirSync(TERMS_SRC).filter((f) => f.endsWith(".kt"))) {
  const content = readFileSync(join(TERMS_SRC, file), "utf8");
  for (const block of extractBlocks(content, "MedicalTermEntity")) {
    const f = parseFields(block);
    if (!f.termEn || !f.module) {
      warnings.push(`${file}: entity ignored (termEn/module missing)`);
      continue;
    }
    if (!Number.isInteger(f.id) || f.id < 1) {
      warnings.push(`${file}: entity "${f.termEn}" ignored (missing numeric id)`);
      continue;
    }
    terms.push({
      id: f.id,
      en: f.termEn,
      fr: f.termFr || "",
      ar: f.termAr || "",
      defEn: f.definitionEn || "",
      defFr: f.definitionFr || "",
      defAr: f.definitionAr || "",
      etym: f.etymology || "",
      pearl: f.clinicalPearl || "",
      mnemo: f.mnemonic || "",
      module: f.module,
      chapter: f.chapter || "",
      exEn: f.exampleEn || f.example || "",
      exFr: f.exampleFr || "",
      exAr: f.exampleAr || "",
      ipa: f.ipaPhonetic || "",
    });
  }
}

// Same order and same ids as the Kotlin seeds, so both apps agree on term identity.
terms.sort((a, b) => a.id - b.id);

const exercises = extractBlocks(readFileSync(EXERCISES_SRC, "utf8"), "ExerciseEntity").map((b) => {
  const f = parseFields(b);
  return {
    id: f.id,
    level: f.level || 1,
    type: f.type,
    difficulty: f.difficulty || "beginner",
    module: f.module,
    chapter: f.chapter || "",
    qEn: f.questionEn || "",
    qFr: f.questionFr || "",
    qAr: f.questionAr || "",
    options: (f.optionsRaw || "").split("|").filter(Boolean),
    answer: f.correctAnswer || "",
    expEn: f.explanationEn || "",
    expFr: f.explanationFr || "",
    expAr: f.explanationAr || "",
    points: f.points || 10,
    ctxEn: f.contextTextEn || "",
    ctxFr: f.contextTextFr || "",
    ctxAr: f.contextTextAr || "",
  };
});

// Fail loudly rather than shipping a silently mis-keyed dataset.
const dupIds = terms.map((t) => t.id).filter((v, i, a) => a.indexOf(v) !== i);
if (dupIds.length) throw new Error(`duplicate term ids in the Kotlin seeds: ${[...new Set(dupIds)].join(", ")}`);
if (terms.length) {
  const max = terms[terms.length - 1].id;
  const gaps = [];
  for (let i = 1; i <= max; i++) if (!terms.some((t) => t.id === i)) gaps.push(i);
  if (gaps.length) throw new Error(`gap in the term id space: ${gaps.slice(0, 20).join(", ")}`);
}

mkdirSync(OUT, { recursive: true });
writeFileSync(join(OUT, "terms.json"), JSON.stringify(terms));
writeFileSync(join(OUT, "exercises.json"), JSON.stringify(exercises));

const byModule = {};
for (const t of terms) byModule[t.module] = (byModule[t.module] || 0) + 1;
console.log(`terms: ${terms.length}, exercises: ${exercises.length}`);
console.log(`term id space: 1..${terms.length ? terms[terms.length - 1].id : 0} (copied from the Kotlin seeds)`);
console.log("modules:", JSON.stringify(byModule, null, 2));
if (warnings.length) {
  console.log(`WARNINGS (${warnings.length}):`);
  warnings.slice(0, 20).forEach((w) => console.log("  " + w));
}