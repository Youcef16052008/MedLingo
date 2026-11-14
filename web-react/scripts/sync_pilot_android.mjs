#!/usr/bin/env node
/**
 * P4.1 — Export DÉTERMINISTE des manifestes pilote vers les assets Android.
 *
 * Copie octet-pour-octet les deux manifestes web (source de vérité) vers
 * `app/src/main/assets/pilot/`. La parité est ensuite vérifiée par
 * `npm run check:pilot-parity` (pattern `check:stories`) et par un test Kotlin
 * qui relit les assets réels.
 *
 * Usage : npm run sync:pilot
 */
import { readFileSync, writeFileSync, mkdirSync } from "node:fs";
import { join, dirname } from "node:path";
import { fileURLToPath } from "node:url";

const here = dirname(fileURLToPath(import.meta.url));
const ROOT = join(here, "..");
const FILES = [
  "pilot-anatomy.v1.json",
  "pilot-anatomy-measure.v1.json",
];

const outDir = join(ROOT, "..", "app", "src", "main", "assets", "pilot");
mkdirSync(outDir, { recursive: true });

for (const f of FILES) {
  const src = readFileSync(join(ROOT, "data", f));
  writeFileSync(join(outDir, f), src);
  console.log(`[sync-pilot] ${f} → app/src/main/assets/pilot/${f} (${src.length} octets)`);
}
console.log("[sync-pilot] OK — copie déterministe (octets identiques).");
