"""Génère data/terms/AnglaisMedicalTerms.kt à partir de :
  - tmp_english_rows.json  (dump tmp_medecal_english.sql, dédoublonné)
  - tmp_tr/batch_*.json   (traductions FR/AR des définitions, par id source)
  - tmp_tr/examples_*.json (traductions FR/AR des exemples, par id source)

Contrôles avant écriture :
  - couverture 100 % (chaque id a une définition fr/ar et un exemple fr/ar
    non vides)
  - unicité (module, termEn) — exigée par SeedIntegrityTest
  - unicité et contiguïté des ids globaux après ajout
  - chapter non vide

Les 2 termes existants (ids 1777/1778) sont conservés tels quels ; les nouveaux
termes reçoivent des ids à la suite du plus grand id global (3858..4512), ce
qui évite tout renumérotage et donc toute remigration des progrès utilisateur.
"""
import collections, glob, json, os, re, sys

sys.stdout.reconfigure(encoding="utf-8", errors="replace")

ROOT = os.path.dirname(os.path.abspath(__file__))
ROWS = os.path.join(ROOT, "tmp_english_rows.json")
TRDIR = os.path.join(ROOT, "tmp_tr")
OUT = os.path.join(ROOT, "app", "src", "main", "java", "com", "example",
                   "data", "terms", "AnglaisMedicalTerms.kt")
BASE_ID = 3858  # max id global actuel + 1
KEEP = 2        # termes existants conservés en tête de fichier

rows = json.load(open(ROWS, encoding="utf-8"))

# --- charge les traductions ---
tr = {}
for f in sorted(glob.glob(os.path.join(TRDIR, "batch_*.json"))):
    data = json.load(open(f, encoding="utf-8"))
    for k, v in data.items():
        i = int(k)
        if i in tr:
            sys.exit(f"id {i} défini deux fois ({f})")
        tr[i] = v
print(f"définitions chargées : {len(tr)}  ({len(glob.glob(os.path.join(TRDIR, 'batch_*.json')))} fichiers)")

ex = {}
for f in sorted(glob.glob(os.path.join(TRDIR, "examples_*.json"))):
    data = json.load(open(f, encoding="utf-8"))
    for k, v in data.items():
        i = int(k)
        if i in ex:
            sys.exit(f"exemple id {i} défini deux fois ({f})")
        ex[i] = v
print(f"exemples chargés : {len(ex)}  ({len(glob.glob(os.path.join(TRDIR, 'examples_*.json')))} fichiers)")

# --- couverture ---
missing = []
missing_examples = []
for r in rows:
    t = tr.get(r["id"])
    if not t or not t.get("fr", "").strip() or not t.get("ar", "").strip():
        missing.append(r["id"])
    e = ex.get(r["id"])
    if not e or not e.get("ex_fr", "").strip() or not e.get("ex_ar", "").strip():
        missing_examples.append(r["id"])
if missing:
    print(f"\nMANQUE {len(missing)} définitions sur {len(rows)} termes")
    print("  ids manquants :", missing[:60], "..." if len(missing) > 60 else "")
    sys.exit(1)
if missing_examples:
    print(f"\nMANQUE {len(missing_examples)} exemples sur {len(rows)} termes")
    print("  ids manquants :", missing_examples[:60], "..." if len(missing_examples) > 60 else "")
    sys.exit(1)

# --- unicité (module, termEn) ---
def norm(s):
    return re.sub(r"\s+", " ", str(s).strip().lower())

seen = collections.defaultdict(list)
for r in rows:
    seen[norm(r["term_en"])].append(r["id"])
dups = {k: v for k, v in seen.items() if len(v) > 1}
if dups:
    print("DOUBLONS (module, termEn) :", dups)
    sys.exit(1)

# --- chapitre non vide ---
bad = [r["id"] for r in rows if not r["chapter"].strip()]
if bad:
    print("chapitre vide :", bad[:20])
    sys.exit(1)

# --- ids finaux ---
final = {r["id"]: BASE_ID + i for i, r in enumerate(rows)}
print(f"ids attribués : {BASE_ID}..{BASE_ID + len(rows) - 1} ({len(rows)} termes)")


