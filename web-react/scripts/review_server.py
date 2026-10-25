# -*- coding: utf-8 -*-
"""
Serveur local du dashboard de relecture du corpus d'histoires (V0.1 du plan
docs/RAPPORT_COMPETITIF_ET_PLAN_2026.md).

But : industrialiser la relecture des 814 drafts (docs/RELECTURE_HISTOIRES.md)
sans jamais contourner la règle « un draft n'est pas servi » : le dashboard ne
fait QUE noter des verdicts dans le sidecar `data/review-verdicts.v1.json`.
La publication reste portée par `scripts/build_stories_corpus.py`, qui applique
les verdicts lors de la régénération (les deux copies web/Android sont
réécrites, `npm run check:stories` doit rester vert).

Flux complet :
  1. python scripts/review_server.py          → http://127.0.0.1:8799
  2. relire chapitre par chapitre (verdicts langue + médecine, par lot de 3)
  3. python scripts/build_stories_corpus.py   → publie les doublement approuvées
  4. npm run check:stories && npx vitest run  → parité + harnais de contenu

API :
  GET  /                 → dashboard (review_dashboard.html)
  GET  /api/state        → résumé par module/chapitre + progression
  GET  /api/story?id=…   → histoire complète
  POST /api/verdict      → {storyId, dimension, verdict, by, note?}
  POST /api/note         → {storyId, note}  (note seule, sans verdict)

Aucune dépendance (stdlib). Local uniquement : bind 127.0.0.1 — ne jamais
exposer ce serveur (aucune authentification, écrit sur le dépôt).
Variables d'env de secours pour les tests : MEDLINGO_STORIES, MEDLINGO_VERDICTS.
"""
import json
import os
import sys
import threading
from datetime import datetime
from http.server import BaseHTTPRequestHandler, ThreadingHTTPServer
from pathlib import Path
from urllib.parse import urlparse, parse_qs

ROOT = Path(__file__).resolve().parent.parent
STORIES_PATH = Path(os.environ.get("MEDLINGO_STORIES", ROOT / "public" / "data" / "stories.v1.json"))
VERDICTS_PATH = Path(os.environ.get("MEDLINGO_VERDICTS", ROOT / "data" / "review-verdicts.v1.json"))
DASHBOARD = Path(__file__).resolve().parent / "review_dashboard.html"

PORT = int(sys.argv[1]) if len(sys.argv) > 1 else 8799

VERDICT_VALUES = {"approved", "needs_fixes", "rejected"}
DIMENSIONS = {"language", "medical"}

# Ordre canonique des modules (cf. WAVES du générateur).
MODULES = [
    ("clinical_en", "Anglais Médical"), ("semio", "Sémiologie Médicale"),
    ("termino", "Terminologie Médicale"), ("anat", "Anatomie"),
    ("physio", "Physiologie"), ("biochim", "Biochimie"), ("histo", "Histologie"),
    ("biophys", "Biophysique"), ("genet", "Génétique"), ("cytol", "Cytologie"),
    ("embryo", "Embryologie"), ("microbio", "Microbiologie"),
    ("pharmaco", "Pharmacologie"), ("info_med", "Informatique Médicale"),
    ("anapath", "Anatomie Pathologique"),
]

_LOCK = threading.Lock()


def load_stories():
    data = json.loads(STORIES_PATH.read_text(encoding="utf-8"))
    return data["stories"]


def load_verdicts():
    if not VERDICTS_PATH.exists():
        return {"schemaVersion": 1, "verdicts": {}, "log": []}
    return json.loads(VERDICTS_PATH.read_text(encoding="utf-8"))


def save_verdicts(doc):
    VERDICTS_PATH.parent.mkdir(parents=True, exist_ok=True)
    tmp = VERDICTS_PATH.with_suffix(".tmp")
    tmp.write_text(json.dumps(doc, ensure_ascii=False, indent=2) + "\n", encoding="utf-8", newline="\n")
    os.replace(tmp, VERDICTS_PATH)


def story_state(s, verdict):
    """Résumé d'une histoire pour le dashboard."""
    lang = verdict.get("language", {})
    med = verdict.get("medical", {})
    return {
        "storyId": s["storyId"],
        "moduleId": (s.get("moduleIds") or ["pilotes"])[0],
        "chapter": s.get("chapter") or "(pilotes)",
        "titleEn": s["title"]["en"],
        "publicationStatus": s["publicationStatus"],
        "review": s.get("review", {}),
        "verdict": {"language": lang, "medical": med},
        "ready": lang.get("verdict") == "approved" and med.get("verdict") == "approved",
    }


