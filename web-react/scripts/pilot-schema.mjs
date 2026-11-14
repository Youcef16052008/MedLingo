/**
 * P1.1 — Validation du manifeste éditorial du pilote Anatomie.
 *
 * Importable à la fois par le CLI (`node scripts/check-pilot.mjs`) et par les
 * tests (pattern déjà validé : scripts/exercise-quality.mjs). La façade typée
 * pour l'app vit dans `src/pilot/content.ts`.
 *
 * Règles du plan (P1.1) :
 *  - IDs uniques (concepts ET exercices), références existantes,
 *    langues présentes, niveaux admis, options distinctes, réponse valide ;
 *  - `approved` UNIQUEMENT si les deux relectures (linguistique + anatomique)
 *    couvrent la version courante — une modification de contenu invalide ;
 *  - un ancien `approved` ne doit pas survivre silencieusement (mismatch de
 *    version = erreur bloquante, jamais un silence) ;
 *  - fuite interdite : la réponse ne doit JAMAIS apparaître verbatim (mot
 *    entier) dans une consigne — leçon P0.3 ;
 *  - couverture P1.3 : les trois objectifs (reconnaissance, compréhension en
 *    contexte, rappel) doivent être présents pour chaque concept.
 */

/** Normalisation pour comparaison : casse, espaces, accents Latin. */
export function norm(s) {
  return String(s ?? "")
    .toLowerCase()
    .normalize("NFD")
    .replace(/[\u0300-\u036f]/g, "") // accents latins
    .replace(/\s+/g, " ")
    .trim();
}

function escapeRe(s) {
  return s.replace(/[.*+?^${}()|[\]\\]/g, "\\$&");
}

/** La réponse apparaît-elle comme MOT ENTIER dans le texte ? */
export function answerVisibleIn(text, answer) {
  const a = norm(answer);
  if (!a || !text) return false;
  return new RegExp(`(^|[^\\p{L}\\p{N}])${escapeRe(a)}([^\\p{L}\\p{N}]|$)`, "u").test(norm(text));
}

const UNITS = [1, 2, 3];
const OBJECTIVES = ["recognize", "understand", "recall"];
const ROLES = ["practice", "assessment"];
const KINDS = ["mcq", "free_input"];
const STATUSES = ["draft", "approved"];

/**
 * Valide un manifeste. Retourne { ok, errors: string[], warnings: string[] }.
 * `errors` = bloquant (exit 1) ; `warnings` = à relire, non bloquant.
 */
