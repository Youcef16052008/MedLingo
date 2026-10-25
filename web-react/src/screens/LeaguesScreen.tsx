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
  COHORT_SIZE,
  PROMOTION,
  DEMOTION,
  type Tier,
} from "../domain/leagues";

/* Couleurs de palier — LeagueTier.colorHex (LeagueCohortEntity.kt).
   Le web ne gère que bronze/silver/gold. */
const TIER_COLORS: Record<Tier, string> = {
  bronze: "#CD7F32",
  silver: "#C0C0C0",
  gold: "#FFD700",
};

/* Médailles de tête de classement — LeagueMemberRow.kt. */
const RANK_MEDALS: Record<number, string> = {
  1: "#FFD700",
  2: "#C0C0C0",
  3: "#CD7F32",
};

const TIER_KEYS: Record<Tier, "leagueTierBronze" | "leagueTierSilver" | "leagueTierGold"> = {
  bronze: "leagueTierBronze",
  silver: "leagueTierSilver",
  gold: "leagueTierGold",
};

/* "Bot" ou "Actif" — LeagueMemberRow.kt ; le web n'a que des bots + vous.
 * BUG-021 : traduit (était « Actif/Bot » en dur, invisible du checker i18n). */
function activityLabel(
  isYou: boolean,
  t: (k: "leagueActive" | "leagueBot") => string
): string {
  return isYou ? t("leagueActive") : t("leagueBot");
}

/** Classement hebdomadaire de la cohorte (30 joueurs) — LeagueScreen.kt. */
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
  const tierColor = TIER_COLORS[s.league.tier];
  const cohortId = `${s.league.botSeed}`;

  return (
    <div className="league-screen">
      {/* ===== En-tête de cohorte (LeagueHeader) ===== */}
      <div
        className="card league-hero"
        style={{ background: `${tierColor}1A` }}
      >
        <div className="league-hero-left">
          <span
            className="league-hero-ico"
            style={{ background: `${tierColor}33` }}
            aria-hidden="true"
          >
            {TIER_ICONS[s.league.tier]}
          </span>
          <div>
            <h1 className="league-hero-title">
              {t("leagueTitle")} {t(TIER_KEYS[s.league.tier]).toUpperCase()}
            </h1>
            <span className="league-hero-sub">
              {cohortId}... • {rows.length} membres
            </span>
          </div>
        </div>
        <span
          className="league-hero-badge"
          style={{ background: tierColor }}
        >
          {TIER_ICONS[s.league.tier]} {t(TIER_KEYS[s.league.tier]).toUpperCase()}
        </span>
      </div>

      {/* ===== Zones promotion / relégation ===== */}
      <div className="card league-zones">
        <div className="league-zones-row">
          <span className="league-zone-up">
            🏆 Top {PROMOTION} → {t("leaguePromotion")}
          </span>
          <span className="league-zone-down">
            📉 Bottom {DEMOTION} → {t("leagueDemotion")}
          </span>
        </div>
        <span className="league-zones-week">
          {t("leagueWeekLabel")}: {formatWeekRange(s.league.weekStart, lang)} ·{" "}
          {t("leagueReset")}: {days} j {String(hours).padStart(2, "0")} h
        </span>
      </div>

      {/* ===== Classement (LeagueMemberRow) ===== */}
      <div className="league-list">
        {rows.map((r) => {
          const zone = zoneOf(r.rank);
          const isPromotion = r.rank <= PROMOTION;
          const isDemotion = r.rank > COHORT_SIZE - DEMOTION;
          const medal = RANK_MEDALS[r.rank];
          return (
            <div
              key={r.id}
              className={
                "league-row" +
                (r.isYou ? " you" : "") +
                (zone === "green" ? " zone-green" : zone === "red" ? " zone-red" : "")
              }
              style={{ animationDelay: `${Math.min(r.rank, 15) * 40}ms` }}
            >
              <span
                className="league-rank"
                style={medal ? { background: medal, color: "#fff" } : undefined}
              >
                {r.rank}
              </span>
              <span className="league-avatar" aria-hidden="true">
                {r.avatar}
              </span>
              <span className="league-id">
                <span className="league-name">
                  {r.isYou ? t("leagueYou") : r.name}
                  {r.isYou && <b className="league-you-tag">{t("leagueYou")}</b>}
                </span>
                <span className="league-sub">
                  🔥 {r.isYou ? s.streak : 0}
                  {t("leagueStreakDays")} • {activityLabel(r.isYou, t)}
                </span>
              </span>
              <span className="league-xp-col">
                <b className={isPromotion ? "up" : isDemotion ? "down" : ""}>
                  {r.xp} XP
                </b>
                {isPromotion && r.rank <= PROMOTION ? (
                  <span className="up">↑ {t("leaguePromotion")}</span>
                ) : isDemotion ? (
                  <span className="down">↓ {t("leagueDemotion")}</span>
                ) : null}
              </span>
            </div>
          );
        })}
      </div>
    </div>
  );
}
