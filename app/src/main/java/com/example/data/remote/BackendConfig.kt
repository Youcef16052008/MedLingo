package com.example.data.remote

/**
 * Configuration du backend Supabase — **désactivée par défaut**.
 *
 * ## Activer le backend (optionnel)
 *
 * L'app fonctionne entièrement hors-ligne. Pour activer la synchronisation
 * multi-appareils et les ligues humaines, créer un fichier `secrets.properties`
 * à la racine du projet (voir `GUIDE_BACKEND.md` §3) :
 *
 * ```
 * SUPABASE_URL=https://xxxxx.supabase.co
 * SUPABASE_ANON_KEY=eyJxxxxx...
 * ```
 *
 * Ces valeurs sont injectées par **secrets-gradle-plugin** (déjà configuré) et
 * accessibles via `BuildConfig`. Elles **n'apparaissent jamais** dans le code
 * source — le plugin les supprime du contrôle de version via `.gitignore`.
 *
 * ## Sécurité
 *
 * La clé utilisée côté app est la clé **`anon`** publique de Supabase, dont les
 * permissions sont restreintes par les politiques RLS de
 * `backend/supabase/migrations/002_security_rls.sql`. Elle peut être lue par
 * n'importe qui ayant l'APK — c'est sa conception (comme une clé d'API
 * Firebase). La clé `service_role`, elle, reste **uniquement** dans les Edge
 * Functions et ne doit jamais être embarquée dans l'app.
 *
 * @see GUIDE_BACKEND.md pour le pas-à-pas complet
 */
data class BackendConfig(
    val baseUrl: String,
    val apiKey: String
) {

    /** Backend configuré et utilisable (URL et clé non vides). */
    val isEnabled: Boolean
        get() = baseUrl.isNotBlank() && apiKey.isNotBlank()

    companion object {

        /**
         * Construit la config depuis `BuildConfig`. Quand `secrets.properties`
         * est absent, retourne une config **désactivée** — l'app reste
         * 100 % hors-ligne, sans changement de comportement.
         */
        fun fromBuildConfig(
            url: String?,
            anonKey: String?
        ): BackendConfig = BackendConfig(
            baseUrl = url?.trim().orEmpty(),
            apiKey = anonKey?.trim().orEmpty()
        )
    }
}
