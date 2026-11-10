"""MedLingo — smoke E2E transversal des 5 onglets (driver ADB Artemis).

Couvre ce que les deux E2E existants ne touchent pas :

  1. démarrage à froid (`am start -W`) et mesure du `TotalTime` ;
  2. onglet Parcours : carte « Continuer », sélecteur de module, liste de
     chapitres, prix du gel (200 💎, plus de `$` parasite) ;
  3. onglet Révision : les 4 modes d'entraînement ;
  4. onglet Ligues : en-tête de ligue, effectif, semaine, et **aucune fuite
     d'identifiant technique** (`179418240000…`) ;
  5. onglet Modules : syllabus, compteurs modules/lexique ;
  6. onglet Profil : identité, objectif, trophées ;
  7. bascule de langue FR → EN → FR (R-24), vérifiée sur les libellés de la
     barre de navigation ;
  8. absence de dialogue ANR / crash pendant tout le parcours ;
  9. scan de logcat : FATAL EXCEPTION, ANR, crash natif.

Usage : uv run python medlingo_app_smoke_e2e.py
"""

import asyncio
import re
import subprocess
import sys

from adbutils import AdbClient
from artemis.drivers.android.adb_driver import AndroidAdbDriver
from third_party.mobile_use.clients.ui_automator_client import UIAutomatorClient

SERIAL = "emulator-5554"
ADB = r"C:\Users\pc\AppData\Local\Android\Sdk\platform-tools\adb.exe"
PKG = "com.aistudio.medlingua.dzmed"
ACTIVITY = f"{PKG}/.MainActivity"

# Barre de navigation : 5 items de largeur égale sur un écran 720 px.
NAV_Y = 1500
NAV = {
    "path": (66, "Parcours", "Path"),
    "practice": (213, "Révision", "Practice"),
    "leagues": (360, "Ligues", "Leagues"),
    "modules": (507, "Modules", "Modules"),
    "profile": (654, "Profil", "Profile"),
}

# Un identifiant technique = epoch en millisecondes (13 chiffres commençant
# par 17 ou 18). Il ne doit jamais atteindre l'UI.
EPOCH_LEAK = re.compile(r"\b1[78]\d{11}\b")

failures: list[str] = []
checks = 0


def app_pid() -> str:
    return adb("shell", "pidof", PKG).strip()


def raw_dump() -> str:
    """XML UIAutomator brut via l'outil shell.

    À n'appeler **qu'avant** `driver.connect()` : le serveur UIAutomator2 du
    driver enregistre le service UiAutomation, et un `uiautomator dump`
    concurrent lève « UiAutomationService already registered! » (crash de
    l'outil, pas de l'app).

    Utile parce que le driver tronque les caractères hors BMP : 💎 (U+1F48E)
    arrive en « .. », alors que le dump brut le donne en entité `&#128142;`.
    """
    adb("shell", "uiautomator", "dump", "/sdcard/smoke_ui.xml")
    return adb("shell", "cat", "/sdcard/smoke_ui.xml")


def log_pid(line: str) -> str:
    """PID du processus émetteur : « 11-10 06:45:14.969 30452 30452 E … »."""
    parts = line.split()
    return parts[2] if len(parts) > 3 else ""


def check(condition: bool, label: str) -> bool:
    global checks
    checks += 1
    print(f"  {'OK  ' if condition else 'FAIL'} {label}")
    if not condition:
        failures.append(label)
    return condition


def adb(*args: str) -> str:
    result = subprocess.run([ADB, *args], capture_output=True, check=False)
    return result.stdout.decode("utf-8", "replace")


def bounds(el: dict) -> tuple[int, int, int, int]:
    match = re.match(r"\[(\d+),(\d+)\]\[(\d+),(\d+)\]", el.get("bounds") or "")
    return tuple(int(v) for v in match.groups()) if match else (0, 0, 0, 0)


