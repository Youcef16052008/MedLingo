"""Valide tmp_tr/batch_*.json et tmp_tr/examples_*.json AVANT generation.

Attrape les corruptions les plus fréquentes quand on rédige de l'arabe ou du
français dans un fichier de contexte principalement anglophone :
  - fragments latins restés dans un champ arabe ("causing", "inflamed", ...)
  - chaînes restées en anglais sansMotfd
  - longueur de définition ou d'exemple anormalement courte
  - texte identique à l'anglais (traduction non faite)
  - espaces / guillemets suspects

Les définitions utilisent les champs `fr`/`ar`; les exemples traduits utilisent
les champs `ex_fr`/`ex_ar`.
"""
import glob, json, os, re, sys

sys.stdout.reconfigure(encoding="utf-8", errors="replace")

ROOT = os.path.dirname(os.path.abspath(__file__))
LATIN = re.compile(r"[A-Za-z]")
ARABIC = re.compile(r"[\u0600-\u06FF]")
problems = []


def lev(a, b):
    if a == b:
        return 0
    if not a or not b:
        return max(len(a), len(b))
    prev = list(range(len(b) + 1))
    for i, ca in enumerate(a, 1):
        cur = [i]
        for j, cb in enumerate(b, 1):
            cur.append(min(prev[j] + 1, cur[j - 1] + 1,
                           prev[j - 1] + (ca != cb)))
        prev = cur
    return prev[-1]


rows = {r["id"]: r for r in json.load(open(os.path.join(ROOT, "tmp_english_rows.json"),
                                          encoding="utf-8"))}

# Sigles médicaux latins légitimes en texte arabe : on les tolère, tout le
# reste du latin est une contamination.
ALLOWED_LATIN = {"HDL", "LDL", "MRI", "CT", "IV", "IM"}
# Tout caractère qui n'est ni arabe, ni chiffre, ni ponctuation, ni espace,
# ni lettre latine autorisée : autre alphabet (CJK, cyrillique, etc.).
FOREIGN = re.compile(r"[^\u0600-\u06FF\u0750-\u077F\uFB50-\uFDFF\uFE70-\uFEFF"
                     r"A-Za-z0-9\u00C0-\u024F .,;:!?()\[\]/%\u2013\u2014'\u2019-]")

# (masque de fichiers, champs FR/AR, champ source anglais, longueur minimale).
# Les définitions complètes exigent au moins 12 caractères; les exemples sources
# sont des phrases complètes mais souvent compactes, d'où un seuil de 8.
CHECK_SPECS = [
    ("batch_*.json", ("fr", "ar"), "definition_en", 12),
    ("examples_*.json", ("ex_fr", "ex_ar"), "example_sentence", 8),
]

checked_files = []
for mask, fields, source_key, min_len in CHECK_SPECS:
    for f in sorted(glob.glob(os.path.join(ROOT, "tmp_tr", mask))):
        checked_files.append(f)
        for k, v in json.load(open(f, encoding="utf-8")).items():
            i = int(k)
            src = rows.get(i)
            if src is None:
                problems.append((i, "id absent du dump source"))
                continue
            for lang in fields:
                val = (v.get(lang) or "").strip()
                tag = f"batch {os.path.basename(f)} id={i} [{lang}]"
                if not val:
                    problems.append((i, f"{tag} vide"))
                    continue
                if lang in ("ar", "ex_ar"):
                    words = set(m.group(0) for m in re.finditer(r"[A-Za-z]+", val))
                    stray = words - ALLOWED_LATIN
                    if stray:
                        problems.append((i, f"{tag} latin non autorisé : {sorted(stray)}"))
                    if not ARABIC.search(val):
                        problems.append((i, f"{tag} aucun caractère arabe"))
                    bad = FOREIGN.findall(val)
                    if bad:
                        problems.append((i, f"{tag} caractères d'un autre alphabet : "
                                             f"{sorted(set(bad))}"))
                if len(val) < min_len:
                    problems.append((i, f"{tag} trop court ({len(val)} car.) : {val!r}"))
                # la traduction ne doit pas être la copie de l'anglais
                norm_src = re.sub(r"\s+", " ", src[source_key]).strip().lower()
                if re.sub(r"\s+", " ", val).strip().lower() == norm_src:
                    problems.append((i, f"{tag} identique à {source_key}"))
            # le français doit contenir des caractères latins accentués ou non
            fr = v.get(fields[0]) or ""
            if not re.search(r"[A-Za-zÀ-ÿ]", fr):
                problems.append((i, f"[fr] id={i} sans caractères latins"))

print(f"fichiers analysés : {len(checked_files)}")
if not problems:
    print("OK — aucune contamination detectee")
    sys.exit(0)

print(f"\n{len(problems)} PROBLEME(S) :")
for i, msg in problems:
    print(f"  id={i}: {msg}")
sys.exit(1)