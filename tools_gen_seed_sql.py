"""Generate backend/supabase/migrations/03_seed_content.sql from the web data files.

Seeds `medical_terms` (4512), `lessons` (13 module:level pairs covered by the
exercise bank) and `exercises` (44) so the backend has real content.

Usage: python tools_gen_seed_sql.py
"""
import json
import os

ROOT = os.path.dirname(os.path.abspath(__file__))
TERMS_JSON = os.path.join(ROOT, "web-react", "public", "data", "terms.json")
EXERCISES_JSON = os.path.join(ROOT, "web-react", "public", "data", "exercises.json")
OUT = os.path.join(ROOT, "backend", "supabase", "migrations", "03_seed_content.sql")

MODULE_TO_UNIT = {
    "Anatomie": "anat",
    "Physiologie": "physio",
    "Biochimie": "biochim",
    "Histologie": "histo",
    "Biophysique": "biophys",
    "Génétique": "genet",
    "Terminologie Médicale": "termino",
    "Anglais Médical": "clinical_en",
    "Cytologie": "cytol",
    "Informatique Médicale": "info_med",
    "Embryologie": "embryo",
    "Microbiologie": "microbio",
    "Pharmacologie": "pharmaco",
    "Sémiologie Médicale": "semio",
    "Anapath": "anapath",
    "Anatomie Pathologique": "anapath",
}

TYPE_MAP = {"mcq": "choice", "matching": "match", "sentence_order": "wordbank", "fill_blank": "fill"}


def sql_str(value):
    if value is None:
        return "NULL"
    return "'" + str(value).replace("'", "''") + "'"


def uuid_from_int(n):
    return f"00000000-0000-0000-0000-{int(n):012d}"


def build_spec(ex):
    t = ex["type"]
    if t == "mcq":
        return {
            "kind": "choice",
            "prompt": ex["qEn"],
            "options": ex["options"],
            "correctAnswer": ex["answer"],
        }
    if t == "matching":
        pairs = []
        for item in ex["options"]:
            left, _, right = item.partition(":")
            pairs.append({"left": left, "right": right})
        return {"kind": "match", "prompt": ex["qEn"], "pairs": pairs}
    if t == "sentence_order":
        return {
            "kind": "wordbank",
            "prompt": ex["qEn"],
            "tokens": ex["options"],
            "correctAnswer": ex["answer"],
        }
    if t == "fill_blank":
        return {"kind": "fill", "prompt": ex["qEn"], "correctAnswer": ex["answer"]}
    raise ValueError(f"unknown type {t}")


def insert_terms(terms, batch=200):
    lines = []
    cols = (
        "id, term_en, term_fr, term_ar, definition_en, definition_fr, definition_ar, "
        "etymology, clinical_pearl, mnemonic, module, chapter, example_en, example_fr, "
        "example_ar, ipa_phonetic"
    )
    for start in range(0, len(terms), batch):
        chunk = terms[start : start + batch]
        rows = []
        for t in chunk:
            rows.append(
                "("
                + ", ".join([
                    str(t["id"]),
                    sql_str(t["en"]),
                    sql_str(t["fr"]),
                    sql_str(t["ar"]),
                    sql_str(t.get("defEn")),
                    sql_str(t.get("defFr")),
                    sql_str(t.get("defAr")),
                    sql_str(t.get("etym")),
                    sql_str(t.get("pearl")),
                    sql_str(t.get("mnemo")),
                    sql_str(t["module"]),
                    sql_str(t.get("chapter")),
                    sql_str(t.get("exEn")),
                    sql_str(t.get("exFr")),
                    sql_str(t.get("exAr")),
                    sql_str(t.get("ipa")),
                ])
                + ")"
            )
        lines.append(
            f"insert into medical_terms ({cols}) values\n" + ",\n".join(rows) + "\non conflict (id) do nothing;"
        )
    return "\n\n".join(lines)


def insert_lessons(exercises):
    pairs = sorted({(e["module"], e["level"]) for e in exercises})
    rows = []
    for idx, (module, level) in enumerate(pairs, start=1):
        unit = MODULE_TO_UNIT[module]
        lesson_id = f"{unit}-l{level}-vocab"
        rows.append(
            "("
            + ", ".join([
                sql_str(lesson_id),
                sql_str(unit),
                str(level),
                sql_str(f"{module} — Niveau {level}"),
                sql_str(f"{module} — Level {level}"),
                sql_str(f"{module} — مستوى {level}"),
                str(idx),
                "20",
                sql_str("beginner" if level <= 2 else "intermediate" if level <= 4 else "advanced"),
            ])
            + ")"
        )
    return (
        "insert into lessons (id, unit_id, level, title_fr, title_en, title_ar, order_index, xp_reward, difficulty) values\n"
        + ",\n".join(rows)
        + "\non conflict (id) do nothing;"
    )


def insert_exercises(exercises):
    rows = []
    for e in exercises:
        unit = MODULE_TO_UNIT[e["module"]]
        lesson_id = f"{unit}-l{e['level']}-vocab"
        spec = json.dumps(build_spec(e), ensure_ascii=False, separators=(",", ":"))
        rows.append(
            "("
            + ", ".join([
                sql_str(uuid_from_int(e["id"])),
                sql_str(lesson_id),
                sql_str(unit),
                str(e["level"]),
                sql_str(TYPE_MAP[e["type"]]),
                sql_str(e.get("difficulty", "beginner")),
                sql_str(e["module"]),
                sql_str(e.get("chapter")),
                sql_str(spec),
                sql_str(e["qEn"]),
                sql_str(e.get("qFr")),
                sql_str(e.get("qAr")),
                sql_str("|".join(e["options"]) if e["type"] == "matching" else None),
                sql_str(e["answer"]),
                str(e.get("points", 10)),
                sql_str(e.get("ctxEn")),
                sql_str(e.get("ctxFr")),
                sql_str(e.get("ctxAr")),
                "0.5",
                "0.5",
            ])
            + ")"
        )
    cols = (
        "id, lesson_id, unit_id, level, type, difficulty, module, chapter, spec, "
        "question_en, question_fr, question_ar, options_raw, correct_answer, points, "
        "context_text_en, context_text_fr, context_text_ar, birdbrain_difficulty, birdbrain_discrimination"
    )
    return (
        f"insert into exercises ({cols}) values\n" + ",\n".join(rows) + "\non conflict (id) do nothing;"
    )


def main():
    with open(TERMS_JSON, encoding="utf-8") as f:
        terms = json.load(f)
    with open(EXERCISES_JSON, encoding="utf-8") as f:
        exercises = json.load(f)

    parts = [
        "-- MedLingo content seed — generated by tools_gen_seed_sql.py",
        "-- 4512 medical terms (mirror of the Kotlin/web seed) + 13 lessons + 44 exercises.",
        "-- Idempotent: on conflict do nothing.",
        "",
        insert_terms(terms),
        "",
        insert_lessons(exercises),
        "",
        insert_exercises(exercises),
        "",
    ]
    with open(OUT, "w", encoding="utf-8", newline="\n") as f:
        f.write("\n".join(parts))
    print(f"wrote {OUT}: {len(terms)} terms, {len(exercises)} exercises")


if __name__ == "__main__":
    main()
