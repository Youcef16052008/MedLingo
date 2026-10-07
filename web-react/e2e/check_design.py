"""Confronte les styles calculés du parcours web-react (spec §9 « identique Duolingo »)
aux valeurs Android conservées (ui/theme/Color.kt) et à la palette Duolingo.

Références :
  Color.kt            SurfaceDark #0F172A, SurfaceSubtle #F8FAFC
  spec §9             --duo-green #58CC02, --duo-blue #1CB0F6, --duo-yellow #FFC800,
                      --duo-red #FF4B4B, --duo-purple #CE82FF ;
                      bulles de leçon rondes 64-72 px, ombre portée 0 4px 0,
                      nœud verrouillé #E5E7EB, bannière d'unité texte blanc
  HomeScreen 520-553  perle : fond #FFFBEB, filet #FDE68A, rayon 20.dp, badge #FEF08A/#92400E
  HomeScreen 149-156  avatar : filet #80CBC4, cercle
"""
import json, os, sys
from playwright.sync_api import sync_playwright

URL = os.environ.get("APP_URL", "http://localhost:5173")
sys.stdout.reconfigure(encoding="utf-8", errors="replace")

PROBE = """() => {
  const cs = (sel, props) => {
    const el = document.querySelector(sel);
    if (!el) return null;
    const c = getComputedStyle(el);
    const o = {};
    props.forEach(p => o[p] = p.startsWith('--') ? c.getPropertyValue(p).trim() : c[p]);
    return o;
  };
  const one = (sel, props) => { const r = cs(sel, props); return r ? r[props[0]] : null; };
  const node = document.querySelector('.path-node');
  return {
    nightToken:    (cs(':root', ['--night']) || {})['--night'],
    bodyBg:        one('body', ['backgroundColor']),
    duoGreen:      (cs(':root', ['--duo-green']) || {})['--duo-green'],
    duoBlue:       (cs(':root', ['--duo-blue']) || {})['--duo-blue'],
    duoYellow:     (cs(':root', ['--duo-yellow']) || {})['--duo-yellow'],
    duoRed:        (cs(':root', ['--duo-red']) || {})['--duo-red'],
    duoPurple:     (cs(':root', ['--duo-purple']) || {})['--duo-purple'],
    tabCount:      document.querySelectorAll('.tabbar a').length,
    tabBadgeBg:    one('.tab-badge', ['backgroundColor']),
    tabBadgeText:  (document.querySelector('.tab-badge') || {}).textContent || null,
    dueChip:       !!document.querySelector('.stat-chip.due'),
    pearlBg:       one('.pearl', ['backgroundColor']),
    pearlBorder:   one('.pearl', ['borderTopColor']),
    pearlRadius:   one('.pearl', ['borderTopLeftRadius']),
    badgeBg:       one('.pearl-badge', ['backgroundColor']),
    badgeFg:       one('.pearl-badge', ['color']),
    avatarBorder:  one('.brand-mark', ['borderTopColor']),
    avatarRadius:  one('.brand-mark', ['borderRadius']),
    mediCount:     document.querySelectorAll('.medi').length,
    ringStroke:    one('.goal-fill', ['stroke']),
    ringTrack:     one('.goal-track', ['stroke']),
    unitCount:     document.querySelectorAll('.path-unit').length,
    nodeCount:     document.querySelectorAll('.path-node').length,
    nodeBoss:      document.querySelectorAll('.path-node.boss').length,
    nodeLocked:    document.querySelectorAll('.path-node.locked').length,
    nodeW:         node ? node.offsetWidth : 0,
    nodeH:         node ? node.offsetHeight : 0,
    nodeRadius:    one('.path-node', ['borderRadius']),
    nodeAvailBg:   one('.path-node.available', ['backgroundColor']),
    nodeShadow:    one('.path-node.available', ['boxShadow']),
    lockedBg:      one('.path-node.locked', ['backgroundColor']),
    bannerColor:   one('.unit-banner', ['color']),
    chestBadge:    !!document.querySelector('.chest-badge'),
  };
}"""

# Deux cartes dues (badge onglet Révision) + une caisse en attente (pop-up de récompense).
# Attention : corps de script exécuté directement (pas une fonction), sinon
# add_init_script évalue l'expression sans l'appeler et localStorage reste vide.
SEED = """localStorage.setItem('medlingo_web_v1', JSON.stringify({
  lang: 'fr', gems: 3, streak: 2, lastStudy: '',
  xp: 40, answered: 10, correct: 8, flashReviewed: 4, quizDone: 1,
  introSeen: true, dataVersion: 'android-ids-v1',
  pendingChests: 1,
  sm2: { '1': { rep: 1, ef: 2.5, iv: 1, due: 0 }, '2': { rep: 2, ef: 2.5, iv: 2, due: 0 } }
}));"""

failures = []

def check(label, got, want):
    ok = got == want
    print(("  OK    " if ok else "  ECHEC ") + f"{label}: {got!r}" + ("" if ok else f"  (attendu {want!r})"))
    if not ok:
        failures.append(label)

def check_in(label, needle, haystack):
    ok = needle in (haystack or "")
    print(("  OK    " if ok else "  ECHEC ") + f"{label}: {needle!r} dans {haystack!r}")
    if not ok:
        failures.append(label)

