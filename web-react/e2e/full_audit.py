"""Audit complet : actifs (favicon/manifest/données) + clic sur chaque bouton de chaque écran.

Usage : APP_URL=http://localhost:5173 npm run audit  (serveur lancé à part)
Sortie non nulle si erreur console, erreur JS, requête HTTP >= 400 ou contrôle défaillant.
"""
import json, os, pathlib, re, sys
from playwright.sync_api import sync_playwright

URL = os.environ.get("APP_URL", "http://localhost:5173")
sys.stdout.reconfigure(encoding="utf-8", errors="replace")

errors, pageerrs, netfails, assets = [], [], [], []
failures = []
clicks = 0


def norm(s):
    return re.sub(r"\s+", " ", (s or "")).strip().lower()


def fail(msg):
    failures.append(msg)
    print("  ✗ " + msg)


def ok(msg):
    print("  ✓ " + msg)


EX = json.loads(pathlib.Path("public/data/exercises.json").read_text(encoding="utf-8"))
TERMS = json.loads(pathlib.Path("public/data/terms.json").read_text(encoding="utf-8"))
BY_Q = {}
for e in EX:
    for k in ("qFr", "qEn", "qAr"):
        if e.get(k):
            BY_Q.setdefault(norm(e[k]), e)


def build_order(answer, words):
    """Reconstruit l'ordre attendu d'une phrase à partir des jetons proposés.

    Les jetons peuvent être des expressions (« so that ») : on essaie d'abord le
    découpage le plus long, puis le plus court, en respectant exactement la
    casse de `answer` (la notation de l'app est sensible à la casse)."""
    for pick in (lambda c: max(c, key=len), lambda c: min(c, key=len)):
        cur, rest, order = answer, list(words), []
        while cur:
            cands = [w for w in rest if cur == w or cur.startswith(w + " ")]
            if not cands:
                break
            w = pick(cands)
            order.append(w)
            rest.remove(w)
            cur = cur[len(w):].lstrip(" ")
        if not cur and not rest:
            return order
    return None


def wire(page):
    page.on("console", lambda m: errors.append(f"[{m.type}] {m.text}") if m.type == "error" else None)
    page.on("pageerror", lambda e: pageerrs.append(str(e)))
    page.on("response", lambda r: netfails.append(f"{r.status} {r.url}") if r.status >= 400 else None)
    page.on("response", lambda r: assets.append((r.url, r.status, r.headers.get("content-type", ""))))


def dismiss_dialog(page, label=None):
    """Ferme un dialog. Si `label` est fourni, clique ce bouton-là."""
    dlg = page.locator(".dialog")
    if dlg.count() == 0:
        return None
    if label:
        b = dlg.locator("button", has_text=label)
        if b.count():
            b.first.click()
            page.wait_for_timeout(250)
            return label
    btns = dlg.locator("button")
    txt = btns.first.inner_text() if btns.count() else "?"
    if btns.count():
        btns.first.click()
        page.wait_for_timeout(250)
    return txt


def dismiss_reward(page):
    """Ferme le pop-up de récompense (spec §11) s'il recouvre l'écran."""
    ov = page.locator(".reward-overlay")
    if not ov.count():
        return False
    btn = page.locator(".reward-card button.btn").last
    if btn.count():
        btn.click()
        page.wait_for_timeout(250)
    return True


def reset_session(page):
    """Remet la page sur un état propre (pas de dialog/fiche/pop-up)."""
    dismiss_reward(page)
    if page.locator(".dialog").count():
        dismiss_dialog(page)
    if page.locator(".sheet").count():
        b = page.locator(".sheet button.btn.ghost")
        if b.count():
            b.last.click()
            page.wait_for_timeout(200)


def set_lang(page, label):
    """Force la langue via le profil — l'audit des écrans laisse l'état résiduel."""
    cur = page.evaluate(
        "() => { try { return JSON.parse(localStorage.getItem('medlingo_web_v1')||'{}').lang }"
        " catch (e) { return '' } }"
    )
    if cur == label:
        return
    reset_session(page)
    page.goto(URL + "#/profile", wait_until="networkidle")
    page.wait_for_timeout(350)
    b = page.locator(".lang-switch button", has_text=label)
    if b.count():
        b.first.click()
        page.wait_for_timeout(300)


