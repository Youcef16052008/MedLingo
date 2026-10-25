# -*- coding: utf-8 -*-
"""
Corpus « 3 histoires par chapitre » (docs/PLAN_STORIES_CORPUS.md).

Génère, pour chaque chapitre du module « Anglais Médical » (vague 1),
3 histoires CLINIQUES (récits de patients) à partir des VRAIS termes du
chapitre — archétypes A (le cas), B (l'explication au patient),
C (la passation de garde). Déterministe ; tout est `draft` avec
`provenance: "generated:corpus-v1"` : la relecture reste obligatoire.

Usage : python scripts/build_stories_corpus.py  (exécute d'abord build_stories_p3)
"""
import json
import os
import re
import sys
from pathlib import Path

sys.path.insert(0, str(Path(__file__).resolve().parent))
# NB (BUG-006) : build_stories_p3 n'est PAS importé au niveau module — il
# réécrit les deux copies dès son import ; on ne l'importe dans main() qu'une
# fois le sidecar de relecture validé et le corpus complet bâti en mémoire.

ROOT = Path(__file__).resolve().parent.parent
WEB = ROOT / "public" / "data" / "stories.v1.json"
ANDROID = ROOT.parent / "app" / "src" / "main" / "assets" / "learning" / "stories.v1.json"
VERDICTS_PATH = ROOT / "data" / "review-verdicts.v1.json"


def write_atomic(target: Path, text: str):
    """tmp + os.replace : jamais de JSON à moitié écrit (le serveur de
    relecture relit ce fichier à chaque requête)."""
    tmp = target.with_suffix(".json.tmp")
    tmp.write_text(text, encoding="utf-8", newline="\n")
    os.replace(tmp, target)

PROVENANCE = "generated:corpus-v1"

# Vagues du rollout (docs/PLAN_STORIES_CORPUS.md). Les ids d'histoires sont
# préfixés par le slug du module ; les ids d'EXPRESSIONS sont globaux (un seul
# SM-2 par expression, même terme dans plusieurs modules).
WAVES = [
    {"fr": "Anglais Médical", "id": "clinical_en", "slug": "clinical-en"},
    {"fr": "Sémiologie Médicale", "id": "semio", "slug": "semio"},
    {"fr": "Terminologie Médicale", "id": "termino", "slug": "termino"},
    # Vagues 4–15 : couverture complète (ids unit du dépôt, cf. MODULE_TO_UNIT).
    {"fr": "Anatomie", "id": "anat", "slug": "anat"},
    {"fr": "Physiologie", "id": "physio", "slug": "physio"},
    {"fr": "Biochimie", "id": "biochim", "slug": "biochim"},
    {"fr": "Histologie", "id": "histo", "slug": "histo"},
    {"fr": "Biophysique", "id": "biophys", "slug": "biophys"},
    {"fr": "Génétique", "id": "genet", "slug": "genet"},
    {"fr": "Cytologie", "id": "cytol", "slug": "cytol"},
    {"fr": "Embryologie", "id": "embryo", "slug": "embryo"},
    {"fr": "Microbiologie", "id": "microbio", "slug": "microbio"},
    {"fr": "Pharmacologie", "id": "pharmaco", "slug": "pharmaco"},
    {"fr": "Informatique Médicale", "id": "info_med", "slug": "info-med"},
    {"fr": "Anatomie Pathologique", "id": "anapath", "slug": "anapath"},
]

# Sujets trop lourds pour des récits A2 de langue — relire avant usage.
DENYLIST = {
    "Autopsy", "Living will", "Organ donation", "Miscarriage", "Terminal illness",
    "Death", "Stillbirth", "Abortion", "Euthanasia", "Suicide",
}

SYMPTOMISH = (
    "pain", "ache", "fever", "cough", "bleeding", "rash", "nausea", "vomiting",
    "dizziness", "fatigue", "swelling", "shortness of breath", "breath", "itching",
    "numbness", "tingling", "constipation", "diarrhea", "incontinence", "urination",
    "anxiety", "stress", "insomnia", "memory loss", "hearing loss", "hair loss",
    "weight loss", "vertigo", "tinnitus", "heartburn", "bloating",
    # Sémiologie : signes mesurables + suffixes classiques.
    "tachycardia", "bradycardia", "hypertension", "hypotension",
    "hyperthermia", "hypothermia", "pyrexia", "palpitations", "sputum",
    "wheezing", "macule", "papule", "vesicle", "seizure", "coma", "convulsion",
    "headache", "stiffness", "crepitus", "lymphadenopathy", "cachexia",
    "hemoptysis", "hematemesis", "hematuria", "proteinuria", "dysuria",
    "oliguria", "goiter", "orientation", "hallucination", "delusion",
)

# Adjectifs positionnels/qualificatifs : mauvais pivots (« The anterior is
# not normal ») — relégables en 3e terme par le tri.
ADJECTIVAL = {
    "anterior", "posterior", "superior", "inferior", "lateral", "medial",
    "proximal", "distal", "external", "internal", "chronic", "acute",
    "benign", "malignant", "congenital", "acquired", "systemic", "local",
    "superficial", "deep", "central", "peripheral", "axial", "sagittal",
    "coronal", "transverse", "dorsal", "ventral", "cranial", "caudal",
    "visceral", "parietal", "afferent", "efferent",
}

# Suffixes sémiologiques : -algia (douleur), -uria (urines), -pnea (souffle),
# -rrhea (écoulement), -phagia/polydipsia (appétit/soif), -pathy, -itis.
SYMPTOM_SUFFIXES = ("algia", "uria", "pnea", "rrhea", "phagia", "dipsia", "pathy", "itis", "emesis")

PERSONAS = [
    ("Sara", "she"), ("Omar", "he"), ("Leila", "she"), ("Karim", "he"),
    ("Nadia", "she"), ("Youssef", "he"), ("Amel", "she"), ("Farid", "he"),
    ("Meriem", "she"), ("Sofiane", "he"), ("Hana", "she"), ("Bilal", "he"),
]
STUDENTS = [("Lina", "she"), ("Amine", "he")]


