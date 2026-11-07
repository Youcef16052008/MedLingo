import { describe, it, expect } from "vitest";
import {
  canSpeak,
  classifyEnglishVoices,
  getTtsMode,
  setTtsMode,
} from "../speech";

describe("speech (R-11, R-13)", () => {
  it("defaults to offline mode", () => {
    // Reset to be safe.
    setTtsMode("offline");
    expect(getTtsMode()).toBe("offline");
  });

  it("canSpeak is false in a node environment without speechSynthesis", () => {
    setTtsMode("offline");
    expect(canSpeak()).toBe(false);
  });

  it("classifyEnglishVoices detects english", () => {
    expect(classifyEnglishVoices([{ lang: "en-US" }])).toBe("ok");
    expect(classifyEnglishVoices([{ lang: "fr-FR" }])).toBe("no-english");
    expect(classifyEnglishVoices([])).toBe("unknown");
  });

  it("setTtsMode persists and normalizes", () => {
    setTtsMode("online");
    expect(getTtsMode()).toBe("online");
    setTtsMode("bogus" as any);
    expect(getTtsMode()).toBe("offline");
    setTtsMode("offline");
  });
});