export function validatePilotManifest(manifest, { knownTermIds = null } = {}) {
  const errors = [];
  const warnings = [];
  const err = (m) => errors.push(m);
  const warn = (m) => warnings.push(m);

  if (!manifest || typeof manifest !== "object") {
    return { ok: false, errors: ["manifeste absent ou non objet"], warnings };
  }
  if (manifest.schemaVersion !== 1) {
    err(`schemaVersion inconnue : ${JSON.stringify(manifest.schemaVersion)} (attendu 1)`);
  }
  if (!String(manifest.contentVersion ?? "").trim()) {
    err("contentVersion du manifeste absente");
  }
  if (!Number.isFinite(Date.parse(manifest.generatedAt))) {
    err(`generatedAt n'est pas une date ISO valide : ${JSON.stringify(manifest.generatedAt)}`);
  }
  if (!String(manifest.generatedFrom ?? "").trim()) {
    err("generatedFrom (provenance de génération) absent");
  }
  const manifestVersion = String(manifest.contentVersion ?? "");

  const concepts = Array.isArray(manifest.concepts) ? manifest.concepts : [];
  if (concepts.length === 0) err("aucun concept dans le manifeste");

  const conceptIds = new Set();
  const exerciseIds = new Set();
  const ordersByUnit = new Map();

  for (const c of concepts) {
    const cid = String(c.id ?? "?");
    const tag = `[${cid}]`;
    if (conceptIds.has(cid)) err(`${tag} id de concept dupliqué`);
    conceptIds.add(cid);

    if (!UNITS.includes(c.unit)) err(`${tag} unité hors 1..3 : ${JSON.stringify(c.unit)}`);
    if (!Number.isInteger(c.order) || c.order < 1) err(`${tag} ordre invalide : ${JSON.stringify(c.order)}`);
    if (c.unit != null) {
      const list = ordersByUnit.get(c.unit) ?? [];
      list.push(c.order);
      ordersByUnit.set(c.unit, list);
    }

    if (typeof c.legacyRef === "number") {
      if (knownTermIds && !knownTermIds.has(c.legacyRef)) {
        err(`${tag} legacyRef ${c.legacyRef} inexistant dans terms.json`);
      }
    } else if (c.legacyRef != null) {
      err(`${tag} legacyRef doit être un nombre (id terms.json) ou absent`);
    }

    if (!String(c.labelEn ?? "").trim()) err(`${tag} labelEn vide`);
    if (!String(c.hints?.fr ?? "").trim()) err(`${tag} aide FR vide`);
    if (!String(c.hints?.ar ?? "").trim()) err(`${tag} aide AR vide`);
    if (!String(c.languageObjective ?? "").trim()) err(`${tag} languageObjective vide`);
    for (const l of ["en", "fr", "ar"]) {
      if (!String(c.definition?.[l] ?? "").trim()) err(`${tag} définition ${l.toUpperCase()} vide`);
    }
    if (c.example && !String(c.example.en ?? "").trim()) {
      warn(`${tag} exemple présent mais exemple EN vide`);
    }
    if (!STATUSES.includes(c.status)) err(`${tag} statut inconnu : ${JSON.stringify(c.status)}`);
    if (!String(c.contentVersion ?? "").trim()) err(`${tag} contentVersion absente`);
    else if (manifestVersion && c.contentVersion !== manifestVersion) {
      // Un ancien approved (ou tout concept) ne survit pas silencieusement à
      // un bump de version globale.
      err(`${tag} contentVersion ${c.contentVersion} ≠ manifeste ${manifestVersion}`);
    }
    if (!String(c.selectionNote ?? "").trim()) {
      err(`${tag} selectionNote absente (justification P1.2 à faire examiner)`);
    }

    // ——— Verrou de publication : approved ⟺ deux relectures sur LA VERSION
    //     COURANTE, par une personne réelle, datée.
    for (const key of ["linguistic", "medical"]) {
      const r = c.reviews?.[key];
      if (!r || typeof r !== "object") {
        err(`${tag} relecture ${key} absente`);
        continue;
      }
      const done = r.status === "done";
      if (done) {
        if (!String(r.reviewer ?? "").trim()) err(`${tag} relecture ${key} « done » sans personne réelle`);
        if (!Number.isFinite(Date.parse(r.date ?? ""))) err(`${tag} relecture ${key} « done » sans date ISO`);
        if (r.reviewedContentVersion !== c.contentVersion) {
          err(
            `${tag} relecture ${key} porte sur la version ${JSON.stringify(r.reviewedContentVersion)} ≠ version courante ${JSON.stringify(c.contentVersion)} — revalidation requise`,
          );
        }
      } else if (r.status !== "pending") {
        err(`${tag} relecture ${key} statut inconnu : ${JSON.stringify(r.status)}`);
      }
      // Jamais de faux verdict : si une relecture « done » a été déclarée mais
      // que le concept est draft, c'est une incohérence éditoriale.
      if (done && c.status === "draft") {
        warn(`${tag} relectures « done » mais statut draft — publication volontairement non activée`);
      }
    }
    if (c.status === "approved") {
      const ling = c.reviews?.linguistic;
      const med = c.reviews?.medical;
      const covers = (r) => r?.status === "done" && r?.reviewedContentVersion === c.contentVersion;
      if (!covers(ling) || !covers(med)) {
        err(`${tag} statut « approved » sans les DEUX relectures couvrant la version courante`);
      }
    }

    // ——— Exercices.
    const exs = Array.isArray(c.exercises) ? c.exercises : [];
    if (exs.length === 0) err(`${tag} aucun exercice`);
    const objectivesSeen = new Set();
    for (const e of exs) {
      const eid = `[${cid} / ${String(e.id ?? "?")}]`;
      if (exerciseIds.has(e.id)) err(`${eid} id d'exercice dupliqué (global)`);
      exerciseIds.add(e.id);
      if (e.conceptId !== c.id) err(`${eid} conceptId ${JSON.stringify(e.conceptId)} ≠ ${JSON.stringify(c.id)}`);
      if (!OBJECTIVES.includes(e.objective)) err(`${eid} objectif inconnu : ${JSON.stringify(e.objective)}`);
      else objectivesSeen.add(e.objective);
      if (!ROLES.includes(e.role)) err(`${eid} rôle inconnu : ${JSON.stringify(e.role)}`);
      if (!KINDS.includes(e.kind)) err(`${eid} type inconnu : ${JSON.stringify(e.kind)}`);
      for (const l of ["en", "fr", "ar"]) {
        if (!String(e.prompt?.[l] ?? "").trim()) err(`${eid} consigne ${l.toUpperCase()} vide`);
        if (!String(e.explanation?.[l] ?? "").trim()) err(`${eid} explication ${l.toUpperCase()} vide`);
      }
      const answer = String(e.answer ?? "").trim();
      if (!answer) err(`${eid} réponse vide`);
      // Fuite : leçon P0.3 — jamais la réponse verbatim dans une consigne.
      for (const l of ["en", "fr", "ar"]) {
        if (answerVisibleIn(e.prompt?.[l], answer)) {
          err(`${eid} RÉPONSE VISIBLE dans la consigne ${l.toUpperCase()} (fuite P0.3)`);
        }
      }
      if (e.kind === "mcq") {
        const opts = Array.isArray(e.options) ? e.options.map(String) : [];
        if (opts.length < 2) err(`${eid} mcq avec moins de 2 options`);
        const normed = opts.map(norm);
        if (new Set(normed).size !== normed.length) err(`${eid} options non distinctes`);
        if (answer && !normed.includes(norm(answer))) err(`${eid} réponse absente des options`);
      } else {
        if (Array.isArray(e.options) && e.options.length > 0) {
          err(`${eid} free_input avec options parasites`);
        }
      }
      const variants = Array.isArray(e.acceptedVariants) ? e.acceptedVariants.map(String) : [];
      const vn = variants.map(norm);
      if (new Set(vn).size !== vn.length) err(`${eid} variantes dupliquées`);
      if (answer && vn.includes(norm(answer))) err(`${eid} variante identique à la réponse`);
      for (const v of variants) {
        if (!v.trim()) err(`${eid} variante vide`);
      }
    }
    // Couverture P1.3 : les trois objectifs présents pour chaque concept.
    for (const o of OBJECTIVES) {
      if (!objectivesSeen.has(o)) err(`${tag} objectif P1.3 « ${o} » absent des exercices`);
    }

    // ——— Médias : licence/attribution/provenance obligatoires si présents.
    for (const m of Array.isArray(c.media) ? c.media : []) {
      if (!String(m.license ?? "").trim() || !String(m.attribution ?? "").trim() || !String(m.source ?? "").trim()) {
        err(`${tag} média sans licence/attribution/provenance complète`);
      }
    }
  }

  // Ordres uniques par unité.
  for (const [unit, orders] of ordersByUnit) {
    if (new Set(orders).size !== orders.length) {
      err(`unité ${unit} : ordres en doublon (${orders.join(", ")})`);
    }
  }

  return { ok: errors.length === 0, errors, warnings };
}

