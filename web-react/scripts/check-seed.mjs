/**
 * P1-5 : valider que le seed SQL backend couvre tout le corpus de référence.
 *
 * `backend/supabase/migrations/03_seed_content.sql` est généré depuis
 * `public/data/terms.json` + `public/data/exercises.json` par
 * `tools_gen_seed_sql.py` (racine). Le 12/10/2026 ce fichier ne contenait que
 * 478 termes sur 4 504 : le générateur n'avait pas suivi l'ajout des types
 * `clinical_case` / `reading` et le seed n'avait jamais été régénéré.
 *
 * Ce script rejette tout seed incomplet. Doit tourner à chaque PR.
 *
 * Usage : npm run check:seed
 */
import { readFileSync, existsSync } from "node:fs";
import { join, dirname } from "node:path";
import { fileURLToPath } from "node:url";

const __dirname = dirname(fileURLToPath(import.meta.url));
const ROOT = join(__dirname, "..", "..");
const SEED = join(ROOT, "backend", "supabase", "migrations", "03_seed_content.sql");
const TERMS = join(__dirname, "..", "public", "data", "terms.json");
const EXERCISES = join(__dirname, "..", "public", "data", "exercises.json");

let failures = 0;

function fail(msg) {
  console.error("✗ " + msg);
  failures++;
}

if (!existsSync(SEED)) {
  // Backend non présent dans ce checkout : non-bloquant.
  console.log("ABSENT: seed SQL backend introuvable — rien à valider.");
  process.exit(0);
}

const sql = readFileSync(SEED, "utf8");
const terms = JSON.parse(readFileSync(TERMS, "utf8"));
const exercises = JSON.parse(readFileSync(EXERCISES, "utf8"));

// --- Termes -------------------------------------------------------------
const start = sql.indexOf("insert into medical_terms");
if (start < 0) fail("aucun bloc 'insert into medical_terms' dans le seed SQL");
else {
  const end = sql.indexOf("insert into lessons", start);
  const segment = end > 0 ? sql.slice(start, end) : sql.slice(start);
  const seedIds = [...segment.matchAll(/^\s*\((\d+),/gm)].map((m) => Number(m[1]));

  if (seedIds.length === 0)
    fail("le seed SQL ne contient AUCUN terme — générateur cassé ? Lancer `python tools_gen_seed_sql.py`");
  else {
    const expected = new Set(terms.map((t) => t.id));
    const seedSet = new Set(seedIds);
    const missing = [...expected].filter((id) => !seedSet.has(id));
    const extra = [...seedSet].filter((id) => !expected.has(id));

    if (missing.length)
      fail(`seed SQL incomplet : ${missing.length} termes manquants (présents dans terms.json, absents du SQL). Lancer \`python tools_gen_seed_sql.py\`. Exemples : ${missing.slice(0, 5).join(", ")}`);
    if (extra.length)
      fail(`seed SQL : ${extra.length} ids inconnus du corpus : ${extra.slice(0, 5).join(", ")}`);

    const dupes = seedIds.filter((id, i) => seedIds.indexOf(id) !== i);
    if (dupes.length) fail(`seed SQL : ids de termes en doublon : ${[...new Set(dupes)].slice(0, 5).join(", ")}`);
  }
}

// --- Exercices ----------------------------------------------------------
const exStart = sql.indexOf("insert into exercises");
if (exStart < 0) fail("aucun bloc 'insert into exercises' dans le seed SQL");
else {
  // On borne au bloc exercises : jusqu'au 'on conflict' qui le termine.
  const conflictAt = sql.indexOf("on conflict", exStart);
  const exSeg = conflictAt > 0 ? sql.slice(exStart, conflictAt) : sql.slice(exStart);
  // Chaque exercice commence par une ligne "('00000000-0000-...')" (uuid_from_int).
  // On ne compte que les uuids en début de ligne pour éviter les faux positifs
  // dans les champs JSON (spec) ou les valeurs.
  const seedEx = (exSeg.match(/^\('([0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12})'/gm) || []).length;

  if (seedEx === 0)
    fail("le seed SQL ne contient AUCUN exercice — générateur cassé ?");
  else if (seedEx !== exercises.length)
    fail(`seed SQL : ${seedEx} exercices, le corpus en a ${exercises.length}. Lancer \`python tools_gen_seed_sql.py\``);
}

if (failures === 0) {
  console.log(`OK : seed SQL cohérent — ${terms.length} termes, ${exercises.length} exercices.`);
  process.exit(0);
}
console.error(`\n${failures} problème(s). Lancer \`python tools_gen_seed_sql.py\` puis recommencer.`);
process.exit(1);
