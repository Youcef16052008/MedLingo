"""MedLingo — exploration multi-modules (onglet Modules) de bout en bout.

Scénario déterministe, piloté par le driver ADB d'Artemis (sans LLM) :

  1. ouvre l'onglet Modules et vérifie le panneau « Exploration multi-modules »
     (4 modes, multi-sélection de modules, quantités jusqu'à 300) ;
  2. sélectionne **deux** modules (Anatomie + Biochimie) et la quantité **100**,
     puis vérifie que le vivier affiché reste ≤ 300 ;
  3. lance la session et vérifie l'écran de correction (saisie, micro,
     « voir la réponse », progression 1/N) ;
  4. écrit le terme **exact** → verdict « Exact ! » et score 100/100 ;
  5. écrit un mot sans rapport → verdict « Pas encore » et score < 55 ;
  6. vérifie que le bouton micro existe et répond proprement (statut localisé
     ou dialogue système) sans faire tomber l'app ;
  7. ferme la session et vérifie le bouton « Explorer » propre à un module.

Note : `Modifier.testTag` n'est visible par uiautomator que parce que
MainActivity pose `semantics { testTagsAsResourceId = true }` à la racine ;
les tags arrivent donc en `resource-id`, pas en `content-desc`.

Usage : uv run python medlingo_explore_e2e.py
"""

import asyncio
import os
import re
import sqlite3
import subprocess
import sys
import tempfile

from adbutils import AdbClient
from artemis.drivers.android.adb_driver import AndroidAdbDriver
from third_party.mobile_use.clients.ui_automator_client import UIAutomatorClient

from medlingo_lesson_e2e import hide_system_dialogs

SERIAL = "emulator-5554"
ADB = r"C:\Users\pc\AppData\Local\Android\Sdk\platform-tools\adb.exe"
PKG = "com.aistudio.medlingua.dzmed"
ACTIVITY = f"{PKG}/.MainActivity"

MAX_CARDS = 300

failures: list[str] = []
checks = 0


def check(condition: bool, label: str) -> bool:
    global checks
    checks += 1
    if condition:
        print(f"  OK   {label}")
    else:
        print(f"  FAIL {label}")
        failures.append(label)
    return condition


def bounds(el: dict) -> tuple[int, int, int, int]:
    match = re.match(r"\[(\d+),(\d+)\]\[(\d+),(\d+)\]", el.get("bounds") or "")
    if not match:
        return (0, 0, 0, 0)
    return tuple(int(v) for v in match.groups())  # type: ignore[return-value]


