import { useEffect, useRef, useState } from "react";
import { useT } from "../i18n/useT";
import type { TKey } from "../i18n";
import { update, useAppState } from "../domain/store";
import type { AppState } from "../domain/store";
import { startLesson, endLesson, addToReviewQueue, markReviewPlayed } from "../domain/store";
import { lessonQuestions, quizExercises, quizExercisesForLevel, getReview } from "../domain/lessons";
import { addXp, touchStreak, calculateGemsForLesson, goalStreakOf } from "../domain/gamification";
import { lessonKey } from "../domain/path";
import { modLabel } from "../data/modules";
import { loadLessonData, getLessonEntry, getLessonMap, chapterIdOf } from "../data/load";
import { earnChest, cadenceBonusChest } from "../domain/chests";
import { claimTrophies, type TrophyId } from "../domain/trophies";
import { fillScore } from "../domain/scoring";
import { weakChapters } from "../domain/mastery";
import { MODULES } from "../data/modules";
import { shuffle, norm } from "../lib/utils";
import { SessionTop } from "../components/SessionTop";
import { RewardPopup, type RewardData } from "../components/RewardPopup";
import { useDialog } from "../components/DialogProvider";
import { useToast } from "../components/ToastProvider";
import { navigate } from "../lib/router";
import type { Exercise, Lang } from "../types";

export const QUIZ_CAP = 10;
/** WS5.3 — format EMD : 20 questions, 15 minutes, seuil de réussite 50 %. */
export const EMD_QUESTION_COUNT = 20;
export const EMD_DURATION_MIN = 15;
export const EMD_MIN_QUESTIONS = 10;
export const EMD_PASS_SCORE = 50;

/* Miroir de LearningLevel.kt — 6 niveaux progressifs, chacun avec son icône,
   sa couleur et son objectif. `objectiveKey` pointe vers les i18n. */
const LEARNING_LEVELS: {
  n: number;
  icon: string;
  color: string;
  key: TKey;
  objectiveKey: TKey;
}[] = [
  { n: 1, icon: "📖", color: "#146B43", key: "lvl1", objectiveKey: "lvl1Obj" },
  { n: 2, icon: "🧩", color: "#0F766E", key: "lvl2", objectiveKey: "lvl2Obj" },
  { n: 3, icon: "💬", color: "#1565C0", key: "lvl3", objectiveKey: "lvl3Obj" },
  { n: 4, icon: "📝", color: "#6A1B9A", key: "lvl4", objectiveKey: "lvl4Obj" },
  { n: 5, icon: "📄", color: "#E65100", key: "lvl5", objectiveKey: "lvl5Obj" },
  { n: 6, icon: "🏥", color: "#DC2626", key: "lvl6", objectiveKey: "lvl6Obj" },
];

/* Miroir de UserStatsEntity.isLevelUnlocked / getScoreForLevel — UNLOCK_THRESHOLD = 70.
   Sur le web les scores de niveau sont stockés dans `lessonBest` sous la clé
   `quiz:N` (le quiz libre n'a pas de module ; on utilise le pseudo-module `quiz`). */
function scoreForLevel(n: number, s: AppState): number {
  return s.lessonBest[`quiz:${n}`] ?? 0;
}
function isLevelUnlocked(n: number, s: AppState): boolean {
  if (n === 1) return true;
  return scoreForLevel(n - 1, s) >= 70;
}

/**
 * WS5.3 — compte à rebours de l'examen EMD. `onExpire` clôt la session une
 * seule fois (l'échéance force `next()` qui termine normalement la séance).
 * Horloge à la seconde : un examen est la seule session chronométrée.
 */
