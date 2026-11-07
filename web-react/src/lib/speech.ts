/** Equivalent web de TtsManager.kt — Microsoft Edge TTS (doctor voice).

 * Voix medecin : en-US-GuyNeural, rate -10%, pauses 1500ms entre sections.
 * Fallback : Web Speech API si edge-tts indisponible.
 */

import { EdgeTTSClient, ProsodyOptions, OUTPUT_FORMAT } from "edge-tts-client";

const DOCTOR_VOICE = "en-US-GuyNeural";
const DOCTOR_RATE = -10; // percentage
const DOCTOR_VOLUME = 100;

let client: EdgeTTSClient | null = null;
let speakTimer: number | undefined;
let currentAudio: HTMLAudioElement | null = null;

/** Synthese vocale disponible sur cet appareil ? */
export function canSpeak(): boolean {
  return typeof window !== "undefined" && "AudioContext" in window;
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

/** Parle avec la voix medecin (edge-tts). */
export function speak(text: string, code = "en-US", rate = 0.9): void {
  if (!text) return;

  // Stop any ongoing speech
  stopSpeak();

  // Use edge-tts for English, fallback to Web Speech for others
  if (code.startsWith("en")) {
    speakEdgeTTS(text).catch((err) => {
      console.warn("Edge TTS failed, falling back to Web Speech:", err);
      fallbackSpeak(text, code, rate);
    });
  } else {
    fallbackSpeak(text, code, rate);
  }
}

/** Parle avec edge-tts. */
async function speakEdgeTTS(text: string): Promise<void> {
  const c = getClient();
  await c.setMetadata(DOCTOR_VOICE, OUTPUT_FORMAT.AUDIO_24KHZ_48KBITRATE_MONO_MP3);

  const options = new ProsodyOptions();
  options.rate = DOCTOR_RATE;
  options.volume = DOCTOR_VOLUME;

  const stream = c.toStream(text, options);

  const chunks: Uint8Array[] = [];
  await new Promise<void>((resolve) => {
    stream.on("data", (chunk: Uint8Array) => {
      chunks.push(chunk);
    });
    stream.on("end", () => resolve());
    stream.on("close", () => resolve());
  });

  // Combine chunks into a single blob
  const totalLength = chunks.reduce((acc, c) => acc + c.length, 0);
  const audioData = new Uint8Array(totalLength);
  let offset = 0;
  for (const chunk of chunks) {
    audioData.set(chunk, offset);
    offset += chunk.length;
  }

  // Play the audio
  const blob = new Blob([audioData], { type: "audio/mpeg" });
  const url = URL.createObjectURL(blob);
  const audio = new Audio(url);
  currentAudio = audio;

  await new Promise<void>((resolve, reject) => {
    audio.onended = () => {
      URL.revokeObjectURL(url);
      currentAudio = null;
      resolve();
    };
    audio.onerror = (e) => {
      URL.revokeObjectURL(url);
      currentAudio = null;
      reject(e);
    };
    audio.play().catch(reject);
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
  if (currentAudio) {
    currentAudio.pause();
    currentAudio = null;
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
