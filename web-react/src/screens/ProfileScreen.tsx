import { useT } from "../i18n/useT";
import type { TKey } from "../i18n";
import { useAppState, resetProgress, update } from "../domain/store";
import {
  FREEZE_COST_GEMS,
  buyFreeze,
  goalProgress,
} from "../domain/gamification";
import { TROPHIES } from "../domain/trophies";
import { GoalRing } from "../components/GoalRing";
import { useDialog } from "../components/DialogProvider";
import { useToast } from "../components/ToastProvider";
import type { Lang } from "../types";

const LANGS: { id: Lang; label: string }[] = [
  { id: "fr", label: "FR" },
  { id: "en", label: "EN" },
  { id: "ar", label: "AR" },
];

/* Defaults du profil — MedLinguaViewModel.kt : Dr. Youcef, MED_STUDENT,
   YEAR_1, INTERMEDIATE. Le web n'a pas l'onboarding diagnostic ; ces
   valeurs sont les mêmes que côté Android au premier lancement. */
const PROFILE = {
  name: "Dr. Youcef",
  typeIcon: "🎓",
  typeLabel: { fr: "Étudiant en Médecine", en: "Medical Student", ar: "طالب طب" },
  yearLabel: { fr: "1ère Année (PCEM1)", en: "1st Year (PCEM1)", ar: "السنة الأولى" },
  levelEmoji: "📙",
  levelLabel: { fr: "Intermédiaire", en: "Intermediate", ar: "متوسط" },
} as const;

export function ProfileScreen() {
  const { t, lang, setLang } = useT();
  const { open } = useDialog();
  const toast = useToast();
  const s = useAppState();

  const acc = s.answered ? Math.round((s.correct / s.answered) * 100) : 0;
  const goal = goalProgress(s, Date.now());
  const canBuy = s.gems >= FREEZE_COST_GEMS;
  const wonCount = TROPHIES.filter(
    (tr) => typeof s.trophies[tr.id] === "number" && s.trophies[tr.id] > 0
  ).length;

  const askReset = async () => {
    const r = await open({
      icon: "⚠️",
      title: t("resetTitle"),
      text: t("resetText"),
      actions: [
        { id: "cancel", label: t("cancelLbl") },
        { id: "reset", label: t("resetConfirmLbl"), cls: "danger" },
      ],
    });
    if (r === "reset") {
      resetProgress(Date.now());
      toast("✓");
    }
  };

  return (
    <div className="profile-screen">
      {/* ===== Carte profil étudiant (gradient #146B43 → #15803D) ===== */}
      <div className="card profile-hero">
        <div className="profile-hero-row">
          <span className="profile-avatar" aria-hidden="true">
            {PROFILE.typeIcon}
          </span>
          <div>
            <b className="profile-name">{PROFILE.name}</b>
            <span className="profile-type">
              {PROFILE.typeLabel[lang]} • {PROFILE.yearLabel[lang]}
            </span>
            <span className="profile-level">
              {PROFILE.levelEmoji} {PROFILE.levelLabel[lang]}
            </span>
          </div>
        </div>
      </div>

      {/* ===== Objectif du jour + série + congélation ===== */}
      <div className="card profile-goal">
        <div className="profile-goal-top">
          <div>
            <b className="profile-card-title">{t("goalTitle")}</b>
            <span className="profile-goal-line">
              {goal.today} / {goal.goal} {t("statXp")}
            </span>
            <span className="profile-streak-line">
              🔥 {s.streak} {t("statStreak")} · ❄️ {s.freezeCount}
            </span>
          </div>
          <GoalRing pct={goal.pct} done={goal.done} size={56} />
        </div>
        <button
          type="button"
          className="freeze-buy"
          disabled={!canBuy}
          onClick={() => update((d) => void buyFreeze(d))}
        >
          ❄️ {t("freezeBuy")} · {FREEZE_COST_GEMS} 💎
        </button>
      </div>

      {/* ===== Grille des 12 trophées ===== */}
      <div className="card profile-trophies">
        <div className="profile-trophies-head">
          <b className="profile-card-title">{t("trophyTitle")}</b>
          <span className="profile-trophies-count">
            {wonCount}/{TROPHIES.length}
          </span>
        </div>
        <div className="trophy-grid">
          {TROPHIES.map((tr) => {
            const at = s.trophies[tr.id];
            const won = typeof at === "number" && at > 0;
            return (
              <div
                key={tr.id}
                className={`trophy ${won ? "won" : "locked"}`}
                title={t(tr.descKey as TKey)}
              >
                <span className="trophy-ico" aria-hidden="true">
                  {tr.icon}
                </span>
                <b>{t(tr.titleKey as TKey)}</b>
              </div>
            );
          })}
        </div>
      </div>

      {/* ===== Rapport d'apprentissage (MetricBox) ===== */}
      <div className="card profile-report">
        <b className="profile-card-title">{t("answeredLbl")}</b>
        <div className="metric-grid">
          <div className="metric-box">
            <span aria-hidden="true">📝</span>
            <b>{s.answered}</b>
            <span>{t("answeredLbl")}</span>
          </div>
          <div className="metric-box">
            <span aria-hidden="true">🏆</span>
            <b>{s.quizDone}</b>
            <span>{t("quizTitle")}</span>
          </div>
          <div className="metric-box">
            <span aria-hidden="true">✅</span>
            <b>{s.correct}</b>
            <span>{t("correctLbl")}</span>
          </div>
          <div className="metric-box">
            <span aria-hidden="true">🎯</span>
            <b>{acc}%</b>
            <span>{t("statAccuracy")}</span>
          </div>
        </div>
      </div>

      {/* ===== Langue ===== */}
      <div className="card profile-lang">
        <b className="profile-card-title">{t("langLbl")}</b>
        <div className="lang-switch">
          {LANGS.map((l) => (
            <button
              key={l.id}
              type="button"
              className={lang === l.id ? "active" : undefined}
              onClick={() => setLang(l.id)}
            >
              {l.label}
            </button>
          ))}
        </div>
        <div className="setting-row" style={{ marginTop: 8 }}>
          <button type="button" className="btn danger small" onClick={askReset}>
            ↺ {t("resetLbl")}
          </button>
        </div>
      </div>

      <p className="tiny" style={{ textAlign: "center", marginTop: 24 }}>
        {t("aboutTxt")}
      </p>
    </div>
  );
}
