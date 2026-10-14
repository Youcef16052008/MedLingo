/**
 * Générateur en masse — port TypeScript de `BulkExerciseGenerator.kt`.
 *
 * Produit exactement le même corpus que la version Android : mêmes 13 templates,
 * mêmes seeds (mulberry32 + hashSeed FNV-1a), mêmes ids négatifs. C'est ce qui
 * garantit qu'une leçon est identique sur les deux plateformes — la règle déjà
 * suivie par `lessonQuestions` et `LessonGenerator.kt`.
 *
 * 3 exercices par terme × 4 504 termes = 13 512 exercices.
 *
 * Les 13 templates (ordre = priorité, rares d'abord) :
 *   etymology, clinicalPearl, mnemonic, wordOrder, fillBlank,
 *   definition, pronunciation, reading, clinicalCase, enFr, enAr, frEn, matching
 *
 * Contrats respectés (identiques à la version Kotlin) :
 * - `options` contient toujours la `answer` (sinon l'UI marque l'exercice faux) ;
 * - `matching` : `options` = "terme:trad|…" et `answer` = options.join("|") ;
 * - `sentence_order` : `options` = mots mêlés, `answer` = phrase complète ;
 * - `reading`/`clinical_case` : la vignette va dans `ctxEn`/`ctxFr`/`ctxAr` ;
 * - `points` suit la table L1..L6 → 10/15/20/25/30/35.
 */
import { hashSeed, mulberry32 } from "./leagues";
import { norm, shuffleWith } from "../lib/utils";
import { MODULES } from "../data/modules";
import { onDataChanged, termsOfModule } from "../data/load";
import type { Exercise, ExerciseType, Term } from "../types";

/** Nombre d'exercices produits pour chaque terme. */
export const EXERCISES_PER_TERM = 3;

/** Nombre d'options d'un QCM (réponse + 3 distracteurs). */
const MCQ_OPTIONS = 4;

/** Nombre de paires d'un exercice d'appariement. */
const MATCH_PAIRS = 4;

/** Les 13 templates, dans l'ordre de priorité (rares d'abord). */
const KINDS = [
  "etymology",
  "clinicalPearl",
  "mnemonic",
  "wordOrder",
  "fillBlank",
  "definition",
  "pronunciation",
  "reading",
  "clinicalCase",
  "enFr",
  "enAr",
  "frEn",
  "matching",
] as const;

type Kind = (typeof KINDS)[number];

/**
 * Pools de distracteurs pré-classés pour un module.
 *
 * Calculer le classement par ressemblance coûte O(taille_du_module²) : on le
 * fait **une fois par terme** puis on réutilise le résultat pour les 3
 * exercices qui demandent des distracteurs. La version naive réutilisait
 * `moduleTerms.map(...)` à chaque exercice et dépassait la minute.
 */
interface ModulePools {
  /** termId → candidats `en` triés par ressemblance (le plus proche d'abord). */
  enRanked: Map<number, string[]>;
  /** termId → candidats `fr` triés. */
  frRanked: Map<number, string[]>;
  /** termId → candidats `ar` triés. */
  arRanked: Map<number, string[]>;
}

/** Construit les trois pools classés d'un module (une fois pour tout le module). */
function buildPools(terms: Term[]): ModulePools {
  const enRanked = new Map<number, string[]>();
  const frRanked = new Map<number, string[]>();
  const arRanked = new Map<number, string[]>();
  const enPool = terms.map((t) => t.en);
  const frPool = terms.map((t) => t.fr);
  const arPool = terms.map((t) => t.ar);
  for (const t of terms) {
    enRanked.set(t.id, rankCandidates(t.en, enPool));
    frRanked.set(t.id, rankCandidates(t.fr, frPool));
    arRanked.set(t.id, rankCandidates(t.ar, arPool));
  }
  return { enRanked, frRanked, arRanked };
}

