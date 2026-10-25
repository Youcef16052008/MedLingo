# MedLingo — À faire (le reste)

**Dernière mise à jour : 25/10/2026** · Stratégie complète : [`RAPPORT_COMPETITIF_ET_PLAN_2026.md`](RAPPORT_COMPETITIF_ET_PLAN_2026.md) (phases V0–V4, KPIs K1–K4).

---

## ✅ Fait (25/10/2026)

- [x] Rapport d'analyse concurrentielle + plan stratégique « mieux que Duolingo sur le créneau » — commit `852671d`
- [x] **V0.1 — Dashboard de relecture** du corpus (commit `6fbf815`) : `npm run review`, verdicts langue/médecine persistés dans `data/review-verdicts.v1.json`, publication automatique ⟺ double approbation, harnais 5086/5086 vert, cycle testé bout-en-bout.

---

## 🔴 HUMAIN — bloque la mise en store

### 1. Campagne de relecture des 814 drafts (cible V0.1 : ≥ 150 cette semaine)

- [ ] `cd web-react && npm run review` → http://127.0.0.1:8799
- [ ] Renseigner ses initiales (en-tête), puis relire **chapitre par chapitre** :
      verdicts **Langue** et **Médecine** (Approuver / À corriger / Rejeter, note obligatoire si pas approuvé).
      Liste de contrôle intégrée en haut de page (protocole `RELECTURE_HISTOIRES.md`).
- [ ] Après chaque session — publier ce qui est doublement approuvé :
      ```bash
      cd web-react
      python scripts/build_stories_corpus.py && npm run check:stories && npx vitest run
      ```
- [ ] Committer `web-react/data/review-verdicts.v1.json` + les deux `stories.v1.json` régénérés.
- [ ] Cible finale : 814/814. Rythme de référence : ~50 chapitres (150 histoires)/semaine en solo ; double avec 2 relecteurs.
- [ ] Priorisation suggérée : Anglais Médical → Sémiologie → Terminologie (forte demande examen), puis le reste.

### 2. Recruter 2–3 relecteurs (faculté)

- [ ] 1 profil linguistique (anglais + aides FR/AR) + 1–2 profils médicaux.
- [ ] Pour chacun : clone du repo, `npm install` dans `web-react/`, `npm run review` — rien d'autre à installer.
- [ ] Leur donner `docs/RELECTURE_HISTOIRES.md` + ce fichier (suivi des chapitres traités).

### 3. Pilote étudiant (V0.3 — protocole déjà prêt)

- [ ] Confirmer ≥ 5 étudiants (`docs/PROTOCOLE_PILOTE_ETUDIANTS.md`).
- [ ] Caler les dates ; exécuter ; collecter : compréhension sur audio neuf à J+7, réutilisation des expressions, aides nécessaires.
- [ ] Écrire le rapport de mesures → baseline K1 (maîtrise ≥ 70 % à J+7) et K3 (rétention D30 ≥ 25 %).

---

## 🟡 DEV (agent — sur demande, dans l'ordre)

### V0.4 — Release interne Play Store (prochain jalon dev)
- [ ] Keystore de production : `my-upload-key.jks` + variables `KEYSTORE_PATH` / `STORE_PASSWORD` / `KEY_PASSWORD` (déjà prévues dans `app/build.gradle.kts`).
- [ ] Procédure de build/release documentée (bus factor R6).
- [ ] Crash reporting privacy-first + `versionCode` incrémental.
- [ ] Fiche store FR/AR/EN + CGU + **disclaimer médical** (risque R2).
- [ ] ADR comptes/sync : local-first par défaut, cloud opt-in (dette D5).

### V0.2 — 44 chapitres trop minces
- [ ] Enrichir les termes des 44 chapitres reportés (1–2 termes, liste dans la sortie du générateur), puis régénérer — même gate relecture que le reste.

### V1 — Parité engagement (après V0)
- [ ] Porter les **ligues** sur Android (`LeaguesScreen` + états `domain/gamification`).
- [ ] Notifications push + rappel quotidien (FCM ; function `notifications` à câbler ; ≤ 1/jour, opt-in).
- [ ] Objectif quotidien paramétrable + série résiliente (gel hebdo honnête, sans paywall).
- [ ] Recherche Android (port de `SearchScreen`).
- [ ] Comptes opt-in + sync (Supabase Auth) si l'ADR V0.4 dit oui.
- [ ] Instrumentation produit minimale privacy-first (funnels D1/D7/D30).

### V2+ — voir le plan stratégique §9 (moteur de maîtrise, mode examen EMD, dictées, trilingue intégral…)

---

## Ordre recommandé

1. **Relecture en continu** (humain) — chaque lot approuvé est immédiatement publiée et utilisable par les étudiants du pilote.
2. **V0.4 en parallèle** (dev) — sans dépendre de la relecture.
3. **Pilote dès 150+ histoires publiées** — ne pas attendre les 814.
4. V1 ensuite, sur données du pilote.