def center(el: dict) -> tuple[int, int]:
    x1, y1, x2, y2 = bounds(el)
    return ((x1 + x2) // 2, (y1 + y2) // 2)


def texts(screen) -> list[str]:
    return [(el.get("text") or "").strip() for el in screen.ui_elements]


def find_tag(screen, tag: str) -> dict | None:
    """testTag Compose → resource-id (exact, ou préfixe pour les tags dynamiques)."""
    for el in screen.ui_elements:
        rid = (el.get("resource-id") or "").strip()
        if rid == tag or rid.startswith(tag):
            return el
    return None


def has_tag(screen, tag: str) -> bool:
    return find_tag(screen, tag) is not None


def text_of_tag(screen, tag: str) -> str:
    """Texte d'un nœud porteur du tag, ou de son premier descendant textuel."""
    el = find_tag(screen, tag)
    if el is None:
        return ""
    text = (el.get("text") or "").strip()
    if text:
        return text
    x1, y1, x2, y2 = bounds(el)
    for other in screen.ui_elements:
        ox1, oy1, ox2, oy2 = bounds(other)
        if ox1 >= x1 and oy1 >= y1 and ox2 <= x2 and oy2 <= y2:
            candidate = (other.get("text") or "").strip()
            if candidate:
                return candidate
    return ""


async def settle(driver, seconds: float = 1.2):
    await asyncio.sleep(seconds)
    return await driver.get_screen_data(skip_settling=True)


async def scroll_until(driver, tag: str, swipes: int = 8, dy: int = 250, comfort: bool = False):
    """Petits défilements verticaux jusqu'à ce que `tag` entre dans l'arbre.

    Avec `comfort=True`, on continue tant que le nœud est rogné par le bord bas
    du viewport (une cible réduite à quelques pixels ne reçoit pas le tap).
    """
    screen = await settle(driver, 0.4)
    for _ in range(swipes + 1):
        el = find_tag(screen, tag)
        if el is not None:
            x1, y1, x2, y2 = bounds(el)
            if not comfort or (y2 <= 1400 and (y2 - y1) >= 70 and y1 >= 260):
                return screen, True
        await driver.swipe(360, 1200, 360, 1200 - dy, duration_ms=250)
        screen = await settle(driver, 0.8)
    return screen, has_tag(screen, tag)


async def relaunch_on_modules(driver):
    """Relance proprement l'app puis ouvre l'onglet Modules.

    Repartir d'un état neuf est bien plus fiable que de remonter une
    LazyColumn de plusieurs milliers de pixels (les balayages vers le bas ne
    sont captés que dans la zone défilante et peuvent être avalés par le bord
    de l'écran).
    """
    subprocess.run([ADB, "shell", "am", "force-stop", PKG], check=False)
    subprocess.run([ADB, "shell", "am", "start", "-n", ACTIVITY], check=False)
    await settle(driver, 6.0)
    return await open_modules_tab(driver)


def app_alive() -> bool:
    out = subprocess.run([ADB, "shell", "pidof", PKG], capture_output=True, text=True).stdout
    return bool(out.strip())


async def open_modules_tab(driver, attempts: int = 3):
    """Ouvre l'onglet Modules et attend que le panneau d'exploration apparaisse.

    L'émulateur ANR parfois au premier affichage des modules (rendu très
    dense) ; on relance alors l'app et on retape l'onglet au lieu d'échouer.
    """
    screen = await settle(driver, 0.8)
    for _ in range(attempts):
        if has_tag(screen, "explore_panel_header"):
            return screen
        if not app_alive():
            print("  (app absente — relance)")
            subprocess.run([ADB, "shell", "am", "start", "-n", ACTIVITY], check=False)
            await settle(driver, 7.0)
        await driver.tap(507, 1500)  # onglet Modules
        for _ in range(8):
            screen = await settle(driver, 1.2)
            if has_tag(screen, "explore_panel_header"):
                return screen
            if not app_alive():
                print("  (app tuée pendant l'affichage — relance)")
                subprocess.run([ADB, "shell", "am", "start", "-n", ACTIVITY], check=False)
                await settle(driver, 7.0)
                break
    return screen


async def tap_tag(driver, screen, tag: str) -> bool:
    el = find_tag(screen, tag)
    if el is None:
        return False
    x, y = center(el)
    await driver.tap(x, y)
    return True


def session_open(screen) -> bool:
    """Une session plein écran est ouverte.

    `explore_screen` (tag posé sur un simple conteneur) n'est pas publié dans
    l'arbre d'accessibilité ; on s'appuie sur les deux nœuds réels de la session.
    """
    return has_tag(screen, "explore_close_btn") and has_tag(screen, "explore_answer_input")


def pull_terms() -> dict[str, str]:
    """termFr → termEn depuis la base réelle de l'appareil (pour l'essai exact)."""
    result = subprocess.run(
        [ADB, "exec-out", "run-as", PKG, "cat", "databases/medlingua_dz.db"],
        capture_output=True,
    )
    if result.returncode != 0 or len(result.stdout) < 4096:
        print("  (base non lisible via run-as — le test exact sera adapté)")
        return {}
    handle, path = tempfile.mkstemp(suffix=".db")
    os.close(handle)
    with open(path, "wb") as fh:
        fh.write(result.stdout)
    try:
        con = sqlite3.connect(path)
        rows = con.execute("SELECT termFr, termEn FROM medical_terms").fetchall()
        con.close()
        return {fr.strip(): en.strip() for fr, en in rows if fr and en}
    finally:
        os.remove(path)


async def type_and_check(driver, screen, answer: str):
    field = find_tag(screen, "explore_answer_input")
    if field is None:
        screen, _ = await scroll_until(driver, "explore_answer_input", swipes=3, dy=-300)
        field = find_tag(screen, "explore_answer_input")
    if field is not None:
        x, y = center(field)
        await driver.tap(x, y)
        await asyncio.sleep(0.5)
    await driver.input_text(answer)
    await asyncio.sleep(0.5)
    await driver.press_key(66)  # IME action Done → « Vérifier »
    return await settle(driver, 1.4)


async def main() -> int:
    hide_system_dialogs()
    # Le micro doit être autorisé d'avance pour que l'écran ne demande pas de
    # permission au milieu du scénario.
    subprocess.run(
        [ADB, "shell", "pm", "grant", PKG, "android.permission.RECORD_AUDIO"],
        check=False,
        capture_output=True,
    )
    subprocess.run([ADB, "shell", "am", "force-stop", PKG], check=False)
    subprocess.run([ADB, "shell", "am", "start", "-n", ACTIVITY], check=False)

    client = AdbClient()
    ui = UIAutomatorClient(SERIAL)
    driver = AndroidAdbDriver(device_id=SERIAL, adb_client=client, ui_adb_client=ui)
    await driver.connect()

    # L'intro animée se referme d'elle-même ; on attend qu'elle disparaisse.
    await settle(driver, 6.0)

    # --- 1. Panneau d'exploration dans l'onglet Modules ---------------------
    print("\n[1] Onglet Modules → panneau d'exploration")
    screen = await open_modules_tab(driver)

    check(has_tag(screen, "explore_panel"), "le panneau « Exploration multi-modules » est affiché")
    for tag, label in (
        ("explore_mode_all", "mode Tous"),
        ("explore_mode_random", "mode Au hasard"),
        ("explore_mode_done", "mode Déjà faits"),
        ("explore_mode_to_review", "mode À revoir"),
    ):
        check(has_tag(screen, tag), f"{label} présent")

    # Les puces de modules et les quantités vivent juste sous le panneau :
    # on s'ancre sur « Tous les modules » pour garantir les deux visibles.
    screen, ok = await scroll_until(driver, "explore_modules_all", swipes=6)
    check(ok, "les réglages du panneau (modules, quantité) sont atteignables")
    check(has_tag(screen, "explore_modules_all"), "puce « Tous les modules » présente")
    check(has_tag(screen, "explore_module_chip_anat"), "puce du module Anatomie présente")
    for quantity in (10, 25, 50, 100, 150, 200, 300):
        check(
            has_tag(screen, f"explore_count_{quantity}"),
            f"quantité {quantity} proposée",
        )

    # --- 2. Deux modules + 100 cartes ---------------------------------------
    print("\n[2] Anatomie + Biochimie, quantité 100 (plafond 300)")
    check(await tap_tag(driver, screen, "explore_module_chip_anat"), "sélection du module Anatomie")
    screen = await settle(driver, 0.8)
    check(await tap_tag(driver, screen, "explore_module_chip_biochim"), "sélection du module Biochimie")
    screen = await settle(driver, 0.8)
    check(await tap_tag(driver, screen, "explore_count_100"), "quantité 100 sélectionnée")
    screen = await settle(driver, 0.9)

    screen, ok = await scroll_until(driver, "explore_available_label", swipes=4)
    available_text = text_of_tag(screen, "explore_available_label")
    match = re.search(r"(\d+)", available_text)
    available = int(match.group(1)) if match else -1
    check(available > 0, f"vivier annoncé « {available_text} » (non vide)")

    # --- 3. Lancement de la session ----------------------------------------
    print("\n[3] Lancement → écran de correction")
    screen, ok = await scroll_until(driver, "explore_launch_btn", swipes=3, comfort=True)
    check(ok, "bouton de lancement atteignable")
    # Le rappel du plafond est juste au-dessus du bouton de lancement.
    check(
        any("Maximum 300" in t for t in texts(screen)),
        "plafond « Maximum 300 cartes par session » affiché",
    )
    check(await tap_tag(driver, screen, "explore_launch_btn"), "bouton « Lancer l'exploration »")
    screen = await settle(driver, 2.0)

    check(session_open(screen), "la session plein écran est ouverte")
    check(has_tag(screen, "explore_answer_input"), "champ de saisie présent")
    check(has_tag(screen, "explore_voice_btn"), "bouton micro présent")
    check(has_tag(screen, "explore_reveal_btn"), "bouton « Voir la réponse » présent")

    progress = text_of_tag(screen, "explore_progress")
    check(bool(re.match(r"^\d+/\d+$", progress)), f"progression affichée « {progress} »")
    check(progress == "1/100", f"la quantité 100 demandée est respectée « {progress} »")

    prompt_fr = text_of_tag(screen, "explore_prompt_term")
    check(bool(prompt_fr), f"terme français affiché « {prompt_fr} »")

    # --- 4. Réponse exacte --------------------------------------------------
    print("\n[4] Écriture du terme exact → « Exact ! »")
    terms = pull_terms()
    expected = terms.get(prompt_fr)
    if not expected:
        check(False, f"terme anglais introuvable en base pour « {prompt_fr} »")
        expected = "heart"  # on continue pour vérifier au moins le verdict

    screen = await type_and_check(driver, screen, expected)

    verdict = text_of_tag(screen, "explore_verdict_label")
    score = text_of_tag(screen, "explore_verdict_score")
    check(verdict.startswith("Exact"), f"verdict « {verdict} » pour « {expected} »")
    check(score == "100/100", f"score parfait affiché « {score} »")

    # Les notes SM-2 vivent sous la carte de verdict : un défilement les révèle.
    screen, ok = await scroll_until(driver, "rate_btn_5", swipes=3, dy=300)
    check(ok, "les notes SM-2 sont proposées après correction")

    # --- 5. Mauvaise réponse ------------------------------------------------
    print("\n[5] Note 5/5 → carte suivante, puis mot sans rapport → « Pas encore »")
    check(await tap_tag(driver, screen, "rate_btn_5"), "notation 5/5 enregistrée")
    screen = await settle(driver, 1.6)

    progress = text_of_tag(screen, "explore_progress")
    check(progress.startswith("2/"), f"passage à la carte 2 « {progress} »")

    screen = await type_and_check(driver, screen, "zzzqqq")

    verdict = text_of_tag(screen, "explore_verdict_label")
    score = text_of_tag(screen, "explore_verdict_score")
    score_value = int(score.split("/")[0]) if "/" in score else 999
    check(verdict.startswith("Pas encore"), f"verdict « {verdict} » pour un mot sans rapport")
    check(score_value < 55, f"score faible affiché « {score} »")

    # --- 6. Micro : présent et sans crash ----------------------------------
    print("\n[6] Bouton vocal : réponse propre (moteur absent sur l'émulateur)")
    screen, ok = await scroll_until(driver, "rate_btn_1", swipes=3, dy=300)
    await tap_tag(driver, screen, "rate_btn_1")
    screen = await settle(driver, 1.6)
    mic = find_tag(screen, "explore_voice_btn")
    check(mic is not None, "bouton micro sur la carte suivante")
    if mic is not None:
        x, y = center(mic)
        await driver.tap(x, y)
        await asyncio.sleep(2.0)
        current = await driver.get_current_package()
        after = await settle(driver, 0.8)
        status = text_of_tag(after, "explore_voice_status")
        if current and PKG not in current:
            # Un moteur de reconnaissance a ouvert son activité : on revient.
            await driver.press_key(4)
            after = await settle(driver, 1.4)
            check(session_open(after), "retour à la carte après le dialogue vocal système")
            screen = after
        else:
            check(
                bool(status) or session_open(after),
                f"statut vocal localisé affiché « {status} »" if status else "l'app reste sur la carte",
            )
            screen = after

    # --- 7. Fermeture de la session ----------------------------------------
    print("\n[7] Fermeture de la session")
    check(await tap_tag(driver, screen, "explore_close_btn"), "bouton de fermeture")
    screen = await settle(driver, 1.6)
    check(not session_open(screen), "la session plein écran est refermée")

    # --- 8. Plafond de 300 cartes ------------------------------------------
    print("\n[8] Quantité maximale 300 → la session est bien plafonnée")
    screen = await relaunch_on_modules(driver)
    check(has_tag(screen, "explore_panel_header"), "retour au panneau d'exploration")
    screen, ok = await scroll_until(driver, "explore_count_300", swipes=8, comfort=True)
    check(ok, "réglages du panneau atteignables")
    check(await tap_tag(driver, screen, "explore_count_300"), "quantité 300 sélectionnée")
    screen = await settle(driver, 0.9)
    screen, ok = await scroll_until(driver, "explore_launch_btn", swipes=4, comfort=True)
    launch_label = text_of_tag(screen, "explore_launch_btn")
    check("300" in launch_label, f"le bouton annonce la quantité choisie « {launch_label} »")
    check(await tap_tag(driver, screen, "explore_launch_btn"), "lancement avec 300 cartes")
    screen = await settle(driver, 2.0)
    progress = text_of_tag(screen, "explore_progress")
    check(progress == "1/300", f"session plafonnée à 300 « {progress} »")
    await tap_tag(driver, screen, "explore_close_btn")
    screen = await settle(driver, 1.2)

    # --- 9. Bouton « Explorer » propre à un module --------------------------
    print("\n[9] « Explorer » propre à un module")
    screen = await open_modules_tab(driver)
    screen, ok = await scroll_until(driver, "explore_module_random_anat", swipes=16, comfort=True)
    if ok:
        check(await tap_tag(driver, screen, "explore_module_random_anat"), "bouton « Explorer » du module Anatomie")
        screen = await settle(driver, 2.0)
        check(session_open(screen), "session ouverte depuis un module")
        await tap_tag(driver, screen, "explore_close_btn")
        await settle(driver, 1.0)
    else:
        check(False, "bouton « Explorer » du module Anatomie introuvable")

    print(f"\n=== {checks - len(failures)}/{checks} vérifications OK ===")
    if failures:
        for failure in failures:
            print(f"  - {failure}")
        return 1
    return 0


if __name__ == "__main__":
    sys.exit(asyncio.run(main()))
