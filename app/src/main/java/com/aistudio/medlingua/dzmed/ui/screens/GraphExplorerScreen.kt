package com.aistudio.medlingua.dzmed.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.border.BorderStroke
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aistudio.medlingua.dzmed.ui.viewmodel.KnowledgeGraphViewModel
import com.aistudio.medlingua.dzmed.data.local.entity.KnowledgeGraphNodeEntity
import com.aistudio.medlingua.dzmed.ui.viewmodel.KnowledgeGraphViewModel.GraphNodeDetail

@Composable
fun GraphExplorerScreen(
    onNavigateBack: () -> Unit,
    viewModel: KnowledgeGraphViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    var searchQuery by remember { mutableStateOf("") }

    LaunchedEffect(uiState.isLoading) {
        if (uiState.isLoading && uiState.nodes.isEmpty()) {
            viewModel.loadAllNodes()
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onNavigateBack) {
                Icon(Icons.Default.ArrowBack, "Retour", tint = MaterialTheme.colorScheme.onSurface)
            }
            Text(
                text = "Graphe de connaissances",
                style = MaterialTheme.typography.titleMedium
            )
        }

        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            placeholder = { Text("Rechercher un terme...") },
            leadingIcon = {
                Icon(Icons.Default.Search, "Rechercher", tint = MaterialTheme.colorScheme.onSurface)
            },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedContainerColor = MaterialTheme.colorScheme.surface
            )
        )

        if (uiState.isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else if (uiState.nodes.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Aucun terme trouve")
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(uiState.nodes, key = { it.nodeId }) { node ->
                    NodeRow(
                        node = node,
                        isSelected = uiState.selectedNodeId == node.nodeId,
                        onClick = { viewModel.selectNode(node.nodeId) }
                    )
                }
            }
        }
    }

    uiState.nodeDetail?.let { detail ->
        NodeDetailCard(detail = detail, onClose = { viewModel.clearSelection() })
    }
}

@Composable
private fun NodeRow(
    node: KnowledgeGraphNodeEntity,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        color = if (isSelected) {
            MaterialTheme.colorScheme.primary
        } else {
            MaterialTheme.colorScheme.surface
        },
        shadowElevation = 2.dp,
        border = if (isSelected) {
            BorderStroke(2.dp, MaterialTheme.colorScheme.primary)
        } else {
            null
        },
        shape = MaterialTheme.shapes.medium
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = node.termEn,
                style = MaterialTheme.typography.titleMedium,
                color = if (isSelected) {
                    MaterialTheme.colorScheme.onPrimary
                } else {
                    MaterialTheme.colorScheme.onSurface
                }
            )
            Text(
                text = node.termFr,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = node.module,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun NodeDetailCard(
    detail: GraphNodeDetail,
    onClose: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        tonalElevation = 4.dp,
        shape = MaterialTheme.shapes.medium
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Details",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.weight(1f)
                )
                IconButton(onClick = onClose) {
                    Icon(Icons.Default.Close, "Fermer")
                }
            }

            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Definition (" + detail.node.termEn + ")",
                    style = MaterialTheme.typography.titleSmall
                )
                Text(
                    text = detail.node.definitionEn,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            if (detail.edgesFrom.isNotEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Liens :", style = MaterialTheme.typography.labelMedium)
                detail.edgesFrom.forEach { edge ->
                    val targetNode = detail.relatedNodes.find { it.nodeId == edge.toNodeId }
                    Row(Modifier.fillMaxWidth()) {
                        Text(
                            text = "-> " + edge.relationType,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.primary
                        )
                        if (targetNode != null) {
                            Text(text = " " + targetNode.termEn, style = MaterialTheme.typography.bodySmall)
                        }
                    }
                }
            }

            if (detail.edgesTo.isNotEmpty()) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Recus :", style = MaterialTheme.typography.labelMedium)
                detail.edgesTo.forEach { edge ->
                    val sourceNode = detail.relatedNodes.find { it.nodeId == edge.fromNodeId }
                    Row(Modifier.fillMaxWidth()) {
                        Text(
                            text = "<- " + edge.relationType,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.secondary
                        )
                        if (sourceNode != null) {
                            Text(text = " " + sourceNode.termEn, style = MaterialTheme.typography.bodySmall)
                        }
                    }
                }
            }
        }
    }
}
