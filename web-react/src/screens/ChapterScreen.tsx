import { useMemo } from "react";
import { useT } from "../i18n/useT";
import { useAppState } from "../domain/store";
import { getChapters } from "../data/load";
import type { LessonEntry } from "../data/load";

export interface ChapterScreenProps {
  moduleId: string;
  chapterId: string;
  currentLessonId: string;
  onLessonStart: (lessonId: string) => void;
  onLessonEnd: (lessonId: string, score: number, missedExIds: number[]) => void;
  onBack: () => void;
}

/** Données d'une leçon affichées dans le chapitre. */
export interface LessonData {
  id: string;
  kind: "new" | "review";
  level: number;
  termIds: number[];
  questionCount: number;
  reviewedLessonIds?: string[];
  score: number;
  hearts: number;
  state: "locked" | "available" | "done";
  isReview: boolean;
}

export function ChapterScreen({
  chapterId,
  currentLessonId,
  onLessonStart,
  onBack,
}: ChapterScreenProps) {
  const { t } = useT();
  const chapters = useMemo(() => getChapters(), []);
  const chapter = chapters.find((c) => c.id === chapterId);
  const progress = useAppState().lessonProgress;

  const chapterLessons: LessonData[] = chapter?.modules.map((entry: LessonEntry): LessonData => {
    const progressEntry = progress[entry.id];
    const score = progressEntry?.score ?? 0;
    const hearts = progressEntry?.heartsRemain ?? 5;
    const state: "locked" | "available" | "done" = (() => {
      if (entry.kind === "new") {
        return score >= 70 ? "done" : "available";
      }
      return "available";
    })();

    return {
      ...entry,
      score,
      hearts,
      state,
      isReview: entry.kind === "review",
    };
  }) ?? [];

  const currentLessonData = chapterLessons.find((l) => l.id === currentLessonId);

  const totalDone = chapterLessons.filter((l) => l.state === "done").length;
  const pct = chapterLessons.length > 0 ? Math.round((totalDone / chapterLessons.length) * 100) : 0;

  return (
    <>
      <div className="chapter-back">
        <button type="button" className="btn ghost" onClick={onBack}>
          ← {t("backLbl")}
        </button>
      </div>

      <div className="chapter-head">
        <p className="tiny muted" style={{ margin: 0 }}>
          {t("buildPathTitle")}
        </p>
        <h1 className="chapter-title">{chapter?.name}</h1>
        <span className="chapter-progress">{totalDone}/{chapterLessons.length} · {pct}%</span>
      </div>

      <div className="chapter-content">
        {chapterLessons.map((lesson: LessonData) => (
          <div
            key={lesson.id}
            className={`lesson-card ${lesson.state} ${lesson.isReview ? "review" : ""} ${currentLessonId === lesson.id ? "active" : ""}`}
          >
            <div className="lesson-header">
              <span className="lesson-level">L{lesson.level}</span>
              <span className="lesson-kind">
                {lesson.isReview ? t("reviewLbl") : t("lessonLbl")} · {lesson.questionCount} Q
              </span>
            </div>
            <div className="lesson-body">
              {lesson.isReview ? (
                <p>
                  {t("reviewDesc")} · {lesson.reviewedLessonIds?.length ?? 0} leçons revues
                </p>
              ) : (
                <p>
                  {lesson.termIds?.length ?? 0} termes · {lesson.questionCount} questions
                </p>
              )}
              {(lesson as { state: "locked" | "available" | "done" }).state === "done" && (
                <span className="lesson-score">{Math.round(lesson.score)}%</span>
              )}
            </div>
            {((lesson as { state: "locked" | "available" | "done" }).state) !== "locked" && (
              <button
                className="btn primary"
                onClick={() => {
                  onLessonStart(lesson.id);
                }}
              >
                {((lesson as { state: "locked" | "available" | "done" }).state === "done"
                  ? t("replayLbl")
                  : t("startLbl"))}
              </button>
            )}
          </div>
        ))}
      </div>

      {currentLessonData && currentLessonData.id === currentLessonId && (
        <div className="lesson-progress-track">
          <div className="heart-track">
            <span className="heart-label">♥ {t("heartsLbl")}</span>
            <div className="heart-bar">
              {Array.from({ length: 5 }).map((_, i) => (
                <span
                  key={i}
                  className={`heart-icon ${i < (currentLessonData.hearts ?? 5) ? "full" : "empty"}`}
                  aria-label={t("heartLabel")}
                >
                  ♥
                </span>
              ))}
            </div>
          </div>
        </div>
      )}
    </>
  );
}
