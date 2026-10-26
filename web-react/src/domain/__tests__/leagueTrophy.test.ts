import { describe, it, expect } from "vitest";
import { evaluate } from "../trophies";
import { makeDefaults } from "../store";
import type { AppState } from "../store";

/** BUG-020 : une relégation ne débloque jamais « promotion ». */
function withLeague(league: AppState["league"]): AppState {
  return { ...makeDefaults(0), league } as AppState;
}

describe("trophée league_promoted (BUG-020)", () => {
  it("promotion → débloqué (bestTier au-dessus de bronze)", () => {
    const won = evaluate(withLeague({ tier: "silver", weekStart: "", botSeed: 1, bestTier: "silver" }));
    expect(won).toContain("league_promoted");
  });

  it("relégation gold→silver SANS promotion passée → pas débloqué", () => {
    // L'utilisateur n'a jamais validé un palier au-dessus de bronze :
    // être silver après relégation ne récompense rien.
    const won = evaluate(withLeague({ tier: "silver", weekStart: "", botSeed: 1, bestTier: "bronze" }));
    expect(won).not.toContain("league_promoted");
  });

  it("retombé en bronze mais bestTier=silver → reste débloqué (déjà gagné)", () => {
    const won = evaluate(withLeague({ tier: "bronze", weekStart: "", botSeed: 1, bestTier: "silver" }));
    expect(won).toContain("league_promoted");
  });
});
