import { describe, it, expect } from "vitest";
import { norm } from "../utils";

describe("norm", () => {
  it("plie les diacritiques latins : café = cafe (B44)", () => {
    expect(norm("Café")).toBe(norm("cafe"));
    expect(norm("Hôpital")).toBe(norm("hopital"));
    expect(norm("Élevage")).toBe(norm("elevage"));
  });

  it("laisse l'arabe intact (signes hors du bloc U+0300–U+036F)", () => {
    expect(norm("نبض")).toBe("نبض");
    expect(norm("النبض")).toBe(norm("النبض"));
  });

  it("minuscules, trim, espaces multiples et ponctuation", () => {
    expect(norm("  Hello,  World! ")).toBe("hello world");
    expect(norm(null)).toBe("");
  });
});
