/**
 * RISK-007 : parité du contenu d'histoires Android/web.
 *
 * `app/src/main/assets/learning/stories.v1.json` (source Android) et
 * `web-react/public/data/stories.v1.json` (copie servie par le web) doivent
 * être IDENTIQUES : mêmes ids, mêmes versions, mêmes faits (checklist §11 du
 * guide d'intégration). Ce script rejette toute divergence (ids, versions de
 * contenu, clés de métadonnées, ou contenu different).
 *
 * Usage : npm run check:stories [webPath] [androidPath] (chemins surchargables pour les tests).
 */
import { readFileSync, existsSync } from "node:fs";
import { join, dirname } from "node:path";
import { fileURLToPath } from "node:url";

const __dirname = dirname(fileURLToPath(import.meta.url));
const WEB = process.argv[2] ?? join(__dirname, "..", "public", "data", "stories.v1.json");
const ANDROID =
  process.argv[3] ?? join(__dirname, "..", "..", "app", "src", "main", "assets", "learning", "stories.v1.json");

function fail(msg) {
  console.error("✗ " + msg);
  process.exit(1);
}

if (!existsSync(WEB)) fail(`introuvable : ${WEB}`);
if (!existsSync(ANDROID)) fail(`introuvable : ${ANDROID}`);

const web = JSON.parse(readFileSync(WEB, "utf8"));
const android = JSON.parse(readFileSync(ANDROID, "utf8"));

// 1. Mêmes ids, même ordre, mêmes versions de contenu.
const webIds = (web.stories ?? []).map((s) => `${s.storyId}@v${s.contentVersion}`);
const androidIds = (android.stories ?? []).map((s) => `${s.storyId}@v${s.contentVersion}`);
if (webIds.join("|") !== androidIds.join("|")) {
  fail(
    `histoires divergentes :\n  web    = ${webIds.join(", ") || "(vide)"}\n  android= ${androidIds.join(", ") || "(vide)"}`
  );
}

// 2. Contenu strictement identique (comparaison canonique, ordre des clés
//    insensible — seule la sémantique compte).
function canon(value) {
  if (Array.isArray(value)) return value.map(canon);
  if (value && typeof value === "object") {
    return Object.fromEntries(
      Object.keys(value)
        .sort()
        .map((k) => [k, canon(value[k])])
    );
  }
  return value;
}

for (let i = 0; i < (web.stories ?? []).length; i++) {
  const a = canon(web.stories[i]);
  const b = canon(android.stories[i]);
  if (JSON.stringify(a) !== JSON.stringify(b)) {
    fail(`contenu différent pour « ${web.stories[i].storyId} » — resynchroniser les deux copies`);
  }
}

if (web.schemaVersion !== android.schemaVersion) {
  fail(`schemaVersion divergent : web=${web.schemaVersion} android=${android.schemaVersion}`);
}

console.log(
  `OK : parité stories — ${webIds.length} histoire(s), schemaVersion ${web.schemaVersion}, contenu identique.`
);
