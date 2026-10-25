# -*- coding: utf-8 -*-
"""
Phase 3 (3.1) — génère stories.v1.json : 10 histoires pilotes.

L'histoire 001 (published) est reprise telle quelle ; les 9 nouvelles sont
`draft` : la règle du guide §6.1 s'applique — un draft n'est JAMAIS servi,
il devient `published` seulement après relecture (docs/RELECTURE_HISTOIRES.md).

Les ids d'expressions sont PARTAGÉS entre histoires (3.3) : réutiliser un id
existants (ex. en:no-fever:v1) fait vivre une seule carte SM-2 partout.

Usage : python scripts/build_stories_p3.py   (depuis web-react/)
Écrit les DEUX copies (web + asset Android) puis vérifie la parité.
"""
import json
import shutil
from pathlib import Path

ROOT = Path(__file__).resolve().parent.parent          # web-react/
REPO = ROOT.parent
WEB = ROOT / "public" / "data" / "stories.v1.json"
ANDROID = REPO / "app" / "src" / "main" / "assets" / "learning" / "stories.v1.json"

current = json.loads(WEB.read_text(encoding="utf-8"))
pilot = current["stories"][0]
assert pilot["storyId"] == "clinical-symptoms-001"


def L(en, fr, ar):
    return {"en": en, "fr": fr, "ar": ar}


def seg(en, fr, ar):
    return {"en": en, "fr": fr, "ar": ar}


def q(qid, text, qfr, qar, accepted, model, hint=None, phrase_ids=None):
    out = {"id": qid, "q": text, "qFr": qfr, "qAr": qar, "accepted": accepted, "model": model}
    if hint:
        out["hint"] = hint
    if phrase_ids:
        out["phraseIds"] = phrase_ids
    return out


def story(sid, title, goal, modules, term_ids, minutes, segments, phrases,
          questions, drill_explain, drills, retell_prompt, retell_starters, retell_model,
          tr_context, tr_gloss, tr_questions, tr_task_prompt, tr_task_starters, tr_task_model):
    return {
        "schemaVersion": 1,
        "storyId": sid,
        "contentVersion": 1,
        "title": title,
        "goal": goal,
        "moduleIds": modules,
        "relatedTermIds": term_ids,
        "languageBand": "beginner_supported",
        "publicationStatus": "draft",
        "durationMin": minutes,
        "review": {"language": "pending", "medical": "pending"},
        "segments": segments,
        "targetPhrases": phrases,
        "questions": questions,
        "drill": {"explain": drill_explain, "items": drills},
        "retell": {"prompt": retell_prompt, "starters": retell_starters, "model": retell_model},
        "transfer": {
            "context": tr_context,
            "gloss": tr_gloss,
            "questions": tr_questions,
            "task": {"prompt": tr_task_prompt, "starters": tr_task_starters, "model": tr_task_model},
        },
    }


stories = [pilot]

# ============================================================ 002 — Se présenter
stories.append(story(
    "self-intro-002",
    L("Introducing yourself as a student", "Se présenter comme étudiant", "التعرّف بنفسك كطالب"),
    L("Say who you are, what you study and what language you speak.",
      "Dire qui tu es, ce que tu étudies et quelle langue tu parles.",
      "تقول من أنت وماذا تدرس وأي لغة تتحدث."),
    ["clinical_en"], [3860, 3858], 10,
    [
        seg("Hello, my name is Amine.", "Bonjour, je m'appelle Amine.", "مرحبًا، اسمي أمين."),
        seg("I am a medical student.", "Je suis étudiant en médecine.", "أنا طالب طب."),
        seg("I am in my first year.", "Je suis en première année.", "أنا في السنة الأولى."),
        seg("I speak a little English.", "Je parle un peu anglais.", "أتحدث قليلًا من الإنجليزية."),
        seg("The doctor says, “Welcome to the clinic.”", "Le médecin dit : « Bienvenue à la clinique. »", "يقول الطبيب: «مرحبًا بك في العيادة.»"),
        seg("Amine answers, “Thank you, doctor.”", "Amine répond : « Merci, docteur. »", "يجيب أمين: «شكرًا يا دكتور.»"),
    ],
    [
        {"id": "en:my-name-is:v1", "en": "my name is…", "fr": "je m'appelle…", "ar": "اسمي…"},
        {"id": "en:medical-student:v1", "en": "I am a medical student", "fr": "je suis étudiant en médecine", "ar": "أنا طالب طب"},
        {"id": "en:a-little:v1", "en": "a little English", "fr": "un peu d'anglais", "ar": "قليلًا من الإنجليزية"},
        {"id": "en:welcome-to:v1", "en": "Welcome to the clinic", "fr": "Bienvenue à la clinique", "ar": "مرحبًا بك في العيادة"},
    ],
    [
        q("q1", "What is the student's name?", "Quel est le nom de l'étudiant ?", "ما اسم الطالب؟",
          ["amine", "his name is amine", "my name is amine"], "His name is Amine.",
          phrase_ids=["en:my-name-is:v1"]),
        q("q2", "Is Amine a doctor?", "Amine est-il médecin ?", "هل أمين طبيب؟",
          ["no", "no he is not", "no he isn't", "he is a student"], "No, he is not. He is a medical student.",
          hint=L("Careful: Amine is a STUDENT, not a doctor.", "Attention : Amine est ÉTUDIANT, pas médecin.", "انتبه: أمين طالب، وليس طبيبًا."),
          phrase_ids=["en:medical-student:v1"]),
        q("q3", "Does Amine speak English?", "Amine parle-t-il anglais ?", "هل يتحدث أمين الإنجليزية؟",
          ["yes", "yes he does", "yes a little", "a little"], "Yes, he speaks a little English.",
          phrase_ids=["en:a-little:v1"]),
        q("q4", "What year is he in?", "En quelle année est-il ?", "في أي سنة هو؟",
          ["first year", "the first year", "his first year", "one"], "He is in his first year."),
        q("q5", "Who says “Welcome to the clinic”?", "Qui dit « Bienvenue à la clinique » ?", "من قال «مرحبًا بك في العيادة»؟",
          ["the doctor", "doctor"], "The doctor says it.", phrase_ids=["en:welcome-to:v1"]),
    ],
    L("I am → He is. My name is → His name is.",
      "I am → He is. My name is → His name is.",
      "I am ← He is. My name is ← His name is."),
    [
        {"id": "d1", "prompt": "He … a medical student.", "accepted": ["is"], "model": "He is a medical student."},
        {"id": "d2", "prompt": "… name is Amine.", "accepted": ["his"], "model": "His name is Amine."},
        {"id": "d3", "prompt": "I … in my first year.", "accepted": ["am"], "model": "I am in my first year."},
    ],
    L("Introduce Amine in two short sentences.", "Présente Amine en deux phrases courtes.", "قدّم أمين في جملتين قصيرتين."),
    ["His name is…", "He is…"], "His name is Amine. He is a medical student.",
    L("Salma is a nursing student. She is in her second year. She speaks a little English too.",
      "Salma est étudiante en soins infirmiers. Elle est en deuxième année. Elle parle aussi un peu anglais.",
      "سلمى طالبة تمريض. هي في السنة الثانية. تتحدث هي أيضًا قليلًا من الإنجليزية."),
    L("a nursing student = a student who studies nursing", "nursing student : étudiant(e) en soins infirmiers", "nursing student: طالب/طالبة تمريض"),
    [
        q("t1", "Is Salma a medical student or a nursing student?", "Salma est-elle étudiante en médecine ou en soins infirmiers ?", "هل سلمى طالبة طب أم تمريض؟",
          ["a nursing student", "nursing student", "she is a nursing student"], "She is a nursing student."),
        q("t2", "What year is she in?", "En quelle année est-elle ?", "في أي سنة هي؟",
          ["second year", "the second year", "her second year", "two"], "She is in her second year."),
    ],
    L("Introduce Salma in two sentences.", "Présente Salma en deux phrases.", "قدّم سلمى في جملتين."),
    ["Her name is…", "She is…"], "Her name is Salma. She is a nursing student.",
))