/** Le kind est-il jouable sur ce terme ? Une donnée manquante => exercice sauté. */
function isPlayable(kind: Kind, term: Term, moduleSize: number): boolean {
  switch (kind) {
    case "enFr":
      return Boolean(term.fr);
    case "enAr":
      return Boolean(term.ar);
    case "frEn":
      return Boolean(term.en && term.fr);
    case "definition":
      return Boolean(term.defEn);
    case "etymology":
      return Boolean(term.etym);
    case "pronunciation":
      return Boolean(term.ipa);
    case "clinicalPearl":
      return Boolean(term.pearl);
    case "mnemonic":
      return Boolean(term.mnemo);
    case "reading":
      return Boolean(term.exEn);
    case "clinicalCase":
      return Boolean(term.exEn);
    case "matching":
      return moduleSize >= MATCH_PAIRS && Boolean(term.fr);
    case "wordOrder":
      return Boolean(term.exEn);
    case "fillBlank":
      return Boolean(term.exEn);
  }
}

/** Type d'UI produit par un kind (sert à la diversité des exercices). */
function typeOfKind(kind: Kind): ExerciseType {
  switch (kind) {
    case "enFr":
    case "enAr":
    case "frEn":
    case "definition":
    case "etymology":
    case "pronunciation":
    case "mnemonic":
      return "mcq";
    case "clinicalPearl":
    case "clinicalCase":
      return "clinical_case";
    case "reading":
      return "reading";
    case "matching":
      return "matching";
    case "wordOrder":
      return "sentence_order";
    case "fillBlank":
      return "fill_blank";
  }
}

/** Niveau d'apprentissage associé à chaque kind (1..6). */
function levelFor(kind: Kind): number {
  switch (kind) {
    case "enFr":
    case "enAr":
    case "frEn":
    case "pronunciation":
      return 1;
    case "definition":
    case "matching":
      return 2;
    case "fillBlank":
    case "etymology":
      return 3;
    case "wordOrder":
    case "reading":
      return 4;
    case "clinicalCase":
    case "clinicalPearl":
      return 5;
    case "mnemonic":
      return 6;
  }
}

/** Points : table de référence Android L1..L6 → 10/15/20/25/30/35. */
function pointsFor(level: number): number {
  return 10 + (level - 1) * 5;
}

/**
 * Id stable et négatif — miroir exact de `BulkExerciseGenerator.stableId` en
 * Kotlin. Négatif pour ne jamais collisionner avec un exercice écrit main
 * (Room autoGenerate démarre à 1, le seed JSON a des ids positifs).
 *
 * **L'espace d'ids doit être large** : 4 504 termes × 13 kinds = 58 552 ids à
 * caser dans un espace qui ne peut pas être le même entre deux exercices. Avec
 * un modulo de 8 M (version d'origine), le paradoxe des anniversaires produit
 * ~270 collisions → un exercice écrase un autre et le test "aucun id dupliqué"
 * échoue. On étend donc à 1 milliard.
 *
 * **Piège** : la version Kotlin normalise `hashSeed` en Long non signé avant
 * le modulo parce que FNV-1a déborde en Int signé. En JS, les nombres sont des
 * doubles (32 bits pour les opérations bit-à-bit), on utilise `>>> 0` pour
 * obtenir l'équivalent UInt32 — les deux plateformes obtiennent le même id.
 */
function stableId(moduleTitle: string, termId: number, kindOrdinal: number): number {
  const h = hashSeed(`bulk:${moduleTitle}:${termId}:${kindOrdinal}`);
  return -100_000_000 - (h % 900_000_000);
}

/** Sépare les options par `|` — mais le type web utilise un tableau. */
function optionsList(options: string[]): string[] {
  return options;
}

