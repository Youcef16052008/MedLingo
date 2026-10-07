import { useEffect } from "react";
import { useT } from "../i18n/useT";
import { useNow } from "../lib/useNow";
import { useAppState, update, getState } from "../domain/store";
import {
  buildStandings,
  formatWeekRange,
  msUntilReset,
  rollover,
  userWeekXp,
  weekStartKey,
  zoneOf,
  YOU,
  TIER_ICONS,
  type Tier,
} from "../domain/leagues";

const TIER_KEYS: Record<Tier, "leagueTierBronze" | "leagueTierSilver" | "leagueTierGold"> = {
  bronze: "leagueTierBronze",
  silver: "leagueTierSilver",
  gold: "leagueTierGold",
};

/** Classement hebdomadaire de la cohorte (30 joueurs). */
export function LeaguesScreen() {
  const { t, lang } = useT();
  const s = useAppState();
  const now = useNow();

  // Changement de semaine : promotion / relégation + remise à zéro de l'XP.
  useEffect(() => {
    const st = getState();
    if (st.league.weekStart !== weekStartKey(Date.now())) {
      update((d) => void rollover(d, Date.now(), YOU));
    }
  }, []);

  const rows = buildStandings(
    s.league,
    s.weeklyXp,
    { ...YOU, xp: userWeekXp(s.weeklyXp, s.league.weekStart) }
  );
  const ms = msUntilReset(now);
  const days = Math.floor(ms / 86_400_000);
  const hours = Math.floor((ms % 86_400_000) / 3_600_000);

  return (
    <>
      <div className="section-head" style={{ marginTop: 0 }}>
        <div>
          <p className="eyebrow">🏆 {formatWeekRange(s.league.weekStart, lang)}</p>
          <h1>{t("leagueTitle")}</h1>
        </div>
        <span className="stat-chip streak" title={t("leagueReset")}>
          {TIER_ICONS[s.league.tier]} {t(TIER_KEYS[s.league.tier])}
        </span>
      </div>

      <p className="tiny" style={{ margin: "-4px 0 12px" }}>
        {t("leagueReset")} : {days} j {String(hours).padStart(2, "0")} h ·{" "}
        {t("leaguePromotion")} : 10 · {t("leagueDemotion")} : 5
      </p>

      <div className="league-list">
        {rows.map((r) => (
          <div
            key={r.id}
            className={`league-row ${r.isYou ? "you" : ""} zone-${zoneOf(r.rank)}`}
            style={{ animationDelay: `${Math.min(r.rank, 15) * 40}ms` }}
          >
            <span className="league-rank">{r.rank}</span>
            <span className="league-avatar" aria-hidden="true">
              {r.avatar}
            </span>
            <span className="league-name">{r.isYou ? t("leagueYou") : r.name}</span>
            <b className="league-xp">
              {r.xp} {t("statXp")}
            </b>
          </div>
        ))}
      </div>
    </>
  );
}
