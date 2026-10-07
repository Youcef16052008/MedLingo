import { translate, type TKey } from "./index";
import { update, useAppState } from "../domain/store";
import type { Lang } from "../types";

/** Récupère la fonction `t` et la langue courante depuis le store. */
export function useT(): { t: (k: TKey) => string; lang: Lang; setLang: (l: Lang) => void } {
  const { lang } = useAppState();
  return {
    lang,
    t: (k: TKey) => translate(lang, k),
    setLang: (l: Lang) => update((draft) => void (draft.lang = l)),
  };
}

/** Applique `lang`/`dir` sur `<html>` (nécessaire pour l'RTL arabe). */
export function applyDocumentLang(lang: Lang): void {
  if (typeof document === "undefined") return;
  document.documentElement.lang = lang;
  document.documentElement.dir = lang === "ar" ? "rtl" : "ltr";
}