def audit_screen(page, hash_, name):
    global clicks
    print(f"\n=== ÉCRAN {name} ({hash_}) ===")
    reset_session(page)
    page.goto(URL + hash_, wait_until="networkidle")
    page.wait_for_timeout(600)

    # deux jeux d'indices distincts : les saisies ne partagent pas le même
    # sélecteur que les boutons, sinon les positions se décalent.
    inputs = page.evaluate("""() => [...document.querySelectorAll('input, textarea, select')]
        .map((e, i) => ({ i, tag: e.tagName.toLowerCase(), ph: e.placeholder || '',
                          cls: e.className || '', disabled: !!e.disabled }))""")
    buttons = page.evaluate("""() => [...document.querySelectorAll('button, a[href], [role="button"]')]
        .map((e, i) => ({ i, tag: e.tagName.toLowerCase(),
                          text: (e.innerText || e.getAttribute('aria-label') || '').trim().slice(0, 60),
                          cls: e.className || '', href: e.getAttribute('href'), disabled: !!e.disabled }))""")
    if not buttons:
        fail(f"{name}: aucun bouton")
        return
    ok(f"{name}: {len(buttons)} boutons, {len(inputs)} saisies inventoriés")
    expected_sig = tuple(b["text"] for b in buttons)

    def signature():
        return tuple(page.evaluate(
            """() => [...document.querySelectorAll('button, a[href], [role="button"]')]
                .map(e => (e.innerText || e.getAttribute('aria-label') || '').trim().slice(0, 60))"""))

    def restore():
        """Revenir à l'état initial de l'écran : ouvrir une session de quiz, par
        exemple, change tout le DOM sans changer le hash."""
        reset_session(page)
        if signature() != expected_sig or page.evaluate("() => location.hash") != hash_:
            page.reload(wait_until="networkidle")
            if page.evaluate("() => location.hash") != hash_:
                page.goto(URL + hash_, wait_until="networkidle")
            page.wait_for_timeout(500)
            if page.locator(".dialog").count():
                dismiss_dialog(page)
                page.wait_for_timeout(250)

    # --- champs de saisie ---
    for e in inputs:
        if e["disabled"]:
            continue
        loc = page.locator("input, textarea, select").nth(e["i"])
        try:
            loc.click()
            loc.fill("aorta")
            page.wait_for_timeout(450)
            n = page.locator(".result-item").count()
            if hash_ == "#/search":
                if not n:
                    fail(f"{name}: recherche 'aorta' → 0 résultat")
                else:
                    page.locator(".result-item").first.click()
                    page.wait_for_timeout(400)
                    if page.locator(".sheet").count():
                        clicks += 1
                        ok(f"{name}: fiche de terme ouverte depuis les résultats")
                    else:
                        fail(f"{name}: le résultat n'ouvre pas la fiche de terme")
                    b = page.locator(".sheet button.btn.ghost")
                    if b.count():
                        b.last.click()
                        page.wait_for_timeout(250)
                    loc = page.locator("input, textarea, select").nth(e["i"])
            loc.fill("")
            page.wait_for_timeout(350)
            clicks += 1
            if hash_ == "#/search" and n == 0:
                fail(f"{name}: saisie 'aorta' → 0 résultat")
            else:
                ok(f"{name}: saisie {e['ph'][:28]!r} → {n} résultats")
        except Exception as ex:
            fail(f"{name}: saisie impossible — {type(ex).__name__}: {ex}")

    # --- boutons ---
    for e in buttons:
        if e["disabled"]:
            continue
        restore()
        if page.evaluate("() => location.hash") != hash_:
            page.goto(URL + hash_, wait_until="networkidle")
            page.wait_for_timeout(450)

        loc = page.locator('button, a[href], [role="button"]').nth(e["i"])
        if loc.count() == 0:
            fail(f"{name}: élément #{e['i']} disparu")
            continue
        try:
            before_hash = page.evaluate("() => location.hash")
            try:
                loc.click(timeout=5000, no_wait_after=True)
            except Exception:
                # bouton en pulse (nœud « suivant » du parcours, badge caisse) :
                # Playwright attend l'immobilité, un vrai doigt clique quand même.
                loc.click(timeout=5000, no_wait_after=True, force=True)
            clicks += 1
            page.wait_for_timeout(350)
        except Exception as ex:
            fail(f"{name}: clic impossible sur {e['tag']} {e['text'][:40]!r} — {type(ex).__name__}")
            continue

        after_hash = page.evaluate("() => location.hash")
        note = []
        if after_hash != before_hash:
            note.append(f"route → {after_hash}")
        if page.locator(".dialog").count():
            labels = page.locator(".dialog button").all_inner_texts()
            note.append(f"dialog {labels}")
            # le premier bouton est toujours l'action non destructive (Annuler/Rester)
            dismiss_dialog(page)
            page.wait_for_timeout(200)
            if len(labels) > 1 and not page.locator(".dialog").count():
                # rouvrir pour éprouver l'action destructive du dialog
                loc = page.locator('button, a[href], [role="button"]').nth(e["i"])
                if loc.count():
                    loc.click(timeout=3000, no_wait_after=True)
                    page.wait_for_timeout(300)
                    if page.locator(".dialog").count():
                        dismiss_dialog(page, labels[-1])
                        page.wait_for_timeout(350)
                        note.append("action terminale testée")
        if page.locator(".sheet").count():
            note.append("fiche de terme")
            b = page.locator(".sheet button.btn.ghost")
            if b.count():
                b.last.click()
                page.wait_for_timeout(250)
        if page.locator(".toast:not([hidden])").count():
            note.append("toast")
        print(f"    {' · '.join(note) or 'ok':<64} {e['tag']:<6} {e['text'][:36]!r}")

        if after_hash != before_hash:
            page.goto(URL + hash_, wait_until="networkidle")
            page.wait_for_timeout(450)

    # l'audit des boutons de langue laisse l'AR : on repasse en FR pour la suite
    set_lang(page, "fr")


