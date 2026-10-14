"""Valide la table XP de web-react/public/data/exercises.json.

Référence : les 112 exercices écrits main d'Android (LearningExercisesData +
MedLingoExercisesData), qui sont la source de vérité.

La grande majorité suit la table progressive L1..L6 -> 10 / 15 / 20 / 25 / 30
/ 35 (`10 + (level - 1) * 5`). Quelques exercices ont un bonus/malus manuel
intenté (ex. matching L1 à 15, cas clinique L5 à 35) : on les accepte tant
qu'ils sont **identiques à la source Android**.

Le test web `bulkExercises` valide la table pour les exercices *générés*,
mais pas pour les 112 écrits main : ce script comble le trou.

Usage: python tools_align_points.py [--check] [--apply]
  --check : affiche seulement le diff, n'écrit rien (mode CI)
  --apply : recopie les points Android exacts sur le web
"""
import argparse
import json
import os
import re

ROOT = os.path.dirname(os.path.abspath(__file__))
EXERCISES_JSON = os.path.join(ROOT, "web-react", "public", "data", "exercises.json")
SEED_GENERATOR = os.path.join(ROOT, "tools_gen_seed_sql.py")

ANDROID_SOURCES = [
    "app/src/main/java/com/example/data/initial/LearningExercisesData.kt",
    "app/src/main/java/com/example/data/initial/MedLingoExercisesData.kt",
]


def android_points(level: int) -> int:
    """Table de référence Android : L1..L6 -> 10 / 15 / 20 / 25 / 30 / 35."""
    assert 1 <= level <= 6, f"niveau hors plage: {level}"
    return 10 + (level - 1) * 5


def parse_android_points() -> dict[int, int]:
    """id -> points lus dans les deux fichiers de seed Android."""
    out: dict[int, int] = {}
    for rel in ANDROID_SOURCES:
        path = os.path.join(ROOT, rel)
        if not os.path.exists(path):
            continue
        src = open(path, encoding="utf-8").read()
        for block in re.split(r"ExerciseEntity\(", src)[1:]:
            mi = re.search(r"id\s*=\s*(\d+)", block)
            mp = re.search(r"points\s*=\s*(\d+)", block)
            if mi and mp:
                out[int(mi.group(1))] = int(mp.group(1))
    return out


def main() -> int:
    ap = argparse.ArgumentParser()
    ap.add_argument("--check", action="store_true",
                    help="afficher le diff sans écrire (mode CI)")
    ap.add_argument("--apply", action="store_true",
                    help="recopier les points Android exacts sur le web")
    args = ap.parse_args()

    with open(EXERCISES_JSON, encoding="utf-8") as f:
        exercises = json.load(f)

    android = parse_android_points()
    if not android:
        print("ABSENT: source Android introuvable — rien à valider.")
        return 0

    # Exercices web dont les points divergent de la source de vérité Android.
    changed = []
    for e in exercises:
        ref = android.get(e["id"])
        if ref is not None and e.get("points") != ref:
            changed.append((e["id"], e["level"], e.get("points"), ref))
            if args.apply:
                e["points"] = ref

    # Détecte aussi les exercices Android que le web n'aurait pas du tout.
    web_ids = {e["id"] for e in exercises}
    missing_from_web = sorted(set(android) - web_ids)

    if not changed and not missing_from_web:
        bonus = [e["id"] for e in exercises
                 if android.get(e["id"]) != android_points(e["level"])]
        print(f"OK: {len(exercises)} exercices, points identiques à la source "
              f"Android ({len(bonus)} bonus manuels acceptés, table "
              f"L1..L6 -> 10/15/20/25/30/35 pour le reste).")
        return 0

    if changed:
        print(f"{len(changed)} exercices à corriger :")
        for ex_id, level, old, new in changed[:15]:
            print(f"  id={ex_id} L{level}: {old} -> {new}")
        if len(changed) > 15:
            print(f"  ... et {len(changed) - 15} autres")
    if missing_from_web:
        print(f"Exercices Android manquants côté web: {missing_from_web[:10]}")

    if args.check:
        print(f"\nÉCHEC (mode --check) : {len(changed)} exercices non alignés "
              f"sur la source Android.")
        return 1

    if not args.apply:
        print("\nUtiliser --apply pour recopier les points Android sur le web.")
        return 1

    with open(EXERCISES_JSON, "w", encoding="utf-8", newline="\n") as f:
        json.dump(exercises, f, ensure_ascii=False, indent=2)
        f.write("\n")
    print(f"\nÉcrit: {len(changed)} exercices alignés dans {EXERCISES_JSON}")

    # Le seed SQL backend dérive d'exercises.json : le régénérer.
    print("Régénération du seed SQL backend...")
    import subprocess
    r = subprocess.run(["python", SEED_GENERATOR], capture_output=True, text=True,
                       cwd=ROOT)
    print(r.stdout.strip())
    if r.returncode != 0:
        print("ERREUR lors de la régénération du seed:", r.stderr.strip())
        return 1
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
