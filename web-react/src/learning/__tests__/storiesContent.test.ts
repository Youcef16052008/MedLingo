import { describe, it, expect } from "vitest";
import { readFileSync } from "node:fs";
import { join, dirname } from "node:path";
import { fileURLToPath } from "node:url";
import type { StoryFile } from "../types";
import { normalizeAnswer } from "../session";

const __dirname = dirname(fileURLToPath(import.meta.url));

/**
 * Validation de CONTENU (phase 3.1b) — porte sur le fichier brut, drafts
 * inclus : la qualité doit être garantie AVANT le passage à `published`.
 * Garde-fous du guide : réponses acceptées non ambiguës, faits critiques
 * signalés, termes référencés réels, pas d'expression orpheline.
 */
const raw = JSON.parse(
  readFileSync(join(__dirname, "..", "..", "..", "public", "data", "stories.v1.json"), "utf-8")
) as StoryFile;
const terms = JSON.parse(
  readFileSync(join(__dirname, "..", "..", "..", "public", "data", "terms.json"), "utf-8")
) as { id: number; en: string }[];
const termIds = new Set(terms.map((t) => t.id));

describe("contenu des histoires (pilote ×10)", () => {
  const PILOT_IDS = [
    "clinical-symptoms-001", "self-intro-002", "locate-structure-003", "symptom-absent-004",
    "when-started-005", "reformulate-confirm-006", "organ-function-007", "tissue-simple-008",
    "cause-consequence-009", "summarize-situation-010",
  ];

  it("10 pilotes en tête + corpus généré, schéma v1, ids uniques", () => {
    expect(raw.schemaVersion).toBe(1);
    const ids = raw.stories.map((s) => s.storyId);
    expect(new Set(ids).size).toBe(ids.length);
    expect(raw.stories.slice(0, 10).map((s) => s.storyId)).toEqual(PILOT_IDS);
    // Vagues 1–3 : Anglais Médical + Sémiologie + Terminologie (42+15+37 ch. × 3).
    const generated = raw.stories.slice(10);
    expect(generated.length).toBeGreaterThanOrEqual(282);
    const byModule = new Set<string>();
    for (const s of generated) {
      expect(s.provenance, s.storyId).toBe("generated:corpus-v1");
      expect(s.publicationStatus, s.storyId).toBe("draft");
      expect(s.chapter, s.storyId).toBeTruthy();
      expect(s.moduleIds.length, s.storyId).toBe(1);
      byModule.add(s.moduleIds[0]);
    }
    expect([...byModule].sort()).toEqual(["clinical_en", "semio", "termino"]);
  });

  it("seule l'histoire pilote est published — les 9 nouvelles attendent la relecture", () => {
    const published = raw.stories.filter((s) => s.publicationStatus === "published");
    expect(published.map((s) => s.storyId)).toEqual(["clinical-symptoms-001"]);
    for (const s of raw.stories) {
      if (s.storyId !== "clinical-symptoms-001") {
        expect(s.publicationStatus, s.storyId).toBe("draft");
        expect(s.review.language, s.storyId).toBe("pending");
        expect(s.review.medical, s.storyId).toBe("pending");
      }
    }
  });

  for (const s of raw.stories) {
    describe(`« ${s.storyId} »`, () => {
      it("champs obligatoires non vides dans les trois langues", () => {
        for (const key of ["title", "goal"] as const) {
          for (const l of ["en", "fr", "ar"] as const) {
            expect(s[key][l].trim().length, `${s.storyId}.${key}.${l}`).toBeGreaterThan(0);
          }
        }
        expect(s.segments.length).toBeGreaterThanOrEqual(5);
        for (const seg of s.segments) {
          expect(seg.en.trim()).not.toBe("");
          expect(seg.fr.trim()).not.toBe("");
          expect(seg.ar.trim()).not.toBe("");
        }
      });

      it("chaque question a des réponses acceptées sans doublon ni ambiguïté normalisée", () => {
        const all = [...s.questions, ...s.transfer.questions];
        expect(all.length).toBeGreaterThanOrEqual(4 + 2);
        for (const q of all) {
          expect(q.accepted.length, q.id).toBeGreaterThan(0);
          const normalized = q.accepted.map(normalizeAnswer);
          expect(new Set(normalized).size, q.id).toBe(normalized.length);
          expect(q.model.trim()).not.toBe("");
        }
      });

      it("les faits négatifs critiques portent un hint (anti-inversion)", () => {
        // Toute question dont UNE réponse acceptée contient une négation doit
        // avoir un hint : le guide interdit le feedback qui inverse un fait.
        for (const q of [...s.questions, ...s.transfer.questions]) {
          const hasNegative = q.accepted.some((a) => /\bnot\b|\bno\b|doesn/i.test(a));
          if (hasNegative && q.accepted.some((a) => /^(yes|no)\b/i.test(a.trim()))) {
            expect(q.hint, `${s.storyId}/${q.id}`).toBeDefined();
          }
        }
      });

      it("phraseIds référencent des expressions déclarées", () => {
        const known = new Set(s.targetPhrases.map((p) => p.id));
        expect(known.size).toBeGreaterThanOrEqual(3);
        for (const q of [...s.questions, ...s.transfer.questions]) {
          for (const pid of q.phraseIds ?? []) {
            expect(known.has(pid), `${s.storyId}/${q.id} → ${pid}`).toBe(true);
          }
        }
      });

      it("relatedTermIds existent réellement dans le corpus", () => {
        for (const tid of s.relatedTermIds) {
          expect(termIds.has(tid), `${s.storyId} → terme ${tid}`).toBe(true);
        }
      });

      it("drill + productions présents (3 transformations, amorces, modèles)", () => {
        expect(s.drill.items).toHaveLength(3);
        expect(s.drill.explain.fr.trim()).not.toBe("");
        for (const d of s.drill.items) {
          expect(d.accepted.length).toBeGreaterThan(0);
          expect(d.model.trim()).not.toBe("");
        }
        expect(s.retell.starters.length).toBeGreaterThanOrEqual(2);
        expect(s.transfer.task.starters.length).toBeGreaterThanOrEqual(2);
        expect(s.retell.model.trim()).not.toBe("");
        expect(s.transfer.task.model.trim()).not.toBe("");
      });
    });
  }

  it("les expressions partagées gardent un libellé identique entre histoires (3.3)", () => {
    const byId = new Map<string, { en: string; stories: string[] }>();
    for (const s of raw.stories) {
      for (const p of s.targetPhrases) {
        const prev = byId.get(p.id);
        if (prev) {
          expect(prev.en, `${p.id} libellés divergents entre ${prev.stories} et ${s.storyId}`).toBe(p.en);
          prev.stories.push(s.storyId);
        } else {
          byId.set(p.id, { en: p.en, stories: [s.storyId] });
        }
      }
    }
    const shared = [...byId.entries()].filter(([, v]) => v.stories.length > 1);
    expect(shared.length).toBeGreaterThanOrEqual(5); // une seule carte SM-2 par expression partagée
  });
});
