"""Parcours complet : intro, 5 onglets, recherche, flashcards, quiz (4 types), RTL, persistance."""
import json, os, pathlib, re, sys
from playwright.sync_api import sync_playwright

URL = os.environ.get("APP_URL", "http://localhost:5173")
sys.stdout.reconfigure(encoding="utf-8", errors="replace")

errors, pageerrs, netfails = [], [], []
seen_types = set()


def norm(s):
    return re.sub(r"\s+", " ", (s or "")).strip().lower()


EX = json.loads(pathlib.Path("public/data/exercises.json").read_text(encoding="utf-8"))
BY_Q = {}
for _e in EX:
    for _k in ("qFr", "qEn", "qAr"):
        if _e.get(_k):
            BY_Q.setdefault(norm(_e[_k]), _e)


def dump(page, label):
    data = page.evaluate("""() => ({
        hash: location.hash,
        dir: document.documentElement.dir,
        headings: [...document.querySelectorAll('h1,h2')].map(e => e.textContent.trim()),
        body: document.body.innerText.replace(/\\s+/g, ' ').slice(0, 500),
    })""")
    print(f"\n### {label}\n{json.dumps(data, ensure_ascii=False, indent=2)}")
    return data


def shot(page, name):
    page.screenshot(path=f"shots/{name}.png", full_page=True)


def dismiss_dialog(page):
    """Ferme un dialog s'il est ouvert. Renvoie True si un dialog était là."""
    if page.locator(".dialog").count() == 0:
        return False
    page.locator(".dialog button").last.click()
    page.wait_for_timeout(250)
    return True