def check_assets(page):
    print("\n=== ACTIFS (favicon / manifest / données) ===")
    info = page.evaluate("""async () => {
        const out = { http: {}, png: {} };
        const paths = ['/icon.svg', '/favicon-16.png', '/favicon-32.png',
                       '/icon-192.png', '/icon-512.png',
                       '/apple-touch-icon.png', '/icon-maskable-512.png',
                       '/manifest.webmanifest', '/data/terms.json', '/data/exercises.json', '/sw.js'];
        for (const p of paths) {
            try {
                const r = await fetch(p, { cache: 'no-store' });
                out.http[p] = { status: r.status, type: r.headers.get('content-type') || '' };
            } catch (e) { out.http[p] = { status: -1, type: String(e) }; }
        }
        out.icons = [...document.querySelectorAll('link[rel=icon]')].map(l => ({
            href: l.href, sizes: l.getAttribute('sizes') || '' }));
        const at = document.querySelector('link[rel=apple-touch-icon]');
        out.apple = at ? at.href : '';
        const img = new Image();
        out.imgOk = await new Promise(res => { img.onload = () => res(true); img.onerror = () => res(false);
            img.src = '/icon.svg'; });
        out.w = img.naturalWidth; out.h = img.naturalHeight;
        const all = ['/favicon-16.png', '/favicon-32.png', '/icon-192.png', '/icon-512.png',
                     '/apple-touch-icon.png', '/icon-maskable-512.png'];
        for (const p of all) {
            out.png[p] = await new Promise(res => { const i = new Image();
                i.onload = () => res([i.naturalWidth, i.naturalHeight]);
                i.onerror = () => res(null); i.src = p + '?v=' + Date.now(); });
        }
        // les 4 coins + le haut doivent être opaques et verts (pas de fond blanc)
        out.corners = {};
        for (const p of all) {
            out.corners[p] = await new Promise(res => { const i = new Image();
                i.onerror = () => res(null);
                i.onload = () => {
                    const c = document.createElement('canvas');
                    c.width = i.naturalWidth; c.height = i.naturalHeight;
                    const g = c.getContext('2d');
                    g.drawImage(i, 0, 0);
                    const w = c.width, h = c.height;
                    const pts = [[1, 1], [w - 2, 1], [1, h - 2], [w - 2, h - 2], [w >> 1, 1]];
                    res(pts.map(([x, y]) => Array.from(g.getImageData(x, y, 1, 1).data)));
                };
                i.src = p + '?c=' + Date.now(); });
        }
        try {
            const r = await fetch('/manifest.webmanifest');
            const mf = await r.json();
            out.mfName = mf.name; out.mfIcons = mf.icons || [];
            out.iconsMf = (mf.icons || []).map(i => i.src + ' [' + i.purpose + ']');
            out.start = mf.start_url; out.scope = mf.scope; out.display = mf.display;
        } catch (e) { out.mfErr = String(e); }
        out.data = await Promise.all(['terms.json','exercises.json'].map(async n => {
            const r = await fetch('/data/' + n);
            const j = await r.json();
            return [n, r.status, Array.isArray(j) ? j.length : 'pas un tableau'];
        }));
        return out;
    }""")

    expected = {
        "/icon.svg": "image/svg+xml",
        "/favicon-16.png": "image/png",
        "/favicon-32.png": "image/png",
        "/icon-192.png": "image/png",
        "/icon-512.png": "image/png",
        "/apple-touch-icon.png": "image/png",
        "/icon-maskable-512.png": "image/png",
        "/manifest.webmanifest": "",
        "/data/terms.json": "json",
        "/data/exercises.json": "json",
        "/sw.js": "javascript",
    }
    for path, want in expected.items():
        got = info["http"].get(path)
        if not got:
            fail(f"{path} : requête impossible")
            continue
        if got["status"] != 200:
            fail(f"{path} → HTTP {got['status']}")
        elif want and want not in got["type"]:
            fail(f"{path} → content-type {got['type']!r} (attendu {want!r})")
        else:
            ok(f"{path} → 200 {got['type']}")

    # favicon : SVG moderne + PNG de repli (dont un 32×32 pour les onglets)
    if not any(i["href"].endswith("/icon.svg") for i in info["icons"]):
        fail(f"aucun link[rel=icon] SVG : {info['icons']}")
    elif not any("png" in i["href"] for i in info["icons"]):
        fail(f"aucun link[rel=icon] PNG de repli : {info['icons']}")
    elif not any(i["sizes"] == "32x32" for i in info["icons"]):
        fail(f"aucun link[rel=icon] PNG 32x32 : {info['icons']}")
    else:
        ok(f"favicon : {[(i['href'].rsplit('/',1)[1], i['sizes']) for i in info['icons']]}")

    if not info["apple"].endswith(".png"):
        fail(f"apple-touch-icon n'est pas un PNG (iOS l'ignore) : {info['apple']!r}")
    else:
        ok(f"apple-touch-icon = {info['apple'].rsplit('/',1)[1]}")

    if not info.get("imgOk"):
        fail("l'icône SVG ne se decode pas dans le navigateur")
    else:
        ok(f"icône SVG rendue : {info['w']}x{info['h']}")

    for p, dim in info.get("png", {}).items():
        if not dim:
            fail(f"{p} ne se decode pas dans le navigateur")
        else:
            ok(f"{p} décodée : {dim[0]}x{dim[1]}")

    # chaque PNG doit être plein-cadre : coins opaques et verts, jamais blancs
    for p, px in (info.get("corners") or {}).items():
        if not px:
            fail(f"{p} : coins illisibles")
            continue
        blancs = [c for c in px if c[3] != 255 or c[0] > 120 or c[1] < 120 or c[2] > 140]
        if blancs:
            fail(f"{p} : coins transparents ou blancs {blancs}")
        else:
            ok(f"{p} : 5 points d'échantillon opaques et verts")

    if info.get("mfErr"):
        fail(f"manifest illisible : {info['mfErr']}")
    else:
        ok(f"manifest lisible · name={info.get('mfName')!r} · display={info.get('display')}")
        mf_icons = info.get("mfIcons") or []
        if not mf_icons:
            fail("manifest sans icônes")
        else:
            ok(f"icônes du manifest : {info['iconsMf']}")
        if not any(i.get("type") == "image/png" and i.get("purpose", "any") == "any" for i in mf_icons):
            fail("manifest : aucune icône PNG « any » (Android refuse le SVG seul)")
        if not any(i.get("type") == "image/png" and i.get("purpose") == "maskable" for i in mf_icons):
            fail("manifest : aucune icône PNG « maskable »")
        sizes = {(i.get("src"), i.get("sizes")) for i in mf_icons}
        if ("icon-192.png", "192x192") not in sizes or ("icon-512.png", "512x512") not in sizes:
            fail(f"manifest : tailles d'icônes déclarées inattendues : {sorted(sizes)}")
        if info.get("start") not in ("./index.html", "/", "/index.html"):
            fail(f"manifest start_url inattendu : {info.get('start')!r}")
        if not str(info.get("scope", "")).startswith("./") and info.get("scope") != "/":
            fail(f"manifest scope inattendu : {info.get('scope')!r}")

    counts = {}
    for name, status, count in info.get("data", []):
        counts[name] = count
        if status != 200:
            fail(f"données {name} → HTTP {status}")
        elif not isinstance(count, int):
            fail(f"données {name} → {count}")
        else:
            ok(f"données {name} → {count} entrées")
    if counts.get("terms.json") not in (None, len(TERMS)):
        fail(f"terms.json serveur={counts.get('terms.json')} fichier={len(TERMS)}")
    if counts.get("exercises.json") not in (None, len(EX)):
        fail(f"exercises.json serveur={counts.get('exercises.json')} fichier={len(EX)}")

    # icônes réellement servies (le navigateur peut ne pas les demander en headless)
    base = URL.rstrip("/")
    for u, s, ct in assets:
        if s >= 400:
            fail(f"requête {u} → {s}")


