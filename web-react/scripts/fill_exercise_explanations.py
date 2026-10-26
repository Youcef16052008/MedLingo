# -*- coding: utf-8 -*-
"""
WS1.3b — remplit les explications trilingues (expEn/expFr/expAr) des exercices
gap/pair du corpus v2 à partir des définitions des termes (terms.json).

Les 4 408 exercices mcq du corpus régénéré portaient déjà l'explication du
terme cible ; les 8 592 gap/pair non (BUG-027 : bloc « Explication » vide
2 fois sur 3). Ce script rétablit la parité de traitement : l'answer d'un
gap/pair EST le terme cible → on lui attache les définitions defEn/defFr/defAr
du terme, exactement la même source que les mcq.

Usage : python scripts/fill_exercise_explanations.py [--dry-run]
Écrit atomiquement (tmp + os.replace). Les exercices sans appariement sont
laissés intacts (dénombrés honnêtement).
"""
import json
import os
import re
import sys
from pathlib import Path

ROOT = Path(__file__).resolve().parent.parent
EXERCISES = ROOT / "public" / "data" / "exercises.json"
TERMS = ROOT / "public" / "data" / "terms.json"

DRY = "--dry-run" in sys.argv


def display_key(s: str) -> str:
    """« DVT (Deep vein thrombosis) » / « BP — Blood Pressure » → clé canonique.
    Même extraction que display_en() du générateur d'histoires + pluriel toléré."""
    s = s.strip()
    if " — " in s:
        s = s.split(" — ", 1)[1]
    s = re.sub(r"\s*\([^)]*\)\s*$", "", s).strip()
    s = re.sub(r"[^a-z0-9]+", " ", s.lower()).strip()
    return s


def main():
    terms = json.loads(TERMS.read_text(encoding="utf-8"))
    by_key = {}
    for t in terms:
        by_key.setdefault(display_key(t["en"]), t)
        # variante pluriel → singulier (« atria » ↔ « atrium » non géré : les
        # réponses du corpus reprennent le display du terme tel quel)
        k = display_key(t["en"])
        if k.endswith("s") and not k.endswith("ss"):
            by_key.setdefault(k[:-1], t)

    data = json.loads(EXERCISES.read_text(encoding="utf-8"))
    filled = untouched = no_match = 0
    for ex in data:
        if ex.get("expEn"):
            untouched += 1
            continue
        t = by_key.get(display_key(ex.get("answer", "")))
        if not t or not t.get("defEn"):
            no_match += 1
            continue
        ex["expEn"] = t["defEn"].strip()
        ex["expFr"] = (t.get("defFr") or t["defEn"]).strip()
        ex["expAr"] = (t.get("defAr") or "").strip()
        filled += 1

    print(f"déjà pourvues : {untouched} · remplies : {filled} · sans appariement : {no_match} · total : {len(data)}")
    if DRY:
        return
    tmp = EXERCISES.with_suffix(".json.tmp")
    tmp.write_text(json.dumps(data, ensure_ascii=False, indent=2) + "\n", encoding="utf-8", newline="\n")
    os.replace(tmp, EXERCISES)
    print("écrit:", EXERCISES)


if __name__ == "__main__":
    main()
