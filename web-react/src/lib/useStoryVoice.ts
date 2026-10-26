import { useEffect, useState } from "react";
import { canSpeak, classifyEnglishVoices } from "./speech";

/**
 * État des voix pour la narration anglaise — RISK-002.
 *
 * `"unknown"` tant que le moteur n'a pas publié ses voix (`getVoices()` vide
 * au premier appel, puis événement `voiceschanged`) ; une fenêtre de grâce
 * borne cette attente : au-delà, sans voix du tout, on déclare l'audio
 * indisponible au lieu de laisser des boutons muets à l'étudiant.
 */
export type StoryVoiceState = ReturnType<typeof classifyEnglishVoices>;

const VOICE_GRACE_MS = 1500;

export function useStoryVoice(): StoryVoiceState {
  const [state, setState] = useState<StoryVoiceState>(() => snapshot());

  useEffect(() => {
    if (!canSpeak()) return; // absence totale de synthèse : l'écran la détecte
    const synth = window.speechSynthesis;
    const update = () => setState(snapshot());
    synth.addEventListener?.("voiceschanged", update);
    const grace = window.setTimeout(() => {
      // Moteur silencieux : ni `voiceschanged`, ni voix — on tranche.
      if (snapshot() === "unknown") setState("no-english");
    }, VOICE_GRACE_MS);
    return () => {
      synth.removeEventListener?.("voiceschanged", update);
      window.clearTimeout(grace);
    };
  }, []);

  return state;
}

function snapshot(): StoryVoiceState {
  if (!canSpeak()) return "unknown";
  return classifyEnglishVoices(window.speechSynthesis.getVoices());
}
