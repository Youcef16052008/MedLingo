import { useEffect, useState } from "react";

/** Petit routeur hash — équivalent React du routeur `#/...` du référentiel. */

const NAV_EVENT = "medlingo:navigate";

/** Hash brut (`quiz?module=anat&level=3`) — la query FAIT partie de la valeur :
 *  changer uniquement la query doit re-rendre et remonter l'écran cadré (B23). */
function currentRaw(): string {
  if (typeof window === "undefined") return "path";
  return window.location.hash.replace(/^#\/?/, "") || "path";
}

/** Nom de route (`quiz`), sans query — pour le switch d'écrans et les onglets. */
export function routeName(raw: string): string {
  return raw.split("/")[0].split("?")[0];
}

export function useHashRoute(): string {
  const [route, setRoute] = useState(currentRaw);
  // Compteur : force un re-rendu même quand le hash cible est identique
  // (`navigate()` vers la route courante n'émet pas `hashchange`, B24).
  const [, setTick] = useState(0);

  useEffect(() => {
    const update = () => {
      setRoute(currentRaw());
      setTick((n) => n + 1);
    };
    window.addEventListener("hashchange", update);
    window.addEventListener(NAV_EVENT, update);
    return () => {
      window.removeEventListener("hashchange", update);
      window.removeEventListener(NAV_EVENT, update);
    };
  }, []);

  return route;
}

export function navigate(path: string): void {
  const next = (path.startsWith("#") ? path.slice(1) : path).replace(/^\/+/, "");
  if (currentRaw() === next) {
    // Déjà sur cette route : on notifie quand même (re-rendu / retour en haut).
    window.dispatchEvent(new Event(NAV_EVENT));
    return;
  }
  window.location.hash = `#/${next}`;
}

/** Onglet actif pour la barre du bas. */
export function activeTab(route: string): string {
  const name = routeName(route);
  // Sous-écrans : recherche → Cours ; fiches/quiz → Révision ; ancien accueil → Parcours.
  if (name === "search") return "modules";
  if (name === "flash" || name === "quiz") return "practice";
  if (name === "home") return "path";
  return ["path", "practice", "leagues", "modules", "profile"].includes(name)
    ? name
    : "path";
}
