import { useEffect, useMemo, useState } from "react";
import { useT } from "../i18n/useT";

export interface KGNode {
  id: string;
  en: string;
  fr: string;
  ar: string;
  defEn: string;
  defFr: string;
  defAr: string;
  module: string;
  chapter: string;
  exEn: string;
  exFr: string;
  exAr: string;
  etym: string;
  pearl: string;
  mnemo: string;
  ipa: string;
}

export interface KGEdge {
  id: string;
  type: string;
  from: number;
  to: number;
  confiance: string;
  evidence: string;
  is_placeholder?: boolean;
}

export function GraphExplorerScreen() {
  const { t } = useT();
  const [nodes, setNodes] = useState<KGNode[]>([]);
  const [edges, setEdges] = useState<KGEdge[]>([]);
  const [search, setSearch] = useState("");
  const [selectedNode, setSelectedNode] = useState<string | null>(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    let cancelled = false;
    fetch("/data/tree.json")
      .then((res) => res.json())
      .then((data) => {
        if (!cancelled) {
          const nodesMap: KGNode[] = [];
          (data.modules as any[]).forEach((m) => {
            (m.chapters as any[]).forEach((c) => {
              (c.lessons as any[]).forEach((l) => {
                nodesMap.push({ ...l, id: String(l.id), chapter: String(c.id), module: m.id } as KGNode);
              });
            });
          });
          setNodes(nodesMap);
          setEdges([]);
          setLoading(false);
        }
      })
      .catch((e) => { console.error("[GraphExplorerScreen] chargement:", e); setLoading(false); });
    return () => { cancelled = true; };
  }, []);

  const filteredNodes = useMemo(() => {
    if (!search) return nodes;
    return nodes.filter((n) =>
      n.en.toLowerCase().includes(search.toLowerCase()) ||
      n.fr.toLowerCase().includes(search.toLowerCase()) ||
      n.ar.toLowerCase().includes(search.toLowerCase()) ||
      (n.defEn && n.defEn.toLowerCase().includes(search.toLowerCase()))
    );
  }, [nodes, search]);

  return (
    <div className="shell">
      <div className="section-head">
        <h1>Recherche dans le graphe de connaissances</h1>
        <span className="tiny">
          {nodes.length} termes • {edges.length} relations
        </span>
      </div>

      <div className="search-box" style={{ marginBottom: 12 }}>
        <span aria-hidden="true">🔍</span>
        <input
          value={search}
          onChange={(e) => setSearch(e.target.value)}
          placeholder={t("searchPlaceholder")}
          style={{ padding: "8px 12px", borderRadius: 6, border: "1px solid var(--border)", fontSize: 14, width: "100%", boxSizing: "border-box" }}
        />
      </div>

      {loading && <p className="tiny">Chargement du graphe…</p>}

      {!loading && filteredNodes.length === 0 && (
        <p className="tiny">Aucun terme trouvé</p>
      )}

      <div style={{ overflowY: "auto", maxHeight: "600px", border: "1px solid var(--border)", borderRadius: 8, padding: 8 }}>
        {filteredNodes.map((node) => (
          <button
            key={node.id}
            onClick={() => setSelectedNode(node.id)}
            style={{
              width: "100%",
              textAlign: "left",
              padding: "8px 12px",
              border: "1px solid var(--border)",
              borderRadius: 6,
              background: selectedNode === node.id ? "var(--accent)" : "var(--card)",
              color: "var(--foreground)",
              cursor: "pointer",
            }}
          >
            <div style={{ fontWeight: 600 }}>{node.en} <span className="tiny">({node.fr})</span></div>
            <div className="tiny" style={{ color: "var(--muted-foreground)" }}>
              {node.module} • {node.chapter} • {node.defEn?.substring(0, 60)}...
            </div>
          </button>
        ))}
      </div>

      {selectedNode && (
        <div style={{ marginTop: 16, padding: 12, border: "1px solid var(--border)", borderRadius: 8, background: "var(--card)" }}>
          <h2 style={{ margin: 0, fontSize: 18, color: "var(--foreground)" }}>
            {nodes.find((n) => n.id === selectedNode)?.en}
            <span className="tiny"> ({nodes.find((n) => n.id === selectedNode)?.fr})</span>
          </h2>
          <p className="tiny" style={{ color: "var(--muted-foreground)" }}>
            {nodes.find((n) => n.id === selectedNode)?.module} • {nodes.find((n) => n.id === selectedNode)?.chapter}
          </p>
          <div style={{ marginTop: 8, whiteSpace: "pre-wrap" }}>
            <strong style={{ color: "var(--foreground)" }}>Définition:</strong>
            <p style={{ margin: "4px 0", color: "var(--foreground)" }}>
              {nodes.find((n) => n.id === selectedNode)?.defEn}
            </p>
          </div>
        </div>
      )}
    </div>
  );
}
