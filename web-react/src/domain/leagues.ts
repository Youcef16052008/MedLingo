/**
 * Ligues hebdomadaires — miroir du `LeagueManager` Android.
 * Cohorte de 30 : top 10 promu, bottom 5 relégué. Bots à XP déterministe
 * (PRNG à graine `botSeed` + semaine) → reproductible en test.
 */
import type { Lang } from "../types";
import { todayKey } from "./gamification";

export const COHORT_SIZE = 30;
export const PROMOTION = 10;
export const DEMOTION = 5;

export type Tier = "bronze" | "silver" | "gold";

export const TIER_ORDER: Tier[] = ["bronze", "silver", "gold"];

export const TIER_ICONS: Record<Tier, string> = {
  bronze: "🥉",
  silver: "🥈",
  gold: "🥇",
};

/** Joueur local (miroir de l'identité affichée dans le classement). */
export const YOU = { name: "TOI", avatar: "🩺" };

/** 29 partenaires de cohorte (noms DZ, miroir de `LeagueManager.kt`). */
export const BOT_NAMES: string[] = [
  "Amine", "Yacine", "Sofiane", "Meriem", "Lina", "Nassim", "Imene",
  "Karim", "Sara", "Walid", "Riad", "Nawel", "Toufik", "Hakim", "Amel",
  "Zineb", "Bilal", "Yasmine", "Islam", "Ryma", "Adel", "Chaima", "Mehdi",
  "Feriel", "Oussama", "Hanen", "Djalal", "Souhila", "Mourad",
];

const BOT_AVATARS = ["🦊", "🐼", "🦉", "🐸", "🐵", "🦁", "🐯", "🐨", "🦄", "🐧"];

export interface LeagueRow {
  id: string;
  name: string;
  avatar: string;
  xp: number;
  isYou: boolean;
  rank: number;
}

export interface LeagueInfo {
  tier: Tier;
  weekStart: string;
  botSeed: number;
}

/** PRNG déterministe (mulberry32). */
export function mulberry32(seed: number): () => number {
  let a = seed >>> 0;
  return () => {
    a = (a + 0x6d2b79f5) >>> 0;
    let t = a;
    t = Math.imul(t ^ (t >>> 15), t | 1);
    t ^= t + Math.imul(t ^ (t >>> 7), t | 61);
    return ((t ^ (t >>> 14)) >>> 0) / 4_294_967_296;
  };
}

/** Graine stable à partir d'une chaîne (seed + semaine). */
export function hashSeed(text: string): number {
  let h = 2_166_136_261;
  for (let i = 0; i < text.length; i++) {
    h ^= text.charCodeAt(i);
    h = Math.imul(h, 16_777_619);
  }
  return h >>> 0;
}

/** Lundi 00:00 de la semaine contenant `now`, sous forme de jour clé. */
export function weekStartKey(now: number): string {
  const d = new Date(now);
  const day = (d.getDay() + 6) % 7; // lun = 0
  d.setHours(0, 0, 0, 0);
  d.setDate(d.getDate() - day);
  return todayKey(d.getTime());
}

/** XP hebdomadaire du joueur (fenêtre depuis `weekStart`). */
export function userWeekXp(weeklyXp: Record<string, number>, weekStart: string): number {
  let sum = 0;
  for (const [key, value] of Object.entries(weeklyXp)) {
    if (key >= weekStart) sum += value;
  }
  return sum;
}

/** Classement de la cohorte : le joueur + 29 bots, trié par XP. */
export function buildStandings(
  league: LeagueInfo,
  weeklyXp: Record<string, number>,
  you: { name: string; avatar: string; xp?: number }
): LeagueRow[] {
  const rows: LeagueRow[] = [
    {
      id: "you",
      name: you.name,
      avatar: you.avatar,
      xp: you.xp ?? userWeekXp(weeklyXp, league.weekStart),
      isYou: true,
      rank: 0,
    },
  ];

  // XP bot : déterministe (seed + semaine + index), calibré par palier.
  const ranges: Record<Tier, [number, number]> = {
    bronze: [20, 260],
    silver: [80, 460],
    gold: [160, 820],
  };
  const [min, max] = ranges[league.tier];

  BOT_NAMES.forEach((name, i) => {
    const rand = mulberry32(hashSeed(`${league.botSeed}:${league.weekStart}:${i}`));
    const xp = Math.round(min + rand() * (max - min));
    rows.push({
      id: `bot${i}`,
      name,
      avatar: BOT_AVATARS[i % BOT_AVATARS.length],
      xp,
      isYou: false,
      rank: 0,
    });
  });

  rows.sort((a, b) => b.xp - a.xp || a.name.localeCompare(b.name));
  rows.forEach((r, i) => (r.rank = i + 1));
  return rows;
}

