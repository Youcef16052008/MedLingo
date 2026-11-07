// Build-time precache for the service worker (R-03, Phase 2).
//
// The hashed bundle filenames (index-*.js, index-*.css, lazy chunks) only
// exist after `vite build`, so they can never be listed in the hand-written
// public/sw.js. This script runs after vite build and rewrites dist/sw.js so
// that its precache list contains every hashed asset plus the static shell.
//
// It also derives the cache name from a hash of the list, so a deploy with
// changed assets invalidates the old cache automatically.

import fs from "node:fs";
import path from "node:path";
import crypto from "node:crypto";

const dist = path.resolve(process.argv[2] || "dist");
const swPath = path.join(dist, "sw.js");
const indexPath = path.join(dist, "index.html");

if (!fs.existsSync(swPath)) throw new Error(`missing ${swPath}`);
if (!fs.existsSync(indexPath)) throw new Error(`missing ${indexPath}`);

const indexHtml = fs.readFileSync(indexPath, "utf8");
const assetSet = new Set();

// Assets referenced by index.html (boot JS/CSS, favicon, etc.)
for (const m of indexHtml.matchAll(/(?:src|href)="(\/assets\/[^"]+)"/g)) {
  assetSet.add(m[1]);
}

// All hashed chunks produced by vite (including lazy routes).
const assetsDir = path.join(dist, "assets");
if (fs.existsSync(assetsDir)) {
  for (const f of fs.readdirSync(assetsDir)) {
    if (/\.(js|css|svg|png|webp|woff2?)$/i.test(f)) {
      assetSet.add("/assets/" + f);
    }
  }
}

const assetList = [...assetSet].map((p) => "." + p).sort();

let sw = fs.readFileSync(swPath, "utf8");
const start = sw.indexOf("const SHELL = [");
const end = sw.indexOf("];", start);
if (start === -1 || end === -1) throw new Error("SHELL array not found in sw.js");

// Preserve the static (non-hashed) entries already present.
const arrText = sw.slice(start, end + 2);
const entryRe = /"\.\/[^"]*"/g;
const staticEntries = [];
let m;
while ((m = entryRe.exec(arrText)) !== null) {
  const v = m[0].slice(1, -1);
  if (!v.startsWith("./assets/")) staticEntries.push(v);
}

const allEntries = [...new Set([...staticEntries, ...assetList])];
const newArr =
  "const SHELL = [\n" + allEntries.map((e) => `  "${e}",`).join("\n") + "\n];";
sw = sw.slice(0, start) + newArr + sw.slice(end + 2);

// Derive a cache name from the precache list so deploys invalidate cleanly.
const hash = crypto.createHash("sha256").update(allEntries.join("|")).digest("hex").slice(0, 8);
sw = sw.replace(/const CACHE = "medlingo-web-v\d+"/, `const CACHE = "medlingo-web-${hash}"`);

fs.writeFileSync(swPath, sw);
console.log(`[patch-sw-precache] precached ${allEntries.length} entries, cache medlingo-web-${hash}`);