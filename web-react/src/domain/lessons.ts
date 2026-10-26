/**
 * Générateur de leçons — 44 exercices réels pour 90 nœuds du parcours.
 *
 * Règle (spec §7) : une leçon = 8 questions
 *   1. les exercices réels du (module, niveau), au plus 4 ;
 *   2. le reste est généré depuis `termsOfModule(moduleId)` :
 *      L1/L2 → QCM EN→FR, EN→AR, FR→EN · L3/L4 → définition/étymologie + appariement
 *      L5 → lecture (contexte) sinon définition · L6 → cas clinique sinon traduction
 *   3. difficulté croissante : d'abord des distracteurs éloignés, puis proches.
 *
 * Tirage déterministe (seed = module:niveau) → même leçon à chaque visite.
 */
import { getExercises, termsOfModule, termById, getLessonMap } from "../data/load";
import { modById, type Module } from "../data/modules";
import { norm, shuffleWith } from "../lib/utils";
import { mulberry32, hashSeed } from "./leagues";
import { generateBulkExercises, resetBulkCache, warmBulkCache } from "./bulkExercises";
import type { Exercise, Term } from "../types";
import { onDataChangedReview, type LessonMap } from "../data/load";

/** Questions par leçon. */
export const LESSON_SIZE = 8;
/** Exercices réels réutilisés au maximum dans une leçon. */
export const REAL_EXERCISES_MAX = 4;
/** Paires d'un exercice d'appariement. */
const MATCH_PAIRS = 4;

type Kind = "en_fr" | "en_ar" | "fr_en" | "def" | "etym" | "reading" | "clinical" | "match";

/** Ressemblance de Jaccard sur les bigrammes (0 → 1). */
export function similarity(a: string, b: string): number {
  const grams = (s: string): Set<string> => {
    const t = norm(s).replace(/\s+/g, "");
    const out = new Set<string>();
    for (let i = 0; i < t.length - 1; i++) out.add(t.slice(i, i + 2));
    return out;
  };
  const A = grams(a);
  const B = grams(b);
  if (!A.size || !B.size) return 0;
  let shared = 0;
  for (const g of A) if (B.has(g)) shared += 1;
  return (2 * shared) / (A.size + B.size);
}

/** Plan des kinds générés pour un niveau : difficulty croissante par index. */
export function lessonPlan(level: number, count: number): Kind[] {
  const cycle = (kinds: Kind[]): Kind[] =>
    Array.from({ length: count }, (_, i) => kinds[i % kinds.length]);

  if (level <= 2) return cycle(["en_fr", "en_ar", "fr_en"]);
  if (level <= 4) {
    return Array.from({ length: count }, (_, i) =>
      i % 3 === 1 ? "match" : i % 3 === 2 ? "etym" : "def"
    );
  }
  if (level === 5) return cycle(["reading", "def"]);
  return cycle(["clinical", "fr_en"]);
}

/** 3 distracteurs du même module : proches pour les questions tardives. */
function distractors(
  answer: string,
  pool: string[],
  rand: () => number,
  difficulty: number
): string[] {
  const scored = pool
    .filter((c) => norm(c) && norm(c) !== norm(answer))
    .map((c) => ({ c, s: similarity(answer, c) }))
    .sort((a, b) => b.s - a.s); // proches d'abord

  // difficulté 0 → banque la plus éloignée ; 1 → la plus proche
  const size = Math.min(8, scored.length);
  const pickFrom = difficulty < 0.5 ? scored.slice(scored.length - size) : scored.slice(0, size);
  const out: string[] = [];
  const used = new Set([norm(answer)]);
  while (out.length < 3 && pickFrom.length) {
    const idx = Math.floor(rand() * pickFrom.length);
    const candidate = pickFrom.splice(idx, 1)[0].c;
    if (used.has(norm(candidate))) continue;
    used.add(norm(candidate));
    out.push(candidate);
  }
  // repli : si la tranche était trop pauvre, on prend le reste des candidats
  if (out.length < 3) {
    for (const { c } of scored) {
      if (out.length === 3) break;
      if (used.has(norm(c))) continue;
      used.add(norm(c));
      out.push(c);
    }
  }
  return out;
}

function base(
  mod: Module,
  term: Term,
  level: number,
  id: number
): Omit<Exercise, "type" | "qEn" | "qFr" | "qAr" | "options" | "answer"> {
  return {
    id,
    level,
    module: mod.fr,
    chapter: term.chapter,
    expEn: term.defEn,
    expFr: term.defFr,
    expAr: term.defAr,
    // Table de référence Android : L1..L6 → 10 / 15 / 20 / 25 / 30 / 35 XP.
    points: 10 + (level - 1) * 5,
    ctxEn: term.exEn,
    ctxFr: term.exFr,
    ctxAr: term.exAr,
  };
}

