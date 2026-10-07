import { useT } from "../i18n/useT";
import { useAppState } from "../domain/store";
import { totalDue, type Sm2Map } from "../domain/progress";
import { goalProgress } from "../domain/gamification";
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
        <span className="stat-chip gems" title={t("goalTitle")}>
          ⭐ <b>{goal.today}</b> / {goal.goal}
        </span>
      </div>

      <div className="practice-head">
        <GoalRing pct={goal.pct} done={goal.done} size={56} />
        <Medi mood={goal.done ? "celebrating" : due > 0 ? "motivating" : "happy"} />
      </div>

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
