// @vitest-environment jsdom
/**
 * Tests composant de la séance « histoire » (P1.7).
 *
 * jsdom n'a ni `speechSynthesis` ni audio : c'est aussi ce qu'on teste —
 * l'écran doit rester utilisable et honnête sans voix (RISK-002).
 */
import { describe, it, expect, beforeAll, beforeEach } from "vitest";
import { render, screen, fireEvent, cleanup } from "@testing-library/react";
import { readFileSync } from "node:fs";
import { join, dirname } from "node:path";
import { fileURLToPath } from "node:url";
import type { ReactElement } from "react";
import { StorySessionScreen } from "../StorySessionScreen";
import { DialogProvider } from "../../components/DialogProvider";
import { ToastProvider } from "../../components/ToastProvider";
import { setStories } from "../../learning/stories";
import type { StoryFile } from "../../learning/types";
import {
  resetProgress,
  update,
  getState,
  STORY_SESSION_XP,
} from "../../domain/store";

const __dirname = dirname(fileURLToPath(import.meta.url));

function ui(jsx: ReactElement) {
  return render(
    <ToastProvider>
      <DialogProvider>{jsx}</DialogProvider>
    </ToastProvider>
  );
}

beforeAll(() => {
  const file = JSON.parse(
    readFileSync(join(__dirname, "..", "..", "..", "public", "data", "stories.v1.json"), "utf-8")
  ) as StoryFile;
  setStories(file);
  localStorage.clear();
  resetProgress(Date.now());
  // Assertions en français : jsdom démarre en `en-US`, le store suivrait.
  update((d) => void (d.lang = "fr"));
});

// Le store est un singleton : chaque test repart d'une séance vierge,
// sinon l'écran REPREND la session sauvegardée du test précédent.
beforeEach(() => {
  resetProgress(Date.now());
  update((d) => void (d.lang = "fr"));
});

describe("StorySessionScreen — séance complète en environnement sans audio", () => {
  it("affiche le titre, l'objectif et la bannière audio honnête (RISK-002)", () => {
    ui(<StorySessionScreen storyId="clinical-symptoms-001" onQuit={() => {}} />);
    // jsdom : pas de speechSynthesis → bannière « audio indisponible ».
    expect(screen.getByText(/Audio indisponible/i)).toBeTruthy();
    expect(screen.getByText(/Comprendre un symptôme simple/i)).toBeTruthy();
    expect(screen.getByText(/symptôme présent/i)).toBeTruthy();
    cleanup();
  });

  it("déroule écoute → question : réponse courte acceptée, feedback + modèle révélés", () => {
    ui(<StorySessionScreen storyId="clinical-symptoms-001" onQuit={() => {}} />);
    // 3 passages d'écoute → CIRCLING
    for (let i = 0; i < 3; i++) {
      fireEvent.click(screen.getAllByText(/Continuer/i)[0]);
    }
    // Question 1 en anglais
    expect(screen.getByText(/Does Yacine have a headache\?/i)).toBeTruthy();
    const input = screen.getByLabelText(/Ta réponse en anglais/i) as HTMLInputElement;
    fireEvent.change(input, { target: { value: "Yes" } });
    fireEvent.click(screen.getByText(/Vérifier/i));
    expect(screen.getByText(/Correct !/i)).toBeTruthy();
    expect(screen.getByText(/Yes, he has a headache\./i)).toBeTruthy();
    cleanup();
  });

  it("« Je ne sais pas encore » révèle le modèle sans compter d'erreur bloquante", () => {
    ui(<StorySessionScreen storyId="clinical-symptoms-001" onQuit={() => {}} />);
    for (let i = 0; i < 3; i++) fireEvent.click(screen.getAllByText(/Continuer/i)[0]);
    fireEvent.click(screen.getByText(/Je ne sais pas encore/i));
    expect(screen.getByText(/Yes, he has a headache\./i)).toBeTruthy();
    // On peut continuer normalement.
    fireEvent.click(screen.getAllByText(/Continuer/i)[0]);
    expect(screen.getByText(/Does he have a fever\?/i)).toBeTruthy();
    cleanup();
  });

  it("termine la boucle : bilan affiché et XP attribuée une seule fois (récompense idempotente)", () => {
    ui(<StorySessionScreen storyId="clinical-symptoms-001" onQuit={() => {}} />);
    for (let i = 0; i < 3; i++) fireEvent.click(screen.getAllByText(/Continuer/i)[0]);
    // Circling : 5 questions (dernière réponse = variantes acceptées couvertes ailleurs)
    const answers = ["Yes", "No", "a headache", "a headache", "Lina"];
    for (const a of answers) {
      const input = screen.getByLabelText(/Ta réponse en anglais/i) as HTMLInputElement;
      fireEvent.change(input, { target: { value: a } });
      fireEvent.click(screen.getByText(/Vérifier/i));
      fireEvent.click(screen.getAllByText(/Continuer/i)[0]);
    }
    // Drill : 3 transformations
    for (const a of ["has", "does", "have"]) {
      const input = screen.getByLabelText(/Ta réponse en anglais/i) as HTMLInputElement;
      fireEvent.change(input, { target: { value: a } });
      fireEvent.click(screen.getByText(/Vérifier/i));
      fireEvent.click(screen.getAllByText(/Continuer/i)[0]);
    }
    // Reformulation : je saute (production facultative, non bloquante)
    fireEvent.click(screen.getByText(/Passer/i));
    fireEvent.click(screen.getAllByText(/Continuer/i)[0]);
    // Transfert : 2 questions puis tâche sautée
    for (const a of ["a sore throat", "no"]) {
      const input = screen.getByLabelText(/Ta réponse en anglais/i) as HTMLInputElement;
      fireEvent.change(input, { target: { value: a } });
      fireEvent.click(screen.getByText(/Vérifier/i));
      fireEvent.click(screen.getAllByText(/Continuer/i)[0]);
    }
    fireEvent.click(screen.getByText(/Passer/i));
    fireEvent.click(screen.getAllByText(/Continuer/i)[0]);
    // Bilan
    expect(screen.getByText(/Séance terminée/i)).toBeTruthy();
    const gained = getState().xp;
    expect(gained).toBe(STORY_SESSION_XP);
    // Aucune progression de leçon classique n'a été écrite (piège de migration §9.3)
    expect(Object.keys(getState().lessonBest)).toHaveLength(0);
    expect(Object.keys(getState().sm2)).toHaveLength(0);
    // Les expressions ciblées sont planifiées dans l'espace séparé
    expect(Object.keys(getState().exprSm2).length).toBeGreaterThanOrEqual(3);
    cleanup();
  });

  it("un id inconnu (après chargement réglé) affiche l'écran invalide, pas un crash", () => {
    ui(<StorySessionScreen storyId="bogus" onQuit={() => {}} />);
    expect(screen.getByText(/Cette leçon n'existe pas/i)).toBeTruthy();
    cleanup();
  });
});