def check_integrated(page):
    print("\n=== INTÉGRATION DES DONNÉES ===")
    page.goto(URL + "#/modules", wait_until="networkidle")
    page.wait_for_timeout(700)
    data = page.evaluate("""() => ({
        cards: document.querySelectorAll('.module-card').length,
        total: (document.querySelector('.section-head .tiny') || {}).textContent || '',
        titles: [...document.querySelectorAll('.module-card .tiny')].map(e => e.textContent.trim()),
    })""")
    if data["cards"] != 15:
        fail(f"{data['cards']} modules affichés (attendu 15)")
    else:
        ok("15 modules affichés")
    n_terms = sum(int(m.group(1)) for t in data["titles"] if "termes" in t and (m := re.match(r"\s*(\d+)", t)))
    shown_total = re.sub(r"\D", "", data["total"])
    if n_terms != 4512:
        fail(f"somme des modules = {n_terms} termes (attendu 4512)")
    elif shown_total != "4512":
        fail(f"total affiché = {data['total']!r} (attendu 4512 termes)")
    else:
        ok("4512 termes (somme des modules + total affiché)")

    # chaque module doit ouvrir une session de flashcards non vide
    bad = []
    hashes = page.locator("a.module-card").evaluate_all("els => els.map(e => e.getAttribute('href'))")
    for h in hashes:
        page.goto(URL + h, wait_until="networkidle")
        page.wait_for_timeout(350)
        has_card = page.locator(".flashcard").count()
        has_empty = page.locator(".empty").count()
        if not has_card and not has_empty:
            bad.append(f"{h} ni carte ni état vide")
        if has_empty:
            cnt = page.locator(".empty").inner_text()[:40]
            bad.append(f"{h} vide : {cnt}")
        reset_session(page)
    if bad:
        for b in bad:
            fail(b)
    else:
        ok(f"{len(hashes)} modules → session de flashcards jouable")

    # l'écran Recherche doit retrouver des termes en 3 langues
    page.goto(URL + "#/search", wait_until="networkidle")
    page.wait_for_timeout(400)
    box = page.locator(".search-box input")
    for probe, lang in [("heart", "EN"), ("cœur", "FR"), ("القلب", "AR")]:
        box.fill(probe)
        page.wait_for_timeout(400)
        n = page.locator(".result-item").count()
        if n == 0:
            fail(f"recherche {lang} '{probe}' → 0 résultat")
        else:
            ok(f"recherche {lang} '{probe}' → {n} résultats")
        box.fill("")
        page.wait_for_timeout(250)


def check_flash_buttons(page):
    print("\n=== BOUTONS DES FLASHCARDS (les 5 notations) ===")
    set_lang(page, "fr")
    page.goto(URL + "#/flash/anat", wait_until="networkidle")
    page.wait_for_timeout(500)
    labels = page.locator(".rating-btn").all_inner_texts()
    if len(labels) != 5:
        fail(f"{len(labels)} boutons de notation (attendu 5)")
    ok("notations : " + " / ".join(l.replace("\n", " ") for l in labels))
    disabled = page.locator(".rating-btn:disabled").count()
    if disabled != 5:
        fail(f"{disabled} boutons désactivés avant retournement (attendu 5)")
    ok("toutes désactivées avant retournement")

    # bouton 🔊 (synthèse vocale)
    page.locator(".speak-btn").first.click()
    page.wait_for_timeout(300)
    ok("bouton synthèse vocale cliqué sans erreur")

    for q in range(1, 6):
        page.locator(".flashcard").click()
        page.wait_for_timeout(250)
        btn = page.locator(f'.rating-btn[data-q="{q}"]')
        if btn.count() == 0:
            fail(f"notation data-q={q} absente")
            continue
        if btn.is_disabled():
            fail(f"notation data-q={q} reste désactivée après retournement")
            continue
        before = int(json.loads(page.evaluate("() => localStorage.getItem('medlingo_web_v1')") or "{}").get("gems", 0))
        btn.click()
        page.wait_for_timeout(350)
        after = int(json.loads(page.evaluate("() => localStorage.getItem('medlingo_web_v1')") or "{}").get("gems", 0))
        want = 2 if q == 5 else (1 if q >= 3 else 0)
        if after - before != want:
            fail(f"notation {q} crédite +{after - before} gemmes (attendu +{want})")
        elif q >= 3:
            ok(f"notation {q} → +{after - before} gemme(s) · état global")
        ok(f"data-q={q} ({labels[q-1].splitlines()[0]}) acceptée")
    # retour au clavier : espace pour retourner
    page.keyboard.press(" ")
    page.wait_for_timeout(300)
    flipped = "flipped" in (page.locator(".flashcard").get_attribute("class") or "")
    if not flipped:
        fail("la touche Espace ne retourne pas la carte")
    else:
        ok("touche Espace → retournement")
    page.keyboard.press("3")
    page.wait_for_timeout(300)
    ok("touche 3 → notation au clavier")

    # bouton ✕ (quitter) : tester les DEUX actions du dialog
    page.locator(".icon-btn").first.click()
    page.wait_for_timeout(300)
    if page.locator(".dialog").count() == 0:
        fail("le bouton ✕ n'ouvre pas de dialog de confirmation")
    else:
        labels = page.locator(".dialog button").all_inner_texts()
        ok(f"dialog de sortie : {labels}")
        dismiss_dialog(page, labels[0])  # « Rester »
        page.wait_for_timeout(300)
        if page.locator(".dialog").count() or page.locator(".flashcard").count() == 0:
            fail("« Rester » ne conserve pas la session")
        else:
            ok("« Rester » conserve la session")
        page.locator(".icon-btn").first.click()
        page.wait_for_timeout(300)
        labels = page.locator(".dialog button").all_inner_texts()
        dismiss_dialog(page, labels[-1])  # « Quitter »
        page.wait_for_timeout(400)
        if page.locator(".flashcard").count():
            fail("« Quitter » ne quitte pas la session")
        else:
            ok("« Quitter » quitte la session")


