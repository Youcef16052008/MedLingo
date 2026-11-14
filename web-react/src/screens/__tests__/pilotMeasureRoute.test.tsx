// @vitest-environment jsdom
/**
 * P3.1/P3.2 — tests écran de la mesure (`#/pilot/mesure`).
 *
 * Le manifeste RÉEL est 100 % draft : en parcours réel, la mesure refuse de
 * démarrer (message explicite, même verrou que l'entraînement). En aperçu
 * développeur, une séance T0 démarre, enregistre SANS afficher de correction
 * (aucun « Correct ! », aucune réponse révélée après enregistrement), et
 * l'interruption est consignée sans fabriquer de score.
 */
import { describe, it, expect, beforeEach, afterEach } from "vitest";
import { render, screen, fireEvent, cleanup } from "@testing-library/react";
import type { ReactElement } from "react";
import { PilotMeasureScreen } from "../PilotMeasureScreen";
import { DialogProvider } from "../../components/DialogProvider";
import { ToastProvider } from "../../components/ToastProvider";
import { resetProgress, update } from "../../domain/store";
import { MEASURE_STORAGE_KEY } from "../../pilot/measureStorage";

function ui(jsx: ReactElement) {
  return render(
    <ToastProvider>
      <DialogProvider>{jsx}</DialogProvider>
    </ToastProvider>,
  );
}

beforeEach(() => {
  localStorage.clear();
  resetProgress(Date.now());
  update((d) => void (d.lang = "fr"));
});

afterEach(() => {
  cleanup();
  window.location.hash = "";
});

describe("PilotMeasureScreen — parcours réel (verrou actif)", () => {
  it("affiche les temps, le pseudonyme et refuse de démarrer sans concept approuvé", () => {
    ui(<PilotMeasureScreen />);
    expect(screen.getByText(/T0 avant entraînement/i)).toBeTruthy();
    expect(screen.getByText(/Aucune correction n'est affichée/i)).toBeTruthy();
    expect(screen.getByText(/aucune donnée patient/i)).toBeTruthy();
    // 0 concept approuvé → pas de bouton Démarrer, message explicite.
    expect(screen.getByText(/Aucun concept approuvé : la mesure exige un contenu relu/i)).toBeTruthy();
    expect(screen.queryByText("Démarrer")).toBeNull();
    // Export/reset présents mais inertes sans données.
    expect(screen.getByText("Exporter la mesure (JSON)")).toBeTruthy();
    // Aucune donnée de mesure persistée à ce stade.
    expect(localStorage.getItem(MEASURE_STORAGE_KEY)).toBeNull();
  });
});

describe("PilotMeasureScreen — aperçu développeur (?dev=1)", () => {
  it("séance T0 : enregistre sans montrer de verdict ni de correction ; interruption consignée", () => {
    window.location.hash = "#/pilot/mesure?dev=1";
    ui(<PilotMeasureScreen />);
    fireEvent.click(screen.getByText("Démarrer"));
    // Séance T0 ouverte, item 1/60 (30 concepts × 2 items, version A).
    expect(screen.getByText(/T0 · Item 1 \/ 60/)).toBeTruthy();
    expect(screen.getByText(/Aucune correction n'est affichée/i)).toBeTruthy();
    // Répondre le premier item (QCM reconnaissance — concept 1 = Head).
    const option = screen
      .getAllByRole("button")
      .find((b) => b.textContent?.trim() === "Head");
    expect(option).toBeTruthy();
    fireEvent.click(option!);
    // PAS de verdict affiché : ni correct ni faux ni la réponse révélée.
    expect(screen.queryByText(/Correct !/)).toBeNull();
    expect(screen.queryByText(/Pas encore/)).toBeNull();
    // L'item suivant est servi (avance automatique après enregistrement).
    expect(screen.getByText(/Item 2 \/ 60/)).toBeTruthy();
    // Interruption : consignée, aucun score fabriqué.
    fireEvent.click(screen.getByText("←"));
    expect(screen.getByText(/Mesure interrompue/i)).toBeTruthy();
    const persisted = JSON.parse(localStorage.getItem(MEASURE_STORAGE_KEY)!);
    const s = persisted.sessions[0];
    expect(s.time).toBe("T0");
    expect(s.interrupted).toBe(true);
    expect(s.finished).toBe(false);
    expect(s.results).toHaveLength(1);
    // T1 reste bloqué : T0 n'est pas terminée.
    expect(screen.getByText(/Terminez d'abord la mesure T0/i)).toBeTruthy();
  });

  it("pseudonyme éditable, persisté, sans donnée personnelle imposée", () => {
    window.location.hash = "#/pilot/mesure?dev=1";
    ui(<PilotMeasureScreen />);
    const input = screen.getByPlaceholderText("ex. participant-01") as HTMLInputElement;
    fireEvent.change(input, { target: { value: "participant-07" } });
    const persisted = JSON.parse(localStorage.getItem(MEASURE_STORAGE_KEY)!);
    expect(persisted.pseudonym).toBe("participant-07");
  });
});