/** Construit une question générée. Renvoie `null` si la donnée manque (fallback). */
function build(
  kind: Kind,
  mod: Module,
  term: Term,
  level: number,
  id: number,
  others: Term[],
  rand: () => number,
  difficulty: number
): Exercise | null {
  const b = base(mod, term, level, id);
  // distracteurs dans la même langue que la réponse attendue
  const field = (t: Term): string =>
    kind === "en_fr" ? t.fr : kind === "en_ar" ? t.ar : t.en;
  const pool = others.map(field);

  switch (kind) {
    case "en_fr":
      return {
        ...b,
        type: "mcq",
        qEn: `What is the French translation of "${term.en}"?`,
        qFr: `Quelle est la traduction française de « ${term.en} » ?`,
        qAr: `ما الترجمة الفرنسية للمصطلح « ${term.en} »؟`,
        options: shuffleWith([term.fr, ...distractors(term.fr, pool, rand, difficulty)], rand),
        answer: term.fr,
      };
    case "en_ar":
      if (!term.ar) return null;
      return {
        ...b,
        type: "mcq",
        qEn: `What is the Arabic translation of "${term.en}"?`,
        qFr: `Quelle est la traduction arabe de « ${term.en} » ?`,
        qAr: `ما الترجمة العربية للمصطلح « ${term.en} »؟`,
        options: shuffleWith([term.ar, ...distractors(term.ar, pool, rand, difficulty)], rand),
        answer: term.ar,
      };
    case "fr_en":
      return {
        ...b,
        type: "mcq",
        qEn: `Which English term matches "${term.fr}"?`,
        qFr: `Quel est le terme anglais pour « ${term.fr} » ?`,
        qAr: `ما المصطلح الإنجليزي المقابل لـ« ${term.fr} »؟`,
        options: shuffleWith([term.en, ...distractors(term.en, pool, rand, difficulty)], rand),
        answer: term.en,
      };
    case "def":
      if (!term.defFr && !term.defEn) return null;
      return {
        ...b,
        type: "mcq",
        qEn: term.defEn || term.defFr,
        qFr: term.defFr || term.defEn,
        qAr: term.defAr || term.defEn,
        options: shuffleWith([term.en, ...distractors(term.en, pool, rand, difficulty)], rand),
        answer: term.en,
      };
    case "etym":
      if (!term.etym) return null;
      return {
        ...b,
        type: "mcq",
        qEn: `Which term comes from: ${term.etym}?`,
        qFr: `Quel terme vient de : ${term.etym} ?`,
        qAr: `ما المصطلح الذي مشتق من: ${term.etym}؟`,
        options: shuffleWith([term.en, ...distractors(term.en, pool, rand, difficulty)], rand),
        answer: term.en,
      };
    case "reading":
      if (!term.exEn) return null;
      return {
        ...b,
        type: "reading",
        qEn: "Which term fits this context?",
        qFr: "Quel terme correspond à ce contexte ?",
        qAr: "ما المصطلح الذي يناسب هذا السياق؟",
        ctxEn: term.exEn,
        ctxFr: term.exFr || term.exEn,
        ctxAr: term.exAr || term.exEn,
        options: shuffleWith([term.en, ...distractors(term.en, pool, rand, difficulty)], rand),
        answer: term.en,
      };
    case "clinical":
      if (!term.exEn && !term.pearl) return null;
      return {
        ...b,
        type: "clinical_case",
        qEn: "Identify the term used in this clinical context.",
        qFr: "Identifiez le terme employé dans ce contexte clinique.",
        qAr: "حدّد المصطلح المستخدم في هذا السياق السريري.",
        ctxEn: term.exEn || term.pearl,
        ctxFr: term.exFr || term.pearl,
        ctxAr: term.exAr || term.pearl,
        options: shuffleWith([term.en, ...distractors(term.en, pool, rand, difficulty)], rand),
        answer: term.en,
      };
    case "match": {
      const terms = [term, ...others.slice(0, MATCH_PAIRS - 1)];
      if (terms.length < MATCH_PAIRS) return null;
      const options = terms.map((t) => `${t.en}:${t.fr}`);
      return {
        ...b,
        type: "matching",
        qEn: "Match each term with its French translation.",
        qFr: "Associez chaque terme à sa traduction française.",
        qAr: "صِل كل مصطلح بترجمته الفرنسية.",
        options,
        answer: options.join("  |  "),
      };
    }
    default:
      return null;
  }
}

