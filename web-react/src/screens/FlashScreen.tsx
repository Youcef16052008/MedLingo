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
          aria-label={term.fr}
          onClick={(e) => {
            if (e.target instanceof HTMLElement && e.target.closest("#flashSpeak, #flashBookmark")) return;
            flip();
          }}
        >
          {/* ============ RECTO — FrontCardSide : gradient vert, FR ============ */}
          <div className="flash-face flash-front">
            <div className="ff-top">
              <span className="ff-module">🎓 {term.module} • {term.chapter}</span>
              <button
                type="button"
                id="flashBookmark"
                className="ff-bookmark"
                aria-label={t("bookmarkLbl")}
                title={t("bookmarkLbl")}
                onClick={(e) => {
                  e.stopPropagation();
                  update((d) => { d.sm2[String(term.id)] = d.sm2[String(term.id)] || ({ iv: 1, ef: 2.5, due: 0, rep: 0 } as Sched); });
                }}
              >
                🔖
              </button>
            </div>
            <div className="ff-center">
              <span className="ff-nomenclature">🇫🇷 Nomenclature Médicale Française</span>
              <div className="ff-term-fr">{term.fr}</div>
              {term.ar && (
                <div className="ff-term-ar" dir="rtl">🇸🇦 {term.ar}</div>
              )}
              <span className="ff-flip-pill">👆 {t("tapFlip")}</span>
            </div>
          </div>

          {/* ============ VERSO — BackCardSide : blanc, EN + détails ============ */}
          <div className="flash-face flash-back">
            <div className="fb-head">
              <span className="fb-flag" aria-hidden="true">🇬🇧</span>
              <div className="fb-en-col">
                <b className="fb-term-en">{term.en}</b>
                {term.ipa && <span className="fb-ipa">{term.ipa}</span>}
              </div>
              <button
                type="button"
                id="flashSpeak"
                className="fb-speak"
                aria-label={t("speakLbl")}
                title={t("speakLbl")}
                onClick={(e) => {
                  e.stopPropagation();
                  speakTerm(term, lang);
                }}
              >
                🔊
              </button>
            </div>
            <hr className="fb-divider" />
            <div className="fb-cross">
              <b className="fb-cross-ar" dir="rtl">🇸🇦 {term.ar}</b>
              <span className="fb-cross-fr">🇫🇷 {term.fr}</span>
            </div>
            {term.etym && (
              <div className="fb-box fb-etym">
                <b>🏛️ {t("etymLbl")}</b>
                <span>{term.etym}</span>
              </div>
            )}
            {term.pearl && (
              <div className="fb-box fb-pearl">
                <b>🩺 {t("pearlLbl")}</b>
                <span>{term.pearl}</span>
              </div>
            )}
            {term.mnemo && (
              <div className="fb-box fb-mnemo">
                <b>💡 {t("mnemoLbl")}</b>
                <span>{term.mnemo}</span>
              </div>
            )}
            {term.defEn && (
              <div className="fb-def">📖 {term.defEn}</div>
            )}
          </div>
        </div>
      </div>

      <div className="rating-row">
        {[1, 2, 3, 4, 5].map((q) => {
          const sim = sm2Next(q, prev, Date.now());
          const RATE_META = [
            { emoji: "😫", color: "#D32F2F" },
            { emoji: "😕", color: "#F57C00" },
            { emoji: "🤔", color: "#FFA000" },
            { emoji: "😊", color: "#689F38" },
            { emoji: "🤩", color: "#15803D" },
          ][q - 1];
          return (
            <button
              key={q}
              type="button"
              className="rating-btn"
              data-q={q}
              style={{ "--rc": RATE_META.color } as React.CSSProperties}
              disabled={!session.flipped}
              onClick={() => rate(q)}
            >
              <span className="rating-emoji" aria-hidden="true">
                {RATE_META.emoji}
              </span>
              <span className="rating-label">{t(RATE_KEYS[q - 1])}</span>
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
