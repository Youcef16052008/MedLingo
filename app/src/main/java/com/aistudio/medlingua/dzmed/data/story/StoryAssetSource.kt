package com.aistudio.medlingua.dzmed.data.story

import android.content.Context
import com.aistudio.medlingua.dzmed.domain.story.Story
import com.aistudio.medlingua.dzmed.domain.story.parseStoryFile

/**
 * Lecteur de l'asset partagé `learning/stories.v1.json` — même fichier que le
 * web (parité vérifiée par `npm run check:stories`). Un `draft` n'est jamais
 * servi ; une version de schéma inconnue lève franchement (RISK-008).
 */
object StoryAssetSource {
    private const val ASSET_PATH = "learning/stories.v1.json"

    fun load(context: Context): List<Story> {
        val raw = context.assets.open(ASSET_PATH).bufferedReader().use { it.readText() }
        return parseStoryFile(raw)
    }
}
