import { useEffect, useMemo, useState } from "react";
import { useT } from "../i18n/useT";

export interface TreeIndexData {
  modules: {
    id: string;
    name: string;
    chapterCount: number;
    totalLessons: number;
    chapters: {
      id: string;
      name: string;
      termCount: number;
      lessonCount: number;
      lessons: { id: string; kind: string; level: number; termIds: number[] }[];
    }[];
  }[];
}

let treeIndexPromise: Promise<TreeIndexData> | null = null;

export function loadTreeIndex(): Promise<TreeIndexData> {
  if (!treeIndexPromise) {
    treeIndexPromise = (async () => {
      const res = await fetch("/data/tree.json");
      if (!res.ok) throw new Error(`/data/tree.json -> HTTP ${res.status}`);
      return (await res.json()) as TreeIndexData;
    })().catch((e) => {
      console.error("[treeIndex.ts] loadTreeIndex: FETCH FAILED:", e);
      treeIndexPromise = null;
      throw e;
    });
  }
  return treeIndexPromise;
}

export function TreeIndexScreen() {
  const { t } = useT();
  const [tree, setTree] = useState<TreeIndexData | null>(null);
  const [loading, setLoading] = useState(true);
  const [search, setSearch] = useState("");

  useEffect(() => {
    let cancelled = false;
    loadTreeIndex()
      .then((data) => {
        if (!cancelled) setTree(data);
      })
      .catch((e) => {
        console.error("[TreeIndexScreen] loadTreeIndex:", e);
      })
      .finally(() => {
        if (!cancelled) setLoading(false);
      });
    return () => { cancelled = true; };
  }, []);

  const modules = tree?.modules ?? [];
  const totalChapters = modules.reduce((s, m) => s + m.chapterCount, 0);
  const totalLessons = modules.reduce((s, m) => s + m.totalLessons, 0);

  const filteredModules = useMemo(() => {
    if (!search) return modules;
    return modules.filter((m) =>
      m.name.toLowerCase().includes(search.toLowerCase()) ||
      m.chapters.some((c) => c.name.toLowerCase().includes(search.toLowerCase()))
    );
  }, [modules, search]);

  if (loading) {
    return (
      <div className="shell">
        <div className="section-head">
          <h1>{t("modulesTitle")}</h1>
          <span className="tiny">Chargement de l'index...</span>
        </div>
        <div className="module-grid">
          {[...Array(15)].map((_, i) => (
            <div key={i} className="module-card" style={{ textAlign: "center", padding: 24 }}>
              <div className="tiny">Chargement...</div>
            </div>
))})
        </div>
      </div>
    );
  }

  return (
    <div className="shell">
      <div className="section-head">
        <h1>{t("modulesTitle")}</h1>
        <span className="tiny">
          {modules.length} modules - {totalChapters} chapitres - {totalLessons} lecons
        </span>
      </div>

      <div className="search-box" style={{ marginBottom: 12 }}>
        <span aria-hidden="true">T</span>
        <input
          value={search}
          onChange={(e) => setSearch(e.target.value)}
          placeholder={""}
          style={{ padding: "8px 12px", borderRadius: 6, border: "1px solid var(--border)", fontSize: 14, width: "100%", boxSizing: "border-box" }}
        />
      </div>

      {filteredModules.length === 0 && <p className="tiny">Aucun module trouve</p>}

      <div className="module-grid">
        {filteredModules.map((m) => (
          <ModuleNode key={m.id} module={m} open={false} onToggle={() => {}} />
        ))}
      </div>
    </div>
  );
}

function ModuleNode({ module, open, onToggle }: {
  module: { id: string; name: string; chapterCount: number; totalLessons: number; chapters: { id: string; name: string; termCount: number; lessonCount: number; lessons: { id: string; kind: string; level: number; termIds: number[] }[] }[] };
  open: boolean;
  onToggle: () => void;
}) {
  const [search, setSearch] = useState("");

  const filteredChapters = useMemo(() => {
    if (!search) return module.chapters;
    return module.chapters.filter((c) => c.name.toLowerCase().includes(search.toLowerCase()));
  }, [module.chapters, search]);

  return (
    <div>
      <button
        className="module-card"
        onClick={onToggle}
        style={{
          cursor: "pointer",
          border: open ? "1px solid var(--accent)" : "1px solid var(--border)",
          borderRadius: 8,
          padding: "12px",
          width: "100%",
          boxSizing: "border-box",
          background: open ? "var(--accent)" : "transparent",
        }}
      >
        <div style={{ display: "flex", alignItems: "center", gap: 10, width: "100%", justifyContent: "space-between" }}>
          <div style={{ flex: 1 }}>
            <div style={{ fontWeight: 600, fontSize: 16, color: "var(--foreground)" }}>{module.name}</div>
            <div style={{ fontSize: 12, color: "var(--muted-foreground)" }}>
              {module.chapterCount} chapitres - {module.totalLessons} lecons - {module.chapters.reduce((s, c) => s + c.termCount, 0)} termes
            </div>
          </div>
          <span style={{ fontSize: 14, color: "var(--muted-foreground)", transition: "transform 0.2s", transform: open ? "rotate(90deg)" : "rotate(0deg)" }}>▶</span>
        </div>
      </button>

      {open && (
        <div style={{ marginLeft: 16, border: "1px solid var(--border)", borderRadius: 6, overflow: "hidden" }}>
          <input
            value={search}
            onChange={(e) => setSearch(e.target.value)}
            placeholder={""}
            style={{ padding: "6px 10px", borderRadius: 4, border: "1px solid var(--border)", fontSize: 13, width: "100%", boxSizing: "border-box" }}
          />
          {filteredChapters.map((chap) => (
            <ChapterNode key={chap.id} chapter={chap} />
          ))}
        </div>
      )}
    </div>
  );
}

function ChapterNode({ chapter }: { chapter: { id: string; name: string; termCount: number; lessonCount: number; lessons: { id: string; kind: string; level: number; termIds: number[] }[] } }) {
  return (
    <div style={{ paddingLeft: 16 + 24, margin: "6px 0", borderLeft: "2px solid var(--border)" }}>
      <div style={{ display: "flex", alignItems: "center", gap: 8, cursor: "pointer", fontSize: 14, color: "var(--foreground)" }}>
        <span style={{ color: "var(--accent)" }}>✓</span>
        <strong>{chapter.name}</strong>
        <span className="tiny" style={{ color: "var(--muted-foreground)", marginLeft: 8 }}>
          {chapter.termCount} termes - {chapter.lessonCount} lecons
        </span>
      </div>
      <div style={{ marginTop: 6, display: "flex", flexWrap: "wrap", gap: 8, padding: "8px" }}>
        {chapter.lessons.map((l) => (
          <a
            key={l.id}
            href={"#" + l.id}
            style={{ padding: "4px 10px", border: "1px solid var(--border)", borderRadius: 6, fontSize: 12, color: "var(--foreground)", textDecoration: "none", backgroundColor: "var(--card)", whiteSpace: "nowrap" }}
          >
            {l.id} - {l.kind} - {l.kind === "new" ? 8 : 16} Q
          </a>
        ))}
      </div>
    </div>
  );
}
