import { useMemo, useState } from "react";
import { useT } from "../i18n/useT";
import { useAppState } from "../domain/store";
import { storyById, getStories } from "../learning/stories";
import type { Story } from "../learning/types";
import { navigate } from "../lib/router";

interface StoryBrowserProps {
  onOpenStory: (storyId: string) => void;
}

export function StoryBrowser({ onOpenStory }: StoryBrowserProps) {
  const { lang } = useT();
  const s = useAppState();
  const [search, setSearch] = useState("");
  const [filterChapter, setFilterChapter] = useState<string>("all");
  const [showAll, setShowAll] = useState<boolean>(false);

  const inProgressStories = useMemo(() => {
    return Object.entries(s.storyProgress || {})
      .filter(([, progress]) => progress.completedAt === 0)
      .map(([storyId]) => storyById(storyId))
      .filter(Boolean) as Story[];
  }, [s.storyProgress]);

  const completedStories = useMemo(() => {
    return Object.entries(s.storyProgress || {})
      .filter(([, progress]) => progress.completedAt > 0)
      .map(([storyId]) => storyById(storyId))
      .filter(Boolean) as Story[];
  }, [s.storyProgress]);

  const allStories = useMemo(() => getStories(), []);

  const filteredStories = useMemo(() => {
    let list = showAll
      ? [...allStories]
      : [...inProgressStories, ...completedStories];
    if (search) {
      const q = search.toLowerCase();
      list = list.filter((story) => {
        const title = (story.title[lang] || story.title.fr || "").toLowerCase();
        return title.includes(q);
      });
    }
    if (filterChapter !== "all") {
      list = list.filter((story) => story.chapter === filterChapter);
    }
    return list;
  }, [inProgressStories, completedStories, search, filterChapter, lang]);

  // Get unique chapters
  const chapters = useMemo(() => {
    const chs = new Set<string>();
    const src = showAll ? allStories : [...inProgressStories, ...completedStories];
    src.forEach((s) => {
      chs.add(s.chapter || "unknown");
    });
    return Array.from(chs);
  }, [showAll, allStories, inProgressStories, completedStories]);

  return (
    <div className="story-browser">
      <div className="section-head" style={{ marginTop: 0 }}>
        <h1>📖 Bibliothèque d'Histoires</h1>
      </div>

      {/* Search */}
      <div className="search-box" style={{ marginBottom: 12 }}>
        <span aria-hidden="true">🔍</span>
        <input
          type="search"
          placeholder="Rechercher une histoire..."
          value={search}
          onChange={(e) => setSearch(e.target.value)}
        />
      </div>

      {/* Chapter filter */}
      {chapters.length > 0 && (
        <div style={{ marginBottom: 12 }}>
          <select
            value={filterChapter}
            onChange={(e) => setFilterChapter(e.target.value)}
            style={{ padding: "6px 12px", borderRadius: 6 }}
          >
            <option value="all">Tous les chapitres</option>
            {chapters.map((ch) => (
              <option key={ch} value={ch}>
                {ch}
              </option>
            ))}
          </select>
        </div>
      )}

      {/* Stats */}
      <div className="story-stats" style={{ display: "flex", gap: 12, marginBottom: 12 }}>
        <div className="stat-card">
          <span className="stat-value">{inProgressStories.length}</span>
          <span className="stat-label">En cours</span>
        </div>
        <div className="stat-card">
          <span className="stat-value">{completedStories.length}</span>
          <span className="stat-label">Terminées</span>
        </div>
      </div>

      {/* Discover toggle (R-26) */}
      <div style={{ marginBottom: 12 }}>
        <button
          className="btn ghost small"
          onClick={() => setShowAll((v) => !v)}
        >
          {showAll ? "Mes histoires" : "Toutes les histoires"}
        </button>
      </div>

      {/* Story list */}
      <div className="story-list">
        {filteredStories.length === 0 ? (
          <div className="empty">
            <span className="empty-ico">📖</span>
            <p>Aucune histoire trouvée</p>
            <p className="tiny muted">
              Les histoires sont chargées au fur et à mesure de votre progression.
            </p>
          </div>
        ) : (
          filteredStories.map((story) => {
            const progress = s.storyProgress[story.storyId];
            const isCompleted = progress?.completedAt > 0;
            const title = story.title[lang] || story.title.fr;
            const goal = story.goal[lang] || story.goal.fr;

            return (
              <div
                key={story.storyId}
                className="story-card"
                style={{
                  padding: 12,
                  marginBottom: 8,
                  borderRadius: 8,
                  border: "1px solid var(--border)",
                  background: "var(--card)",
                }}
              >
                <div style={{ display: "flex", justifyContent: "space-between", alignItems: "start" }}>
                  <div>
                    <h3 style={{ margin: "0 0 4px" }}>{title}</h3>
                    <p className="tiny muted">{goal}</p>
                    <p className="tiny">
                      {story.chapter} · {story.targetPhrases.length} phrases
                    </p>
                  </div>
                  <div style={{ textAlign: "right" }}>
                    {isCompleted ? (
                      <span style={{ color: "green" }}>✅ Terminé</span>
                    ) : (
                      <span style={{ color: "orange" }}>🔄 En cours</span>
                    )}
                  </div>
                </div>
                <button
                  className="btn primary small"
                  style={{ marginTop: 8 }}
                  onClick={() => onOpenStory(story.storyId)}
                >
                  {isCompleted ? "Rejouer" : "Continuer"}
                </button>
              </div>
            );
          })
        )}
      </div>

      {/* Back to home */}
      <button
        className="btn ghost block"
        style={{ marginTop: 16 }}
        onClick={() => navigate("home")}
      >
        ← Retour à l'accueil
      </button>
    </div>
  );
}