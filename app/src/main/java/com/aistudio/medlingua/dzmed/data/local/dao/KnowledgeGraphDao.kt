package com.aistudio.medlingua.dzmed.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aistudio.medlingua.dzmed.data.local.entity.KnowledgeGraphEdgeEntity
import com.aistudio.medlingua.dzmed.data.local.entity.KnowledgeGraphNodeEntity
import com.aistudio.medlingua.dzmed.data.local.entity.KnowledgeGraphReportEntity

@Dao
interface KnowledgeGraphDao {

    /* ── Nœuds ─────────────────────────────────────────────────────── */

    @Query("SELECT * FROM knowledge_graph_nodes ORDER BY module, chapter, termEn")
    suspend fun getAllNodes(): List<KnowledgeGraphNodeEntity>

    @Query("SELECT * FROM knowledge_graph_nodes WHERE module = :module ORDER BY chapter, termEn")
    suspend fun getNodesByModule(module: String): List<KnowledgeGraphNodeEntity>

    @Query("SELECT * FROM knowledge_graph_nodes WHERE chapter = :chapter ORDER BY termEn")
    suspend fun getNodesByChapter(chapter: String): List<KnowledgeGraphNodeEntity>

    @Query("""
        SELECT * FROM knowledge_graph_nodes
        WHERE termEn LIKE '%' || :query || '%'
           OR termFr LIKE '%' || :query || '%'
           OR termAr LIKE '%' || :query || '%'
           OR definitionEn LIKE '%' || :query || '%'
        ORDER BY module, chapter, termEn
    """)
    suspend fun searchNodes(query: String): List<KnowledgeGraphNodeEntity>

    /* ── Arêtes ────────────────────────────────────────────────────── */

    @Query("SELECT * FROM knowledge_graph_edges WHERE fromNodeId = :nodeId ORDER BY relationType, toNodeId")
    suspend fun getEdgesFrom(nodeId: Int): List<KnowledgeGraphEdgeEntity>

    @Query("SELECT * FROM knowledge_graph_edges WHERE toNodeId = :nodeId ORDER BY relationType, fromNodeId")
    suspend fun getEdgesTo(nodeId: Int): List<KnowledgeGraphEdgeEntity>

    @Query("""
        SELECT kgn.*
        FROM knowledge_graph_edges kge
        JOIN knowledge_graph_nodes kgn ON kge.toNodeId = kgn.nodeId
        WHERE kge.fromNodeId = :nodeId AND (kge.relationType = 'part_of' OR kge.relationType = 'located_in_module')
        ORDER BY kgn.module, kgn.chapter, kgn.termEn
    """)
    suspend fun getRelatedNodes(nodeId: Int): List<KnowledgeGraphNodeEntity>

    @Query("SELECT * FROM knowledge_graph_edges ORDER BY relationType, edgeId")
    suspend fun getAllEdges(): List<KnowledgeGraphEdgeEntity>

    /* ── Rapport ───────────────────────────────────────────────────── */

    @Query("SELECT * FROM knowledge_graph_report WHERE reportKey = 'summary'")
    suspend fun getReport(): KnowledgeGraphReportEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReport(report: KnowledgeGraphReportEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNodes(nodes: List<KnowledgeGraphNodeEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEdges(edges: List<KnowledgeGraphEdgeEntity>)
}