def display_en(t):
    """« DVT (Deep vein thrombosis) » → « DVT » ; « BP — Blood Pressure » → « Blood Pressure »."""
    en = t["en"].strip()
    if " — " in en:
        en = en.split(" — ", 1)[1]
    en = re.sub(r"\s*\([^)]*\)\s*$", "", en).strip()
    return en


def slug(s):
    s = s.lower()
    s = re.sub(r"[^a-z0-9]+", "-", s).strip("-")
    return s


def low(display):
    """Minuscule pour usage en milieu de phrase ; les acronymes (DVT, COVID-19,
    BP…) restent intacts — « have DVT », pas « have dvt »."""
    if display and re.match(r"^[A-Z0-9]{2,}", display):
        return display
    return display.lower() if display else display


def is_symptomish(display):
    dl = display.lower()
    return any(k in dl for k in SYMPTOMISH) or dl.endswith(SYMPTOM_SUFFIXES)


def np_have(display):
    """Groupe nominal après « have » : bare pour les symptômes non comptables
    ET pour les pluriels (« has vital signs », pas « has a vital signs »)."""
    if is_symptomish(display):
        return low(display)
    if display.lower().endswith("s") and not display.lower().endswith("ss"):
        return low(display)
    article = "an" if low(display)[:1] in "aeiou" else "a"
    return f"{article} {low(display)}"


def L(en, fr, ar):
    return {"en": en, "fr": fr, "ar": ar}


def seg(en, fr, ar):
    return {"en": en, "fr": fr, "ar": ar}


def q(qid, text_en, text_fr, text_ar, accepted, model, hint=None, phrase_ids=None):
    # Déduplique après normalisation (ex. « chest pain » ≡ « a chest pain »
    # quand le terme est symptomish : low(d) == np_have(d)).
    seen = set()
    deduped = []
    for a in accepted:
        key = re.sub(r"\s+", " ", a.lower().replace(".", "").replace(",", "").replace("!", "").replace("?", "").strip())
        if key not in seen:
            seen.add(key)
            deduped.append(a)
    out = {"id": qid, "q": text_en, "qFr": text_fr, "qAr": text_ar,
           "accepted": deduped, "model": model}
    if hint:
        out["hint"] = hint
    if phrase_ids:
        out["phraseIds"] = phrase_ids
    return out


def pron(p, subj=True):
    return {"she": "She", "he": "He"}[p] if subj else {"she": "she", "he": "he"}[p]


def prons(p):
    return {"She": "she", "He": "he"}[pron(p)]


def make_phrases(terms):
    """Expressions = l'expression canonique « has X » — id GLOBAL par terme
    (slug du terme, pas du module) : une seule carte SM-2 partout."""
    out = []
    for t in terms:
        d = display_en(t)
        out.append({
            "id": f"gen:{slug(d)}:v1",
            "en": np_have(d),
            "fr": (t["fr"] or d).strip(),
            "ar": (t["ar"] or d).strip(),
        })
    return out


