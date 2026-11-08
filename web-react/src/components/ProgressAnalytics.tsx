import { useAppState } from "../domain/store";
import { getTerms, getExercises } from "../data/load";
import { MODULES } from "../data/modules";
import { useT } from "../i18n/useT";
import { useMemo } from "react";

export function ProgressAnalytics() {
  const s = useAppState();
  const { t } = useT();
  const terms = getTerms();
  const exercises = getExercises();

  // Calculate module progress
  const moduleProgress = useMemo(() => {
    return MODULES.map((mod) => {
      const moduleTerms = terms.filter((t) => t.module === mod.fr);
      const moduleExercises = exercises.filter((e) => e.module === mod.fr);
      const completed = s.moduleBest?.[mod.id] || 0;
      const total = moduleExercises.length;
      const progress = total > 0 ? Math.round((completed / total) * 100) : 0;

      return {
        id: mod.id,
        name: mod.fr,
        icon: mod.icon,
        color: mod.color,
        terms: moduleTerms.length,
        exercises: total,
        completed: completed,
        progress: progress,
      };
    });
  }, [s, terms, exercises]);

  // Overall stats
  const totalModules = MODULES.length;
  const completedModules = moduleProgress.filter((m) => m.progress === 100).length;
  const totalLessons = Object.keys(s.lessonBest || {}).length;
  const totalReviews = Object.keys(s.reviewQueue || {}).length;
  const dailyGoal = s.dailyGoal || 20;
  const xpToday = s.xpToday || 0;
  const dailyProgress = Math.min(100, Math.round((xpToday / dailyGoal) * 100));

  // SM-2 stats
  const sm2Items = Object.keys(s.sm2 || {}).length;
  const exprSm2Items = Object.keys(s.exprSm2 || {}).length;

  // Story stats
  const completedStories = Object.values(s.storyProgress || {}).filter((sp: any) => sp.completedAt > 0).length;

  return (
    <div className="progress-analytics">
      <h2>📊 {t("analyticsTitle")}</h2>

      {/* Overall Stats */}
      <div className="analytics-overview">
        <div className="analytics-card">
          <span className="analytics-icon">📚</span>
          <span className="analytics-value">{totalLessons}</span>
          <span className="analytics-label">{t("playerLessonsCompleted")}</span>
        </div>
        <div className="analytics-card">
          <span className="analytics-icon">📖</span>
          <span className="analytics-value">{completedModules}/{totalModules}</span>
          <span className="analytics-label">{t("playerModulesCompleted")}</span>
        </div>
        <div className="analytics-card">
          <span className="analytics-icon">🔄</span>
          <span className="analytics-value">{totalReviews}</span>
          <span className="analytics-label">{t("reviewsPending")}</span>
        </div>
        <div className="analytics-card">
          <span className="analytics-icon">📖</span>
          <span className="analytics-value">{completedStories}</span>
          <span className="analytics-label">{t("storiesCompleted")}</span>
        </div>
      </div>

      {/* Daily Goal */}
      <div className="analytics-daily">
        <h3>{t("playerDailyGoal")}</h3>
        <div className="daily-progress-bar">
          <div className="daily-fill" style={{ width: `${dailyProgress}%` }}></div>
        </div>
        <span className="daily-text">{xpToday} / {dailyGoal} XP ({dailyProgress}%)</span>
      </div>

      {/* Module Progress */}
      <div className="analytics-modules">
        <h3>{t("progressByModule")}</h3>
        <div className="module-list">
          {moduleProgress.map((mod) => (
            <div key={mod.id} className="module-item">
              <div className="module-header">
                <span className="module-icon">{mod.icon}</span>
                <span className="module-name">{mod.name}</span>
                <span className="module-percent">{mod.progress}%</span>
              </div>
              <div className="module-progress-bar">
                <div
                  className="module-fill"
                  style={{ width: `${mod.progress}%`, backgroundColor: mod.color }}
                ></div>
              </div>
              <div className="module-stats">
                <span>{mod.terms} {t("termsLbl")}</span>
                <span>{mod.exercises} {t("exCount")}</span>
                <span>{mod.completed} {t("completedLbl")}</span>
              </div>
            </div>
          ))}
        </div>
      </div>

      {/* SM-2 Stats */}
      <div className="analytics-sm2">
        <h3>{t("spacedMemory")}</h3>
        <div className="sm2-stats">
          <div className="sm2-item">
            <span className="sm2-label">{t("termsInReview")}</span>
            <span className="sm2-value">{sm2Items}</span>
          </div>
          <div className="sm2-item">
            <span className="sm2-label">{t("exprsInReview")}</span>
            <span className="sm2-value">{exprSm2Items}</span>
          </div>
        </div>
      </div>
    </div>
  );
}