import { describe, it, expect } from "vitest";
import { pearlOfDay } from "../PathScreen";
import type { Term } from "../../types";

const pearl = (id: number): Term => ({
  id,
  en: `t${id}`,
  fr: "",
  ar: "",
  defEn: "",
  defFr: "",
  defAr: "",
  etym: "",
  pearl: `p${id}`,
  mnemo: "",
  module: "anat",
  chapter: "",
  exEn: "",
  exFr: "",
  exAr: "",
  ipa: "",
});

describe("pearlOfDay (B10 — perle du jour tirée par date)", () => {
  const DAY = Date.UTC(2026, 9, 7); // 7 octobre 2026

  it("stabilité : même jour civil → même terme", () => {
    const terms = [pearl(1), pearl(2), pearl(3)];
    expect(pearlOfDay(terms, DAY)).toBe(pearlOfDay(terms, DAY + 3_600_000));
  });

  it("le tirage change d'un jour à l'autre", () => {
    const terms = [pearl(1), pearl(2), pearl(3)];
    const seen = new Set([
      pearlOfDay(terms, DAY)!.id,
      pearlOfDay(terms, DAY + 86_400_000)!.id,
      pearlOfDay(terms, DAY + 172_800_000)!.id,
    ]);
    expect(seen.size).toBe(3); // 3 jours consécutifs → les 3 perles sorties
  });

  it("ignore les termes sans perle", () => {
    expect(pearlOfDay([{ ...pearl(9), pearl: "" }], DAY)).toBeUndefined();
    expect(
      pearlOfDay(
        [
          { ...pearl(9), pearl: " " },
          { ...pearl(8), pearl: "" },
        ],
        DAY
      )
    ).toBeUndefined();
  });
});