# ============================================================ 003 — Localiser une structure
stories.append(story(
    "locate-structure-003",
    L("Locating a structure: the heart", "Localiser une structure : le cœur", "تحديد موقع بنية: القلب"),
    L("Say where an organ is and what is around it.", "Dire où se trouve un organe et ce qui l'entoure.", "تقول أين يقع العضو وما حوله."),
    ["anat"], [3989, 3691, 3960], 10,
    [
        seg("The heart is an organ.", "Le cœur est un organe.", "القلب عضو."),
        seg("It is in the chest.", "Il est dans la poitrine.", "يقع في الصدر."),
        seg("It is between the two lungs.", "Il est entre les deux poumons.", "يقع بين الرئتين."),
        seg("The heart pumps blood.", "Le cœur pompe le sang.", "القلب يضخ الدم."),
        seg("A student asks, “Where is the heart?”", "Un étudiant demande : « Où est le cœur ? »", "يسأل طالب: «أين يقع القلب؟»"),
        seg("The teacher points to the chest.", "L'enseignant désigne la poitrine.", "يشير الأستاذ إلى الصدر."),
    ],
    [
        {"id": "en:in-the-chest:v1", "en": "in the chest", "fr": "dans la poitrine", "ar": "في الصدر"},
        {"id": "en:between-the-lungs:v1", "en": "between the two lungs", "fr": "entre les deux poumons", "ar": "بين الرئتين"},
        {"id": "en:where-is:v1", "en": "Where is…?", "fr": "Où est… ?", "ar": "أين يقع…؟"},
        {"id": "en:pumps-blood:v1", "en": "pumps blood", "fr": "pompe le sang", "ar": "يضخ الدم"},
    ],
    [
        q("q1", "Where is the heart?", "Où est le cœur ?", "أين يقع القلب؟",
          ["in the chest", "the chest", "it is in the chest"], "It is in the chest.",
          phrase_ids=["en:in-the-chest:v1", "en:where-is:v1"]),
        q("q2", "Is the heart in the abdomen?", "Le cœur est-il dans l'abdomen ?", "هل القلب في البطن؟",
          ["no", "no it is not", "no it isn't", "it is in the chest"], "No, it is not. It is in the chest.",
          hint=L("Careful: the heart is in the CHEST, not in the abdomen.", "Attention : le cœur est dans la POITRINE, pas dans l'abdomen.", "انتبه: القلب في الصدر، وليس في البطن."),
          phrase_ids=["en:in-the-chest:v1"]),
        q("q3", "Is it between the lungs or behind the stomach?", "Est-il entre les poumons ou derrière l'estomac ?", "هل هو بين الرئتين أم خلف المعدة؟",
          ["between the lungs", "between the two lungs", "it is between the lungs"], "It is between the two lungs.",
          phrase_ids=["en:between-the-lungs:v1"]),
        q("q4", "What does the heart pump?", "Que pompe le cœur ?", "ماذا يضخ القلب؟",
          ["blood", "it pumps blood"], "It pumps blood.", phrase_ids=["en:pumps-blood:v1"]),
        q("q5", "Who asks, “Where is the heart?”", "Qui demande : « Où est le cœur ? »", "من يسأل: «أين يقع القلب؟»",
          ["a student", "the student", "student"], "A student asks it."),
    ],
    L("It is in… → They are in… One organ: is. Two organs: are.",
      "It is in… → They are in… Un organe : is. Deux organes : are.",
      "It is in… ← They are in… عضو واحد: is. عضوان: are."),
    [
        {"id": "d1", "prompt": "The heart … in the chest.", "accepted": ["is"], "model": "The heart is in the chest."},
        {"id": "d2", "prompt": "The lungs … in the chest.", "accepted": ["are"], "model": "The lungs are in the chest."},
        {"id": "d3", "prompt": "The heart … between the lungs.", "accepted": ["is"], "model": "The heart is between the lungs."},
    ],
    L("Say where the heart is, in two sentences.", "Dis où est le cœur, en deux phrases.", "قل أين يقع القلب في جملتين."),
    ["The heart is…", "It is between…"], "The heart is in the chest. It is between the two lungs.",
    L("The stomach is an organ. It is in the abdomen. The liver is in the abdomen too.",
      "L'estomac est un organe. Il est dans l'abdomen. Le foie est aussi dans l'abdomen.",
      "المعدة عضو. تقع في البطن. الكبد أيضًا في البطن."),
    L("the abdomen = the belly", "abdomen : le ventre", "abdomen: البطن"),
    [
        q("t1", "Where is the stomach?", "Où est l'estomac ?", "أين تقع المعدة؟",
          ["in the abdomen", "the abdomen", "it is in the abdomen"], "It is in the abdomen."),
        q("t2", "Is the liver in the chest or in the abdomen?", "Le foie est-il dans la poitrine ou dans l'abdomen ?", "هل الكبد في الصدر أم في البطن؟",
          ["in the abdomen", "the abdomen", "it is in the abdomen"], "It is in the abdomen.",
          hint=L("Careful: the liver is in the abdomen.", "Attention : le foie est dans l'abdomen.", "انتبه: الكبد في البطن.")),
    ],
    L("Say where the stomach is, in two sentences.", "Dis où est l'estomac, en deux phrases.", "قل أين تقع المعدة في جملتين."),
    ["The stomach is…", "The liver is…"], "The stomach is in the abdomen. The liver is in the abdomen too.",
))

