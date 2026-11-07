import { useAuth } from "../hooks/useAuth";
import { useAppState } from "../domain/store";

export function PlayerProfile() {
  const { user, signOut, loadProgress } = useAuth();
  const s = useAppState();

  const handleSync = async () => {
    if (!user) return;
    await loadProgress();
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
          <h2>{user?.email || "Invité"}</h2>
          <p className="profile-status">{user ? "Connecté" : "Non connecté"}</p>
        </div>
      </div>

      {user && (
        <div className="profile-actions">
          <button className="btn secondary" onClick={handleSync}>
            🔄 Synchroniser les données
          </button>
          <button className="btn danger" onClick={handleLogout}>
            Déconnexion
          </button>
        </div>
      )}

      {!user && (
        <div className="profile-login-prompt">
          <p>Connectez-vous pour synchroniser vos progrès</p>
          <button className="btn primary" onClick={() => (window.location.hash = "#/login")}>
            Se connecter
          </button>
        </div>
      )}

      <div className="profile-stats">
        <h3>Vos Statistiques</h3>
        <div className="stats-grid">
          <div className="stat-item">
            <span className="stat-label">XP Total</span>
            <span className="stat-value">{s.xp}</span>
          </div>
          <div className="stat-item">
            <span className="stat-label">Niveau</span>
            <span className="stat-value">{Math.floor(s.xp / 100) + 1}</span>
          </div>
          <div className="stat-item">
            <span className="stat-label">Série</span>
            <span className="stat-value">{s.streak} jours</span>
          </div>
          <div className="stat-item">
            <span className="stat-label">Gemmes</span>
            <span className="stat-value">{s.gems}</span>
          </div>
          <div className="stat-item">
            <span className="stat-label">Leçons complétées</span>
            <span className="stat-value">{Object.keys(s.lessonBest || {}).length}</span>
          </div>
          <div className="stat-item">
            <span className="stat-label">Modules complétés</span>
            <span className="stat-value">{Object.keys(s.moduleBest || {}).length}</span>
          </div>
        </div>
      </div>
    </div>
  );
}