with sync_playwright() as p:
    browser = p.chromium.launch(headless=True)
    page = browser.new_page(viewport={"width": 390, "height": 844})
    page.add_init_script(SEED)
    page.goto(URL, wait_until="networkidle", timeout=60_000)
    if page.locator(".dialog").count():
        page.locator(".dialog button").first.click()
    page.locator(".path-node").first.wait_for(state="visible", timeout=20_000)
    page.wait_for_timeout(400)
    d = page.evaluate(PROBE)

    # Pop-up de récompense (badge caisse → ouverture de la caisse)
    reward = None
    if d["chestBadge"]:
        # le badge pulse (animation) → clic forcé, comme un doigt sur un bouton animé
        page.locator(".chest-badge").click(force=True)
        page.locator(".reward-card").first.wait_for(state="visible", timeout=5_000)
        page.wait_for_timeout(350)
        reward = page.evaluate("""() => {
          const c = getComputedStyle(document.querySelector('.reward-card'));
          const x = getComputedStyle(document.querySelector('.reward-xp'));
          const b = getComputedStyle(document.querySelector('.reward-chest'));
          return { cardRadius: c.borderTopLeftRadius, xpColor: x.color, chestBg: b.backgroundColor };
        }""")
        page.screenshot(path="shots/design-reward.png", full_page=True)
        page.locator(".reward-card button.btn").last.click()
        page.wait_for_timeout(250)
    browser.close()

print(json.dumps(d, indent=2, ensure_ascii=False))
if reward:
    print(json.dumps(reward, indent=2, ensure_ascii=False))

print("\n--- palette Duolingo (spec §9) ---")
check("--duo-green #58CC02", d["duoGreen"], "#58CC02")
check("--duo-blue #1CB0F6", d["duoBlue"], "#1CB0F6")
check("--duo-yellow #FFC800", d["duoYellow"], "#FFC800")
check("--duo-red #FF4B4B", d["duoRed"], "#FF4B4B")
check("--duo-purple #CE82FF", d["duoPurple"], "#CE82FF")

print("\n--- structure du parcours ---")
check("5 onglets", d["tabCount"], 5)
check("en-tête Medi", d["mediCount"], 1)
check("anneau d'objectif (piste)", d["ringTrack"], "rgb(226, 232, 240)")
check("anneau d'objectif (remplissage vert)", d["ringStroke"], "rgb(88, 204, 2)")
check("15 unités", d["unitCount"], 15)
check("90 nœuds", d["nodeCount"], 90)
check("15 nœuds BOSS", d["nodeBoss"], 15)
check("nœuds verrouillés présents", d["nodeLocked"] > 0, True)
check("bannière d'unité : texte blanc", d["bannerColor"], "rgb(255, 255, 255)")

print("\n--- bulles de leçon (spec §9 : 64-72 px, ombre 4 px) ---")
check("largeur 74 px (70 + ombre)", d["nodeW"], 74)
check("hauteur 66 px", d["nodeH"], 66)
check("forme circulaire", d["nodeRadius"], "50%")
check("fond vert #58CC02", d["nodeAvailBg"], "rgb(88, 204, 2)")
check_in("ombre portée 0 4px 0", "0px 4px 0px", d["nodeShadow"])
check("verrouillé #E5E7EB", d["lockedBg"], "rgb(229, 231, 235)")

print("\n--- fondations conservées (Color.kt) ---")
check("jeton --night = #0F172A", d["nightToken"], "#0F172A")
check("fond de page #F8FAFC", d["bodyBg"], "rgb(248, 250, 252)")
check_in("filet avatar #80CBC4", "128, 203, 196", d["avatarBorder"] or "")
check("avatar circulaire", d["avatarRadius"], "50%")

print("\n--- perle clinique (HomeScreen.kt 520-553) ---")
check("fond #FFFBEB", d["pearlBg"], "rgb(255, 251, 235)")
check("filet #FDE68A", d["pearlBorder"], "rgb(253, 230, 138)")
check("rayon 20px = 20.dp", d["pearlRadius"], "20px")
check("badge #FEF08A", d["badgeBg"], "rgb(254, 240, 138)")
check("texte badge #92400E", d["badgeFg"], "rgb(146, 64, 14)")

print("\n--- navigation ---")
check("badge de révision présent", d["tabBadgeBg"], "rgb(198, 40, 40)")
check("badge = nombre de dues", d["tabBadgeText"], "2")
check("pastille « à Réviser » présente", d["dueChip"], True)

if reward:
    print("\n--- pop-up de récompense (spec §11) ---")
    check("carte à rayon 22px", reward["cardRadius"], "22px")
    check("compteur XP vert", reward["xpColor"], "rgb(88, 204, 2)")
    check("bouton caisse jaune", reward["chestBg"], "rgb(255, 200, 0)")
else:
    failures.append("pop-up de récompense non affichée")
    print("\n  ECHEC  pop-up de récompense non affichée")

print("\n" + ("TOUT EST CONFORME" if not failures else f"{len(failures)} ECHEC(S) : {failures}"))
sys.exit(1 if failures else 0)
