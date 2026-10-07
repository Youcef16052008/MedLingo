/** Équivalent web de `TtsManager.kt` (Web Speech API). */

let speakTimer: number | undefined;

export function speak(text: string, code = "en-US"): void {
  if (typeof window === "undefined" || !("speechSynthesis" in window) || !text) return;
  // Chrome : `cancel()` puis `speak()` dans le même tick reste parfois sans
  // effet (course documentée) — on décale la prise de parole.
  window.speechSynthesis.cancel();
  window.clearTimeout(speakTimer);
  speakTimer = window.setTimeout(() => {
    speakTimer = undefined;
    const u = new SpeechSynthesisUtterance(text);
    u.lang = code;
    u.rate = 0.95;
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