# ============================================================ 004 — Absence d'un symptôme
stories.append(story(
    "symptom-absent-004",
    L("A symptom that is NOT there", "Un symptôme qui n'est PAS là", "عَرَض غير موجود"),
    L("Understand and ask about absent symptoms without changing the meaning.",
      "Comprendre et interroger les symptômes absents sans inverser le sens.",
      "فهم السؤال عن الأعراض الغائبة دون قلب المعنى."),
    ["clinical_en", "semio"], [3886, 3885, 3895], 10,
    [
        seg("Amina is a patient.", "Amina est une patiente.", "أمينة مريضة."),
        seg("She has a cough.", "Elle a une toux.", "لديها سعال."),
        seg("She does not have a fever.", "Elle n'a pas de fièvre.", "ليست لديها حمى."),
        seg("She does not have chest pain.", "Elle n'a pas de douleur thoracique.", "ليست لديها آلام في الصدر."),
        seg("The student asks, “Do you have chest pain?”", "L'étudiante demande : « Avez-vous une douleur thoracique ? »", "تسأل الطالبة: «هل عندك ألم في الصدر؟»"),
        seg("Amina says, “No, I do not.”", "Amina répond : « Non. »", "تجيب أمينة: «لا.»"),
    ],
    [
        {"id": "en:has-a-cough:v1", "en": "has a cough", "fr": "a une toux", "ar": "لديها سعال"},
        {"id": "en:no-fever:v1", "en": "does not have a fever", "fr": "n'a pas de fièvre", "ar": "ليست لديها حمى"},
        {"id": "en:no-chest-pain:v1", "en": "does not have chest pain", "fr": "n'a pas de douleur thoracique", "ar": "ليست لديها آلام في الصدر"},
        {"id": "en:do-you-have:v1", "en": "Do you have…?", "fr": "Avez-vous… ?", "ar": "هل عندك…؟"},
    ],
    [
        q("q1", "Does Amina have a cough?", "Amina a-t-elle une toux ?", "هل عند أمينة سعال؟",
          ["yes", "yes she does", "yes she has a cough"], "Yes, she has a cough.",
          phrase_ids=["en:has-a-cough:v1"]),
        q("q2", "Does she have a fever?", "A-t-elle de la fièvre ?", "هل عندها حمى؟",
          ["no", "no she does not", "no she doesn't", "she does not have a fever", "no she has not", "she doesn't"],
          "No, she does not have a fever.",
          hint=L("Careful: she does NOT have a fever.", "Attention : elle n'a PAS de fièvre.", "انتبه: ليست عندها حمى."),
          phrase_ids=["en:no-fever:v1"]),
        q("q3", "Does she have chest pain?", "A-t-elle une douleur thoracique ?", "هل عندها ألم في الصدر؟",
          ["no", "no she does not", "no she doesn't", "she does not have chest pain", "no she has not", "she doesn't"],
          "No, she does not have chest pain.",
          hint=L("Careful: she does NOT have chest pain.", "Attention : elle n'a PAS de douleur thoracique.", "انتبه: ليست عندها آلام في الصدر."),
          phrase_ids=["en:no-chest-pain:v1"]),
        q("q4", "Does she have a cough or chest pain?", "A-t-elle une toux ou une douleur thoracique ?", "هل عندها سعال أم ألم في الصدر؟",
          ["a cough", "cough", "she has a cough"], "She has a cough."),
        q("q5", "Who asks the question?", "Qui pose la question ?", "من طرح السؤال؟",
          ["the student", "a student", "student"], "The student asks it."),
    ],
    L("I do not have… → She does not have…",
      "I do not have… → She does not have…",
      "I do not have… ← She does not have…"),
    [
        {"id": "d1", "prompt": "She … not have a fever.", "accepted": ["does"], "model": "She does not have a fever."},
        {"id": "d2", "prompt": "They … not have chest pain.", "accepted": ["do"], "model": "They do not have chest pain."},
        {"id": "d3", "prompt": "I … not have a cough.", "accepted": ["do"], "model": "I do not have a cough."},
    ],
    L("Present Amina's situation in two sentences. Keep the negation.", "Présente la situation d'Amina en deux phrases. Garde la négation.", "اذكر حالة أمينة في جملتين مع الحفاظ على النفي."),
    ["She has…", "She does not have…"], "She has a cough. She does not have a fever.",
    L("Omar is a patient. He has back pain. He does not have a fever. He does not have a cough.",
      "Omar est un patient. Il a mal au dos. Il n'a pas de fièvre. Il n'a pas de toux.",
      "عمر مريض. لديه ألم في الظهر. ليست عنده حمى وليس عنده سعال."),
    L("back pain = pain in the back", "back pain : mal au dos", "back pain: ألم في الظهر"),
    [
        q("t1", "Does Omar have back pain or chest pain?", "Omar a-t-il mal au dos ou à la poitrine ?", "هل عند عمر ألم في الظهر أم في الصدر؟",
          ["back pain", "he has back pain"], "He has back pain."),
        q("t2", "Does he have a fever?", "A-t-il de la fièvre ?", "هل عنده حمى؟",
          ["no", "no he does not", "no he doesn't", "he does not have a fever", "no he has not", "he doesn't"],
          "No, he does not have a fever.",
          hint=L("Careful: he does NOT have a fever.", "Attention : il n'a PAS de fièvre.", "انتبه: ليست عنده حمى."),
          phrase_ids=["en:no-fever:v1"]),
    ],
    L("Present Omar's symptoms in two sentences. Keep the negation.", "Présente les symptômes d'Omar en deux phrases. Garde la négation.", "اذكر أعراض عمر في جملتين مع الحفاظ على النفي."),
    ["He has…", "He does not have…"], "He has back pain. He does not have a fever.",
))

