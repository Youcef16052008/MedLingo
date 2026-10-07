// Vérifie que toutes les clés i18n utilisées existent dans fr/en/ar,
// et signale les clés définies dans les trois langues mais jamais référencées.
// Usage : npm run check:i18n
import { readFileSync, readdirSync, statSync } from "node:fs";
import { join, dirname } from "node:path";
import { fileURLToPath } from "node:url";

const __dirname = dirname(fileURLToPath(import.meta.url));
const SRC_DIR = join(__dirname, "..", "src");
const DICT_FILE = join(SRC_DIR, "i18n", "index.ts");

function walk(dir, out = []) {
  for (const name of readdirSync(dir)) {
    const p = join(dir, name);
    if (statSync(p).isDirectory()) walk(p, out);
    else if (/\.(ts|tsx)$/.test(name)) out.push(p);
  }
  return out;
}

const files = walk(SRC_DIR);
const sources = new Map(files.map((f) => [f, readFileSync(f, "utf8")]));

// 1. Clés citées par t("clé") / t('clé', args) — tirets soulignés inclus.
const used = new Set();
for (const [file, src] of sources) {
  if (file === DICT_FILE) continue;
  for (const m of src.matchAll(/\bt\(\s*"([A-Za-z0-9_]+)"\s*[,)]/g)) used.add(m[1]);
  for (const m of src.matchAll(/\bt\(\s*'([A-Za-z0-9_]+)'\s*[,)]/g)) used.add(m[1]);
}

// 2. Clés référencées via variables/tableaux (RATE_KEYS, LEVEL_KEYS,
//    trophy titleKey/descKey…) : tout mot du source hors dictionnaire compte.
const tokens = new Set();
for (const [file, src] of sources) {
  if (file === DICT_FILE) continue;
  for (const m of src.matchAll(/[A-Za-z0-9_]+/g)) tokens.add(m[0]);
}

const dict = sources.get(DICT_FILE);
const blocks = {};
for (const lang of ["fr", "en", "ar"]) {
  const start = dict.indexOf(`  ${lang}: {`);
  if (start === -1) continue;
  const end = dict.indexOf("\n  },", start);
  const body = dict.slice(start, end);
  blocks[lang] = new Set([...body.matchAll(/^\s{4}(\w+):/gm)].map((m) => m[1]));
}

let fail = false;
for (const lang of ["fr", "en", "ar"]) {
  const have = blocks[lang] ?? new Set();
  const missing = [...used].filter((k) => !have.has(k));
  const extra = [...have].filter((k) => !blocks.fr.has(k));
  console.log(
    `${lang}: ${have.size} clés — manquantes: ${missing.join(",") || "aucune"} — désynchronisées: ${extra.join(",") || "aucune"}`
  );
  if (missing.length || extra.length) fail = true;
}

// 3. Clés mortes : définies en fr (donc en en/ar, vérifié ci-dessus) mais
//    jamais citées ni référencées par identifiant dans le code.
const dead = [...blocks.fr].filter((k) => !used.has(k) && !tokens.has(k));
if (dead.length) {
  console.log(`clés mortes (jamais référencées): ${dead.join(",")}`);
  fail = true;
} else {
  console.log("clés mortes: aucune");
}
process.exit(fail ? 1 : 0);
