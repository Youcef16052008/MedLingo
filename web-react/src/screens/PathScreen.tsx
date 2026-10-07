import { useMemo, useState } from "react";
import { useT } from "../i18n/useT";
import { useNow } from "../lib/useNow";
import { useAppState, update } from "../domain/store";
import { getTerms } from "../data/load";
import { pickContinueModule, totalDue, type Sm2Map } from "../domain/progress";
import { buildPath, type PathNode } from "../domain/path";
import { goalProgress, buyFreeze, FREEZE_COST_GEMS } from "../domain/gamification";
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

export function PathScreen() {
  const { t, lang } = useT();
  const s = useAppState();
  const [reward, setReward] = useState<RewardData | null>(null);

  const now = useNow();
  const goal = goalProgress(s, now);
  const units = useMemo(
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