def story_case(mod, idx, chapter, terms, symptom_mode=True):
    name, p = PERSONAS[idx % len(PERSONAS)]
    sname, _ = STUDENTS[idx % len(STUDENTS)]
    t1, t2, t3 = terms[0], terms[1], terms[2]
    d1, d2, d3 = display_en(t1), display_en(t2), display_en(t3)
    P = pron(p)
    p_low = prons(p)
    phrases = make_phrases(terms[:3])
    pid1, pid2 = phrases[0]["id"], phrases[1]["id"]

    if not symptom_mode:
        return story_case_results(mod, idx, chapter, terms)

    segments = [
        seg(f"{name} is a patient at the clinic.", f"{name} est un·e patient·e à la clinique.", f"{name} مريض/مريضة في العيادة."),
        seg(f"{name} talks to {sname}, a medical student.", f"{name} parle avec {sname}, étudiant·e en médecine.", f"{name} يتحدث مع {sname}، طالب/طالبة طب."),
        seg(f"{P} has {np_have(d1)}.", f"{p_low == 'she' and 'Elle' or 'Il'} a {(t1['fr'] or d1).strip()}.", f"عنده {(t1['ar'] or d1).strip()}."),
        seg(f"{P} does not have {np_have(d2)}.", f"{'Elle' if p == 'she' else 'Il'} n'a pas de {(t2['fr'] or d2).strip()}.", f"ليس عنده {(t2['ar'] or d2).strip()}."),
        seg(f"{sname} asks, “Do you have {np_have(d2)}?”", f"{sname} demande : « Avez-vous {(t2['fr'] or d2).strip()} ? »", f"يسأل {sname}: «هل عندك {(t2['ar'] or d2).strip()}؟»"),
        seg(f"{name} says, “No, I do not.”", f"{name} répond : « Non. »", f"يجيب {name}: «لا.»"),
        seg(f"{sname} writes “{d1}” in the file.", f"{sname} écrit « {(t1['fr'] or d1).strip()} » dans le dossier.", f"يكتب {sname} «{(t1['fr'] or d1).strip()}» في الملف."),
    ]

    questions = [
        q("q1", f"Does {name} have {low(d1)}?", f"{name} a-t-il/elle {low((t1['fr'] or d1))} ?", f"هل عند {name} {(t1['ar'] or d1).strip()}؟",
          ["yes", "yes " + p_low + " does", f"yes {p_low} has {np_have(d1)}", f"{p_low} has {np_have(d1)}"],
          f"Yes, {p_low} has {np_have(d1)}.", phrase_ids=[pid1]),
        q("q2", f"Does {p_low} have {low(d2)}?", f"A-t-il/elle {low((t2['fr'] or d2))} ?", f"هل عنده {(t2['ar'] or d2).strip()}؟",
          ["no", f"no {p_low} does not", f"no {p_low} doesn't", f"{p_low} does not have {np_have(d2)}", f"{p_low} doesn't"],
          f"No, {p_low} does not have {np_have(d2)}.",
          hint=L(f"Careful: {name} does NOT have {low(d2)}.",
                 f"Attention : {name} n'a PAS {low((t2['fr'] or d2))}.",
                 f"انتبه: ليس عند {name} {(t2['ar'] or d2).strip()}."),
          phrase_ids=[pid2]),
        q("q3", f"Does {p_low} have {low(d1)} or {low(d2)}?", f"A-t-il/elle {low((t1['fr'] or d1))} ou {low((t2['fr'] or d2))} ?", f"هل عنده {(t1['ar'] or d1).strip()} أم {(t2['ar'] or d2).strip()}؟",
          [low(d1), np_have(d1), f"{p_low} has {np_have(d1)}"],
          f"{P} has {np_have(d1)}.", phrase_ids=[pid1]),
        q("q4", f"What does {name} have?", f"Qu'a {name} ?", f"ماذا عند {name}؟",
          [low(d1), np_have(d1), f"{p_low} has {np_have(d1)}"],
          f"{P} has {np_have(d1)}.", phrase_ids=[pid1]),
        q("q5", "Who writes in the file?", "Qui écrit dans le dossier ?", "من يكتب في الملف؟",
          [sname.lower(), f"the student", "the medical student"], f"{sname} writes in the file."),
    ]

    drills = [
        {"id": "d1", "prompt": f"{P} … {np_have(d1)}. (have)", "accepted": ["has"], "model": f"{P} has {np_have(d1)}."},
        {"id": "d2", "prompt": f"They … not have {np_have(d2)}. (do)", "accepted": ["do"], "model": f"They do not have {np_have(d2)}."},
        {"id": "d3", "prompt": f"I … {np_have(d1)}. (have)", "accepted": ["have"], "model": f"I have {np_have(d1)}."},
    ]

    return {
        "schemaVersion": 1, "storyId": f"{mod['slug']}-case-{idx:03d}", "contentVersion": 1,
        "title": L(f"{name}: {d1}", f"Le cas de {name} : {(t1['fr'] or d1).strip()}", f"حالة {name}: {(t1['ar'] or d1).strip()}"),
        "goal": L(f"Understand a patient case: {low(d1)} present, {low(d2)} absent.",
                  f"Comprendre un cas : {low((t1['fr'] or d1))} présent, {low((t2['fr'] or d2))} absent.",
                  f"فهم حالة: {(t1['ar'] or d1).strip()} موجود و{(t2['ar'] or d2).strip()} غائب."),
        "moduleIds": [mod["id"]], "relatedTermIds": [t1["id"], t2["id"], t3["id"]],
        "languageBand": "beginner_supported", "publicationStatus": "draft", "durationMin": 10, "chapter": chapter,
        "review": {"language": "pending", "medical": "pending"},
        "provenance": PROVENANCE,
        "segments": segments, "targetPhrases": phrases, "questions": questions,
        "drill": {"explain": L("I have → He/She has. He has → He does not have (negation).",
                                "I have → He/She has. He has → He does not have (négation).",
                                "I have ← He/She has. He has ← He does not have (النفي)."),
                  "items": drills},
        "retell": {"prompt": L(f"Report {name}'s case in two sentences. Keep the negation.",
                               f"Rapporte le cas de {name} en deux phrases. Garde la négation.",
                               f"اذكر حالة {name} في جملتين مع الحفاظ على النفي."),
                   "starters": [f"{P} has…", f"{P} does not have…"],
                   "model": f"{P} has {np_have(d1)}. {P} does not have {np_have(d2)}."},
        "transfer": {
            "context": L(f"Another patient has {np_have(d3)}. He does not have {np_have(d1)}.",
                          f"Un autre patient a {(t3['fr'] or d3).strip()}. Il n'a pas de {(t1['fr'] or d1).strip()}.",
                          f"مريض آخر عنده {(t3['ar'] or d3).strip()}. ليس عنده {(t1['ar'] or d1).strip()}."),
            "gloss": L(f"{low(d3)} = a medical word from this chapter",
                       f"{(t3['fr'] or d3).strip()} : un mot médical de ce chapitre",
                       f"{(t3['ar'] or d3).strip()}: كلمة طبية من هذا الفصل"),
            "questions": [
                q("t1", f"Does the other patient have {low(d3)}?", f"L'autre patient a-t-il {low((t3['fr'] or d3))} ?", f"هل عند المريض الآخر {(t3['ar'] or d3).strip()}؟",
                  ["yes", "yes he does", f"he has {np_have(d3)}"], f"Yes, he has {np_have(d3)}."),
                q("t2", f"Does he have {low(d1)}?", f"A-t-il {low((t1['fr'] or d1))} ?", f"هل عنده {(t1['ar'] or d1).strip()}؟",
                  ["no", "no he does not", "no he doesn't", f"he does not have {np_have(d1)}", "he doesn't"],
                  f"No, he does not have {np_have(d1)}.",
                  hint=L(f"Careful: he does NOT have {low(d1)}.",
                         f"Attention : il n'a PAS {low((t1['fr'] or d1))}.",
                         f"انتبه: ليس عنده {(t1['ar'] or d1).strip()}.")),
            ],
            "task": {"prompt": L("Report the new case in two sentences. Keep the negation.",
                                  "Rapporte le nouveau cas en deux phrases. Garde la négation.",
                                  "اذكر الحالة الجديدة في جملتين مع الحفاظ على النفي."),
                     "starters": ["He has…", "He does not have…"],
                     "model": f"He has {np_have(d3)}. He does not have {np_have(d1)}."},
        },
    }


