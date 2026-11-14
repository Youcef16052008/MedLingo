/**
 * P0.3 — contrôle de qualité du corpus d'exercices (`public/data/exercises.json`).
 *
 * Deux niveaux, conformément au plan (lot B) :
 *  - ERREURS CERTAINES (bloquantes, exit 1) : défaut objective de l'exercice —
 *    réponse affichée dans la question (après flèche ou copiée), blanc annoncé
 *    mais absent, options invalides, réponse vide, « définition » égale à la
 *    réponse, options parasites sur un type à saisie libre ;
 *  - SUSPICIONS (rapportées, non bloquantes) : motifs qui méritent une relecture
 *    humaine mais qu'on ne peut pas condamner automatiquement — une sous-chaîne
 *    commune ou un cognat légitime (ex. « Cardiaque » → « Cardiac ») n'est
 *    JAMAIS une fuite.
 *
 * Usage :
 *   node scripts/exercise-quality.mjs [chemin-exercises.json]
 *   (sans argument : corpus par défaut + exit 1 si erreur certaine)
 *
 * Importable comme module depuis les tests : `auditExercises(exercises)`.
 */

import { readFileSync } from "node:fs";
import { join, dirname } from "node:path";
import { fileURLToPath } from "node:url";

const LANGS = ["En", "Fr", "Ar"];

/** Normalisation pour comparaison (casse, espaces, ponctuation finale). */
function norm(s) {
  return String(s ?? "")
    .toLowerCase()
    .replace(/\s+/g, " ")
    .replace(/[.!?؟]+$/g, "")
    .trim();
}

/** Le texte contient-il un « blanc » jouable ? */
function hasBlank(text) {
  return /_{3,}/.test(text);
}

/** L'instruction annonce-t-elle un trou à compléter ? */
function claimsBlank(text) {
  return (
    /fill in the blank/i.test(text) ||
    /complétez la phrase/i.test(text) ||
    /أكمل الفراغ/.test(text)
  );
}

/** La réponse apparaît-elle comme MOT ENTIER dans le corps de la question ? */
function answerCopiedInQuestion(question, answer) {
  const a = norm(answer);
  if (!a) return false;
  // Frontières de mots : « Cardiaque » ne condamne pas « Cardiac » ; un mot
  // emprunté entier visible (« Valve » cité dans la question attendue « Valve »)
  // reste une fuite réelle.
  const re = new RegExp(`(^|[^\\p{L}\\p{N}])${escapeRe(a)}([^\\p{L}\\p{N}]|$)`, "u");
  return re.test(norm(question));
}

function escapeRe(s) {
  return s.replace(/[.*+?^${}()|[\]\\]/g, "\\$&");
}

/** Dédupliqué normalisé — deux options « identiques » pour l'étudiant. */
function optionProblems(ex) {
  const opts = ex.options;
  if (!Array.isArray(opts) || opts.length < 2) {
    return ["options absentes ou trop peu nombreuses"];
  }
  const problems = [];
  if (ex.type === "matching") {
    // Contrat bulkExercises : options = "terme:trad|…" et answer = join("|").
    for (const o of opts) {
      if (!/^[^:]+:.+/.test(String(o))) problems.push(`option au mauvais format « left:right » : « ${o} »`);
    }
    const normOpts = opts.map((o) => norm(o));
    if (new Set(normOpts).size !== opts.length) problems.push("option dupliquée");
    if (norm(String(ex.answer)) !== norm(opts.join("|"))) {
      problems.push("answer ≠ jointure des options (contrat matching rompu)");
    }
    return problems;
  }
  if (ex.type === "sentence_order") {
    // Options = mots mêlés ; answer = phrase reconstruite (mêmes mots).
    const normOpts = opts.map((o) => norm(o));
    if (new Set(normOpts).size !== opts.length) problems.push("option dupliquée");
    const answerWords = norm(ex.answer).split(" ").sort().join(" ");
    const optionWords = [...normOpts].sort().join(" ");
    if (answerWords !== optionWords) {
      problems.push("les options ne correspondent pas aux mots de la réponse");
    }
    return problems;
  }
  // mcq : réponse toujours présente, options toutes distinctes.
  const seen = new Map();
  for (const o of opts) {
    const n = norm(o);
    if (seen.has(n)) problems.push(`option dupliquée : « ${o} »`);
    seen.set(n, o);
  }
  if (!seen.has(norm(ex.answer))) problems.push("la réponse ne figure pas dans les options");
  return problems;
}

/**
 * Audite un corpus. Retourne :
 *   certain    : [{ id, type, rule, lang?, detail }] — DOIT rester vide.
 *   suspicious : [{ id, type, rule, detail }] — à relire humainement.
 *   summary    : compteurs par règle.
 */
