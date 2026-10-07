import { useT } from "../i18n/useT";

interface Props {
  index: number;
  total: number;
  onExit: () => void;
}

/** Barre de progression d'une session (flashcards ou quiz) + bouton quitter. */
export function SessionTop({ index, total, onExit }: Props) {
  const { t } = useT();
  // B20 : la barre suit le libellé `index+1/total` — 100 % sur la dernière
  // carte (l'ancienne formule plafonnait à (n−1)/n).
  const pct = Math.min(100, Math.round(((index + 1) / Math.max(1, total)) * 100));

  return (
    <div className="session-top">
      <button
        type="button"
        className="icon-btn"
        onClick={onExit}
        title={t("exitLbl")}
        aria-label={t("exitLbl")}
      >
        ✕
      </button>
      <div className="progress-track">
        <div className="progress-fill" style={{ width: `${pct}%` }} />
      </div>
      <span className="tiny" style={{ fontFamily: "var(--font-display)", fontWeight: 600 }}>
        {index + 1}/{total}
      </span>
    </div>
  );
}
