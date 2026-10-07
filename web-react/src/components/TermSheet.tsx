import { useEffect, useRef } from "react";
import type { Lang, Term } from "../types";
import { useT } from "../i18n/useT";
import { speakTerm, stopSpeak } from "../lib/speech";
import { modLabel } from "../data/modules";

interface Props {
  term: Term;
  onClose: () => void;
}

/** Fiche de terme en bottom sheet (équivalent de `openTermSheet`). */
export function TermSheet({ term, onClose }: Props) {
  const { t, lang } = useT();
  const sheetRef = useRef<HTMLDivElement>(null);
  const onCloseRef = useRef(onClose);
  onCloseRef.current = onClose;

  // F19 : focus initial, Échap ferme, piège à Tab, retour au déclencheur.
  // B17 : la voix s'arrête aussi au démontage.
  useEffect(() => {
    const prev = document.activeElement instanceof HTMLElement ? document.activeElement : null;
    sheetRef.current?.querySelector<HTMLElement>("button")?.focus();
    const onKey = (e: KeyboardEvent) => {
      if (e.key === "Escape") {
        e.preventDefault();
        onCloseRef.current();
        return;
      }
      if (e.key !== "Tab") return;
      const nodes = sheetRef.current?.querySelectorAll<HTMLElement>("button, a[href]");
      if (!nodes || nodes.length === 0) return;
      const firstEl = nodes[0];
      const lastEl = nodes[nodes.length - 1];
      const active = document.activeElement;
      const inSheet = active instanceof Node && sheetRef.current?.contains(active);
      if (!inSheet) {
        e.preventDefault();
        (e.shiftKey ? lastEl : firstEl).focus();
      } else if (e.shiftKey && active === firstEl) {
        e.preventDefault();
        lastEl.focus();
      } else if (!e.shiftKey && active === lastEl) {
        e.preventDefault();
        firstEl.focus();
      }
    };
    document.addEventListener("keydown", onKey);
    return () => {
      document.removeEventListener("keydown", onKey);
      stopSpeak();
      if (prev && document.contains(prev)) prev.focus();
    };
  }, []);

  const def = lang === "ar" ? term.defAr : lang === "en" ? term.defEn : term.defFr;
  const ex = lang === "ar" ? term.exAr : lang === "en" ? term.exEn : term.exFr || term.exEn;

  const rows: [string, string][] = [
    [t("defLbl"), def],
    [t("exLbl"), ex],
    [t("etymLbl"), term.etym],
    [t("pearlLbl"), term.pearl],
    [t("mnemoLbl"), term.mnemo],
  ].filter(([, v]) => v) as [string, string][];

  return (
    <div className="sheet-backdrop" onClick={(e) => e.target === e.currentTarget && onClose()}>
      <div
        className="sheet"
        role="dialog"
        aria-modal="true"
        aria-label={term.en}
        ref={sheetRef}
      >
        <div className="sheet-grip" />
        <div style={{ display: "flex", alignItems: "center", gap: 12 }}>
          <div style={{ flex: 1 }}>
            <h2>
              {term.en}{" "}
              {term.ipa && (
                <span className="tiny" style={{ fontFamily: "var(--font-ui)" }}>
                  {term.ipa}
                </span>
              )}
            </h2>
            <p className="muted" style={{ margin: "4px 0 0" }}>
              {term.fr} · <span dir="rtl">{term.ar}</span>
            </p>
            <p className="tiny" style={{ margin: "2px 0 0" }}>
              {modLabel(term.module, lang)}
              {term.chapter ? ` · ${term.chapter}` : ""}
            </p>
          </div>
          <button
            type="button"
            className="speak-btn"
            title={t("speakLbl")}
            aria-label={t("speakLbl")}
            onClick={() => speakTerm(term, lang as Lang)}
          >
            🔊
          </button>
        </div>
        <dl>
          {rows.map(([k, v]) => (
            <div key={k}>
              <dt>{k}</dt>
              <dd>{v}</dd>
            </div>
          ))}
        </dl>
        <button type="button" className="btn ghost block" style={{ marginTop: 18 }} onClick={onClose}>
          {t("closeLbl")}
        </button>
      </div>
    </div>
  );
}
