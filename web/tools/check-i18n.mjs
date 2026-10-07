// Vérifie que toutes les clés i18n utilisées existent dans fr/en/ar.
import { readFileSync } from "node:fs";
import { join, dirname } from "node:path";
import { fileURLToPath } from "node:url";

const src = readFileSync(join(dirname(fileURLToPath(import.meta.url)), "..", "app.js"), "utf8");

const used = new Set();
for (const m of src.matchAll(/\bt\("([A-Za-z0-9]+)"\)/g)) used.add(m[1]);
// clés concaténées dynamiquement : t("rate" + q)
for (const k of ["rate1", "rate2", "rate3", "rate4", "rate5", "lvl1", "lvl2", "lvl3", "lvl4", "lvl5", "lvl6"]) used.add(k);

const blocks = {};
for (const m of src.matchAll(/Object\.assign\(I18N\.(\w+),\s*\{([\s\S]*?)\n\}\);/g)) {
  blocks[m[1]] = new Set([...m[2].matchAll(/\b(\w+):\s*"/g)].map((x) => x[1]));
}

let fail = false;
for (const l of ["fr", "en", "ar"]) {
  const miss = [...used].filter((k) => !blocks[l]?.has(k));
  const extra = [...(blocks[l] || [])].filter((k) => !blocks.fr.has(k));
  console.log(`${l}: ${blocks[l]?.size ?? 0} clés — manquantes: ${miss.join(",") || "aucune"} — orphelines: ${extra.join(",") || "aucune"}`);
  if (miss.length || extra.length) fail = true;
}
process.exit(fail ? 1 : 0);