# ============================================================ 005 — Quand cela a commencé
stories.append(story(
    "when-started-005",
    L("When did it start?", "Quand est-ce commencé ?", "متى بدأ الألم؟"),
    L("Ask and answer when a symptom started.", "Demander et dire quand un symptôme a commencé.", "السؤال والإجابة عن موعد بدأ العَرَض."),
    ["clinical_en", "semio"], [3992, 3768], 10,
    [
        seg("Karim has a stomach ache.", "Karim a mal à l'estomac.", "عند كريم ألم في المعدة."),
        seg("It started yesterday.", "Cela a commencé hier.", "بدأ الألم أمس."),
        seg("It started after lunch.", "Cela a commencé après le déjeuner.", "بدأ بعد الغداء."),
        seg("Lina asks, “When did it start?”", "Lina demande : « Quand est-ce commencé ? »", "تسأل لينا: «متى بدأ؟»"),
        seg("Karim says, “Yesterday, after lunch.”", "Karim répond : « Hier, après le déjeuner. »", "يجيب كريم: «أمس، بعد الغداء.»"),
    ],
    [
        {"id": "en:stomach-ache:v1", "en": "a stomach ache", "fr": "mal à l'estomac", "ar": "ألم في المعدة"},
        {"id": "en:started-yesterday:v1", "en": "it started yesterday", "fr": "cela a commencé hier", "ar": "بدأ أمس"},
        {"id": "en:when-did-it-start:v1", "en": "When did it start?", "fr": "Quand est-ce commencé ?", "ar": "متى بدأ؟"},
        {"id": "en:after-lunch:v1", "en": "after lunch", "fr": "après le déjeuner", "ar": "بعد الغداء"},
    ],
    [
        q("q1", "What does Karim have?", "Qu'a Karim ?", "ماذا عند كريم؟",
          ["a stomach ache", "stomach ache", "he has a stomach ache"], "He has a stomach ache.",
          phrase_ids=["en:stomach-ache:v1"]),
        q("q2", "When did it start?", "Quand est-ce commencé ?", "متى بدأ؟",
          ["yesterday", "it started yesterday", "yesterday after lunch"], "It started yesterday.",
          phrase_ids=["en:started-yesterday:v1", "en:when-did-it-start:v1"]),
        q("q3", "Did it start this morning or yesterday?", "Cela a-t-il commencé ce matin ou hier ?", "هل بدأ هذا الصباح أم أمس؟",
          ["yesterday", "it started yesterday"], "It started yesterday.",
          hint=L("Careful: it started YESTERDAY, not this morning.", "Attention : cela a commencé HIER, pas ce matin.", "انتبه: بدأ أمس، وليس هذا الصباح."),
          phrase_ids=["en:started-yesterday:v1"]),
        q("q4", "Did it start before or after lunch?", "Cela a-t-il commencé avant ou après le déjeuner ?", "هل بدأ قبل الغداء أم بعده؟",
          ["after lunch", "after", "it started after lunch"], "It started after lunch.",
          phrase_ids=["en:after-lunch:v1"]),
        q("q5", "Who asks, “When did it start?”", "Qui demande : « Quand est-ce commencé ? »", "من يسأل: «متى بدأ؟»",
          ["lina", "the student", "a student"], "Lina asks it."),
    ],
    L("It starts → It started. Yesterday, the pain … (past).",
      "It starts → It started. Hier, la douleur … (passé).",
      "It starts ← It started. بالأمس، الألم… (الماضي)."),
    [
        {"id": "d1", "prompt": "The pain … yesterday. (start)", "accepted": ["started"], "model": "The pain started yesterday."},
        {"id": "d2", "prompt": "The pain … after lunch. (start)", "accepted": ["started"], "model": "The pain started after lunch."},
        {"id": "d3", "prompt": "The pain usually … at night. (start — present)", "accepted": ["starts"], "model": "The pain usually starts at night."},
    ],
    L("Say what Karim has and when it started, in two sentences.", "Dis ce que Karim a et quand cela a commencé, en deux phrases.", "قل ماذا عند كريم ومتى بدأ، في جملتين."),
    ["He has…", "It started…"], "He has a stomach ache. It started yesterday.",
    L("Farida has a sore throat. It started two days ago. It started in the morning.",
      "Farida a mal à la gorge. Cela a commencé il y a deux jours, le matin.",
      "عند فريدة ألم في الحلق. بدأ قبل يومين، في الصباح."),
    L("two days ago = two days before today", "two days ago : il y a deux jours", "two days ago: قبل يومين"),
    [
        q("t1", "What does Farida have?", "Qu'a Farida ?", "ماذا عند فريدة؟",
          ["a sore throat", "sore throat", "she has a sore throat"], "She has a sore throat."),
        q("t2", "When did it start?", "Quand est-ce commencé ?", "متى بدأ؟",
          ["two days ago", "it started two days ago", "two days ago in the morning"], "It started two days ago."),
    ],
    L("Say what Farida has and when it started, in two sentences.", "Dis ce que Farida a et quand cela a commencé, en deux phrases.", "قل ماذا عند فريدة ومتى بدأ، في جملتين."),
    ["She has…", "It started…"], "She has a sore throat. It started two days ago.",
))

# ============================================================ 006 — Reformuler et confirmer
stories.append(story(
    "reformulate-confirm-006",
    L("Checking you understood", "Vérifier qu'on a compris", "التأكد من الفهم"),
    L("Repeat the facts and confirm them with the patient.",
      "Répéter les faits et les confirmer avec le patient.",
      "إعادة ذكر الحقائق والتأكد منها مع المريض."),
    ["clinical_en"], [3860, 3896], 10,
    [
        seg("The patient has a headache.", "Le patient a mal à la tête.", "المريض عنده صداع."),
        seg("It started two days ago.", "Cela a commencé il y a deux jours.", "بدأ قبل يومين."),
        seg("Lina repeats: “You have a headache.”", "Lina répète : « Vous avez mal à la tête. »", "تعيد لينا: «عندك صداع.»"),
        seg("“It started two days ago.”", "« Cela a commencé il y a deux jours. »", "«بدأ قبل يومين.»"),
        seg("She asks, “Is that right?”", "Elle demande : « C'est bien ça ? »", "تسأل: «هل هذا صحيح؟»"),
        seg("The patient says, “Yes, that is right.”", "Le patient dit : « Oui, c'est bien ça. »", "يقول المريض: «نعم، هذا صحيح.»"),
    ],
    [
        {"id": "en:have-headache:v1", "en": "has a headache", "fr": "a mal à la tête", "ar": "عنده صداع"},
        {"id": "en:is-that-right:v1", "en": "Is that right?", "fr": "C'est bien ça ?", "ar": "هل هذا صحيح؟"},
        {"id": "en:that-is-right:v1", "en": "Yes, that is right", "fr": "Oui, c'est bien ça", "ar": "نعم، هذا صحيح"},
        {"id": "en:you-have:v1", "en": "You have…", "fr": "Vous avez…", "ar": "عندك…"},
    ],
    [
        q("q1", "What does the patient have?", "Qu'a le patient ?", "ماذا عند المريض؟",
          ["a headache", "headache", "he has a headache"], "He has a headache.",
          phrase_ids=["en:have-headache:v1"]),
        q("q2", "When did it start?", "Quand est-ce commencé ?", "متى بدأ؟",
          ["two days ago", "it started two days ago"], "It started two days ago."),
        q("q3", "Does Lina invent new information?", "Lina invente-t-elle des informations ?", "هل تختلق لينا معلومات جديدة؟",
          ["no", "no she does not", "no she doesn't"], "No, she does not. She repeats the facts.",
          hint=L("Careful: Lina repeats the SAME facts, she does not invent.", "Attention : Lina répète les MÊMES faits, elle n'invente rien.", "انتبه: تعيد لينا الحقائق نفسها ولا تختلق شيئًا.")),
        q("q4", "What does Lina ask at the end?", "Que demande Lina à la fin ?", "ماذا تسأل لينا في النهاية؟",
          ["is that right", "\"is that right?\"", "she asks if that is right"], "She asks, “Is that right?”",
          phrase_ids=["en:is-that-right:v1"]),
        q("q5", "Does the patient confirm?", "Le patient confirme-t-il ?", "هل يؤكد المريض؟",
          ["yes", "yes he does", "yes that is right"], "Yes, he says, “That is right.”",
          phrase_ids=["en:that-is-right:v1"]),
    ],
    L("I have → You have. He has → She has.",
      "I have → You have. He has → She has.",
      "I have ← You have. He has ← She has."),
    [
        {"id": "d1", "prompt": "… have a headache. (to the patient)", "accepted": ["you"], "model": "You have a headache."},
        {"id": "d2", "prompt": "The patient … a headache.", "accepted": ["has"], "model": "The patient has a headache."},
        {"id": "d3", "prompt": "… have a headache. (about yourself)", "accepted": ["i"], "model": "I have a headache."},
    ],
    L("Confirm the two facts to the patient, then ask if it is right.", "Confirme les deux faits au patient, puis demande si c'est bien ça.", "أكّد الحقيقتين للمريض ثم اسأل إن كان ذلك صحيحًا."),
    ["You have…", "It started…", "Is that right?"], "You have a headache. It started two days ago. Is that right?",
    L("The patient has back pain. It started on Monday. Lina repeats and confirms.",
      "Le patient a mal au dos. Cela a commencé lundi. Lina répète et confirme.",
      "المريض عنده ألم في الظهر. بدأ يوم الاثنين. تعيد لينا وتتأكد."),
    L("on Monday = the day called Monday", "on Monday : le lundi", "on Monday: يوم الاثنين"),
    [
        q("t1", "What does the patient have?", "Qu'a le patient ?", "ماذا عند المريض؟",
          ["back pain", "he has back pain"], "He has back pain."),
        q("t2", "When did it start?", "Quand est-ce commencé ?", "متى بدأ؟",
          ["on monday", "monday", "it started on monday"], "It started on Monday."),
    ],
    L("Confirm the two facts, then ask if it is right.", "Confirme les deux faits, puis demande si c'est bien ça.", "أكّد الحقيقتين ثم اسأل إن كان ذلك صحيحًا."),
    ["You have…", "It started…", "Is that right?"], "You have back pain. It started on Monday. Is that right?",
))

