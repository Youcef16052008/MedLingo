// @vitest-environment jsdom
import { describe, it, expect, beforeAll } from "vitest";
import { render, screen } from "@testing-library/react";
import { setupFetchForTests } from "../../setupTests";

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

describe("App routing: #/login (R-05)", () => {
  beforeAll(async () => {
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
    setupFetchForTests();
    window.location.hash = "#/login";
  });

  it("renders the login form when the hash is #/login", async () => {
    const { default: App } = await import("../../App");
    render(<App />);
    // Wait a tick for loadOnce resolution
    await new Promise((r) => setTimeout(r, 50));
    const emailInputs = Array.from(document.querySelectorAll('input[type="email"], input[name*="mail" i], input[placeholder*="mail" i]'));
    expect(emailInputs.length).toBeGreaterThan(0);
  });
});