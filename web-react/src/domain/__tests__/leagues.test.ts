import { describe, it, expect } from "vitest";
import {
  buildStandings,
  rollover,
  weekStartKey,
  userWeekXp,
  msUntilReset,
  formatWeekRange,
  COHORT_SIZE,
  PROMOTION,
  DEMOTION,
  BOT_NAMES,
  mulberry32,
  hashSeed,
  type LeagueInfo,
} from "../leagues";

const NOW = 1_700_000_000_000; // 2023-11-14 (mardi)
const DAY = 86_400_000;
const you = (xp: number) => ({ name: "TOI", avatar: "🩺", xp });
const YOU = you(0);

const league = (overrides: Partial<LeagueInfo> = {}): LeagueInfo => ({
  tier: "bronze",
  weekStart: weekStartKey(NOW),
  botSeed: 1,
  ...overrides,
});

describe("semaine", () => {
  it("weekStartKey est un lundi", () => {
    const key = weekStartKey(NOW);
    const day = new Date(`${key}T00:00:00`).getDay();
    expect(day).toBe(1);
  });

  it("stable pour toute la semaine, change la semaine suivante", () => {
    expect(weekStartKey(NOW + 3 * DAY)).toBe(weekStartKey(NOW));
    expect(weekStartKey(NOW + 7 * DAY)).not.toBe(weekStartKey(NOW));
  });

  it("msUntilReset est positif et < 7 jours", () => {
    const ms = msUntilReset(NOW);
    expect(ms).toBeGreaterThan(0);
    expect(ms).toBeLessThan(7 * DAY);
  });

  it("formatWeekRange rend une plage de dates", () => {
    const label = formatWeekRange(weekStartKey(NOW), "fr");
    expect(label).toMatch(/–/);
    expect(formatWeekRange(weekStartKey(NOW), "en")).toMatch(/–/);
    expect(formatWeekRange(weekStartKey(NOW), "ar")).toMatch(/–/);
  });

  it("formatWeekRange tolère une clé absente ou corrompue (B13)", () => {
    expect(formatWeekRange("", "fr")).not.toMatch(/Invalid/);
    expect(formatWeekRange("", "en")).toMatch(/–/);
    expect(formatWeekRange("not-a-date", "ar")).not.toMatch(/Invalid/);
  });
});

describe("classement", () => {
  it("30 lignes : TOI + 29 bots", () => {
    const rows = buildStandings(league(), {}, YOU);
    expect(rows).toHaveLength(COHORT_SIZE);
    expect(BOT_NAMES).toHaveLength(29);
    expect(rows.filter((r) => r.isYou)).toHaveLength(1);
    expect(rows.map((r) => r.rank)).toEqual(rows.map((_, i) => i + 1));
  });

  it("trié par XP décroissant", () => {
    const rows = buildStandings(league(), { [weekStartKey(NOW)]: 400 }, you(400));
    for (let i = 1; i < rows.length; i++) {
      expect(rows[i - 1].xp).toBeGreaterThanOrEqual(rows[i].xp);
    }
  });

  it("déterministe : même graine et semaine → même classement", () => {
    const a = buildStandings(league(), {}, YOU);
    const b = buildStandings(league(), {}, YOU);
    expect(a.map((r) => `${r.name}:${r.xp}`)).toEqual(b.map((r) => `${r.name}:${r.xp}`));
  });

  it("une graine ou une semaine différente change les XP", () => {
    const a = buildStandings(league(), {}, YOU);
    const b = buildStandings(league({ botSeed: 2 }), {}, YOU);
    const c = buildStandings(league({ weekStart: "2024-01-01" }), {}, YOU);
    expect(a.map((r) => r.xp)).not.toEqual(b.map((r) => r.xp));
    expect(a.map((r) => r.xp)).not.toEqual(c.map((r) => r.xp));
  });

  it("userWeekXp additionne les jours depuis le début de semaine", () => {
    const start = weekStartKey(NOW);
    const weekly = { [start]: 30, "1999-01-01": 500 };
    expect(userWeekXp(weekly, start)).toBe(30);
    expect(userWeekXp({}, start)).toBe(0);
  });

  it("zones : top 10 vert, bottom 5 rouge", () => {
    const rows = buildStandings(league({ tier: "gold" }), { [weekStartKey(NOW)]: 100 }, YOU);
    const zones = rows.map((r) => (r.rank <= PROMOTION ? "g" : r.rank > COHORT_SIZE - DEMOTION ? "r" : "-"));
    expect(zones.slice(0, 10)).toEqual(Array(10).fill("g"));
    expect(zones.slice(-5)).toEqual(Array(5).fill("r"));
  });
});

