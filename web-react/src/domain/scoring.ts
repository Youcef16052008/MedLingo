/**
 * Score d'un exercice `fill_blank` — miroir de `ExerciseChecker.checkFill`
 * côté Android : exact = 1, partiel (fuzzy) = 0.5, sinon 0.
 *
 * Le crédit partiel n'est accordé qu'à des réponses significatives (≥ 3
 * caractères) : une réponse de 1–2 caractères comme "a" satisferait
 * trivialement `includes()` contre n'importe quelle réponse acceptée.
 */
export function fillScore(userAnswer: string, accepted: string): number {
  const user = userAnswer.trim().toLowerCase();
  const target = accepted.trim().toLowerCase();
  if (!user || !target) return 0;
  if (user === target) return 1;
  if (user.length >= 3 && (user.includes(target) || target.includes(user))) return 0.5;
  return 0;
}