/**
/** Exercices jouables au quiz : le corpus écrit main (112) + les exercices
 * générés (13 512 = 3 par terme × 4 504 termes).
 *
 * Les exercices générés sont produits à la demande depuis `terms.json` ; les
 * 112 exercices écrits main restent prioritaires (ils couvrent les types rares
 * comme `clinical_case` manuel).
 *
 * **Mémoïsé** : `generateBulkExercises()` coûte ~8 s ; sans cache, chaque
 * render de l'écran quiz (le compteur « N exercices » appelle
 * `quizExercisesForLevel`) relançait la génération complète et gelait l'UI.
 */
let quizPoolCache: Exercise[] | null = null;

export function quizExercises(): Exercise[] {
  if (!quizPoolCache) quizPoolCache = [...getExercises(), ...generateBulkExercises()];
  return quizPoolCache;
}

/** Même pool, filtré par niveau (L1..L6). Mémoïsé par niveau. */
const levelPoolCache = new Map<number, Exercise[]>();

export function quizExercisesForLevel(level: number): Exercise[] {
  let pool = levelPoolCache.get(level);
  if (!pool) {
    pool = quizExercises().filter((e) => e.level === level);
    levelPoolCache.set(level, pool);
  }
  return pool;
}

/**
 * Les 8 questions d'une leçon (ordre conservé : difficulté croissante).
 * Déterministe pour un couple (moduleId, level) donné — donc mémoïsé :
 * `PathScreen` peut redemander la même leçon à chaque render.
 */
const lessonCache = new Map<string, Exercise[]>();

export function lessonQuestions(moduleId: string, level: number): Exercise[] {
  const cacheKey = `${moduleId}:${level}`;
  const hit = lessonCache.get(cacheKey);
  if (hit) return hit;
  const mod = modById(moduleId);
  if (!mod || level < 1 || level > 6) return [];

  const rand = mulberry32(hashSeed(`${moduleId}:${level}`));
  const terms = termsOfModule(moduleId);
  if (!terms.length) return [];

  // 1. exercices réels du (module, niveau)
  const real = getExercises()
    .filter((e) => e.module === mod.fr && e.level === level)
    .slice(0, REAL_EXERCISES_MAX);

  // 2. complétion générée
  const needed = Math.max(0, LESSON_SIZE - real.length);
  const plan = lessonPlan(level, needed);
  const pool = shuffleWith(terms.slice(), rand);

  const out: Exercise[] = [...real];
  let cursor = 0;
  let gen = 0;

  while (out.length < LESSON_SIZE && gen < needed * 3) {
    const kind = plan[gen % plan.length] ?? "fr_en";
    const term = pool[cursor % pool.length];
    const others = pool.filter((t) => t.id !== term.id);
    const id = -1_000_000 - (hashSeed(`${moduleId}:${level}:${gen}`) % 8_000_000);
    const difficulty = needed > 1 ? gen / (needed - 1) : 1;

    const ex = build(kind, mod, term, level, id, others, rand, difficulty);
    if (ex) {
      out.push(ex);
      gen += 1;
      cursor += kind === "match" ? MATCH_PAIRS : 1;
    } else {
      // donnée manquante (ar/déf/étym absent) → on essaie le kind suivant,
      // sans bloquer la leçon (et sans ré-utiliser le mêem générateur → id unique)
      gen += 1;
      cursor += 1;
    }
  }

  // 3. sécurité : on ne rend jamais plus de LESSON_SIZE questions
  const out2 = out.slice(0, LESSON_SIZE);
  lessonCache.set(cacheKey, out2);
  return out2;
}

/* ------------------------------------------------------------------ */
/* Nouveau : leçons par chapitre (3 termes)                            */
/* ------------------------------------------------------------------ */

/**
 * Générateur de leçon NEW.
 *
 * Règles :
 *  - 3 termes par leçon (strict)
 *  - 8 questions
 *  - déterministe (seed = chapterId:levelIndex)
 *  - tiré des `lesson_map.json` (termIds pré-chosés)
 */
