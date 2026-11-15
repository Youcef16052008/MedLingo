import { defineConfig } from "vitest/config";
import react from "@vitejs/plugin-react";

export default defineConfig({
  plugins: [react()],
  test: {
    // `forks` (défaut) échouait sur cette machine : 8 des 39 fichiers de test ne
    // démarraient jamais leur worker (`[vitest-pool]: Failed to start forks worker`,
    // « Timeout waiting for worker to respond »), le process sortait en 1 alors que
    // tous les tests exécutés passaient — 8 fichiers n'étaient donc jamais exécutés.
    // Mesuré : `forks` 31/39 fichiers + 8 erreurs, `threads` 39/39 fichiers, 5231 tests,
    // exit 0, sur la même machine (12 CPU logiques, Windows). Aucun test ne dépend
    // d'une isolation par processus (aucun `process.pid`/`child_process`/`process.exit`).
    pool: "threads",
    projects: [
      {
        test: {
          name: "domain",
          environment: "node",
          include: ["src/**/*.test.ts"],
          setupFiles: ["src/setupTests.ts"],
          testTimeout: 300000,
          hookTimeout: 300000,
        },
      },
      {
        test: {
          name: "ui",
          environment: "jsdom",
          include: ["src/**/*.test.tsx"],
          setupFiles: ["src/setupTests.ts"],
          testTimeout: 300000,
          hookTimeout: 300000,
        },
      },
    ],
  },
});