def center(el: dict) -> tuple[int, int]:
    x1, y1, x2, y2 = bounds(el)
    return ((x1 + x2) // 2, (y1 + y2) // 2)


def text_of(el: dict) -> str:
    return (el.get("text") or "").strip()


def desc_of(el: dict) -> str:
    return (el.get("content-desc") or "").strip()


def texts(screen) -> list[str]:
    return [text_of(el) for el in screen.ui_elements if text_of(el)]


def has(screen, needle: str) -> bool:
    return any(needle in t for t in texts(screen)) or any(
        needle in desc_of(el) for el in screen.ui_elements
    )


def find(screen, needle: str, exact: bool = False) -> list[dict]:
    found = []
    for el in screen.ui_elements:
        text = text_of(el)
        if not text:
            continue
        if (text == needle) if exact else (needle in text):
            found.append(el)
    return found


def is_anr(screen) -> bool:
    current = texts(screen)
    return any("ne répond pas" in t or "isn't responding" in t for t in current)


async def settle(driver, seconds: float = 1.2):
    await asyncio.sleep(seconds)
    return await driver.get_screen_data(skip_settling=True)


async def wait_for(driver, predicate, timeout: float = 25.0, step: float = 0.8):
    screen = await driver.get_screen_data(skip_settling=True)
    waited = 0.0
    while waited < timeout and not predicate(screen):
        await asyncio.sleep(step)
        waited += step
        screen = await driver.get_screen_data(skip_settling=True)
    return screen


async def tap_nav(driver, key: str) -> None:
    x, *_ = NAV[key]
    await driver.tap(x, NAV_Y)


async def scroll_until(driver, predicate, swipes: int = 8):
    """Descend dans une liste jusqu'à ce que `predicate(screen)` soit vrai."""
    screen = await driver.get_screen_data(skip_settling=True)
    for _ in range(swipes):
        if predicate(screen):
            return screen
        await driver.swipe(360, 1250, 360, 700, duration_ms=350)
        await asyncio.sleep(0.6)
        screen = await driver.get_screen_data(skip_settling=True)
    return screen


async def scroll_to_top(driver, swipes: int = 8):
    """Remonte en haut d'une liste (le sélecteur de langue est en bas)."""
    screen = await driver.get_screen_data(skip_settling=True)
    for _ in range(swipes):
        await driver.swipe(360, 700, 360, 1300, duration_ms=350)
        await asyncio.sleep(0.5)
        screen = await driver.get_screen_data(skip_settling=True)
    return screen


async def main() -> int:
    # --- 0. Conditions de départ ------------------------------------------
    print("[0] Préparation (dialogues système masqués, app arrêtée, logcat vidé)")
    adb("shell", "settings", "put", "global", "hide_error_dialogs", "1")
    adb("shell", "am", "force-stop", PKG)
    adb("logcat", "-c")
    start = adb("shell", "am", "start", "-W", "-n", ACTIVITY)
    total_match = re.search(r"TotalTime:\s*(\d+)", start)
    total_ms = int(total_match.group(1)) if total_match else -1
    print(f"    démarrage à froid : TotalTime = {total_ms} ms")
    check(total_ms > 0, f"l'app démarre (am start -W → {total_ms} ms)")
    check(total_ms < 8000, f"démarrage à froid sous 8 s (mesuré {total_ms} ms)")

    # Dump brut AVANT de connecter le driver (conflit UiAutomation sinon) :
    # c'est la seule source qui préserve les emoji hors BMP.
    import time as _time

    _time.sleep(5.0)
    raw_initial = raw_dump()

    client = AdbClient()
    ui = UIAutomatorClient(SERIAL)
    driver = AndroidAdbDriver(device_id=SERIAL, adb_client=client, ui_adb_client=ui)
    await driver.connect()

    screen = await wait_for(driver, lambda s: has(s, "Parcours"), timeout=30.0)
    check(not is_anr(screen), "aucun dialogue « ne répond pas » au démarrage")
    check(has(screen, "Parcours"), "l'app s'ouvre sur l'onglet Parcours")

    # --- 1. Parcours --------------------------------------------------------
    print("\n[1] Onglet Parcours")
    check(has(screen, "Continuer"), "la carte « Continuer » est présente")
    check(has(screen, "Changer"), "le sélecteur de module est accessible (« Changer »)")
    check(
        any(re.match(r"^\d+ chapitres · \d+/\d+ leçons", t) for t in texts(screen)),
        "l'unité du module affiche « N chapitres · x/y leçons »",
    )
    check(has(screen, "Choisis un chapitre"), "la liste des chapitres est visible")
    freeze = [t for t in texts(screen) if "Congeler" in t]
    check(bool(freeze), f"le bouton de gel est affiché ({freeze[0] if freeze else 'absent'})")
    check(
        bool(freeze) and "200" in freeze[0],
        "le prix du gel affiche 200 (plus de « $ » parasite)",
    )
    check(
        not any("$" in t for t in freeze),
        "aucun placeholder « $ » dans le libellé du gel",
    )
    # 💎 est hors BMP : le driver le tronque en « .. ». On lit donc le dump brut
    # pris avant la connexion du driver.
    freeze_raw = re.findall(r'text="([^"]*Congeler[^"]*)"', raw_initial)
    check(
        bool(freeze_raw) and "&#128142;" in freeze_raw[0],
        f"le prix du gel porte le pictogramme de gemme ({freeze_raw[0] if freeze_raw else 'absent'})",
    )
    leaks = [t for t in texts(screen) if EPOCH_LEAK.search(t)]
    check(not leaks, f"aucun identifiant technique affiché sur Parcours (vu {leaks})")
    with open("medlingo_smoke_path.png", "wb") as fh:
        fh.write(screen.screenshot_bytes)

    # --- 2. Révision --------------------------------------------------------
    print("\n[2] Onglet Révision")
    await tap_nav(driver, "practice")
    screen = await wait_for(
        driver, lambda s: has(s, "Choisis ton entraînement"), timeout=20.0
    )
    check(has(screen, "Choisis ton entraînement"), "l'en-tête de révision s'affiche")
    for anchor in ("Révision du jour", "Quiz par niveau", "Termes faibles", "Examen blanc"):
        check(has(screen, anchor), f"mode d'entraînement présent : « {anchor} »")
    check(not is_anr(screen), "aucun ANR sur Révision")

    # --- 3. Ligues ----------------------------------------------------------
    print("\n[3] Onglet Ligues")
    await tap_nav(driver, "leagues")
    screen = await wait_for(driver, lambda s: has(s, "Ligue"), timeout=20.0)
    check(has(screen, "Ligue BRONZE"), "la ligue courante (BRONZE) est affichée")
    check(has(screen, "Top 10"), "la zone de promotion est expliquée")
    check(has(screen, "Semaine"), "la plage de la semaine est affichée")
    check(
        any(re.search(r"\d+ membres", t) for t in texts(screen)),
        "l'effectif de la ligue est affiché en clair",
    )
    leaks = [t for t in texts(screen) if EPOCH_LEAK.search(t)]
    check(
        not leaks,
        f"aucun identifiant de cohorte brut dans Ligues (vu {leaks})",
    )
    with open("medlingo_smoke_leagues.png", "wb") as fh:
        fh.write(screen.screenshot_bytes)

    # --- 4. Modules ---------------------------------------------------------
    print("\n[4] Onglet Modules")
    await tap_nav(driver, "modules")
    screen = await wait_for(
        driver, lambda s: has(s, "Modules") and has(s, "Syllabus"), timeout=25.0
    )
    check(has(screen, "Syllabus"), "le syllabus médical s'affiche")
    check(
        any(re.search(r"Modules \(\d+\)", t) for t in texts(screen)),
        "le compteur de modules est affiché",
    )
    check(
        any(re.search(r"Lexique \(\d+\)", t) for t in texts(screen)),
        "le compteur de lexique est affiché",
    )
    check(not is_anr(screen), "aucun ANR sur Modules")

    # --- 5. Profil ----------------------------------------------------------
    print("\n[5] Onglet Profil")
    await tap_nav(driver, "profile")
    screen = await wait_for(driver, lambda s: has(s, "Dr. Youcef"), timeout=25.0)
    check(has(screen, "Dr. Youcef"), "le profil utilisateur est affiché")
    check(has(screen, "Objectif quotidien"), "l'objectif quotidien est affiché")
    check(has(screen, "Trophées"), "les trophées sont affichés")
    check(not is_anr(screen), "aucun ANR sur Profil")

    # --- 6. Bascule de langue FR → EN → FR (R-24) ---------------------------
    print("\n[6] Bascule de langue FR → EN → FR")
    screen = await scroll_until(driver, lambda s: has(s, "English"), swipes=10)
    if not check(has(screen, "English"), "le sélecteur de langue est atteignable"):
        with open("medlingo_smoke_profile.png", "wb") as fh:
            fh.write(screen.screenshot_bytes)
        return report()

    english = find(screen, "English", exact=True)
    if not english:
        english = find(screen, "English")
    await driver.tap(*center(english[0]))
    screen = await settle(driver, 2.0)
    check(
        has(screen, "Profile & Stats") or has(screen, "Profile"),
        "les libellés passent en anglais (Profil → Profile)",
    )
    check(
        has(screen, "App language"),
        "la section langue passe en anglais (App language)",
    )
    # Le sélecteur est en bas de page : on remonte pour lire l'en-tête traduit.
    screen = await scroll_to_top(driver, swipes=10)
    check(
        has(screen, "Daily goal"),
        "le contenu de l'écran passe en anglais (Daily goal)",
    )
    check(has(screen, "Trophies"), "les trophées passent en anglais (Trophies)")

    await tap_nav(driver, "path")
    screen = await wait_for(driver, lambda s: has(s, "Path"), timeout=15.0)
    check(has(screen, "Path"), "la barre de navigation est traduite (Parcours → Path)")
    check(has(screen, "Practice"), "l'onglet Révision devient Practice")
    await tap_nav(driver, "profile")
    screen = await settle(driver, 2.0)
    screen = await scroll_until(driver, lambda s: has(s, "Français"), swipes=12)
    french = find(screen, "Français", exact=True) or find(screen, "Français")
    if check(bool(french), "le sélecteur reste atteignable en anglais"):
        await driver.tap(*center(french[0]))
        screen = await settle(driver, 2.0)
        check(
            has(screen, "Langue de l'application"),
            "la section langue revient en français (Langue de l'application)",
        )
        screen = await scroll_to_top(driver, swipes=10)
        check(has(screen, "Objectif quotidien"), "retour au français (Objectif quotidien)")
        check(has(screen, "Trophées"), "retour au français (Trophées)")
    await tap_nav(driver, "path")
    screen = await wait_for(driver, lambda s: has(s, "Parcours"), timeout=15.0)
    check(has(screen, "Parcours"), "la barre de navigation revient en français")
    with open("medlingo_smoke_path_fr.png", "wb") as fh:
        fh.write(screen.screenshot_bytes)

    # --- 7. Logcat ----------------------------------------------------------
    print("\n[7] Journal système")
    log = adb("logcat", "-d")
    pid = app_pid()
    # On n'attribue une FATAL EXCEPTION à l'app que si elle vient de SON pid :
    # les outils de test (uiautomator, instrumentation) crashent aussi, et leurs
    # traces ne disent rien de MedLingo.
    fatals = [
        line
        for line in log.splitlines()
        if "FATAL EXCEPTION" in line and pid and log_pid(line) == pid
    ]
    app_process = [
        line for line in log.splitlines() if f"Process: {PKG}" in line
    ]
    check(
        not fatals and not app_process,
        f"aucune FATAL EXCEPTION venant de l'app (pid {pid}, vu {len(fatals) + len(app_process)})",
    )
    for line in (fatals + app_process)[:6]:
        print("      ", line.strip()[:160])
    other_fatals = [
        line
        for line in log.splitlines()
        if "FATAL EXCEPTION" in line and line not in fatals
    ]
    if other_fatals:
        print(f"    (info) {len(other_fatals)} FATAL EXCEPTION d'autres processus (outils de test)")
    app_crashes = [
        line
        for line in log.splitlines()
        if "ANR in" in line and "medlingua" in line
    ]
    check(not app_crashes, f"aucun ANR applicatif dans logcat (vu {len(app_crashes)})")
    native = [
        line
        for line in log.splitlines()
        if re.search(r"(Fatal signal|SIGSEGV|libc.*Fatal)", line)
        and "medlingua" in line.lower()
    ]
    check(not native, f"aucun crash natif dans logcat (vu {len(native)})")
    killed = [
        line
        for line in log.splitlines()
        if "Process " + PKG in line and "has died" in line
    ]
    check(not killed, f"le processus n'est pas tué pendant le parcours (vu {len(killed)})")

    return report()


def report() -> int:
    print(f"\n{checks - len(failures)}/{checks} vérifications OK")
    if failures:
        print("Échecs :")
        for item in failures:
            print(" -", item)
        return 1
    print("SMOKE OK")
    return 0


if __name__ == "__main__":
    sys.exit(asyncio.run(main()))
