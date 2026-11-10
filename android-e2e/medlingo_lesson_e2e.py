"""MedLingo — jouer une leçon de chapitre de bout en bout (driver ADB Artemis).

Parcours réel, sans LLM, en deux passes sur la même leçon (génération
déterministe — cf. `LessonGenerator.lessonQuestions`) :

  Passe 1 (oracle) : on répond « A » à chaque question ; l'app révèle la bonne
  réponse (marqueur `Correct`), qu'on enregistre.
  Passe 2 : on rejoue la même leçon en cochant les bonnes réponses → score 100 %.

Vérifie ensuite :
  - l'écran de bilan s'affiche ;
  - la table Room `lesson_scores` contient `anat|Angiologie|2` ;
  - de retour au Parcours, Leçon 2 passe à 100 %, la Révision 1 devient
    disponible (facultative : les deux leçons sont réussies) et Leçon 3 aussi ;
  - le chapitre Angiologie affiche 2/20 nœuds et l'unité Anatomie 2/N.

Le découpage « 3 termes par leçon » (ChapterSyllabus) donne 13 leçons + 7
révisions pour les 38 termes d'Angiologie : la fixture ne valide que L1, donc
la première leçon jouable est bien la Leçon 2 (8 questions).

Usage : uv run python medlingo_lesson_e2e.py
"""

import asyncio
import re
import sqlite3
import subprocess
import sys
import time
import unicodedata

from adbutils import AdbClient
from artemis.drivers.android.adb_driver import AndroidAdbDriver
from third_party.mobile_use.clients.ui_automator_client import UIAutomatorClient

SERIAL = "emulator-5554"
ADB = r"C:\Users\pc\AppData\Local\Android\Sdk\platform-tools\adb.exe"
PKG = "com.aistudio.medlingua.dzmed"
DB_REMOTE = "databases/medlingua_dz.db"
DB_LOCAL = "medlingo_medlingua_dz.db"

failures: list[str] = []
checks = 0


def check(condition: bool, label: str) -> bool:
    global checks
    checks += 1
    print(f"  {'OK  ' if condition else 'FAIL'} {label}")
    if not condition:
        failures.append(label)
    return condition


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
    return [text_of(el) for el in screen.ui_elements]


async def wait_for(driver, predicate, timeout=30.0, step=0.8):
    screen = await driver.get_screen_data(skip_settling=True)
    waited = 0.0
    while waited < timeout and not predicate(screen):
        await asyncio.sleep(step)
        waited += step
        screen = await driver.get_screen_data(skip_settling=True)
    return screen


def options(screen) -> list[dict]:
    """Cartes d'option QCM : lettre A-D + texte aligné sur la même ligne."""
    els = screen.ui_elements
    letters = [el for el in els if re.fullmatch(r"[A-D]", text_of(el))]
    cards = []
    for letter in letters:
        y = center(letter)[1]
        same_row = [
            el
            for el in els
            if text_of(el)
            and not re.fullmatch(r"[A-D]", text_of(el))
            and abs(center(el)[1] - y) <= 10
            and bounds(el)[0] > bounds(letter)[0]
        ]
        if same_row:
            cards.append(same_row[0])
    return cards


def question_of(screen) -> str:
    for text in texts(screen):
        if re.match(r"^(What|Which|Complete|Quelle|Quel|Complétez|Choisissez)", text):
            return text
    return ""


def is_results(screen) -> bool:
    """Écran de bilan : score « N% (x / 8 correct) » + boutons de fin."""
    current = texts(screen)
    has_score = any(re.match(r"^\d+% \(\d+ / \d+ correct\)$", t) for t in current)
    has_buttons = any(
        "Retour à la Pyramide" in t or "Recommencer l'évaluation" in t for t in current
    )
    return has_score and has_buttons


def is_anr(screen) -> bool:
    current = texts(screen)
    return any("isn't responding" in t or "ne répond pas" in t for t in current)


def norm(value: str) -> str:
    """Comparaison tolérante : casse, accents et espaces."""
    stripped = unicodedata.normalize("NFKD", value)
    stripped = "".join(c for c in stripped if not unicodedata.combining(c))
    return " ".join(stripped.lower().split())


