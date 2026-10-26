/**
 * Tests du générateur en masse — port web de `BulkExerciseGeneratorTest.kt`.
 *
 * Ces tests garantissent les invariants de l'UI : si l'un échoue, l'écran de
 * quiz plante ou marque une bonne réponse comme fausse.
 */
import { describe, it, expect, beforeAll } from "vitest";
import { readFileSync } from "node:fs";
import { join, dirname } from "node:path";
import { fileURLToPath } from "node:url";
import { loadOnce, getTerms, setTestData } from "../../data/load";
import { MODULES } from "../../data/modules";
import {
  generateBulkExercises,
  generateBulkModule,
  resetBulkCache,
  EXERCISES_PER_TERM,
} from "../bulkExercises";
import type { Exercise, Term } from "../../types";

/**
 * Timeout étendu pour ce fichier : `generateBulkExercises()` produit 13 512
 * exercices (classement des distracteurs par module) et prend ~8 s. Le défaut
 * de vitest est 5 s.
 */
const BULK_TIMEOUT = 120_000;

const dir = dirname(fileURLToPath(import.meta.url));
const root = join(dir, "..", "..", ".."); // web-react/

/** Charge `terms.json` depuis le disque (Node) puis branche `termsOfModule`. */
async function loadTestData(): Promise<void> {
  const terms = JSON.parse(
    readFileSync(join(root, "public", "data", "terms.json"), "utf8")
  ) as Term[];
  setTestData(terms);
  await loadOnce().catch(() => {
    /* `loadOnce` refait un fetch qui échoue en Node : `setTestData` a déjà
     * branché `termsOfModule`, c'est tout ce dont le générateur a besoin. */
  });
}

const ALLOWED_TYPES = new Set([
  "mcq",
  "fill_blank",
  "matching",
  "sentence_order",
  "reading",
  "clinical_case",
]);

// Les tests attendent que les données soient chargées. Dans l'environnement de
// test Node, `fetch("/data/terms.json")` échoue (URL relative) : on charge
// directement depuis le disque, comme le fait `i18n.test.ts`.
let exercises: Exercise[] = [];

beforeAll(async () => {
  await loadTestData();
  exercises = generateBulkExercises();
}, 120_000);

describe("bulkExercises — volume", () => {
  it("dépasse 10 000 exercices", () => {
    expect(exercises.length, `total = ${exercises.length}`).toBeGreaterThanOrEqual(10_000);
  });

  it("chaque terme a exactement 3 exercices", () => {
    const terms = getTerms();
    expect(exercises.length).toBe(terms.length * EXERCISES_PER_TERM);
  });

  it("les 15 modules sont couverts", () => {
    const covered = new Set(exercises.map((e) => e.module));
    for (const m of MODULES) {
      expect(covered.has(m.fr), `module non couvert : ${m.fr}`).toBe(true);
    }
  });
});

describe("bulkExercises — intégrité", () => {
  it("aucun id n'est dupliqué", () => {
    const ids = exercises.map((e) => e.id);
    const dupes = ids.filter((id, i) => ids.indexOf(id) !== i);
    expect(dupes.length, `${dupes.length} ids dupliqués`).toBe(0);
  });

  it("tous les ids sont négatifs", () => {
    // Les exercices générés ne doivent jamais collisionner avec les 112
    // exercices écrits main (ids positifs dans exercises.json).
    expect(exercises.every((e) => typeof e.id === "number" && e.id < 0)).toBe(true);
  });

  it("tous les types sont supportés par l'UI", () => {
    for (const e of exercises) {
      expect(ALLOWED_TYPES.has(e.type), `type non géré : ${e.type}`).toBe(true);
    }
  });

  it("aucun champ obligatoire n'est vide", () => {
    for (const e of exercises) {
      for (const [name, value] of [
        ["qEn", e.qEn],
        ["qFr", e.qFr],
        ["qAr", e.qAr],
        ["answer", e.answer],
        ["expEn", e.expEn],
        ["module", e.module],
      ] as const) {
        expect(String(value).trim().length, `ex ${e.id} : ${name} vide`).toBeGreaterThan(0);
      }
    }
  });
});

