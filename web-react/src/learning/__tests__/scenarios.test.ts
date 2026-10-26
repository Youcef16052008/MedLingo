import { describe, it, expect } from "vitest";
import { readFileSync } from "node:fs";
import { join, dirname } from "node:path";
import { fileURLToPath } from "node:url";
import {
  initSession,
  sessionReducer,
  sessionSummary,
} from "../session";
import { setStories, storyById } from "../stories";

const __dirname = dirname(fileURLToPath(import.meta.url));

/**
 * Scénarios PARTAGÉS web/Kotlin (§6.2 du guide) : ce test et
 * `StorySessionEngineTest.kt` lisent les MÊMES fichiers —
 *   - scénarios : app/src/test/resources/learning/story-scenarios.json
 *   - contenu   : app/src/main/assets/learning/stories.v1.json (copie
 *     identique de public/data/stories.v1.json, parité check:stories)
 * Une divergence de comportement entre les deux moteurs fait échouer au moins
 * un des deux côtés.
 */
const REPO = join(__dirname, "..", "..", "..", "..");
const scenariosFile = JSON.parse(
  readFileSync(join(REPO, "app", "src", "test", "resources", "learning", "story-scenarios.json"), "utf-8")
);
const storyFile = JSON.parse(
  readFileSync(join(REPO, "app", "src", "main", "assets", "learning", "stories.v1.json"), "utf-8")
);
setStories(storyFile);
const story = storyById("clinical-symptoms-001");
if (!story) throw new Error("histoire pilote absente du fichier Android");

describe("scénarios partagés web/Kotlin", () => {
  it("le fichier de scénarios est la version 1", () => {
    expect(scenariosFile.schemaVersion).toBe(1);
    expect(scenariosFile.scenarios.length).toBeGreaterThan(0);
  });

  for (const scenario of scenariosFile.scenarios) {
    it(`« ${scenario.id} » — mêmes décisions que le moteur Kotlin`, () => {
      let s = initSession(story, { duePhraseIds: scenario.duePhraseIds ?? [] });
      for (const a of scenario.actions) {
        const action = (() => {
          switch (a.type) {
            case "ADVANCE": return { type: "ADVANCE" as const };
            case "SUBMIT_ANSWER": return { type: "SUBMIT_ANSWER" as const, value: a.value ?? "" };
            case "SUBMIT_DRILL": return { type: "SUBMIT_DRILL" as const, value: a.value ?? "" };
            case "REVEAL": return { type: "REVEAL" as const };
            case "HELP": return { type: "HELP" as const };
            case "REVIEW_RATED": return { type: "REVIEW_RATED" as const, phraseId: a.phraseId ?? "", remembered: a.remembered ?? true };
            case "RETELL_DONE": return { type: "RETELL_DONE" as const, attempted: a.attempted ?? true };
            case "TRANSFER_TASK_DONE": return { type: "TRANSFER_TASK_DONE" as const, attempted: a.attempted ?? true };
            default: throw new Error(`action inconnue : ${a.type}`);
          }
        })();
        s = sessionReducer(s, action, story);
      }
      const sum = sessionSummary(s, story);
      expect(s.finished, `${scenario.id} finished`).toBe(scenario.expect.finished);
      expect(s.step, `${scenario.id} step`).toBe(scenario.expect.step);
      expect(sum.firstTry, `${scenario.id} firstTry`).toBe(scenario.expect.firstTry);
      expect(sum.helped, `${scenario.id} helped`).toBe(scenario.expect.helped);
      expect(sum.mistakes, `${scenario.id} mistakes`).toBe(scenario.expect.mistakes);
      expect(sum.difficultPhrases, `${scenario.id} difficultPhrases`).toEqual(scenario.expect.difficultPhrases);
    });
  }
});
