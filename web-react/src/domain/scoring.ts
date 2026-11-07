// Scoring web - miroir de ExerciseChecker.kt (Android).
//
// R-18/R-19 (2026-11-07): canonical normalization (NFD, strip combining
// marks + Arabic harakat, lowercase, drop punctuation) and Levenshtein-based
// near-miss tolerance. Arbitrary substring containment NO LONGER earns
// partial credit (it allowed farming half-XP with 3-letter fragments).

export function normalizeAnswer(s: string): string {
  return s
    .trim()
    .toLowerCase()
    .normalize("NFD")
    .replace(/[\u0640\u064B-\u0652\u0670]/g, "")
    .replace(/\p{Mn}/gu, "")
    .replace(/[^\p{L}\p{N} ]/gu, "")
    .replace(/\s+/g, " ")
    .trim();
}

export function levenshtein(a: string, b: string): number {
  if (a === b) return 0;
  if (a.length === 0) return b.length;
  if (b.length === 0) return a.length;
  let prev = Array.from({ length: b.length + 1 }, (_, i) => i);
  const curr = new Array<number>(b.length + 1);
  for (let i = 0; i < a.length; i++) {
    curr[0] = i + 1;
    for (let j = 0; j < b.length; j++) {
      const cost = a[i] === b[j] ? 0 : 1;
      curr[j + 1] = Math.min(curr[j] + 1, prev[j + 1] + 1, prev[j] + cost);
    }
    for (let j = 0; j <= b.length; j++) prev[j] = curr[j];
  }
  return prev[b.length];
}

/** Score d'un fill_blank : 1 (exact), 0.5 (typo near-miss), 0 (sinon). */
export function fillScore(userAnswer: string, accepted: string): number {
  const u = normalizeAnswer(userAnswer);
  const t = normalizeAnswer(accepted);
  if (!u || !t) return 0;
  if (u === t) return 1;
  // Near-miss (typo) tolerance.
  if (u.length >= 3) {
    const maxDist = u.length <= 4 ? 1 : Math.max(1, Math.min(2, Math.floor(u.length / 10)));
    const d = levenshtein(u, t);
    if (d >= 1 && d <= maxDist) return 0.5;
  }
  return 0;
}