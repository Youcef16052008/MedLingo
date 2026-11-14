package com.aistudio.medlingua.dzmed.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aistudio.medlingua.dzmed.data.local.entity.KnowledgeGraphNodeEntity
import com.aistudio.medlingua.dzmed.data.local.entity.KnowledgeGraphEdgeEntity
import com.aistudio.medlingua.dzmed.data.repository.KnowledgeGraphRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class GraphNodeDetail(
    val node: KnowledgeGraphNodeEntity,
    val edgesFrom: List<KnowledgeGraphEdgeEntity>,
    val edgesTo: List<KnowledgeGraphEdgeEntity>,
    val relatedNodes: List<KnowledgeGraphNodeEntity> = emptyList()
)

class KnowledgeGraphViewModel(
    private val repository: KnowledgeGraphRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(GraphUiState())
    val uiState: StateFlow<GraphUiState> = _uiState.asStateFlow()

    init {
        loadAllNodes()
    }

    fun loadAllNodes() {
        viewModelScope.launch {
            val nodes = repository.nodes
            _uiState.value = _uiState.value.copy(
                nodes = nodes,
                isLoading = false
            )
        }
    }

    fun loadNodesByModule(module: String) {
        viewModelScope.launch {
            val nodes = repository.getNodesByModule(module)
            _uiState.value = _uiState.value.copy(
                nodes = nodes,
                selectedModule = module,
                isLoading = false
            )
        }
    }

    fun searchNodes(query: String) {
        viewModelScope.launch {
            val nodes = repository.searchNodes(query)
            _uiState.value = _uiState.value.copy(
                nodes = nodes,
                searchQuery = query,
                isLoading = false
            )
        }
    }

    fun selectNode(nodeId: Int) {
        viewModelScope.launch {
            val node = _uiState.value.nodes.find { it.nodeId == nodeId }
                ?: return@launch
            
            val edgesFrom = repository.getEdgesFrom(nodeId)
            val edgesTo = repository.getEdgesTo(nodeId)
            val relatedNodes = repository.getRelatedNodes(nodeId)
            
            _uiState.value = _uiState.value.copy(
                selectedNodeId = nodeId,
                nodeDetail = GraphNodeDetail(
                    node = node,
                    edgesFrom = edgesFrom,
                    edgesTo = edgesTo,
                    relatedNodes = relatedNodes
                ),
                isLoading = false
            )
        }
    }

    fun clearSelection() {
        _uiState.value = _uiState.value.copy(
            selectedNodeId = null,
            nodeDetail = null
        )
    }

    data class GraphUiState(
        val nodes: List<KnowledgeGraphNodeEntity> = emptyList(),
        val edges: List<KnowledgeGraphEdgeEntity> = emptyList(),
        val selectedNodeId: Int? = null,
        val nodeDetail: GraphNodeDetail? = null,
        val selectedModule: String? = null,
        val searchQuery: String = "",
        val isLoading: Boolean = true
    )

    data class GraphNodeDetail(
        val node: KnowledgeGraphNodeEntity,
        val edgesFrom: List<KnowledgeGraphEdgeEntity>,
        val edgesTo: List<KnowledgeGraphEdgeEntity>,
        val relatedNodes: List<KnowledgeGraphNodeEntity> = emptyList()
    )
}
