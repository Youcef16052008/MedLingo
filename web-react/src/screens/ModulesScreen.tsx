import { useT } from "../i18n/useT";
import { getTerms } from "../data/load";
import { MODULES } from "../data/modules";
import { ModuleCard } from "../components/ModuleCard";

export function ModulesScreen() {
  const { t } = useT();

  return (
    <>
      <div className="section-head" style={{ marginTop: 0 }}>
        <h1>{t("modulesTitle")}</h1>
        <span className="tiny">
          {getTerms().length} {t("termsLbl")}
        </span>
      </div>

      <a className="search-box" href="#/search" style={{ textDecoration: "none", color: "inherit" }}>
        <span aria-hidden="true">🔍</span>
        <span className="muted" style={{ flex: 1 }}>
          {t("searchPlaceholder")}
        </span>
      </a>

      <div className="module-grid">
        {MODULES.map((m) => (
          <ModuleCard key={m.id} mod={m} />
        ))}
      </div>
    </>
  );
}
