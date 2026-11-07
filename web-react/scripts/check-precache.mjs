// CI guard for the service-worker precache (R-03, Phase 2).
// Fails if dist/sw.js does not contain every hashed asset referenced by
// dist/index.html and every file under dist/assets/*.js|*.css.

import fs from "node:fs";
import path from "node:path";

const dist = path.resolve(process.argv[2] || "dist");
const sw = fs.readFileSync(path.join(dist, "sw.js"), "utf8");
const indexHtml = fs.readFileSync(path.join(dist, "index.html"), "utf8");

const required = new Set();
for (const m of indexHtml.matchAll(/(?:src|href)="(\/assets\/[^"]+)"/g)) required.add(m[1]);
const assetsDir = path.join(dist, "assets");
for (const f of fs.readdirSync(assetsDir)) {
  if (/\.(js|css)$/i.test(f)) required.add("/assets/" + f);
}

let missing = 0;
for (const a of required) {
  if (!sw.includes(JSON.stringify("." + a))) {
    console.error(`MISSING from precache: ${a}`);
    missing++;
  }
}
if (missing > 0) {
  console.error(`[check-precache] ${missing} asset(s) missing`);
  process.exit(1);
}
console.log(`[check-precache] OK — ${required.size} hashed assets precached`);