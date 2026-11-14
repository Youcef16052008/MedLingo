/**
 * P2.4 — vérification « petit écran » (390×844) des écrans du pilote Anatomie.
 *
 * Complète e2e/check_design.py (qui audite le parcours, pas le pilote).
 * Exécutée via Node/Playwright (le Python de ce poste n'a pas playwright) :
 *   node e2e/check_pilot_small.mjs
 *
 * Vérifie, en FR (LTR) puis en AR (RTL) :
 *   - aucun débordement horizontal (scrollWidth <= clientWidth) ;
 *   - contrôles de <main> visibles et dans les bornes horizontales ;
 *   - cibles tactiles >= 24 px (WCAG 2.2 / 2.5.8, minimum AA) ;
 *   - action principale cliquable (démarrage de séance).
 * Sont exclus : input[type=file][hidden] (pattern légitime déclenché par le
 * bouton « Importer », lui-même cible tactile conforme).
 * Ne modifie aucune donnée de production : manifests réels draft, les
 * écrans ne démarrent qu'en aperçu dev (?dev=1 dans le hash).
 */
import { chromium } from "playwright";

const URL = process.env.APP_URL || "http://localhost:4173";

const SEED = (lang) =>
  `localStorage.setItem('medlingo_web_v1', JSON.stringify({
    lang: '${lang}', gems: 0, streak: 0, lastStudy: '',
    xp: 0, answered: 0, correct: 0, flashReviewed: 0, quizDone: 0,
    introSeen: true, dataVersion: 'android-ids-v1'
  }));`;

const PROBE = () => {
  const doc = document.documentElement;
  const main = document.querySelector('main');
  const controls = Array.from(
    main ? main.querySelectorAll('button, a, input:not([hidden])') : []
  ).map(el => {
    const r = el.getBoundingClientRect();
    return {
      tag: el.tagName,
      text: (el.innerText || el.placeholder || el.getAttribute('aria-label') || '').trim().slice(0, 24),
      w: Math.round(r.width), h: Math.round(r.height),
      left: Math.round(r.left), right: Math.round(r.right),
      visible: r.width > 0 && r.height > 0,
      inHorizBounds: r.left >= -1 && r.right <= doc.clientWidth + 1
    };
  });
  return {
    url: location.hash,
    dir: doc.getAttribute('dir'),
    viewport: doc.clientWidth + 'x' + doc.clientHeight,
    hOverflow: doc.scrollWidth > doc.clientWidth,
    controls: controls.length,
    offBounds: controls.filter(c => c.visible && !c.inHorizBounds).map(c => c.text),
    tiny: controls.filter(c => c.visible && (c.h < 24 || c.w < 24)).map(c => c.text + ' ' + c.w + 'x' + c.h),
    zeroSize: controls.filter(c => !c.visible).map(c => c.text)
  };
};

const hasStartBtn = () =>
  Array.from(document.querySelectorAll('main button, main a'))
    .find(b => /Start session|Commencer la séance|ابدأ الجلسة/.test(b.innerText)) !== undefined;

const clickStartBtn = () =>
  Array.from(document.querySelectorAll('main button, main a'))
    .find(x => /Start session|Commencer la séance|ابدأ الجلسة/.test(x.innerText)).click();

const failures = [];

function check(label, got, want) {
  const ok = got === want;
  console.log((ok ? "  OK    " : "  ECHEC ") + `${label}: ${JSON.stringify(got)}` + (ok ? "" : `  (attendu ${JSON.stringify(want)})`));
  if (!ok) failures.push(label);
}

async function runPass(langLabel, lang) {
  console.log(`\n=== ${langLabel} ===`);
  // Chromium Playwright n'est pas installé sur ce poste : on utilise le
  // Chrome système (channel="chrome") en repli, sans téléchargement.
  let browser;
  try {
    browser = await chromium.launch({ headless: true });
  } catch {
    browser = await chromium.launch({ headless: true, channel: "chrome" });
  }
  const page = await browser.newPage({ viewport: { width: 390, height: 844 } });
  await page.addInitScript(SEED(lang));
  await page.goto(URL + "/#/pilot?dev=1", { waitUntil: "networkidle", timeout: 60_000 });
  if (await page.locator(".dialog").count()) {
    await page.locator(".dialog button").first().click();
  }
  await page.waitForTimeout(500);

  const home = await page.evaluate(PROBE);
  console.log(JSON.stringify(home, null, 2));
  check("accueil : pas de débordement horizontal", home.hOverflow, false);
  check("accueil : contrôles dans les bornes (0 trouvé)", home.offBounds.length, 0);
  check("accueil : cibles tactiles <24px (0 trouvé)", home.tiny.length, 0);
  check("accueil : contrôles de taille nulle (0 trouvé)", home.zeroSize.length, 0);

  // Tap sur le bouton démarrer (action principale)
  const start = await page.evaluate(hasStartBtn);
  check("accueil : bouton démarrer présent", start, true);
  if (start) {
    await page.evaluate(clickStartBtn);
    await page.waitForTimeout(400);
    const sess = await page.evaluate(PROBE);
    console.log(JSON.stringify(sess, null, 2));
    check("séance : pas de débordement horizontal", sess.hOverflow, false);
    check("séance : contrôles dans les bornes", sess.offBounds.length, 0);
    check("séance : cibles tactiles <24px", sess.tiny.length, 0);
  }

  // Écran de mesure
  await page.goto(URL + "/#/pilot/mesure?dev=1", { waitUntil: "networkidle", timeout: 60_000 });
  await page.waitForTimeout(500);
  const mes = await page.evaluate(PROBE);
  console.log(JSON.stringify(mes, null, 2));
  check("mesure : pas de débordement horizontal", mes.hOverflow, false);
  check("mesure : contrôles dans les bornes", mes.offBounds.length, 0);
  check("mesure : cibles tactiles <24px", mes.tiny.length, 0);
  check("mesure : contrôles de taille nulle", mes.zeroSize.length, 0);

  await page.screenshot({ path: "shots/pilot-small-" + langLabel.toLowerCase() + ".png", fullPage: true });
  await browser.close();
}

await runPass("FR", "fr");
await runPass("AR-RTL", "ar");

console.log("\n" + (failures.length === 0 ? "TOUT EST CONFORME" : `${failures.length} ECHEC(S) : ${JSON.stringify(failures)}`));
process.exit(failures.length ? 1 : 0);
