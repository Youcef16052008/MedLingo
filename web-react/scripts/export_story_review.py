# -*- coding: utf-8 -*-
"""
Exporte les histoires générées en fiches de relecture lisibles :
docs/review/stories/<module-slug>/<chapter-slug>.md — une fiche par chapitre
(3 histoires), avec tout ce que le relecteur doit vérifier et des cases à
cocher. Le relecteur n'a PAS à lire le JSON.

Usage : python scripts/export_story_review.py   (régénère toutes les fiches)
"""
import json
import re
from pathlib import Path

ROOT = Path(__file__).resolve().parent.parent
OUT_DIR = ROOT.parent / "docs" / "review" / "stories"

data = json.loads((ROOT / "public" / "data" / "stories.v1.json").read_text(encoding="utf-8"))
generated = [s for s in data["stories"] if s.get("provenance") == "generated:corpus-v1"]


def slug(s):
    s = s.lower()
    s = re.sub(r"[^a-z0-9]+", "-", s).strip("-")
    return s


def check(label):
    return f"- [ ] {label}"


def preserve_checks(new_text, existing_path):
    """BUG-032 : réexporter ne doit pas effacer le travail des relecteurs.
    Toute case « - [x] <label> » du fichier existant dont le libellé figure
    dans le nouveau texte est réintroduite cochée."""
    if not existing_path.exists():
        return new_text
    done = set()
    for line in existing_path.read_text(encoding="utf-8").splitlines():
        m = re.match(r"- \[x\] (.+)$", line.strip())
        if m:
            done.add(m.group(1).strip())
    if not done:
        return new_text
    out = []
    for line in new_text.split("\n"):
        m = re.match(r"- \[ \] (.+)$", line)
        if m and m.group(1).strip() in done:
            out.append(f"- [x] {m.group(1)}")
        else:
            out.append(line)
    return "\n".join(out)


def render_story(s, n):
    lines = []
    lines.append(f"## {n}. `{s['storyId']}` — {s['title']['fr']} / _{s['title']['en']}_")
    lines.append("")
    lines.append(f"**Objectif :** {s['goal']['fr']}")
    lines.append(f"**Archétype :** {s['storyId'].split('-')[1]} · **Durée :** {s['durationMin']} min · **Statut :** {s['publicationStatus']}")
    lines.append("")
    lines.append("### Récit (segments EN / aide FR / aide AR)")
    for i, seg in enumerate(s["segments"], 1):
        lines.append(f"{i}. **EN** {seg['en']}")
        lines.append(f"   - FR : {seg['fr']}")
        lines.append(f"   - AR : {seg['ar']}")
    lines.append("")
    lines.append("### Expressions ciblées")
    for p in s["targetPhrases"]:
        lines.append(f"- `{p['id']}` — **{p['en']}** · FR : {p['fr']} · AR : {p['ar']}")
    lines.append("")
    lines.append("### Questions (acceptées / modèle / hint)")
    for q in s["questions"] + s["transfer"]["questions"]:
        tag = "T" if q["id"].startswith("t") else ""
        lines.append(f"- **{tag}{q['id']}.** {q['q']}")
        lines.append(f"  - accepté : {' ; '.join(q['accepted'])}")
        lines.append(f"  - modèle : {q['model']}")
        if q.get("hint"):
            lines.append(f"  - hint (si erreur) — FR : {q['hint']['fr']}")
    lines.append("")
    lines.append("### Drill")
    lines.append(f"- Explication : {s['drill']['explain']['fr']}")
    for d in s["drill"]["items"]:
        lines.append(f"- `{d['prompt']}` → {d['accepted'][0]} · modèle : {d['model']}")
    lines.append("")
    lines.append("### Productions")
    lines.append(f"- **Reformulation :** {s['retell']['prompt']['fr']} — amorces : {' / '.join(s['retell']['starters'])} — modèle : *{s['retell']['model']}*")
    tr = s["transfer"]
    lines.append(f"- **Transfert (contexte) :** {tr['context']['fr']}")
    if tr.get("gloss"):
        lines.append(f"- Glossaire : {tr['gloss']['fr']}")
    lines.append(f"- **Tâche :** {tr['task']['prompt']['fr']} — modèle : *{tr['task']['model']}*")
    lines.append("")
    lines.append("### À vérifier")
    lines.append(check("Anglais naturel et niveau A1–A2 (articles, genre, pluriel)"))
    lines.append(check("Aides FR/AR fidèles — négations jamais inversées"))
    lines.append(check("Faits cliniques exacts, aucun conseil diagnostique/thérapeutique"))
    lines.append(check("Hints présents et justes sur les faits négatifs"))
    lines.append(check("Réponses acceptées exhaustives (contractions incluses)"))
    lines.append("")
    return "\n".join(lines)


by_chapter = {}
for s in generated:
    by_chapter.setdefault((s["moduleIds"][0], s.get("chapter", "sans-chapitre")), []).append(s)

OUT_DIR.mkdir(parents=True, exist_ok=True)
count = 0
for (mod, chapter), stories in sorted(by_chapter.items()):
    order = {"case": 0, "explain": 1, "handover": 2}
    stories.sort(key=lambda s: order.get(s["storyId"].split("-")[-2], 9))
    content = []
    content.append(f"# Relecture — {chapter} ({mod})")
    content.append("")
    content.append(
        "Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans "
        "`stories.v1.json` (ou le builder), puis publier le chapitre selon "
        "[RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md)."
    )
    content.append("")
    content.append(check("**Chapitre entier relu (linguistique)**"))
    content.append(check("**Chapitre entier relu (médical)**"))
    content.append("")
    for i, s in enumerate(stories, 1):
        content.append(render_story(s, i))
    target = OUT_DIR / mod / f"{slug(chapter)}.md"
    target.parent.mkdir(parents=True, exist_ok=True)
    target.write_text(preserve_checks("\n".join(content), target), encoding="utf-8", newline="\n")
    count += 1

print(f"{count} fiches de relecture écrites dans {OUT_DIR}")
