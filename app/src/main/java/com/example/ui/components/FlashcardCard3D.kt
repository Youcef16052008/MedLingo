package com.example.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.TouchApp
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.local.entity.MedicalTermEntity
import com.example.localization.Language
import com.example.localization.Strings
import com.example.ui.theme.AmberGold
import com.example.ui.theme.ClinicalTeal
import com.example.ui.theme.MedGreenDark
import com.example.ui.theme.MedGreenPrimary

@Composable
fun FlashcardCard3D(
    term: MedicalTermEntity,
    isFlipped: Boolean,
    onFlip: () -> Unit,
    onSpeak: (String) -> Unit,
    onBookmarkToggle: (MedicalTermEntity) -> Unit,
    currentLanguage: Language,
    modifier: Modifier = Modifier
) {
    val rotation by animateFloatAsState(
        targetValue = if (isFlipped) 180f else 0f,
        animationSpec = tween(durationMillis = 400),
        label = "card_rotation"
    )

    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(410.dp)
            .graphicsLayer {
                rotationY = rotation
                cameraDistance = 16f * density
            }
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { onFlip() }
            .testTag("flashcard_card_3d"),
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        if (rotation <= 90f) {
            // Front Side
            FrontCardSide(
                term = term,
                currentLanguage = currentLanguage,
                onSpeak = onSpeak,
                onBookmarkToggle = onBookmarkToggle
            )
        } else {
            // Back Side (mirrored back by 180f)
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer { rotationY = 180f }
            ) {
                BackCardSide(
                    term = term,
                    onSpeak = onSpeak,
                    onBookmarkToggle = onBookmarkToggle,
                    currentLanguage = currentLanguage
                )
            }
        }
    }
}

@Composable
private fun FrontCardSide(
    term: MedicalTermEntity,
    currentLanguage: Language,
    onSpeak: (String) -> Unit,
    onBookmarkToggle: (MedicalTermEntity) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF0D532C),
                        Color(0xFF1B5E20),
                        Color(0xFF2E7D32)
                    )
                )
            )
            .padding(22.dp)
    ) {
        // Top Row: Module and Bookmark
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.White.copy(alpha = 0.18f))
                    .padding(horizontal = 14.dp, vertical = 6.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.School,
                        contentDescription = null,
                        tint = Color(0xFFA5F4AC),
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = "${term.module} • ${term.chapter}",
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            IconButton(
                onClick = { onBookmarkToggle(term) },
                modifier = Modifier.size(36.dp)
            ) {
                Icon(
                    imageVector = if (term.isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                    contentDescription = "Bookmark",
                    tint = if (term.isBookmarked) AmberGold else Color.White
                )
            }
        }

        // Center Content
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.White.copy(alpha = 0.12f))
                    .padding(horizontal = 12.dp, vertical = 4.dp)
            ) {
                Text(
                    text = "🇫🇷 Nomenclature Médicale Française",
                    color = Color.White.copy(alpha = 0.85f),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = term.termFr,
                color = Color.White,
                fontSize = 32.sp,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center,
                lineHeight = 38.sp
            )

            if (term.termAr.isNotBlank()) {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "🇸🇦 ${term.termAr}",
                    color = Color(0xFFA5F4AC),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(30.dp))
                    .background(Color.White.copy(alpha = 0.18f))
                    .border(1.dp, Color.White.copy(alpha = 0.3f), RoundedCornerShape(30.dp))
                    .padding(horizontal = 18.dp, vertical = 8.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.TouchApp,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = Strings.get("tap_to_flip", currentLanguage),
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

@Composable
private fun BackCardSide(
    term: MedicalTermEntity,
    onSpeak: (String) -> Unit,
    onBookmarkToggle: (MedicalTermEntity) -> Unit,
    currentLanguage: Language
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .border(2.dp, Color(0xFF1B5E20), RoundedCornerShape(24.dp))
            .padding(18.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // English Header + Speaker
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFE8F5E9)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "🇬🇧", fontSize = 20.sp)
                    }

                    Column {
                        Text(
                            text = term.termEn,
                            color = MedGreenDark,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                        if (term.ipaPhonetic.isNotBlank()) {
                            Text(
                                text = term.ipaPhonetic,
                                color = Color(0xFF388E3C),
                                fontSize = 13.sp,
                                fontStyle = FontStyle.Italic
                            )
                        }
                    }
                }

                FilledIconButton(
                    onClick = { onSpeak(term.termEn) },
                    colors = IconButtonDefaults.filledIconButtonColors(containerColor = Color(0xFFE8F5E9)),
                    modifier = Modifier.size(42.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.VolumeUp,
                        contentDescription = "Listen to pronunciation",
                        tint = MedGreenDark,
                        modifier = Modifier.size(22.dp)
                    )
                }
            }

            HorizontalDivider(color = Color(0xFFE2E8F0), thickness = 1.dp)

            // Trilingual Cross-Reference (Standard Arabic & French)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFFF8FAFC))
                    .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(12.dp))
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "🇸🇦 ${term.termAr}",
                        color = Color(0xFF1E2922),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "🇫🇷 ${term.termFr}",
                        color = Color(0xFF64748B),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            // Etymology & Roots Section (🏛️)
            if (term.etymology.isNotBlank()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFFF0FDF4))
                        .border(1.dp, Color(0xFFBBF7D0), RoundedCornerShape(12.dp))
                        .padding(10.dp)
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                        Text(
                            text = Strings.get("etymology", currentLanguage),
                            color = Color(0xFF166534),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = term.etymology,
                            color = Color(0xFF14532D),
                            fontSize = 12.sp,
                            lineHeight = 16.sp
                        )
                    }
                }
            }

            // Clinical Pearl / High-Yield Box (🩺)
            if (term.clinicalPearl.isNotBlank()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFFEFF6FF))
                        .border(1.dp, Color(0xFFBFDBFE), RoundedCornerShape(12.dp))
                        .padding(10.dp)
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                        Text(
                            text = Strings.get("clinical_pearl", currentLanguage),
                            color = Color(0xFF1E40AF),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = term.clinicalPearl,
                            color = Color(0xFF1E3A8A),
                            fontSize = 12.sp,
                            lineHeight = 16.sp
                        )
                    }
                }
            }

            // Medical Mnemonic (💡)
            if (term.mnemonic.isNotBlank()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFFFEF3C7))
                        .border(1.dp, Color(0xFFFDE68A), RoundedCornerShape(12.dp))
                        .padding(10.dp)
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                        Text(
                            text = Strings.get("mnemonic_tip", currentLanguage),
                            color = Color(0xFF92400E),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = term.mnemonic,
                            color = Color(0xFF78350F),
                            fontSize = 12.sp,
                            fontStyle = FontStyle.Italic,
                            lineHeight = 16.sp
                        )
                    }
                }
            }

            // English Academic Definition
            if (term.definitionEn.isNotBlank()) {
                Text(
                    text = "📖 ${term.definitionEn}",
                    color = Color(0xFF334155),
                    fontSize = 12.sp,
                    lineHeight = 17.sp
                )
            }
        }
    }
}
