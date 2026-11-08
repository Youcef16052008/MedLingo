import { useAuth } from "../hooks/useAuth";
import { useAppState } from "../domain/store";
import { getTerms, getExercises } from "../data/load";
import { MODULES } from "../data/modules";

export function PlayerScreen() {
  const s = useAppState();
  const { user } = useAuth();

  const totalTerms = getTerms().length;
  const totalExercises = getExercises().length;
  const totalModules = MODULES.length;

  const xp = s.xp;
  const level = Math.floor(xp / 100) + 1;
  const streak = s.streak;
  const gems = s.gems;
  const dailyGoal = s.dailyGoal || 20;
  const xpToday = s.xpToday || 0;
  const dailyProgress = Math.min(100, Math.round((xpToday / dailyGoal) * 100));

  const COMPLETED_THRESHOLD = 70;
  const modulesCompleted = Object.values(s.moduleBest || {}).filter((v) => (v ?? 0) >= COMPLETED_THRESHOLD).length;
  const lessonsCompleted = Object.values(s.lessonBest || {}).filter((v) => (v ?? 0) >= COMPLETED_THRESHOLD).length;

  const handleStartLesson = () => {
    window.location.hash = "#/path";
  };

  const handleStartQuiz = () => {
    window.location.hash = "#/practice";
  };

  const handleViewStories = () => {
    window.location.hash = "#/stories";
  };

  const handleViewProfile = () => {
    window.location.hash = "#/profile";
  };

  return (
    <div className="player-screen">
      <div className="player-header">
        <h1 className="player-title">
          {user ? `Bonjour, ${user.email?.split("@")[0] || "Apprenant"} !` : "MedLingo"}
        </h1>
        <p className="player-subtitle">Votre parcours d'apprentissage médical</p>
      </div>

      {/* Stats Cards */}
      <div className="player-stats-grid">
        <div className="stat-card">
          <div className="stat-icon">⭐</div>
          <div className="stat-value">{xp}</div>
          <div className="stat-label">XP Total</div>
        </div>
        <div className="stat-card">
          <div className="stat-icon">📊</div>
          <div className="stat-value">{level}</div>
          <div className="stat-label">Niveau</div>
        </div>
        <div className="stat-card">
          <div className="stat-icon">🔥</div>
          <div className="stat-value">{streak}</div>
          <div className="stat-label">Série</div>
        </div>
        <div className="stat-card">
          <div className="stat-icon">💎</div>
          <div className="stat-value">{gems}</div>
          <div className="stat-label">Gemmes</div>
        </div>
      </div>

      {/* Daily Goal Progress */}
      <div className="player-daily-goal">
        <h2>Objectif Quotidien</h2>
        <div className="progress-bar">
          <div className="progress-fill" style={{ width: `${dailyProgress}%` }}></div>
        </div>
        <div className="progress-text">
          {xpToday} / {dailyGoal} XP
        </div>
      </div>

      {/* Corpus Stats */}
      <div className="player-corpus-stats">
        <h2>Corpus Disponible</h2>
        <div className="corpus-grid">
          <div className="corpus-item">
            <span className="corpus-number">{totalTerms.toLocaleString()}</span>
            <span className="corpus-label">Termes</span>
          </div>
          <div className="corpus-item">
            <span className="corpus-number">{totalExercises.toLocaleString()}</span>
            <span className="corpus-label">Exercices</span>
          </div>
          <div className="corpus-item">
            <span className="corpus-number">{totalModules}</span>
            <span className="corpus-label">Modules</span>
          </div>
          <div className="corpus-item">
            <span className="corpus-number">{modulesCompleted}/{totalModules}</span>
            <span className="corpus-label">Modules complétés</span>
          </div>
        </div>
      </div>

      {/* Quick Actions */}
      <div className="player-quick-actions">
        <h2>Actions Rapides</h2>
        <div className="action-grid">
          <button className="action-card" onClick={handleStartLesson}>
            <span className="action-icon">📚</span>
            <span className="action-label">Commencer une leçon</span>
          </button>
          <button className="action-card" onClick={handleStartQuiz}>
            <span className="action-icon">🎯</span>
            <span className="action-label">Pratiquer</span>
          </button>
          <button className="action-card" onClick={handleViewStories}>
            <span className="action-icon">📖</span>
            <span className="action-label">Lire des histoires</span>
          </button>
          <button className="action-card" onClick={handleViewProfile}>
            <span className="action-icon">👤</span>
            <span className="action-label">Mon profil</span>
          </button>
          <button className="action-card" onClick={() => { window.location.hash = "#/analytics"; }}>
            <span className="action-icon">📊</span>
            <span className="action-label">Analytics</span>
          </button>
          <button className="action-card" onClick={() => { window.location.hash = "#/gamification"; }}>
            <span className="action-icon">🎮</span>
            <span className="action-label">Gamification</span>
          </button>
        </div>
      </div>

      {/* Learning Progress */}
      <div className="player-progress">
        <h2>Progression</h2>
        <div className="progress-stats">
          <div className="progress-stat">
            <span className="progress-icon">✅</span>
            <span className="progress-value">{lessonsCompleted}</span>
            <span className="progress-label">Leçons complétées</span>
          </div>
          <div className="progress-stat">
            <span className="progress-icon">📖</span>
            <span className="progress-value">{modulesCompleted}</span>
            <span className="progress-label">Modules complétés</span>
          </div>
        </div>
      </div>
    </div>
  );
}