def is_matching(screen) -> bool:
    return any(
        "Match each term" in t or "Associez chaque terme" in t for t in texts(screen)
    )


def matching_columns(screen) -> tuple[list[dict], list[dict]]:
    """Colonne gauche = termes EN, colonne droite = traductions FR."""
    els = [el for el in screen.ui_elements if text_of(el)]
    instruction_bottom = 0
    for el in els:
        text = text_of(el)
        if "Associez chaque terme médical" in text or "Match each term" in text:
            instruction_bottom = max(instruction_bottom, bounds(el)[3])
    left: list[dict] = []
    right: list[dict] = []
    for el in els:
        x, y = center(el)
        if y <= instruction_bottom or y > 1450:
            continue
        if x < 360:
            left.append(el)
        else:
            right.append(el)
    return left, right


async def solve_matching(driver, screen, fr_by_en: dict[str, str]) -> int:
    """Apparie EN→FR depuis la base : zéro faute, donc score parfait."""
    left, right = matching_columns(screen)
    print(
        "    appariement:",
        [text_of(el) for el in left],
        "→",
        [text_of(el) for el in right],
    )
    used: set[str] = set()
    done = 0
    for el in left:
        term = text_of(el)
        wanted = fr_by_en.get(norm(term))
        target = next(
            (
                r
                for r in right
                if norm(text_of(r)) == wanted and text_of(r) not in used
            ),
            None,
        ) if wanted else None
        if target is None:
            remaining = [r for r in right if text_of(r) not in used]
            if len(remaining) == 1:
                target = remaining[0]
        if target is None:
            print(f"    paire introuvable pour {term!r}")
            continue
        await driver.tap(*center(el))
        await asyncio.sleep(0.35)
        await driver.tap(*center(target))
        await asyncio.sleep(0.45)
        used.add(text_of(target))
        done += 1
    return done


async def dismiss_anr(driver, screen):
    """L'émulateur affiche un ANR si des taps arrivent sans fenêtre focalisée."""
    for _ in range(2):
        if not is_anr(screen):
            return screen
        button = next((el for el in screen.ui_elements if text_of(el) == "Wait"), None)
        print("    ANR détecté → Wait")
        if not button:
            return screen
        await driver.tap(*center(button))
        await asyncio.sleep(3.0)
        screen = await driver.get_screen_data(skip_settling=True)
    return screen


def trace(screen, label: str, limit: int = 14) -> None:
    print(f"    [{label}] " + " | ".join(t for t in texts(screen) if t)[:limit * 40])


def results_line(screen) -> str:
    return " | ".join(t for t in texts(screen) if t)[:300]


async def next_button(screen) -> dict | None:
    for el in screen.ui_elements:
        text = text_of(el)
        if "Question suivante" in text or "Terminer et voir le bilan" in text:
            return el
    return None


def adb(*args: str) -> bytes:
    return subprocess.run([ADB, *args], capture_output=True, check=False).stdout


def hide_system_dialogs() -> None:
    """Les ANR/crash dialogs masquent l'app et cassent les taps ADB."""
    adb("shell", "settings", "put", "global", "hide_error_dialogs", "1")


def wait_app_focus(timeout: float = 45.0) -> bool:
    """Attend que la fenêtre MedLingua ait réellement le focus (sinon ANR)."""
    deadline = timeout
    while deadline > 0:
        out = adb("shell", "dumpsys", "window").decode("utf-8", "ignore")
        focus = [line.strip() for line in out.splitlines() if "mCurrentFocus" in line]
        if focus and "medlingua" in focus[0]:
            return True
        time.sleep(1.0)
        deadline -= 1.0
    return False


def start_app() -> None:
    adb("shell", "am", "force-stop", PKG)
    adb("shell", "am", "start", "-n", f"{PKG}/.MainActivity")
    wait_app_focus()


async def open_path(driver):
    """Attend le parcours, en réarmant l'ANR dialog si l'émulateur l'affiche."""
    screen = await wait_for(
        driver, lambda s: "Angiologie" in texts(s) or is_anr(s), timeout=30.0
    )
    for _ in range(3):
        if not is_anr(screen):
            break
        screen = await dismiss_anr(driver, screen)
        screen = await wait_for(
            driver, lambda s: "Angiologie" in texts(s) or is_anr(s), timeout=30.0
        )
    return screen


