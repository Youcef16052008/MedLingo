import { useT } from "../i18n/useT";
import { useNow } from "../lib/useNow";
import { useAppState } from "../domain/store";
import { totalDue, type Sm2Map } from "../domain/progress";
import { weekStartKey } from "../domain/leagues";
import { navigate } from "../lib/router";

/* Miroir de GemsLeagueTopBar.kt : série 🔥, gemmes 💎, tier de ligue 🏆.
   Les coeurs ont été supprimés en migration v10 (plus de puce de cours).
   Chaque puce porte la couleur de fond qu'elle a sur Android. */
export function TopBar() {
  const { t } = useT();
  const s = useAppState();
  const now = useNow();
  const due = totalDue(s.sm2 as Sm2Map, now);

  // weeklyXp de la semaine courante (clé = lundi), miroir de
  // userStats.weeklyXp lu par GemsLeagueTopBar.kt.
  const weeklyXp = (s.weeklyXp ?? {})[weekStartKey(now)] ?? 0;
  // tier affiché en majuscules tronqué à 4 lettres, comme Android.
  const tier = (s.league?.tier ?? "bronze").toUpperCase().slice(0, 4);

  return (
    <header className="topbar">
      <a className="brand" href="#/path" aria-label={`${t("docTitle")} DZ — ${t("navHome")}`}>
        {/* Avatar : dégradé + liseré, comme l'en-tête « residency » d'Android */}
        <span className="brand-mark" aria-hidden="true">
          🩺
        </span>
        <span className="brand-name">
          {t("docTitle")} <em>DZ</em>
        </span>
      </a>
      <div className="topbar-stats" aria-label={t("statStreak")}>
        <span className="stat-chip streak" title={t("statStreak")}>
          🔥 <b>{s.streak}</b>
        </span>
        <span className="stat-chip gems" title={t("statGems")}>
          💎 <b>{s.gems}</b>
        </span>
        {due > 0 && (
          <span className="stat-chip due" title={t("vitalsDue")}>
            🔔 <b>{due}</b>
          </span>
        )}
        <button
          type="button"
          className="stat-chip league"
          title={t("navLeagues")}
          onClick={() => navigate("leagues")}
        >
          🏆 <b>{tier}</b> <span className="chip-extra">{weeklyXp}XP</span>
        </button>
      </div>
      <p className="brand-sub">{t("facultyLbl")}</p>
    </header>
  );
}