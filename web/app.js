/* ============================================================
   MedLingo DZ — Web PWA
   Vanilla JS SPA : accueil, modules, flashcards SM-2, quiz, profil
   Données extraites des seeds Kotlin (app/src/main/.../data/initial)
   ============================================================ */
"use strict";

/* ---------- Modules (miroir de InitialData.modulesList) ---------- */
const MODULES = [
  { id: "anat", icon: "🦴", fr: "Anatomie", en: "Anatomy", ar: "علم التشريح البشري", color: "#1B5E20" },
  { id: "physio", icon: "❤️", fr: "Physiologie", en: "Physiology", ar: "علم وظائف الأعضاء", color: "#00695C" },
  { id: "biochim", icon: "🧬", fr: "Biochimie", en: "Biochemistry", ar: "الكيمياء الحيوية الطبية", color: "#1565C0" },
  { id: "histo", icon: "🔬", fr: "Histologie", en: "Histology", ar: "علم الأنسجة العام", color: "#6A1B9A" },
  { id: "biophys", icon: "🧪", fr: "Biophysique", en: "Biophysics", ar: "الفيزياء الحيوية الطبية", color: "#E65100" },
  { id: "genet", icon: "🧬", fr: "Génétique", en: "Medical Genetics", ar: "علم الوراثة الطبية", color: "#004D40" },
  { id: "termino", icon: "📙", fr: "Terminologie Médicale", en: "Medical Terminology", ar: "المصطلحات الطبية", color: "#E65100" },
  { id: "clinical_en", icon: "🩺", fr: "Anglais Médical", en: "Clinical English", ar: "الإنجليزية الطبية السريرية", color: "#00695C" },
  { id: "cytol", icon: "🧫", fr: "Cytologie", en: "Cytology", ar: "علم الأحياء الخلوية", color: "#00796B" },
  { id: "info_med", icon: "💻", fr: "Informatique Médicale", en: "Medical Informatics", ar: "المعلوماتية الطبية", color: "#1976D2" },
  { id: "embryo", icon: "👶", fr: "Embryologie", en: "Embryology", ar: "علم الأجنة البشرية", color: "#C2185B" },
  { id: "microbio", icon: "🦠", fr: "Microbiologie", en: "Microbiology", ar: "علم الأحياء الدقيقة", color: "#00897B" },
  { id: "pharmaco", icon: "💊", fr: "Pharmacologie", en: "Pharmacology", ar: "علم الأدوية", color: "#7B1FA2" },
  { id: "semio", icon: "🩺", fr: "Sémiologie Médicale", en: "Semiology", ar: "علم الأعراض والتشخيص", color: "#0288D1" },
  { id: "anapath", icon: "🫀", fr: "Anatomie Pathologique", en: "Pathology", ar: "علم الأمراض التشريحي", color: "#C2185B" },
];

/* ---------- i18n ---------- */
const I18N = { fr: {}, en: {}, ar: {} };

Object.assign(I18N.fr, {
  navHome: "Accueil", navCourses: "Cours", navQuiz: "Quiz", navProfile: "Profil",
  greetMorning: "Bonjour", greetAfternoon: "Bon après-midi", greetEvening: "Bonsoir",
  heroTag: "L'anglais médical pour les carabins algériens : 1 640 flashcards SM-2 et 44 exercices, 100 % hors-ligne.",
  ctaContinue: "Reprendre",
  statStreak: "Série", statGems: "Gemmes", statXp: "XP", statAccuracy: "Précision", statHearts: "Cœurs",
  dueTitle: "À réviser aujourd'hui", dueNone: "Toutes les cartes sont à jour 🎉",
  reviewNow: "Réviser", cardsDue: "cartes dues",
  progressLbl: "Progression globale", masteredLbl: "cartes apprises",
  seeAll: "Tout voir", quizCtaTitle: "Entraînez-vous au quiz", quizCtaText: "44 exercices progressifs sur 6 niveaux.",
  modulesTitle: "Modules", searchLink: "Rechercher un terme dans le lexique",
  termsLbl: "termes", chaptersLbl: "chapitres",
  searchTitle: "Lexique", searchPlaceholder: "Rechercher en anglais, français ou arabe…",
  searchEmpty: "Commencez à taper pour explorer les 1 640 termes médicaux.",
  noResults: "Aucun terme trouvé. Essayez une autre orthographe.",
  exitLbl: "Quitter", quitTitle: "Quitter la session ?", quitText: "La progression de cette session sera perdue.",
  stayLbl: "Rester", quitLbl: "Quitter",
  tapFlip: "Toucher pour retourner",
  rate1: "Oublié", rate2: "Difficile", rate3: "Moyen", rate4: "Bon", rate5: "Parfait",
  rateWait: "Retournez la carte pour noter",
  doneTitle: "Session terminée !", doneSub: "Revenez demain pour entretenir la série.",
  successLbl: "Réussite", gemsLbl: "Gemmes gagnées",
  backCourses: "Retour aux cours", backHome: "Accueil",
  emptyDue: "Rien à réviser ici", emptyDueSub: "Toutes les cartes de ce module ont été vues. Revenez plus tard.",
  quizTitle: "Quiz", quizSub: "Choisissez un niveau pour commencer", levelAll: "Tous",
  lvl1: "Vocabulaire", lvl2: "Collocations", lvl3: "Phrases simples", lvl4: "Phrases complexes", lvl5: "Paragraphes", lvl6: "Cas cliniques",
  startQuiz: "Commencer", exCount: "exercices",
  questionLbl: "Question", checkLbl: "Vérifier", nextLbl: "Continuer",
  correctLbl: "Bonne réponse !", wrongLbl: "Pas tout à fait…",
  explainLbl: "Explication", answerLbl: "Réponse",
  matchHint: "Appariez chaque terme avec sa correspondance.",
  orderHint: "Tapez les mots dans le bon ordre.",
  typeHint: "Tapez la réponse puis vérifiez.",
  doneQuiz: "Quiz terminé !", scoreLbl: "Score", accuracyLbl: "Précision",
  replay: "Rejouer", homeLbl: "Accueil",
  outHeartsTitle: "Plus de cœurs", outHeartsText: "Vous avez épuisé vos cœurs. Rechargez-les avec des gemmes ou revenez plus tard.",
  refillLbl: "Recharger — 50 💎", leaveLbl: "Quitter",
  profileTitle: "Profil & statistiques", langLbl: "Langue de l'interface",
  answeredLbl: "Exercices réalisés", resetLbl: "Réinitialiser la progression",
  resetTitle: "Tout effacer ?", resetText: "Cartes, gemmes, cœurs et statistiques seront définitivement supprimés.",
  cancelLbl: "Annuler", resetConfirmLbl: "Effacer",
  aboutTxt: "MedLingo DZ — version web. Données PCM1 embarquées, fonctionne hors-ligne.",
  introTitle: "Bienvenue sur MedLingo DZ 🇩🇿",
  introText: "Apprenez l'anglais médical avec 1 640 flashcards, la répétition espacée SM-2 et 44 exercices progressifs — sans connexion.",
  introCta: "Commencer",
  defLbl: "Définition", exLbl: "Exemple", etymLbl: "Étymologie", pearlLbl: "Perle clinique", mnemoLbl: "Moyen mnémotechnique",
  closeLbl: "Fermer", heartsLbl: "cœurs",
});