# ============================================================ 007 — Fonction d'un organe
stories.append(story(
    "organ-function-007",
    L("What does the heart do?", "Que fait le cœur ?", "ماذا يفعل القلب؟"),
    L("Describe what an organ does, simply.", "Décrire simplement ce que fait un organe.", "وصف وظيفة عضو ببساطة."),
    ["physio"], [3989, 1243], 10,
    [
        seg("The heart is a muscle.", "Le cœur est un muscle.", "القلب عضلة."),
        seg("It pumps blood.", "Il pompe le sang.", "يضخ الدم."),
        seg("The blood carries oxygen.", "Le sang transporte l'oxygène.", "الدم يحمل الأكسجين."),
        seg("The oxygen goes to the organs.", "L'oxygène va vers les organes.", "يذهب الأكسجين إلى الأعضاء."),
        seg("A student asks, “What does the heart do?”", "Un étudiant demande : « Que fait le cœur ? »", "يسأل طالب: «ماذا يفعل القلب؟»"),
        seg("The teacher answers, “It pumps blood.”", "L'enseignant répond : « Il pompe le sang. »", "يجيب الأستاذ: «يضخ الدم.»"),
    ],
    [
        {"id": "en:pumps-blood:v1", "en": "pumps blood", "fr": "pompe le sang", "ar": "يضخ الدم"},
        {"id": "en:carries-oxygen:v1", "en": "carries oxygen", "fr": "transporte l'oxygène", "ar": "يحمل الأكسجين"},
        {"id": "en:goes-to:v1", "en": "goes to the organs", "fr": "va vers les organes", "ar": "يذهب إلى الأعضاء"},
        {"id": "en:what-does-do:v1", "en": "What does … do?", "fr": "Que fait … ?", "ar": "ماذا يفعل…؟"},
    ],
    [
        q("q1", "What does the heart do?", "Que fait le cœur ?", "ماذا يفعل القلب؟",
          ["it pumps blood", "pumps blood", "pump blood"], "It pumps blood.",
          phrase_ids=["en:pumps-blood:v1", "en:what-does-do:v1"]),
        q("q2", "Is the heart a bone?", "Le cœur est-il un os ?", "هل القلب عظمة؟",
          ["no", "no it is not", "no it isn't", "it is a muscle"], "No, it is not. It is a muscle.",
          hint=L("Careful: the heart is a MUSCLE.", "Attention : le cœur est une MUSCLE.", "انتبه: القلب عضلة.")),
        q("q3", "What does the blood carry?", "Que transporte le sang ?", "ماذا يحمل الدم؟",
          ["oxygen", "it carries oxygen"], "It carries oxygen.",
          phrase_ids=["en:carries-oxygen:v1"]),
        q("q4", "Does the blood carry oxygen or food?", "Le sang transporte-t-il l'oxygène ou la nourriture ?", "هل يحمل الدم الأكسجين أم الطعام؟",
          ["oxygen", "it carries oxygen"], "It carries oxygen.",
          hint=L("Careful: the blood carries OXYGEN.", "Attention : le sang transporte l'OXYGÈNE.", "انتبه: الدم يحمل الأكسجين.")),
        q("q5", "Where does the oxygen go?", "Où va l'oxygène ?", "إلى أين يذهب الأكسجين؟",
          ["to the organs", "the organs", "it goes to the organs"], "It goes to the organs.",
          phrase_ids=["en:goes-to:v1"]),
    ],
    L("It pumps → They pump. The heart pumpS (one) / the lungs pump? No — carry!",
      "It pumps → They pump. Le cœur pumpS (un) / les poumons… carry !",
      "It pumps ← They pump. عضو واحد يأخذ s، وعضوان لا."),
    [
        {"id": "d1", "prompt": "The heart … blood. (pump)", "accepted": ["pumps"], "model": "The heart pumps blood."},
        {"id": "d2", "prompt": "The blood … oxygen. (carry)", "accepted": ["carries"], "model": "The blood carries oxygen."},
        {"id": "d3", "prompt": "The organs … the blood. (receive — present)", "accepted": ["receive"], "model": "The organs receive the blood."},
    ],
    L("Explain what the heart does, in two sentences.", "Explique ce que fait le cœur, en deux phrases.", "اشرح ماذا يفعل القلب في جملتين."),
    ["The heart is…", "It pumps…"], "The heart is a muscle. It pumps blood.",
    L("The kidneys are organs. They filter the blood. They clean the blood.",
      "Les reins sont des organes. Ils filtrent le sang. Ils nettoient le sang.",
      "الكليتان عضوان. تصفّيان الدم وتنظّفانه."),
    L("to filter = to remove waste", "filter : éliminer les déchets", "to filter: يصفّي"),
    [
        q("t1", "What do the kidneys do?", "Que font les reins ?", "ماذا تفعل الكليتان؟",
          ["they filter the blood", "filter the blood", "they filter blood"], "They filter the blood."),
        q("t2", "Are the kidneys organs?", "Les reins sont-ils des organes ?", "هل الكليتان عضوان؟",
          ["yes", "yes they are"], "Yes, they are organs."),
    ],
    L("Explain what the kidneys do, in two sentences.", "Explique ce que font les reins, en deux phrases.", "اشرح ماذا تفعل الكليتان في جملتين."),
    ["The kidneys are…", "They filter…"], "The kidneys are organs. They filter the blood.",
))

