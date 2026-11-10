"""MedLingo — parcours Android de bout en bout, piloté par le driver ADB d'Artemis.

Scénario (déterministe, sans LLM) :
  1. relance l'app et attend l'onglet Parcours ;
  2. vérifie que le module affiché liste ses **chapitres réels** (`N chapitres`,
     total de leçons > 0) et qu'aucun module n'affiche « 0 chapitres » ;
  3. vérifie le découpage du chapitre « Angiologie » : 38 termes → 13 leçons de
     3 termes + 7 révisions, soit 20 nœuds (règle « 3 termes par leçon ») ;
  4. tape le chapitre → sa page dédiée s'ouvre avec Leçon 1 jouable, Leçon 2
     verrouillée et Révision 1 (obligatoire) verrouillée — plus aucun « BOSS » ;
  5. tape Leçon 1 et vérifie que la leçon s'ouvre (« Question 1 / 8 » et
     l'en-tête « Leçon 1 · 3 termes »).

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

# Chapitre Angiologie du seed : 38 termes → 13 leçons (12×3 + 2 + 2) et
# 7 révisions (une après chaque paire, la dernière seule).
ANGIO_TERMS = 38
ANGIO_LESSONS = 13
ANGIO_REVIEWS = 7
ANGIO_NODES = ANGIO_LESSONS + ANGIO_REVIEWS

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
    print("[0] Chapitre Angiologie remis à zéro (Leçon 1 jouable, la suite verrouillée)")
    hide_system_dialogs()
    reset_chapter_progress(seed_l1=False)
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
            chapters > 0 and total > 0 and done <= total,
            f"cohérence unité « {text} » ({chapters} chapitres, {total} nœuds)",
        )
    check("Angiologie" in texts(screen), "le chapitre réel « Angiologie » est listé")

    # --- 2b. Découpage « 3 termes par leçon » visible dans la liste ---------
    print("\n[2b] Découpage du chapitre (leçons de 3 termes + révisions)")
    row_info = re.compile(
        r"^(\d+) leçons \+ (\d+) révisions · (\d+)/(\d+) · moyenne (\d+)%$"
    )
    infos = [row_info.match(t) for t in texts(screen) if row_info.match(t)]
    angio = next(
        (
            m
            for m in infos
            if tuple(int(v) for v in m.groups())
            == (ANGIO_LESSONS, ANGIO_REVIEWS, 0, ANGIO_NODES, 0)
        ),
        None,
    )
    check(
        angio is not None,
        f"Angiologie affiche « {ANGIO_LESSONS} leçons + {ANGIO_REVIEWS} révisions · "
        f"0/{ANGIO_NODES} · moyenne 0% » (vu {[m.group(0) for m in infos][:3]})",
    )

    # --- 3. Ouvrir la page du chapitre Angiologie ---------------------------
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
    joined = " | ".join(texts(screen))
    check(
        f"0/{ANGIO_NODES} leçons" in joined,
        f"la page du chapitre affiche 0/{ANGIO_NODES} leçons",
    )

    # --- 3b. Les nœuds : Leçon 1 jouable, Leçon 2 et Révision 1 verrouillées -
    print("\n[3b] Nœuds du chapitre : Leçon 1, Leçon 2, Révision 1")
    seen_descs: dict[str, str] = {}
    seen_texts: set[str] = set()
    for _ in range(5):
        for el in screen.ui_elements:
            desc = (el.get("content-desc") or "").strip()
            if desc.startswith(("Leçon ", "Révision ")):
                seen_descs[desc.split(" · ")[0]] = desc
            text = (el.get("text") or "").strip()
            if text:
                seen_texts.add(text)
        # La légende du nœud (« 6 termes · obligatoire ») est sous le cercle :
        # elle peut être hors écran quand seul le cercle est visible.
        if {"Leçon 1", "Leçon 2", "Révision 1"} <= set(seen_descs) and any(
            "obligatoire" in t or "facultative" in t for t in seen_texts
        ):
            break
        await driver.swipe(360, 1300, 360, 800, duration_ms=350)
        screen = await settle(driver, 1.0)
    print("    descs:", {k: v for k, v in sorted(seen_descs.items())[:6]})
    print(
        "    légendes:",
        sorted(t for t in seen_texts if "termes" in t and "·" in t)[:6],
    )

    check(
        seen_descs.get("Leçon 1", "") == "Leçon 1 · 3 termes · disponible",
        f"Leçon 1 est jouable avec 3 termes ({seen_descs.get('Leçon 1')!r})",
    )
    check(
        seen_descs.get("Leçon 2", "") == "Leçon 2 · 3 termes · verrouillée",
        f"Leçon 2 est verrouillée ({seen_descs.get('Leçon 2')!r})",
    )
    check(
        seen_descs.get("Révision 1", "") == "Révision 1 · 6 termes · verrouillée",
        f"Révision 1 (6 termes) suit les deux leçons ({seen_descs.get('Révision 1')!r})",
    )
    check(
        any("obligatoire" in t for t in seen_texts),
        "la révision est marquée « obligatoire » tant que les leçons ne sont pas réussies",
    )
    check(
        not any("BOSS" in t for t in seen_texts),
        "plus aucun nœud « BOSS » (remplacé par les révisions)",
    )

    # --- 4. Ouvrir la leçon 1 ----------------------------------------------
    print("\n[4] Ouverture de la Leçon 1")
    for _ in range(3):
        await driver.swipe(360, 700, 360, 1400, duration_ms=300)
        await asyncio.sleep(0.3)
    screen = await settle(driver, 1.2)
    l1 = [
        el
        for el in screen.ui_elements
        if (el.get("content-desc") or "").strip().startswith("Leçon 1 · 3 termes")
    ]
    if l1:
        sx, sy = center(l1[0])
        await driver.tap(sx, sy)
        screen = await settle(driver, 2.5)
        quiz = [t for t in texts(screen) if re.match(r"^Question \d+ / \d+", t)]
        check(bool(quiz), f"l'écran de leçon s'ouvre ({quiz[0] if quiz else 'aucun'})")
        check(
            any(t.startswith("Leçon 1 · 3 termes") for t in texts(screen)),
            "l'en-tête de la leçon affiche « Leçon 1 · 3 termes »",
        )
        check(
            not any("Aucun exercice" in t or "no_exercises" in t for t in texts(screen)),
            "la leçon n'est pas vide (pas de « no_exercises »)",
        )
    else:
        check(False, "impossible de taper Leçon 1 (nœud introuvable)")

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
