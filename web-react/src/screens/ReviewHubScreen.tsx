import { useMemo } from "react";
import { useT } from "../i18n/useT";
import { useAppState } from "../domain/store";
import type { ReviewQueueItem } from "../domain/store";

export function ReviewHubScreen() {
  const { t } = useT();
  const s = useAppState();
  const queue = s.reviewQueue;
  const playedCount = s.reviewQueue.filter((x) => x.status === "played").length;

  // Groupe par module
  const grouped = useMemo(() => {
    const map = new Map<string, ReviewQueueItem[]>();
    for (const item of queue) {
      const module = item.moduleId;
      if (!map.has(module)) map.set(module, []);
      map.get(module)!.push(item);
    }
    return map;
  }, [queue]);

  const renderItem = (item: ReviewQueueItem) => (
    <div key={item.lessonId} className="review-item">
      <div className="review-item-info">
        <span className="review-item-title">📖 {item.chapterId}</span>
        <span className="review-item-desc">
          {item.exIds?.length ?? 0} exos racés
        </span>
      </div>
      <div className="review-item-actions">
        <button
          type="button"
          className="btn primary"
          onClick={() => {
            // WS2.1 : la séance de révision se joue via le quiz encadré
            // (`?review=` → getReview + markReviewPlayed à la fin).
            window.location.hash = `#/quiz?review=${encodeURIComponent(item.lessonId)}`;
          }}
        >
          ▶ Jouer
        </button>
        <button
          type="button"
          className="btn ghost"
          onClick={() => {
            window.location.hash = `#/path`;
          }}
        >
          Annuler
        </button>
      </div>
    </div>
  );

  return (
    <div className="review-hub">
      <div className="review-hub-header">
        <h1>{t("reviewHubTitle")}</h1>
        <p className="tiny muted" style={{ margin: 0 }}>
          {t("reviewHubDesc")}
        </p>
        <p className="review-hub-desc">
          {queue.length} révision{queue.length > 1 ? "s" : ""} en attente ·{" "}
          {playedCount} {t("playedLbl")}
        </p>
      </div>

      {queue.length === 0 ? (
        <div className="review-empty">
          <p className="tiny">{t("reviewEmpty")}</p>
          <button type="button" className="btn primary" onClick={() => (window.location.hash = "#/path")}>
            Prendre un cours
          </button>
        </div>
      ) : (
        <div className="review-list">
          {Array.from(grouped.entries()).map(([moduleId, items]) => (
            <div key={moduleId} className="review-group">
              <h2 className="review-group-title">{moduleId}</h2>
              {items.map(renderItem)}
            </div>
          ))}
        </div>
      )}
    </div>
  );
}