# ============================================================ 008 — Décrire un tissu
stories.append(story(
    "tissue-simple-008",
    L("Describing a tissue simply", "Décrire un tissu simplement", "وصف نسيج ببساطة"),
    L("Describe a tissue: what it does and how it looks.",
      "Décrire un tissu : ce qu'il fait et son aspect.",
      "وصف نسيج: ما يفعله وشكله."),
    ["histo"], [1117], 10,
    [
        seg("Epithelial tissue covers the body.", "Le tissu épithélial couvre le corps.", "النسيج الظهاري يغطي الجسم."),
        seg("It forms a protective layer.", "Il forme une couche protectrice.", "يشكّل طبقة واقية."),
        seg("The cells are close together.", "Les cellules sont serrées.", "الخلايا متقاربة."),
        seg("There is little space between the cells.", "Il y a peu d'espace entre les cellules.", "هناك مساحة صغيرة بين الخلايا."),
        seg("A student asks, “What does epithelial tissue do?”", "Un étudiant demande : « Que fait le tissu épithélial ? »", "يسأل طالب: «ماذا يفعل النسيج الظهاري؟»"),
    ],
    [
        {"id": "en:covers-the-body:v1", "en": "covers the body", "fr": "couvre le corps", "ar": "يغطي الجسم"},
        {"id": "en:protective-layer:v1", "en": "a protective layer", "fr": "une couche protectrice", "ar": "طبقة واقية"},
        {"id": "en:close-together:v1", "en": "the cells are close together", "fr": "les cellules sont serrées", "ar": "الخلايا متقاربة"},
    ],
    [
        q("q1", "What does epithelial tissue cover?", "Que couvre le tissu épithélial ?", "ماذا يغطي النسيج الظهاري؟",
          ["the body", "body", "it covers the body"], "It covers the body.",
          phrase_ids=["en:covers-the-body:v1"]),
        q("q2", "Does it form a protective layer?", "Forme-t-il une couche protectrice ?", "هل يشكّل طبقة واقية؟",
          ["yes", "yes it does", "it forms a protective layer"], "Yes, it forms a protective layer.",
          phrase_ids=["en:protective-layer:v1"]),
        q("q3", "Are the cells close together or far apart?", "Les cellules sont-elles serrées ou espacées ?", "هل الخلايا متقاربة أم متباعدة؟",
          ["close together", "they are close together"], "They are close together.",
          phrase_ids=["en:close-together:v1"]),
        q("q4", "Is there a lot of space between the cells?", "Y a-t-il beaucoup d'espace entre les cellules ?", "هل هناك مساحة كبيرة بين الخلايا؟",
          ["no", "no there is not", "no there isn't", "there is little space"], "No, there is little space.",
          hint=L("Careful: there is LITTLE space between the cells.", "Attention : il y a PEU d'espace entre les cellules.", "انتبه: هناك مساحة صغيرة بين الخلايا.")),
    ],
    L("It covers → They cover. One tissue: covers. Many cells: cover.",
      "It covers → They cover. Un tissu : covers. Des cellules : cover.",
      "It covers ← They cover. نسيج واحد: covers. خلايا كثيرة: cover."),
    [
        {"id": "d1", "prompt": "Epithelial tissue … the body. (cover)", "accepted": ["covers"], "model": "Epithelial tissue covers the body."},
        {"id": "d2", "prompt": "The cells … a layer. (form)", "accepted": ["form"], "model": "The cells form a layer."},
        {"id": "d3", "prompt": "The cells … close together. (be)", "accepted": ["are"], "model": "The cells are close together."},
    ],
    L("Describe epithelial tissue in two sentences.", "Décris le tissu épithélial en deux phrases.", "صِف النسيج الظهاري في جملتين."),
    ["It covers…", "The cells are…"], "Epithelial tissue covers the body. The cells are close together.",
    L("Muscle tissue moves the body. The muscle cells are long. They contract.",
      "Le tissu musculaire fait bouger le corps. Les cellules musculaires sont longues. Elles se contractent.",
      "النسيج العضلي يحرّك الجسم. خلايا العضلات طويلة وتنقبض."),
    L("to contract = to become shorter", "contract : se raccourcir", "to contract: ينقبض"),
    [
        q("t1", "What does muscle tissue do?", "Que fait le tissu musculaire ?", "ماذا يفعل النسيج العضلي؟",
          ["it moves the body", "moves the body", "move the body"], "It moves the body."),
        q("t2", "Are the muscle cells short or long?", "Les cellules musculaires sont-elles courtes ou longues ?", "هل خلايا العضلات قصيرة أم طويلة؟",
          ["long", "they are long"], "They are long.",
          hint=L("Careful: the muscle cells are LONG.", "Attention : les cellules musculaires sont LONGUES.", "انتبه: خلايا العضلات طويلة.")),
    ],
    L("Describe muscle tissue in two sentences.", "Décris le tissu musculaire en deux phrases.", "صِف النسيج العضلي في جملتين."),
    ["Muscle tissue…", "The cells are…"], "Muscle tissue moves the body. The cells are long.",
))

