import { useT } from "../i18n/useT";
import { useNow } from "../lib/useNow";
import { useAppState } from "../domain/store";
import { totalDue, type Sm2Map } from "../domain/progress";

export function TopBar() {
  const { t } = useT();
  const s = useAppState();
  const now = useNow();
  const due = totalDue(s.sm2 as Sm2Map, now);

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
      </div>
      <p className="brand-sub">{t("facultyLbl")}</p>
    </header>
  );
}