Object.assign(I18N.en, {
  navHome: "Home", navCourses: "Courses", navQuiz: "Quiz", navProfile: "Profile",
  greetMorning: "Good morning", greetAfternoon: "Good afternoon", greetEvening: "Good evening",
  heroTag: "Medical English for Algerian medical students: 1,640 SM-2 flashcards and 44 exercises, 100% offline.",
  ctaContinue: "Continue",
  statStreak: "Streak", statGems: "Gems", statXp: "XP", statAccuracy: "Accuracy", statHearts: "Hearts",
  dueTitle: "Due today", dueNone: "All caught up 🎉",
  reviewNow: "Review", cardsDue: "cards due",
  progressLbl: "Overall progress", masteredLbl: "cards learned",
  seeAll: "See all", quizCtaTitle: "Practice with the quiz", quizCtaText: "44 progressive exercises across 6 levels.",
  modulesTitle: "Modules", searchLink: "Search a term in the lexicon",
  termsLbl: "terms", chaptersLbl: "chapters",
  searchTitle: "Lexicon", searchPlaceholder: "Search in English, French or Arabic…",
  searchEmpty: "Start typing to explore 1,640 medical terms.",
  noResults: "No term found. Try another spelling.",
  exitLbl: "Exit", quitTitle: "Leave the session?", quitText: "Progress in this session will be lost.",
  stayLbl: "Stay", quitLbl: "Leave",
  tapFlip: "Tap to flip",
  rate1: "Forgot", rate2: "Hard", rate3: "Medium", rate4: "Good", rate5: "Perfect",
  rateWait: "Flip the card to rate it",
  doneTitle: "Session complete!", doneSub: "Come back tomorrow to keep the streak.",
  successLbl: "Success", gemsLbl: "Gems earned",
  backCourses: "Back to courses", backHome: "Home",
  emptyDue: "Nothing to review here", emptyDueSub: "Every card in this module has been seen. Check back later.",
  quizTitle: "Quiz", quizSub: "Pick a level to start", levelAll: "All",
  lvl1: "Vocabulary", lvl2: "Collocations", lvl3: "Simple sentences", lvl4: "Complex sentences", lvl5: "Paragraphs", lvl6: "Clinical cases",
  startQuiz: "Start", exCount: "exercises",
  questionLbl: "Question", checkLbl: "Check", nextLbl: "Continue",
  correctLbl: "Correct!", wrongLbl: "Not quite…",
  explainLbl: "Explanation", answerLbl: "Answer",
  matchHint: "Match each term with its pair.",
  orderHint: "Tap the words in the correct order.",
  typeHint: "Type your answer, then check.",
  doneQuiz: "Quiz finished!", scoreLbl: "Score", accuracyLbl: "Accuracy",
  replay: "Play again", homeLbl: "Home",
  outHeartsTitle: "Out of hearts", outHeartsText: "You are out of hearts. Refill them with gems or come back later.",
  refillLbl: "Refill — 50 💎", leaveLbl: "Leave",
  profileTitle: "Profile & stats", langLbl: "Interface language",
  answeredLbl: "Exercises done", resetLbl: "Reset progress",
  resetTitle: "Erase everything?", resetText: "Cards, gems, hearts and stats will be permanently deleted.",
  cancelLbl: "Cancel", resetConfirmLbl: "Erase",
  aboutTxt: "MedLingo DZ — web version. PCM1 data embedded, works offline.",
  introTitle: "Welcome to MedLingo DZ 🇩🇿",
  introText: "Learn medical English with 1,640 flashcards, SM-2 spaced repetition and 44 progressive exercises — no connection needed.",
  introCta: "Get started",
  defLbl: "Definition", exLbl: "Example", etymLbl: "Etymology", pearlLbl: "Clinical pearl", mnemoLbl: "Mnemonic",
  closeLbl: "Close", heartsLbl: "hearts",
});

Object.assign(I18N.ar, {
  navHome: "الرئيسية", navCourses: "الدورات", navQuiz: "اختبار", navProfile: "الملف",
  greetMorning: "صباح الخير", greetAfternoon: "مساء الخير", greetEvening: "مساء الخير",
  heroTag: "الإنجليزية الطبية لطلبة الطب الجزائريين: 1640 بطاقة SM-2 و44 تمرينًا، دون اتصال بالإنترنت.",
  ctaContinue: "متابعة",
  statStreak: "سلسلة", statGems: "جواهر", statXp: "خبرة", statAccuracy: "الدقة", statHearts: "قلوب",
  dueTitle: "مجدولة اليوم", dueNone: "كل البطاقات محدَّثة 🎉",
  reviewNow: "مراجعة", cardsDue: "بطاقة مستحقة",
  progressLbl: "التقدم العام", masteredLbl: "بطاقة مكتسبة",
  seeAll: "عرض الكل", quizCtaTitle: "تدرّب على الاختبار", quizCtaText: "44 تمرينًا تدريجيًا على 6 مستويات.",
  modulesTitle: "الوحدات", searchLink: "ابحث عن مصطلح في المعجم",
  termsLbl: "مصطلحًا", chaptersLbl: "فصلًا",
  searchTitle: "المعجم", searchPlaceholder: "ابحث بالإنجليزية أو الفرنسية أو العربية…",
  searchEmpty: "ابدأ الكتابة لاستكشاف 1640 مصطلحًا طبيًا.",
  noResults: "لم يُعثر على أي مصطلح. جرّب صياغة أخرى.",
  exitLbl: "خروج", quitTitle: "مغادرة الجلسة؟", quitText: "ستفقد تقدّم هذه الجلسة.",
  stayLbl: "البقاء", quitLbl: "مغادرة",
  tapFlip: "اضغط لقلب البطاقة",
  rate1: "نسيت", rate2: "صعبة", rate3: "متوسطة", rate4: "جيدة", rate5: "مثالية",
  rateWait: "اقلب البطاقة للتقييم",
  doneTitle: "انتهت الجلسة!", doneSub: "عد غدًا للحفاظ على السلسلة.",
  successLbl: "النجاح", gemsLbl: "جواهر مكتسبة",
  backCourses: "العودة إلى الدورات", backHome: "الرئيسية",
  emptyDue: "لا شيء للمراجعة هنا", emptyDueSub: "تم الاطلاع على كل بطاقات هذه الوحدة. عد لاحقًا.",
  quizTitle: "اختبار", quizSub: "اختر مستوى للبدء", levelAll: "الكل",
  lvl1: "المفردات", lvl2: "التلازمات", lvl3: "جمل بسيطة", lvl4: "جمل مركبة", lvl5: "فقرات", lvl6: "حالات سريرية",
  startQuiz: "ابدأ", exCount: "تمرينًا",
  questionLbl: "السؤال", checkLbl: "تحقق", nextLbl: "متابعة",
  correctLbl: "إجابة صحيحة!", wrongLbl: "ليست صحيحة…",
  explainLbl: "التوضيح", answerLbl: "الإجابة",
  matchHint: "طابق كل مصطلح مع نظيره.",
  orderHint: "اضغط الكلمات بالترتيب الصحيح.",
  typeHint: "اكتب إجابتك ثم تحقق.",
  doneQuiz: "انتهى الاختبار!", scoreLbl: "النتيجة", accuracyLbl: "الدقة",
  replay: "إعادة", homeLbl: "الرئيسية",
  outHeartsTitle: "نفدت القلوب", outHeartsText: "نفدت قلوبك. أعد ملءها بالجواهر أو عد لاحقًا.",
  refillLbl: "إعادة الملء — 50 💎", leaveLbl: "مغادرة",
  profileTitle: "الملف والإحصائيات", langLbl: "لغة الواجهة",
  answeredLbl: "تمرينًا منجزًا", resetLbl: "إعادة تعيين التقدم",
  resetTitle: "مسح كل شيء؟", resetText: "سيتم حذف البطاقات والجواهر والقلوب والإحصائيات نهائيًا.",
  cancelLbl: "إلغاء", resetConfirmLbl: "مسح",
  aboutTxt: "MedLingo DZ — نسخة الويب. بيانات PCM1 مدمجة وتعمل دون اتصال.",
  introTitle: "مرحبًا بك في MedLingo DZ 🇩🇿",
  introText: "تعلّم الإنجليزية الطبية مع 1640 بطاقة وتكرار متباعد SM-2 و44 تمرينًا تدريجيًا — دون اتصال.",
  introCta: "ابدأ",
  defLbl: "التعريف", exLbl: "مثال", etymLbl: "الأصل اللغوي", pearlLbl: "لمسة سريرية", mnemoLbl: "تذكّر",
  closeLbl: "إغلاق", heartsLbl: "قلوب",
});

