/** Equivalent web de TtsManager.kt - Microsoft Edge TTS (doctor voice).
 *
 * Voix medecin : en-US-GuyNeural, rate -10%, pauses 1500ms entre sections.
 * Fallback : Web Speech API si edge-tts indisponible.
 *
 * R-11 (2026-11-07): offline (Web Speech) is the DEFAULT. Network edge-TTS
 * is an explicit opt-in ("online" mode) because it transmits the studied term
 * to a third party and requires connectivity.
 */

import { EdgeTTSClient, ProsodyOptions, OUTPUT_FORMAT } from "edge-tts-client";

const DOCTOR_VOICE = "en-US-GuyNeural";
const DOCTOR_RATE = -10; // percentage
const DOCTOR_VOLUME = 100;

export type TtsMode = "offline" | "online";

let ttsMode: TtsMode = "offline";

// Restore persisted preference (R-11).
if (typeof window !== "undefined") {
  try {
    const saved = window.localStorage.getItem("medlingo_tts_mode");
    if (saved === "online" || saved === "offline") ttsMode = saved;
  } catch {
    // ignore
  }
}

export function getTtsMode(): TtsMode {
  return ttsMode;
}

export function setTtsMode(mode: TtsMode): void {
  ttsMode = mode === "online" ? "online" : "offline";
  if (typeof window !== "undefined") {
    try {
      window.localStorage.setItem("medlingo_tts_mode", ttsMode);
    } catch {
      // ignore
    }
  }
}

let client: EdgeTTSClient | null = null;
let speakTimer: number | undefined;
let currentAudio: HTMLAudioElement | null = null;
let currentAudioUrl: string | null = null;
let speakGeneration = 0;
let pendingResolve: (() => void) | null = null;

/** Synthese vocale disponible sur cet appareil ? */
export function canSpeak(): boolean {
  if (typeof window === "undefined") return false;
  // Web Speech is the offline baseline; edge-TTS only in online mode.
  if ("speechSynthesis" in window) return true;
  return ttsMode === "online";
}

/** Classe les voix disponibles pour la langue cible (anglais). */
export function classifyEnglishVoices(
  voices: readonly { lang?: string }[]
): "ok" | "no-english" | "unknown" {
  if (voices.length === 0) return "unknown";
  return voices.some((v) => (v.lang ?? "").toLowerCase().startsWith("en"))
    ? "ok"
    : "no-english";
}

/** Initialise le client edge-tts. */
function getClient(): EdgeTTSClient {
  if (!client) {
    client = new EdgeTTSClient(false);
  }
  return client;
}

/** Parle avec la voix medecin (edge-tts en mode online, Web Speech sinon). */
export function speak(text: string, code = "en-US", rate = 0.9): void {
  if (!text) return;

  // Invalidate any in-flight speak.
  speakGeneration++;
  const gen = speakGeneration;

  // Stop any ongoing speech
  stopSpeakInternal(gen);

  // Offline mode: always Web Speech (no third-party, no connectivity).
  if (ttsMode === "offline" || !code.startsWith("en")) {
    fallbackSpeak(text, code, rate);
    return;
  }

  speakEdgeTTS(text, gen).catch((err) => {
    if (gen !== speakGeneration) return; // superseded
    console.warn("Edge TTS failed, falling back to Web Speech:", err);
    fallbackSpeak(text, code, rate);
  });
}

