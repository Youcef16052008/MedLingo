// P1.2 — extraction de candidats concepts depuis terms.json (Anatomie).
import { readFileSync } from "node:fs";
import { join, dirname } from "node:path";
import { fileURLToPath } from "node:url";

const here = dirname(fileURLToPath(import.meta.url));
const terms = JSON.parse(readFileSync(join(here, "..", "public", "data", "terms.json"), "utf8"));

const LIST = {
  U1_corps: [
    "Head", "Neck", "Chest", "Abdomen", "Pelvis", "Back", "Limb", "Hand", "Foot", "Trunk", "Skull", "Spine",
  ],
  U2_organes: [
    "Heart", "Lung", "Liver", "Stomach", "Kidney", "Brain", "Intestine", "Bladder", "Spleen", "Pancreas",
  ],
  U3_localisation: [
    "Superior", "Inferior", "Anterior", "Posterior", "Medial", "Lateral", "Proximal", "Distal", "Superficial", "Deep",
  ],
};

for (const [unit, words] of Object.entries(LIST)) {
  console.log("### " + unit);
  for (const w of words) {
    const hits = terms.filter((t) => t.en.toLowerCase() === w.toLowerCase() || t.en.toLowerCase() === w.toLowerCase() + "s");
    for (const h of hits.slice(0, 3)) {
      console.log(
        `${h.id} | ${h.en} | ${h.fr} | ${h.ar} | [${h.module}/${h.chapter}] | def: ${String(h.defEn).slice(0, 80)}`,
      );
    }
  }
}