/* ---------- State (localStorage) ---------- */
const STORE_KEY = "medlingo_web_v1";
/* Les ids de termes sont désormais ceux des seeds Android, et non un numérotage propre
   au site. Un sm2 enregistré avec l'ancien numérotage attribuerait l'historique de
   révision au mauvais terme ; le jeu de données étant généré, l'ancien mapping est
   irrécupérable. On garde donc les statistiques du joueur et on jette ce qui est indexé
   par id. Incrémenter cette valeur pour purger sm2 au prochain chargement. */
const DATA_VERSION = "android-ids-v1";
const DEFAULTS = {
  lang: "", gems: 0, hearts: 5, heartsTs: Date.now(),
  streak: 0, lastStudy: "", xp: 0, answered: 0, correct: 0,
  flashReviewed: 0, flashSuccess: 0, quizDone: 0,
  sm2: {}, introSeen: false, dataVersion: DATA_VERSION,
};
let state = (() => {
  const base = () => Object.assign({}, DEFAULTS);
  try {
    const raw = JSON.parse(localStorage.getItem(STORE_KEY));
    if (raw && typeof raw === "object") {
      const merged = Object.assign(base(), raw);
      if (raw.dataVersion !== DATA_VERSION) merged.sm2 = {};
      merged.dataVersion = DATA_VERSION;
      return merged;
    }
  } catch (_) { /* corrupted store → defaults */ }
  return base();
})();

function save() { localStorage.setItem(STORE_KEY, JSON.stringify(state)); }

if (!state.lang) {
  const nav = (navigator.language || "fr").toLowerCase();
  state.lang = nav.startsWith("ar") ? "ar" : nav.startsWith("en") ? "en" : "fr";
}
let lang = state.lang;
const t = (k) => I18N[lang][k] ?? I18N.fr[k] ?? k;

function setLang(l) {
  lang = l; state.lang = l; save(); applyDocumentLang(); renderChrome(); route();
}
function applyDocumentLang() {
  document.documentElement.lang = lang;
  document.documentElement.dir = lang === "ar" ? "rtl" : "ltr";
}

/* ---------- SM-2 (port de SpacedRepetitionAlgorithm.kt) ---------- */
function sm2Next(quality, prev) {
  prev = prev || { rep: 0, ef: 2.5, iv: 1, due: Date.now() };
  let ef = prev.ef, rep, iv;
  if (quality < 3) {
    rep = 0; iv = 1;
  } else {
    rep = prev.rep + 1;
    if (rep === 1) iv = 1;
    else if (rep === 2) iv = 3;
    else if (rep === 3) iv = 7;
    else {
      const calc = Math.floor(prev.iv * prev.ef);
      iv = calc <= prev.iv ? prev.iv + 1 : calc;
    }
  }
  const qd = 5 - quality;
  ef = ef + (0.1 - qd * (0.08 + qd * 0.02));
  if (ef < 1.3) ef = 1.3;
  return { rep, ef: +ef.toFixed(4), iv, due: Date.now() + iv * 86400000 };
}
const isDue = (s) => s && s.due <= Date.now();

/* ---------- gamification ---------- */
function todayKey(d) {
  d = d || new Date();
  const p = (n) => String(n).padStart(2, "0");
  return `${d.getFullYear()}-${p(d.getMonth() + 1)}-${p(d.getDate())}`;
}
function touchStreak() {
  const today = todayKey();
  if (state.lastStudy === today) return;
  const yesterday = todayKey(new Date(Date.now() - 86400000));
  state.streak = state.lastStudy === yesterday ? state.streak + 1 : 1;
  state.lastStudy = today;
}
function regenHearts() {
  const per = 30 * 60 * 1000; // 1 cœur toutes les 30 min, max 5
  if (state.hearts >= 5) { state.heartsTs = Date.now(); return; }
  const gained = Math.floor((Date.now() - state.heartsTs) / per);
  if (gained > 0) {
    state.hearts = Math.min(5, state.hearts + gained);
    state.heartsTs += gained * per;
    save();
  }
}
function loseHeart() {
  regenHearts();
  if (state.hearts > 0) state.hearts -= 1;
  if (state.hearts === 0) state.heartsTs = Date.now();
  save();
}