export function newLesson(
  chapterId: string,
  levelIndex: number
): Exercise[] {
  const map = getLessonMap();
  const entry = map[`${chapterId}-l${levelIndex + 1}`];
  if (!entry || entry.kind !== "new") return [];
  const termIds = entry.termIds;
  if (!termIds || termIds.length < 3) return [];

  const terms = termIds.map((id) => termById(id)).filter(Boolean) as Term[];
  if (terms.length < 3) return [];

  // Mélange déterministe à partir des termes choisis
  const rand = mulberry32(hashSeed(`${chapterId}:${levelIndex}`));
  const pool = shuffleWith(terms.slice(), rand);

  const out: Exercise[] = [];
  const ids = new Set<number>();
  const freshId = (): number => {
    let id = -5_000_000 - out.length;
    while (ids.has(id)) id -= 1;
    ids.add(id);
    return id;
  };
  const sameLang = (t: Term, f: "fr" | "ar" | "en"): string =>
    f === "fr" ? t.fr : f === "ar" ? t.ar : t.en;

  const buildMcq = (term: Term, answer: string, field: "fr" | "ar" | "en"): Exercise | null => {
    if (!answer) return null;
    // 4 options distinctes : la réponse + 3 distracteurs du même champ
    const others = pool.filter((t) => t !== term).map((t) => sameLang(t, field));
    const options = shuffleWith([answer, ...distractors(answer, others, rand, 0.5)], rand);
    while (options.length < 4) options.push(sameLang(pool[0] ?? term, field));
    return {
      id: freshId(),
      level: levelIndex + 1,
      module: term.module,
      chapter: term.chapter,
      expEn: term.defEn,
      expFr: term.defFr,
      expAr: term.defAr,
      points: 10 + levelIndex * 5,
      ctxEn: term.exEn,
      ctxFr: term.exFr,
      ctxAr: term.exAr,
      type: "mcq",
      qEn: `What is the French translation of "${term.en}"?`,
      qFr: `Quelle est la traduction française de « ${term.en} » ?`,
      qAr: `ما الترجمة الفرنسية للمصطلح « ${term.en} »؟`,
      options,
      answer,
    };
  };

  const buildDef = (term: Term): Exercise | null => {
    const def = term.defFr || term.defEn;
    if (!def) return null;
    const others = pool.filter((t) => t !== term).map((t) => sameLang(t, "en"));
    const options = shuffleWith([term.en, ...distractors(term.en, others, rand, 0.5)], rand);
    while (options.length < 4) options.push(term.en);
    return {
      id: freshId(),
      level: levelIndex + 1,
      module: term.module,
      chapter: term.chapter,
      expEn: term.defEn,
      expFr: term.defFr,
      expAr: term.defAr,
      points: 10 + levelIndex * 5,
      ctxEn: term.exEn,
      ctxFr: term.exFr,
      ctxAr: term.exAr,
      type: "mcq",
      qEn: def,
      qFr: def,
      qAr: term.defAr || def,
      options,
      answer: term.en,
    };
  };

  for (const term of pool) {
    if (out.length >= 8) break;
    const mcqFr = buildMcq(term, term.fr, "fr");
    if (mcqFr) out.push(mcqFr);
    if (term.ar && out.length < 8) {
      const mcqAr = buildMcq(term, term.ar, "ar");
      if (mcqAr) out.push(mcqAr);
    }
    if ((term.defFr || term.defEn) && out.length < 8) {
      const def = buildDef(term);
      if (def) out.push(def);
    }
  }

  // Compléter jusqu'à 8 questions si nécessaire
  let i = 0;
  while (out.length < 8) {
    const term = pool[i % pool.length];
    if (!term) break;
    const mcqFr = buildMcq(term, term.fr, "fr");
    if (mcqFr) out.push(mcqFr);
    i += 1;
  }

  // Sécurité : jamais plus de 8 questions
  return out.slice(0, 8);
}

/**
 * Générateur de leçon REVIEW.
 *
 * Règles :
 *  - 0 termes (revue d'exercices ratés)
 *  - 16 questions
 *  - tiré des exercices des leçons référencées (`reviewedLessonIds`)
 *  - priorité aux exercices ratés (missés d'abord)
 */