def has_lesson_nodes(screen) -> bool:
    return any(
        desc_of(el).startswith(("Leçon", "Révision")) for el in screen.ui_elements
    )


async def open_chapter(driver, title: str = "Angiologie"):
    """Ouvre la page dédiée du chapitre (parcours en drill-down :
    module → liste des chapitres → page de leçons)."""
    screen = await driver.get_screen_data(skip_settling=True)
    for _ in range(3):
        if has_lesson_nodes(screen):
            return screen
        row = [el for el in screen.ui_elements if text_of(el) == title]
        if not row:
            # Module sélectionné ≠ Anatomie (état d'une exécution précédente) :
            # on repart de l'app fraîche, qui sélectionne le module de la
            # prochaine leçon (Angiologie → Anatomie).
            start_app()
            screen = await open_path(driver)
            row = [el for el in screen.ui_elements if text_of(el) == title]
            if not row:
                return screen
        await driver.tap(*center(row[0]))
        await asyncio.sleep(1.6)
        screen = await driver.get_screen_data(skip_settling=True)
        if has_lesson_nodes(screen):
            return screen
        # La page peut s'ouvrir un peu plus bas : on remonte les nœuds.
        for _ in range(3):
            await driver.swipe(360, 1250, 360, 900, duration_ms=300)
            await asyncio.sleep(0.35)
            screen = await driver.get_screen_data(skip_settling=True)
            if has_lesson_nodes(screen):
                return screen
    return screen


async def collect_lesson_nodes(driver, screen):
    """Accumule les nœuds de leçon sur plusieurs écrans (ils défilent)."""
    descs: set[str] = set()
    texts_seen: list[str] = []
    for _ in range(6):
        for el in screen.ui_elements:
            desc = desc_of(el)
            if desc.startswith(("Leçon", "Révision")):
                descs.add(desc)
            text = text_of(el)
            if text and text not in texts_seen:
                texts_seen.append(text)
        if len(descs) >= 6:
            break
        await driver.swipe(360, 1250, 360, 850, duration_ms=300)
        await asyncio.sleep(0.45)
        screen = await driver.get_screen_data(skip_settling=True)
    return sorted(descs), texts_seen, screen


async def open_available_lesson(driver, screen):
    node = [el for el in screen.ui_elements if "disponible" in desc_of(el)]
    if not node:
        return None, None, screen
    match = re.match(r"^Leçon (\d+) ", desc_of(node[0]))
    level = int(match.group(1)) if match else None
    await driver.tap(*center(node[0]))
    screen = await wait_for(
        driver, lambda s: any(re.match(r"^Question \d+ / \d+", t) for t in texts(s))
    )
    return desc_of(node[0]), level, screen


async def scroll_to_top(driver):
    for _ in range(2):
        await driver.swipe(360, 700, 360, 1400, duration_ms=300)
        await asyncio.sleep(0.4)


async def find_next_button(driver, screen):
    """Le bouton « suivant » vit sous l'explication : on remonte jusqu'à lui."""
    for _ in range(4):
        button = await next_button(screen)
        if button:
            return button, screen
        await driver.swipe(360, 1300, 360, 700, duration_ms=350)
        await asyncio.sleep(0.7)
        screen = await driver.get_screen_data(skip_settling=True)
    return None, screen