/** Parle avec edge-tts. */
async function speakEdgeTTS(text: string, gen: number): Promise<void> {
  const c = getClient();
  await c.setMetadata(DOCTOR_VOICE, OUTPUT_FORMAT.AUDIO_24KHZ_48KBITRATE_MONO_MP3);

  if (gen !== speakGeneration) return; // superseded during await

  const options = new ProsodyOptions();
  options.rate = DOCTOR_RATE;
  options.volume = DOCTOR_VOLUME;

  const stream = c.toStream(text, options);

  const chunks: Uint8Array[] = [];
  const s = stream as any;
  await new Promise<void>((resolve, reject) => {
    const onData = (chunk: Uint8Array) => chunks.push(chunk);
    const cleanup = () => {
      s.removeListener("data", onData);
      s.removeListener("end", onEnd);
      s.removeListener("close", onClose);
      s.removeListener("error", onError);
    };
    const onEnd = () => {
      cleanup();
      resolve();
    };
    const onClose = () => onEnd();
    const onError = (e: unknown) => {
      cleanup();
      reject(e);
    };
    s.on("data", onData);
    s.on("end", onEnd);
    s.on("close", onClose);
    s.on("error", onError);
  });

  if (gen !== speakGeneration) return; // superseded during stream

  // Combine chunks into a single blob
  const totalLength = chunks.reduce((acc, c) => acc + c.length, 0);
  const audioData = new Uint8Array(totalLength);
  let offset = 0;
  for (const chunk of chunks) {
    audioData.set(chunk, offset);
    offset += chunk.length;
  }

  if (gen !== speakGeneration) return; // superseded

  // Play the audio
  const blob = new Blob([audioData], { type: "audio/mpeg" });
  const url = URL.createObjectURL(blob);
  const audio = new Audio(url);
  currentAudio = audio;
  currentAudioUrl = url;

  await new Promise<void>((resolve, reject) => {
    pendingResolve = resolve;
    const finish = (ok: boolean, err?: unknown) => {
      if (pendingResolve !== resolve) return; // superseded
      pendingResolve = null;
      URL.revokeObjectURL(url);
      if (currentAudio === audio) {
        currentAudio = null;
        currentAudioUrl = null;
      }
      if (ok) resolve();
      else reject(err ?? new Error("audio playback failed"));
    };
    audio.onended = () => finish(true);
    audio.onerror = (e) => finish(false, e);
    audio.play().catch((e) => finish(false, e));
  });
}

/** Fallback Web Speech API. */
function fallbackSpeak(text: string, code = "en-US", rate = 0.95): void {
  if (typeof window === "undefined" || !("speechSynthesis" in window)) return;
  window.speechSynthesis.cancel();
  window.clearTimeout(speakTimer);
  speakTimer = window.setTimeout(() => {
    speakTimer = undefined;
    const u = new SpeechSynthesisUtterance(text);
    u.lang = code;
    u.rate = rate;
    window.speechSynthesis.speak(u);
  }, 50);
}

/** Coupe la synthese. */
export function stopSpeak(): void {
  speakGeneration++; // invalidate in-flight
  stopSpeakInternal(speakGeneration);
}

function stopSpeakInternal(_gen: number): void {
  if (currentAudio) {
    try {
      currentAudio.pause();
    } catch {
      // ignore
    }
    if (currentAudioUrl) {
      try {
        URL.revokeObjectURL(currentAudioUrl);
      } catch {
        // ignore
      }
    }
    currentAudio = null;
    currentAudioUrl = null;
  }
  if (pendingResolve) {
    const r = pendingResolve;
    pendingResolve = null;
    try {
      r();
    } catch {
      // ignore
    }
  }
  if (client) {
    try {
      client.close();
    } catch {
      // ignore
    }
    client = null;
  }
  if (typeof window !== "undefined" && "speechSynthesis" in window) {
    window.clearTimeout(speakTimer);
    speakTimer = undefined;
    window.speechSynthesis.cancel();
  }
}

/** Parle un terme avec la voix medecin. */
export function speakTerm(term: { en: string; ar: string }, lang: "fr" | "en" | "ar"): void {
  if (lang === "ar" && term.ar) {
    speak(term.ar, "ar-SA");
    return;
  }
  speak(term.en, "en-US");
}

/** Nettoie les ressources. */
export function disposeSpeak(): void {
  stopSpeak();
  client = null;
}