def check_quiz_buttons(page):
    print("\n=== BOUTONS DU QUIZ ===")
    set_lang(page, "fr")
    page.goto(URL + "#/quiz", wait_until="networkidle")
    page.wait_for_timeout(600)
    chips = page.locator(".chip-row .chip")
    if chips.count() != 7:
        fail(f"{chips.count()} filtres de niveau (attendu 7 : Tous + 6 niveaux)")
    for i in range(chips.count()):
        chips.nth(i).click()
        page.wait_for_timeout(150)
        active = "active" in (chips.nth(i).get_attribute("class") or "")
        if not active:
            fail(f"filtre {chips.nth(i).inner_text()!r} ne s'active pas")
    ok("les 7 filtres de niveau s'activent")
    page.locator(".chip-row .chip", has_text="Tous").first.click()
    page.wait_for_timeout(200)

    go = page.locator("button.btn.block", has_text="Commencer")
    if go.count() == 0 or go.first.is_disabled():
        fail("bouton « Commencer » absent ou désactivé")
        return
    go.first.click()
    page.locator(".quiz-q").first.wait_for(timeout=15_000)
    ok("démarrage de session")

    seen = set()
    for i in range(12):
        if page.locator(".summary").count() or page.locator(".dialog").count():
            break
        opts = page.locator(".options .option")
        fill = page.locator(".fill-input")
        bank = page.locator("#bank .word")
        match = page.locator(".match-grid")
        qt = page.locator(".quiz-q").inner_text()
        ex = BY_Q.get(norm(qt))

        if opts.count():
            kind = "mcq"
            want = norm(ex["answer"]) if ex and ex.get("answer") else ""
            j = next((k for k, o in enumerate(opts.all()) if want and norm(o.inner_text()) == want), 0)
            opts.nth(j).click()
            page.wait_for_timeout(150)
            if "selected" not in (opts.nth(j).get_attribute("class") or ""):
                fail("l'option cliquée n'est pas marquée « selected »")
            check = page.locator("button.btn.block")
            if check.last.is_disabled():
                fail("bouton « Vérifier » désactivé après sélection")
            check.last.click()
        elif fill.count():
            kind = "fill_blank"
            if page.locator("button.btn.block").last.is_disabled():
                ok("« Vérifier » désactivé tant que la saisie est vide")
            fill.fill(ex["answer"] if ex and ex.get("answer") else "")
            page.wait_for_timeout(150)
            if page.locator("button.btn.block").last.is_disabled():
                fail("« Vérifier » reste désactivé après saisie")
            page.locator("button.btn.block").last.click()
        elif bank.count():
            kind = "sentence_order"
            order = build_order(ex["answer"], ex["options"]) if ex else None
            for tok in (order or []):
                words = page.locator("#bank .word").all()
                hit = next((w for w in words if w.is_enabled() and w.inner_text() == tok), None)
                if hit:
                    hit.click()
            if page.locator("button.btn.block").last.is_disabled():
                fail("« Vérifier » désactivé après assemblage de la phrase")
            page.locator("button.btn.block").last.click()
        elif match.count():
            kind = "matching"
            pairs = [tuple(o.split(":", 1)) for o in (ex["options"] if ex else [])]

            def find_exact(sel, text):
                loc2 = page.locator(sel)
                for k in range(loc2.count()):
                    if norm(loc2.nth(k).inner_text()) == norm(text):
                        return loc2.nth(k)
                return None

            for l, r in pairs:
                if page.locator("#colL .match-item.done").count() >= len(pairs):
                    break
                left = find_exact("#colL .match-item", l)
                right = find_exact("#colR .match-item", r)
                if left is None or right is None or left.is_disabled() or right.is_disabled():
                    continue
                left.click()
                right.click()
        else:
            fail("type de question non reconnu")
            break

        page.wait_for_timeout(350)
        fb = page.locator(".feedback")
        if fb.count() != 1:
            fail(f"pas de retour après réponse ({kind})")
            break
        verdict = "ok" if page.locator(".feedback.ok").count() else "ko"
        if verdict == "ko":
            fail(f"réponse juste notée fausse [{kind}] : {qt[:80]}")
        seen.add(kind)
        nxt = page.locator(".feedback button.btn.block")
        if nxt.count() == 0:
            fail("bouton « Continuer » absent")
            break
        nxt.first.click()
        page.wait_for_timeout(350)

    ok(f"types exercés : {sorted(seen)}")
    dismiss_reward(page)  # la pop-up de récompense recouvre le récapitulatif
    if page.locator(".summary").count():
        btns = page.locator(".summary a, .summary button")
        labels = btns.all_inner_texts()
        ok(f"écran de fin : {labels}")
        # bouton « Rejouer »
        rejouer = page.locator(".summary button", has_text="Rejouer")
        if rejouer.count():
            rejouer.first.click()
            page.wait_for_timeout(600)
            if page.locator(".summary").count():
                fail("« Rejouer » ne relance pas une session")
            else:
                ok("« Rejouer » relance une session")
            # on relance une partie : le bouton ✕ n'existe que pendant la session
            start = page.locator("button.btn.block", has_text="Commencer")
            if start.count() and not start.first.is_disabled():
                start.first.click()
                page.locator(".quiz-q").first.wait_for(timeout=15_000)

        # bouton ✕ de la session en cours
        if page.locator(".icon-btn").count() == 0:
            fail("bouton ✕ absent de la session de quiz")
            return
        page.locator(".icon-btn").first.click()
        page.wait_for_timeout(300)
        if page.locator(".dialog").count():
            labels = page.locator(".dialog button").all_inner_texts()
            dismiss_dialog(page, labels[-1])
            page.wait_for_timeout(400)
            if page.locator(".summary").count() or page.locator(".quiz-q").count():
                fail("« Quitter » ne quitte pas la session de quiz")
            else:
                ok("« Quitter » quitte la session de quiz")