describe("bulkExercises — contrats UI", () => {
  it("la réponse est toujours une option des QCM", () => {
    // `McqQuestionView` compare le texte de l'option à `answer` : si la réponse
    // n'est pas dans `options`, l'exercice est injouable.
    //
    // `fill_blank` est exclu : c'est une saisie libre (`fillScore`), où
    // `options` ne contient que la réponse acceptée (miroir d'`acceptedAnswers`
    // côté Android).
    const mcqLike = exercises.filter((x) => x.type === "mcq");
    for (const e of mcqLike) {
      expect(e.options.length, `ex ${e.id} : options vides`).toBeGreaterThan(0);
      const normalized = e.options.map((o) => o.trim().toLowerCase());
      expect(
        normalized.includes(e.answer.trim().toLowerCase()),
        `ex ${e.id} : '${e.answer}' n'est pas dans ${e.options}`
      ).toBe(true);
    }
  });

  it("les QCM ont entre 2 et 4 options uniques", () => {
    for (const e of exercises.filter((x) => x.type === "mcq")) {
      expect(e.options.length, `ex ${e.id} : ${e.options.length} options`).toBeGreaterThanOrEqual(2);
      expect(e.options.length, `ex ${e.id} : ${e.options.length} options`).toBeLessThanOrEqual(4);
      const normalized = e.options.map((o) => o.trim().toLowerCase());
      expect(new Set(normalized).size, `ex ${e.id} : options dupliquées`).toBe(normalized.length);
    }
  });

  it("le matching a 4 paires bien formées", () => {
    for (const e of exercises.filter((x) => x.type === "matching")) {
      expect(e.options.length, `ex ${e.id} : ${e.options.length} paires`).toBe(4);
      for (const pair of e.options) {
        const parts = pair.split(":");
        expect(parts.length, `ex ${e.id} : paire malformée '${pair}'`).toBe(2);
        for (const part of parts) {
          expect(part.trim().length, `ex ${e.id} : côté de paire vide`).toBeGreaterThan(0);
        }
      }
      // `checkMatchFromRaw` compare `answer` à `options` : ils doivent être
      // identiques pour que les paires soient les bonnes.
      expect(e.answer, `ex ${e.id} : answer != options`).toBe(e.options.join("|"));
    }
  });

  it("le sentence_order a des mots qui reconstituent la phrase", () => {
    for (const e of exercises.filter((x) => x.type === "sentence_order")) {
      const answer = e.answer.split(" ").filter((w) => w.trim().length > 0);
      expect(answer.length, `ex ${e.id} : phrase vide`).toBeGreaterThan(0);
      expect(new Set(answer), `ex ${e.id} : le pool ne correspond pas à la phrase`).toEqual(
        new Set(e.options)
      );
    }
  });

  it("le reading et le clinical_case ont un contexte non vide", () => {
    for (const e of exercises.filter(
      (x) => x.type === "reading" || x.type === "clinical_case"
    )) {
      expect(e.ctxEn.trim().length, `ex ${e.id} (${e.type}) : ctxEn vide`).toBeGreaterThan(0);
      expect(e.ctxFr.trim().length, `ex ${e.id} (${e.type}) : ctxFr vide`).toBeGreaterThan(0);
      expect(e.qEn.trim().length, `ex ${e.id} (${e.type}) : qEn vide`).toBeGreaterThan(0);
    }
  });

  it("les points suivent la table android L1..L6", () => {
    const expected: Record<number, number> = { 1: 10, 2: 15, 3: 20, 4: 25, 5: 30, 6: 35 };
    for (const e of exercises) {
      expect(e.level, `ex ${e.id} : level hors plage`).toBeGreaterThanOrEqual(1);
      expect(e.level, `ex ${e.id} : level hors plage`).toBeLessThanOrEqual(6);
      expect(e.points, `ex ${e.id} (L${e.level}) : points=${e.points}`).toBe(expected[e.level]);
    }
  });
});

/**
 * Indices d'un échantillon représentatif d'une liste (premiers, derniers,
 * un tous les 1 000). Utilisé pour comparer deux générations sans parcourir
 * les 13 512 exercices deux à deux.
 */
function pickProbe(len: number): number[] {
  const probe = new Set<number>();
  for (let i = 0; i < 20; i++) probe.add(i);
  for (let i = 0; i < len; i += 1000) probe.add(i);
  probe.add(len - 1);
  return [...probe].sort((a, b) => a - b);
}

describe("bulkExercises — robustesse", () => {
  it("la génération est déterministe (et survit à un reset de cache)", () => {
    // La génération est mémoïsée : le deuxième appel sans reset rendrait la
    // même instance et ne prouverait rien. On vide le cache entre les deux
    // appels pour vérifier la vraie garantit : mêmes seeds → même corpus.
    const first = generateBulkExercises();
    resetBulkCache();
    const second = generateBulkExercises();
    expect(first.length).toBe(second.length);
    const probe = pickProbe(first.length);
    for (const i of probe) {
      const a = first[i];
      const b = second[i];
      expect(a.id, `ex #${i} : id divergent`).toBe(b.id);
      expect(a.type, `ex #${i} : type divergent`).toBe(b.type);
      expect(a.qEn, `ex #${i} : qEn divergent`).toBe(b.qEn);
      expect(a.answer, `ex #${i} : answer divergent`).toBe(b.answer);
      expect(a.options.join("|"), `ex #${i} : options divergentes`).toBe(b.options.join("|"));
    }
  }, BULK_TIMEOUT);

  it("le cache est mémoïsé : deuxième appel instantané et identique", () => {
    resetBulkCache();
    const first = generateBulkExercises(); // ~5-12 s : la vraie génération
    const t0 = performance.now();
    const second = generateBulkExercises();
    const dt = performance.now() - t0;
    expect(second).toBe(first); // même instance : aucun recalcul
    expect(dt).toBeLessThan(50); // ms — le recalcul coûterait plusieurs secondes
  }, BULK_TIMEOUT);

  it("un module vide ne plante pas", () => {
    expect(generateBulkModule("ModuleInexistant", [])).toEqual([]);
  });

  it("au moins 3 types différents sont produits", () => {
    // Les 6 types de l'UI : mcq, fill_blank, matching, sentence_order, reading,
    // clinical_case. Le tirage dépend des données disponibles (etymology 23 %,
    // pearl 10 %, mnemo 3 %) ; on exige 3 types au lieu de 6 pour que le test
    // reste robuste si un module manque d'un champ.
    const types = new Set(exercises.map((e) => e.type));
    expect(types.size, `${types.size} types : ${[...types].join(", ")}`).toBeGreaterThanOrEqual(3);
  });
});
