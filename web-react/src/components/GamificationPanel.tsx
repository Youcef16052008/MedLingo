import { useAppState, update } from "../domain/store";
import { rollover, weekStartKey, YOU } from "../domain/leagues";
import { useT } from "../i18n/useT";
import { useState } from "react";

export function GamificationPanel() {
  const s = useAppState();
  const { t } = useT();
  const [showChest, setShowChest] = useState(false);

  // Trophy info
  const trophies = s.trophies || {};
  const trophyCount = Object.keys(trophies).length;

  // Chest info
  const pendingChests = s.pendingChests || 0;
  const chestsOpened = s.chestsOpened || 0;

  // Weekly XP total
  const weekStart = weekStartKey(Date.now());
  const weeklyXp = s.weeklyXp || {};
  const weeklyTotal = Object.entries(weeklyXp)
    .filter(([k]) => k.startsWith(weekStart))
    .reduce((sum, [, v]) => sum + (v || 0), 0);

  const handleClaimChest = () => {
    if (pendingChests > 0) {
      update((d) => {
        d.pendingChests -= 1;
        d.chestsOpened += 1;
        // Add chest rewards
        d.gems += 50;
      });
      setShowChest(true);
      setTimeout(() => setShowChest(false), 3000);
    }
  };

  const handleRollover = () => {
    update((d) => void rollover(d, Date.now(), YOU));
  };

  return (
    <div className="gamification-panel">
      <h2>🎮 Gamification</h2>

      {/* Leagues */}
      <div className="gamification-section">
        <h3>🏆 Ligues</h3>
        <div className="league-info">
          <div className="league-current">
            <span className="league-name">{s.league?.tier || "Bronze"}</span>
            <span className="league-xp">{weeklyTotal} XP cette semaine</span>
          </div>
          <button className="btn small" onClick={handleRollover}>
            Mettre à jour la ligue
          </button>
        </div>
      </div>

      {/* Trophies */}
      <div className="gamification-section">
        <h3>🏅 {t("trophyTitle")}</h3>
        <div className="trophy-grid">
          {trophyCount > 0 ? (
            Object.entries(trophies).map(([id]) => (
              <div key={id} className="trophy-item">
                <span className="trophy-icon">🏆</span>
                <span className="trophy-name">{id}</span>
              </div>
            ))
          ) : (
            <p className="muted">{t("trophyNone")}</p>
          )}
        </div>
        <p className="trophy-count">{trophyCount} {t("trophyCountLbl")}</p>
      </div>

      {/* Chests */}
      <div className="gamification-section">
        <h3>📦 Caisse(s)</h3>
        <div className="chest-info">
          <span className="chest-count">{pendingChests} caisse(s) en attente</span>
          <span className="chest-opened">{chestsOpened} caisse(s) ouverte(s)</span>
        </div>
        {pendingChests > 0 && (
          <button className="btn primary" onClick={handleClaimChest}>
            Ouvrir une caisse
          </button>
        )}
        {showChest && (
          <div className="chest-reward">
            🎉 +50 💎 Gemmes !
          </div>
        )}
      </div>

      {/* Daily Goal */}
      <div className="gamification-section">
        <h3>🎯 Objectif Quotidien</h3>
        <div className="daily-goal">
          <div className="goal-progress">
            <div
              className="goal-fill"
              style={{ width: `${Math.min(100, (s.xpToday || 0) / (s.dailyGoal || 20) * 100)}%` }}
            ></div>
          </div>
          <span className="goal-text">
            {s.xpToday || 0} / {s.dailyGoal || 20} XP
          </span>
        </div>
      </div>
    </div>
  );
}