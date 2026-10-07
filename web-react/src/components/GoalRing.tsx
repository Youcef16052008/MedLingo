/**
 * Anneau d'objectif quotidien (SVG, `stroke-dasharray`) — spec §5.1/§5.2.
 * Utilisé en grand dans l'en-tête du Parcours, en petit dans le hub Révision.
 */
export function GoalRing({
  pct,
  done,
  size = 74,
}: {
  pct: number;
  done: boolean;
  /** Diamètre en px. */
  size?: number;
}) {
  const r = 34;
  const c = 2 * Math.PI * r;
  const offset = c - (Math.min(100, pct) / 100) * c;
  return (
    <div
      className={`goal-ring ${done ? "done" : ""} ${size <= 56 ? "mini" : ""}`}
      style={{ width: size, height: size }}
      role="img"
      aria-label={`${pct}%`}
      title={`${pct}%`}
    >
      <svg viewBox="0 0 80 80" aria-hidden="true">
        <circle className="goal-track" cx="40" cy="40" r={r} />
        <circle
          className="goal-fill"
          cx="40"
          cy="40"
          r={r}
          strokeDasharray={c}
          strokeDashoffset={offset}
        />
      </svg>
      <span aria-hidden="true">{done ? "🎯" : "⭐"}</span>
    </div>
  );
}