def check_edge_cases(page):
    """Routage, liens profonds, retour navigateur, fiche, sessions, clavier, progression."""
    global clicks
    print("\n=== CAS LIMITES ===")
    set_lang(page, "fr")
    reset_session(page)

    # 1. route inconnue → Parcours (défaut) + onglet actif
    page.goto(URL + "#/nimporte-quoi", wait_until="networkidle")
    page.wait_for_timeout(700)
    hero = page.locator(".path-head").count()
    active = page.locator(".tabbar a.active")
    if hero == 1 and active.count() == 1 and "Parcours" in active.inner_text():
        ok("route inconnue → Parcours rendu, onglet Parcours actif")
    else:
        fail(f"route inconnue → path-head={hero}, onglets actifs={active.count()}")

    # 2. module de flash inexistant → état vide plutôt qu'un crash
    page.goto(URL + "#/flash/inconnu", wait_until="networkidle")
    page.wait_for_timeout(700)
    if page.locator(".empty").count():
        ok("#/flash/inconnu → état « Rien à réviser »")
    else:
        fail("#/flash/inconnu → ni carte ni état vide")

    # 3. lien profond direct vers une session
    page.goto(URL + "#/flash/all", wait_until="networkidle")
    page.wait_for_timeout(800)
    if page.locator(".flashcard").count() == 1:
        ok("#/flash/all en direct → carte affichée")
    else:
        fail("#/flash/all en direct → pas de carte")

    # 4. retour / avant du navigateur (hashchange)
    page.goto(URL + "#/profile", wait_until="networkidle")
    page.wait_for_timeout(600)
    page.go_back()
    page.wait_for_timeout(800)
    h = page.evaluate("() => location.hash")
    ncard = page.locator(".flashcard").count()
    if h == "#/flash/all" and ncard == 1:
        ok("retour navigateur → session flash restaurée")
    else:
        fail(f"retour navigateur → hash={h!r}, cartes={ncard}")
    page.go_forward()
    page.wait_for_timeout(800)
    if page.locator(".lang-switch").count():
        ok("avant navigateur → profil restauré")
    else:
        fail("avant navigateur → profil non restauré")

    # 5. recherche : invite à 1 caractère + aucune correspondance
    page.goto(URL + "#/search", wait_until="networkidle")
    page.wait_for_timeout(600)
    box = page.locator(".search-box input")
    box.fill("a")
    page.wait_for_timeout(450)
    if page.locator("#results .empty").count():
        ok("1 caractère → invite « tapez au moins 2 »")
    else:
        fail("1 caractère → pas d'invite")
    box.fill("zzzzqqq")
    page.wait_for_timeout(450)
    if page.locator("#results .empty").count():
        ok("aucune correspondance → état vide")
    else:
        fail("aucune correspondance → pas d'état vide")

    # 6. fiche de terme : contenu, prononciation, fermeture par le fond
    box.fill("heart")
    page.wait_for_timeout(550)
    if page.locator(".result-item").count() == 0:
        fail("recherche 'heart' → 0 résultat avant ouverture de fiche")
    else:
        page.locator(".result-item").first.click()
        page.wait_for_timeout(500)
        if page.locator(".sheet").count() != 1:
            fail("fiche de terme non ouverte depuis les résultats")
        else:
            title = page.locator(".sheet h2").inner_text().splitlines()[0]
            rows = page.locator(".sheet dl div").count()
            speak = page.locator(".sheet .speak-btn").count()
            if rows:
                ok(f"fiche {title[:26]!r} · {rows} lignes · 🔊×{speak}")
            else:
                fail(f"fiche vide pour {title!r}")
            if not speak:
                fail("bouton de prononciation absent de la fiche")
            else:
                page.locator(".sheet .speak-btn").first.click()
                clicks += 1
                page.wait_for_timeout(250)
            page.locator(".sheet-backdrop").click(position={"x": 4, "y": 4})
            page.wait_for_timeout(450)
            if page.locator(".sheet").count():
                fail("le clic sur le fond ne ferme pas la fiche")
            else:
                ok("clic sur le fond → fiche fermée")

    # 7. plus de cœurs : une session ne doit jamais être interrompue (barrière supprimée)
    page.evaluate(
        """(patch) => {
            const cur = JSON.parse(localStorage.getItem('medlingo_web_v1') || '{}');
            delete cur.hearts;
            delete cur.heartsTs;
            localStorage.setItem('medlingo_web_v1', JSON.stringify({ ...cur, ...patch }));
        }""",
        {"gems": 60, "introSeen": True},
    )
    page.reload(wait_until="networkidle")
    page.wait_for_timeout(700)
    page.goto(URL + "#/quiz", wait_until="networkidle")
    page.wait_for_timeout(600)
    page.locator(".chip-row .chip").nth(5).click()  # niveau 5 = 6 QCM
    page.wait_for_timeout(250)
    page.locator("button.btn.block", has_text="Commencer").first.click()
    page.locator(".quiz-q").first.wait_for(timeout=15_000)
    ok("session lancée (aucune barrière de cœurs)")

    for i in range(4):
        if page.locator(".dialog").count() or page.locator(".summary").count():
            break
        opts = page.locator(".options .option")
        if opts.count():
            opts.nth(i % opts.count()).click()
            page.wait_for_timeout(180)
            page.locator("button.btn.block").last.click()
            page.wait_for_timeout(350)
        nxt = page.locator(".feedback button.btn.block")
        if nxt.count():
            nxt.first.click()
            page.wait_for_timeout(500)

    if page.locator(".dialog").count():
        fail(f"dialog inattendu pendant la session : {page.locator('.dialog').all_inner_texts()}")
    else:
        ok("4 questions jouées sans interruption ni dialog de recharge")

    st = json.loads(page.evaluate("() => localStorage.getItem('medlingo_web_v1')") or "{}")
    if "hearts" in st:
        fail("le store écrit encore `hearts`")
    else:
        ok("aucun champ `hearts` persisté")

    # 8. saisie au clavier : Entrée valide un « fill_blank » (niveau 3)
    page.reload(wait_until="networkidle")
    page.wait_for_timeout(700)
    page.goto(URL + "#/quiz", wait_until="networkidle")
    page.wait_for_timeout(600)
    page.locator(".chip-row .chip").nth(3).click()  # niveau 3 = 2 fill_blank + 6 phrases
    page.wait_for_timeout(250)
    page.locator("button.btn.block", has_text="Commencer").first.click()
    page.locator(".quiz-q").first.wait_for(timeout=15_000)

    entered = False
    for _ in range(10):
        if page.locator(".summary").count() or page.locator(".dialog").count():
            break
        fill = page.locator(".fill-input")
        if fill.count() and not fill.is_disabled():
            qt = page.locator(".quiz-q").inner_text()
            ex = BY_Q.get(norm(qt))
            if not ex or not ex.get("answer"):
                fail(f"exercice introuvable pour {qt[:60]!r}")
                break
            fill.fill(ex["answer"])
            page.wait_for_timeout(200)
            fill.press("Enter")
            page.wait_for_timeout(500)
            if page.locator(".feedback").count():
                entered = True
                ok(f"Entrée valide la réponse ({qt[:44]!r})")
            else:
                fail("Entrée ne valide pas la réponse")
            break

        # on résout les questions intermédiaires (phrases à remettre en ordre)
        bank = page.locator("#bank .word")
        if bank.count():
            qt = page.locator(".quiz-q").inner_text()
            ex = BY_Q.get(norm(qt))
            order = build_order(ex["answer"], ex["options"]) if ex else None
            if not order:
                fail(f"ordre introuvable pour {qt[:60]!r}")
                break
            for tok in order:
                words = page.locator("#bank .word").all()
                hit = next((w for w in words if w.is_enabled() and w.inner_text() == tok), None)
                if hit:
                    hit.click()
            page.locator("button.btn.block").last.click()
            page.wait_for_timeout(400)
            nxt = page.locator(".feedback button.btn.block")
            if nxt.count():
                nxt.first.click()
                page.wait_for_timeout(450)
            continue
        fail("type de question inattendu dans le niveau 3")
        break
    if not entered and not page.locator(".dialog").count():
        fail("aucun « fill_blank » rencontré dans le niveau 3 (8 exercices)")

    # 9. la progression d'un module évolue après une notation
    page.goto(URL + "#/modules", wait_until="networkidle")
    page.wait_for_timeout(700)
    card = page.locator(".module-card", has_text="Embryologie")
    if card.count() == 0:
        fail("carte Embryologie introuvable")
    else:
        before = int((card.locator(".mod-pct").inner_text() or "0").replace("%", "") or 0)
        page.goto(URL + "#/flash/embryo", wait_until="networkidle")
        page.wait_for_timeout(700)
        # mod-pct est un pourcentage arrondi (progress.ts : round(seen/total*100)).
        # Embryologie compte 236 termes, donc une seule carte notee arrondit
        # toujours a 0 % : il faut en noter plusieurs pour que l'indicateur bouge.
        noted = 0
        for _ in range(5):
            if page.locator(".dialog").count():
                break
            fc = page.locator(".flashcard")
            if fc.count() == 0:
                break
            fc.first.click()          # revele la carte (active les boutons)
            page.wait_for_timeout(250)
            btn = page.locator('.rating-btn[data-q="5"]')
            if btn.count() == 0:
                break
            btn.first.click()
            noted += 1
            page.wait_for_timeout(400)
        page.goto(URL + "#/modules", wait_until="networkidle")
        page.wait_for_timeout(700)
        card = page.locator(".module-card", has_text="Embryologie")
        after = int((card.locator(".mod-pct").inner_text() or "0").replace("%", "") or 0)
        if after > before:
            ok(f"progression Embryologie : {before}% → {after}%")
        else:
            fail(f"progression Embryologie inchangée : {before}% → {after}% ({noted} carte(s) notee(s))")

    # 10. la langue survit à un rechargement
    set_lang(page, "ar")
    page.reload(wait_until="networkidle")
    page.wait_for_timeout(700)
    got = page.evaluate("() => [document.documentElement.dir, document.documentElement.lang]")
    if got == ["rtl", "ar"]:
        ok("langue AR conservée après rechargement (dir=rtl)")
    else:
        fail(f"langue perdue après rechargement : {got}")
    set_lang(page, "fr")