/** Zone de classement : "green" (top 10), "red" (bottom 5) ou neutre. */
export function zoneOf(rank: number): "green" | "red" | "neutral" {
  if (rank <= PROMOTION) return "green";
  if (rank > COHORT_SIZE - DEMOTION) return "red";
  return "neutral";
}

/**
 * Passe à la semaine suivante : recalcule la promotion/relégation sur les
 * classements de la semaine qui s'achève, puis remet l'XP hebdo à zéro.
 */
export function rollover(
  state: { league: LeagueInfo; weeklyXp: Record<string, number> },
  now: number,
  you: { name: string; avatar: string }
): { changed: boolean; promoted: boolean; demoted: boolean } {
  const current = weekStartKey(now);
  if (state.league.weekStart === current) return { changed: false, promoted: false, demoted: false };

  // Première visite (weekStart vide) : on adopte la semaine en cours sans
  // calcul de classement ni purge — l'XP déjà gagné appartient à cette semaine.
  if (!state.league.weekStart) {
    state.league = { ...state.league, weekStart: current };
    return { changed: false, promoted: false, demoted: false };
  }

  const before = buildStandings(state.league, state.weeklyXp, {
    name: you.name,
    avatar: you.avatar,
    xp: userWeekXp(state.weeklyXp, state.league.weekStart),
  });
  const yourRank = before.find((r) => r.isYou)?.rank ?? COHORT_SIZE;

  let tier = state.league.tier;
  let promoted = false;
  let demoted = false;
  if (yourRank <= PROMOTION && tier !== "gold") {
    tier = TIER_ORDER[TIER_ORDER.indexOf(tier) + 1];
    promoted = true;
  } else if (yourRank > COHORT_SIZE - DEMOTION && tier !== "bronze") {
    tier = TIER_ORDER[TIER_ORDER.indexOf(tier) - 1];
    demoted = true;
  }

  state.league = {
    tier,
    weekStart: current,
    botSeed: (state.league.botSeed + 1) % 1_000_000,
  };
  // On ne garde que l'XP déjà gagné dans la nouvelle semaine : l'XP de la
  // semaine close a servi au classement ci-dessus, celui de la nouvelle
  // semaine (étudiée avant l'ouverture de l'onglet) doit survivre.
  const newWeek: Record<string, number> = {};
  for (const [key, value] of Object.entries(state.weeklyXp)) {
    if (key >= current) newWeek[key] = value;
  }
  state.weeklyXp = newWeek;
  return { changed: true, promoted, demoted };
}

/** Plage de dates de la semaine courante : « 6 – 12 oct. ». */
export function formatWeekRange(startKey: string, lang: Lang): string {
  let start = new Date(`${startKey}T00:00:00`);
  // Clé absente/corrompue (`""` avant le premier rollover) → « Invalid Date ».
  if (Number.isNaN(start.getTime())) start = new Date(`${weekStartKey(Date.now())}T00:00:00`);
  const end = new Date(start.getTime() + 6 * 86_400_000);
  const locale = lang === "ar" ? "ar-DZ" : lang === "en" ? "en-US" : "fr-FR";
  const opts: Intl.DateTimeFormatOptions = { day: "numeric", month: "short" };
  return `${start.toLocaleDateString(locale, opts)} – ${end.toLocaleDateString(locale, opts)}`;
}

/** Millisecondes restantes avant le reset (lundi 00:00 local). */
export function msUntilReset(now: number): number {
  const next = new Date(now);
  next.setHours(0, 0, 0, 0);
  const day = (next.getDay() + 6) % 7;
  next.setDate(next.getDate() + (7 - day));
  return next.getTime() - now;
}
