import { useMemo } from "react";
import { useT } from "../i18n/useT";
import { useAppState } from "../domain/store";
import { termsOfModule } from "../data/load";
import type { Module } from "../data/modules";
import { moduleProgress, type Sm2Map } from "../domain/progress";

export { moduleProgress };
export type { Progress } from "../domain/progress";

/* Miroir de FigmaModuleDetailedCard (ModulesScreen.kt) : carte pleine largeur,
   icône 50px arrondie 16 dans la couleur du module à 12 %, titre 16 bold,
   sous-titre "TitleEn • N chapitres", badge "N termes" coloré, chips de
   chapitres, barre de progression et pourcentage. */
export function ModuleCard({ mod }: { mod: Module }) {
  const { t, lang } = useT();
  const { sm2 } = useAppState() as { sm2: Sm2Map };
  const p = moduleProgress(mod, sm2);

  const terms = termsOfModule(mod.id);
  const chapters = useMemo(
    () => [...new Set(terms.map((x) => x.chapter))],
    [terms]
  );

  return (
    <a
      className="module-card"
      style={{ "--mod-color": mod.color } as React.CSSProperties}
      href={`#/flash/${mod.id}`}
    >
      <div className="module-head">
        <div className="module-titlecol">
          <span className="module-ico" aria-hidden="true">
            {mod.icon}
          </span>
          <div>
            <h3>{mod[lang]}</h3>
            <span className="module-sub">
              {mod.en} • {chapters.length} {t("chaptersLbl")}
            </span>
          </div>
        </div>
        <span className="module-terms">
          {terms.length} {t("termsLbl")}
        </span>
      </div>

      {chapters.length > 0 && (
        <div className="module-chapters">
          {chapters.map((c) => (
            <span key={c} className="module-chip">
              {c}
            </span>
          ))}
        </div>
      )}

      <div className="progress-track">
        <div
          className="progress-fill"
          style={{ width: `${p.pct}%`, background: mod.color }}
        />
      </div>
      <span className="mod-pct">{p.pct}%</span>
    </a>
  );
}