async def play_lesson(driver, screen, oracle: list[str | None] | None, fr_by_en: dict[str, str]):
    """Joue 8 questions. `oracle` vide → passe 1 (répond A, mémorise).

    L'oracle est indexé par **position**, pas par texte : une leçon peut
    contenir deux fois la même question (termes répétés), et une clé textuelle
    écraserait la première réponse.
    """
    discovered: list[str | None] = []
    seen: list[str] = []
    answered = 0
    mcq = 0
    for _ in range(12):
        if not any(re.match(r"^Question \d+ / \d+", t) for t in texts(screen)):
            break
        await scroll_to_top(driver)
        screen = await driver.get_screen_data(skip_settling=True)
        question = question_of(screen)
        position = len(seen)
        seen.append(question)
        cards = options(screen)

        if not cards and is_matching(screen):
            pairs = await solve_matching(driver, screen, fr_by_en)
            answered += 1
            discovered.append(None)
            print(f"    appariement résolu ({pairs} paires)")
            await asyncio.sleep(1.0)
            screen = await driver.get_screen_data(skip_settling=True)
            button, screen = await find_next_button(driver, screen)
            if not button:
                print("    bouton suivant introuvable après appariement")
                break
            await driver.tap(*center(button))
            screen = await driver.get_screen_data(skip_settling=True)
            continue

        if not cards:
            print(f"    type d'exercice non géré : {question[:50]!r}")
            break

        mcq += 1
        if oracle is not None:
            wanted = oracle[position] if position < len(oracle) else None
            target = next((el for el in cards if text_of(el) == wanted), None)
            if target is None:
                target = cards[0]
            await driver.tap(*center(target))
        else:
            letter = [el for el in screen.ui_elements if text_of(el) == "A"][0]
            lx, ly = center(letter)
            await driver.tap(lx + 120, ly)

        screen = await wait_for(
            driver, lambda s: any(desc_of(el) in ("Correct", "Wrong") for el in s.ui_elements)
        )
        answered += 1

        answer: str | None = None
        if oracle is None:
            marker = [el for el in screen.ui_elements if desc_of(el) == "Correct"]
            if marker:
                y = center(marker[0])[1]
                good = [
                    el
                    for el in screen.ui_elements
                    if text_of(el)
                    and not re.fullmatch(r"[A-D]", text_of(el))
                    and abs(center(el)[1] - y) <= 10
                    and 120 < bounds(el)[0] < bounds(marker[0])[0]
                ]
                if good:
                    answer = text_of(good[0])
        discovered.append(answer)

        button, screen = await find_next_button(driver, screen)
        if not button:
            print("    bouton suivant introuvable")
            break
        await driver.tap(*center(button))
        screen = await driver.get_screen_data(skip_settling=True)
    return answered, mcq, discovered, seen, screen


async def dismiss_reward(driver, screen):
    """La fin de leçon ouvre une pop-up de récompense avant le bilan."""
    for _ in range(3):
        button = next(
            (el for el in screen.ui_elements if text_of(el) == "Continuer"), None
        )
        if not button:
            return screen
        await driver.tap(*center(button))
        await asyncio.sleep(1.2)
        screen = await driver.get_screen_data(skip_settling=True)
    return screen


async def exit_lesson(driver):
    screen = await driver.get_screen_data(skip_settling=True)
    back = [el for el in screen.ui_elements if desc_of(el) == "Back"]
    if back:
        await driver.tap(*center(back[0]))
    else:
        subprocess.run([ADB, "shell", "input", "keyevent", "4"], check=False)
    return await open_path(driver)


def pull_db() -> bool:
    """Récupère la base + son journal WAL (Room écrit dedans avant checkpoint)."""
    for suffix in ("", "-wal", "-shm"):
        blob = adb("exec-out", "run-as", PKG, "cat", DB_REMOTE + suffix)
        if suffix == "" and not blob.startswith(b"SQLite format 3"):
            return False
        if blob:
            with open(DB_LOCAL + suffix, "wb") as fh:
                fh.write(blob)
    return True


def load_fr_by_en() -> dict[str, str]:
    """termEn → termFr du chapitre : la table de vérité des appariements."""
    con = sqlite3.connect(DB_LOCAL)
    rows = con.execute(
        "select termEn, termFr from medical_terms "
        "where module = 'Anatomie' and chapter = 'Angiologie'"
    ).fetchall()
    con.close()
    return {norm(en): norm(fr) for en, fr in rows if en and fr}


def sql(statement: str) -> str:
    """`adb shell` recolle les arguments sans les requoter : on passe donc une
    seule commande, entre guillemets, pour que le shell du device respecte les
    `|` (Room les utilise dans les clés `anat|Angiologie|3`)."""
    command = f'run-as {PKG} sqlite3 {DB_REMOTE} "{statement}"'
    return adb("shell", command).decode("utf-8", "ignore").strip()


