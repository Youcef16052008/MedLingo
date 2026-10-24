import { describe, it, expect } from "vitest";
import { classifyEnglishVoices } from "../speech";

/** RISK-002 — classification pure, détachable du DOM. */
describe("classifyEnglishVoices", () => {
  it("liste vide → unknown (moteur pas encore initialisé)", () => {
    expect(classifyEnglishVoices([])).toBe("unknown");
  });

  it("des voix mais aucune en anglais → no-english", () => {
    expect(classifyEnglishVoices([{ lang: "fr-FR" }, { lang: "ar-SA" }])).toBe("no-english");
  });

  it("au moins une voix EN (variantes en-US/en-GB, casse) → ok", () => {
    expect(classifyEnglishVoices([{ lang: "fr-FR" }, { lang: "en-GB" }])).toBe("ok");
    expect(classifyEnglishVoices([{ lang: "EN-US" }])).toBe("ok");
  });

  it("voix sans champ lang → ignorée sans crash", () => {
    expect(classifyEnglishVoices([{}, { lang: "en-US" }])).toBe("ok");
    expect(classifyEnglishVoices([{}])).toBe("no-english");
  });
});
