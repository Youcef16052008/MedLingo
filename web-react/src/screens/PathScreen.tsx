import { useMemo, useState } from "react";
import { useT } from "../i18n/useT";
import { useNow } from "../lib/useNow";
import { useAppState, update } from "../domain/store";
import { getTerms } from "../data/load";
import { getStories, isStoryLoaded } from "../learning/stories";
import { pickTodayStory } from "../learning/selection";
import { navigate } from "../lib/router";
import { pickContinueModule, totalDue, type Sm2Map } from "../domain/progress";
import { buildPath, type PathNode } from "../domain/path";
import { goalProgress, buyFreeze, FREEZE_COST_GEMS, last7Days, goalStreakOf } from "../domain/gamification";
import { Medi } from "../components/Medi";
import { GoalRing } from "../components/GoalRing";
import { RewardPopup, type RewardData } from "../components/RewardPopup";
import type { Term } from "../types";

/**
 * « Perle du jour » : tirage déterministe par date (B10) — le même terme toute
 * la journée, un autre le lendemain. Indexe sur le jour civil local.
 */
export function pearlOfDay(terms: Term[], now: number): Term | undefined {
  const withPearl = terms.filter((x) => x.pearl && x.pearl.trim().length > 0);
  if (!withPearl.length) return undefined;
  const d = new Date(now);
  const day = Math.floor(Date.UTC(d.getFullYear(), d.getMonth(), d.getDate()) / 86_400_000);
  return withPearl[((day % withPearl.length) + withPearl.length) % withPearl.length];
}

/**
 * Carte « Ma séance d'aujourd'hui » (guide §2.2) : sélection déterministe —
 * reprise > expressions échues > jamais vue > plus anciennement terminée
 * (RISK-004). Invisible tant que le contenu n'est pas chargé — la carte ne
 * doit jamais bloquer le parcours.
 */
export function TodayStoryCard() {
  const { t, lang } = useT();
  const s = useAppState();
  const now = useNow();

  if (!isStoryLoaded()) return null;
  const story = pickTodayStory({
    stories: getStories(),
    exprSm2: s.exprSm2,
    progress: s.storyProgress,
    now,
  });
  if (!story) return null;

  const saved = s.storyProgress[story.storyId];
  const resumable = !!saved && saved.completedAt === 0 && saved.savedAt > 0;
  const title = story.title[lang] || story.title.fr;
  // Progression du pilote (seules les histoires publiées comptent).
  const allStories = getStories();
  const done = allStories.filter((x) => {
    const p = s.storyProgress[x.storyId];
    return !!p && p.completedAt > 0;
  }).length;

  return (
    <section className="card" style={{ borderLeft: "4px solid var(--accent, #2e7d32)" }}>
      <span className="pearl-badge">📖 {t("storyCardTitle")}</span>
      <h3 style={{ marginTop: 8 }}>{title}</h3>
      <p className="tiny muted">
        {t("storyCardSub")} · {story.durationMin} {t("storyMinutes")}
        {allStories.length > 1 ? ` · ${done}/${allStories.length}` : ""}
      </p>
      <button
        type="button"
        className="btn block"
        onClick={() => navigate(`story/${story.storyId}`)}
      >
        {resumable ? t("storyResume") : t("storyStart")}
      </button>
    </section>
  );
}

