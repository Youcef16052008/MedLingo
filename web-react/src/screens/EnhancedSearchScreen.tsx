import { useMemo, useState } from "react";
import { useT } from "../i18n/useT";
import { getTerms, getExercises } from "../data/load";
import { modLabel, MODULES } from "../data/modules";
import { norm } from "../lib/utils";
import { TermSheet } from "../components/TermSheet";

type SearchTab = "terms" | "exercises";

export function EnhancedSearchScreen() {
  const { lang } = useT();
  const [query, setQuery] = useState("");
  const [tab, setTab] = useState<SearchTab>("terms");
  const [selectedTerm, setSelectedTerm] = useState<number | null>(null);
  const [filterModule, setFilterModule] = useState<string>("all");

  const q = norm(query);

  // Term search
  const termHits = useMemo(() => {
    if (q.length < 2) return null;
    return getTerms()
      .filter((x) => {
        if (filterModule !== "all" && x.module !== filterModule) return false;
        return (
          norm(x.en).includes(q) ||
          norm(x.fr).includes(q) ||
          norm(x.ar).includes(q) ||
          norm(x.defFr).includes(q) ||
          norm(x.defEn).includes(q) ||
          norm(x.defAr).includes(q) ||
          norm(x.pearl).includes(q) ||
          norm(x.mnemo).includes(q)
        );
      })
      .slice(0, 50);
  }, [q, filterModule]);

  // Exercise search
  const exerciseHits = useMemo(() => {
    if (q.length < 2) return null;
    return getExercises()
      .filter((x) => {
        if (filterModule !== "all" && x.module !== filterModule) return false;
        return (
          norm(x.qEn).includes(q) ||
          norm(x.qFr).includes(q) ||
          norm(x.qAr).includes(q) ||
          norm(x.expEn).includes(q) ||
          norm(x.expFr).includes(q) ||
          norm(x.expAr).includes(q)
        );
      })
      .slice(0, 50);
  }, [q, filterModule]);

  const term = selectedTerm != null ? getTerms().find((x) => x.id === selectedTerm) : undefined;

  return (
    <>
      <div className="section-head" style={{ marginTop: 0 }}>
        <h1>🔍 Recherche Avancée</h1>
      </div>

      {/* Tab selector */}
      <div className="search-tabs" style={{ display: "flex", gap: 8, marginBottom: 12 }}>
        <button
          className={`btn ${tab === "terms" ? "primary" : ""}`}
          onClick={() => setTab("terms")}
        >
          📚 Termes ({getTerms().length})
        </button>
        <button
          className={`btn ${tab === "exercises" ? "primary" : ""}`}
          onClick={() => setTab("exercises")}
        >
          📝 Exercices ({getExercises().length})
        </button>
      </div>

      {/* Module filter */}
      <div className="search-filter" style={{ marginBottom: 12 }}>
        <select
          value={filterModule}
          onChange={(e) => setFilterModule(e.target.value)}
          style={{ padding: "6px 12px", borderRadius: 6 }}
        >
          <option value="all">Tous les modules</option>
          {MODULES.map((m) => (
            <option key={m.id} value={m.fr}>
              {m.icon} {m.fr}
            </option>
          ))}
        </select>
      </div>

      {/* Search box */}
      <div className="search-box">
        <span aria-hidden="true">🔍</span>
        <input
          type="search"
          autoFocus
          autoComplete="off"
          placeholder={
            tab === "terms"
              ? "Rechercher un terme..."
              : "Rechercher un exercice..."
          }
          value={query}
          onChange={(e) => setQuery(e.target.value)}
        />
      </div>

      {/* Results */}
      <div id="results">
        {tab === "terms" && (
          <>
            {termHits === null && (
              <div className="empty">
                <span className="empty-ico">📖</span>
                Tapez au moins 2 caractères
              </div>
            )}
            {termHits !== null && termHits.length === 0 && (
              <div className="empty">
                <span className="empty-ico">🔍</span>
                Aucun terme trouvé
              </div>
            )}
            {termHits?.map((x) => (
              <button
                key={x.id}
                type="button"
                className="result-item"
                onClick={() => setSelectedTerm(x.id)}
              >
                <strong>{x.en}</strong>
                <span className="r-fr">{x.fr}</span>
                <span className="tiny">
                  {modLabel(x.module, lang)}
                  {x.chapter ? ` · ${x.chapter}` : ""}
                </span>
              </button>
            ))}
          </>
        )}

        {tab === "exercises" && (
          <>
            {exerciseHits === null && (
              <div className="empty">
                <span className="empty-ico">📖</span>
                Tapez au moins 2 caractères
              </div>
            )}
            {exerciseHits !== null && exerciseHits.length === 0 && (
              <div className="empty">
                <span className="empty-ico">🔍</span>
                Aucun exercice trouvé
              </div>
            )}
            {exerciseHits?.map((x, idx) => (
              <div key={idx} className="result-item">
                <strong>{x.qFr || x.qEn || x.qAr}</strong>
                <span className="tiny">
                  {modLabel(x.module, lang)}
                  {x.chapter ? ` · ${x.chapter}` : ""}
                </span>
                <span className="tiny" style={{ display: "block", marginTop: 4, color: "#666" }}>
                  {x.expFr || x.expEn || x.expAr}
                </span>
              </div>
            ))}
          </>
        )}
      </div>

      {term && <TermSheet term={term} onClose={() => setSelectedTerm(null)} />}
    </>
  );
}