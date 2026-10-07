import { useT } from "../i18n/useT";

export type MediMood = "happy" | "motivating" | "celebrating" | "frustrated";

const MOOD_ICON: Record<MediMood, string> = {
  happy: "✨",
  motivating: "💪",
  celebrating: "🎉",
  frustrated: "😅",
};

const MOOD_KEY = {
  happy: "mediHappy",
  motivating: "mediMotivating",
  celebrating: "mediCelebrating",
  frustrated: "mediFrustrated",
} as const;

/**
 * Medi 🩺 — la mascotte du compagnon d'étude (bulle de dialogue contextuelle).
 * Pas d'asset externe : cercle emoji + bulle, comme le reste de la PWA.
 */
export function Medi({
  mood = "happy",
  message,
}: {
  mood?: MediMood;
  /** Texte libre (sinon la clé i18n du mood est utilisée). */
  message?: string;
}) {
  const { t } = useT();
  const text = message ?? t(MOOD_KEY[mood]);

  return (
    <div className={`medi mood-${mood}`}>
      <span className="medi-avatar" aria-hidden="true">
        🩺
        <span className="medi-badge" aria-hidden="true">
          {MOOD_ICON[mood]}
        </span>
      </span>
      <p className="medi-bubble">{text}</p>
    </div>
  );
}
