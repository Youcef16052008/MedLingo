/**
 * P2.4 — seconde visite HORS-LIGNE du pilote Anatomie (épreuve réelle :
 * serveur coupé, sans émulation CDP). Usage :
 *   node e2e/check_pilot_offline.mjs <PID_du_serveur_preview>
 * Le script coupe lui-même le serveur après la première visite.
 */
import { chromium } from "playwright";
import { execSync } from "node:child_process";

const URL = process.env.APP_URL || "http://localhost:4173";
const serverPID = process.argv[2];

const SEED = `localStorage.setItem('medlingo_web_v1', JSON.stringify({
  lang: 'fr', gems: 0, streak: 0, lastStudy: '',
  xp: 0, answered: 0, correct: 0, flashReviewed: 0, quizDone: 0,
  introSeen: true, dataVersion: 'android-ids-v1'
}));`;

const failures = [];

function check(label, got, want) {
  const ok = got === want;
  console.log((ok ? "  OK    " : "  ECHEC ") + `${label}: ${JSON.stringify(got)}` + (ok ? "" : `  (attendu ${JSON.stringify(want)})`));
  if (!ok) failures.push(label);
}

let browser;
try { browser = await chromium.launch({ headless: true }); }
catch { browser = await chromium.launch({ headless: true, channel: "chrome" }); }

const context = await browser.newContext({ viewport: { width: 390, height: 844 } });
await context.addInitScript(SEED);
const page = await context.newPage();

console.log("=== 1. première visite (SW install + precache) ===");
await page.goto(URL + "/#/pilot?dev=1", { waitUntil: "domcontentloaded", timeout: 60_000 });
await page.waitForTimeout(1500);
const swState = await page.evaluate(async () => {
  const reg = await navigator.serviceWorker.ready;
  const names = await caches.keys();
  const first = names[0] || "";
  const entries = first ? (await (await caches.open(first)).keys()).length : 0;
  return { active: !!reg.active, cache: first, entries };
}).catch(e => ({ error: e.message.slice(0, 120) }));
console.log(JSON.stringify(swState, null, 2));
check("service worker actif", swState.active, true);
check("cache du SW présent", (swState.entries || 0) > 0, true);

console.log("=== 2. serveur coupé + reload (offline réel) ===");
if (serverPID) {
  execSync(`taskkill /PID ${serverPID} /F`);
  console.log(`serveur preview coupé (PID ${serverPID})`);
}
await page.reload({ waitUntil: "domcontentloaded", timeout: 60_000 }).catch(e => console.log("reload:", e.message.slice(0, 90)));
await page.waitForTimeout(2000);

const offline = await page.evaluate(() => {
  const main = document.querySelector("main");
  return {
    title: main ? main.innerText.slice(0, 120) : "(main absent)",
    buttons: main ? Array.from(main.querySelectorAll("button, a")).map(b => (b.innerText || "").trim().slice(0, 30)).filter(Boolean) : [],
    bodySize: document.body.innerText.length
  };
}).catch(e => ({ error: e.message.slice(0, 120), bodySize: 0 }));
console.log(JSON.stringify(offline, null, 2));
check("reload hors-ligne rend du contenu", (offline.bodySize || 0) > 200, true);
check("écran pilote visible hors-ligne", /Anatomy Pilot|Pilote|التشريح/.test(offline.title || ""), true);

console.log("=== 3. démarrage d'une séance hors-ligne ===");
const started = await page.evaluate(() => {
  const btn = Array.from(document.querySelectorAll("main button, main a"))
    .find(b => /Start session|Commencer la séance|ابدأ الجلسة/.test(b.innerText));
  if (!btn) return { found: false };
  btn.click();
  return { found: true };
});
await page.waitForTimeout(600);
const session = await page.evaluate(() => {
  const main = document.querySelector("main");
  return { text: main ? main.innerText.slice(0, 160) : "" };
}).catch(e => ({ error: e.message.slice(0, 120), text: "" }));
console.log(JSON.stringify(session, null, 2));
check("bouton démarrer présent hors-ligne", started.found, true);
check("séance démarrée hors-ligne", /DEVELOPER PREVIEW|aperçu|Step|Étape|الخطوة/.test(session.text || ""), true);

await browser.close();

console.log("\n" + (failures.length === 0 ? "TOUT EST CONFORME" : `${failures.length} ECHEC(S) : ${JSON.stringify(failures)}`));
process.exit(failures.length ? 1 : 0);