/* ---------- utils ---------- */
const $ = (sel) => document.querySelector(sel);
const $$ = (sel) => Array.from(document.querySelectorAll(sel));
function esc(s) {
  return String(s == null ? "" : s)
    .replace(/&/g, "&amp;").replace(/</g, "&lt;").replace(/>/g, "&gt;")
    .replace(/"/g, "&quot;").replace(/'/g, "&#39;");
}
function shuffle(a) {
  a = a.slice();
  for (let i = a.length - 1; i > 0; i--) {
    const j = Math.floor(Math.random() * (i + 1));
    [a[i], a[j]] = [a[j], a[i]];
  }
  return a;
}
const norm = (s) => String(s).toLowerCase().trim().replace(/\s+/g, " ").replace(/[.,;:!?«»"']/g, "");
function fmtIv(days) {
  if (days < 30) return lang === "fr" ? days + " j" : days + "d";
  const m = Math.round(days / 30);
  return lang === "fr" ? m + " mois" : lang === "ar" ? m + " شهرًا" : m + " mois";
}

/* ---------- TTS (équivalent web de TtsManager.kt) ---------- */
function speak(text, code) {
  if (!("speechSynthesis" in window) || !text) return;
  speechSynthesis.cancel();
  const u = new SpeechSynthesisUtterance(text);
  u.lang = code || "en-US";
  u.rate = 0.95;
  speechSynthesis.speak(u);
}
function speakTerm(term) {
  if (lang === "ar" && term.ar) { speak(term.ar, "ar-SA"); return; }
  speak(term.en, "en-US");
}

/* ---------- données ---------- */
let TERMS = [], EXERCISES = [], MODULE_TERMS = new Map(), TERMS_BY_ID = new Map();
async function loadData() {
  if (TERMS.length) return;
  const [te, ex] = await Promise.all([
    fetch("data/terms.json").then((r) => r.json()),
    fetch("data/exercises.json").then((r) => r.json()),
  ]);
  TERMS = te; EXERCISES = ex;
  TERMS_BY_ID = new Map(te.map((x) => [x.id, x]));
  MODULE_TERMS = new Map(MODULES.map((m) => [m.id, te.filter((x) => x.module === m.fr)]));
}
const modById = (id) => MODULES.find((m) => m.id === id);

/* ---------- toast & dialog ---------- */
let toastTimer = null;
function toast(msg) {
  const el = $("#toast");
  el.textContent = msg;
  el.hidden = false;
  clearTimeout(toastTimer);
  toastTimer = setTimeout(() => { el.hidden = true; }, 2400);
}
function closeDialog() {
  const ov = $("#overlay");
  ov.hidden = true;
  ov.innerHTML = "";
}
/** actions: [{ id, label, cls }] → résout avec l'id cliqué */
function dialog(opts) {
  return new Promise((resolve) => {
    const ov = $("#overlay");
    ov.innerHTML = `
      <div class="dialog" role="dialog" aria-modal="true" aria-label="${esc(opts.title)}">
        <div class="dlg-ico">${opts.icon || "⚕"}</div>
        <h2>${esc(opts.title)}</h2>
        <p>${opts.text || ""}</p>
        <div class="dlg-actions">
          ${opts.actions.map((a, i) => `<button class="btn ${a.cls || ""} block" data-i="${i}">${esc(a.label)}</button>`).join("")}
        </div>
      </div>`;
    ov.hidden = false;
    ov.querySelectorAll("button[data-i]").forEach((b) => {
      b.addEventListener("click", () => {
        const a = opts.actions[+b.dataset.i];
        closeDialog();
        resolve(a.id);
      });
    });
  });
}

/* ---------- chrome (topbar, onglets) ---------- */
function renderChrome() {
  regenHearts();
  $("#topbarStats").innerHTML = `
    <span class="stat-chip streak" title="${esc(t("statStreak"))}">🔥 <b>${state.streak}</b></span>
    <span class="stat-chip gems" title="${esc(t("statGems"))}">💎 <b>${state.gems}</b></span>
    <span class="stat-chip hearts" title="${esc(t("statHearts"))}">❤️ <b>${state.hearts}</b></span>`;
  $$("[data-t]").forEach((el) => { el.textContent = t(el.dataset.t); });
  const tab = currentTab();
  $$("#tabbar a").forEach((a) => a.classList.toggle("active", a.dataset.tab === tab));
}
function currentTab() {
  const name = (location.hash.replace(/^#\/?/, "").split("/")[0] || "home");
  if (name === "search") return "modules";
  if (name === "flash") return "modules";
  return ["home", "modules", "quiz", "profile"].includes(name) ? name : "home";
}

/* ---------- routeur ---------- */
const FLASH_CAP = 20;
const QUIZ_CAP = 10;
let flashSession = null; // état de la session flash en cours
let quizSession = null;  // état de la session quiz en cours

async function route() {
  const hash = location.hash.replace(/^#\/?/, "") || "home";
  const [name, param] = hash.split("/");
  if (name !== "quiz" && quizSession && quizSession.phase === "done") quizSession = null;
  await loadData();
  renderChrome();
  const view = $("#view");
  view.scrollTop = 0;
  window.scrollTo({ top: 0 });
  switch (name) {
    case "modules": viewModules(view); break;
    case "search": viewSearch(view); break;
    case "flash": viewFlash(view, param); break;
    case "quiz": viewQuiz(view, param); break;
    case "profile": viewProfile(view); break;
    default: viewHome(view);
  }
}

/** compteur de cartes dues sur un ensemble de termes */
function dueCount(terms) {
  let n = 0;
  for (const x of terms) if (isDue(state.sm2[x.id])) n++;
  return n;
}
/** module ayant le plus de cartes dues, sinon le moins avancé */
function pickContinueModule() {
  let best = null, bestDue = -1, bestProg = 2;
  for (const m of MODULES) {
    const list = MODULE_TERMS.get(m.id) || [];
    if (!list.length) continue;
    const d = dueCount(list);
    if (d > bestDue) { bestDue = d; best = m; }
    if (d === 0) {
      const seen = list.filter((x) => state.sm2[x.id]).length / list.length;
      if (seen < bestProg) { bestProg = seen; if (bestDue === 0) best = m; }
    }
  }
  return best || MODULES[0];
}
function moduleProgress(m) {
  const list = MODULE_TERMS.get(m.id) || [];
  const seen = list.filter((x) => state.sm2[x.id]).length;
  return { seen, total: list.length, pct: list.length ? Math.round((seen / list.length) * 100) : 0 };
}

/* ---------- vue : Accueil ---------- */
function viewHome(view) {
  const hour = new Date().getHours();
  const greet = hour < 12 ? t("greetMorning") : hour < 18 ? t("greetAfternoon") : t("greetEvening");
  const cont = pickContinueModule();
  const allDue = dueCount(TERMS);
  const learned = Object.keys(state.sm2).length;
  const pct = Math.round((learned / TERMS.length) * 100);
  const acc = state.answered ? Math.round((state.correct / state.answered) * 100) : 0;
  const preview = MODULES.slice(0, 4);

  view.innerHTML = `
    <section class="hero">
      <p class="eyebrow" style="color:#A7D8AD">${greet} — ${esc(cont[lang] || cont.fr)}</p>
      <h1>MedLingo DZ</h1>
      <p>${esc(t("heroTag"))}</p>
      <a class="btn" href="#/flash/${cont.id}">${esc(t("ctaContinue"))} →</a>
    </section>

    <div class="stat-row">
      <div class="stat-tile"><b>${state.streak}</b><span>🔥 ${esc(t("statStreak"))}</span></div>
      <div class="stat-tile"><b>${state.xp}</b><span>⭐ ${esc(t("statXp"))}</span></div>
      <div class="stat-tile"><b>${acc}%</b><span>🎯 ${esc(t("statAccuracy"))}</span></div>
    </div>

    <div class="card due-banner">
      <div>
        <h3>${esc(t("dueTitle"))}</h3>
        <p class="tiny" style="margin:4px 0 0">${allDue
          ? `<b style="color:var(--amber);font-size:1.1rem;font-family:var(--font-display)">${allDue}</b> ${esc(t("cardsDue"))}`
          : esc(t("dueNone"))}</p>
      </div>
      <a class="btn small ${allDue ? "" : "ghost"}" href="#/flash/all">${esc(t("reviewNow"))}</a>
    </div>

    <div class="section-head"><h2>${esc(t("progressLbl"))}</h2><span class="tiny">${pct}%</span></div>
    <div class="card">
      <div class="progress-track"><div class="progress-fill" style="width:${pct}%"></div></div>
      <p class="tiny" style="margin:10px 0 0"><b>${learned}</b> / ${TERMS.length} ${esc(t("masteredLbl"))}</p>
    </div>

    <div class="section-head"><h2>${esc(t("modulesTitle"))}</h2><a href="#/modules">${esc(t("seeAll"))} →</a></div>
    <div class="module-grid">
      ${preview.map(moduleCardHtml).join("")}
    </div>

    <div class="section-head"><h2>${esc(t("quizCtaTitle"))}</h2></div>
    <a class="card due-banner" href="#/quiz" style="text-decoration:none;color:inherit">
      <div><h3>📝 ${esc(t("quizTitle"))}</h3><p class="tiny" style="margin:4px 0 0">${esc(t("quizCtaText"))}</p></div>
      <span class="btn small">→</span>
    </a>`;
}

function moduleCardHtml(m) {
  const p = moduleProgress(m);
  const chapters = new Set((MODULE_TERMS.get(m.id) || []).map((x) => x.chapter)).size;
  return `
    <a class="module-card" style="--mod-color:${m.color}" href="#/flash/${m.id}">
      <span class="module-ico" aria-hidden="true">${m.icon}</span>
      <h3>${esc(m[lang] || m.fr)}</h3>
      <span class="tiny">${p.total} ${esc(t("termsLbl"))} · ${chapters} ${esc(t("chaptersLbl"))}</span>
      <div class="progress-track"><div class="progress-fill" style="width:${p.pct}%;background:${m.color}"></div></div>
      <span class="mod-pct">${p.pct}%</span>
    </a>`;
}

/* ---------- vue : Modules ---------- */
function viewModules(view) {
  view.innerHTML = `
    <div class="section-head" style="margin-top:0">
      <h1>${esc(t("modulesTitle"))}</h1>
      <span class="tiny">${TERMS.length} ${esc(t("termsLbl"))}</span>
    </div>
    <a class="search-box" href="#/search" style="text-decoration:none;color:inherit">
      <span aria-hidden="true">🔍</span>
      <span class="muted" style="flex:1">${esc(t("searchPlaceholder"))}</span>
    </a>
    <div class="module-grid">${MODULES.map(moduleCardHtml).join("")}</div>`;
}

/* ---------- vue : Lexique / recherche ---------- */
function viewSearch(view) {
  view.innerHTML = `
    <div class="section-head" style="margin-top:0"><h1>${esc(t("searchTitle"))}</h1></div>
    <div class="search-box">
      <span aria-hidden="true">🔍</span>
      <input id="q" type="search" placeholder="${esc(t("searchPlaceholder"))}" autocomplete="off" autofocus>
    </div>
    <div id="results"><div class="empty"><span class="empty-ico">📖</span>${esc(t("searchEmpty"))}</div></div>`;

  const input = $("#q");
  const out = $("#results");
  input.addEventListener("input", () => {
    const q = norm(input.value);
    if (q.length < 2) {
      out.innerHTML = `<div class="empty"><span class="empty-ico">📖</span>${esc(t("searchEmpty"))}</div>`;
      return;
    }
    const hits = TERMS.filter((x) =>
      norm(x.en).includes(q) || norm(x.fr).includes(q) ||
      norm(x.ar).includes(q) || norm(x.defFr).includes(q)
    ).slice(0, 40);
    if (!hits.length) {
      out.innerHTML = `<div class="empty"><span class="empty-ico">🔍</span>${esc(t("noResults"))}</div>`;
      return;
    }
    out.innerHTML = hits.map((x) => `
      <button class="result-item" data-id="${x.id}">
        <strong>${esc(x.en)}</strong>
        <span class="r-fr">${esc(x.fr)}</span>
        <span class="tiny">${esc(x.module)}${x.chapter ? " · " + esc(x.chapter) : ""}</span>
      </button>`).join("");
    out.querySelectorAll("[data-id]").forEach((b) =>
      b.addEventListener("click", () => openTermSheet(+b.dataset.id)));
  });
  input.focus();
}

/* ---------- fiche de terme (bottom sheet) ---------- */
function openTermSheet(id) {
  const x = TERMS_BY_ID.get(id);
  if (!x) return;
  const def = lang === "ar" ? x.defAr : lang === "en" ? x.defEn : x.defFr;
  const ex = lang === "ar" ? x.exAr : lang === "en" ? x.exEn : x.exFr || x.exEn;
  const rows = [
    [t("defLbl"), def],
    [t("exLbl"), ex],
    [t("etymLbl"), x.etym],
    [t("pearlLbl"), x.pearl],
    [t("mnemoLbl"), x.mnemo],
  ].filter(([, v]) => v);

  const wrap = document.createElement("div");
  wrap.className = "sheet-backdrop";
  wrap.innerHTML = `
    <div class="sheet" role="dialog" aria-modal="true">
      <div class="sheet-grip"></div>
      <div style="display:flex;align-items:center;gap:12px">
        <div style="flex:1">
          <h2>${esc(x.en)} ${x.ipa ? `<span class="tiny" style="font-family:var(--font-ui)">${esc(x.ipa)}</span>` : ""}</h2>
          <p class="muted" style="margin:4px 0 0">${esc(x.fr)} · <span dir="rtl">${esc(x.ar)}</span></p>
          <p class="tiny" style="margin:2px 0 0">${esc(x.module)}${x.chapter ? " · " + esc(x.chapter) : ""}</p>
        </div>
        <button class="speak-btn" title="Prononcer" aria-label="Prononcer">🔊</button>
      </div>
      <dl>${rows.map(([k, v]) => `<dt>${esc(k)}</dt><dd${k === "" ? "" : ""}>${esc(v)}</dd>`).join("")}</dl>
      <button class="btn ghost block" style="margin-top:18px">${esc(t("closeLbl"))}</button>
    </div>`;
  document.body.appendChild(wrap);
  wrap.querySelector(".speak-btn").addEventListener("click", () => speakTerm(x));
  const close = () => wrap.remove();
  wrap.querySelector(".btn").addEventListener("click", close);
  wrap.addEventListener("click", (e) => { if (e.target === wrap) close(); });
}

/* ---------- vue : Flashcards (session SM-2) ---------- */
function buildFlashSession(param) {
  let list;
  if (param === "all") list = TERMS;
  else {
    const m = modById(param);
    list = m ? (MODULE_TERMS.get(m.id) || []) : [];
  }
  if (!list.length) return null;
  const now = Date.now();
  const due = list.filter((x) => isDue(state.sm2[x.id]))
    .sort((a, b) => state.sm2[a.id].due - state.sm2[b.id].due);
  const fresh = shuffle(list.filter((x) => !state.sm2[x.id]));
  const queue = [...due, ...fresh].map((x) => x.id).slice(0, FLASH_CAP);
  return { queue, i: 0, flipped: false, ok: 0, gems: 0, reviewed: 0, param };
}

function viewFlash(view, param) {
  if (!flashSession || flashSession.param !== param) flashSession = buildFlashSession(param);
  const s = flashSession;
  if (!s || s.i >= s.queue.length) return renderFlashDone(view, s);

  const x = TERMS_BY_ID.get(s.queue[s.i]);
  const prev = state.sm2[x.id];
  const def = lang === "ar" ? x.defAr : lang === "en" ? x.defEn : x.defFr;
  const ex = lang === "ar" ? x.exAr : lang === "en" ? x.exEn : x.exFr || x.exEn;
  const prog = Math.round((s.i / s.queue.length) * 100);

  view.innerHTML = `
    <div class="session-top">
      <button class="icon-btn" id="flashExit" title="${esc(t("exitLbl"))}" aria-label="${esc(t("exitLbl"))}">✕</button>
      <div class="progress-track"><div class="progress-fill" style="width:${prog}%"></div></div>
      <span class="tiny" style="font-family:var(--font-display);font-weight:600">${s.i + 1}/${s.queue.length}</span>
    </div>

    <div class="flash-stage">
      <div class="flashcard" id="flashcard" role="button" tabindex="0" aria-label="${esc(x.en)}">
        <div class="flash-face flash-front">
          ${x.chapter ? `<span class="chapter-tag">${esc(x.chapter)}</span>` : ""}
          <div class="term-en" ${lang === "ar" ? "" : ""}>${esc(x.en)}</div>
          ${x.ipa ? `<div class="ipa">${esc(x.ipa)}</div>` : ""}
          <button class="speak-btn" id="flashSpeak" aria-label="Prononcer" title="Prononcer">🔊</button>
          <span class="flip-hint">${esc(t("tapFlip"))} · ␣</span>
        </div>
        <div class="flash-face flash-back">
          <span class="back-lang">FR · AR</span>
          <div class="back-term">${esc(x.fr)}</div>
          <div class="back-ar" dir="rtl">${esc(x.ar)}</div>
          <div class="back-def">${esc(def)}</div>
          ${ex ? `<div class="back-extra">${esc(t("exLbl"))} : ${esc(ex)}</div>` : ""}
        </div>
      </div>
    </div>

    <div class="rating-row" id="ratingRow">
      ${[1, 2, 3, 4, 5].map((q) => {
        const sim = sm2Next(q, prev || { rep: 0, ef: 2.5, iv: 1 });
        return `<button class="rating-btn" data-q="${q}" disabled>
          ${esc(t("rate" + q))}<small>${fmtIv(sim.iv)}</small></button>`;
      }).join("")}
    </div>
    <p class="tiny" id="rateHint" style="text-align:center;margin-top:10px">${esc(t("rateWait"))}</p>`;

  const card = $("#flashcard");
  const flip = () => {
    s.flipped = !s.flipped;
    card.classList.toggle("flipped", s.flipped);
    $$("#ratingRow .rating-btn").forEach((b) => { b.disabled = !s.flipped; });
    $("#rateHint").textContent = s.flipped ? "" : t("rateWait");
  };
  card.addEventListener("click", (e) => { if (!e.target.closest("#flashSpeak")) flip(); });
  card.addEventListener("keydown", (e) => { if (e.key === "Enter") flip(); });
  $("#flashSpeak").addEventListener("click", (e) => { e.stopPropagation(); speakTerm(x); });

  $$("#ratingRow .rating-btn").forEach((b) => {
    b.addEventListener("click", () => rateCard(+b.dataset.q, view, param));
  });
  $("#flashExit").addEventListener("click", async () => {
    const r = await dialog({
      icon: "👋", title: t("quitTitle"), text: t("quitText"),
      actions: [
        { id: "stay", label: t("stayLbl"), cls: "" },
        { id: "quit", label: t("quitLbl"), cls: "danger" },
      ],
    });
    if (r === "quit") { flashSession = null; location.hash = "#/modules"; }
  });
}

function rateCard(q, view, param) {
  const s = flashSession;
  const id = s.queue[s.i];
  state.sm2[id] = sm2Next(q, state.sm2[id]);
  state.flashReviewed += 1;
  if (q >= 3) {
    state.flashSuccess += 1;
    state.gems += 1;
    s.gems += 1;
    if (q === 5) state.gems += 1;
  }
  if (q === 5) s.gems += 1;
  state.xp += 5;
  s.ok += q >= 3 ? 1 : 0;
  s.reviewed += 1;
  s.i += 1;
  s.flipped = false;
  touchStreak();
  save();
  renderChrome();
  viewFlash(view, param);
}

function renderFlashDone(view, s) {
  flashSession = null;
  const total = s ? s.reviewed : 0;
  if (total === 0) {
    view.innerHTML = `
      <div class="empty card" style="margin-top:24px">
        <span class="empty-ico">✅</span>
        <h2>${esc(t("emptyDue"))}</h2>
        <p class="muted">${esc(t("emptyDueSub"))}</p>
        <a class="btn" href="#/modules" style="margin-top:12px">${esc(t("backCourses"))}</a>
      </div>`;
    return;
  }
  const rate = Math.round((s.ok / total) * 100);
  view.innerHTML = `
    <div class="summary card" style="margin-top:24px">
      <div class="dlg-ico" style="font-size:2.6rem">🎉</div>
      <h1>${esc(t("doneTitle"))}</h1>
      <p class="muted" style="margin:6px 0 0">${esc(t("doneSub"))}</p>
      <div class="summary-stats">
        <div class="stat-tile"><b>${rate}%</b><span>${esc(t("successLbl"))}</span></div>
        <div class="stat-tile"><b>+${s ? s.gems : 0}</b><span>💎 ${esc(t("gemsLbl"))}</span></div>
        <div class="stat-tile"><b>${total}</b><span>🃏 ${esc(t("masteredLbl"))}</span></div>
      </div>
      <a class="btn block" href="#/home">${esc(t("backHome"))}</a>
      <a class="btn ghost block" style="margin-top:10px" href="#/modules">${esc(t("backCourses"))}</a>
    </div>`;
}

/* ---------- vue : Quiz ---------- */
function viewQuiz(view, param) {
  if (param === "start" || !quizSession) quizSession = { phase: "start", level: 0 };
  if (quizSession.phase === "start") return quizStart(view);
  if (quizSession.phase === "done") return quizDone(view);
  return quizPlay(view);
}

function quizFiltered() {
  const lv = quizSession.level;
  const pool = lv ? EXERCISES.filter((e) => e.level === lv) : EXERCISES;
  return shuffle(pool).slice(0, QUIZ_CAP);
}

function quizStart(view) {
  const q = quizSession;
  const count = (q.level ? EXERCISES.filter((e) => e.level === q.level) : EXERCISES).length;
  view.innerHTML = `
    <div class="section-head" style="margin-top:0">
      <div><p class="eyebrow">📝 ${esc(t("quizSub"))}</p><h1>${esc(t("quizTitle"))}</h1></div>
      <span class="stat-chip hearts">❤️ <b>${state.hearts}</b></span>
    </div>
    <div class="chip-row">
      <button class="chip ${q.level === 0 ? "active" : ""}" data-lv="0">${esc(t("levelAll"))}</button>
      ${[1, 2, 3, 4, 5, 6].map((n) =>
        `<button class="chip ${q.level === n ? "active" : ""}" data-lv="${n}">${n}. ${esc(t("lvl" + n))}</button>`).join("")}
    </div>
    <div class="card">
      <p class="muted" style="margin:0 0 14px">${count} ${esc(t("exCount"))} · ${Math.min(count, QUIZ_CAP)} ${esc(t("exCount"))} par session · ⭐ ${esc(t("statXp"))} + 💎 ${esc(t("statGems"))}</p>
      <button class="btn block" id="quizGo" ${count ? "" : "disabled"}>${esc(t("startQuiz"))} →</button>
    </div>`;

  view.querySelectorAll(".chip").forEach((c) => c.addEventListener("click", () => {
    quizSession.level = +c.dataset.lv;
    quizStart(view);
  }));
  const go = $("#quizGo");
  if (go) go.addEventListener("click", () => {
    Object.assign(quizSession, {
      phase: "play", list: quizFiltered(), i: 0,
      correctN: 0, gems: 0, xp: 0, mistakes: 0,
    });
    setupQuestion();
    quizPlay(view);
  });
}

/** prépare l'état d'une question (options mélangées, banque de mots, paires) */
function setupQuestion() {
  const q = quizSession;
  const ex = q.list[q.i];
  q.sel = null; q.checked = false; q.wordSel = []; q.matchDone = {}; q.matchPick = null; q.mistakesQ = 0;
  if (ex.type === "mcq" || ex.type === "reading" || ex.type === "clinical_case") {
    const pairs = shuffle(ex.options.map((o, idx) => ({ o })));
    q.opts = pairs.map((p) => p.o);
    q.answerIdx = q.opts.findIndex((o) => norm(o) === norm(ex.answer));
  } else if (ex.type === "sentence_order") {
    q.bank = shuffle(ex.options.map((w) => ({ w, used: false })));
  } else if (ex.type === "matching") {
    const pairs = ex.options.map((s) => {
      const i = s.indexOf(":");
      return { l: s.slice(0, i).trim(), r: s.slice(i + 1).trim() };
    });
    q.lefts = shuffle(pairs.map((p) => p.l));
    q.rights = shuffle(pairs.map((p) => p.r));
    q.pairs = pairs;
  }
}

function quizQText(ex) { return lang === "ar" ? ex.qAr || ex.qEn : lang === "en" ? ex.qEn : ex.qFr || ex.qEn; }
function quizExpText(ex) { return lang === "ar" ? ex.expAr || ex.expEn : lang === "en" ? ex.expEn : ex.expFr || ex.expEn; }
function quizCtxText(ex) { return lang === "ar" ? ex.ctxAr || ex.ctxEn : lang === "en" ? ex.ctxEn : ex.ctxFr || ex.ctxEn; }

function quizPlay(view) {
  const q = quizSession;
  const ex = q.list[q.i];
  const prog = Math.round((q.i / q.list.length) * 100);
  const ctx = (ex.type === "reading" || ex.type === "clinical_case") ? quizCtxText(ex) : "";

  let body = "";
  if (ex.type === "mcq" || ex.type === "reading" || ex.type === "clinical_case") {
    body = `<div class="options" id="opts">
      ${q.opts.map((o, i) => `<button class="option" data-i="${i}" ${q.checked ? "disabled" : ""}>${esc(o)}</button>`).join("")}
    </div>`;
  } else if (ex.type === "fill_blank") {
    body = `
      <p class="tiny">${esc(t("typeHint"))}</p>
      <input class="fill-input" id="fillInput" type="text" autocomplete="off" ${q.checked ? "disabled" : ""} value="${esc(q.fillVal || "")}">`;
  } else if (ex.type === "sentence_order") {
    body = `
      <p class="tiny">${esc(t("orderHint"))}</p>
      <div class="wordbank" id="zone">${q.wordSel.length
        ? q.wordSel.map((bi) => `<button class="word" data-zone="${bi}">${esc(q.bank[bi].w)}</button>`).join("")
        : `<span class="tiny" style="align-self:center">${esc(t("orderHint"))}</span>`}</div>
      <div class="word-bank" id="bank">
        ${q.bank.map((b, i) => `<button class="word" data-bank="${i}" ${b.used || q.checked ? "disabled" : ""}>${esc(b.w)}</button>`).join("")}
      </div>`;
  } else if (ex.type === "matching") {
    body = `
      <p class="tiny">${esc(t("matchHint"))}</p>
      <div class="match-grid">
        <div class="match-col" id="colL">
          ${q.lefts.map((l) => `<button class="match-item ${q.matchDone[l] ? "done" : ""} ${q.matchPick === l ? "picked" : ""}" data-l="${esc(l)}" ${q.matchDone[l] ? "disabled" : ""}>${esc(l)}</button>`).join("")}
        </div>
        <div class="match-col" id="colR">
          ${q.rights.map((r) => {
            const doneL = Object.keys(q.matchDone).find((l) => q.matchDone[l] === r);
            return `<button class="match-item ${doneL ? "done" : ""}" data-r="${esc(r)}" ${doneL ? "disabled" : ""}>${esc(r)}</button>`;
          }).join("")}
        </div>
      </div>`;
  }

  view.innerHTML = `
    <div class="session-top">
      <button class="icon-btn" id="quizExit" aria-label="${esc(t("exitLbl"))}">✕</button>
      <div class="progress-track"><div class="progress-fill" style="width:${prog}%"></div></div>
      <span class="tiny" style="font-family:var(--font-display);font-weight:600">${q.i + 1}/${q.list.length}</span>
    </div>
    ${ctx ? `<div class="quiz-ctx">${esc(ctx)}</div>` : ""}
    <p class="eyebrow">${esc(t("questionLbl"))} · ${esc(ex.module)} · ⭐${ex.points}</p>
    <div class="quiz-q">${esc(quizQText(ex))}</div>
    ${body}
    <div id="fbArea"></div>
    ${ex.type === "matching" ? "" : `<button class="btn block" id="quizCheck" style="margin-top:16px" disabled>${esc(t("checkLbl"))}</button>`}`;

  wireQuizQuestion(view, ex);
}

function wireQuizQuestion(view, ex) {
  const q = quizSession;
  const checkBtn = $("#quizCheck");

  $("#quizExit").addEventListener("click", async () => {
    const r = await dialog({
      icon: "👋", title: t("quitTitle"), text: t("quitText"),
      actions: [
        { id: "stay", label: t("stayLbl") },
        { id: "quit", label: t("quitLbl"), cls: "danger" },
      ],
    });
    if (r === "quit") { quizSession = null; location.hash = "#/quiz"; }
  });

  if (ex.type === "mcq" || ex.type === "reading" || ex.type === "clinical_case") {
    $$("#opts .option").forEach((b) => b.addEventListener("click", () => {
      if (q.checked) return;
      q.sel = +b.dataset.i;
      $$("#opts .option").forEach((o) => o.classList.toggle("selected", +o.dataset.i === q.sel));
      if (checkBtn) checkBtn.disabled = false;
    }));
  }

  if (ex.type === "fill_blank") {
    const inp = $("#fillInput");
    inp.addEventListener("input", () => {
      q.fillVal = inp.value;
      if (checkBtn) checkBtn.disabled = !inp.value.trim();
    });
    inp.addEventListener("keydown", (e) => {
      if (e.key === "Enter" && inp.value.trim() && !q.checked) doCheck(view, ex);
    });
    inp.focus();
  }

  if (ex.type === "sentence_order") {
    $$("#bank .word").forEach((b) => b.addEventListener("click", () => {
      if (q.checked) return;
      const i = +b.dataset.bank;
      q.bank[i].used = true;
      q.wordSel.push(i);
      quizPlay(view);
    }));
    $$("#zone .word").forEach((b) => b.addEventListener("click", () => {
      if (q.checked) return;
      const bi = +b.dataset.zone;
      q.wordSel = q.wordSel.filter((x) => x !== bi);
      q.bank[bi].used = false;
      quizPlay(view);
    }));
    if (checkBtn && q.wordSel.length) checkBtn.disabled = false;
  }

  if (ex.type === "matching") {
    $$("#colL .match-item").forEach((b) => b.addEventListener("click", () => {
      if (b.disabled) return;
      q.matchPick = q.matchPick === b.dataset.l ? null : b.dataset.l;
      quizPlay(view);
    }));
    $$("#colR .match-item").forEach((b) => b.addEventListener("click", () => {
      if (b.disabled || !q.matchPick) return;
      const pair = q.pairs.find((p) => p.l === q.matchPick);
      if (pair && pair.r === b.dataset.r) {
        q.matchDone[q.matchPick] = pair.r;
        q.matchPick = null;
        if (Object.keys(q.matchDone).length === q.pairs.length) {
          applyResult(view, ex, q.mistakesQ === 0);
          return;
        }
        quizPlay(view);
      } else {
        q.mistakesQ += 1;
        b.classList.add("shake");
        setTimeout(() => { b.classList.remove("shake"); q.matchPick = null; quizPlay(view); }, 380);
      }
    }));
  }

  if (checkBtn) checkBtn.addEventListener("click", () => doCheck(view, ex));
}

function evalAnswer(ex) {
  const q = quizSession;
  if (ex.type === "mcq" || ex.type === "reading" || ex.type === "clinical_case") {
    return q.sel === q.answerIdx;
  }
  if (ex.type === "fill_blank") {
    return norm(q.fillVal || "") === norm(ex.answer);
  }
  if (ex.type === "sentence_order") {
    const joined = q.wordSel.map((i) => q.bank[i].w).join(" ").replace(/\s+/g, " ").trim();
    const want = ex.answer.replace(/\s+/g, " ").trim();
    return joined === want;
  }
  return false;
}

function doCheck(view, ex) {
  const q = quizSession;
  if (q.checked) return;
  q.checked = true;
  applyResult(view, ex, evalAnswer(ex));
}

function applyResult(view, ex, ok) {
  const q = quizSession;
  if (q.alreadyResult) return; // garde anti double-comptage (matching auto)
  q.alreadyResult = true;
  state.answered += 1;
  if (ok) {
    state.correct += 1;
    state.gems += 1;
    state.xp += ex.points;
    q.correctN += 1;
    q.gems += 1;
    q.xp += ex.points;
  } else {
    loseHeart();
    q.mistakes += 1;
  }
  touchStreak();
  save();
  renderChrome();

  // surbrillance des QCM
  if (q.opts) {
    $$("#opts .option").forEach((o) => {
      const i = +o.dataset.i;
      o.disabled = true;
      o.classList.remove("selected");
      if (i === q.answerIdx) o.classList.add("correct");
      else if (i === q.sel) o.classList.add("wrong");
    });
  }
  const checkBtn = $("#quizCheck");
  if (checkBtn) checkBtn.hidden = true;

  const correctText = ex.type === "matching"
    ? ex.options.join("  |  ")
    : ex.type === "sentence_order" ? ex.answer : ex.answer;
  const exp = quizExpText(ex);
  $("#fbArea").innerHTML = `
    <div class="feedback ${ok ? "ok" : "ko"}">
      <div class="fb-title">${ok ? "✅ " + esc(t("correctLbl")) : "❌ " + esc(t("wrongLbl"))}</div>
      ${ok ? "" : `<div class="fb-exp"><b>${esc(t("answerLbl"))} :</b> ${esc(correctText)}</div>`}
      ${exp ? `<div class="fb-exp" style="margin-top:6px"><b>${esc(t("explainLbl"))} :</b> ${esc(exp)}</div>` : ""}
      <button class="btn block" id="quizNext">${esc(t("nextLbl"))} →</button>
    </div>`;
  $("#quizNext").addEventListener("click", () => nextQuestion(view));
  $("#fbArea").scrollIntoView({ behavior: "smooth", block: "nearest" });
}

async function nextQuestion(view) {
  const q = quizSession;
  q.alreadyResult = false;
  q.i += 1;
  if (q.i >= q.list.length) {
    state.quizDone += 1;
    save();
    q.phase = "done";
    quizDone(view);
    return;
  }
  if (state.hearts === 0) {
    const actions = [];
    if (state.gems >= 50) actions.push({ id: "refill", label: t("refillLbl") });
    actions.push({ id: "leave", label: t("leaveLbl"), cls: "danger" });
    const r = await dialog({ icon: "❤️", title: t("outHeartsTitle"), text: t("outHeartsText"), actions });
    if (r === "refill") {
      state.gems -= 50;
      state.hearts = 5;
      state.heartsTs = Date.now();
      save();
      renderChrome();
    } else {
      quizSession = null;
      location.hash = "#/home";
      return;
    }
  }
  setupQuestion();
  quizPlay(view);
}

function quizDone(view) {
  const q = quizSession;
  const total = q.list.length;
  const score = Math.round((q.correctN / total) * 100);
  view.innerHTML = `
    <div class="summary card" style="margin-top:24px">
      <div class="big-num">${score}%</div>
      <div class="big-label">${esc(t("scoreLbl"))} · ${esc(t("doneQuiz"))}</div>
      <div class="summary-stats">
        <div class="stat-tile"><b>${q.correctN}/${total}</b><span>✅ ${esc(t("correctLbl"))}</span></div>
        <div class="stat-tile"><b>+${q.xp}</b><span>⭐ ${esc(t("statXp"))}</span></div>
        <div class="stat-tile"><b>+${q.gems}</b><span>💎 ${esc(t("statGems"))}</span></div>
      </div>
      <a class="btn block" href="#/home">${esc(t("homeLbl"))}</a>
      <button class="btn ghost block" style="margin-top:10px" id="quizReplay">${esc(t("replay"))}</button>
    </div>`;
  $("#quizReplay").addEventListener("click", () => {
    quizSession = { phase: "start", level: quizSession.level };
    quizStart(view);
  });
}

/* ---------- vue : Profil ---------- */
function viewProfile(view) {
  const learned = Object.keys(state.sm2).length;
  const acc = state.answered ? Math.round((state.correct / state.answered) * 100) : 0;
  const master = Object.values(state.sm2).filter((s) => s.rep >= 3).length;
  view.innerHTML = `
    <div class="section-head" style="margin-top:0"><h1>${esc(t("profileTitle"))}</h1></div>
    <div class="stat-row">
      <div class="stat-tile"><b>${state.streak}</b><span>🔥 ${esc(t("statStreak"))}</span></div>
      <div class="stat-tile"><b>${state.xp}</b><span>⭐ ${esc(t("statXp"))}</span></div>
      <div class="stat-tile"><b>${acc}%</b><span>🎯 ${esc(t("statAccuracy"))}</span></div>
    </div>
    <div class="card" style="margin-top:14px">
      <div class="setting-row"><span>${esc(t("masteredLbl"))}</span><b style="font-family:var(--font-display)">${learned} / ${TERMS.length}</b></div>
      <div class="setting-row"><span>🎓 ${esc(t("lvl1"))} — rep ≥ 3</span><b style="font-family:var(--font-display)">${master}</b></div>
      <div class="setting-row"><span>🃏 ${esc(t("successLbl"))} flashcards</span><b style="font-family:var(--font-display)">${state.flashReviewed}</b></div>
      <div class="setting-row"><span>📝 ${esc(t("answeredLbl"))}</span><b style="font-family:var(--font-display)">${state.answered}</b></div>
      <div class="setting-row"><span>🏁 ${esc(t("quizTitle"))}</span><b style="font-family:var(--font-display)">${state.quizDone}</b></div>
      <div class="setting-row"><span>💎 ${esc(t("statGems"))} · ❤️ ${esc(t("statHearts"))}</span><b style="font-family:var(--font-display)">${state.gems} · ${state.hearts}/5</b></div>
    </div>

    <div class="section-head"><h2>${esc(t("langLbl"))}</h2></div>
    <div class="card">
      <div class="lang-switch">
        <button data-lang="fr" class="${lang === "fr" ? "active" : ""}">FR</button>
        <button data-lang="en" class="${lang === "en" ? "active" : ""}">EN</button>
        <button data-lang="ar" class="${lang === "ar" ? "active" : ""}">AR</button>
      </div>
      <div class="setting-row" style="margin-top:8px">
        <button class="btn danger small" id="resetBtn">↺ ${esc(t("resetLbl"))}</button>
      </div>
    </div>
    <p class="tiny" style="text-align:center;margin-top:24px">${esc(t("aboutTxt"))}</p>`;

  view.querySelectorAll("[data-lang]").forEach((b) =>
    b.addEventListener("click", () => setLang(b.dataset.lang)));
  $("#resetBtn").addEventListener("click", async () => {
    const r = await dialog({
      icon: "⚠️", title: t("resetTitle"), text: t("resetText"),
      actions: [
        { id: "cancel", label: t("cancelLbl") },
        { id: "reset", label: t("resetConfirmLbl"), cls: "danger" },
      ],
    });
    if (r === "reset") {
      const keepLang = state.lang, keepIntro = state.introSeen;
      state = Object.assign({}, DEFAULTS, { lang: keepLang, introSeen: keepIntro, heartsTs: Date.now() });
      save();
      flashSession = null; quizSession = null;
      renderChrome();
      viewProfile(view);
      toast("✓");
    }
  });
}

/* ---------- boot ---------- */
function bootKeyboard() {
  document.addEventListener("keydown", (e) => {
    const tag = (document.activeElement && document.activeElement.tagName) || "";
    if (tag === "INPUT" || tag === "TEXTAREA") return;
    const card = $("#flashcard");
    if (card && (e.code === "Space" || e.key === "Enter") && !e.target.closest("button")) {
      e.preventDefault();
      card.click();
      return;
    }
    if (card && flashSession && flashSession.flipped && /^[1-5]$/.test(e.key)) {
      const btn = document.querySelector(`#ratingRow .rating-btn[data-q="${e.key}"]`);
      if (btn && !btn.disabled) btn.click();
    }
  });
}

async function introIfNeeded() {
  if (state.introSeen) return;
  await dialog({
    icon: "🇸🇩",
    title: t("introTitle"),
    text: esc(t("introText")),
    actions: [{ id: "go", label: t("introCta") }],
  });
  state.introSeen = true;
  save();
}

async function boot() {
  applyDocumentLang();
  await loadData();
  renderChrome();
  bootKeyboard();
  window.addEventListener("hashchange", route);
  if (!location.hash) location.hash = "#/home";
  await route();
  await introIfNeeded();
  if ("serviceWorker" in navigator) {
    navigator.serviceWorker.register("sw.js").catch(() => { /* servi en local sans SW */ });
  }
}

boot();













