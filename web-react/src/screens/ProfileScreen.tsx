import { useT } from "../i18n/useT";
import type { TKey } from "../i18n";
import { useAppState, resetProgress, update } from "../domain/store";
import { getTerms } from "../data/load";
import {
  DAILY_GOAL_OPTIONS,
  FREEZE_COST_GEMS,
  buyFreeze,
} from "../domain/gamification";
import { TROPHIES } from "../domain/trophies";
import { useDialog } from "../components/DialogProvider";
import { useToast } from "../components/ToastProvider";
import type { Lang } from "../types";

const LANGS: { id: Lang; label: string }[] = [
  { id: "fr", label: "FR" },
  { id: "en", label: "EN" },
  { id: "ar", label: "AR" },
];

export function ProfileScreen() {
  const { t, lang, setLang } = useT();
  const { open } = useDialog();
  const toast = useToast();
  const s = useAppState();

  const total = getTerms().length;
  const learned = Object.keys(s.sm2).length;
  const acc = s.answered ? Math.round((s.correct / s.answered) * 100) : 0;
  const master = Object.values(s.sm2).filter((x) => x.rep >= 3).length;

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
    <>
      <div className="section-head" style={{ marginTop: 0 }}>
        <h1>{t("profileTitle")}</h1>
      </div>

      <div className="stat-row">
        <div className="stat-tile">
          <b>{s.streak}</b>
          <span>🔥 {t("statStreak")}</span>
        </div>
        <div className="stat-tile">
          <b>{s.xp}</b>
          <span>⭐ {t("statXp")}</span>
        </div>
        <div className="stat-tile">
          <b>{acc}%</b>
          <span>🎯 {t("statAccuracy")}</span>
        </div>
      </div>

      <div className="card" style={{ marginTop: 14 }}>
        <div className="setting-row">
          <span>{t("masteredLbl")}</span>
          <b style={{ fontFamily: "var(--font-display)" }}>
            {learned} / {total}
          </b>
        </div>
        <div className="setting-row">
          <span>🎓 {t("masterLbl")}</span>
          <b style={{ fontFamily: "var(--font-display)" }}>{master}</b>
        </div>
        <div className="setting-row">
          <span>🃏 {t("reviewedLbl")}</span>
          <b style={{ fontFamily: "var(--font-display)" }}>{s.flashReviewed}</b>
        </div>
        <div className="setting-row">
          <span>📝 {t("answeredLbl")}</span>
          <b style={{ fontFamily: "var(--font-display)" }}>{s.answered}</b>
        </div>
        <div className="setting-row">
          <span>🏁 {t("quizTitle")}</span>
          <b style={{ fontFamily: "var(--font-display)" }}>{s.quizDone}</b>
        </div>
        <div className="setting-row">
          <span>💎 {t("statGems")}</span>
          <b style={{ fontFamily: "var(--font-display)" }}>{s.gems}</b>
        </div>
      </div>

      <div className="section-head">
        <h2>{t("goalTitle")}</h2>
        <span className="tiny">
          🔥 {s.streak} · ❄️ {s.freezeCount}
        </span>
      </div>
      <div className="card">
        <div className="goal-seg" role="group" aria-label={t("goalTitle")}>
          {DAILY_GOAL_OPTIONS.map((g) => (
            <button
              key={g}
              type="button"
              className={s.dailyGoal === g ? "active" : undefined}
              aria-pressed={s.dailyGoal === g}
              onClick={() => update((d) => void (d.dailyGoal = g))}
            >
              {g} XP
            </button>
          ))}
        </div>
        <div className="setting-row" style={{ marginTop: 10 }}>
          <span>❄️ {t("freezeBuy")}</span>
          <button
            type="button"
            className="btn small"
            disabled={s.gems < FREEZE_COST_GEMS}
            onClick={() => update((d) => void buyFreeze(d))}
          >
            {FREEZE_COST_GEMS} 💎
          </button>
        </div>
      </div>

      <div className="section-head">
        <h2>{t("trophyTitle")}</h2>
        <span className="tiny">
          {Object.keys(s.trophies).length}/{TROPHIES.length}
        </span>
      </div>
      <div className="trophy-grid">
        {TROPHIES.map((tr) => {
          const at = s.trophies[tr.id];
          const won = typeof at === "number" && at > 0;
          const date = won
            ? new Date(at).toLocaleDateString(lang === "ar" ? "ar-DZ" : lang)
            : t(tr.descKey as TKey);
          return (
            <div key={tr.id} className={`trophy ${won ? "won" : "locked"}`} title={t(tr.descKey as TKey)}>
              <span className="trophy-ico" aria-hidden="true">
                {won ? tr.icon : "🔒"}
              </span>
              <b>{t(tr.titleKey as TKey)}</b>
              <span className="tiny">{date}</span>
            </div>
          );
        })}
      </div>

      <div className="section-head">
        <h2>{t("langLbl")}</h2>
      </div>
      <div className="card">
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
    </>
  );
}
