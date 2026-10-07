import { useSyncExternalStore } from "react";
import type { Lang } from "../types";
import type { Sched } from "./sm2";
import { DAILY_GOAL_OPTIONS } from "./gamification";
import type { Tier } from "./leagues";

/**
 * Store applicatif unique (pattern subscribe + `useSyncExternalStore`).
 *
 * La persistance est localStorage — équivalent web de la base Room côté Android.
 * Aucune fonction ici ne lit l'horloge murale : `now` est passé par l'appelant,
 * ce qui garde les effets de temps reproductibles en test.
 */

export interface AppState {
  lang: Lang;
  gems: number;
  streak: number;
  lastStudy: string;
  xp: number;
  /** XP gagné aujourd'hui (objet de gamification quotidien). */
  xpToday: number;
  /** Jour civil (`todayKey`) auquel appartient `xpToday`. */
  goalDate: string;
  /** Objectif quotidien en XP : 20 | 50 | 100. */
  dailyGoal: number;
  /** Congélations de série restantes (achat 200 💎). */
  freezeCount: number;
  /** XP des 7 derniers jours, indexé par jour clé → pour la ligue hebdo. */
  weeklyXp: Record<string, number>;
  /** Caisse(s) gagnée(s) et non encore ouverte(s). */
  pendingChests: number;
  /** Nombre total de caisses ouvertes (trophée). */
  chestsOpened: number;
  /** Meilleur % par module (affichage d'unité, trophée `module_master`). */
  moduleBest: Record<string, number>;
  /** Meilleur % par leçon, clé `lessonKey(moduleId, level)` → déblocage du parcours. */
  lessonBest: Record<string, number>;
  /** Trophées obtenus : id → horodatage de déblocage. */
  trophies: Record<string, number>;
  /** État de la ligue hebdomadaire. */
  league: LeagueState;
  answered: number;
  correct: number;
  flashReviewed: number;
  quizDone: number;
  sm2: Record<string, Sched>;
  introSeen: boolean;
  dataVersion: string;
}

/** Ligue hebdomadaire : palier courant, début de semaine (jour clé), graine des bots. */
export interface LeagueState {
  tier: "bronze" | "silver" | "gold";
  weekStart: string;
  botSeed: number;
}

/** Champs d'un ancien store qui ne sont plus écrits (cœurs et flashSuccess supprimés). */
const DROPPED_KEYS = ["hearts", "heartsTs", "flashSuccess"];

const STORAGE_KEY = "medlingo_web_v1";

/**
 * Les ids de termes web sont désormais ceux des seeds Android, et non un
 * numérotage propre au site.
 *
 * Un `sm2` enregistré avec l'ancien numérotage attribuerait l'historique de révision au
 * mauvais terme — une corruption silencieuse. Le jeu de données étant généré (jamais
 * versionné), l'ancien mapping est irrécupérable : on ne peut pas le reconstruire sans
 * risquer de décaler l'historique. On conserve donc les statistiques du joueur et on
 * jette uniquement ce qui est indexé par id.
 *
 * Incrémenter cette valeur pour purger `sm2` au prochain chargement.
 */
export const TERM_DATA_VERSION = "android-ids-v1";

function detectLang(): Lang {
  const nav =
    typeof navigator !== "undefined" && navigator.language
      ? navigator.language.toLowerCase()
      : "fr";
  if (nav.startsWith("ar")) return "ar";
  if (nav.startsWith("en")) return "en";
  return "fr";
}

export function makeDefaults(_now: number): AppState {
  return {
    lang: detectLang(),
    gems: 0,
    streak: 0,
    lastStudy: "",
    xp: 0,
    xpToday: 0,
    goalDate: "",
    dailyGoal: 20,
    freezeCount: 0,
    weeklyXp: {},
    pendingChests: 0,
    chestsOpened: 0,
    moduleBest: {},
    lessonBest: {},
    trophies: {},
    league: { tier: "bronze", weekStart: "", botSeed: 1 },
    answered: 0,
    correct: 0,
    flashReviewed: 0,
    quizDone: 0,
    sm2: {},
    introSeen: false,
    dataVersion: TERM_DATA_VERSION,
  };
}

function storage(): Storage | null {
  try {
    return typeof localStorage !== "undefined" ? localStorage : null;
  } catch {
    return null; // stockage indisponible (navigateur privé, environnement de test)
  }
}

/** Persiste l'état — échec silencieux (quota dépassé, stockage plein). */
function persist(): void {
  try {
    storage()?.setItem(STORAGE_KEY, JSON.stringify(state));
  } catch {
    /* quota/accès refusé : l'état en mémoire reste la source de vérité */
  }
}

/* ---------- assainissement du store rechargé (B6) ---------- */

const LANGS: readonly Lang[] = ["ar", "en", "fr"];
const TIERS: readonly Tier[] = ["bronze", "silver", "gold"];

function coerceNum(v: unknown, fallback: number): number {
  return typeof v === "number" && Number.isFinite(v) ? v : fallback;
}

function coerceRecord(v: unknown): Record<string, unknown> {
  return v && typeof v === "object" && !Array.isArray(v) ? (v as Record<string, unknown>) : {};
}

function coerceNumMap(v: unknown): Record<string, number> {
  const out: Record<string, number> = {};
  for (const [k, n] of Object.entries(coerceRecord(v))) {
    if (typeof n === "number" && Number.isFinite(n)) out[k] = n;
  }
  return out;
}

