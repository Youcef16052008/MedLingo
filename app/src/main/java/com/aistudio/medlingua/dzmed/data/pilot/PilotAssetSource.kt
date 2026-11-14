package com.aistudio.medlingua.dzmed.data.pilot

import android.content.Context
import com.aistudio.medlingua.dzmed.domain.pilot.MeasureManifest
import com.aistudio.medlingua.dzmed.domain.pilot.PilotManifest
import com.aistudio.medlingua.dzmed.domain.pilot.PilotSchemaException
import com.aistudio.medlingua.dzmed.domain.pilot.parseMeasureManifest
import com.aistudio.medlingua.dzmed.domain.pilot.parsePilotManifest

/**
 * P4.1 — Lecteur des assets partagés du pilote — MÊMES fichiers que le web
 * (parité vérifiée par `npm run check:pilot-parity`).
 *
 * Les deux manifestes sont chargés ENSEMBLE et leur `contentVersion` est
 * recoupée : un désaccord est un échec franc (banques de mesure invalides),
 * jamais une survivance silencieuse. Une version de schéma inconnue lève
 * franchement (pattern `parseStoryFile`, verrou P0.4).
 */
object PilotAssetSource {
    private const val TRAINING_PATH = "pilot/pilot-anatomy.v1.json"
    private const val MEASURE_PATH = "pilot/pilot-anatomy-measure.v1.json"

    data class PilotAssets(val training: PilotManifest, val measure: MeasureManifest)

    fun load(context: Context): PilotAssets {
        val trainingRaw = context.assets.open(TRAINING_PATH).bufferedReader().use { it.readText() }
        val measureRaw = context.assets.open(MEASURE_PATH).bufferedReader().use { it.readText() }
        val training = parsePilotManifest(trainingRaw)
        val measure = parseMeasureManifest(measureRaw, training.contentVersion)
        return PilotAssets(training, measure)
    }
}

/** Erreur de cohérence de contenu — l'écran affiche le message, jamais un silence. */
class PilotContentException(message: String) : IllegalArgumentException(message)
