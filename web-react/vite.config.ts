import { defineConfig } from "vitest/config";
import react from "@vitejs/plugin-react";

export default defineConfig({
  plugins: [react()],
  test: {
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