/** Découpe une phrase en mots jouables : mots alphabétiques de plus de 2 lettres. */
function splitWords(sentence: string): string[] {
  return sentence
    .split(/\s+/)
    .map((w) => w.trim().replace(/[.,;:!?"]/g, ""))
    .filter((w) => w.length > 2 && /^[A-Za-zÀ-ÿ-]+$/.test(w));
}

/** Remplace les occurrences du terme par un trou dans la phrase. */
function blankTermInSentence(
  sentence: string,
  term: string
): [string, string, string] | null {
  if (!sentence || !term) return null;
  const regex = new RegExp(term.replace(/[.*+?^${}()|[\]\\]/g, "\\$&"), "gi");
  if (!regex.test(sentence)) return null;
  const blanked = sentence.replace(regex, "______");
  return [blanked, blanked, blanked];
}

/** Construit un exercice, ou `null` si la donnée manque finalement. */
function build(
  kind: Kind,
  moduleTitle: string,
  term: Term,
  moduleTerms: Term[],
  pools: ModulePools,
  rand: () => number
): Exercise | null {
  const level = levelFor(kind);
  const points = pointsFor(level);

  const base = {
    id: stableId(moduleTitle, term.id, KINDS.indexOf(kind)),
    level,
    module: moduleTitle,
    chapter: term.chapter,
    expEn: term.defEn,
    expFr: term.defFr,
    expAr: term.defAr,
    points,
    ctxEn: "",
    ctxFr: "",
    ctxAr: "",
  };

  const mcq = (
    qEn: string,
    qFr: string,
    qAr: string,
    answer: string,
    ranked: string[]
  ): Exercise => ({
    ...base,
    type: "mcq",
    qEn,
    qFr,
    qAr,
    options: shuffleWith([answer, ...distractors(answer, ranked, rand)], rand),
    answer,
  });

  switch (kind) {
    case "enFr":
      return mcq(
        `What is the French translation of "${term.en}"?`,
        `Quelle est la traduction française de « ${term.en} » ?`,
        `ما الترجمة الفرنسية للمصطلح « ${term.en} »؟`,
        term.fr,
        pools.frRanked.get(term.id) ?? []
      );
    case "enAr":
      return mcq(
        `What is the Arabic translation of "${term.en}"?`,
        `Quelle est la traduction arabe de « ${term.en} » ?`,
        `ما الترجمة العربية للمصطلح « ${term.en} »؟`,
        term.ar,
        pools.arRanked.get(term.id) ?? []
      );
    case "frEn":
      return mcq(
        `Which English term matches "${term.fr}"?`,
        `Quel est le terme anglais pour « ${term.fr} » ?`,
        `ما المصطلح الإنجليزي المقابل لـ« ${term.fr} »؟`,
        term.en,
        pools.enRanked.get(term.id) ?? []
      );
    case "definition":
      return mcq(
        `Which term is defined as: ${term.defEn}?`,
        `Quel terme correspond à la définition : ${term.defFr}?`,
        `ما المصطلح الذي يُعرَّف بـ: ${term.defAr}؟`,
        term.en,
        pools.enRanked.get(term.id) ?? []
      );
    case "etymology":
      if (!term.etym) return null;
      return mcq(
        `Which term derives from: ${term.etym}?`,
        `Quel terme dérive de : ${term.etym} ?`,
        `ما المصطلح المشتق من: ${term.etym}؟`,
        term.en,
        pools.enRanked.get(term.id) ?? []
      );
    case "pronunciation":
      if (!term.ipa) return null;
      return mcq(
        `Which term is transcribed /${term.ipa.replace(/\//g, "")}?`,
        `Quel terme se transcrit /${term.ipa.replace(/\//g, "")} ?`,
        `ما المصطلح الذي يُكتب صوتيًا /${term.ipa.replace(/\//g, "")}؟`,
        term.en,
        pools.enRanked.get(term.id) ?? []
      );
    case "clinicalPearl":
      if (!term.pearl) return null;
      return {
        ...mcq(
          `Which term matches this clinical note: ${term.pearl}?`,
          `Quel terme correspond à cette note clinique : ${term.pearl} ?`,
          `ما المصطلح الذي يناسب هذه الملاحظة السريرية: ${term.pearl}؟`,
          term.en,
          pools.enRanked.get(term.id) ?? []
        ),
        // L5 doit transporter un contexte clinique (tableau de niveaux Android).
        ctxEn: `Clinical pearl: ${term.pearl}`,
        ctxFr: `Perle clinique : ${term.pearl}`,
        ctxAr: `جوهرة سريرية: ${term.pearl}`,
      };
    case "mnemonic":
      if (!term.mnemo) return null;
      return {
        ...mcq(
          `Which term is helped by this mnemonic: ${term.mnemo}?`,
          `Quel terme est aidé par ce moyen mnémotechnique : ${term.mnemo} ?`,
          `ما المصطلح الذي يساعده هذا التذكر: ${term.mnemo}؟`,
          term.en,
          pools.enRanked.get(term.id) ?? []
        ),
        // L6 doit transporter un contexte clinique (tableau de niveaux Android).
        ctxEn:
          `Mnemonic for “${term.en}”: ${term.mnemo}` +
          (term.pearl ? ` — Clinical relevance: ${term.pearl}` : ""),
        ctxFr:
          `Moyen mnémotechnique pour « ${term.en} » : ${term.mnemo}` +
          (term.pearl ? ` — Pertinence clinique : ${term.pearl}` : ""),
        ctxAr:
          `أسلوب تذكر لمصطلح « ${term.en} »: ${term.mnemo}` +
          (term.pearl ? ` — الأهمية السريرية: ${term.pearl}` : ""),
      };
    case "reading": {
      if (!term.exEn) return null;
      const opts = shuffleWith(
        [
          term.en,
          ...distractors(term.en, pools.enRanked.get(term.id) ?? [], rand),
        ],
        rand
      );
      return {
        ...base,
        type: "reading",
        qEn: "Which term fits this context?",
        qFr: "Quel terme correspond à ce contexte ?",
        qAr: "ما المصطلح الذي يناسب هذا السياق؟",
        options: opts,
        answer: term.en,
        ctxEn: term.exEn,
        ctxFr: term.exFr || term.exEn,
        ctxAr: term.exAr || term.exEn,
      };
    }
    case "clinicalCase": {
      if (!term.exEn) return null;
      const opts = shuffleWith(
        [
          term.en,
          ...distractors(term.en, pools.enRanked.get(term.id) ?? [], rand),
        ],
        rand
      );
      return {
        ...base,
        type: "clinical_case",
        qEn: "Identify the term used in this clinical context.",
        qFr: "Identifiez le terme employé dans ce contexte clinique.",
        qAr: "حدّد المصطلح المستخدم في هذا السياق السريري.",
        options: opts,
        answer: term.en,
        ctxEn: term.exEn,
        ctxFr: term.exFr || term.exEn,
        ctxAr: term.exAr || term.exEn,
      };
    }
    case "matching": {
      if (moduleTerms.length < MATCH_PAIRS) return null;
      const pairTerms = shuffleWith(
        moduleTerms.filter((t) => t.id !== term.id),
        rand
      ).slice(0, MATCH_PAIRS - 1);
      if (pairTerms.length < MATCH_PAIRS - 1) return null;
      const group = [term, ...pairTerms];
      const options = optionsList(group.map((t) => `${t.en}:${t.fr}`));
      return {
        ...base,
        type: "matching",
        qEn: "Match each term with its French translation.",
        qFr: "Associez chaque terme à sa traduction française.",
        qAr: "صِل كل مصطلح بترجمته الفرنسية.",
        options,
        answer: options.join("|"),
      };
    }
    case "wordOrder": {
      const words = splitWords(term.exEn);
      if (words.length < 3) return null;
      return {
        ...base,
        type: "sentence_order",
        qEn: `Arrange the words to build the sentence about "${term.en}".`,
        qFr: `Remettez les mots dans l'ordre pour construire la phrase sur « ${term.en} ».`,
        qAr: `رتب الكلمات لبناء الجملة حول « ${term.en} ».`,
        options: shuffleWith(words, rand),
        answer: words.join(" "),
      };
    }
    case "fillBlank": {
      // `fill_blank` est une saisie libre sur les deux plateformes :
      // - Android : `ExerciseSpec.Fill.acceptedAnswers = listOf(correctAnswer)`
      //   (optionsRaw est ignoré pour ce type).
      // - Web : `fillScore(q.fillVal, ex.answer)`, sans boutons d'options.
      // Générer des distracteurs ici serait inutile — on stocke la réponse
      // seule, comme fait `lessons.ts` pour ses propres fill_blank.
      const blanked = blankTermInSentence(term.exEn, term.en);
      if (!blanked) return null;
      return {
        ...base,
        type: "fill_blank",
        qEn: `Fill in the blank: ${blanked[0]}`,
        qFr: `Complétez la phrase : ${blanked[1]}`,
        qAr: `أكمل الفراغ: ${blanked[2]}`,
        options: [term.en],
        answer: term.en,
      };
    }
  }
}

/**
 * 3 distracteurs tirés parmi les candidats déjà triés par ressemblance.
 *
 * Le classement des candidats est calculé **une fois par terme** (voir
 * `rankCandidates`) puis partagé entre les 3 exercices : la version naive
 * recalculait ~3 × Σ N² similarités (≈ 15 M d'appels sur les 15 modules),
 * ce qui dépassait la minute.
 *
 * Miroir de `BulkExerciseGenerator.distractors` (Kotlin).
 */
function distractors(answer: string, ranked: string[], rand: () => number): string[] {
  if (!ranked.length) return [];
  const normAnswer = norm(answer);
  if (!normAnswer) return [];

  const candidates = ranked.slice(0, 12);
  if (!candidates.length) return [];

  const out: string[] = [];
  const used = new Set([normAnswer]);
  while (out.length < MCQ_OPTIONS - 1 && candidates.length) {
    const idx = Math.min(
      Math.floor(rand() * candidates.length),
      candidates.length - 1
    );
    const [picked] = candidates.splice(idx, 1);
    const normCandidate = norm(picked);
    if (used.has(normCandidate)) continue;
    used.add(normCandidate);
    out.push(picked);
  }
  return out;
}

/**
 * Ressemblance de Jaccard sur les bigrammes (0 → 1).
 * Cohérente avec la version Kotlin (`distractors`).
 */
function similarity(a: string, b: string): number {
  const A = bigrams(a);
  const B = bigrams(b);
  if (!A.size || !B.size) return 0;
  let shared = 0;
  for (const g of A) if (B.has(g)) shared += 1;
  return (2 * shared) / (A.size + B.size);
}

/** Bigrammes normalisés d'un libellé (mis en cache : ~7 M d'appels sinon). */
const gramCache = new Map<string, Set<string>>();
function bigrams(s: string): Set<string> {
  const cached = gramCache.get(s);
  if (cached) return cached;
  const t = norm(s).replace(/\s+/g, "");
  const out = new Set<string>();
  for (let i = 0; i < t.length - 1; i++) out.add(t.slice(i, i + 2));
  gramCache.set(s, out);
  return out;
}

/**
 * Classe les candidats à la ressemblance d'une réponse, une fois par terme.
 *
 * @param answer terme dont on cherche les proches voisins (la réponse).
 * @param pool   valeurs candidates du module (toutes les `en` ou toutes les `fr`).
 * @returns candidats distincts de `answer`, du plus ressemblant au moins.
 */
function rankCandidates(answer: string, pool: string[]): string[] {
  if (!pool.length) return [];
  const normAnswer = norm(answer);
  if (!normAnswer) return [];

  const seen = new Set<string>();
  const out: { c: string; s: number }[] = [];
  for (const c of pool) {
    const nc = norm(c);
    if (!nc || nc === normAnswer || seen.has(nc)) continue;
    seen.add(nc);
    out.push({ c, s: similarity(answer, c) });
  }
  out.sort((a, b) => b.s - a.s);
  return out.map((x) => x.c);
}

/**
 * Génère exactement [EXERCISES_PER_TERM] exercices pour un terme.
 *
 * Le tirage est déterministe (`mulberry32(hashSeed("bulk:<module>:<termId>"))`),
 * donc identique à la version Kotlin. Les kinds rares sont prioritaires ;
 * les kinds communs garantissent le repli (termEn/Fr/Ar remplis à 100 %).
 */
function generateForTerm(
  moduleTitle: string,
  term: Term,
  moduleTerms: Term[],
  pools: ModulePools
): Exercise[] {
  const rand = mulberry32(hashSeed(`bulk:${moduleTitle}:${term.id}`));

  const playable = KINDS.filter((k) => isPlayable(k, term, moduleTerms.length));
  if (!playable.length) return [];

  const chosen: Exercise[] = [];
  const usedKinds = new Set<Kind>();
  const usedTypes = new Set<string>();

  /**
   * Premier passage : on n'accepte un exercice que s'il apporte un type d'UI
   * encore absent, pour garantir la diversité.
   *
   * Sans cette règle, les 3 slots sont systématiquement pris par
   * WORD_ORDER / FILL_BLANK / READING (exampleEn rempli à 85 %), tous placés
   * avant MATCHING dans KINDS : on ne produisait que 4 types sur 6 et jamais
   * d'appariement (miroir exact du correctif Kotlin).
   *
   * Le kind refusé reste jouable : le second passage le reprend, sans
   * contrainte de type, pour garantir les 3 exercices par terme.
   */
  // 1. Premier passage dans l'ordre de KINDS (rare d'abord), en privilégiant
  //    la diversité des types d'UI.
  for (const kind of KINDS) {
    if (chosen.length >= EXERCISES_PER_TERM) break;
    if (!playable.includes(kind) || usedKinds.has(kind)) continue;
    const ex = build(kind, moduleTitle, term, moduleTerms, pools, rand);
    if (!ex) continue;
    // Ne PAS marquer le kind comme construit s'il est refusé pour doublon de
    // type : il doit rester disponible au second passage, sinon on consume un
    // kind sans produire d'exercice et certains termes n'atteignent plus les 3
    // exercices obligatoires.
    if (!usedTypes.has(ex.type)) {
      chosen.push(ex);
      usedKinds.add(kind);
      usedTypes.add(ex.type);
    }
  }

  // 2. Complétion : kinds jouables restants. Priorité aux types encore absents,
  //    puis n'importe quel kind, pour garantir les 3 exercices.
  if (chosen.length < EXERCISES_PER_TERM) {
    const remaining = shuffleWith(
      playable.filter((k) => !usedKinds.has(k)),
      rand
    ).sort((a, b) => {
      const ta = usedTypes.has(typeOfKind(a)) ? 1 : 0;
      const tb = usedTypes.has(typeOfKind(b)) ? 1 : 0;
      return ta - tb;
    });
    for (const kind of remaining) {
      if (chosen.length >= EXERCISES_PER_TERM) break;
      const ex = build(kind, moduleTitle, term, moduleTerms, pools, rand);
      if (ex) {
        chosen.push(ex);
        usedKinds.add(kind);
        usedTypes.add(ex.type);
      }
    }
  }

  return chosen.slice(0, EXERCISES_PER_TERM);
}

/** Génère les exercices d'un module entier. */
export function generateBulkModule(moduleTitle: string, terms: Term[]): Exercise[] {
  if (!terms.length) return [];
  const pools = buildPools(terms);
  const out: Exercise[] = [];
  for (const term of terms) {
    out.push(...generateForTerm(moduleTitle, term, terms, pools));
  }
  return out;
}

/**
 * Génère tous les exercices de tous les modules.
 *
 * À appeler après `loadOnce()` : les termes sont lus depuis `terms.json`.
 *
 * **Mémoïsé** : la génération complète coûte ~8 s (classement de similarité
 * O(n²) par module). Elle était pourtant appelée à *chaque render* de l'écran
 * quiz — via `quizExercisesForLevel()` pour le simple compteur « N exercices »
 * — ce qui gelait l'UI plusieurs secondes à chaque interaction. Le corpus est
 * déterministe (mêmes seeds que Android) : on le calcule une fois et on le
 * garde. `warmBulkCache()` le préchauffe en arrière-plan, module par module,
 * pour que le premier quiz soit instantané.
 */
let bulkCache: Exercise[] | null = null;
let warming = false;

/** Vide le cache (branchement des données : `loadOnce` / `setTestData`). */
export function resetBulkCache(): void {
  bulkCache = null;
  warming = false;
}

function buildAllSync(): Exercise[] {
  const out: Exercise[] = [];
  for (const mod of MODULES) {
    const terms = termsOfModule(mod.id);
    if (!terms.length) continue;
    out.push(...generateBulkModule(mod.fr, terms));
  }
  bulkCache = out;
  return out;
}

export function generateBulkExercises(): Exercise[] {
  if (bulkCache) return bulkCache;
  // Précharge en cours : on n'attend pas, on complète en synchrone.
  if (warming) {
    warming = false;
  }
  return buildAllSync();
}

/**
 * Préchauffe le cache module par module, en rendant la main entre deux
 * modules (`setTimeout 0`) : aucun bloc de 8 s sur le thread principal, la
 * navigation reste fluide pendant la génération. Une fois terminée,
 * `generateBulkExercises()` (et donc le quiz) est instantané.
 */
export function warmBulkCache(): void {
  if (bulkCache || warming) return;
  warming = true;
  const parts: Exercise[] = [];
  let i = 0;
  const step = () => {
    if (i >= MODULES.length) {
      bulkCache = parts;
      warming = false;
      return;
    }
    const mod = MODULES[i++];
    const terms = termsOfModule(mod.id);
    if (terms.length) parts.push(...generateBulkModule(mod.fr, terms));
    setTimeout(step, 0);
  };
  step();
}

/** Types réellement produits (utile pour les tests). */
export const BULK_TYPES: ExerciseType[] = [
  "mcq",
  "fill_blank",
  "matching",
  "sentence_order",
  "reading",
  "clinical_case",
];

// Le corpus est dérivé des termes : tout rebranchement de données
// (`loadOnce`, `setTestData`) doit vider le cache.
onDataChanged(resetBulkCache);