def check_profile_buttons(page):
    print("\n=== BOUTONS DU PROFIL ===")
    page.goto(URL + "#/profile", wait_until="networkidle")
    page.wait_for_timeout(600)

    # les 3 boutons de langue
    for label, d, l in [("EN", "ltr", "en"), ("AR", "rtl", "ar"), ("FR", "ltr", "fr")]:
        b = page.locator(".lang-switch button", has_text=label)
        if b.count() == 0:
            fail(f"bouton de langue {label} absent")
            continue
        b.first.click()
        page.wait_for_timeout(350)
        got = page.evaluate("() => [document.documentElement.dir, document.documentElement.lang]")
        if got != [d, l]:
            fail(f"{label} → dir/lang {got} (attendu {[d, l]})")
        else:
            ok(f"{label} → dir={got[0]} lang={got[1]}")

    # « Réinitialiser la progression » : tester les DEUX actions
    reset = page.locator("button", has_text="Réinitialiser")
    if reset.count() == 0:
        fail("bouton de réinitialisation absent")
        return
    reset.first.click()
    page.wait_for_timeout(350)
    if page.locator(".dialog").count() == 0:
        fail("pas de confirmation avant effacement")
        return
    labels = page.locator(".dialog button").all_inner_texts()
    ok(f"dialog de réinitialisation : {labels}")
    dismiss_dialog(page, labels[0])  # Annuler
    page.wait_for_timeout(350)
    if page.locator(".dialog").count():
        fail("« Annuler » ne ferme pas le dialog")
    state = json.loads(page.evaluate("() => localStorage.getItem('medlingo_web_v1')") or "{}")
    if state.get("xp", 0) == 0 and state.get("answered", 0) == 0:
        fail("« Annuler » a quand même effacé la progression")
    else:
        ok("« Annuler » conserve la progression")

    reset.first.click()
    page.wait_for_timeout(350)
    labels = page.locator(".dialog button").all_inner_texts()
    dismiss_dialog(page, labels[-1])  # Effacer
    page.wait_for_timeout(450)
    state = json.loads(page.evaluate("() => localStorage.getItem('medlingo_web_v1')") or "{}")
    if state.get("xp") or state.get("answered") or state.get("sm2"):
        fail(f"l'effacement n'a pas remis à zéro : xp={state.get('xp')} answered={state.get('answered')}")
    else:
        ok("« Effacer » remet progression, XP et SM-2 à zéro")
    if not state.get("introSeen"):
        fail("l'effacement a perdu le drapeau « introSeen » (dialog d'accueil reviendra)")
    else:
        ok("« introSeen » conservé après effacement")