# ============================================================ 009 — Cause et conséquence
stories.append(story(
    "cause-consequence-009",
    L("If the artery is blocked", "Si l'artère est bouchée", "إذا انسدّ الشريان"),
    L("Link a cause and its consequence with “if”.", "Relier une cause et sa conséquence avec « if ».", "ربط السبب بنتيجته باستخدام if."),
    ["physio"], [3673, 1243, 3727], 10,
    [
        seg("An artery carries blood.", "Une artère transporte le sang.", "الشريان يحمل الدم."),
        seg("If the artery is blocked, the blood stops.", "Si l'artère est bouchée, le sang s'arrête.", "إذا انسدّ الشريان يتوقف الدم."),
        seg("The muscle does not receive oxygen.", "Le muscle ne reçoit pas d'oxygène.", "العضلة لا تستقبل الأكسجين."),
        seg("The muscle hurts.", "La muscle fait mal.", "العضلة تتألم."),
        seg("The teacher explains the cause and the consequence.", "L'enseignant explique la cause et la conséquence.", "يشرح الأستاذ السبب والنتيجة."),
    ],
    [
        {"id": "en:if-blocked:v1", "en": "If the artery is blocked", "fr": "Si l'artère est bouchée", "ar": "إذا انسدّ الشريان"},
        {"id": "en:blood-stops:v1", "en": "the blood stops", "fr": "le sang s'arrête", "ar": "يتوقف الدم"},
        {"id": "en:not-receive:v1", "en": "does not receive oxygen", "fr": "ne reçoit pas d'oxygène", "ar": "لا يستقبل الأكسجين"},
        {"id": "en:cause-consequence:v1", "en": "the cause and the consequence", "fr": "la cause et la conséquence", "ar": "السبب والنتيجة"},
    ],
    [
        q("q1", "What does an artery carry?", "Que transporte une artère ?", "ماذا يحمل الشريان؟",
          ["blood", "it carries blood"], "It carries blood."),
        q("q2", "What happens if the artery is blocked?", "Que se passe-t-il si l'artère est bouchée ?", "ماذا يحدث إذا انسدّ الشريان؟",
          ["the blood stops", "blood stops", "it stops"], "The blood stops.",
          phrase_ids=["en:if-blocked:v1", "en:blood-stops:v1"]),
        q("q3", "Does the muscle receive oxygen?", "Le muscle reçoit-il de l'oxygène ?", "هل تستقبل العضلة الأكسجين؟",
          ["no", "no it does not", "no it doesn't", "it does not receive oxygen", "it doesn't"],
          "No, it does not receive oxygen.",
          hint=L("Careful: the muscle does NOT receive oxygen.", "Attention : le muscle ne reçoit PAS d'oxygène.", "انتبه: العضلة لا تستقبل الأكسجين."),
          phrase_ids=["en:not-receive:v1"]),
        q("q4", "Why does the muscle hurt?", "Pourquoi le muscle fait-il mal ?", "لماذا تتألم العضلة؟",
          ["it does not receive oxygen", "because it does not receive oxygen", "no oxygen", "because there is no oxygen"],
          "Because it does not receive oxygen.",
          hint=L("Careful: the muscle hurts because it does NOT receive oxygen.", "Attention : le muscle fait mal parce qu'il ne reçoit PAS d'oxygène.", "انتبه: تتألم العضلة لأنها لا تستقبل الأكسجين."),
          phrase_ids=["en:not-receive:v1"]),
        q("q5", "What does the teacher explain?", "Qu'explique l'enseignant ?", "ماذا يشرح الأستاذ؟",
          ["the cause and the consequence", "the cause", "cause and consequence"], "The teacher explains the cause and the consequence.",
          phrase_ids=["en:cause-consequence:v1"]),
    ],
    L("If the artery IS blocked, the blood STOPS. Present after “if”, present for the result.",
      "If the artery IS blocked, the blood STOPS. Présent après « if », présent pour le résultat.",
      "بعد if نستخدم المضارع، والنتيجة أيضًا مضارع."),
    [
        {"id": "d1", "prompt": "If the artery … blocked, the blood stops. (be)", "accepted": ["is"], "model": "If the artery is blocked, the blood stops."},
        {"id": "d2", "prompt": "If the arteries … blocked, the blood stops. (be)", "accepted": ["are"], "model": "If the arteries are blocked, the blood stops."},
        {"id": "d3", "prompt": "If the artery is blocked, the muscle … not receive oxygen. (do)", "accepted": ["does"], "model": "If the artery is blocked, the muscle does not receive oxygen."},
    ],
    L("Link the cause and the consequence in two sentences.", "Relie la cause et la conséquence en deux phrases.", "اربط السبب بالنتيجة في جملتين."),
    ["If the artery is blocked,", "the blood…"], "If the artery is blocked, the blood stops. The muscle does not receive oxygen.",
    L("A nerve carries messages. If the nerve is cut, the message stops. The muscle does not move.",
      "Un nerf transporte des messages. Si le nerf est coupé, le message s'arrête. Le muscle ne bouge pas.",
      "العصب يحمل الرسائل. إذا قُطع العصب تتوقف الرسالة. العضلة لا تتحرك."),
    L("a nerve = a fibre that carries messages", "nerf : fibre qui transporte des messages", "a nerve: عصب ينقل الرسائل"),
    [
        q("t1", "What happens if the nerve is cut?", "Que se passe-t-il si le nerf est coupé ?", "ماذا يحدث إذا قُطع العصب؟",
          ["the message stops", "the message stops and the muscle does not move", "it stops"], "The message stops."),
        q("t2", "Does the muscle move?", "Le muscle bouge-t-il ?", "هل تتحرك العضلة؟",
          ["no", "no it does not", "no it doesn't", "it does not move"], "No, it does not move.",
          hint=L("Careful: the muscle does NOT move.", "Attention : le muscle ne bouge PAS.", "انتبه: العضلة لا تتحرك.")),
    ],
    L("Link the cause and the consequence, in two sentences.", "Relie la cause et la conséquence, en deux phrases.", "اربط السبب بالنتيجة في جملتين."),
    ["If the nerve is cut,", "the message…"], "If the nerve is cut, the message stops. The muscle does not move.",
))