export function PathScreen() {
  const { t, lang } = useT();
  const s = useAppState();
  const [reward, setReward] = useState<RewardData | null>(null);

  const now = useNow();
  const goal = goalProgress(s, now);
  const units = useMemo(
    // BUG-002 : sans ses arguments, buildPath() prend {} → tout niveau ≥ 2
    // restait « locked » à vie et aucune leçon n'apparaissait terminée.
    () => buildPath(s.lessonBest, s.moduleBest),
    [s.lessonBest, s.moduleBest]
  );
  const sm2 = s.sm2 as Sm2Map;
  const allDue = totalDue(sm2, now);
  const terms = getTerms();
  const pearl = pearlOfDay(terms, now);
  const cont = pickContinueModule(sm2, now);

  const hour = new Date(now).getHours();
  const greet =
    hour < 12 ? t("greetMorning") : hour < 18 ? t("greetAfternoon") : t("greetEvening");

  const mood = goal.done ? "celebrating" : s.streak === 0 ? "motivating" : "happy";

  const onFreeze = () => {
    update((d) => {
      buyFreeze(d);
    });
  };

  const openNode = (n: PathNode) => {
    if (n.state === "locked") return; // nœud désactivé : « 70 % requis » via tooltip
    window.location.hash = `#/quiz?module=${n.moduleId}&level=${n.level}`;
  };

  return (
    <>
      {reward && <RewardPopup data={reward} onContinue={() => setReward(null)} />}

      {/* En-tête : Medi + anneau d'objectif + série/congélation + caisse */}
      <section className="card path-head">
        <div className="path-head-top">
          <Medi mood={mood} message={`${greet} · ${cont[lang]}`} />
          <GoalRing pct={goal.pct} done={goal.done} />
        </div>

        <p className="tiny path-goal">
          {goal.today} / {goal.goal} {t("statXp")} · {allDue} {t("cardsDue")}
        </p>

        {s.reviewQueue.filter((x) => x.status === "unlocked").length > 0 && (
          <a
            className="btn small block"
            href="#/review"
            style={{ marginTop: 8, textAlign: "center" }}
          >
            📥 {s.reviewQueue.filter((x) => x.status === "unlocked").length}{" "}
            {t("reviewHubTitle")}
          </a>
        )}

        {/* WS4 levier 2 : calendrier hebdo de l'objectif — 7 cellules
            « tracé ECG » (objectif atteint / XP partiel / rien). */}
        <div
          className="streak-row"
          style={{ gap: 4, justifyContent: "space-between", marginTop: 8 }}
          aria-label={t("cardsDue")}
        >
          {last7Days(now).map((day) => {
            const xp = s.weeklyXp[day] ?? 0;
            const hit = xp >= s.dailyGoal && s.dailyGoal > 0;
            const some = xp > 0;
            return (
              <div
                key={day}
                title={`${day} · ${xp} XP`}
                style={{
                  flex: 1,
                  height: 26,
                  borderRadius: 6,
                  display: "flex",
                  alignItems: "center",
                  justifyContent: "center",
                  fontSize: 11,
                  fontWeight: hit ? 700 : 400,
                  color: hit ? "#fff" : "var(--muted, #5C6B63)",
                  background: hit ? "#0E8A5F" : some ? "rgba(14,138,95,.25)" : "rgba(0,0,0,.06)",
                }}
              >
                {hit ? "✓" : some ? "·" : ""}
              </div>
            );
          })}
        </div>

        {/* WS4 : la cadence d'objectifs consécutifs, lisible sous le calendrier. */}
        {goalStreakOf(s, now) > 0 && (
          <p className="tiny path-goal" style={{ marginTop: 4 }}>
            🎯 {goalStreakOf(s, now)} {goalStreakOf(s, now) === 1 ? "jour d'objectif d'affilée" : "jours d'objectif d'affilée"} · coffre bonus tous les 3
          </p>
        )}

        <div className="streak-row">
          <span className="streak-pill">
            🔥 {s.streak} {t("statStreak")}
          </span>
          <span className="streak-pill">❄️ {s.freezeCount}</span>
          <button
            type="button"
            className="btn small freeze-btn"
            disabled={s.gems < FREEZE_COST_GEMS}
            onClick={onFreeze}
          >
            ❄️ {t("freezeBuy")} · {FREEZE_COST_GEMS} 💎
          </button>
        </div>

        {s.pendingChests > 0 && (
          <button
            type="button"
            className="chest-badge"
            onClick={() => setReward({ xp: 0, gems: 0, goalHit: false, trophies: [] })}
          >
            📦 {t("rewardChest")} ×{s.pendingChests}
          </button>
        )}
      </section>

      {/* Ma séance d'aujourd'hui — mode histoire (guide d'intégration §2.2) */}
      <TodayStoryCard />

      {/* Unités : 15 modules, 6 nœuds chacun */}
      <div className="section-head">
        <h1>{t("pathTitle")}</h1>
        <span className="tiny">{t("pathSub")}</span>
      </div>

      <div className="path-units">
        {units.map((unit) => {
          const name = lang === "ar" ? unit.module.ar : lang === "en" ? unit.module.en : unit.module.fr;
          const pct = Math.round((unit.completed / unit.nodes.length) * 100);
          const nextIdx = unit.nodes.findIndex((n) => n.state === "available");
          return (
            <section className="path-unit" key={unit.module.id}>
              <header className="unit-banner" style={{ background: unit.module.color }}>
                <span className="unit-ico" aria-hidden="true">
                  {unit.module.icon}
                </span>
                <div className="unit-name">
                  <h3>{name}</h3>
                  <p>
                    {unit.completed}/{unit.nodes.length} · {pct}%
                  </p>
                </div>
                <span className="unit-medal" aria-hidden="true">
                  {pct === 100 ? "👑" : pct >= 50 ? "🥇" : "📘"}
                </span>
              </header>

              <div className="unit-path">
                {unit.nodes.map((n, idx) => {
                  const done = n.state === "done";
                  const locked = n.state === "locked";
                  const cls = [
                    "path-node",
                    n.state,
                    n.isBoss ? "boss" : "",
                    !done && !locked && idx === nextIdx ? "next" : "",
                    done && n.score >= 90 ? "gold" : done && n.score >= 80 ? "silver" : "",
                  ]
                    .filter(Boolean)
                    .join(" ");
                  const label = n.isBoss ? "👑" : done ? "✅" : locked ? "🔒" : "⭐";
                  return (
                    <button
                      key={n.level}
                      type="button"
                      className={cls}
                      style={{ marginInlineStart: idx % 2 ? 56 : 0 }}
                      disabled={locked}
                      onClick={() => openNode(n)}
                      aria-label={`${name} · L${n.level}${n.isBoss ? " BOSS" : ""}`}
                      title={locked ? t("pathLockedText") : `${t("lessonLbl")} ${n.level}`}
                    >
                      <span className="node-face" aria-hidden="true">
                        {label}
                      </span>
                      <span className="node-meta" aria-hidden="true">
                        {n.isBoss ? "BOSS" : done ? `${n.score}%` : locked ? "" : `L${n.level}`}
                      </span>
                    </button>
                  );
                })}
              </div>
            </section>
          );
        })}
      </div>

      {/* Perle clinique du jour (relocalisée depuis l'ancien Home) */}
      {pearl && (
        <>
          <div className="section-head">
            <h2>{t("pearlTitle")}</h2>
          </div>
          <article className="card pearl">
            <span className="pearl-badge">💡 {t("pearlLbl")}</span>
            <h3 className="pearl-term">{pearl.en}</h3>
            <p className="pearl-body">{pearl.pearl}</p>
            {pearl.mnemo && (
              <p className="pearl-mnemo">
                <b>MNEMO</b> · {pearl.mnemo}
              </p>
            )}
          </article>
        </>
      )}
    </>
  );
}
