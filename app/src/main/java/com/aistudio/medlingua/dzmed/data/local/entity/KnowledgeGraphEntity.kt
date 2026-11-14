package com.aistudio.medlingua.dzmed.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/** Nœud du graphe de connaissances (1 nœud = 1 terme médical). */
@Entity(
    tableName = "knowledge_graph_nodes",
    indices = [
        Index("module"),
        Index("chapter"),
        Index("module", "chapter")
    ]
)
data class KnowledgeGraphNodeEntity(
    @PrimaryKey(autoGenerate = true)
    val nodeId: Int = 0,
    val termEn: String,
    val termFr: String,
    val termAr: String,
    val definitionEn: String,
    val definitionFr: String,
    val definitionAr: String,
    val etymology: String = "",
    val clinicalPearl: String = "",
    val mnemonic: String = "",
    val module: String = "",
    val chapter: String = "",
    val exEn: String = "",
    val exFr: String = "",
    val exAr: String = "",
    val ipa: String = "",
    val isBookmarked: Boolean = false
)

/** Arête du graphe de connaissances (relation entre deux termes). */
@Entity(
    tableName = "knowledge_graph_edges",
    indices = [Index("fromNodeId"), Index("toNodeId"), Index("relationType")]
)
data class KnowledgeGraphEdgeEntity(
    @PrimaryKey(autoGenerate = true)
    val edgeId: Int = 0,
    val fromNodeId: Int,
    val toNodeId: Int,
    val relationType: String, // part_of, function_of, location_of, causes, located_in_module, located_in_chapter
    val confidence: String = "high", // high, medium, low
    val evidence: String = ""
)

/** Rapport de synthèse du graphe (optimisé pour l'Android). */
@Entity(tableName = "knowledge_graph_report")
data class KnowledgeGraphReportEntity(
    @PrimaryKey var reportKey: String = "summary",
    var totalNodes: Int = 0,
    var totalEdges: Int = 0,
    var semanticEdges: Int = 0,
    var structuralEdges: Int = 0,
    var relationBreakdown: String = ""
)