with sync_playwright() as p:
    browser = p.chromium.launch(headless=True)
    page = browser.new_page(viewport={"width": 390, "height": 844})
    page.on("console", lambda m: errors.append(f"[{m.type}] {m.text}") if m.type == "error" else None)
    page.on("pageerror", lambda e: pageerrs.append(str(e)))
    page.on("response", lambda r: netfails.append(f"{r.status} {r.url}") if r.status >= 400 else None)

    # ---------- accueil + intro ----------
    page.goto(URL, wait_until="networkidle", timeout=60_000)
    # Attendre le dialogue plutôt qu'un délai fixe : sur une machine chargée, 700 ms
    # ne suffisaient pas et le test échouait par intermittence.
    page.locator(".dialog").first.wait_for(state="visible", timeout=20_000)
    assert page.locator(".dialog").count() == 1, "dialog d'intro attendu"
    shot(page, "01-intro")
    page.locator(".dialog button").first.click()
    page.wait_for_timeout(250)
    assert page.locator(".dialog").count() == 0, "intro non fermée"
    # Parcours Duolingo : en-tête Medi + anneau d'objectif, 15 unités × 6 nœuds, perle du jour.
    assert page.locator(".path-head").count() == 1, "en-tête du parcours absent"
    assert page.locator(".path-head .medi").count() == 1, "mascotte Medi absente de l'en-tête"
    assert page.locator(".goal-ring").count() == 1, "anneau d'objectif quotidien absent"
    assert page.locator(".path-unit").count() == 15, "15 unités attendues"
    assert page.locator(".path-unit .path-node").count() == 90, "90 nœuds de leçon attendus"
    assert page.locator(".path-node.boss").count() == 15, "un nœud BOSS par unité attendu"
    assert page.locator(".pearl").count() == 1, "carte perle clinique absente"
    print("✓ intro affichée et fermée ; parcours (Medi, anneau, 15 unités, 90 nœuds, perle)")
    shot(page, "02-home")

    # ---------- 5 onglets ----------
    for hash_, label in [("#/path", "Parcours"), ("#/practice", "Revision"),
                         ("#/leagues", "Ligues"), ("#/modules", "Cours"),
                         ("#/profile", "Profil")]:
        page.goto(URL + hash_, wait_until="networkidle")
        page.wait_for_timeout(350)
        d = dump(page, f"onglet {hash_}")
        assert d["headings"], f"aucun titre sur {hash_}"
        shot(page, f"03-{label}")

    # une session de flashcards appartient à l'onglet Révision
    page.goto(URL + "#/flash/all", wait_until="networkidle")
    page.wait_for_timeout(350)
    assert page.locator(".session-top").count() == 1, "session de fiches absente"
    assert page.locator(".tabbar a.active").get_attribute("href") == "#/practice", \
        "l'onglet Revision devrait être actif pendant une session"

    # la barre du bas expose les cinq destinations du parcours Duolingo
    page.goto(URL + "#/path", wait_until="networkidle")
    page.wait_for_timeout(350)
    tabs = page.eval_on_selector_all(
        ".tabbar a", "els => els.map(e => e.getAttribute('href'))")
    assert tabs == ["#/path", "#/practice", "#/leagues", "#/modules", "#/profile"], \
        f"5 onglets attendus dans l'ordre, obtenu {tabs}"
    print(f"✓ barre du bas à 5 onglets : {tabs}")

    # ---------- recherche ----------
    page.goto(URL + "#/search", wait_until="networkidle")
    page.wait_for_timeout(350)
    box = page.locator(".search-box input")
    assert box.count() == 1, "champ de recherche introuvable"
    box.fill("renal")
    page.wait_for_timeout(450)
    results = page.locator(".result-item")
    n = results.count()
    print(f"✓ recherche 'renal' → {n} résultats")
    assert n > 0, "aucun résultat pour 'renal'"
    shot(page, "04-search")
    results.first.click()
    page.wait_for_timeout(400)
    assert page.locator(".dialog, .term-sheet, .sheet").count() > 0, "fiche terme non ouverte"
    print("✓ fiche de terme ouverte")
    shot(page, "05-term-sheet")
    page.locator(".sheet button.btn.ghost").last.click()
    page.wait_for_timeout(300)
    assert page.locator(".sheet").count() == 0, "fiche terme non fermée"

    # ---------- bascule RTL ----------
    page.goto(URL + "#/profile", wait_until="networkidle")
    page.wait_for_timeout(350)
    page.locator(".lang-switch button", has_text="AR").first.click()
    page.wait_for_timeout(300)
    d = page.evaluate("() => ({dir: document.documentElement.dir, lang: document.documentElement.lang})")
    assert d["dir"] == "rtl" and d["lang"] == "ar", f"RTL non appliqué: {d}"
    print(f"✓ bascule AR → {d}")
    shot(page, "06-profile-ar")
    page.locator(".lang-switch button", has_text="FR").first.click()
    page.wait_for_timeout(250)
    assert page.evaluate("() => document.documentElement.dir") == "ltr"

    # ---------- flashcards ----------
    page.goto(URL + "#/flash/anat", wait_until="networkidle")
    page.wait_for_timeout(500)
    assert page.locator(".flashcard").count() == 1, "carte absente"
    assert page.locator(".rating-btn:not([disabled])").count() == 0, "notation disallowed avant retournement"
    page.locator(".flashcard").click()
    page.wait_for_timeout(350)
    assert "flipped" in (page.locator(".flashcard").get_attribute("class") or ""), "carte non retournée"
    assert page.locator(".rating-btn:not([disabled])").count() == 5, "notation non activée"
    shot(page, "07-flash-back")
    page.locator('.rating-btn[data-q="3"]').click()
    page.wait_for_timeout(450)
    assert page.locator(".flashcard").count() == 1, "carte suivante absente"
    print("✓ flip + notation Moyen → carte suivante")
    shot(page, "08-flash-next")

    # ---------- quiz : sessions jusqu'à couvrir les 4 types ----------
    def solve(i):
        opts = page.locator(".options .option")
        fill = page.locator(".fill-input")
        bank = page.locator("#bank .word")
        match = page.locator(".match-grid")
        ex = BY_Q.get(norm(page.locator(".quiz-q").inner_text()))

        if opts.count():
            kind = "mcq/reading"
            want = norm(ex["answer"]) if ex and ex.get("answer") else ""
            picked = next(
                (j for j, o in enumerate(opts.all()) if want and norm(o.inner_text()) == want),
                0,
            )
            opts.nth(picked).click()
            page.locator("button.btn.block").last.click()
        elif fill.count():
            kind = "fill_blank"
            fill.fill(ex["answer"] if ex and ex.get("answer") else "xyz")
            page.locator("button.btn.block").last.click()
        elif bank.count():
            kind = "sentence_order"
            # `answer` est la phrase complète alors que la banque peut regrouper
            # plusieurs mots (« so that ») : on découpe la réponse sur les units
            # de la banque pour obtenir l'ordre exact des clics.
            def build_order(answer, words):
                for pick in (lambda c: max(c, key=len), lambda c: min(c, key=len)):
                    cur, rest, order = answer, list(words), []
                    while cur:
                        cands = [
                            w for w in rest
                            if cur == w or cur.startswith(w + " ")
                        ]
                        if not cands:
                            break
                        w = pick(cands)
                        order.append(w)
                        rest.remove(w)
                        cur = cur[len(w):].lstrip(" ")
                    if not cur and not rest:
                        return order
                return None

            def take_word(tok):
                words = page.locator("#bank .word").all()
                for w in words:
                    if w.is_enabled() and w.inner_text() == tok:
                        return w
                for w in words:
                    if w.is_enabled() and norm(w.inner_text()) == norm(tok):
                        return w
                return None

            order = build_order(
                ex["answer"], ex["options"]
            ) if ex and ex.get("answer") and ex.get("options") else None
            if order is None:
                order = (ex["answer"].split() if ex and ex.get("answer") else [])
            for tok in order:
                w = take_word(tok)
                if w is None:
                    break
                w.click()
            page.locator("button.btn.block").last.click()
        elif match.count():
            kind = "matching"
            nL = page.locator("#colL .match-item").count()

            def find_exact(sel, text):
                loc = page.locator(sel)
                for k in range(loc.count()):
                    if norm(loc.nth(k).inner_text()) == norm(text):
                        return loc.nth(k)
                return None

            pairs = [tuple(o.split(":", 1)) for o in (ex["options"] if ex else [])]
            if len(pairs) != nL:
                # secours : force brute réessayant chaque côté
                nR = page.locator("#colR .match-item").count()
                for li in range(nL):
                    before = page.locator("#colL .match-item.done").count()
                    for ri in range(nR):
                        left = page.locator("#colL .match-item").nth(li)
                        if left.is_disabled():
                            break
                        left.click()
                        right = page.locator("#colR .match-item").nth(ri)
                        if right.is_disabled():
                            continue
                        right.click()
                        if page.locator("#colL .match-item.done").count() > before:
                            break
            else:
                for l, r in pairs:
                    if page.locator("#colL .match-item.done").count() >= nL:
                        break
                    left = find_exact("#colL .match-item", l)
                    right = find_exact("#colR .match-item", r)
                    if left is None or right is None:
                        raise AssertionError(f"élément introuvable: {l!r} / {r!r}")
                    if left.is_disabled() or right.is_disabled():
                        continue
                    left.click()
                    right.click()
            final = page.locator("#colL .match-item.done").count()
            if final != nL:
                print(f"  ⚠ matching incomplet {final}/{nL}")
                print("     lefts :", page.locator("#colL .match-item").all_inner_texts())
                print("     rights:", page.locator("#colR .match-item").all_inner_texts())
                print("     pairs :", pairs)
                print("     classes:", page.locator("#colL .match-item").evaluate_all(
                    "els => els.map(e => e.className)"))
                dump(page, "match-echec")
                raise AssertionError(f"appariement incomplet {final}/{nL}")
        else:
            dump(page, "question inconnue")
            return None

        page.wait_for_timeout(400)
        fb = page.locator(".feedback")
        assert fb.count() == 1, f"pas de retour après réponse ({kind})"
        verdict = "ok" if page.locator(".feedback.ok").count() else "ko"
        if verdict == "ko":
            print(f"\n  ⚠ KO [{kind}]")
            print("    question:", page.locator(".quiz-q").inner_text()[:200])
            print("    attendu :", (ex or {}).get("answer"))
            if page.locator(".options .option").count():
                print("    classes :", page.locator(".options .option").evaluate_all(
                    "els => els.map(e => ({t: e.innerText, c: e.className}))"))
            if page.locator("#zone .word").count():
                print("    zone    :", page.locator("#zone .word").all_inner_texts())
                print("    bank    :", page.locator("#bank .word").all_inner_texts())
            print("    retour  :", page.locator(".feedback").inner_text()[:300])
        shot(page, f"09-quiz-{i}-{kind.replace('/', '-')}-{verdict}")
        nxt = page.locator(".feedback button.btn.block")
        if nxt.count():
            nxt.first.click()
        page.wait_for_timeout(400)
        return (kind, verdict)

    needed = {"mcq/reading", "fill_blank", "sentence_order", "matching"}
    verdicts = []

    def level_of_type(kind):
        """Premier niveau contenant le type `kind` (None si absent du corpus)."""
        base = kind.split("/")[0]
        for e in EX:
            if e["type"] == base:
                return e["level"]
        return None

    for session in range(6):
        if needed <= seen_types:
            break
        # repartir d'un écran neutre : rester sur #/quiz laisserait l'écran « fini »
        page.goto(URL + "#/path", wait_until="networkidle")
        page.wait_for_timeout(250)
        page.goto(URL + "#/quiz", wait_until="networkidle")
        page.wait_for_timeout(350)
        # `fill_blank` ne représente que 2 exercices sur 44, tous deux au niveau 3 :
        # en piochant au hasard, la couverture dépend du tirage (~5 % d'échec). On cible
        # donc le niveau qui contient le type encore manquant.
        target = next(
            (level_of_type(k) for k in sorted(needed - seen_types) if level_of_type(k)),
            None,
        )
        page.locator(".chip-row .chip").nth(target or 0).first.click()
        page.wait_for_timeout(200)
        page.locator("button.btn.block", has_text="Commencer").first.click()
        page.wait_for_timeout(450)
        for i in range(12):
            if dismiss_dialog(page) or page.locator(".summary").count():
                break
            r = solve(session * 10 + i)
            if r is None:
                break
            seen_types.add(r[0])
            verdicts.append(r[1])
        print(f"  session {session + 1} → types vus: {sorted(seen_types)}")

    print(f"✓ types de questions exercés : {sorted(seen_types)}")
    print(f"✓ verdicts : ok={verdicts.count('ok')} ko={verdicts.count('ko')}")
    assert needed <= seen_types, f"types non couverts : {sorted(needed - seen_types)}"
    assert verdicts.count("ko") == 0, f"réponses justes notées fausses : {verdicts.count('ko')}"
    assert verdicts.count("ok") >= 10, "session complète non validée"
    dump(page, "fin du quiz")

    # ---------- parcours : clic nœud → leçon → récompense ----------
    page.goto(URL + "#/path", wait_until="networkidle")
    page.wait_for_timeout(500)
    locked = page.locator(".path-node.locked")
    assert locked.count() > 0, "aucun nœud verrouillé sur un parcours neuf"
    assert locked.first.is_disabled(), "nœud verrouillé non désactivé"
    print("✓ nœuds verrouillés désactivés (« 70 % requis » en tooltip)")

    # le nœud « suivant » pulse (animation) : clic forcé, comme un doigt sur un bouton animé
    page.locator(".path-node.available").first.click(force=True)
    page.wait_for_timeout(800)
    assert "#/quiz?module=" in page.evaluate("() => location.hash"), \
        f"clic nœud → quiz cadré attendu, hash={page.evaluate('() => location.hash')}"
    assert page.locator(".quiz-q").count() == 1, "leçon non démarrée depuis le parcours"
    print("✓ clic sur un nœud → leçon cadrée (module + niveau)")

    # 8 questions générées : on répond, le verdict n'a pas d'importance ici
    for i in range(10):
        if page.locator(".reward-overlay").count() or page.locator(".summary").count():
            break
        if solve(100 + i) is None:
            break
    page.locator(".reward-overlay").first.wait_for(state="visible", timeout=20_000)
    assert page.locator(".reward-xp").count() == 1, "compteur XP de la récompense absent"
    assert page.locator(".confetti i").count() == 12, "confettis absents"
    print("✓ leçon terminée → pop-up de récompense (compteur + confettis)")
    shot(page, "11-reward")
    page.locator(".reward-card button.btn").last.click()  # « Continuer »
    page.wait_for_timeout(300)
    assert page.locator(".reward-overlay").count() == 0, "pop-up non fermée"
    assert page.locator(".summary").count() == 1, "récapitulatif absent"
    page.locator(".summary a.btn").first.click()  # « Accueil » → Parcours
    page.wait_for_timeout(400)
    assert page.evaluate("() => location.hash") == "#/path", "retour au Parcours attendu"

    # ---------- hub Révision : 4 cartes + anneau + Medi ----------
    page.goto(URL + "#/practice", wait_until="networkidle")
    page.wait_for_timeout(350)
    assert page.locator(".practice-card").count() == 4, "4 cartes de révision attendues"
    assert page.locator(".practice-head .goal-ring").count() == 1, "mini-anneau d'objectif absent"
    assert page.locator(".practice-head .medi").count() == 1, "conseil Medi absent"
    print("✓ hub Révision : 4 cartes + mini-anneau + Medi")
    shot(page, "12-practice")

    # ---------- ligues : classement ----------
    page.goto(URL + "#/leagues", wait_until="networkidle")
    page.wait_for_timeout(500)
    assert page.locator(".league-row").count() == 30, "30 lignes de classement attendues"
    assert page.locator(".league-row.you").count() == 1, "ligne « TOI » absente"
    assert page.locator(".league-row.zone-green").count() == 10, "zone verte = top 10"
    assert page.locator(".league-row.zone-red").count() == 5, "zone rouge = bottom 5"
    print("✓ ligues : 30 lignes, zone verte (10), zone rouge (5), ligne TOI")
    shot(page, "13-leagues")

    # ---------- profil : trophées + objectif ----------
    page.goto(URL + "#/profile", wait_until="networkidle")
    page.wait_for_timeout(400)
    assert page.locator(".trophy").count() == 12, "12 trophées attendus"
    assert page.locator(".trophy.won").count() >= 1, "aucun trophée gagné affiché"
    assert page.locator(".goal-seg button").count() == 3, "objectif 20/50/100 attendu"
    print("✓ profil : grille de 12 trophées (gagnés en couleur) + objectif quotidien")
    shot(page, "14-profile-trophies")

    # ---------- responsive 360 px → desktop (spec T3.2) ----------
    for w, h in ((360, 780), (1280, 900)):
        page.set_viewport_size({"width": w, "height": h})
        for hash_ in ("#/path", "#/practice", "#/leagues", "#/modules", "#/profile"):
            page.goto(URL + hash_, wait_until="networkidle")
            page.wait_for_timeout(250)
            overflow = page.evaluate(
                "() => document.documentElement.scrollWidth - window.innerWidth"
            )
            assert overflow <= 1, f"débordement horizontal {overflow}px sur {hash_} @ {w}px"
        shot(page, f"15-responsive-{w}")
    page.set_viewport_size({"width": 390, "height": 844})
    print("✓ responsive 360 px → desktop sans débordement horizontal")

    # ---------- persistance ----------
    page.goto(URL + "#/path", wait_until="networkidle")
    page.wait_for_timeout(500)
    state = page.evaluate("() => localStorage.getItem('medlingo_web_v1')")
    st = json.loads(state) if state else {}
    print("\n### localStorage medlingo_web_v1")
    print(json.dumps({k: (v if not isinstance(v, dict) else f"<{len(v)} entrées>") for k, v in st.items()},
                     ensure_ascii=False, indent=2))
    assert st.get("flashReviewed", 0) >= 1, "flashcards non comptabilisées"
    assert st.get("answered", 0) >= 1, "réponses quiz non comptabilisées"
    assert st.get("quizDone", 0) >= 1, "session de quiz non finalisée"
    assert st.get("xp", 0) > 0, "XP non crédité"
    assert st.get("streak", 0) >= 1, "série non démarrée"
    assert len(st.get("sm2", {})) >= 1, "planning SM-2 non mis à jour"
    assert st.get("lessonBest"), "meilleurs scores de leçon non persistés"
    assert len(st.get("trophies", {})) >= 1, "aucun trophée persisté"
    assert st.get("pendingChests", 0) >= 1, "aucune caisse en attente après sessions"

    page.reload(wait_until="networkidle")
    page.wait_for_timeout(600)
    state2 = json.loads(page.evaluate("() => localStorage.getItem('medlingo_web_v1')"))
    assert state2.get("answered") == st.get("answered"), "état non persisté après rechargement"
    print("✓ état persisté après rechargement")
    shot(page, "10-after-reload")

    browser.close()

print("\n=========== RAPPORT ===========")
print(f"console.error : {len(errors)}")
for e in errors:
    print("  ", e)
print(f"pageerror     : {len(pageerrs)}")
for e in pageerrs:
    print("  ", e)
print(f"HTTP >= 400   : {len(netfails)}")
for e in netfails:
    print("  ", e)
sys.exit(1 if (errors or pageerrs or netfails) else 0)