def build_state():
    stories = load_stories()
    doc = load_verdicts()
    verdicts = doc.get("verdicts", {})
    states = [story_state(s, verdicts.get(s["storyId"], {})) for s in stories]

    label_of = dict(MODULES)
    by_module = {}
    for st in states:
        key = st["chapter"] == "(pilotes)" and "pilotes" or st["moduleId"]
        by_module.setdefault(key, {"id": key, "label": label_of.get(key, key), "chapters": {}})
        ch = by_module[key]["chapters"].setdefault(st["chapter"], [])
        ch.append(st)

    modules = []
    for key in ["pilotes"] + [m for m, _ in MODULES]:
        if key not in by_module:
            continue
        chapters = []
        for name, items in by_module[key]["chapters"].items():
            chapters.append({
                "name": name,
                "storyIds": [i["storyId"] for i in items],
                "reviewed": sum(1 for i in items if i["verdict"]["language"] and i["verdict"]["medical"]),
                "approved": sum(1 for i in items if i["ready"]),
            })
        modules.append({"id": key, "label": by_module[key]["label"], "chapters": chapters})

    progress = {
        "total": len(states),
        "reviewed": sum(1 for s in states if s["verdict"]["language"] and s["verdict"]["medical"]),
        "ready": sum(1 for s in states if s["ready"]),
        "fixes": sum(1 for s in states
                     if "needs_fixes" in (s["verdict"]["language"].get("verdict"), s["verdict"]["medical"].get("verdict"))
                     or "rejected" in (s["verdict"]["language"].get("verdict"), s["verdict"]["medical"].get("verdict"))),
        "published": sum(1 for s in states if s["publicationStatus"] == "published"),
    }
    return {"progress": progress, "modules": modules, "stories": {s["storyId"]: s for s in states}}


class Handler(BaseHTTPRequestHandler):
    def log_message(self, fmt, *args):  # console lisible
        sys.stderr.write("%s %s\n" % (self.address_string(), fmt % args))

    # ---- utilitaires -------------------------------------------------
    def _send_json(self, obj, code=200):
        body = json.dumps(obj, ensure_ascii=False).encode("utf-8")
        self.send_response(code)
        self.send_header("Content-Type", "application/json; charset=utf-8")
        self.send_header("Content-Length", str(len(body)))
        self.send_header("Cache-Control", "no-store")
        self.end_headers()
        self.wfile.write(body)

    def _send_file(self, path, mime):
        body = path.read_bytes()
        self.send_response(200)
        self.send_header("Content-Type", mime + "; charset=utf-8")
        self.send_header("Content-Length", str(len(body)))
        self.send_header("Cache-Control", "no-store")
        self.end_headers()
        self.wfile.write(body)

    def _read_json(self):
        n = int(self.headers.get("Content-Length") or 0)
        return json.loads(self.rfile.read(n).decode("utf-8"))

    # ---- routes ------------------------------------------------------
    def do_GET(self):
        url = urlparse(self.path)
        if url.path == "/":
            self._send_file(DASHBOARD, "text/html")
        elif url.path == "/api/state":
            with _LOCK:
                self._send_json(build_state())
        elif url.path == "/api/story":
            sid = (parse_qs(url.query).get("id") or [""])[0]
            stories = load_stories()
            for s in stories:
                if s["storyId"] == sid:
                    self._send_json(s)
                    return
            self._send_json({"error": "introuvable: " + sid}, 404)
        else:
            self._send_json({"error": "route inconnue"}, 404)

    def do_POST(self):
        url = urlparse(self.path)
        try:
            payload = self._read_json()
        except Exception:
            self._send_json({"error": "JSON invalide"}, 400)
            return

        with _LOCK:
            if url.path == "/api/verdict":
                self._post_verdict(payload)
            elif url.path == "/api/note":
                self._post_note(payload)
            else:
                self._send_json({"error": "route inconnue"}, 404)

    def _post_verdict(self, p):
        sid = (p.get("storyId") or "").strip()
        dim = p.get("dimension")
        verdict = p.get("verdict")
        by = (p.get("by") or "").strip()[:24]
        note = (p.get("note") or "").strip()[:2000]
        if not any(s["storyId"] == sid for s in load_stories()):
            self._send_json({"error": "storyId inconnu: " + sid}, 404)
            return
        if dim not in DIMENSIONS:
            self._send_json({"error": "dimension invalide"}, 400)
            return
        if verdict not in VERDICT_VALUES:
            self._send_json({"error": "verdict invalide (approved|needs_fixes|rejected)"}, 400)
            return
        if not by:
            self._send_json({"error": "initiales du relecteur obligatoires"}, 400)
            return

        doc = load_verdicts()
        entry = doc["verdicts"].setdefault(sid, {})
        entry[dim] = {"verdict": verdict, "by": by, "date": datetime.now().isoformat(timespec="seconds"),
                      "note": note or entry.get(dim, {}).get("note", "")}
        doc["log"].append({"date": datetime.now().isoformat(timespec="seconds"), "storyId": sid,
                           "dimension": dim, "verdict": verdict, "by": by})
        save_verdicts(doc)
        self._send_json({"ok": True, "verdict": entry[dim]})

    def _post_note(self, p):
        sid = (p.get("storyId") or "").strip()
        note = (p.get("note") or "").strip()[:2000]
        if not any(s["storyId"] == sid for s in load_stories()):
            self._send_json({"error": "storyId inconnu: " + sid}, 404)
            return
        doc = load_verdicts()
        entry = doc["verdicts"].setdefault(sid, {})
        entry["note"] = note
        doc["log"].append({"date": datetime.now().isoformat(timespec="seconds"), "storyId": sid,
                           "dimension": "note", "verdict": "-", "by": "-"})
        save_verdicts(doc)
        self._send_json({"ok": True})


def main():
    print("MedLingo — relecture du corpus")
    print("  histoires :", STORIES_PATH)
    print("  verdicts  :", VERDICTS_PATH)
    print("→ http://127.0.0.1:%d  (Ctrl+C pour arrêter)" % PORT)
    ThreadingHTTPServer(("127.0.0.1", PORT), Handler).serve_forever()


if __name__ == "__main__":
    main()
