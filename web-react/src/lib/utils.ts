import type { Lang } from "../types";

/** Mélange copie (Fisher–Yates). */
export function shuffle<T>(a: T[]): T[] {
  const out = a.slice();
  for (let i = out.length - 1; i > 0; i--) {
    const j = Math.floor(Math.random() * (i + 1));
    [out[i], out[j]] = [out[j], out[i]];
  }
  return out;
}

/** Mélange copie avec une source aléatoire injectée → déterministe en test. */
export function shuffleWith<T>(a: T[], rand: () => number): T[] {
  const out = a.slice();
  for (let i = out.length - 1; i > 0; i--) {
    const j = Math.floor(rand() * (i + 1));
    [out[i], out[j]] = [out[j], out[i]];
  }
  return out;
}

/** Normalisation utilisée pour comparer une saisie à une réponse. */
export function norm(s: unknown): string {
  const raw = String(s ?? "");
  // Pas de NFD si la chaîne est déjà ASCII (majorité des libellés) : évite
  // ~60 % de surcoût sur les générations de leçons.
  const folded = /[^\u0000-\u007f]/.test(raw)
    ? // Décomposition NFD + suppression des diacritiques latins : « café » =
      // « cafe » (les signes arabes restent hors de ce bloc, B44).
      raw.normalize("NFD").replace(/[\u0300-\u036f]/g, "")
    : raw;
  return folded
    .toLowerCase()
    .trim()
    .replace(/\s+/g, " ")
    .replace(/[.,;:!?«»"']/g, "");
}

/** Affiche une durée en jours : "7 j" / "7d" / "3 mois". */
export function fmtIv(days: number, lang: Lang): string {
  if (days < 30) return lang === "fr" ? `${days} j` : `${days}d`;
  const m = Math.round(days / 30);
  return lang === "ar" ? `${m} شهرًا` : `${m} mois`;
}

