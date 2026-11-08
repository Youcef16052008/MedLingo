const fs = require("fs");
const path = require("path");

function walk(dir, out = []) {
  for (const name of fs.readdirSync(dir)) {
    const p = path.join(dir, name);
    if (fs.statSync(p).isDirectory()) walk(p, out);
    else if (/\.tsx?$/.test(name) && !p.includes("__tests__")) out.push(p);
  }
  return out;
}

// French markers: accented chars typical of FR + common FR words in JSX text
const frText = />([^<>{}]*[\u00C0-\u024F][^<>{}]*)</g;
const frString = /\{\s*"([^"]*[\u00C0-\u024F][^"]*)"\s*\}/g;
// common unaccented FR words in JSX text
const frWords = /\b(les?|une?|des|vous|votre|avec|pour|dans|connexion|inscription|mot de passe)\b/i;
// mojibake (double-encoded UTF-8) — garbled text in the UI, must never ship
const mojibake = /[\u00C2\u00C3][\u0080-\u00BF]|\u00F0\u0178|\u00E2\u0153/;

let hits = 0;
const files = walk("src");
for (const f of files) {
  const src = fs.readFileSync(f, "utf8");
  const lines = src.split("\n");
  lines.forEach((line, i) => {
    // skip comments
    const trimmed = line.trim();
    if (trimmed.startsWith("//") || trimmed.startsWith("*") || trimmed.startsWith("/*")) return;
    if (mojibake.test(line)) {
      console.log(`${f}:${i + 1}: [mojibake] ${line.trim().slice(0, 70)}`);
      hits++;
      return;
    }
    let m;
    frText.lastIndex = 0;
    while ((m = frText.exec(line)) !== null) {
      const text = m[1].trim();
      if (!text || /[=<>]/.test(text)) continue;
      if (frWords.test(text) || /[\u00C0-\u024F]/.test(text)) {
        console.log(`${f}:${i + 1}: [text] ${text.slice(0, 70)}`);
        hits++;
      }
    }
    frString.lastIndex = 0;
    while ((m = frString.exec(line)) !== null) {
      const text = m[1];
      if (/[\u00C0-\u024F]/.test(text) || frWords.test(text)) {
        console.log(`${f}:${i + 1}: [str] ${text.slice(0, 70)}`);
        hits++;
      }
    }
  });
}

if (hits > 0) {
  console.error(`\n${hits} hardcoded French/mojibake literal(s) found — route them through t().`);
  process.exit(1);
}
console.log("no hardcoded French literals or mojibake in JSX");