def story_case_results(mod, idx, chapter, terms):
    """Chapitres NON symptomatiques (anatomie, systèmes, examens) : cadre
    « résultats » — « The {X} is normal. The {Y} is not normal. » fonctionne
    pour n'importe quel nom et garde un fait négatif critique."""
    name, p = PERSONAS[(idx + 3) % len(PERSONAS)]
    sname, _ = STUDENTS[idx % len(STUDENTS)]
    t1, t2, t3 = terms[0], terms[1], terms[2]
    d1, d2, d3 = display_en(t1), display_en(t2), display_en(t3)
    phrases = make_phrases(terms[:3])
    pid1, pid3 = phrases[0]["id"], phrases[2]["id"]
    fr1 = (t1["fr"] or d1).strip()
    fr3 = (t3["fr"] or d3).strip()
    ar1 = (t1["ar"] or d1).strip()
    ar3 = (t3["ar"] or d3).strip()

    segments = [
        seg(f"{name} is a patient at the clinic.", f"{name} est un·e patient·e à la clinique.", f"{name} مريض/مريضة في العيادة."),
        seg("Today, the doctor has the results.", "Aujourd'hui, le médecin a les résultats.", "اليوم، الطبيب عنده النتائج."),
        seg(f"{sname}, a medical student, listens.", f"{sname}, étudiant·e en médecine, écoute.", f"{sname}، طالب/طالبة طب، يستمع."),
        seg(f"The doctor says, “The {low(d1)} is normal.”", f"Le médecin dit : « {fr1} est normal(e). »", f"يقول الطبيب: «{ar1} طبيعي/طبيعية.»"),
        seg(f"“But the {low(d3)} is not normal.”", f"« Mais {fr3} n'est pas normal(e). »", f"«لكن {ar3} غير طبيعي/طبيعية.»"),
        seg(f"{sname} asks, “Is the {low(d3)} normal?”", f"{sname} demande : « {fr3} est-il/elle normal(e) ? »", f"يسأل {sname}: «هل {ar3} طبيعي/طبيعية؟»"),
        seg("The doctor says, “No, it is not.”", "Le médecin répond : « Non. »", "يجيب الطبيب: «لا.»"),
    ]

    questions = [
        q("q1", f"Is the {low(d1)} normal?", f"{fr1} est-il/elle normal(e) ?", f"هل {ar1} طبيعي/طبيعية؟",
          ["yes", "yes it is", f"the {low(d1)} is normal", "it is normal"],
          f"Yes, the {low(d1)} is normal.", phrase_ids=[pid1]),
        q("q2", f"Is the {low(d3)} normal?", f"{fr3} est-il/elle normal(e) ?", f"هل {ar3} طبيعي/طبيعية؟",
          ["no", "no it is not", "no it isn't", f"the {low(d3)} is not normal", "it is not normal", "it isn't normal"],
          f"No, the {low(d3)} is not normal.",
          hint=L(f"Careful: the {low(d3)} is NOT normal.", f"Attention : {fr3} n'est PAS normal(e).", f"انتبه: {ar3} غير طبيعي/طبيعية."),
          phrase_ids=[pid3]),
        q("q3", f"Which one is not normal: the {low(d1)} or the {low(d3)}?", f"Lequel/Laquelle n'est pas normal(e) : {fr1} ou {fr3} ?", f"أيهما غير طبيعي: {ar1} أم {ar3}؟",
          [low(d3), f"the {low(d3)}"], f"The {low(d3)} is not normal.", phrase_ids=[pid3]),
        q("q4", "Who has the results?", "Qui a les résultats ?", "من عنده النتائج؟",
          ["the doctor", "doctor"], "The doctor has the results."),
        q("q5", "Who listens to the doctor?", "Qui écoute le médecin ?", "من يستمع إلى الطبيب؟",
          [sname.lower(), "the student", "the medical student"], f"{sname} listens to the doctor."),
    ]

    drills = [
        {"id": "d1", "prompt": f"The {low(d1)} … normal. (be)", "accepted": ["is"], "model": f"The {low(d1)} is normal."},
        {"id": "d2", "prompt": "The results … not normal. (be)", "accepted": ["are"], "model": "The results are not normal."},
        {"id": "d3", "prompt": "It … not normal. (be)", "accepted": ["is"], "model": "It is not normal."},
    ]

    return {
        "schemaVersion": 1, "storyId": f"{mod['slug']}-case-{idx:03d}", "contentVersion": 1,
        "title": L(f"{name}: the {low(d3)} is not normal", f"Le cas de {name} : {fr3} anormal(e)", f"حالة {name}: {ar3} غير طبيعي"),
        "goal": L(f"Understand exam results: {low(d1)} normal, {low(d3)} not normal.",
                  f"Comprendre des résultats : {fr1} normal(e), {fr3} anormal(e).",
                  f"فهم النتائج: {ar1} طبيعي و{ar3} غير طبيعي."),
        "moduleIds": [mod["id"]], "relatedTermIds": [t1["id"], t2["id"], t3["id"]],
        "languageBand": "beginner_supported", "publicationStatus": "draft", "durationMin": 10,
        "review": {"language": "pending", "medical": "pending"}, "provenance": PROVENANCE,
        "chapter": chapter,
        "segments": segments, "targetPhrases": phrases, "questions": questions,
        "drill": {"explain": L("The heart is → The results are. One thing: is. Many things: are.",
                                "The heart is → The results are. Une chose : is. Plusieurs : are.",
                                "The heart is ← The results are. شيء واحد: is. أشياء كثيرة: are."),
                  "items": drills},
        "retell": {"prompt": L(f"Tell {name} the results, in two sentences. Keep the negation.",
                                f"Annonce les résultats à {name} en deux phrases. Garde la négation.",
                                f"أخبر {name} بالنتائج في جملتين مع الحفاظ على النفي."),
                   "starters": [f"The {low(d1)} is…", f"The {low(d3)} is not…"],
                   "model": f"The {low(d1)} is normal. The {low(d3)} is not normal."},
        "transfer": {
            "context": L(f"Another patient has results too: the {low(d2)} is normal, but the {low(d1)} is not normal.",
                          f"Un autre patient a aussi des résultats : {(t2['fr'] or d2).strip()} normal(e), mais {fr1} anormal(e).",
                          f"مريض آخر عنده نتائج أيضًا: {(t2['ar'] or d2).strip()} طبيعي، لكن {ar1} غير طبيعي."),
            "gloss": None,
            "questions": [
                q("t1", f"Is the {low(d2)} normal?", f"{(t2['fr'] or d2).strip()} est-il/elle normal(e) ?", f"هل {(t2['ar'] or d2).strip()} طبيعي؟",
                  ["yes", "yes it is", f"the {low(d2)} is normal"], f"Yes, the {low(d2)} is normal."),
                q("t2", f"Is the {low(d1)} normal?", f"{fr1} est-il/elle normal(e) ?", f"هل {ar1} طبيعي؟",
                  ["no", "no it is not", "no it isn't", f"the {low(d1)} is not normal", "it is not normal"],
                  f"No, the {low(d1)} is not normal.",
                  hint=L(f"Careful: the {low(d1)} is NOT normal for this patient.", f"Attention : {fr1} n'est PAS normal(e) pour ce patient.", f"انتبه: {ar1} غير طبيعي لهذا المريض.")),
            ],
            "task": {"prompt": L("Tell the new results, in two sentences.", "Annonce les nouveaux résultats, en deux phrases.", "اذكر النتائج الجديدة في جملتين."),
                     "starters": [f"The {low(d2)} is…", f"The {low(d1)} is not…"],
                     "model": f"The {low(d2)} is normal. The {low(d1)} is not normal."},
        },
    }