/* ------------------------------------------------------------------ *
 * P3.1 — Validation des banques de MESURE (fichier séparé de
 * l'entraînement). Les items de mesure ne passent JAMAIS par le schéma
 * d'entraînement : dimensions propres, versions parallèles explicites,
 * pas d'explication affichée pendant la mesure (aucun feedback correctif).
 * ------------------------------------------------------------------ */

const DIMENSIONS = ["recognize", "recall", "comprehend", "production", "real_use"];

/**
 * Valide un manifeste de mesure. `conceptIds` : ensemble des ids de concepts
 * du manifeste d'entraînement (cross-référence obligatoire) ; `expectedVersions`
 * : versions parallèles attendues (A/B/C).
 */
export function validatePilotMeasureManifest(
  manifest,
  { conceptIds = null, expectedVersions = null } = {},
) {
  const errors = [];
  const warnings = [];
  const err = (m) => errors.push(m);
  const warn = (m) => warnings.push(m);

  if (!manifest || typeof manifest !== "object") {
    return { ok: false, errors: ["manifeste de mesure absent ou non objet"], warnings };
  }
  if (manifest.schemaVersion !== 1) {
    err(`schemaVersion inconnue : ${JSON.stringify(manifest.schemaVersion)} (attendu 1)`);
  }
  if (!String(manifest.contentVersion ?? "").trim()) err("contentVersion du manifeste de mesure absente");
  if (!Number.isFinite(Date.parse(manifest.generatedAt ?? ""))) err("generatedAt n'est pas une date ISO valide");
  if (!String(manifest.generatedFrom ?? "").trim()) err("generatedFrom (provenance) absent");

  const versions = Array.isArray(manifest.versions) ? manifest.versions.map(String) : [];
  if (expectedVersions) {
    for (const v of expectedVersions) {
      if (!versions.includes(v)) err(`version parallèle « ${v} » absente (attendues : ${expectedVersions.join(", ")})`);
    }
    if (versions.length !== expectedVersions.length) {
      warn(`versions déclarées (${versions.join(", ")}) ≠ attendues (${expectedVersions.join(", ")}) — vérifier le mapping T0/T1/T2`);
    }
  }

  const banks = Array.isArray(manifest.banks) ? manifest.banks : [];
  if (banks.length === 0) err("aucune banque de mesure");

  const itemIds = new Set();
  const banksSeen = new Set();
  for (const b of banks) {
    const cid = String(b.conceptId ?? "?");
    const version = String(b.version ?? "?");
    const tag = `[${cid}/${version}]`;
    if (conceptIds && !conceptIds.has(cid)) {
      err(`${tag} conceptId inexistant dans le manifeste d'entraînement`);
    }
    if (expectedVersions && !expectedVersions.includes(version)) {
      err(`${tag} version hors mapping parallèle : ${JSON.stringify(version)}`);
    }
    const bankKey = `${cid}::${version}`;
    if (banksSeen.has(bankKey)) err(`${tag} banque dupliquée (concept × version)`);
    banksSeen.add(bankKey);

    const items = Array.isArray(b.items) ? b.items : [];
    if (items.length === 0) err(`${tag} banque vide`);
    const dimsSeen = new Set();
    for (const it of items) {
      const iid = String(it.id ?? "?");
      const itag = `[${iid}]`;
      if (itemIds.has(iid)) err(`${itag} id d'item dupliqué (global)`);
      itemIds.add(iid);
      if (it.conceptId !== cid) err(`${itag} conceptId ${JSON.stringify(it.conceptId)} ≠ ${JSON.stringify(cid)}`);
      if (it.version !== version) err(`${itag} version ${JSON.stringify(it.version)} ≠ banque ${JSON.stringify(version)}`);
      if (!DIMENSIONS.includes(it.dimension)) {
        err(`${itag} dimension de mesure inconnue : ${JSON.stringify(it.dimension)}`);
      } else dimsSeen.add(it.dimension);
      if (it.explanation != null) {
        // La mesure ne doit pas embarquer de correction affichable pendant le
        // test : la rétroaction corrective est interdite pendant la mesure.
        err(`${itag} explanation interdit dans un item de mesure (pas de feedback correctif)`);
      }
      for (const l of ["en", "fr", "ar"]) {
        if (!String(it.prompt?.[l] ?? "").trim()) err(`${itag} consigne ${l.toUpperCase()} vide`);
      }
      const answer = String(it.answer ?? "").trim();
      if (!answer) err(`${itag} réponse vide`);
      // Fuite : même règle P0.3 — la réponse jamais verbatim dans la consigne.
      for (const l of ["en", "fr", "ar"]) {
        if (answerVisibleIn(it.prompt?.[l], answer)) {
          err(`${itag} RÉPONSE VISIBLE dans la consigne ${l.toUpperCase()} (fuite P0.3)`);
        }
      }
      if (it.kind === "mcq") {
        const opts = Array.isArray(it.options) ? it.options.map(String) : [];
        if (opts.length < 2) err(`${itag} mcq avec moins de 2 options`);
        const normed = opts.map(norm);
        if (new Set(normed).size !== normed.length) err(`${itag} options non distinctes`);
        if (answer && !normed.includes(norm(answer))) err(`${itag} réponse absente des options`);
      } else if (it.kind === "free_input") {
        if (Array.isArray(it.options) && it.options.length > 0) err(`${itag} free_input avec options parasites`);
      } else {
        err(`${itag} type inconnu : ${JSON.stringify(it.kind)}`);
      }
      const variants = Array.isArray(it.acceptedVariants) ? it.acceptedVariants.map(String) : [];
      const vn = variants.map(norm);
      if (new Set(vn).size !== vn.length) err(`${itag} variantes dupliquées`);
      if (answer && vn.includes(norm(answer))) err(`${itag} variante identique à la réponse`);
      if (!String(it.source ?? "").trim()) err(`${itag} source (provenance de la consigne) absente`);
    }
    // Couverture minimale par version : chaque concept × version doit mesurer
    // au moins reconnaissance ET rappel (les autres dimensions sont admises
    // absentes tant que le manque est documenté — phrase nouvelle, Lot E).
    for (const d of ["recognize", "recall"]) {
      if (!dimsSeen.has(d)) err(`${tag} dimension « ${d} » absente (couverture minimale P3.1)`);
    }
  }

  // Chaque concept doit avoir UNE banque PAR version (comparabilité T0/T1/T2).
  if (conceptIds && expectedVersions) {
    for (const cid of conceptIds) {
      for (const v of expectedVersions) {
        if (!banksSeen.has(`${cid}::${v}`)) err(`banque manquante : ${cid}/${v}`);
      }
    }
  }

  return { ok: errors.length === 0, errors, warnings };
}
