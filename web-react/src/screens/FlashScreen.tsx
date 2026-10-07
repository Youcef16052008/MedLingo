import { useCallback, useEffect, useRef, useState } from "react";
import { useT } from "../i18n/useT";
import { useAppState, update } from "../domain/store";
import { getTerms, termById, termsOfModule } from "../data/load";
import { dueTerms, type Sm2Map } from "../domain/progress";
import { sm2Next, type Sched } from "../domain/sm2";
import { addXp, touchStreak } from "../domain/gamification";
import { earnChest } from "../domain/chests";
import { claimTrophies, type TrophyId } from "../domain/trophies";
import { shuffle, fmtIv } from "../lib/utils";
import { speakTerm, stopSpeak } from "../lib/speech";
import { SessionTop } from "../components/SessionTop";
import { RewardPopup, type RewardData } from "../components/RewardPopup";
import { useDialog } from "../components/DialogProvider";
import type { TKey } from "../i18n";

export const FLASH_CAP = 20;
const RATE_KEYS: TKey[] = ["rate1", "rate2", "rate3", "rate4", "rate5"];

interface Session {
  queue: number[];
  i: number;
  flipped: boolean;
  ok: number;
  xp: number;
  reviewed: number;
}

function buildSession(param: string, sm2: Sm2Map, now: number): Session | null {
  const weak = param === "weak";
  const base = param === "all" || weak ? getTerms() : termsOfModule(param);
  if (!base.length) return null;

  // « Termes faibles » : uniquement les cartes en retard (aucune → état vide).
  if (weak) {
    const due = dueTerms(base, sm2, now);
    if (!due.length) return null;
    return {
      queue: due.map((x) => x.id).slice(0, FLASH_CAP),
      i: 0,
      flipped: false,
      ok: 0,
      xp: 0,
      reviewed: 0,
    };
  }

  const due = dueTerms(base, sm2, now);
  const fresh = shuffle(base.filter((x) => !sm2[String(x.id)]));
  const queue = [...due, ...fresh].map((x) => x.id).slice(0, FLASH_CAP);
  return { queue, i: 0, flipped: false, ok: 0, xp: 0, reviewed: 0 };
}

interface FlashProps {
  param: string;
  onQuit: () => void;
}

