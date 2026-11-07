import { describe, expect, it } from "vitest";
import { fillScore } from "../scoring";

// Miroir exact d'`ExerciseChecker.checkFill` (Android) :
// app/src/test/java/com/aistudio/medlingua/dzmed/ExerciseSpecFixesTest.kt
describe("fillScore", () => {
  it("rejects 1-char answers (no trivial fuzzy credit)", () => {
    expect(fillScore("a", "protects")).toBe(0);
  });

  it("R-19: arbitrary 3-char substring does not farm half-XP", () => {
    expect(fillScore("val", "Heart valve")).toBe(0);
  });

  it("rejects empty answers", () => {
    expect(fillScore("", "protects")).toBe(0);
    expect(fillScore("   ", "protects")).toBe(0);
  });

  it("gives 0.5 partial credit for a one-edit typo", () => {
    expect(fillScore("protect", "protects")).toBe(0.5);
  });

  it("gives 0 for arbitrary substring containment (no farming)", () => {
    expect(fillScore("it protects", "protects")).toBe(0);
    expect(fillScore("Heart", "Heart valve")).toBe(0);
  });

  it("accepts the exact answer case-insensitively", () => {
    expect(fillScore("PROTECTS", "protects")).toBe(1);
    expect(fillScore("  protects ", "protects")).toBe(1);
  });

  it("returns 0 for a blank accepted answer", () => {
    expect(fillScore("", "")).toBe(0);
    expect(fillScore("x", "")).toBe(0);
  });
});
