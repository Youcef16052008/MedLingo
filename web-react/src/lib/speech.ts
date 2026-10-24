/** Équivalent web de `TtsManager.kt` (Web Speech API). */

let speakTimer: number | undefined;

/** Synthèse vocale disponible sur cet appareil ? (mode texte honnête sinon). */
export function canSpeak(): boolean {
  return typeof window !== "undefined" && "speechSynthesis" in window;
}

/**
 * Classe les voix disponibles pour la langue cible (anglais) — RISK-002 :
 * `speechSynthesis` présent mais sans voix EN (ou liste encore vide) doit
 * s'afficher honnêtement comme « audio indisponible », pas jouer silence.
 *  - "unknown" : liste vide (moteur non encore initialisé — réessayer).
 *  - "no-english" : des voix existent, aucune en anglais.
 *  - "ok" : au moins une voix anglaise.
 */
export function classifyEnglishVoices(
  voices: readonly { lang?: string }[]
): "ok" | "no-english" | "unknown" {
  if (voices.length === 0) return "unknown";
  return voices.some((v) => (v.lang ?? "").toLowerCase().startsWith("en"))
    ? "ok"
    : "no-english";
}

export function speak(text: string, code = "en-US", rate = 0.95): void {
  if (!canSpeak() || !text) return;
  // Chrome : `cancel()` puis `speak()` dans le même tick reste parfois sans
  // effet (course documentée) — on décale la prise de parole.
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

/** Coupe la synthèse (démontage de l'écran, changement de terme). */
export function stopSpeak(): void {
  if (typeof window === "undefined" || !("speechSynthesis" in window)) return;
  window.clearTimeout(speakTimer);
  speakTimer = undefined;
  window.speechSynthesis.cancel();
}

export function speakTerm(term: { en: string; ar: string }, lang: "fr" | "en" | "ar"): void {
  if (lang === "ar" && term.ar) {
    speak(term.ar, "ar-SA");
    return;
  }
  speak(term.en, "en-US");
}