export function FlashScreen({ param, onQuit }: FlashProps) {
  const { t, lang } = useT();
  const { open } = useDialog();
  const { sm2 } = useAppState() as { sm2: Sm2Map };
  const [session, setSession] = useState<Session | null>(() =>
    buildSession(param, sm2, Date.now())
  );
  const [reward, setReward] = useState<RewardData | null>(null);
  const goalHitRef = useRef(false);
  const finishedRef = useRef(false);

  const exhausted = !!session && session.i >= session.queue.length;
  const term = session && !exhausted ? termById(session.queue[session.i]) : undefined;

  const flip = useCallback(() => {
    setSession((s) => (s ? { ...s, flipped: !s.flipped } : s));
  }, []);

  const rate = useCallback(
    (q: number) => {
      if (!session || session.i >= session.queue.length) return;
      const id = session.queue[session.i];
      const now = Date.now();

      update((draft) => {
        draft.sm2[String(id)] = sm2Next(q, draft.sm2[String(id)] as Sched | undefined, now);
        draft.flashReviewed += 1;
        touchStreak(draft, now);
        const out = addXp(draft, 5, now);
        if (out.goalHit) goalHitRef.current = true;
      });

      setSession({
        ...session,
        i: session.i + 1,
        flipped: false,
        ok: session.ok + (q >= 3 ? 1 : 0),
        xp: session.xp + 5,
        reviewed: session.reviewed + 1,
      });
    },
    [session]
  );

  // Fin de session : caisse (≥10 cartes), trophées, pop-up de récompense.
  useEffect(() => {
    if (!session || !exhausted || session.reviewed === 0 || finishedRef.current) return;
    finishedRef.current = true;
    const now = Date.now();
    let trophies: TrophyId[] = [];
    update((draft) => {
      earnChest(draft, "flash", session.reviewed);
      trophies = claimTrophies(draft, now);
    });
    // Pas de gemmes par carte (économie Android) : elles viennent de la caisse.
    setReward({ xp: session.xp, gems: 0, goalHit: goalHitRef.current, trophies });
  }, [exhausted, session]);

  // B17 : la synthèse vocale s'arrête au démontage de l'écran.
  useEffect(() => () => stopSpeak(), []);

  // Raccourcis clavier : Espace/Entrée = retourner, 1-5 = noter (comme l'app).
  useEffect(() => {
    if (!term) return;
    const onKey = (e: KeyboardEvent) => {
      const el = document.activeElement;
      const tag = el instanceof HTMLElement ? el.tagName : "";
      if (tag === "INPUT" || tag === "TEXTAREA") return;
      // B18 : raccourcis inertes pendant qu'une modale est affichée.
      if (document.querySelector(".overlay .dialog")) return;
      if (e.code === "Space" || e.key === "Enter") {
        if (e.target instanceof HTMLElement && e.target.closest("button")) return;
        e.preventDefault();
        flip();
        return;
      }
      if (session?.flipped && /^[1-5]$/.test(e.key)) rate(Number(e.key));
    };
    window.addEventListener("keydown", onKey);
    return () => window.removeEventListener("keydown", onKey);
  }, [term, flip, rate, session?.flipped]);

  const askQuit = useCallback(async () => {
    const r = await open({
      icon: "👋",
      title: t("quitTitle"),
      text: t("quitText"),
      actions: [
        { id: "stay", label: t("stayLbl") },
        { id: "quit", label: t("quitLbl"), cls: "danger" },
      ],
    });
    if (r === "quit") onQuit();
  }, [open, t, onQuit]);

  /* ---------- module vide ---------- */
  if (!session) {
    return (
      <div className="empty card" style={{ marginTop: 24 }}>
        <span className="empty-ico">✅</span>
        <h2>{t("emptyDue")}</h2>
        <p className="muted">{t("emptyDueSub")}</p>
        <a className="btn" href="#/modules" style={{ marginTop: 12 }}>
          {t("backCourses")}
        </a>
      </div>
    );
  }

  /* ---------- rien à réviser (file vide au départ) ---------- */
  if (exhausted && session.reviewed === 0) {
    return (
      <div className="empty card" style={{ marginTop: 24 }}>
        <span className="empty-ico">✅</span>
        <h2>{t("emptyDue")}</h2>
        <p className="muted">{t("emptyDueSub")}</p>
        <a className="btn" href="#/modules" style={{ marginTop: 12 }}>
          {t("backCourses")}
        </a>
      </div>
    );
  }

  /* ---------- session terminée ---------- */
  if (exhausted || !term) {
    const total = session.reviewed;
    const successPct = total ? Math.round((session.ok / total) * 100) : 0;
    return (
      <>
        {reward && (
          <RewardPopup data={reward} onContinue={() => setReward(null)} />
        )}
        <div className="summary card" style={{ marginTop: 24 }}>
          <div className="dlg-ico" style={{ fontSize: "2.6rem" }}>
            🎉
          </div>
          <h1>{t("doneTitle")}</h1>
          <p className="muted" style={{ margin: "6px 0 0" }}>
            {t("doneSub")}
          </p>
          <div className="summary-stats">
            <div className="stat-tile">
              <b>{successPct}%</b>
              <span>{t("successLbl")}</span>
            </div>
            <div className="stat-tile">
              <b>+{session.xp}</b>
              <span>⭐ {t("statXp")}</span>
            </div>
            <div className="stat-tile">
              <b>{total}</b>
              <span>🃏 {t("masteredLbl")}</span>
            </div>
          </div>
          <a className="btn block" href="#/practice">
            {t("backHome")}
          </a>
          <a className="btn ghost block" style={{ marginTop: 10 }} href="#/modules">
            {t("backCourses")}
          </a>
        </div>
      </>
    );
  }

  /* ---------- carte en cours ---------- */
  const def = lang === "ar" ? term.defAr : lang === "en" ? term.defEn : term.defFr;
  const ex = lang === "ar" ? term.exAr : lang === "en" ? term.exEn : term.exFr || term.exEn;
  const prev = sm2[String(term.id)] as Sched | undefined;

  return (
    <>
      <SessionTop index={session.i} total={session.queue.length} onExit={askQuit} />

      <div className="flash-stage">
        <div
          key={term.id}
          className={`flashcard ${session.flipped ? "flipped" : ""}`}
          role="button"
          tabIndex={0}
          aria-label={term.en}
          onClick={(e) => {
            if (e.target instanceof HTMLElement && e.target.closest("#flashSpeak")) return;
            flip();
          }}
        >
          <div className="flash-face flash-front">
            {term.chapter && <span className="chapter-tag">{term.chapter}</span>}
            <div className="term-en">{term.en}</div>
            {term.ipa && <div className="ipa">{term.ipa}</div>}
            <button
              type="button"
              id="flashSpeak"
              className="speak-btn"
              aria-label={t("speakLbl")}
              title={t("speakLbl")}
              onClick={(e) => {
                e.stopPropagation();
                speakTerm(term, lang);
              }}
            >
              🔊
            </button>
            <span className="flip-hint">{t("tapFlip")} · ␣</span>
          </div>
          <div className="flash-face flash-back">
            <span className="back-lang">FR · AR</span>
            <div className="back-term">{term.fr}</div>
            <div className="back-ar" dir="rtl">
              {term.ar}
            </div>
            <div className="back-def">{def}</div>
            {ex && (
              <div className="back-extra">
                {t("exLbl")} : {ex}
              </div>
            )}
          </div>
        </div>
      </div>

      <div className="rating-row">
        {[1, 2, 3, 4, 5].map((q) => {
          const sim = sm2Next(q, prev, Date.now());
          return (
            <button
              key={q}
              type="button"
              className="rating-btn"
              data-q={q}
              disabled={!session.flipped}
              onClick={() => rate(q)}
            >
              {t(RATE_KEYS[q - 1])}
              <small>{fmtIv(sim.iv, lang)}</small>
            </button>
          );
        })}
      </div>
      <p className="tiny" style={{ textAlign: "center", marginTop: 10 }}>
        {session.flipped ? "" : t("rateWait")}
      </p>
    </>
  );
}
