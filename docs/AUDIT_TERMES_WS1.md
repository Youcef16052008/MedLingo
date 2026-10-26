# Audit termes — WS1.4 / WS1.6 (généré le 25/10/2026)

Produit par script (source : `web-react/public/data/terms.json`, 4 504 termes). Trois listes à traiter **humainement** — aucune modification automatique du contenu médical.

## 1. Chapitres minces — WS1.4 (bloque 44×3 = 132 histoires)

**44 chapitres** ont moins de 3 termes (seuil du générateur d'histoires). Enrichir chaque chapitre à ≥ 3 termes validés, puis `python scripts/build_stories_corpus.py`.

| Module | Chapitre | # | Termes actuels |
|---|---|---|---|
| Biochimie | Protéines et Acides Aminés | 2 | Peptide Bond, Michaelis-Menten Kinetics |
| Biochimie | Acides Nucléiques et Génome | 2 | Nucleotide, DNA Replication |
| Cytologie | Cytosquelette & Motilité cellulaire | 2 | Microtubules and Centrosome, Actin Microfilaments |
| Embryologie | Placenta & Tératologie | 2 | Placental Barrier and Hemochorial Villus, Teratogenesis and Critical Embryonic Period |
| Génétique | Réplication & Transcription | 2 | Semiconservative DNA Replication, Alternative Splicing and Spliceosome |
| Génétique | Mutations & Hérédité Mendélienne | 2 | Frameshift Mutation, Autosomal Dominant vs Recessive Inheritance |
| Histologie | Tissus Épithéliaux | 2 | Tight Junction, Basement Membrane |
| Histologie | Tissus Conjonctifs et Cartilage/Os | 2 | Fibroblast, Osteoblast and Osteoclast |
| Informatique Médicale | Biostatistiques Fondamentales | 2 | Statistical Significance and P-Value, Confidence Interval |
| Informatique Médicale | Épidémiologie & Risque Clinique | 2 | Sensitivity and Specificity, Relative Risk |
| Terminologie Médicale | Induction | 2 | Inductive interaction, Competence |
| Anglais Médical | Communication clinique | 1 | Anamnesis / Patient History |
| Anglais Médical | Examen clinique | 1 | Auscultation |
| Biochimie | Bioénergétique et Vitamines | 1 | Oxidative Phosphorylation |
| Biochimie | Enzymes et Énergie | 1 | Mitochondria |
| Biochimie | Glucides et Énergie | 1 | Adenosine Triphosphate |
| Biochimie | Protéines | 1 | Hemoglobin |
| Biophysique | Optique Médicale et Vision | 1 | Emmetropia and Refraction |
| Biophysique | Rayonnements et Radiobiologie | 1 | Ionizing Radiation and Dosimetry |
| Biophysique | Électrophysiologie et Phénomènes de Membrane | 1 | Nernst Equation |
| Biophysique | Solutions Biologiques et Échanges | 1 | Osmotic Pressure and Starling Forces |
| Cytologie | Signalisation cellulaire & Apoptose | 1 | Apoptosis |
| Cytologie | Noyau & Cycle Cellulaire | 1 | Nuclear Pore Complex |
| Embryologie | Gamétogenèse & Fécondation | 1 | Capacitation and Acrosome Reaction |
| Embryologie | Segmentation & Blastocyste | 1 | Morula and Compaction |
| Embryologie | Neurulation & Crêtes Neurales | 1 | Neurulation and Neural Crest Cells |
| Embryologie | Mésoderme & Somites | 1 | Somites and Paraxial Mesoderm |
| Génétique | Anomalies Chromosomiques | 1 | Aneuploidy and Karyotype |
| Histologie | Tissu Sanguin et Hématopoïèse | 1 | Erythrocyte and Reticulocyte |
| Histologie | Tissus épithéliaux | 1 | Epithelium |
| Histologie | Tissu nerveux | 1 | Schwann Cell |
| Informatique Médicale | Dossier Patient Informatisé & SIH | 1 | Electronic Health Record |
| Informatique Médicale | Intelligence Artificielle en Santé | 1 | Artificial Intelligence in Medical Imaging |
| Informatique Médicale | Sécurité des Données de Santé | 1 | Health Data Privacy and HIPAA / RGPD |
| Physiologie | Cardiovasculaire | 1 | Hypertension |
| Physiologie | Physiologie cellulaire | 1 | Homeostasis |
| Physiologie | Physiologie nerveuse | 1 | Action Potential |
| Physiologie | Respiratoire | 1 | Alveoli |
| Physiologie | Endocrinologie et Homéostasie | 1 | Insulin and Glucagon |
| Terminologie Médicale | Suffixes chirurgicaux | 1 | Nephrectomy |
| Terminologie Médicale | Suffixes Chirurgicaux | 1 | Laparotomy vs Laparoscopy |
| Terminologie Médicale | Sémiologie Cardio-Vasculaire | 1 | Acyanotic vs Cyanotic Heart Defect |
| Terminologie Médicale | Sémiologie Respiratoire & Digestive | 1 | Hemoptysis vs Hematemesis |
| Terminologie Médicale | Grands Syndromes Cliniques | 1 | Polyuria, Polydipsia, and Polyphagia |

## 2. Fiches termFr == termEn — WS1.6 (519 fiches)

La traduction française est identique à l'anglais : à traduire ou à valider (certaines sont légitimes — acronymes comme « DVT »). Répartition par module :

- **Terminologie Médicale** : 68
- **Anatomie** : 64
- **Biochimie** : 62
- **Microbiologie** : 53
- **Embryologie** : 33
- **Anglais Médical** : 32
- **Génétique** : 31
- **Physiologie** : 30
- **Cytologie** : 29
- **Pharmacologie** : 28
- **Sémiologie Médicale** : 24
- **Histologie** : 22
- **Biophysique** : 20
- **Informatique Médicale** : 13
- **Anatomie Pathologique** : 10

Exemples (10 premiers) :

```
Plasma  →  fr: Plasma  (module Anatomie)
Ligament  →  fr: Ligament  (module Anatomie)
Tendon  →  fr: Tendon  (module Anatomie)
Flexion  →  fr: Flexion  (module Anatomie)
Extension  →  fr: Extension  (module Anatomie)
Abduction  →  fr: Abduction  (module Anatomie)
Adduction  →  fr: Adduction  (module Anatomie)
Rotation  →  fr: Rotation  (module Anatomie)
Circumduction  →  fr: Circumduction  (module Anatomie)
Suture  →  fr: Suture  (module Anatomie)
```

> Liste exhaustive : `python -c "import json; [print(t['id'], t['en']) for t in json.load(open('public/data/terms.json', encoding='utf-8')) if (t.get('fr') or '').strip().lower()==(t.get('en') or '').strip().lower()]"`

## 3. Doublons de traduction intra-module — WS1.6 (0 groupes)

Deux termes anglais distincts affichés comme un seul concept français :