/** Champs scolaires (SM-2) : structure minimale requise par `sm2Next`. */
function coerceSm2(v: unknown): Record<string, Sched> {
  const out: Record<string, Sched> = {};
  for (const [k, s] of Object.entries(coerceRecord(v))) {
    if (!s || typeof s !== "object") continue;
    const o = s as Record<string, unknown>;
    if (
      typeof o.rep === "number" &&
      typeof o.ef === "number" &&
      typeof o.iv === "number" &&
      typeof o.due === "number" &&
      Number.isFinite(o.ef) &&
      Number.isFinite(o.due)
    ) {
      out[k] = { rep: o.rep, ef: o.ef, iv: o.iv, due: o.due };
    }
  }
  return out;
}

/**
 * Reconstitue l'état depuis une chaîne brute. Isolé de `load()` pour être testable :
 * le store est instancié au chargement du module, ce qui le rend inatteignable depuis
 * un test.
 */
export function revive(raw: string | null, now: number): AppState {
  if (!raw) return makeDefaults(now);
  try {
    const parsed = JSON.parse(raw) as Partial<AppState> & Record<string, unknown> | null;
    if (parsed && typeof parsed === "object") {
      // Les champs retirés (cœurs) ne doivent pas survivre dans l'état ni être
      // réécrits en localStorage au prochain `update()`.
      for (const key of DROPPED_KEYS) delete parsed[key];

      const base = makeDefaults(now);
      const league = coerceRecord(parsed.league);
      const merged: AppState = {
        ...base,
        ...parsed,
        // Enums : valeur hors liste → défaut (store corrompu ou ancien schéma).
        lang: LANGS.includes(parsed.lang as Lang) ? (parsed.lang as Lang) : base.lang,
        dailyGoal: DAILY_GOAL_OPTIONS.includes(parsed.dailyGoal as (typeof DAILY_GOAL_OPTIONS)[number])
          ? (parsed.dailyGoal as number)
          : base.dailyGoal,
        introSeen: parsed.introSeen === true,
        // Nombres : NaN/Infinity/chaîne casseraient les calculs d'XP.
        gems: coerceNum(parsed.gems, base.gems),
        streak: coerceNum(parsed.streak, base.streak),
        xp: coerceNum(parsed.xp, base.xp),
        xpToday: coerceNum(parsed.xpToday, base.xpToday),
        freezeCount: coerceNum(parsed.freezeCount, base.freezeCount),
        pendingChests: coerceNum(parsed.pendingChests, base.pendingChests),
        chestsOpened: coerceNum(parsed.chestsOpened, base.chestsOpened),
        answered: coerceNum(parsed.answered, base.answered),
        correct: coerceNum(parsed.correct, base.correct),
        flashReviewed: coerceNum(parsed.flashReviewed, base.flashReviewed),
        quizDone: coerceNum(parsed.quizDone, base.quizDone),
        // Dates/textes : doivent rester des chaînes (comparaisons lexicographiques).
        lastStudy: typeof parsed.lastStudy === "string" ? parsed.lastStudy : base.lastStudy,
        goalDate: typeof parsed.goalDate === "string" ? parsed.goalDate : base.goalDate,
        // Maps : objets plats, entrées invalides écartées.
        sm2: coerceSm2(parsed.sm2),
        weeklyXp: coerceNumMap(parsed.weeklyXp),
        moduleBest: coerceNumMap(parsed.moduleBest),
        lessonBest: coerceNumMap(parsed.lessonBest),
        trophies: coerceNumMap(parsed.trophies),
        league: {
          tier: TIERS.includes(league.tier as Tier) ? (league.tier as Tier) : base.league.tier,
          weekStart: typeof league.weekStart === "string" ? league.weekStart : base.league.weekStart,
          botSeed: coerceNum(league.botSeed, base.league.botSeed),
        },
      };
      // Historique indexé par id : sans correspondance avec le numérotage courant,
      // il ne veut plus rien dire. On le purge plutôt que de l'attribuer au hasard.
      if (parsed.dataVersion !== TERM_DATA_VERSION) merged.sm2 = {};
      merged.dataVersion = TERM_DATA_VERSION;
      return merged;
    }
  } catch {
    /* store corrompu → on repart des défauts */
  }
  return makeDefaults(now);
}

function load(): AppState {
  return revive(storage()?.getItem(STORAGE_KEY) ?? null, Date.now());
}

let state: AppState = load();
const listeners = new Set<() => void>();

function emit(): void {
  listeners.forEach((l) => l());
}

export function getState(): AppState {
  return state;
}

export function subscribe(fn: () => void): () => void {
  listeners.add(fn);
  return () => {
    listeners.delete(fn);
  };
}

/**
 * Applique une recette sur une copie, persiste et notifie.
 * Les structures mutables (SM-2, maps de progression, ligue) sont copiées :
 * les recettes ne modifient jamais l'objet rendu précédemment.
 */
export function update(recipe: (draft: AppState) => void): void {
  const draft: AppState = {
    ...state,
    sm2: { ...state.sm2 },
    weeklyXp: { ...state.weeklyXp },
    moduleBest: { ...state.moduleBest },
    lessonBest: { ...state.lessonBest },
    trophies: { ...state.trophies },
    league: { ...state.league },
  };
  recipe(draft);
  state = draft;
  persist();
  emit();
}

/** Remet la progression à zéro en conservant la langue et l'intro vue. */
export function resetProgress(now: number): void {
  const keepLang = state.lang;
  const keepIntro = state.introSeen;
  state = { ...makeDefaults(now), lang: keepLang, introSeen: keepIntro };
  persist();
  emit();
}

export function useAppState(): AppState {
  return useSyncExternalStore(subscribe, getState, getState);
}
