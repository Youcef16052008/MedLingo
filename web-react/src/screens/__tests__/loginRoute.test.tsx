// @vitest-environment jsdom
import { describe, it, expect, beforeEach, vi } from "vitest";
import { render, screen } from "@testing-library/react";
import { setupFetchForTests } from "../../setupTests";

/**
 * R-05 / décision D2 : `#/login` ne monte le formulaire de connexion que si
 * `SYNC_ENABLED` est vrai (ADR-3 désactivé par défaut). Ce fichier vérifiait
 * auparavant le formulaire sur la route sans jamais activer le drapeau : il ne
 * passait que parce que le `render()` du test précédent n'était pas démonté et
 * laissait son `input[type="email"]` dans le document (le cleanup automatique de
 * `@testing-library/react` n'est actif qu'avec `globals: true`). Depuis l'ajout
 * du cleanup dans `setupTests.ts`, le test échouait — à raison. Les deux
 * branches du contrat sont maintenant couvertes explicitement.
 */

function stubMatchMedia() {
  if (!window.matchMedia) {
    window.matchMedia = (query: string) =>
      ({
        matches: false,
        media: query,
        onchange: null,
        addListener: () => {},
        removeListener: () => {},
        addEventListener: () => {},
        removeEventListener: () => {},
        dispatchEvent: () => false,
      }) as MediaQueryList;
  }
}

const emailInputsInDocument = () =>
  Array.from(
    document.querySelectorAll(
      'input[type="email"], input[name*="mail" i], input[placeholder*="mail" i]'
    )
  );

describe("LoginScreen (R-05)", () => {
  it("renders an email input and a password input", async () => {
    const { LoginScreen } = await import("../LoginScreen");
    render(<LoginScreen />);
    // Fallback-friendly: queryBy* returns null instead of throwing.
    const emailInput =
      screen.queryByPlaceholderText(/@/) ||
      screen.queryByLabelText(/email/i) ||
      document.querySelector('input[type="email"]');
    expect(emailInput).toBeTruthy();
    const inputs = screen.getAllByRole("textbox");
    expect(inputs.length).toBeGreaterThan(0);
  });
});

describe("App routing: #/login (R-05, D2)", () => {
  beforeEach(() => {
    stubMatchMedia();
    setupFetchForTests();
    window.location.hash = "#/login";
    vi.resetModules();
  });

  it("sync désactivée (défaut D2) : la route retombe sur le parcours, pas de formulaire", async () => {
    vi.doMock("../../lib/featureFlags", () => ({ SYNC_ENABLED: false }));
    const { default: App } = await import("../../App");
    render(<App />);
    // Laisse une chance à `loadOnce` de résoudre puis à React de rendre.
    await new Promise((r) => setTimeout(r, 50));
    expect(emailInputsInDocument()).toHaveLength(0);
  });

  it("sync activée : la route monte le formulaire de connexion", async () => {
    vi.doMock("../../lib/featureFlags", () => ({ SYNC_ENABLED: true }));
    const { default: App } = await import("../../App");
    render(<App />);
    await new Promise((r) => setTimeout(r, 50));
    expect(emailInputsInDocument().length).toBeGreaterThan(0);
  });
});
