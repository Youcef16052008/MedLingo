"""Parse tmp_medecal_english.sql -> tmp_english_rows.json

Robust aux guillemets imbriqués : les champs sont délimités par des apostrophes
simples, et `example_sentence` contient des guillemets doubles ("...") ainsi que
des virgules, ce qui casse un simple split(',') sur place.
"""
import json, re, sys, collections

sys.stdout.reconfigure(encoding="utf-8", errors="replace")

SQL = "tmp_medecal_english.sql"
OUT = "tmp_english_rows.json"

COLS = ["id", "term_en", "term_fr", "term_ar", "phonetic",
        "example_sentence", "definition_en", "category", "chapter"]


def split_fields(body):
    """Découpe un VALUES (...) en champs, en ignorant virgules et apostrophes
    situées à l'intérieur d'un littéral entre apostrophes simples."""
    fields, cur, i, in_str = [], [], 0, False
    while i < len(body):
        ch = body[i]
        if in_str:
            if ch == "\\":
                cur.append(body[i:i + 2]); i += 2; continue
            if ch == "'":
                in_str = False; cur.append("'"); i += 1; continue
            cur.append(ch); i += 1; continue
        if ch == "'":
            in_str = True; cur.append("'"); i += 1; continue
        if ch == ",":
            fields.append("".join(cur)); cur = []; i += 1; continue
        cur.append(ch); i += 1
    fields.append("".join(cur))
    out = []
    for f in fields:
        v = f.strip().strip("'").strip()
        # SQL échappe l'apostrophe en la doublant : "d''urgences" -> "d'urgences".
        v = v.replace("''", "'")
        out.append(v)
    return out


text = open(SQL, encoding="utf-8", errors="replace").read()
rows, bad = [], []
for raw in text.splitlines():
    line = raw.strip().rstrip(",").rstrip(";").strip()
    if not line.startswith("(") or not line.endswith(")"):
        continue
    vals = split_fields(line[1:-1])
    if len(vals) != len(COLS):
        bad.append((line[:60], len(vals)))
        continue
    rec = dict(zip(COLS, vals))
    try:
        rec["id"] = int(rec["id"])
    except ValueError:
        bad.append((line[:60], "id non entier"))
        continue
    rows.append(rec)

# `example_sentence` est stocké entre guillemets doubles à l'intérieur du littéral
# SQL : '"Take him to the emergency room!"' -> on retire l'enveloppe.
for r in rows:
    ex = r["example_sentence"].strip()
    if len(ex) >= 2 and ex[0] == '"' and ex[-1] == '"':
        r["example_sentence"] = ex[1:-1].strip()

print(f"lignes VALUES analysees : {len(rows)}")
print(f"lignes rejetees         : {len(bad)}")
for b in bad[:10]:
    print("   ", b)

rows.sort(key=lambda r: r["id"])
ids = [r["id"] for r in rows]
print(f"id : {min(ids)}..{max(ids)}  uniques={len(set(ids))}")

print("\n--- champs vides ---")
for c in COLS:
    n = sum(1 for r in rows if not str(r[c]).strip())
    if n:
        print(f"  {c}: {n}")

print("\n--- categories ---")
for cat, n in collections.Counter(r["category"] for r in rows).most_common():
    print(f"  {n:4d}  {cat}")

print("\n--- chapitres ---")
for ch, n in sorted(collections.Counter(r["chapter"] for r in rows).items()):
    print(f"  {n:4d}  {ch}")

# Dédoublonnage sur term_en normalisé, en gardant la première occurrence.
def norm(s):
    return re.sub(r"\s+", " ", s.strip().lower())

seen, uniq, dups = {}, [], []
for r in rows:
    k = norm(r["term_en"])
    if k in seen:
        dups.append((k, seen[k]["id"], r["id"], r["chapter"], r["category"]))
        continue
    seen[k] = r
    uniq.append(r)

print(f"\n--- doublons term_en : {len(dups)} (on garde la 1re occurrence) ---")
for k, a, b, ch, cat in dups:
    print(f"  {k:20s} id {a} (chapitre {ch}) ignore id {b} ({cat})")

print(f"\ntermes uniques : {len(uniq)}")
json.dump(uniq, open(OUT, "w", encoding="utf-8"), ensure_ascii=False, indent=1)
print(f"ecrit -> {OUT}")