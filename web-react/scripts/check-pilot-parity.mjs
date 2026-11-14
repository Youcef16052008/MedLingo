#!/usr/bin/env node
/**
 * check:pilot-parity — parité web/Android des manifestes pilote (P4.1).
 * La source de vérité est `web-react/data/` ; les assets Android
 * `app/src/main/assets/pilot/` doivent être des copies OCTET-POUR-OCTET.
 * Un désaccord = exit 1 (l'export a été oublié après une édition web).
 */
import { readFileSync, existsSync } from "node:fs";
import { join, dirname } from "node:path";
import { fileURLToPath } from "node:url";

const here = dirname(fileURLToPath(import.meta.url));
const ROOT = join(here, "..");
const ANDROID_DIR = join(ROOT, "..", "app", "src", "main", "assets", "pilot");
const FILES = ["pilot-anatomy.v1.json", "pilot-anatomy-measure.v1.json"];

const errors = [];
for (const f of FILES) {
  const webPath = join(ROOT, "data", f);
  const androidPath = join(ANDROID_DIR, f);
  if (!existsSync(androidPath)) {
    errors.push(`${f} : asset Android absent — lancer « npm run sync:pilot »`);
    continue;
  }
  const web = readFileSync(webPath);
  const android = readFileSync(androidPath);
  if (!web.equals(android)) {
    errors.push(`${f} : asset Android ≠ manifeste web (${android.length} vs ${web.length} octets) — regénérer puis « npm run sync:pilot »`);
  }
}

if (errors.length > 0) {
  console.error("[check-pilot-parity] ERREURS :");
  for (const e of errors) console.error(`  - ${e}`);
  process.exit(1);
}
console.log("[check-pilot-parity] OK — manifestes pilote identiques web/Android (octet-pour-octet).");