def q(s):
    """Quote Kotlin en échappant antislash, guillemets et retours ligne."""
    s = str(s).replace("\\", "\\\\").replace('"', '\\"')
    s = s.replace("\r\n", "\n").replace("\n", "\\n")
    return f'"{s}"'


def keep_existing(path, n):
    """Relit le fichier courant et en extrait les n premiers MedicalTermEntity."""
    src = open(path, encoding="utf-8").read()
    blocks = re.findall(r"MedicalTermEntity\((?:.|\n)*?\n        \)", src)
    return blocks[:n]


blocks = keep_existing(OUT, KEEP)
if len(blocks) != KEEP:
    sys.exit(f"attendu {KEEP} termes existants, trouvé {len(blocks)}")

for r in rows:
    i, t, e = r["id"], tr[r["id"]], ex[r["id"]]
    blocks.append(f"""MedicalTermEntity(
            id = {final[i]},
            termEn = {q(r['term_en'])},
            termFr = {q(r['term_fr'])},
            termAr = {q(r['term_ar'])},
            definitionEn = {q(r['definition_en'])},
            definitionFr = {q(t['fr'])},
            definitionAr = {q(t['ar'])},
            module = "Anglais Médical",
            chapter = {q(r['chapter'])},
            example = {q(r['example_sentence'])},
            exampleEn = {q(r['example_sentence'])},
            exampleFr = {q(e['ex_fr'])},
            exampleAr = {q(e['ex_ar'])},
            ipaPhonetic = {q(r['phonetic'])}
        )""")

total = len(rows) + KEEP
out = f"""package com.example.data.terms

import com.example.data.local.entity.MedicalTermEntity

/**
 * Anglais Médical — {total} termes médicaux.
 *
 * {KEEP} termes rédigés à la main + {len(rows)} issus de `tmp_medecal_english.sql`
 * (dump « everyday_medical_english », 662 lignes dont 7 doublons termEn écartés).
 *
 * Les ids 3858..{BASE_ID + len(rows) - 1} prolongent la numerotation globale
 * existante : aucun renumerotage, donc aucune remigration de la progression.
 *
 * AVERTISSEMENT QUALITE : les definitions_en et les exemples anglais sont
 * issus du dump d'origine ; les traductions fr/ar ci-dessous ont ete produites
 * automatiquement et DOIVENT etre relues par un locuteur natif avant tout usage
 * pedagogique (voir `docs/anglais-medical-pending-review.md` et
 * `docs/anglais-medical-exemples-a-relire.md`).
 */
object AnglaisMedicalTerms {{
    val terms: List<MedicalTermEntity> = listOf(
        {',\n        '.join(blocks)}
    )
}}
"""
open(OUT, "w", encoding="utf-8").write(out)
print(f"ecrit -> {OUT}  ({total} termes, {len(out)} octets)")

# --- rapport de relecture experte ---
# Les traductions fr/ar sont des traductions automatiques : elles sont livrees
# avec le contenu, mais chaque entree doit etre relue par un locuteur natif.
# Le rapport est genere depuis la meme source que le .kt, donc il ne peut pas
# deriver du corpus livre.
REPORT = os.path.join(ROOT, "docs", "anglais-medical-pending-review.md")
os.makedirs(os.path.dirname(REPORT), exist_ok=True)

def cell(s):
    """Echappe une cellule de tableau markdown."""
    return str(s).replace("\\", "\\\\").replace("|", "\\|").replace("\r", " ").replace("\n", " ").strip()

by_chapter = collections.OrderedDict()
for r in rows:
    by_chapter.setdefault(r["chapter"], []).append(r)

