// @vitest-environment jsdom
import { describe, it, expect, vi } from "vitest";
import { render, screen } from "@testing-library/react";
import { ErrorBoundary } from "../ErrorBoundary";

function Boom(): never {
  throw new Error("boom");
}

describe("ErrorBoundary (R-04)", () => {
  it("renders the fallback when a child throws", () => {
    // Suppress React's expected error logging noise.
    const spy = vi.spyOn(console, "error").mockImplementation(() => {});
    render(
      <ErrorBoundary>
        <Boom />
      </ErrorBoundary>
    );
    expect(screen.getByText(/une erreur est survenue/i)).toBeTruthy();
    spy.mockRestore();
  });
});