def story_explain(mod, idx, chapter, terms):
    name, p = PERSONAS[(idx + 5) % len(PERSONAS)]
    t1, t2, t3 = terms[0], terms[1], terms[2]
    d1, d2, d3 = display_en(t1), display_en(t2), display_en(t3)
    P = pron(p)
    him = "her" if p == "she" else "him"
    phrases = make_phrases(terms[:3])
    pid1, pid2 = phrases[0]["id"], phrases[1]["id"]

    segments = [
        seg(f"{name} is a patient.", f"{name} est un·e patient·e.", f"{name} مريض/مريضة."),
        seg(f"{P} is at the hospital.", f"{'Elle' if p == 'she' else 'Il'} est à l'hôpital.", f"هي/هو في المستشفى."),
        seg(f"The doctor explains the plan to {him}.", f"Le médecin explique le plan à {him == 'her' and 'elle' or 'lui'}.", f"يشرح الطبيب الخطة له/لها."),
        seg(f"“First, we check {low(d1)}.”", f"« D'abord, nous vérifions {(t1['fr'] or d1).strip()}. »", f"«أولًا، نتحقق من {(t1['ar'] or d1).strip()}.»"),
        seg(f"“Then, we check {low(d2)}.”", f"« Ensuite, nous vérifions {(t2['fr'] or d2).strip()}. »", f"«ثم نتحقق من {(t2['ar'] or d2).strip()}.»"),
        seg(f"“After that, I explain the results.”", f"« Après, j'explique les résultats. »", f"«بعد ذلك أشرح النتائج.»"),
        seg(f"{name} says, “Thank you. I understand now.”", f"{name} dit : « Merci. Je comprends maintenant. »", f"يقول {name}: «شكرًا. أفهم الآن.»"),
    ]

    questions = [
        q("q1", "What does the doctor check first?", "Que vérifie le médecin en premier ?", "ماذا يتحقق منه الطبيب أولًا؟",
          [low(d1), f"we check {low(d1)}", f"{low(d1)} first", f"the doctor checks {low(d1)} first"],
          f"First, the doctor checks {low(d1)}.", phrase_ids=[pid1]),
        q("q2", "What does the doctor check next?", "Que vérifie le médecin ensuite ?", "ماذا يتحقق منه الطبيب بعد ذلك؟",
          [low(d2), f"then we check {low(d2)}", f"{low(d2)} next", f"the doctor checks {low(d2)} next"],
          f"Then, the doctor checks {low(d2)}.", phrase_ids=[pid2]),
        q("q3", f"Does the doctor check {low(d2)} before {low(d1)}?", f"Le médecin vérifie-t-il {(t2['fr'] or d2).strip()} avant {(t1['fr'] or d1).strip()} ?",
          f"هل يتحقق الطبيب من {(t2['ar'] or d2).strip()} قبل {(t1['ar'] or d1).strip()}؟",
          ["no", "no he does not", "no he doesn't", f"he checks {low(d1)} first"],
          f"No, he checks {low(d1)} first, then {low(d2)}.",
          hint=L(f"Careful: {low(d1)} comes FIRST, then {low(d2)}.",
                 f"Attention : {(t1['fr'] or d1).strip()} vient D'ABORD, ensuite {(t2['fr'] or d2).strip()}.",
                 f"انتبه: {(t1['ar'] or d1).strip()} أولًا ثم {(t2['ar'] or d2).strip()}.")),
        q("q4", "Does the doctor explain the results?", "Le médecin explique-t-il les résultats ?", "هل يشرح الطبيب النتائج؟",
          ["yes", "yes he does", "he explains the results"], "Yes, he explains the results."),
        q("q5", "Who understands now?", "Qui comprend maintenant ?", "من يفهم الآن؟",
          [name.lower(), "the patient", f"the patient ({name.lower()})"], f"{name} understands now."),
    ]

    drills = [
        {"id": "d1", "prompt": "First, we … the results. (check — présent)", "accepted": ["check"], "model": "First, we check the results."},
        {"id": "d2", "prompt": "Then, the doctor … the plan. (explain — présent)", "accepted": ["explains"], "model": "Then, the doctor explains the plan."},
        {"id": "d3", "prompt": "After that, I … the results. (explain — présent)", "accepted": ["explain"], "model": "After that, I explain the results."},
    ]

    return {
        "schemaVersion": 1, "storyId": f"{mod['slug']}-explain-{idx:03d}", "contentVersion": 1,
        "title": L(f"Explaining the plan to {name}", f"Expliquer le plan à {name}", f"شرح الخطة لـ{name}"),
        "goal": L(f"Understand a doctor explaining {low(d1)} and {low(d2)} to a patient.",
                  f"Comprendre un médecin qui explique {(t1['fr'] or d1).strip()} et {(t2['fr'] or d2).strip()} à un patient.",
                  f"فهم الطبيب وهو يشرح {(t1['ar'] or d1).strip()} و{(t2['ar'] or d2).strip()} لمريض."),
        "moduleIds": [mod["id"]], "relatedTermIds": [t1["id"], t2["id"], t3["id"]],
        "languageBand": "beginner_supported", "publicationStatus": "draft", "durationMin": 10, "chapter": chapter,
        "review": {"language": "pending", "medical": "pending"}, "provenance": PROVENANCE,
        "segments": segments, "targetPhrases": phrases, "questions": questions,
        "drill": {"explain": L("we check → the doctor checkS. First / then / after that = order.",
                                "we check → the doctor checkS. First / then / after that = ordre.",
                                "we check ← the doctor checkS. الكلمات: أولًا ثم بعد ذلك."),
                  "items": drills},
        "retell": {"prompt": L("Tell the plan to the patient, in two sentences.",
                                "Dis le plan au patient, en deux phrases.", "قل الخطة للمريض في جملتين."),
                   "starters": ["First, we check…", "Then, we check…"],
                   "model": f"First, we check {low(d1)}. Then, we check {low(d2)}."},
        "transfer": {
            "context": L(f"The doctor explains another plan: “First, we check {low(d3)}. Then, we check {low(d1)}. After that, I explain the results.”",
                          f"Le médecin explique un autre plan : « D'abord {(t3['fr'] or d3).strip()}, ensuite {(t1['fr'] or d1).strip()}, puis j'explique les résultats. »",
                          f"يشرح الطبيب خطة أخرى: «أولًا {(t3['ar'] or d3).strip()}، ثم {(t1['ar'] or d1).strip()}، ثم أشرح النتائج.»"),
            "gloss": None,
            "questions": [
                q("t1", "What does the doctor check first in the new plan?", "Que vérifie le médecin en premier dans le nouveau plan ?", "ماذا يتحقق منه الطبيب أولًا في الخطة الجديدة؟",
                  [low(d3), f"{low(d3)} first", f"he checks {low(d3)} first"], f"First, the doctor checks {low(d3)}."),
                q("t2", "Does he explain the results at the end?", "Explique-t-il les résultats à la fin ?", "هل يشرح النتائج في النهاية؟",
                  ["yes", "yes he does", "he explains the results after that"], "Yes, he explains the results after that."),
            ],
            "task": {"prompt": L("Tell the new plan, in two sentences.", "Dis le nouveau plan, en deux phrases.", "قل الخطة الجديدة في جملتين."),
                     "starters": ["First, we check…", "Then, we check…"],
                     "model": f"First, we check {low(d3)}. Then, we check {low(d1)}."},
        },
    }


