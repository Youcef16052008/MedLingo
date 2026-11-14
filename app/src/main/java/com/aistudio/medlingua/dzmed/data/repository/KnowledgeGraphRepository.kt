package com.aistudio.medlingua.dzmed.data.repository

import com.aistudio.medlingua.dzmed.data.local.dao.KnowledgeGraphDao
import com.aistudio.medlingua.dzmed.data.local.database.MedLinguaDatabase
import com.aistudio.medlingua.dzmed.data.local.entity.KnowledgeGraphEdgeEntity
import com.aistudio.medlingua.dzmed.data.local.entity.KnowledgeGraphNodeEntity
import com.aistudio.medlingua.dzmed.data.local.entity.KnowledgeGraphReportEntity

class KnowledgeGraphRepository(
    private val dao: KnowledgeGraphDao
) {

    val nodes: List<KnowledgeGraphNodeEntity>
        get() = dao.getAllNodes()

    val edges: List<KnowledgeGraphEdgeEntity>
        get() = dao.getAllEdges()

    suspend fun getNodesByModule(module: String): List<KnowledgeGraphNodeEntity> {
        return dao.getNodesByModule(module)
    }

    suspend fun getNodesByChapter(chapter: String): List<KnowledgeGraphNodeEntity> {
        return dao.getNodesByChapter(chapter)
    }

    suspend fun searchNodes(query: String): List<KnowledgeGraphNodeEntity> {
        return dao.searchNodes(query)
    }

    suspend fun getRelatedNodes(nodeId: Int): List<KnowledgeGraphNodeEntity> {
        return dao.getRelatedNodes(nodeId)
    }

    suspend fun getEdgesFrom(nodeId: Int): List<KnowledgeGraphEdgeEntity> {
        return dao.getEdgesFrom(nodeId)
    }

    suspend fun getEdgesTo(nodeId: Int): List<KnowledgeGraphEdgeEntity> {
        return dao.getEdgesTo(nodeId)
    }

    suspend fun getReport(): KnowledgeGraphReportEntity? {
        return dao.getReport()
    }
}

// Générer la base de données à partir du JSON
object KnowledgeGraphGenerator {
    fun generateFromJson(jsonPath: String): KnowledgeGraphReportEntity {
        // Chargement du JSON et insertion dans la base
        // (Implémenté lors de l'exécution dans l'application)
        throw UnsupportedOperationException("Utiliser le script de génération séparé")
    }
}