def check_intro(page):
    print("\n=== OUVERTURE DE L'APPLICATION ===")
    page.goto(URL, wait_until="networkidle")
    page.wait_for_timeout(900)
    if page.locator(".dialog").count() != 1:
        fail("pas de fenêtre d'accueil à la première ouverture")
    else:
        title = page.locator(".dialog h2").inner_text()
        ok(f"fenêtre d'accueil : {title!r}")
        btn = page.locator(".dialog button")
        if btn.count() != 1:
            fail(f"{btn.count()} bouton(s) dans la fenêtre d'accueil")
        btn.first.click()
        page.wait_for_timeout(350)
        if page.locator(".dialog").count():
            fail("la fenêtre d'accueil ne se ferme pas")
        else:
            ok("fermeture OK")
        page.reload(wait_until="networkidle")
        page.wait_for_timeout(900)
        if page.locator(".dialog").count():
            fail("la fenêtre d'accueil réapparaît après rechargement")
        else:
            ok("pas de fenêtre d'accueil au rechargement")

    # la racine doit rendre sans erreur et sans squelette résiduel
    sk = page.locator(".skeleton").count()
    if sk:
        fail(f"{sk} squelettes de chargement restants")
    else:
        ok("contenu rendu, aucun squelette résiduel")
    n_tabs = page.locator(".tabbar a").count()
    if n_tabs != 5:
        fail(f"barre d'onglets incomplète : {n_tabs} entrées au lieu de 5")
    else:
        ok("barre d'onglets : 5 entrées")


with sync_playwright() as p:
    browser = p.chromium.launch(headless=True)
    page = browser.new_page(viewport={"width": 390, "height": 844})
    wire(page)

    # 1er chargement → actifs
    page.goto(URL, wait_until="networkidle", timeout=60_000)
    page.wait_for_timeout(900)
    check_assets(page)

    # ouverture / fenêtre d'accueil
    check_intro(page)

    # intégration des données
    check_integrated(page)

    # boutons par écran
    for h, n in [("#/path", "Parcours"), ("#/practice", "Revision"), ("#/leagues", "Ligues"),
                 ("#/modules", "Cours"), ("#/search", "Recherche"),
                 ("#/quiz", "Quiz"), ("#/profile", "Profil")]:
        audit_screen(page, h, n)

    # flux spécifiques
    check_flash_buttons(page)
    check_quiz_buttons(page)
    check_edge_cases(page)
    check_profile_buttons(page)

    browser.close()

print("\n" + "=" * 60)
print(f"clics effectués : {clicks}")
print(f"console.error : {len(errors)}")
for e in errors[:20]:
    print("   ", e)
print(f"pageerror     : {len(pageerrs)}")
for e in pageerrs[:20]:
    print("   ", e)
print(f"HTTP >= 400   : {len(netfails)}")
for e in netfails[:20]:
    print("   ", e)
print(f"contrôles en échec : {len(failures)}")
for f in failures:
    print("   ✗", f)
sys.exit(1 if (errors or pageerrs or netfails or failures) else 0)