export function auditExercises(exercises) {
  const certain = [];
  const suspicious = [];
  const push = (list, ex, rule, detail, lang) =>
    list.push({ id: String(ex.id), type: ex.type, rule, lang, detail });

  for (const ex of exercises) {
    const answer = String(ex.answer ?? "");
    // 1. Réponse vide / absente.
    if (answer.trim() === "") {
      push(certain, ex, "empty_answer", "answer vide ou absent");
    }

    for (const lang of LANGS) {
      const q = String(ex[`q${lang}`] ?? "");
      if (!q.trim()) continue;
      // 2. Fuite après flèche : le générateur historique écrivait
      //    « Complete : 'def' → <réponse localisée> » — la flèche APRÈS la
      //    citation est le motif de la fuite (la réponse affichée peut être la
      //    version FR/AR, non présente dans `answer`). Une flèche À
      //    L'INTÉRIEUR d'une définition citée est légitime (notation chimique
      //    serrée « 5'→3' », « pepsinogen → pepsin ») : on ne condamne que le
      //    motif « quote(espace)→ » (AU MOINS UN espace) qui matche 0
      //    définition honnête — le template historique écrivait `'def' → ans`.
      if (/'[^']*'\s+→/.test(q) && ["mcq", "pair", "gap", "fill_blank"].includes(ex.type)) {
        push(certain, ex, "answer_after_arrow", `flèche après la citation dans q${lang} (motif « 'définition' → réponse » du générateur historique)`, lang);
      }
      // 3. Blanc annoncé mais absent.
      if (claimsBlank(q) && !hasBlank(q)) {
        push(certain, ex, "blank_missing", `consigne de complétion sans trou dans q${lang}`, lang);
      }
      // 4. Réponse copiée verbatim dans la question — UNIQUEMENT pour les
      //    types où la réponse doit être produite/choisie sans être montrée.
      //    Un cognat FR/EN n'est pas une fuite : seule la copie VERBATIM de la
      //    réponse attendue est condamnée.
      if (["mcq", "pair", "gap", "fill_blank"].includes(ex.type) && answerCopiedInQuestion(q, answer)) {
        push(certain, ex, "answer_copied_in_question", `réponse copiée dans q${lang}`, lang);
      }
    }

    // 5. Options invalides (QCM / matching / phrase mêlée).
    if (ex.type === "mcq" || ex.type === "matching" || ex.type === "sentence_order") {
      for (const p of optionProblems(ex)) {
        push(certain, ex, "invalid_options", p);
      }
    }
    // 6. Options parasites sur un type à saisie libre.
    if ((ex.type === "pair" || ex.type === "gap" || ex.type === "fill_blank") && Array.isArray(ex.options) && ex.options.length > 0) {
      push(suspicious, ex, "stray_options", "options présentes sur un type à saisie libre");
    }
    // 7. « Définition » égale à la réponse (fallback du générateur historique).
    if (ex.type === "pair") {
      const m = String(ex.qEn ?? "").match(/'([^']+)'/);
      if (m && norm(m[1]) === norm(answer)) {
        push(certain, ex, "definition_equals_answer", "la « définition » est la réponse elle-même");
      }
    }
  }

  const summary = {};
  for (const e of certain) summary[`certain:${e.rule}`] = (summary[`certain:${e.rule}`] ?? 0) + 1;
  for (const e of suspicious) summary[`suspicious:${e.rule}`] = (summary[`suspicious:${e.rule}`] ?? 0) + 1;

  return {
    certain,
    suspicious,
    summary,
    total: exercises.length,
    ok: certain.length === 0,
  };
}

/* ---------- CLI ---------- */

const isCli =
  process.argv[1] && fileURLToPath(import.meta.url) === process.argv[1];

if (isCli) {
  const __dirname = dirname(fileURLToPath(import.meta.url));
  const path = process.argv[2] ?? join(__dirname, "..", "public", "data", "exercises.json");
  const exercises = JSON.parse(readFileSync(path, "utf8"));
  const report = auditExercises(exercises);
  console.log(`[exercise-quality] corpus : ${report.total} exercices`);
  const certainKeys = Object.keys(report.summary).filter((k) => k.startsWith("certain:"));
  const suspiciousKeys = Object.keys(report.summary).filter((k) => k.startsWith("suspicious:"));
  for (const k of [...certainKeys, ...suspiciousKeys]) {
    console.log(`  ${k} : ${report.summary[k]}`);
  }
  if (report.certain.length > 0) {
    console.error(`[exercise-quality] ERREURS CERTAINES (${report.certain.length}) :`);
    for (const e of report.certain.slice(0, 40)) {
      console.error(`  - ${e.id} [${e.type}] ${e.rule}${e.lang ? `/${e.lang}` : ""} : ${e.detail}`);
    }
    if (report.certain.length > 40) console.error(`  … et ${report.certain.length - 40} autres`);
    console.error("[exercise-quality] BLOQUANT : corriger le générateur et régénérer.");
    process.exit(1);
  }
  if (report.suspicious.length > 0) {
    console.log(`[exercise-quality] suspicions à relecture humaine : ${report.suspicious.length} (non bloquant)`);
    for (const s of report.suspicious.slice(0, 20)) {
      console.log(`  ~ ${s.id} [${s.type}] ${s.rule} : ${s.detail}`);
    }
    if (report.suspicious.length > 20) console.log(`  … et ${report.suspicious.length - 20} autres`);
  }
  console.log("[exercise-quality] OK — aucune erreur certaine.");
  process.exit(0);
}
