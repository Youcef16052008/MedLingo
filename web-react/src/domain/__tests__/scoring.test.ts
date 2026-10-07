import { describe, expect, it } from "vitest";
import { fillScore } from "../scoring";

// Miroir exact d'`ExerciseChecker.checkFill` (Android) :
// app/src/test/java/com/example/ExerciseSpecFixesTest.kt
describe("fillScore", () => {
  it("rejects 1-char answers (no trivial fuzzy credit)", () => {
    expect(fillScore("a", "protects")).toBe(0);
  });

  it("rejects empty answers", () => {
    expect(fillScore("", "protects")).toBe(0);
    expect(fillScore("   ", "protects")).toBe(0);
  });

  it("gives 0.5 partial credit for a meaningful prefix", () => {
    expect(fillScore("protect", "protects")).toBe(0.5);
  });

  it("gives 0.5 when the user answer contains the accepted one", () => {
    expect(fillScore("it protects", "protects")).toBe(0.5);
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