def story_handover(mod, idx, chapter, terms, symptom_mode=True):
    sname, _ = STUDENTS[(idx + 1) % len(STUDENTS)]
    t1, t2, t3 = terms[0], terms[1], terms[2]
    d1, d2, d3 = display_en(t1), display_en(t2), display_en(t3)
    phrases = make_phrases(terms[:3])
    pid1, pid2 = phrases[0]["id"], phrases[1]["id"]

    # Anatomie/examens : « has a problem with the heart » est clinique ;
    # « has a heart » ne le serait pas.
    def np1():
        return np_have(d1) if symptom_mode else f"a problem with the {low(d1)}"

    def np2():
        return np_have(d2) if symptom_mode else f"a problem with the {low(d2)}"

    def np3():
        return np_have(d3) if symptom_mode else f"a problem with the {low(d3)}"

    segments = [
        seg(f"{sname} is a medical student.", f"{sname} est étudiant·e en médecine.", f"{sname} طالب/طالبة طب."),
        seg("It is the morning shift.", "C'est la garde du matin.", "إنها مناوبة الصباح."),
        seg(f"The patient in bed 3 has {np1()}.", f"Le patient du lit 3 a {(t1['fr'] or d1).strip()}.", f"مريض السرير ٣ عنده {(t1['ar'] or d1).strip()}."),
        seg(f"The patient in bed 5 has {np2()}.", f"Le patient du lit 5 a {(t2['fr'] or d2).strip()}.", f"مريض السرير ٥ عنده {(t2['ar'] or d2).strip()}."),
        seg(f"The patient in bed 5 does not have {np1()}.", f"Le patient du lit 5 n'a pas de {(t1['fr'] or d1).strip()}.", f"مريض السرير ٥ ليس عنده {(t1['ar'] or d1).strip()}."),
        seg(f"{sname} reports the two cases to the doctor.", f"{sname} rapporte les deux cas au médecin.", f"يذكر {sname} الحالتين للطبيب."),
    ]

    questions = [
        q("q1", "Which patient is in bed 3?", "Quel patient est au lit 3 ?", "أي مريض في السرير ٣؟",
          [low(d1), np_have(d1), f"the patient with {low(d1)}"],
          f"The patient in bed 3 has {np_have(d1)}.", phrase_ids=[pid1]),
        q("q2", "What does the patient in bed 5 have?", "Qu'a le patient du lit 5 ?", "ماذا عند مريض السرير ٥؟",
          [low(d2), np2()], f"He has {np2()}.", phrase_ids=[pid2]),
        q("q3", "Does the patient in bed 5 have " + low(d1) + "?", f"Le patient du lit 5 a-t-il {low((t1['fr'] or d1))} ?", f"هل عند مريض السرير ٥ {(t1['ar'] or d1).strip()}؟",
          ["no", "no he does not", "no he doesn't", f"he does not have {np1()}", "he doesn't"],
          f"No, he does not have {np1()}.",
          hint=L(f"Careful: bed 5 does NOT have {low(d1)} — that is bed 3.",
                 f"Attention : le lit 5 n'a PAS {low((t1['fr'] or d1))} — c'est le lit 3.",
                 f"انتبه: السرير ٥ ليس عنده {(t1['ar'] or d1).strip()} — ذلك السرير ٣."),
          phrase_ids=[pid1]),
        q("q4", "How many cases does the student report?", "Combien de cas l'étudiant·e rapporte-t-il ?", "كم حالة يذكر الطالب؟",
          ["two", "2", "two cases"], "The student reports two cases."),
        q("q5", "Who reports the cases?", "Qui rapporte les cas ?", "من يذكر الحالات؟",
          [sname.lower(), "the student", "the medical student"], f"{sname} reports them."),
    ]

    drills = [
        {"id": "d1", "prompt": "The patient in bed 3 … the case. (report — présent)", "accepted": ["reports"], "model": "The patient in bed 3 reports the case."},
        {"id": "d2", "prompt": f"The patients … {np_have(d1)} and {np_have(d2)}. (have)", "accepted": ["have"], "model": f"The patients have {np_have(d1)} and {np_have(d2)}."},
        {"id": "d3", "prompt": f"The patient in bed 5 … not have {np_have(d1)}. (do)", "accepted": ["does"], "model": f"The patient in bed 5 does not have {np_have(d1)}."},
    ]

    return {
        "schemaVersion": 1, "storyId": f"{mod['slug']}-handover-{idx:03d}", "contentVersion": 1,
        "title": L(f"Morning shift: two cases", "Garde du matin : deux cas", "مناوبة الصباح: حالتان"),
        "goal": L(f"Report two patient cases correctly ({low(d1)} / {low(d2)}, lit 3 et 5).".replace("lit 3 et 5", "beds 3 and 5"),
                  f"Rapporter correctement deux cas ({(t1['fr'] or d1).strip()} / {(t2['fr'] or d2).strip()}, lits 3 et 5).",
                  f"نقل حالتين بدقة ({(t1['ar'] or d1).strip()} / {(t2['ar'] or d2).strip()}، السرير ٣ والسرير ٥)."),
        "moduleIds": [mod["id"]], "relatedTermIds": [t1["id"], t2["id"], t3["id"]],
        "languageBand": "beginner_supported", "publicationStatus": "draft", "durationMin": 10, "chapter": chapter,
        "review": {"language": "pending", "medical": "pending"}, "provenance": PROVENANCE,
        "segments": segments, "targetPhrases": phrases, "questions": questions,
        "drill": {"explain": L("One patient: reports/has/does. Two patients: report/have/do.",
                                "Un patient : reports/has/does. Deux patients : report/have/do.",
                                "مريض واحد: reports/has/does. مريضان: report/have/do."),
                  "items": drills},
        "retell": {"prompt": L("Report the two cases to the doctor, in two sentences.",
                                "Rapporte les deux cas au médecin, en deux phrases.", "اذكر الحالتين للطبيب في جملتين."),
                   "starters": ["The patient in bed 3 has…", "The patient in bed 5 has…"],
                   "model": f"The patient in bed 3 has {np1()}. The patient in bed 5 has {np2()}."},
        "transfer": {
            "context": L(f"Evening shift: the patient in bed 7 has {np3()}. The patient in bed 9 has {np1()}. The patient in bed 9 does not have {np2()}.",
                          f"Garde du soir : le lit 7 a {(t3['fr'] or d3).strip()}, le lit 9 a {(t1['fr'] or d1).strip()} et n'a pas {(t2['fr'] or d2).strip()}.",
                          f"مناوبة المساء: السرير ٧ عنده {(t3['ar'] or d3).strip()}، والسرير ٩ عنده {(t1['ar'] or d1).strip()} وليس عنده {(t2['ar'] or d2).strip()}."),
            "gloss": None,
            "questions": [
                q("t1", "What does the patient in bed 7 have?", "Qu'a le patient du lit 7 ?", "ماذا عند مريض السرير ٧؟",
                  [low(d3), np3()], f"He has {np3()}."),
                q("t2", "Does the patient in bed 9 have " + low(d2) + "?", f"Le patient du lit 9 a-t-il {low((t2['fr'] or d2))} ?", f"هل عند مريض السرير ٩ {(t2['ar'] or d2).strip()}؟",
                  ["no", "no he does not", "no he doesn't", f"he does not have {np2()}", "he doesn't"],
                  f"No, he does not have {np2()}.",
                  hint=L(f"Careful: bed 9 does NOT have {low(d2)}.",
                         f"Attention : le lit 9 n'a PAS {low((t2['fr'] or d2))}.",
                         f"انتبه: السرير ٩ ليس عنده {(t2['ar'] or d2).strip()}.")),
            ],
            "task": {"prompt": L("Report the evening cases, in two sentences.", "Rapporte les cas du soir, en deux phrases.", "اذكر حالات المساء في جملتين."),
                     "starters": ["The patient in bed 7 has…", "The patient in bed 9 has…"],
                     "model": f"The patient in bed 7 has {np3()}. The patient in bed 9 has {np1()}."},
        },
    }


