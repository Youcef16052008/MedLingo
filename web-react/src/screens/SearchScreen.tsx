import { useMemo, useState } from "react";
import { useT } from "../i18n/useT";
import { getTerms } from "../data/load";
import { modLabel } from "../data/modules";
import { norm } from "../lib/utils";
import { TermSheet } from "../components/TermSheet";

export function SearchScreen() {
  const { t, lang } = useT();
  const [query, setQuery] = useState("");
  const [selected, setSelected] = useState<number | null>(null);

  const q = norm(query);
  const hits = useMemo(() => {
    if (q.length < 2) return null;
    return getTerms()
      .filter(
        (x) =>
          norm(x.en).includes(q) ||
          norm(x.fr).includes(q) ||
          norm(x.ar).includes(q) ||
          norm(x.defFr).includes(q) ||
          norm(x.defEn).includes(q) ||
          norm(x.defAr).includes(q) ||
          norm(x.pearl).includes(q) ||
          norm(x.mnemo).includes(q)
      )
      .slice(0, 40);
  }, [q]);

  const term = selected != null ? getTerms().find((x) => x.id === selected) : undefined;

  return (
    <>
      <div className="section-head" style={{ marginTop: 0 }}>
        <h1>{t("searchTitle")}</h1>
      </div>

      <div className="search-box">
        <span aria-hidden="true">🔍</span>
        <input
          type="search"
          autoFocus
          autoComplete="off"
          placeholder={t("searchPlaceholder")}
          value={query}
          onChange={(e) => setQuery(e.target.value)}
        />
      </div>

      <div id="results">
        {hits === null && (
          <div className="empty">
            <span className="empty-ico">📖</span>
            {t("searchEmpty")}
          </div>
        )}
        {hits !== null && hits.length === 0 && (
          <div className="empty">
            <span className="empty-ico">🔍</span>
            {t("noResults")}
          </div>
        )}
        {hits?.map((x) => (
          <button key={x.id} type="button" className="result-item" onClick={() => setSelected(x.id)}>
            <strong>{x.en}</strong>
            <span className="r-fr">{x.fr}</span>
            <span className="tiny">
              {modLabel(x.module, lang)}
              {x.chapter ? ` · ${x.chapter}` : ""}
            </span>
          </button>
        ))}
      </div>

      {term && <TermSheet term={term} onClose={() => setSelected(null)} />}
    </>
  );
}
