/**
 * Setup Vitest pour les tests d'intégration.
 *
 * Cette fonction charge les données de parcours (chapters.json, lesson_map.json)
 * de manière synchrone depuis le disque et les injecte dans le module data/load.ts
 * qui les réutilise pour construire le parcours.
 *
 * Configure aussi `global.fetch` pour qu'il lise les fichiers JSON au lieu de faire
 * une requête réseau (non disponible en environnement Node.js de test).
 */

import fs from "fs";
import path from "path";
import { vi } from "vitest";

export function loadTestData(): { chapters: any[]; lessonMap: any } {
  const baseDir = path.resolve(__dirname, "..", "public", "data");
  const chaptersPath = path.join(baseDir, "chapters.json");
  const lessonMapPath = path.join(baseDir, "lesson_map.json");

  const chaptersData = JSON.parse(fs.readFileSync(chaptersPath, "utf-8"));
  const lessonMapData = JSON.parse(fs.readFileSync(lessonMapPath, "utf-8"));

  return { chapters: chaptersData, lessonMap: lessonMapData };
}

/**
 * Configure fetch global pour les tests Node.js.
 * Remplace fetch() par un lecteur de fichiers local.
 */
export function setupFetchForTests() {
  const originalFetch = global.fetch;

  // Type-safe mock fetch : accepte string | Request, retourne Response
  global.fetch = vi.fn((input: string | Request, _init?: RequestInit): Promise<Response> => {
    const urlStr = typeof input === "string" ? input : input.url;

    // Lit un fichier JSON depuis le disque pour les URLs commençant par /data/
    if (urlStr.startsWith("/data/")) {
      const fileName = urlStr.replace("/data/", "");
      // Chemin absolu vers le dossier public/data du projet
      const projectRoot = path.resolve(__dirname, ".."); // src -> web-react
      const publicDataDir = path.resolve(projectRoot, "public", "data");
      const fullPath = path.join(publicDataDir, fileName);
      const content = fs.readFileSync(fullPath, "utf-8");
      return Promise.resolve({
        ok: true,
        status: 200,
        json: () => Promise.resolve(JSON.parse(content)),
      } as Response);
    }

    // Fallback : lire le fichier directement
    const fullPath = path.resolve(
      __dirname,
      "..",
      "..",
      urlStr.replace(/^\//, "")
    );
    const content = fs.readFileSync(fullPath, "utf-8");
    return Promise.resolve({
      ok: true,
      status: 200,
      json: () => Promise.resolve(JSON.parse(content)),
    } as Response);
  }) as typeof global.fetch;

  return () => {
    (global.fetch as typeof originalFetch) = originalFetch;
  };
}
