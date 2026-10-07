import { useT } from "../i18n/useT";
import { useAppState } from "../domain/store";
import { termsOfModule } from "../data/load";
import type { Module } from "../data/modules";
import { moduleProgress, type Sm2Map } from "../domain/progress";

export { moduleProgress };
export type { Progress } from "../domain/progress";

export function ModuleCard({ mod }: { mod: Module }) {
  const { t, lang } = useT();
  const { sm2 } = useAppState() as { sm2: Sm2Map };
  const p = moduleProgress(mod, sm2);
  const chapters = new Set(termsOfModule(mod.id).map((x) => x.chapter)).size;

  return (
    <a
      className="module-card"
      style={{ "--mod-color": mod.color } as React.CSSProperties}
      href={`#/flash/${mod.id}`}
    >
      <span className="module-ico" aria-hidden="true">
        {mod.icon}
      </span>
      <h3>{mod[lang]}</h3>
      <span className="tiny">
        {p.total} {t("termsLbl")} · {chapters} {t("chaptersLbl")}
      </span>
      <div className="progress-track">
        <div className="progress-fill" style={{ width: `${p.pct}%`, background: mod.color }} />
      </div>
      <span className="mod-pct">{p.pct}%</span>
    </a>
  );
}