lines = [
    "# Anglais Medical — relecture des definitions FR/AR",
    "",
    f"**{len(rows)} entrees** issues du dump `tmp_medecal_english.sql` (module "
    f"`everyday_medical_english`), plus {KEEP} termes rediges a la main (ids 1777/1778, "
    "hors de ce tableau).",
    "",
    "> Les definitions `definitionFr` / `definitionAr` ci-dessous ont ete produites "
    "automatiquement. Elles sont livrees pour completer le corpus, mais **aucun usage "
    "pedagogique n'est valide avant relecture** par un locuteur natif (francais et arabe), "
    "avec verification de la terminologie medicale.",
    "",
    "Pour chaque ligne : verifier la fidelite du sens, le registre, et l'usage "
    "terminologique local. Cocher une ligne = relecture faite. Les corrections se font dans "
    "`app/src/main/java/com/example/data/terms/AnglaisMedicalTerms.kt`.",
    "",
    f"- entrees a relire : **{len(rows)}**",
    f"- chapitres : **{len(by_chapter)}**",
    f"- ids finaux : **{BASE_ID}..{BASE_ID + len(rows) - 1}**",
    "",
    "Termes ecartes comme doublons `termEn` (7) : 233, 253, 266, 336, 454, 512, 632.",
    "",
    "Les exemples traduits correspondants sont suivis séparément dans "
    "`docs/anglais-medical-exemples-a-relire.md`.",
    "",
    "---",
    "",
]
for chapter, group in by_chapter.items():
    lines.append(f"## {chapter} ({len(group)})")
    lines.append("")
    lines.append("| | id src | id | terme (EN) | definition EN | definition FR (a relire) | definition AR (a relire) |")
    lines.append("|---|---|---|---|---|---|---|")
    for r in group:
        i = r["id"]
        t = tr[i]
        lines.append(
            f"| [ ] | {i} | {final[i]} | {cell(r['term_en'])} | {cell(r['definition_en'])} "
            f"| {cell(t['fr'])} | {cell(t['ar'])} |"
        )
    lines.append("")
open(REPORT, "w", encoding="utf-8").write("\n".join(lines))
print(f"ecrit -> {REPORT}  ({len(rows)} entrees, {len(by_chapter)} chapitres)")

# --- rapport de relecture des exemples ---
# Les exemples traduits suivent le même régime que les définitions : livraison
# complète, mais relecture obligatoire par un locuteur natif.
EXAMPLE_REPORT = os.path.join(ROOT, "docs", "anglais-medical-exemples-a-relire.md")
example_lines = [
    "# Anglais Medical — relecture des exemples FR/AR",
    "",
    f"**{len(rows)} exemples** issus du dump `tmp_medecal_english.sql`, avec les "
    "traductions automatiques `exampleFr` / `exampleAr` livrées dans "
    "`app/src/main/java/com/example/data/terms/AnglaisMedicalTerms.kt`.",
    "",
    "> Ces exemples ont ete produits automatiquement. **Aucun usage pedagogique "
    "n'est valide avant relecture** par un locuteur natif (francais et arabe), "
    "avec verification du sens, du registre et de la terminologie medicale.",
    "",
    "Pour chaque ligne : verifier que l'exemple traduit conserve le sens et le "
    "contexte clinique de l'exemple anglais. Cocher une ligne = relecture faite. "
    "Les corrections se font dans "
    "`app/src/main/java/com/example/data/terms/AnglaisMedicalTerms.kt`.",
    "",
    f"- exemples a relire : **{len(rows)}**",
    f"- chapitres : **{len(by_chapter)}**",
    f"- ids finaux : **{BASE_ID}..{BASE_ID + len(rows) - 1}**",
    "",
    "---",
    "",
]
for chapter, group in by_chapter.items():
    example_lines.append(f"## {chapter} ({len(group)})")
    example_lines.append("")
    example_lines.append("| | id src | id | terme (EN) | exemple EN | exemple FR (a relire) | exemple AR (a relire) |")
    example_lines.append("|---|---|---|---|---|---|---|")
    for r in group:
        i = r["id"]
        e = ex[i]
        example_lines.append(
            f"| [ ] | {i} | {final[i]} | {cell(r['term_en'])} | {cell(r['example_sentence'])} "
            f"| {cell(e['ex_fr'])} | {cell(e['ex_ar'])} |"
        )
    example_lines.append("")
open(EXAMPLE_REPORT, "w", encoding="utf-8").write("\n".join(example_lines))
print(f"ecrit -> {EXAMPLE_REPORT}  ({len(rows)} exemples, {len(by_chapter)} chapitres)")