export function reviewLesson(
  chapterId: string,
  reviewIndex: number
): Exercise[] {
  const lessonMap = getLessonMap();
  const entry = lessonMap[`${chapterId}-l${reviewIndex + 1}`];
  if (!entry || entry.kind !== "review") return [];

  const reviewedIds = entry.reviewedLessonIds || [];
  if (reviewedIds.length < 2) return [];

  // Mélange déterministe pour les options et la sélection de la leçon
  const rand = mulberry32(hashSeed(`${chapterId}:review:${reviewIndex}`));

  // Récupérer les exercices des leçons révisées
  const pool: Exercise[] = [];
  for (const refId of reviewedIds) {
    const refEntry = lessonMap[refId];
    if (refEntry && refEntry.kind === "new") {
      const terms = refEntry.termIds.map((id) => termById(id)).filter(Boolean);
      for (const term of terms) {
    if (!term) continue;
        // Ajouter 3 exercices par terme (partiel mock)
        for (let i = 0; i < 3; i++) {
          pool.push({
            id: -3_000_000 - (term.id * 3 + i),
            level: reviewIndex + 1,
            module: term.module,
            chapter: term.chapter,
            expEn: term.defEn,
            expFr: term.defFr,
            expAr: term.defAr,
            points: 15 + reviewIndex,
            ctxEn: term.exEn,
            ctxFr: term.exFr,
            ctxAr: term.exAr,
            type: "mcq",
            qEn: `Review: ${term.en}`,
            qFr: `Aide: ${term.fr}`,
            qAr: `مساعدة: ${term.ar}`,
            options: shuffleWith([term.fr, term.ar, term.en], rand),
            answer: term.fr,
          });
        }
      }
    }
  }

  // Sélection 16 sur 18 (missés d'abord, puis reste)
  // Contrôle : si moins de 16 exercices disponibles, on complète avec les mêmes (differentes questions)
  const needed = 16;
  if (pool.length <= needed) {
    return pool.slice(0, needed);
  }

  const shuffled = shuffleWith(pool.slice(), rand);

  return shuffled.slice(0, needed);
}

/* ------------------------------------------------------------------ */
/* Invalidation + préchauffe                                           */
/* ------------------------------------------------------------------ */

/** Vide tous les caches dérivés des données (branchement de `terms.json`). */
export function resetLessonCaches(): void {
  quizPoolCache = null;
  levelPoolCache.clear();
  lessonCache.clear();
  resetBulkCache();
  resetReviewCaches();
}

/** Abonnés à prévenir quand les données sont (re)branchées. */

let reviewCache = new Map<string, Exercise[]>();

export function getReview(entryId: string): Exercise[] | null {
  const hit = reviewCache.get(entryId);
  if (hit) return hit;
  const entry = getLessonMap()[entryId];
  if (!entry || entry.kind !== "review") return null;
  return buildReview(entry);
}

function buildReview(entry: LessonMap["review"]): Exercise[] {
  const reviewedIds = entry.reviewedLessonIds || [];
  if (reviewedIds.length < 2) return [];
  const rand = mulberry32(hashSeed(`${entry.id}:review`));
  // Pool mock : 18 exos (9 de L1 + 9 de L2) — 3 exos/terme
  const pool: Exercise[] = [];
  for (const refId of reviewedIds) {
    const refEntry = getLessonMap()[refId];
    if (refEntry && refEntry.kind === "new") {
      const terms = refEntry.termIds.map((id) => termById(id)).filter(Boolean);
      for (const term of terms) {
    if (!term) continue;
        // Ajouter 3 exercices par terme (partiel mock)
        for (let i = 0; i < 3; i++) {
          pool.push({
            id: -3_000_000 - (term.id * 3 + i),
            level: 1,
            module: term.module,
            chapter: term.chapter,
            expEn: term.defEn,
            expFr: term.defFr,
            expAr: term.defAr,
            points: 15,
            ctxEn: term.exEn,
            ctxFr: term.exFr,
            ctxAr: term.exAr,
            type: "mcq",
            qEn: `Review: ${term.en}`,
            qFr: `Aide: ${term.fr}`,
            qAr: `مساعدة: ${term.ar}`,
            options: shuffleWith([term.fr, term.ar, term.en].slice(0, 4), rand),
            answer: term.fr,
          });
        }
      }
    }
  }
  const needed = 16;
  if (pool.length <= needed) return pool.slice(0, needed);
  return shuffleWith(pool.slice(), rand).slice(0, needed);
}

function resetReviewCaches(): void {
  reviewCache.clear();
}

onDataChangedReview(resetReviewCaches);

/**
 * Préchauffe le corpus en arrière-plan (module par module, sans bloquer le
 * thread principal). Appelé après `loadOnce()` : le premier quiz est alors
 * instantané au lieu de payer ~8 s de génération au clic.
 */
export function warmQuizPool(): void {
  warmBulkCache();
}

/** Score d'une leçon (0-100) à partir du nombre de bonnes réponses. */
export function lessonScore(correct: number, total: number): number {
  if (total <= 0) return 0;
  return Math.round((correct / total) * 100);
}
