"""MedLingo — parcours Android de bout en bout, piloté par le driver ADB d'Artemis.

Scénario (déterministe, sans LLM) :
  1. relance l'app et attend l'onglet Parcours ;
  2. vérifie que le module affiché liste ses **chapitres réels** (`N chapitres`,
     total de leçons = 6 x N) et qu'aucun module n'affiche « 0 chapitres » ;
  3. tape le chapitre « Angiologie » (Anatomie) → sa page dédiée s'ouvre avec
     les 6 nœuds L1 → L6 (L1 jouable, les suivants verrouillés, L6 = BOSS) ;
  4. tape L1 et vérifie que la leçon s'ouvre (« Question 1 / 8 »).

Usage : uv run python medlingo_path_e2e.py
"""

import asyncio
import re
import subprocess
import sys
import time

from adbutils import AdbClient
from artemis.drivers.android.adb_driver import AndroidAdbDriver
from third_party.mobile_use.clients.ui_automator_client import UIAutomatorClient

from medlingo_lesson_e2e import hide_system_dialogs, reset_chapter_progress

SERIAL = "emulator-5554"
ADB = r"C:\Users\pc\AppData\Local\Android\Sdk\platform-tools\adb.exe"
PKG = "com.aistudio.medlingua.dzmed"
ACTIVITY = f"{PKG}/.MainActivity"

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
    x1, y1, x2, y2 = (int(v) for v in match.groups())
    return (x1, y1, x2, y2)


def center(el: dict) -> tuple[int, int]:
    x1, y1, x2, y2 = bounds(el)
    return ((x1 + x2) // 2, (y1 + y2) // 2)


def texts(screen) -> list[str]:
    return [(el.get("text") or "").strip() for el in screen.ui_elements]


def find(screen, needle: str, exact: bool = False) -> list[dict]:
    found = []
    for el in screen.ui_elements:
        text = (el.get("text") or "").strip()
        if (text == needle) if exact else (needle in text):
            if text:
                found.append(el)
    return found


async def settle(driver, seconds: float = 1.2):
    await asyncio.sleep(seconds)
    return await driver.get_screen_data(skip_settling=True)


async def main() -> int:
    print("[0] Chapitre Angiologie remis à zéro (L1 jouable, L2..L6 verrouillées)")
    hide_system_dialogs()
    reset_chapter_progress(seed_l1_l2=False)
    subprocess.run([ADB, "shell", "am", "start", "-n", ACTIVITY], check=False)

    client = AdbClient()
    ui = UIAutomatorClient(SERIAL)
    driver = AndroidAdbDriver(device_id=SERIAL, adb_client=client, ui_adb_client=ui)
    await driver.connect()

    screen = await settle(driver, 4.0)
    check("Parcours" in texts(screen), "l'app s'ouvre sur l'onglet Parcours")

    # --- 2. Chapitres réels dans chaque unité -------------------------------
    print("\n[2] Unités = modules avec leurs chapitres du seed")
    banner = re.compile(r"^(\d+) chapitres · (\d+)/(\d+) leçons")
    banners = [
        (t, banner.match(t)) for t in texts(screen) if banner.match(t)
    ]
    check(bool(banners), "au moins une unité affiche « N chapitres · x/y leçons »")
    check(
        not any(t.startswith("0 chapitres") for t in texts(screen)),
        "aucune unité n'affiche « 0 chapitres »",
    )
    for text, match in banners[:4]:
        chapters, done, total = (int(v) for v in match.groups())
        check(
            chapters > 0 and total == chapters * 6 and done <= total,
            f"cohérence unité « {text} » ({chapters} chapitres x 6 = {total})",
        )
    check("Angiologie" in texts(screen), "le chapitre réel « Angiologie » est listé")

    # --- 3. Ouvrir la page du chapitre Angiologie → 6 nœuds ------------------
    print("\n[3] Ouverture du chapitre Angiologie → page de leçons")
    chapter_row = find(screen, "Angiologie", exact=True)[0]
    cx, cy = center(chapter_row)
    await driver.tap(cx, cy)
    screen = await settle(driver, 2.0)
    check(
        "Tous les chapitres" in texts(screen),
        "le chapitre ouvre sa page dédiée (retour « Tous les chapitres »)",
    )
    check(
        "Leçons du chapitre" in texts(screen),
        "l'en-tête « Leçons du chapitre » est affiché",
    )

    def node_descs(current) -> dict[int, str]:
        found: dict[int, str] = {}
        for el in current.ui_elements:
            desc = (el.get("content-desc") or "").strip()
            match = re.match(r"^Leçon (\d)", desc)
            if match:
                found[int(match.group(1))] = desc
        return found

    # Les 6 nœuds dépassent la hauteur visible : on accumule sur plusieurs écrans
    # (le libellé « BOSS » est sous le 6e nœud, donc plus bas que son cercle).
    nodes: dict[int, str] = {}
    seen_texts: set[str] = set()
    for _ in range(4):
        nodes.update(node_descs(screen))
        seen_texts.update(texts(screen))
        if sorted(nodes) == [1, 2, 3, 4, 5, 6] and "BOSS" in seen_texts:
            break
        await driver.swipe(360, 1300, 360, 700, duration_ms=400)
        screen = await settle(driver, 1.2)
    print("    descs:", {k: v for k, v in sorted(nodes.items())})
    check(
        sorted(nodes) == [1, 2, 3, 4, 5, 6],
        f"les 6 leçons du chapitre sont présentes (vu {sorted(nodes)})",
    )
    check("BOSS" in seen_texts, "le 6e nœud est marqué BOSS")
    locked = [lvl for lvl, desc in nodes.items() if "verrouillée" in desc]
    check(locked == [2, 3, 4, 5, 6], f"les leçons 2 à 6 sont verrouillées (vu {locked})")
    playable = [lvl for lvl, desc in nodes.items() if "verrouillée" not in desc]
    check(playable == [1], f"seule L1 est jouable (vu {playable})")

    # --- 4. Ouvrir la leçon L1 ---------------------------------------------
    print("\n[4] Ouverture de la leçon L1")
    for _ in range(3):
        await driver.swipe(360, 700, 360, 1400, duration_ms=300)
        await asyncio.sleep(0.3)
    screen = await settle(driver, 1.2)
    l1 = [el for el in screen.ui_elements if (el.get("content-desc") or "").strip() == nodes.get(1)]
    if not l1:
        l1 = [el for el in screen.ui_elements if "Leçon 1 · disponible" in (el.get("content-desc") or "")]
    if l1:
        sx, sy = center(l1[0])
        await driver.tap(sx, sy)
        screen = await settle(driver, 2.5)
        quiz = [t for t in texts(screen) if re.match(r"^Question \d+ / \d+", t)]
        check(bool(quiz), f"l'écran de leçon s'ouvre ({quiz[0] if quiz else 'aucun'})")
        check(
            not any("Aucun exercice" in t or "no_exercises" in t for t in texts(screen)),
            "la leçon n'est pas vide (pas de « no_exercises »)",
        )
    else:
        check(False, "impossible de taper L1 (nœud introuvable)")

    with open("medlingo_path_e2e.png", "wb") as fh:
        fh.write(screen.screenshot_bytes)

    print(f"\n{checks - len(failures)}/{checks} vérifications OK")
    if failures:
        print("Échecs :")
        for item in failures:
            print(" -", item)
        return 1
    print("PARCOURS OK")
    return 0


if __name__ == "__main__":
    sys.exit(asyncio.run(main()))