def reset_chapter_progress(seed_l1: bool = True) -> None:
    """Fixture reproductible : chapitre Angiologie remis dans un état connu.

    `seed_l1=True` → seule la Leçon 1 est validée (85 %), la Leçon 2 est la
    première leçon jouable (8 questions), la Révision 1 attend les deux leçons.
    `seed_l1=False` → chapitre vierge, Leçon 1 jouable (scénario du parcours).

    Sans cela, chaque exécution jouerait la leçon suivante et le scénario ne
    serait plus déterministe.
    """
    adb("shell", "am", "force-stop", PKG)
    sql("delete from lesson_scores where lessonKey like 'anat|Angiologie|%'")
    if not seed_l1:
        return
    sql(
        "insert into lesson_scores (lessonKey, moduleId, level, best) "
        "values ('anat|Angiologie|1', 'anat', 1, 85)"
    )


async def main() -> int:
    hide_system_dialogs()

    print("\n[0] Fixture + base Room (mapping EN→FR pour les appariements)")
    reset_chapter_progress()
    check(
        sql("select group_concat(lessonKey || '=' || best) from lesson_scores "
            "where lessonKey like 'anat|Angiologie|%'")
        == "anat|Angiologie|1=85",
        "progression du chapitre réinitialisée (Leçon 1=85 %, Leçon 2 libre)",
    )
    start_app()
    check(pull_db(), f"base Room récupérée ({DB_LOCAL})")
    fr_by_en = load_fr_by_en()
    check(len(fr_by_en) >= 20, f"mapping EN→FR chargé ({len(fr_by_en)} termes)")

    client = AdbClient()
    ui = UIAutomatorClient(SERIAL)
    driver = AndroidAdbDriver(device_id=SERIAL, adb_client=client, ui_adb_client=ui)
    await driver.connect()

    print("\n[1] Parcours → chapitre Angiologie → première leçon disponible")
    screen = await open_path(driver)
    check("Angiologie" in texts(screen), "le parcours affiche le chapitre Angiologie")
    screen = await open_chapter(driver)
    await scroll_to_top(driver)
    screen = await driver.get_screen_data(skip_settling=True)

    # Totaux lus dans l'en-tête du chapitre, pas codés en dur : le découpage
    # dépend du nombre de termes du seed.
    chapter_total = 0
    unit_total = 0
    for text in texts(screen):
        chapter_line = re.match(r"^\d+/(\d+) leçons", text)
        unit_line = re.match(r"^moyenne \d+% · \d+/(\d+) leçons", text)
        if chapter_line:
            chapter_total = int(chapter_line.group(1))
        if unit_line:
            unit_total = int(unit_line.group(1))
    check(
        chapter_total == 20,
        f"Angiologie compte 20 nœuds (13 leçons + 7 révisions, vu {chapter_total})",
    )
    check(unit_total > 0, f"l'unité Anatomie expose son total de nœuds ({unit_total})")

    label, level, screen = await open_available_lesson(driver, screen)
    check(label is not None, f"une leçon disponible s'ouvre ({label})")
    if label is None or level is None:
        return 1
    check(level == 2, f"la Leçon 2 est la première leçon jouable (vu L{level})")
    lesson_key = f"anat|Angiologie|{level}"
    check(
        any(re.match(r"^Question 1 / 8", t) for t in texts(screen)),
        "l'écran affiche « Question 1 / 8 » (leçon de 3 termes)",
    )
    check(
        any(t.startswith("Leçon 2 · 3 termes") for t in texts(screen)),
        "l'en-tête de la leçon affiche « Leçon 2 · 3 termes »",
    )

    print("\n[2] Passe 1 — répondre A et mémoriser les bonnes réponses")
    answered, mcq1, oracle, seen1, screen = await play_lesson(
        driver, screen, oracle=None, fr_by_en=fr_by_en
    )
    check(answered == 8, f"8 questions répondues (vu {answered})")
    check(
        sum(1 for a in oracle if a) == mcq1 and mcq1 > 0,
        f"oracle reconstruit sur {sum(1 for a in oracle if a)} QCM (vu {mcq1})",
    )
    for i, (q, a) in enumerate(zip(seen1, oracle), 1):
        print(f"    Q{i}: {q[:60]!r} -> {a!r}")
    screen = await dismiss_reward(driver, screen)
    trace(screen, "bilan passe 1")
    check(is_results(screen), f"bilan de la passe 1 affiché ({results_line(screen)})")

    print("\n[3] Passe 2 — rejouer la même leçon avec les bonnes réponses")
    screen = await exit_lesson(driver)
    screen = await open_chapter(driver)
    label2, level2, screen = await open_available_lesson(driver, screen)
    check(label2 is not None, f"la leçon se rouvre ({label2})")
    if label2 is None:
        return 1
    check(level2 == level, f"c'est bien la même leçon (L{level2} vs L{level})")
    answered2, mcq2, again, seen2, screen = await play_lesson(
        driver, screen, oracle=oracle, fr_by_en=fr_by_en
    )
    check(answered2 == 8, f"8 questions répondues (vu {answered2})")
    check(
        seen2 == seen1,
        "la leçon est bien déterministe (mêmes questions aux deux passes)",
    )
    if seen2 != seen1:
        for i, (q1, q2) in enumerate(zip(seen1, seen2), 1):
            print(f"    Q{i} passe1={q1[:45]!r}")
            print(f"    Q{i} passe2={q2[:45]!r}")
    screen = await dismiss_reward(driver, screen)
    result2 = results_line(screen)
    print("    bilan passe 2 :", result2)
    check(is_results(screen), f"bilan de la passe 2 affiché ({result2})")
    check(
        any(re.match(r"^100% \(8 / 8 correct\)$", t) for t in texts(screen)),
        "la passe 2 obtient 100 % (8/8)",
    )

    print("\n[4] Persistance Room (`lesson_scores`)")
    adb("shell", "am", "force-stop", PKG)
    pulled = pull_db()
    check(pulled, f"base Room récupérée ({DB_LOCAL})")
    row = None
    if pulled:
        con = sqlite3.connect(DB_LOCAL)
        rows = con.execute(
            "select lessonKey, moduleId, level, best from lesson_scores order by lessonKey"
        ).fetchall()
        con.close()
        print("    lesson_scores:", rows)
        row = next((r for r in rows if r[0] == lesson_key), None)
        check(row is not None, f"la clé `{lesson_key}` est enregistrée")
        if row:
            check(
                row[1] == "anat" and row[2] == level,
                f"module/level corrects ({row[1]}/{row[2]})",
            )
            check(row[3] >= 70, f"meilleur score ≥ 70 % ({row[3]} %)")

    print("\n[5] Déblocage visible dans le Parcours")
    start_app()
    screen = await open_path(driver)
    screen = await open_chapter(driver)
    await scroll_to_top(driver)
    descs, seen_texts, screen = await collect_lesson_nodes(driver, screen)
    print("    nœuds:", descs)
    check(
        any(d.startswith(f"Leçon {level} ") and d.endswith("%") for d in descs),
        f"Leçon {level} affiche son score ({[d for d in descs if d.startswith(f'Leçon {level} ')]})",
    )
    # Les deux leçons sont réussies → la Révision 1 est facultative, donc la
    # suite s'ouvre sans elle (point de décision après L1 + L2).
    check(
        f"Révision 1 · 6 termes · disponible" in descs,
        f"la Révision 1 devient disponible ({[d for d in descs if d.startswith('Révision 1')]})",
    )
    check(
        f"Leçon {level + 1} · 3 termes · disponible" in descs,
        f"Leçon {level + 1} devient disponible",
    )
    check(
        f"Leçon {level + 2} · 3 termes · verrouillée" in descs,
        f"Leçon {level + 2} reste verrouillée",
    )
    joined = " | ".join(seen_texts)
    check(
        f"{level}/{chapter_total}" in joined,
        f"le chapitre Angiologie affiche {level}/{chapter_total} nœuds",
    )
    check(
        f"{level}/{unit_total}" in joined,
        f"l'unité Anatomie affiche {level}/{unit_total} nœuds",
    )

    with open("medlingo_lesson_e2e.png", "wb") as fh:
        fh.write(screen.screenshot_bytes)

    print(f"\n{checks - len(failures)}/{checks} vérifications OK")
    if failures:
        print("Échecs :")
        for item in failures:
            print(" -", item)
        return 1
    print("LECON OK")
    return 0


if __name__ == "__main__":
    sys.exit(asyncio.run(main()))