describe("rollover hebdomadaire", () => {
  it("aucun changement dans la même semaine", () => {
    const state = { league: league(), weeklyXp: { [weekStartKey(NOW)]: 120 } };
    expect(rollover(state, NOW, YOU)).toEqual({
      changed: false,
      promoted: false,
      demoted: false,
    });
    expect(state.weeklyXp[weekStartKey(NOW)]).toBe(120);
  });

  it("promotion quand TOI finit dans le top 10", () => {
    const state = {
      league: league({ tier: "bronze" }),
      weeklyXp: { [weekStartKey(NOW)]: 9_999 },
    };
    const out = rollover(state, NOW + 7 * DAY, YOU);
    expect(out).toEqual({ changed: true, promoted: true, demoted: false });
    expect(state.league.tier).toBe("silver");
    expect(state.league.weekStart).toBe(weekStartKey(NOW + 7 * DAY));
    expect(state.weeklyXp).toEqual({});
  });

  it("relégation quand TOI finit dans le bottom 5 (0 XP)", () => {
    const state = { league: league({ tier: "silver" }), weeklyXp: {} };
    const out = rollover(state, NOW + 7 * DAY, YOU);
    expect(out).toEqual({ changed: true, promoted: false, demoted: true });
    expect(state.league.tier).toBe("bronze");
  });

  it("l'or ne se relègue pas en dessous de l'argent, l'argent du sommet monte en or", () => {
    const gold = { league: league({ tier: "gold" }), weeklyXp: {} };
    rollover(gold, NOW + 7 * DAY, YOU);
    expect(gold.league.tier).toBe("silver"); // relégué gold → silver

    const silverTop = { league: league({ tier: "silver" }), weeklyXp: { [weekStartKey(NOW)]: 9_999 } };
    rollover(silverTop, NOW + 7 * DAY, YOU);
    expect(silverTop.league.tier).toBe("gold");
  });

  it("la graine change à chaque semaine (nouveaux bots)", () => {
    const state = { league: league({ botSeed: 7 }), weeklyXp: {} };
    rollover(state, NOW + 7 * DAY, YOU);
    expect(state.league.botSeed).toBe(8);
  });

  it("première visite (weekStart vide) : adopte la semaine sans rien effacer", () => {
    const state = { league: league({ weekStart: "" }), weeklyXp: { [weekStartKey(NOW)]: 120 } };
    const out = rollover(state, NOW, YOU);
    expect(out).toEqual({ changed: false, promoted: false, demoted: false });
    expect(state.league.weekStart).toBe(weekStartKey(NOW));
    expect(state.weeklyXp[weekStartKey(NOW)]).toBe(120);
  });

  it("l'XP gagné dans la nouvelle semaine survit au rollover", () => {
    // L'utilisateur étudie lundi matin avant d'ouvrir l'onglet Ligues : cette
    // XP doit compter pour la nouvelle semaine, pas être effacée.
    const nextWeek = weekStartKey(NOW + 7 * DAY);
    const state = {
      league: league(),
      weeklyXp: { [weekStartKey(NOW)]: 500, [nextWeek]: 80 },
    };
    rollover(state, NOW + 7 * DAY, YOU);
    expect(state.weeklyXp).toEqual({ [nextWeek]: 80 });
  });

  it("le classement final est calculé sur la semaine close complète", () => {
    // Le lundi matin, la semaine précédente (dont le lundi) doit encore être
    // présente pour le calcul du rang final.
    const thisWeek = weekStartKey(NOW);
    const lastWeek = weekStartKey(NOW - 7 * DAY);
    const state = {
      league: league({ weekStart: lastWeek }),
      weeklyXp: { [lastWeek]: 9_999, [thisWeek]: 10 },
    };
    const out = rollover(state, NOW, you(0));
    expect(out.promoted).toBe(true); // 9 999 XP la semaine close → top 10
  });
});

describe("PRNG", () => {
  it("mulberry32 est borné et déterministe", () => {
    const r1 = mulberry32(hashSeed("abc"));
    const r2 = mulberry32(hashSeed("abc"));
    const values = Array.from({ length: 100 }, () => r1());
    expect(values).toHaveLength(100);
    expect(values.every((v) => v >= 0 && v < 1)).toBe(true);
    expect(Array.from({ length: 100 }, () => r2())).toEqual(values);
  });

  it("hashSeed est stable et non nul", () => {
    expect(hashSeed("1:2024-01-01:3")).toBe(hashSeed("1:2024-01-01:3"));
    expect(hashSeed("1:2024-01-01:3")).not.toBe(hashSeed("1:2024-01-01:4"));
    expect(hashSeed("")).not.toBe(0);
  });
});
