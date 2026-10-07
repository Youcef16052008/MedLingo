import { useEffect, useState } from "react";

/**
 * Horloge murale : re-rend le composant à intervalle régulier pour que les
 * valeurs dérivées de `Date.now()` (compte à rebours de ligue, salutation,
 * badge « à réviser ») ne restent pas figées jusqu'à la prochaine action (B32/B33).
 */
export function useNow(intervalMs = 60_000): number {
  const [now, setNow] = useState(() => Date.now());
  useEffect(() => {
    const id = window.setInterval(() => setNow(Date.now()), intervalMs);
    return () => window.clearInterval(id);
  }, [intervalMs]);
  return now;
}