# ============================================================ 010 — Résumer une situation
stories.append(story(
    "summarize-situation-010",
    L("Summarizing a new situation", "Résumer une situation nouvelle", "تلخيص حالة جديدة"),
    L("Listen to a new situation and report the facts correctly.",
      "Écouter une nouvelle situation et rapporter les faits correctement.",
      "الاستماع لحالة جديدة ونقل الحقائق بدقة."),
    ["clinical_en"], [3910, 3886, 3885], 10,
    [
        seg("Nadia is a patient.", "Nadia est une patiente.", "ناديا مريضة."),
        seg("She has a sore throat and a cough.", "Elle a mal à la gorge et une toux.", "عندها ألم في الحلق وسعال."),
        seg("She does not have a fever.", "Elle n'a pas de fièvre.", "ليست عندها حمى."),
        seg("The symptoms started three days ago.", "Les symptômes ont commencé il y a trois jours.", "بدأت الأعراض قبل ثلاثة أيام."),
        seg("The student listens and takes notes.", "L'étudiante écoute et prend des notes.", "تستمع الطالبة وتدوّن الملاحظات."),
    ],
    [
        {"id": "en:sore-throat:v1", "en": "a sore throat", "fr": "un mal de gorge", "ar": "ألم في الحلق"},
        {"id": "en:has-a-cough:v1", "en": "has a cough", "fr": "a une toux", "ar": "لديها سعال"},
        {"id": "en:no-fever:v1", "en": "does not have a fever", "fr": "n'a pas de fièvre", "ar": "ليست عندها حمى"},
        {"id": "en:three-days-ago:v1", "en": "three days ago", "fr": "il y a trois jours", "ar": "قبل ثلاثة أيام"},
    ],
    [
        q("q1", "What symptoms does Nadia have?", "Quels symptômes Nadia a-t-elle ?", "ما أعراض ناديا؟",
          ["a sore throat and a cough", "a sore throat", "sore throat and cough", "a cough"],
          "She has a sore throat and a cough.",
          phrase_ids=["en:sore-throat:v1", "en:has-a-cough:v1"]),
        q("q2", "Does she have a fever?", "A-t-elle de la fièvre ?", "هل عندها حمى؟",
          ["no", "no she does not", "no she doesn't", "she does not have a fever", "no she has not", "she doesn't"],
          "No, she does not have a fever.",
          hint=L("Careful: she does NOT have a fever.", "Attention : elle n'a PAS de fièvre.", "انتبه: ليست عندها حمى."),
          phrase_ids=["en:no-fever:v1"]),
        q("q3", "When did the symptoms start?", "Quand les symptômes ont-ils commencé ?", "متى بدأت الأعراض؟",
          ["three days ago", "they started three days ago"], "They started three days ago.",
          phrase_ids=["en:three-days-ago:v1"]),
        q("q4", "Does the student invent information?", "L'étudiante invente-t-elle des informations ?", "هل تختلق الطالبة معلومات؟",
          ["no", "no she does not", "no she doesn't"], "No, she does not. She takes notes.",
          hint=L("Careful: the student does NOT invent. She listens and takes notes.", "Attention : l'étudiante n'invente RIEN. Elle écoute et prend des notes.", "انتبه: الطالبة لا تختلق شيئًا. تستمع وتدوّن.")),
        q("q5", "Does Nadia have a headache?", "Nadia a-t-elle mal à la tête ?", "هل عند ناديا صداع؟",
          ["we do not know", "i don't know", "unknown", "the story does not say"],
          "We do not know. The story does not say.",
          hint=L("Careful: do not invent facts. The story does not say.", "Attention : n'invente pas de faits. L'histoire ne le dit pas.", "انتبه: لا تخترع حقائق. القصة لا تذكر ذلك.")),
    ],
    L("She has → They have. The symptom … (one) / the symptoms … (many).",
      "She has → They have. Le symptôme … (un) / les symptômes … (plusieurs).",
      "She has ← They have. عَرَض واحد يأخذ s، وأعراض كثيرة لا."),
    [
        {"id": "d1", "prompt": "She … a sore throat. (have)", "accepted": ["has"], "model": "She has a sore throat."},
        {"id": "d2", "prompt": "The symptoms … three days ago. (start)", "accepted": ["started"], "model": "The symptoms started three days ago."},
        {"id": "d3", "prompt": "The patient … not have a fever. (do)", "accepted": ["does"], "model": "The patient does not have a fever."},
    ],
    L("Summarize Nadia's situation in three sentences. Do not invent facts.", "Résume la situation de Nadia en trois phrases. N'invente pas de faits.", "لخّص حالة ناديا في ثلاث جمل دون اختلاق حقائق."),
    ["She has…", "She does not have…", "The symptoms started…"],
    "Nadia has a sore throat and a cough. She does not have a fever. The symptoms started three days ago.",
    L("Yacine is a patient. He has back pain. The pain started two days ago. He does not have a fever.",
      "Yacine est un patient. Il a mal au dos. La douleur a commencé il y a deux jours. Il n'a pas de fièvre.",
      "ياسين مريض. عنده ألم في الظهر. بدأ الألم قبل يومين. ليست عنده حمى."),
    None,
    [
        q("t1", "What does Yacine have?", "Qu'a Yacine ?", "ماذا عند ياسين؟",
          ["back pain", "he has back pain"], "He has back pain."),
        q("t2", "When did the pain start?", "Quand la douleur a-t-elle commencé ?", "متى بدأ الألم؟",
          ["two days ago", "it started two days ago"], "It started two days ago."),
        q("t3", "Does he have a fever?", "A-t-il de la fièvre ?", "هل عنده حمى؟",
          ["no", "no he does not", "no he doesn't", "he does not have a fever", "no he has not", "he doesn't"],
          "No, he does not have a fever.",
          hint=L("Careful: he does NOT have a fever.", "Attention : il n'a PAS de fièvre.", "انتبه: ليست عنده حمى."),
          phrase_ids=["en:no-fever:v1"]),
    ],
    L("Summarize Yacine's situation in three sentences.", "Résume la situation de Yacine en trois phrases.", "لخّص حالة ياسين في ثلاث جمل."),
    ["He has…", "The pain started…", "He does not have…"],
    "Yacine has back pain. The pain started two days ago. He does not have a fever.",
))

# ============================================================ validation + écriture
ids = [s["storyId"] for s in stories]
assert len(ids) == len(set(ids)), "ids dupliqués"
for s in stories:
    phrase_ids = {p["id"] for p in s["targetPhrases"]}
    for qn in s["questions"] + s["transfer"]["questions"]:
        assert qn["accepted"], f"{s['storyId']}/{qn['id']}: accepted vide"
        norm_set = {a.lower().replace(".", "").replace(",", "").replace("!", "").replace("?", "").replace("  ", " ").strip() for a in qn["accepted"]}
        assert len(norm_set) == len(set(qn["accepted"])), f"{s['storyId']}/{qn['id']}: doublon après normalisation"
        for pid in qn.get("phraseIds", []):
            assert pid in phrase_ids, f"{s['storyId']}/{qn['id']}: phraseId inconnu {pid}"
    assert s["segments"] and s["targetPhrases"] and s["questions"] and s["drill"]["items"]
    assert s["retell"]["starters"] and s["transfer"]["task"]["starters"]

# Les ids d'expression partagés (3.3) : vérifier les recouvrements voulus.
shared = {}
for s in stories:
    for p in s["targetPhrases"]:
        shared.setdefault(p["id"], []).append(s["storyId"])
reused = {k: v for k, v in shared.items() if len(v) > 1}
print(f"{len(stories)} histoires ; {len(shared)} expressions ; réutilisées : {len(reused)}")
for k, v in sorted(reused.items()):
    print(f"  {k}: {', '.join(v)}")

out = {"schemaVersion": 1, "stories": stories}
for target in (WEB, ANDROID):
    target.write_text(json.dumps(out, ensure_ascii=False, indent=2) + "\n", encoding="utf-8", newline="\n")
print("écrit:", WEB)
print("écrit:", ANDROID)
