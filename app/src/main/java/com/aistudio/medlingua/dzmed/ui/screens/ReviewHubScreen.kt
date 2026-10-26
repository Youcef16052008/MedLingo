package com.aistudio.medlingua.dzmed.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aistudio.medlingua.dzmed.data.local.entity.ReviewQueueItem
import com.aistudio.medlingua.dzmed.localization.Language
import com.aistudio.medlingua.dzmed.localization.Strings

/**
 * Hub central des "Revisions en attente".
 * Affiche les exercices de leçon review qui ont été débloqués (status = unlocked)
 * mais pas encore joués. L'utilisateur peut les lancer maintenant.
 */
@Composable
fun ReviewHubScreen(
    reviews: List<ReviewQueueItem>,
    lang: Language,
    onPlay: (ReviewQueueItem) -> Unit,
    onDelete: (ReviewQueueItem) -> Unit,
    modifier: Modifier = Modifier
) {
    val unlocked = reviews.filter { it.status == ReviewQueueItem.Status.UNLOCKED }
    val played = reviews.filter { it.status == ReviewQueueItem.Status.PLAYED }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF0F7F4))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // En-tête
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = Strings.get("review_hub", lang),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color(0xFF12211C)
                        )
                        Text(
                            text = "${unlocked.size} en attente · ${played.size} joués",
                            fontSize = 13.sp,
                            color = Color(0xFF6B7877)
                        )
                    }
                    if (unlocked.isNotEmpty()) {
                        Box(modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFFFB300))
                            .clickable { /* Rafraîchir */ })
                    }
                }
            }
        }

        // Section: En attente
        if (unlocked.isNotEmpty()) {
            Text(
                text = Strings.get("review_pending", lang),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF12211C)
            )
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(
                    unlocked,
                    key = { it.id }
                ) { item ->
                    ReviewItem(
                        item = item,
                        lang = lang,
                        onPlay = { onPlay(item) },
                        onDelete = { onDelete(item) }
                    )
                }
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = Strings.get("review_empty", lang),
                    fontSize = 15.sp,
                    color = Color(0xFF90A4AE)
                )
            }
        }

        // Section: Joués
        if (played.isNotEmpty()) {
            Text(
                text = Strings.get("review_played", lang),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF12211C)
            )
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(
                    played,
                    key = { it.id }
                ) { item ->
                    ReviewItem(
                        item = item,
                        lang = lang,
                        onPlay = { /* already played */ },
                        onDelete = { onDelete(item) }
                    )
                }
            }
        }
    }
}

@Composable
private fun ReviewItem(
    item: ReviewQueueItem,
    lang: Language,
    onPlay: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = item.title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF12211C)
                )
                Text(
                    text = "L${item.lessonId}",
                    fontSize = 11.sp,
                    color = Color(0xFF90A4AE)
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (item.status == ReviewQueueItem.Status.UNLOCKED) {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF4CAF50))
                            .clickable { onPlay() }
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF90A4AE))
                    )
                }
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFFF5252))
                        .clickable { onDelete() }
                )
            }
        }
    }
}
