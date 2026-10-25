import { masteredModules } from "../domain/trophies";
import { modLabel } from "../data/modules";
import { useT } from "../i18n/useT";

/**
 * WS4 levier 7 — certificat de maîtrise (offline, partageable). La motivation
 * extrinsèque honnête : la preuve de maîtrise est une donnée DURABLE que
 * l'étudiant affiche à sa promo (groupes, CV) — boucle sociale organique sans
 * réseau social à modérer. Génération locale : capture d'écran (aucun serveur).
 */
export function MasteryCertificate({ best }: { best: Record<string, number> }) {
  const { t, lang } = useT();
  const mastered = masteredModules({ moduleBest: best } as never);
  if (mastered.length === 0) return null;
  return (
    <div
      className="card"
      style={{
        marginTop: 14,
        textAlign: "center",
        border: "2px solid #0E8A5F",
        background: "linear-gradient(160deg,#F4F8F6,#E7F5F0)",
      }}
    >
      <div style={{ fontSize: 28 }}>🩺</div>
      <b style={{ display: "block", margin: "2px 0 6px" }}>{t("certTitle")}</b>
      <p className="tiny" style={{ margin: 0 }}>
        {t("certBody")}
      </p>
      <p style={{ margin: "8px 0 4px", fontWeight: 700, color: "#0B5A3C" }}>
        {mastered.map((id) => modLabel(id, lang)).join(" · ")}
      </p>
      <p className="tiny muted" style={{ margin: 0 }}>
        MedLingo — {new Date().toLocaleDateString()}
      </p>
    </div>
  );
}
