import { useT } from "../i18n/useT";
import { useAppState } from "../domain/store";
import { totalDue, type Sm2Map } from "../domain/progress";
import { goalProgress } from "../domain/gamification";
import { masteryOverview, weakestTerms } from "../domain/mastery";
import { termsOfModule, termById } from "../data/load";
import { MODULES, modLabel } from "../data/modules";
import { Medi } from "../components/Medi";
import { GoalRing } from "../components/GoalRing";

/**
 * Hub « Révision » : les quatre portes d'entrée d'entraînement.
 * (Cœur de l'app Duolingo : le joueur vient ici quand il veut réviser.)
 */
export function PracticeScreen() {
  const { t } = useT();
  const s = useAppState();
  const due = totalDue(s.sm2 as Sm2Map, Date.now());
  const goal = goalProgress(s, Date.now());

  const cards: {
    icon: string;
    title: string;
    desc: string;
    href: string;
    badge?: number;
    tone: string;
  }[] = [
    {
      icon: "🔁",
      title: t("practiceDue"),
      desc: t("practiceDueDesc"),
      href: "#/flash/all",
      badge: due,
      tone: "teal",
    },
    {
      icon: "🧠",
      title: t("practiceQuiz"),
      desc: t("practiceQuizDesc"),
      href: "#/quiz",
      tone: "azure",
    },
    {
      icon: "💪",
      title: t("practiceWeak"),
      desc: t("practiceWeakDesc"),
      href: "#/flash/weak",
      tone: "amber",
    },
    {
      icon: "🎓",
      title: t("practiceExam"),
      desc: t("practiceExamDesc"),
      href: "#/quiz?mode=exam",
      tone: "violet",
    },
  ] as const;

  return (
    <>
      <div className="section-head" style={{ marginTop: 0 }}>
        <div>
          <p className="eyebrow">🏋️ {t("practiceSub")}</p>
          <h1>{t("practiceTitle")}</h1>
        </div>
        {/* Chip d'objectif — PracticeScreen.kt : fond #FFF3C4, #B45309,
            rayon 20.dp, 13.sp bold. */}
        <span className="goal-chip" title={t("goalTitle")}>
          ⭐ <b>{goal.today}</b> / {goal.goal}
        </span>
      </div>

      <div className="practice-head">
        <GoalRing pct={goal.pct} done={goal.done} size={56} />
        <Medi mood={goal.done ? "celebrating" : due > 0 ? "motivating" : "happy"} />
      </div>

      {/* WS5.2 — Mes faiblesses : le moteur de maîtrise nomme le module le
          plus faible et les termes à revoir (transparence, prévisibilité). */}
      <WeaknessesPanel />

      <div className="practice-grid">
        {cards.map((c) => (
          <a
            key={c.href + c.title}
            className={`card practice-card ${c.tone}`}
            href={c.href}
            style={{ textDecoration: "none", color: "inherit" }}
          >
            <span className="practice-ico" aria-hidden="true">
              {c.icon}
            </span>
            <div>
              <h3>
                {c.title}
                {c.badge ? <span className="practice-badge">{c.badge}</span> : null}
              </h3>
              <p className="tiny" style={{ margin: "4px 0 0" }}>
                {c.desc}
              </p>
            </div>
          </a>
        ))}
      </div>
    </>
  );
}


/**
 * WS5.2 — panneau « Mes faiblesses » : force unifiée par module (quiz 60 %
 * + santé SM-2 40 %), module le plus faible en tête, termes à revoir avec
 * leur raison (échu / jamais vu / ease bas). Chaque terme pointe vers la
 * flashcard correspondante — la faiblesse devient une action en 1 clic.
 */
function WeaknessesPanel() {
  const { t, lang } = useT();
  const s = useAppState();
  const now = Date.now();
  const sm2 = s.sm2 as Sm2Map;

  // Termes par module (id du registre MODULES ; les données portent le nom FR).
  const termsByModule = MODULES.map((m) => ({
    id: m.id,
    termIds: termsOfModule(m.id).map((term) => term.id),
  })).filter((m) => m.termIds.length > 0);
  const overview = masteryOverview(s.moduleBest, sm2, termsByModule, now);
  const weakest = overview[0];
  if (!weakest) return null;
  const moduleFr = MODULES.find((m) => m.id === weakest.id)?.fr ?? weakest.id;

  const terms = termsOfModule(weakest.id);
  const weak = weakestTerms(
    terms.map((term) => term.id),
    sm2,
    now,
    6
  );

  return (
    <div className="card" style={{ marginTop: 14, padding: 14 }}>
      <div style={{ display: "flex", justifyContent: "space-between", alignItems: "baseline", gap: 8 }}>
        <b>{t("masteryWeakTitle")}</b>
        <span className="tiny muted">
          {t("masteryForce")} : <b style={{ color: "#0B5A3C" }}>{weakest.force}%</b>
        </span>
      </div>
      <p className="tiny" style={{ margin: "4px 0 8px" }}>
        {t("masteryWeakModule")} : <b>{modLabel(moduleFr, lang)}</b> · {weakest.due} {t("cardsDue")} ·{" "}
        {weakest.unseen} {t("masteryUnseen")} ·{" "}
        <a href={`#/quiz?mode=exam&module=${weakest.id}`} style={{ color: "#0B5A3C" }}>
          {t("emdEntry")} →
        </a>
      </p>
      <ul className="plain" style={{ margin: 0 }}>
        {weak.map(({ termId, reason }) => {
          const term = termById(termId);
          if (!term) return null;
          const why =
            reason === "due"
              ? t("masteryReasonDue")
              : reason === "unseen"
                ? t("masteryReasonUnseen")
                : t("masteryReasonEf");
          return (
            <li key={termId} style={{ display: "flex", gap: 8, alignItems: "baseline" }}>
              <a href={`#/flash/${weakest.id}`} style={{ textDecoration: "none", color: "inherit" }}>
                <b>{term.en}</b>
              </a>
              <span className="tiny muted" style={{ flex: 1 }}>
                {term.fr} — {why}
              </span>
            </li>
          );
        })}
      </ul>
    </div>
  );
}
