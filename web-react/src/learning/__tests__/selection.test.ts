import { describe, it, expect } from "vitest";
import { readFileSync } from "node:fs";
import { join, dirname } from "node:path";
import { fileURLToPath } from "node:url";
import { pickTodayStory } from "../selection";
import { storyById, setStories } from "../stories";
import type { Story, StoryFile } from "../types";

const __dirname = dirname(fileURLToPath(import.meta.url));
const NOW = 1_700_000_000_000;
const DAY = 86_400_000;

const file = JSON.parse(
  readFileSync(join(__dirname, "..", "..", "..", "public", "data", "stories.v1.json"), "utf-8")
) as StoryFile;
setStories(file);
const pilot = storyById("clinical-symptoms-001") as Story;

/** Deuxième histoire synthétique : mêmes expressions pour partager le SM-2. */
const second: Story = { ...pilot, storyId: "clinical-symptoms-002", title: { ...pilot.title, fr: "Autre" } };

const noProgress = {};

describe("pickTodayStory — priorité déterministe (RISK-004)", () => {
  it("rien d'entamé → première histoire du fichier", () => {
    expect(
      pickTodayStory({ stories: [pilot, second], exprSm2: {}, progress: noProgress, now: NOW })
    ).toBe(pilot);
  });

  it("séance interrompue prime sur tout, même avec des révisions dues ailleurs", () => {
    const exprSm2 = {
      "en:no-fever:v1": { rep: 1, ef: 2.5, iv: 1, due: NOW - 1 }, // due dans la 1re
    };
    expect(
      pickTodayStory({
        stories: [pilot, second],
        exprSm2,
        progress: { "clinical-symptoms-002": { completedAt: 0, savedAt: NOW } },
        now: NOW,
      })
    ).toBe(second);
  });

  it("expressions échues → l'histoire qui en a le plus", () => {
    const exprSm2 = {
      "en:have-headache:v1": { rep: 1, ef: 2.5, iv: 1, due: NOW - 1 },
      "en:no-fever:v1": { rep: 1, ef: 2.5, iv: 1, due: NOW - 1 },
    };
    // Les DEUX histoires partagent les phrases : ex æquo → la première.
    expect(
      pickTodayStory({ stories: [pilot, second], exprSm2, progress: noProgress, now: NOW })
    ).toBe(pilot);
  });

  it("tout terminé → rotation par la plus anciennement terminée", () => {
    const progress = {
      "clinical-symptoms-001": { completedAt: NOW - 10 * DAY, savedAt: NOW },
      "clinical-symptoms-002": { completedAt: NOW - DAY, savedAt: NOW },
    };
    expect(pickTodayStory({ stories: [pilot, second], exprSm2: {}, progress, now: NOW })).toBe(pilot);
  });

  it("corpus vide → undefined (la carte disparaît, pas de crash)", () => {
    expect(pickTodayStory({ stories: [], exprSm2: {}, progress: noProgress, now: NOW })).toBeUndefined();
  });
});