VALID_VERDICTS = {"approved", "needs_fixes", "rejected"}


def load_verdicts():
    """Charge et valide le sidecar de relecture — AVANT toute écriture
    (BUG-006/016 : un sidecar mal formé doit produire une erreur claire et un
    working tree intact, pas un traceback après écrasement du corpus)."""
    if not VERDICTS_PATH.exists():
        return {}
    try:
        doc = json.loads(VERDICTS_PATH.read_text(encoding="utf-8"))
    except json.JSONDecodeError as e:
        raise SystemExit(f"sidecar de relecture illisible ({VERDICTS_PATH}) : JSON invalide — {e}")
    verdicts = doc.get("verdicts", {})
    if not isinstance(verdicts, dict):
        raise SystemExit("sidecar de relecture invalide : « verdicts » doit être un objet {storyId: {...}}")
    for sid, entry in verdicts.items():
        if not isinstance(entry, dict):
            raise SystemExit(
                f"sidecar de relecture invalide : « {sid} » doit être un objet "
                f'{{"language": {{"verdict": …}}, "medical": …}}, pas {type(entry).__name__}'
            )
        for dim in ("language", "medical"):
            if dim in entry:
                v = entry[dim]
                if not isinstance(v, dict) or v.get("verdict") not in VALID_VERDICTS:
                    raise SystemExit(
                        f'sidecar de relecture invalide : « {sid}.{dim} » doit être '
                        f'{{"verdict": "approved"|"needs_fixes"|"rejected", …}}'
                    )
    return verdicts


