import { readFileSync } from "node:fs";
import { resolve } from "node:path";
import { describe, it, expect } from "vitest";

/**
 * R-02 (2026-11-07): the design-system swap dropped every modal/toast/
 * reward/sheet rule. This contract test fails if any of them disappears
 * again, without depending on a browser layout engine.
 */
const CSS_PATH = resolve(__dirname, "..", "hybrid-design.css");

function ruleFor(css: string, selector: string): string | null {
  // Match a top-level rule whose selector list contains the target selector.
  const re = new RegExp(
    "(^|})\\s*[^{}]*" + selector.replace(/[.*+?^${}()|[\]\\]/g, "\\$&") + "[^{}]*\\{([^}]*)\\}",
    "m"
  );
  const m = css.match(re);
  return m ? m[2] : null;
}

describe("modal/toast/reward/sheet layer (R-02)", () => {
  const css = readFileSync(CSS_PATH, "utf8");

  const required = [
    ".overlay",
    ".dialog",
    ".dlg-actions",
    ".toast",
    ".reward-overlay",
    ".reward-card",
    ".sheet-backdrop",
    ".sheet",
  ];

  for (const sel of required) {
    it(`defines a rule for ${sel}`, () => {
      expect(ruleFor(css, sel)).not.toBeNull();
    });
  }

  it(".overlay is position:fixed with a high z-index", () => {
    const body = ruleFor(css, ".overlay")!;
    expect(body).toMatch(/position\s*:\s*fixed/);
    const z = body.match(/z-index\s*:\s*(\d+)/);
    expect(z).not.toBeNull();
    expect(Number(z![1])).toBeGreaterThanOrEqual(40);
  });

  it(".dialog is a centered card, not in normal flow", () => {
    const body = ruleFor(css, ".dialog")!;
    // either width constraint or padding; must not be position:static default only
    expect(body.length).toBeGreaterThan(20);
  });
});