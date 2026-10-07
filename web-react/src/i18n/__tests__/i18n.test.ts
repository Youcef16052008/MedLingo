import { describe, it, expect } from "vitest";
import { readFileSync } from "node:fs";
import { join, dirname } from "node:path";
import { fileURLToPath } from "node:url";
import { DICT, translate } from "../index";

const dir = dirname(fileURLToPath(import.meta.url));
const root = join(dir, "..", "..", ".."); // web-react/

describe("dictionnaires i18n", () => {
  const langs = ["fr", "en", "ar"] as const;

  it("les trois langues couvrent exactement les mêmes clés", () => {
    const frKeys = Object.keys(DICT.fr).sort();
    for (const lang of langs) {
      expect(Object.keys(DICT[lang]).sort(), `clés manquantes en ${lang}`).toEqual(frKeys);
    }
  });

  it("aucune valeur n'est vide", () => {
    for (const lang of langs) {
      for (const [k, v] of Object.entries(DICT[lang])) {
        expect(String(v).trim().length, `${lang}.${k} vide`).toBeGreaterThan(0);
      }
    }
  });

  it("traduit avec repli sur le français pour une clé inconnue", () => {
    expect(translate("fr", "navHome")).toBe("Accueil");
    expect(translate("en", "navHome")).toBe("Home");
    expect(translate("ar", "navHome")).toBe("الرئيسية");
  });
});

describe("données extraites des seeds Kotlin", () => {
  const terms = JSON.parse(
    readFileSync(join(root, "public", "data", "terms.json"), "utf8")
  ) as { id: number; en: string; module: string; chapter: string }[];
  const exercises = JSON.parse(
    readFileSync(join(root, "public", "data", "exercises.json"), "utf8")
  ) as { id: number; level: number; type: string; options: string[]; answer: string }[];

  it("charge le corpus complet", () => {
    expect(terms.length).toBe(4512);
    expect(exercises.length).toBe(44);
  });

  it("les ids sont uniques et les termes non vides", () => {
    expect(new Set(terms.map((t) => t.id)).size).toBe(terms.length);
    expect(terms.every((t) => t.en.trim().length > 0)).toBe(true);
    expect(terms.every((t) => t.module.trim().length > 0)).toBe(true);
  });

  it("les ids sont contigus et alignés sur les seeds Kotlin", () => {
    // Le web ne renumérote plus : un id doit être le même qu côté Android, sinon
    // l'historique de révision ne survit pas d'une plateforme à l'autre.
    const sorted = terms.map((t) => t.id).sort((a, b) => a - b);
    expect(sorted[0]).toBe(1);
    expect(sorted.at(-1)).toBe(sorted.length);
    sorted.forEach((id, i) => expect(id).toBe(i + 1));
  });

  it("chaque module de terme existe dans la liste MODULES", async () => {
    const { MODULES } = await import("../../data/modules");
    const known = new Set(MODULES.map((m) => m.fr));
    const unknown = [...new Set(terms.map((t) => t.module))].filter((m) => !known.has(m));
    expect(unknown, `modules inconnus: ${unknown.join(", ")}`).toEqual([]);
    // chaque module de MODULES doit avoir du contenu
    for (const m of MODULES) {
      expect(terms.some((t) => t.module === m.fr), `module vide: ${m.fr}`).toBe(true);
    }
  });

  it("les exercices utilisent des types et niveaux supportés", () => {
    const types = new Set(["mcq", "fill_blank", "sentence_order", "matching", "reading", "clinical_case"]);
    expect(exercises.every((e) => types.has(e.type))).toBe(true);
    expect(exercises.every((e) => e.level >= 1 && e.level <= 6)).toBe(true);
    expect(exercises.every((e) => e.answer.trim().length > 0)).toBe(true);
    expect(exercises.filter((e) => e.type === "mcq").every((e) => e.options.length >= 2)).toBe(true);
    expect(exercises.filter((e) => e.type === "matching").every((e) => e.options.every((o) => o.includes(":")))).toBe(true);
  });
});