def check_verdict_ids(verdicts, all_stories):
    """BUG-006 : rejeter les verdicts orphelins AVANT la moindre écriture —
    sinon le corpus déjà écrasé + un SystemExit laissaient un working tree à
    10 histoires avec `check:stories` vert."""
    known = {s["storyId"] for s in all_stories}
    stale = sorted(set(verdicts) - known)
    if stale:
        raise SystemExit(
            f"verdicts orphelins (ids inconnus du corpus) : {stale}\n"
            f"→ nettoyer data/review-verdicts.v1.json avant de régénérer."
        )


def apply_verdicts(all_stories, verdicts):
    """Applique les verdicts de relecture notés via le dashboard
    (scripts/review_server.py → data/review-verdicts.v1.json).

    Règle : une histoire est `published` ⟺ relecture langue ET médecine
    « approved ». Tout autre verdict (needs_fixes/rejected) la retient en
    `draft`. Dès qu'un verdict existe il est AUTORITAIRE (même pour les
    pilotes) ; sans verdict, le statut du builder tient (le pilote dev
    clinical-symptoms-001 reste published tant qu'il n'est pas jugé).
    Pure : aucun fichier n'est lu ni écrit ici."""
    published = 0
    for s in all_stories:
        v = verdicts.get(s["storyId"])
        if not v:
            continue
        for dim in ("language", "medical"):
            if dim in v:
                s["review"][dim] = v[dim]["verdict"]
        if s["review"]["language"] == "approved" and s["review"]["medical"] == "approved":
            s["publicationStatus"] = "published"
            published += 1
        else:
            s["publicationStatus"] = "draft"
    if verdicts:
        print(f"verdicts de relecture : {len(verdicts)} histoire(s) · publiées : {published}")


def main():
    terms_all = json.loads((ROOT / "public" / "data" / "terms.json").read_text(encoding="utf-8"))

    generated = []
    skipped = []
    for mod in WAVES:
        chapters = {}
        for t in terms_all:
            if t["module"] != mod["fr"]:
                continue
            ch = (t.get("chapter") or "").strip()
            if not ch:
                continue
            d = display_en(t)
            if not d or d in DENYLIST:
                continue
            chapters.setdefault(ch, []).append(t)

        idx = 0
        for ch in sorted(chapters):
            ts = sorted(chapters[ch], key=lambda x: x["id"])
            if len(ts) < 3:
                skipped.append((mod["slug"], ch, len(ts)))
                continue
            # Case/handover : les cadres « has / does not have » exigent des
            # symptômes ; sinon « does not have a body temperature » n'a pas de
            # sens clinique. Explain accepte n'importe quel terme (« we check X »).
            symptomish = [t for t in ts if is_symptomish(display_en(t))]
            symptom_mode = len(symptomish) >= 2
            # Pivots : termes substantifs d'abord (les adjectifs positionnels
            # en dernier recours). Tri stable : substantifs par id, puis le reste.
            nouns = [t for t in ts if display_en(t).lower() not in ADJECTIVAL]
            ordered = nouns + [t for t in ts if t not in nouns]
            case_terms = (symptomish[:2] + [t for t in ordered if t not in symptomish[:2]])[:3]                 if symptom_mode else ordered[:3]
            explain_terms = ordered[:2] + [ordered[2]]
            generated.append(story_case(mod, idx, ch, case_terms, symptom_mode=symptom_mode))
            generated.append(story_explain(mod, idx, ch, explain_terms))
            generated.append(story_handover(mod, idx, ch, case_terms, symptom_mode=symptom_mode))
            idx += 1

    # Fusion : les 10 pilotes écrits par build_stories_p3 + le corpus généré.
    # BUG-006 : le corpus existant est lu AVANT tout import de p3 — p3 écrit
    # les deux copies dès son import ; toute validation doit donc précéder.
    current = json.loads(WEB.read_text(encoding="utf-8"))
    pilots = [s for s in current["stories"] if "provenance" not in s]
    all_stories = pilots + generated

    # Verdicts de relecture (V0.1) : validés (forme) puis (ids) AVANT écriture.
    verdicts = load_verdicts()
    check_verdict_ids(verdicts, all_stories)
    apply_verdicts(all_stories, verdicts)

    # Invariants (les mêmes que le pilote + le harnais de test).
    ids = [s["storyId"] for s in all_stories]
    assert len(ids) == len(set(ids)), "ids dupliqués"
    import unicodedata
    for s in generated:
        phrase_ids = {p["id"] for p in s["targetPhrases"]}
        for qn in s["questions"] + s["transfer"]["questions"]:
            assert qn["accepted"], f"{s['storyId']}/{qn['id']}: accepted vide"
            norm = [re.sub(r"\s+", " ", a.lower().replace(".", "").replace(",", "").replace("!", "").replace("?", "").strip()) for a in qn["accepted"]]
            assert len(set(norm)) == len(norm), f"{s['storyId']}/{qn['id']}: doublon normalisé"
            for pid in qn.get("phraseIds", []):
                assert pid in phrase_ids
        assert s["provenance"] == PROVENANCE
        v = verdicts.get(s["storyId"], {})
        lang = v.get("language", {}).get("verdict", "pending")
        med = v.get("medical", {}).get("verdict", "pending")
        expected = "published" if (lang == "approved" and med == "approved") else "draft"
        assert s["publicationStatus"] == expected, f"{s['storyId']}: statut ≠ verdicts de relecture"

    # Tout est validé en mémoire : on peut écrire. p3 d'abord (pilotes — écrit
    # dès l'import, d'où l'import tardif), puis le corpus complet, atomiquement
    # (tmp + os.replace) : un crash en cours d'écriture ne laisse jamais un
    # JSON à moitié écrit, que le serveur de relecture relit à chaque requête.
    import build_stories_p3 as p3  # noqa: F401 — exécute le builder pilote
    out = {"schemaVersion": 1, "stories": all_stories}
    for target in (WEB, ANDROID):
        write_atomic(target, json.dumps(out, ensure_ascii=False, indent=2) + "\n")

    print(f"pilotes: {len(pilots)} · générées: {len(generated)} · total: {len(all_stories)}")
    print(f"chapitres traités: {len(generated) // 3} · reportés (<4 termes): {skipped}")


if __name__ == "__main__":
    main()
