import { useEffect, useRef, useState } from "react";
import { useT, applyDocumentLang } from "./i18n/useT";
import { useAppState, update, getState, endLesson, addToReviewQueue } from "./domain/store";
import { loadOnce, isLoaded, loadLessonData, getChapters, getLessonEntry, chapterIdOf } from "./data/load";
import { loadStories } from "./learning/stories";
import { useHashRoute } from "./lib/router";
import { rollover, weekStartKey, YOU } from "./domain/leagues";
import { warmQuizPool } from "./domain/lessons";
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
import { StorySessionScreen } from "./screens/StorySessionScreen";
import { ProfileScreen } from "./screens/ProfileScreen";
import { ReviewHubScreen } from "./screens/ReviewHubScreen";
import { ChapterScreen } from "./screens/ChapterScreen";
import { navigate } from "./lib/router";

function Shell() {
  const { t, lang } = useT();
  const { open } = useDialog();
  const s = useAppState();
  const route = useHashRoute();
  const [error, setError] = useState<string | null>(null);
  const [ready, setReady] = useState(false);
  const [lessonReady, setLessonReady] = useState(false);
  const introDone = useRef(false);

  /* ---------- chargement des données extraites des seeds ---------- */
  useEffect(() => {
    let cancelled = false;
    loadOnce()
      .then(() => {
        if (!cancelled) setReady(true);
        // Préchauffe le corpus d'exercices (13 512, ~8 s de génération) en
        // arrière-plan, module par module : le premier quiz est instantané
        // au lieu de geler l'UI au clic. `requestIdleCallback` quand dispo.
        if (!cancelled) {
          if (typeof requestIdleCallback === "function") {
            requestIdleCallback(() => warmQuizPool(), { timeout: 4000 });
          } else {
            setTimeout(() => warmQuizPool(), 1200);
          }
        }
      })
      .catch((e: unknown) => {
        if (!cancelled)
          setError(e instanceof Error ? e.message : "load failed");
      });
    // WS2.1 (BUG-012) : chapters.json + lesson_map.json (1,8 Mo) ne sont plus
    // chargés au démarrage — uniquement à l'entrée dans chapitres/révisions.
    loadStories().catch(() => undefined); // histoires : le mode reste utilisable sans
    return () => {
      cancelled = true;
    };
  }, []);

  /* ---------- WS2.1 : chargement paresseux du parcours par chapitres ---------- */
  const routeName = route.split("?")[0].split("/")[0];
  const needsLessonData =
    routeName === "chapter" ||
    routeName === "review" ||
    (routeName === "quiz" && typeof window !== "undefined" && /[?&](review|lesson)=/.test(window.location.hash));
  useEffect(() => {
    if (!needsLessonData || lessonReady) return;
    let cancelled = false;
    loadLessonData()
      .then(() => {
        if (!cancelled) setLessonReady(true);
      })
      .catch(() => {
        if (!cancelled) setLessonReady(true); // l'écran affichera « introuvable » plutôt qu'un squelette éternel
      });
    return () => {
      cancelled = true;
    };
  }, [needsLessonData, lessonReady]);

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

  // `routeName()` ne garde que le nom (`flash`) ; le paramètre (`anat`) se
  // lit sur le hash brut, pas sur le nom. Sinon `param` vaut toujours
  // `undefined` et la session flashcards se construit sur un module vide.
  const [name, param] = route.split("?")[0].split("/");

  // Chapitres/révisions : porte le temps du chargement paresseux (WS2.1).
  if (needsLessonData && !lessonReady) {
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
    case "story":
      // `#/story/:id` — séance narrative ; quitter ramène au parcours.
      screen = <StorySessionScreen key={param} storyId={param} onQuit={() => navigate("path")} />;
      break;
    case "chapter": {
      // `#/chapter/:chapterId` (LOT2/WS2.1) — tableau de bord du chapitre :
      // leçons nouvelles + révisions, progression depuis le store.
      const chapter = getChapters().find((c) => c.id === param);
      if (!chapter) {
        screen = (
          <div className="empty" style={{ padding: 32 }}>
            <p>{t("loading")}</p>
          </div>
        );
        break;
      }
      const activeLesson =
        Object.values(s.lessonProgress).find((p) => p.completedAt === 0)?.lessonId ?? "";
      screen = (
        <ChapterScreen
          key={chapter.id}
          moduleId={chapter.moduleId}
          chapterId={chapter.id}
          currentLessonId={activeLesson}
          onLessonStart={(lessonId) => {
            const entry = getLessonEntry(lessonId);
            if (!entry) return;
            if (entry.kind === "new") {
              navigate(`quiz?module=${chapter.moduleId}&level=${entry.level}&lesson=${lessonId}`);
            } else {
              navigate(`quiz?review=${lessonId}`);
            }
          }}
          onLessonEnd={(lessonId, score, missedExIds) => {
            // Sécurité : si l'écran invoque la clôture lui-même, même chemin
            // que QuizScreen (progression + file de révision).
            const entry = getLessonEntry(lessonId);
            if (!entry) return;
            endLesson(lessonId, score, missedExIds);
            if (missedExIds.length > 0) {
              addToReviewQueue({
                lessonId,
                exIds: missedExIds,
                chapterId: chapterIdOf(entry),
                moduleId: chapter.moduleId,
              });
            }
          }}
          onBack={() => navigate("path")}
        />
      );
      break;
    }
    case "review":
      // `#/review` (LOT2/WS2.1) — hub des révisions en attente.
      screen = <ReviewHubScreen />;
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
