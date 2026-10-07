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
import { getExercises, termsOfModule } from "../data/load";
import { modById, type Module } from "../data/modules";
import { norm, shuffleWith } from "../lib/utils";
import { mulberry32, hashSeed } from "./leagues";
import type { Exercise, Term } from "../types";

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
 * Les 8 questions d'une leçon (ordre conservé : difficulté croissante).
 * Déterministe pour un couple (moduleId, level) donné.
 */
export function lessonQuestions(moduleId: string, level: number): Exercise[] {
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
    const kind = plan[gen] ?? "fr_en";
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
      // donnée manquante sur ce terme → on avance d'un terme, même kind
      cursor += 1;
      if (cursor > pool.length * 2) break;
    }
  }

  // 3. sécurité : on ne rend jamais plus de LESSON_SIZE questions
  return out.slice(0, LESSON_SIZE);
}

/** Score d'une leçon (0-100) à partir du nombre de bonnes réponses. */
export function lessonScore(correct: number, total: number): number {
  if (total <= 0) return 0;
  return Math.round((correct / total) * 100);
}