function ExamCountdown({ endsAt, onExpire }: { endsAt: number; onExpire: () => void }) {
  const [now, setNow] = useState(() => Date.now());
  useEffect(() => {
    const id = window.setInterval(() => setNow(Date.now()), 1000);
    return () => window.clearInterval(id);
  }, []);
  const left = Math.max(0, endsAt - now);
  useEffect(() => {
    if (left === 0) onExpire();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [left === 0]);
  const min = Math.floor(left / 60_000);
  const sec = Math.floor((left % 60_000) / 1000);
  const urgent = left < 60_000;
  return (
    <div
      role="timer"
      aria-live="off"
      style={{
        textAlign: "center",
        fontWeight: 700,
        fontSize: 15,
        margin: "4px 0",
        color: urgent ? "#B3261E" : "#0B5A3C",
        fontVariantNumeric: "tabular-nums",
      }}
    >
      ⏱ {String(min).padStart(2, "0")}:{String(sec).padStart(2, "0")}
    </div>
  );
}

/** Cadre de session lue dans le hash : `#/quiz?module=anat&level=3`, `#/quiz?mode=exam`. */
function frameFromHash(): {
  module?: string;
  level: number;
  mode?: string;
  /** Entrée de chapitre (LOT2) : trace la progression + alimente la file de révision. */
  lesson?: string;
  /** Leçon de révision (LOT2) : rejoue les exercices ratés (`getReview`). */
  review?: string;
} {
  if (typeof window === "undefined") return { level: 0 };
  const query = window.location.hash.split("?")[1] ?? "";
  const p = new URLSearchParams(query);
  const raw = Number(p.get("level") ?? 0);
  return {
    module: p.get("module") ?? undefined,
    level: Number.isFinite(raw) && raw >= 1 && raw <= 6 ? raw : 0,
    mode: p.get("mode") ?? undefined,
    lesson: p.get("lesson") ?? undefined,
    review: p.get("review") ?? undefined,
  };
}

const pick = <T,>(lang: Lang, ar: T, en: T, fr: T): T =>
  lang === "ar" ? ar : lang === "en" ? en : fr;

const qText = (ex: Exercise, lang: Lang) => pick(lang, ex.qAr || ex.qEn, ex.qEn, ex.qFr || ex.qEn);
const expText = (ex: Exercise, lang: Lang) =>
  pick(lang, ex.expAr || ex.expEn, ex.expEn, ex.expFr || ex.expEn);
const ctxText = (ex: Exercise, lang: Lang) =>
  pick(lang, ex.ctxAr || ex.ctxEn, ex.ctxEn, ex.ctxFr || ex.ctxEn);

interface Word {
  w: string;
  used: boolean;
}
interface Pair {
  l: string;
  r: string;
}

interface QuizState {
  phase: "start" | "play" | "done";
  level: number;
  /** Module de la leçon cadrée (`?module=`), vide en quiz libre. */
  moduleId: string;
  list: Exercise[];
  i: number;
  correctN: number;
  gems: number;
  xp: number;
  /* état transitoire de la question courante */
  opts: string[];
  answerIdx: number;
  sel: number | null;
  checked: boolean;
  lastOk: boolean;
  fillVal: string;
  bank: Word[];
  wordSel: number[];
  pairs: Pair[];
  lefts: string[];
  rights: string[];
  matchDone: Record<string, string>;
  matchPick: string | null;
  mistakesQ: number;
  alreadyResult: boolean;
  /* WS5.3 — mode examen EMD : échéance globale (ms) + ratés pour le renvoi. */
  examEndsAt: number | null;
  wrong: Array<{ chapter: string; module: string }>;
}

const IDLE: QuizState = {
  phase: "start",
  // 1 et non 0 : à l'arrivée directe sur `#/quiz` (ou via l'onglet), la carte
  // de quiz libre affiche le pool du niveau courant — avec 0 le compteur
  // affichait « 0 exercices » et le bouton de démarrage restait désactivé
  // jusqu'à ce que l'utilisateur clique d'abord une carte de niveau. L1 est
  // toujours débloqué (pas de prérequis dans la cascade).
  level: 1,
  moduleId: "",
  list: [],
  i: 0,
  correctN: 0,
  gems: 0,
  xp: 0,
  opts: [],
  answerIdx: -1,
  sel: null,
  checked: false,
  lastOk: false,
  fillVal: "",
  bank: [],
  wordSel: [],
  pairs: [],
  lefts: [],
  rights: [],
  matchDone: {},
  matchPick: null,
  mistakesQ: 0,
  alreadyResult: false,
  examEndsAt: null,
  wrong: [],
};

/** Prépare l'état d'une question (options mélangées, banque de mots, paires). */
function setupQuestion(ex: Exercise): Partial<QuizState> {
  const base: Partial<QuizState> = {
    sel: null,
    checked: false,
    lastOk: false,
    fillVal: "",
    wordSel: [],
    matchDone: {},
    matchPick: null,
    mistakesQ: 0,
    alreadyResult: false,
    opts: [],
    answerIdx: -1,
    examEndsAt: null,
    wrong: [],
    bank: [],
    pairs: [],
    lefts: [],
    rights: [],
  };

  if (ex.type === "mcq" || ex.type === "reading" || ex.type === "clinical_case") {
    const opts = shuffle(ex.options);
    base.opts = opts;
    base.answerIdx = opts.findIndex((o) => norm(o) === norm(ex.answer));
  } else if (ex.type === "sentence_order") {
    base.bank = shuffle(ex.options).map((w) => ({ w, used: false }));
  } else if (ex.type === "matching") {
    const pairs: Pair[] = ex.options.map((s) => {
      const idx = s.indexOf(":");
      return { l: s.slice(0, idx).trim(), r: s.slice(idx + 1).trim() };
    });
    base.pairs = pairs;
    base.lefts = shuffle(pairs.map((p) => p.l));
    base.rights = shuffle(pairs.map((p) => p.r));
  } else if (ex.type === "pair") {
    // Type "pair" dans le corpus : format question/réponse (fill-in-the-blank)
    // Exemple : options=['Quel:est'], answer='Heart valve'
    const q = ex.qFr || ex.qEn || ex.qAr || "";
    const a = ex.answer || "";
    if (q && a) {
      const pairs: Pair[] = [{ l: q, r: a }];
      base.pairs = pairs;
      base.lefts = shuffle([q]);
      base.rights = shuffle([a]);
    }
  }
  return base;
}

function evalAnswer(state: QuizState, ex: Exercise): boolean {
  if (ex.type === "mcq" || ex.type === "reading" || ex.type === "clinical_case") {
    return state.sel === state.answerIdx;
  }
  // fill_blank : géré par `fillScore` (crédit partiel 0.5) dans `check()`.
  if (ex.type === "sentence_order") {
    const joined = state.wordSel
      .map((i) => state.bank[i].w)
      .join(" ")
      .replace(/\s+/g, " ")
      .trim();
    return joined === ex.answer.replace(/\s+/g, " ").trim();
  }
  return false;
}

export function QuizScreen() {
  const { t, lang } = useT();
  const { open } = useDialog();
  const toast = useToast();
  const s = useAppState();
  const [q, setQ] = useState<QuizState>(IDLE);
  const [reward, setReward] = useState<RewardData | null>(null);
  const goalHitRef = useRef(false);
  const started = useRef(false);
  // Anti-re-entrée : un double-clic sur « Suivant » ne doit ni sauter une
  // question ni créditer la session deux fois (B21/B22).
  const finishedRef = useRef(false);
  const advToRef = useRef(-1);

  const submittingRef = useRef(false);
  /** Cadre LOT2 : entrée de chapitre en cours (progression + file de révision). */
  const lessonRef = useRef<{ id: string; chapterId: string } | null>(null);
  const missedRef = useRef<Array<number | string>>([]);
  /** Cadre LOT2 : leçon de révision en cours (marquée jouée à la fin). */
  const reviewRef = useRef<string | null>(null);
  const patch = (p: Partial<QuizState>) => setQ((prev) => ({ ...prev, ...p }));

  const begin = (level: number) => {
    // Niveau 0 = examen mixte (`#/quiz?mode=exam`) : le pool filtré par niveau
    // est alors vide, donc l'examen était injouable (« Leçon invalide » +
    // rebond sur la pyramide). Avec ≤ 0 on tire dans tout le corpus.
    const pool = level >= 1 ? quizExercisesForLevel(level) : quizExercises();
    const list = shuffle(pool).slice(0, QUIZ_CAP);
    if (!list.length) return false;
    goalHitRef.current = false;
    finishedRef.current = false;
    advToRef.current = -1;
    setQ({ ...IDLE, phase: "play", level, list, ...setupQuestion(list[0]) });
    return true;
  };

  /** Leçon cadrée par le parcours : 8 questions générées (module + niveau). */
  const beginLesson = (moduleId: string, level: number) => {
    const list = lessonQuestions(moduleId, level);
    if (!list.length) return false;
    goalHitRef.current = false;
    finishedRef.current = false;
    advToRef.current = -1;
    setQ({ ...IDLE, phase: "play", moduleId, level, list, ...setupQuestion(list[0]) });
    return true;
  };

  /** Leçon de révision (LOT2) : rejoue les exercices ratés d'une leçon. */
  /**
   * WS5.3 — examen EMD de module : 20 questions tirées de TOUT le corpus du
   * module (tous niveaux), chrono global 15 min. À l'échéance la session se
   * clôt (le bouton Vérifier devient inopérant). Résultat : score + renvoi
   * aux chapitres les plus ratés.
   */
  const beginExamEmd = (moduleId: string) => {
    const mod = MODULES.find((m) => m.id === moduleId);
    if (!mod) return false;
    const pool = quizExercises().filter((e) => e.module === mod.fr);
    const list = shuffle(pool).slice(0, EMD_QUESTION_COUNT);
    if (list.length < EMD_MIN_QUESTIONS) return false;
    goalHitRef.current = false;
    finishedRef.current = false;
    advToRef.current = -1;
    lessonRef.current = null;
    reviewRef.current = null;
    setQ({
      ...IDLE,
      phase: "play",
      moduleId,
      level: 0,
      list,
      examEndsAt: Date.now() + EMD_DURATION_MIN * 60_000,
      ...setupQuestion(list[0]),
    });
    return true;
  };

  const beginReview = (entryId: string) => {
    const list = getReview(entryId);
    if (!list || !list.length) return false;
    goalHitRef.current = false;
    finishedRef.current = false;
    advToRef.current = -1;
    reviewRef.current = entryId;
    setQ({ ...IDLE, phase: "play", list, ...setupQuestion(list[0]) });
    return true;
  };

  // Démarrage automatique quand on arrive avec un cadre (`?module=`, `?mode=exam`,
  // `?lesson=`, `?review=`). Un cadre invalide (module/niveau inconnu) affiche
  // une erreur au lieu de retomber silencieusement sur l'écran de choix (B34).
  useEffect(() => {
    if (started.current) return;
    const f = frameFromHash();
    const run = () => {
      started.current = true;
      if (f.review) {
        if (!beginReview(f.review)) {
          toast(t("invalidLesson"));
          navigate("review");
        }
        return;
      }
      if (f.module) {
        if (f.lesson) {
          // Cadre chapitre (LOT2) : trace la leçon (progression + file de révision).
          const entry = getLessonEntry(f.lesson);
          if (!entry) {
            toast(t("invalidLesson"));
            navigate("quiz");
            return;
          }
          startLesson(entry.id, f.module, chapterIdOf(entry));
          lessonRef.current = { id: entry.id, chapterId: chapterIdOf(entry) };
          missedRef.current = [];
        }
        if (!f.level || !beginLesson(f.module, f.level)) {
          toast(t("invalidLesson"));
          navigate("quiz");
        }
      } else if (f.mode === "exam") {
        // WS5.3 : `?mode=exam&module=X` = examen EMD du module (chrono) ;
        // sans module, examen mixte historique (tout le corpus).
        if (f.module) {
          if (!beginExamEmd(f.module)) {
            toast(t("invalidLesson"));
            navigate("quiz");
          }
        } else if (!begin(0)) {
          toast(t("invalidLesson"));
          navigate("quiz");
        }
      }
    };
    // `?lesson=`/`?review=` ont besoin de lesson_map.json, chargé
    // paresseusement (WS2.1 : plus de 1,8 Mo au démarrage global).
    if ((f.lesson || f.review) && Object.keys(getLessonMap()).length === 0) {
      loadLessonData()
        .then(run)
        .catch(() => {
          started.current = true;
          toast(t("invalidLesson"));
          navigate(f.review ? "review" : "quiz");
        });
      return;
    }
    run();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  /**
   * Enregistre le résultat. Effectuée HORS du updater `setQ` : React StrictMode
   * invoque les updaters deux fois en dev, ce qui aurait compté les gemmes 2×.
   *
   * Les gemmes ne sont pas créditées par bonne réponse : elles sont accordées
   * en fin de leçon via `calculateGemsForLesson` (10, +10 si parfaite, 0 si
   * < 50 % — règle `GemsManager` côté Android).
   *
   * `partialXp` : crédit partiel (fill_blank fuzzy, 0.5) — demi-XP sans gemme
   * ni point « correct », exactement comme `checkFill` côté Android.
   */
  const submit = (ok: boolean, partialXp = 0) => {
    if (q.phase !== "play" || q.alreadyResult || submittingRef.current) return;
    // WS5.3 : échéance EMD dépassée → plus aucune soumission (la session se
    // clôt via l'effet chrono ci-dessous).
    if (q.examEndsAt !== null && Date.now() > q.examEndsAt) return;
    submittingRef.current = true;
    const ex = q.list[q.i];
    const now = Date.now();

    update((draft) => {
      draft.answered += 1;
      touchStreak(draft, now);
      if (ok) {
        draft.correct += 1;
        const out = addXp(draft, ex.points, now);
        if (out.goalHit) goalHitRef.current = true;
      } else if (partialXp > 0) {
        const out = addXp(draft, partialXp, now);
        if (out.goalHit) goalHitRef.current = true;
      }
    });

    setQ((prev) => ({
      ...prev,
      checked: true,
      lastOk: ok,
      alreadyResult: true,
      correctN: prev.correctN + (ok ? 1 : 0),
      xp: prev.xp + (ok ? ex.points : partialXp),
    }));

    submittingRef.current = false;
  };

  const check = () => {
    if (q.phase !== "play" || q.checked) return;
    const ex = q.list[q.i];
    // Cadre chapitre (LOT2) : un raté alimente la file de révision en fin de leçon.
    const trackMiss = (ok: boolean) => {
      if (!ok && lessonRef.current && !missedRef.current.includes(ex.id)) {
        missedRef.current.push(ex.id);
      }
      if (!ok) setQ((prev) => ({ ...prev, wrong: [...prev.wrong, { chapter: ex.chapter, module: ex.module }] }));
    };
    // fill_blank, gap et pair : réponse en saisie libre (voir `fillScore`/scoring).
    if (ex.type === "fill_blank" || ex.type === "gap" || ex.type === "pair") {
      const score = fillScore(q.fillVal, ex.answer);
      trackMiss(score === 1);
      submit(score === 1, score === 0.5 ? Math.floor(ex.points / 2) : 0);
      return;
    }
    const ok = evalAnswer(q, ex);
    trackMiss(ok);
    submit(ok);
  };

  /** Question suivante — la session se termine normalement, sans barrière de cœurs. */
  const next = () => {
    const i = q.i + 1;
    if (i >= q.list.length) {
      if (finishedRef.current) return;
      finishedRef.current = true;
      const total = q.list.length;
      const score = total ? Math.round((q.correctN / total) * 100) : 0;
      // Gemmes de leçon (10, +10 si parfaite, 0 si < 50 %) — en fin de session.
      const gems = calculateGemsForLesson(q.correctN, total);
      const now = Date.now();
      let trophies: TrophyId[] = [];
      update((draft) => {
        draft.quizDone += 1;
        if (q.moduleId) {
          const key = lessonKey(q.moduleId, q.level);
          draft.lessonBest[key] = Math.max(draft.lessonBest[key] ?? 0, score);
          draft.moduleBest[q.moduleId] = Math.max(draft.moduleBest[q.moduleId] ?? 0, score);
        } else if (score >= 100) {
          // F9 : quiz libre parfait → pseudo-clé `free` (ignorée par le
          // parcours, exclue de `module_master`) — débloque « Sans faute »
          // depuis le hub de pratique.
          draft.moduleBest.free = 100;
        }
        // Quiz libre : on enregistre aussi le score de niveau sous `quiz:N`
        // pour le déblocage en cascade de la pyramide (UserStatsEntity).
        if (!q.moduleId && q.level >= 1 && q.level <= 6) {
          const key = lessonKey("quiz", q.level);
          draft.lessonBest[key] = Math.max(draft.lessonBest[key] ?? 0, score);
        }
        if (gems > 0) draft.gems += gems;
        earnChest(draft, "quiz", 0);
        // WS4 levier 3 : objectif du jour atteint + cadence de 3 journées
        // consécutives → 1 coffre bonus (récompense variable, non achetable).
        if (
          draft.xpToday >= draft.dailyGoal &&
          draft.dailyGoal > 0 &&
          cadenceBonusChest(goalStreakOf(draft, now))
        ) {
          earnChest(draft, "quiz", 0);
        }
        trophies = claimTrophies(draft, now);
      });
      // Cadres LOT2 (WS2.1) : clôturer la leçon de chapitre (progression +
      // file de révision) ou marquer la révision comme jouée.
      const lesson = lessonRef.current;
      if (lesson) {
        endLesson(lesson.id, score, missedRef.current);
        if (missedRef.current.length > 0) {
          addToReviewQueue({
            lessonId: lesson.id,
            exIds: missedRef.current,
            chapterId: lesson.chapterId,
            moduleId: q.moduleId ?? "",
          });
        }
        lessonRef.current = null;
      }
      if (reviewRef.current) {
        markReviewPlayed(reviewRef.current);
        reviewRef.current = null;
      }
      setReward({ xp: q.xp, gems, goalHit: goalHitRef.current, trophies });
      setQ((prev) => ({ ...prev, phase: "done", i, gems }));
    } else {
      if (advToRef.current === i) return;
      advToRef.current = i;
      setQ((prev) => ({ ...prev, i, ...setupQuestion(prev.list[i]) }));
    }
  };

  /* ---------- écran de choix de niveau — LearningPyramidOverviewScreen ---------- */
  if (q.phase === "start") {
    const pool = quizExercisesForLevel(q.level);
    const count = pool.length;
    return (
      <div className="quiz-start">
        {/* Carte pyramide (gradient #0B4A2E → #146B43 → #15803D) */}
        <div className="card pyramid-hero">
          <b className="pyramid-title">{t("pyramidTitle")}</b>
          <p className="pyramid-desc">{t("pyramidDesc")}</p>
          <div className="pyramid-strip">
            {LEARNING_LEVELS.map((lvl) => {
              const unlocked = isLevelUnlocked(lvl.n, s);
              const score = scoreForLevel(lvl.n, s);
              return (
                <div key={lvl.n} className="pyramid-tier">
                  <span
                    className="pyramid-tier-ico"
                    style={{
                      background:
                        score >= 70 ? "#4CAF50" : unlocked ? "#FFD54F" : "rgba(255,255,255,.2)",
                    }}
                    aria-hidden="true"
                  >
                    {lvl.icon}
                  </span>
                  <span className="pyramid-tier-lbl">L{lvl.n}</span>
                  <span className="pyramid-tier-score">
                    {unlocked ? `${score}%` : "🔒"}
                  </span>
                </div>
              );
            })}
          </div>
        </div>

        <b className="pyramid-list-title">{t("pyramidLevelsLbl")}</b>

        {LEARNING_LEVELS.map((lvl) => {
          const unlocked = isLevelUnlocked(lvl.n, s);
          const score = scoreForLevel(lvl.n, s);
          const mastered = score >= 70;
          return (
            <div
              key={lvl.n}
              className={`level-card ${unlocked ? "" : "locked"} chip-row`}
              style={{ "--lvl-color": lvl.color } as React.CSSProperties}
            >
              <div className="level-card-top">
                <span className="level-ico" aria-hidden="true">
                  {lvl.icon}
                </span>
                <div>
                  <b className="level-title">
                    {t("quizTitle")} {lvl.n} : {t(lvl.key as TKey)}
                  </b>
                  <span className="level-objective">{t(lvl.objectiveKey as TKey)}</span>
                </div>
              </div>
              <div className="level-card-bottom">
                {unlocked ? (
                  mastered ? (
                    <span className="level-status mastered">
                      ✓ {t("levelScoreLbl")}: {score}% ({t("levelValidatedLbl")})
                    </span>
                  ) : (
                    <span className="level-status">
                      {t("levelScoreLbl")}: {score}% ({t("levelRequiredLbl")})
                    </span>
                  )
                ) : (
                  <span className="level-status locked">🔒 {t("levelLockedInfo")}</span>
                )}
                <button
                  type="button"
                  className="level-start chip"
                  style={{ background: unlocked ? lvl.color : "#CBD5E1" }}
                  onClick={() => patch({ level: lvl.n })}
                >
                  ▶ {t("startTraining")}
                </button>
              </div>
            </div>
          );
        })}

        <div className="card quiz-free">
          <p className="muted" style={{ margin: "0 0 14px" }}>
            {count} {t("exCount")} · {Math.min(count, QUIZ_CAP)} {t("exCount")} par session · ⭐{" "}
            {t("statXp")} + 💎 {t("statGems")}
          </p>
          <button
            type="button"
            className="btn block"
            disabled={!count}
            onClick={() => begin(q.level)}
          >
            {t("startQuiz")} →
          </button>
        </div>
      </div>
    );
  }

  /* ---------- récapitulatif ---------- */
  if (q.phase === "done") {
    const total = q.list.length;
    const score = total ? Math.round((q.correctN / total) * 100) : 0;
    const replay = () => {
      if (q.moduleId) beginLesson(q.moduleId, q.level);
      else begin(q.level);
    };
    return (
      <>
        {reward && <RewardPopup data={reward} onContinue={() => setReward(null)} />}
        <div className="summary card" style={{ marginTop: 24 }}>
          <div className="big-num">{score}%</div>
          <div className="big-label">
            {t("scoreLbl")} · {t("doneQuiz")}
          </div>
          <div className="summary-stats">
            <div className="stat-tile">
              <b>
                {q.correctN}/{total}
              </b>
              <span>✅ {t("correctLbl")}</span>
            </div>
            <div className="stat-tile">
              <b>+{q.xp}</b>
              <span>⭐ {t("statXp")}</span>
            </div>
            <div className="stat-tile">
              <b>+{q.gems}</b>
              <span>💎 {t("statGems")}</span>
            </div>
          </div>
          {/* WS5.3 — renvoi EMD : les chapitres les plus ratés, chacun
              renvoyant à l'examen du module pour retravailler. */}
          {q.examEndsAt !== null && q.wrong.length > 0 && (
            <div style={{ marginTop: 12, textAlign: "left" }}>
              <b className="tiny">{t("emdChapters")}</b>
              <ul className="plain" style={{ margin: "4px 0" }}>
                {weakChapters(q.wrong).slice(0, 4).map((w) => (
                  <li key={`${w.module}|${w.chapter}`} className="tiny">
                    📖 {w.chapter} ({modLabel(w.module, lang)}) — {w.count}{" "}
                    {w.count > 1 ? t("emdMistakes") : t("emdMistake")}
                  </li>
                ))}
              </ul>
              {score >= EMD_PASS_SCORE ? (
                <p className="tiny" style={{ color: "#0E8A5F" }}>✅ {t("emdPassed")}</p>
              ) : (
                <p className="tiny" style={{ color: "#B3261E" }}>— {t("emdFailed")} ({score}%)</p>
              )}
            </div>
          )}
          <a className="btn block" href={q.moduleId ? "#/path" : "#/practice"}>
            {t("homeLbl")}
          </a>
          <button
            type="button"
            className="btn ghost block"
            style={{ marginTop: 10 }}
            onClick={replay}
          >
            {t("replay")}
          </button>
        </div>
      </>
    );
  }

  /* ---------- question en cours ---------- */
  const ex = q.list[q.i];
  const isChoice = ex.type === "mcq" || ex.type === "reading" || ex.type === "clinical_case";
  const ctx = ex.type === "reading" || ex.type === "clinical_case" ? ctxText(ex, lang) : "";

  const askExit = async () => {
    const r = await open({
      icon: "👋",
      title: t("quitTitle"),
      text: t("quitText"),
      actions: [
        { id: "stay", label: t("stayLbl") },
        { id: "quit", label: t("quitLbl"), cls: "danger" },
      ],
    });
    if (r === "quit") {
      setQ(IDLE);
      // Quitter une leçon ramène au parcours, pas au choix de niveau (B9) ;
      // cela nettoie aussi la query `?module=…` du hash.
      navigate(q.moduleId ? "path" : "practice");
    }
  };

  let body: React.ReactNode;
  if (isChoice) {
    body = (
      <div className="options" id="opts">
        {q.opts.map((o, i) => (
          <button
            key={`${o}-${i}`}
            type="button"
            className={[
              "option",
              q.sel === i && !q.checked ? "selected" : "",
              q.checked && i === q.answerIdx ? "correct" : "",
              q.checked && i === q.sel && i !== q.answerIdx ? "wrong" : "",
            ]
              .filter(Boolean)
              .join(" ")}
            disabled={q.checked}
            onClick={() => patch({ sel: i })}
          >
            {o}
          </button>
        ))}
      </div>
    );
  } else if (ex.type === "fill_blank" || ex.type === "gap" || ex.type === "pair") {
    body = (
      <>
        <p className="tiny">{t("typeHint")}</p>
        <input
          className="fill-input"
          type="text"
          autoComplete="off"
          disabled={q.checked}
          value={q.fillVal}
          onChange={(e) => patch({ fillVal: e.target.value })}
          onKeyDown={(e) => {
            if (e.key === "Enter" && q.fillVal.trim() && !q.checked) check();
          }}
        />
      </>
    );
  } else if (ex.type === "sentence_order") {
    body = (
      <>
        <p className="tiny">{t("orderHint")}</p>
        <div className="wordbank" id="zone">
          {q.wordSel.length ? (
            q.wordSel.map((bi) => (
              <button
                key={`sel-${bi}`}
                type="button"
                className="word"
                disabled={q.checked}
                onClick={() =>
                  patch({
                    wordSel: q.wordSel.filter((x) => x !== bi),
                    bank: q.bank.map((b, idx) => (idx === bi ? { ...b, used: false } : b)),
                  })
                }
              >
                {q.bank[bi].w}
              </button>
            ))
          ) : (
            <span className="tiny" style={{ alignSelf: "center" }}>
              {t("orderHint")}
            </span>
          )}
        </div>
        <div className="word-bank" id="bank">
          {q.bank.map((b, i) => (
            <button
              key={`bank-${i}`}
              type="button"
              className="word"
              disabled={b.used || q.checked}
              onClick={() =>
                patch({
                  bank: q.bank.map((x, idx) => (idx === i ? { ...x, used: true } : x)),
                  wordSel: [...q.wordSel, i],
                })
              }
            >
              {b.w}
            </button>
          ))}
        </div>
      </>
    );
  } else {
    body = (
      <>
        <p className="tiny">{t("matchHint")}</p>
        <div className="match-grid">
          <div className="match-col" id="colL">
            {q.lefts.map((l) => (
              <button
                key={`l-${l}`}
                type="button"
                className={`match-item ${q.matchDone[l] ? "done" : ""} ${
                  q.matchPick === l ? "picked" : ""
                }`}
                disabled={!!q.matchDone[l] || q.checked}
                onClick={() => patch({ matchPick: q.matchPick === l ? null : l })}
              >
                {l}
              </button>
            ))}
          </div>
          <div className="match-col" id="colR">
            {q.rights.map((r) => {
              const doneLeft = Object.keys(q.matchDone).find((l) => q.matchDone[l] === r);
              return (
                <button
                  key={`r-${r}`}
                  type="button"
                  className={`match-item ${doneLeft ? "done" : ""}`}
                  disabled={!!doneLeft || q.checked}
                  onClick={() => {
                    if (!q.matchPick) return;
                    const pair = q.pairs.find((p) => p.l === q.matchPick);
                    if (pair && pair.r === r) {
                      const done = { ...q.matchDone, [q.matchPick]: pair.r };
                      if (Object.keys(done).length === q.pairs.length) {
                        patch({ matchDone: done, matchPick: null });
                        submit(q.mistakesQ === 0);
                      } else {
                        patch({ matchDone: done, matchPick: null });
                      }
                    } else {
                      patch({ mistakesQ: q.mistakesQ + 1, matchPick: null });
                    }
                  }}
                >
                  {r}
                </button>
              );
            })}
          </div>
        </div>
      </>
    );
  }

  const correctText = ex.type === "matching" ? ex.options.join("  |  ") : ex.answer;
  const exp = expText(ex, lang);
  const canCheck =
    ex.type === "fill_blank" || ex.type === "gap" || ex.type === "pair"
      ? !!q.fillVal.trim()
      : ex.type === "sentence_order"
        ? q.wordSel.length > 0
        : q.sel !== null;

  return (
    <>
      <SessionTop index={q.i} total={q.list.length} onExit={askExit} />
      {/* WS5.3 : chrono EMD — restant, en rouge sous 60 s. */}
      {q.examEndsAt !== null && (
        <ExamCountdown endsAt={q.examEndsAt} onExpire={next} />
      )}
      {ctx && <div className="quiz-ctx">{ctx}</div>}
      <p className="eyebrow">
        {q.moduleId
          ? `${t("lessonLbl")} ${q.level} · ${modLabel(ex.module, lang)} · ⭐${ex.points}`
          : `${t("questionLbl")} · ${modLabel(ex.module, lang)} · ⭐${ex.points}`}
      </p>
      <div className="quiz-q">{qText(ex, lang)}</div>
      {body}

      {q.checked ? (
        <div className={`feedback ${q.lastOk ? "ok" : "ko"}`}>
          <div className="fb-title">
            {q.lastOk ? `✅ ${t("correctLbl")}` : `❌ ${t("wrongLbl")}`}
          </div>
          {!q.lastOk && (
            <div className="fb-exp">
              <b>{t("answerLbl")} :</b> {correctText}
            </div>
          )}
          {exp && (
            <div className="fb-exp" style={{ marginTop: 6 }}>
              <b>{t("explainLbl")} :</b> {exp}
            </div>
          )}
          <button type="button" className="btn block" onClick={next}>
            {t("nextLbl")} →
          </button>
        </div>
      ) : (
        ex.type !== "matching" && (
          <button
            type="button"
            className="btn block"
            style={{ marginTop: 16 }}
            disabled={!canCheck}
            onClick={check}
          >
            {t("checkLbl")}
          </button>
        )
      )}
    </>
  );
}
