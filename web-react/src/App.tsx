import { useEffect, useRef, useState } from "react";
import { useT, applyDocumentLang } from "./i18n/useT";
import { useAppState, update, getState } from "./domain/store";
import { loadOnce, isLoaded } from "./data/load";
import { useHashRoute, routeName } from "./lib/router";
import { rollover, weekStartKey, YOU } from "./domain/leagues";
import { TopBar } from "./components/TopBar";
import { TabBar } from "./components/TabBar";
import { DialogProvider, useDialog } from "./components/DialogProvider";
import { ToastProvider } from "./components/ToastProvider";
import { PathScreen } from "./screens/PathScreen";
import { PracticeScreen } from "./screens/PracticeScreen";
import { LeaguesScreen } from "./screens/LeaguesScreen";
import { ModulesScreen } from "./screens/ModulesScreen";
import { SearchScreen } from "./screens/SearchScreen";
import { FlashScreen } from "./screens/FlashScreen";
import { QuizScreen } from "./screens/QuizScreen";
import { ProfileScreen } from "./screens/ProfileScreen";
import { navigate } from "./lib/router";

function Shell() {
  const { t, lang } = useT();
  const { open } = useDialog();
  const s = useAppState();
  const route = useHashRoute();
  const [error, setError] = useState<string | null>(null);
  const [ready, setReady] = useState(false);
  const introDone = useRef(false);

  /* ---------- chargement des données extraites des seeds ---------- */
  useEffect(() => {
    let cancelled = false;
    loadOnce()
      .then(() => {
        if (!cancelled) setReady(true);
      })
      .catch((e: unknown) => {
        if (!cancelled)
          setError(e instanceof Error ? e.message : "load failed");
      });
    return () => {
      cancelled = true;
    };
  }, []);

  /* ---------- direction de lecture (RTL pour l'arabe) ---------- */
  useEffect(() => {
    applyDocumentLang(lang);
  }, [lang]);

  /* ---------- B2 : changement de semaine traité au démarrage ----------
     Sinon l'XP gagnée le lundi avant d'ouvrir l'onglet Ligues est comptée
     dans la semaine close (classement faussé) jusqu'à la visite de l'onglet. */
  useEffect(() => {
    const st = getState();
    if (st.league.weekStart !== weekStartKey(Date.now())) {
      update((d) => void rollover(d, Date.now(), YOU));
    }
  }, []);

  /* ---------- fenêtre d'accueil (une seule fois) ---------- */
  useEffect(() => {
    if (!ready || s.introSeen || introDone.current) return;
    introDone.current = true;
    void open({
      icon: "🇸🇩",
      title: t("introTitle"),
      text: t("introText"),
      actions: [{ id: "go", label: t("introCta") }],
    }).then(() => update((d) => void (d.introSeen = true)));
  }, [ready, s.introSeen, open, t]);

  /* ---------- retour en haut à chaque navigation ---------- */
  useEffect(() => {
    window.scrollTo({ top: 0 });
  }, [route]);

  /* ---------- PWA : service worker uniquement en production ---------- */
  useEffect(() => {
    if (import.meta.env.PROD && "serviceWorker" in navigator) {
      navigator.serviceWorker.register("/sw.js").catch(() => undefined);
    }
  }, []);

  if (error) {
    return (
      <div id="app">
        <TopBar />
        <main className="view">
          <div className="empty card" style={{ marginTop: 24 }}>
            <span className="empty-ico">⚠️</span>
            <h2>{t("loadErrorTitle")}</h2>
            <p className="muted">{error}</p>
          </div>
        </main>
        <TabBar route={route} />
      </div>
    );
  }

  if (!ready || !isLoaded()) {
    return (
      <div id="app">
        <TopBar />
        <main className="view">
          <div className="skeleton" />
          <div className="skeleton" />
          <div className="skeleton" />
          <p className="tiny" style={{ textAlign: "center" }}>
            {t("loading")}
          </p>
        </main>
        <TabBar route={route} />
      </div>
    );
  }

  const [name, param] = routeName(route).split("/");
  let screen: React.ReactNode;
  switch (name) {
    case "practice":
      screen = <PracticeScreen />;
      break;
    case "leagues":
      screen = <LeaguesScreen />;
      break;
    case "modules":
      screen = <ModulesScreen />;
      break;
    case "search":
      screen = <SearchScreen />;
      break;
    case "flash":
      screen = <FlashScreen key={param} param={param} onQuit={() => navigate("practice")} />;
      break;
    case "quiz":
      // Clé = hash brut : un changement de query remonte l'écran et relance
      // le démarrage automatique (`?module=a` → `?module=b`).
      screen = <QuizScreen key={route} />;
      break;
    case "profile":
      screen = <ProfileScreen />;
      break;
    default:
      // `path` (défaut) et l'alias historique `home`
      screen = <PathScreen />;
  }

  return (
    <div id="app">
      <TopBar />
      <main className="view" tabIndex={-1}>
        {screen}
      </main>
      <TabBar route={route} />
    </div>
  );
}

export default function App() {
  return (
    <DialogProvider>
      <ToastProvider>
        <Shell />
      </ToastProvider>
    </DialogProvider>
  );
}
