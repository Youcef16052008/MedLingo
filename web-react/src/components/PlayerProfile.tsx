import { useAuth, useSync } from "../hooks/useAuth";
import { useT } from "../i18n/useT";
import { useAppState } from "../domain/store";
import { useToast } from "./ToastProvider";

export function PlayerProfile() {
  const { user, signOut } = useAuth();
  const { syncNow, syncing } = useSync();
  const { t } = useT();
  const s = useAppState();
  const toast = useToast();

  // R-07: the manual sync button now runs a real pull-merge-push cycle
  // instead of discarding the remote payload.
  const handleSync = async () => {
    if (!user) return;
    const result = await syncNow();
    if (result === "ok") toast("✓ " + t("profileSync"));
    else if (result === "offline") toast(t("loginErrorPrefix") + "offline");
    else if (result === "error") toast(t("loginErrorPrefix") + "sync");
  };

  const handleLogout = async () => {
    await signOut();
    window.location.hash = "#/login";
  };

  return (
    <div className="player-profile">
      <div className="profile-header">
        <div className="profile-avatar">
          {user?.email?.charAt(0).toUpperCase() || "👤"}
        </div>
        <div className="profile-info">
          <h2>{user?.email || t("profileGuest")}</h2>
          <p className="profile-status">
            {user ? t("profileConnected") : t("profileNotConnected")}
          </p>
        </div>
      </div>

      {user && (
        <div className="profile-actions">
          <button className="btn secondary" onClick={handleSync} disabled={syncing}>
            🔄 {t("profileSync")}
          </button>
          <button className="btn danger" onClick={handleLogout}>
            {t("profileLogout")}
          </button>
        </div>
      )}

      {!user && (
        <div className="profile-login-prompt">
          <p>{t("profileLoginPrompt")}</p>
          <button className="btn primary" onClick={() => (window.location.hash = "#/login")}>
            {t("profileSignIn")}
          </button>
        </div>
      )}

      <div className="profile-stats">
        <h3>{t("profileStats")}</h3>
        <div className="stats-grid">
          <div className="stat-item">
            <span className="stat-label">{t("playerXpTotal")}</span>
            <span className="stat-value">{s.xp}</span>
          </div>
          <div className="stat-item">
            <span className="stat-label">{t("playerLevel")}</span>
            <span className="stat-value">{Math.floor(s.xp / 100) + 1}</span>
          </div>
          <div className="stat-item">
            <span className="stat-label">{t("statStreak")}</span>
            <span className="stat-value">{s.streak} {t("daysUnit")}</span>
          </div>
          <div className="stat-item">
            <span className="stat-label">{t("statGems")}</span>
            <span className="stat-value">{s.gems}</span>
          </div>
          <div className="stat-item">
            <span className="stat-label">{t("playerLessonsCompleted")}</span>
            <span className="stat-value">{Object.keys(s.lessonBest || {}).length}</span>
          </div>
          <div className="stat-item">
            <span className="stat-label">{t("playerModulesCompleted")}</span>
            <span className="stat-value">{Object.keys(s.moduleBest || {}).length}</span>
          </div>
        </div>
      </div>
    </div>
  );
}
