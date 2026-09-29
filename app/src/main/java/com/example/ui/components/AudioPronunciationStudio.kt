package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Headphones
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Repeat
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.local.entity.MedicalTermEntity

@Composable
fun AudioPronunciationStudio(
    visible: Boolean,
    currentTerm: MedicalTermEntity?,
    isSpeaking: Boolean,
    isContinuousActive: Boolean,
    currentLanguageCode: String,
    speechRate: Float,
    onPlayPause: () -> Unit,
    onStop: () -> Unit,
    onNext: () -> Unit,
    onPrevious: () -> Unit,
    onToggleContinuous: () -> Unit,
    onChangeLanguage: (String) -> Unit,
    onChangeSpeed: (Float) -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    AnimatedVisibility(
        visible = visible && currentTerm != null,
        enter = slideInVertically(initialOffsetY = { it }) + fadeIn(),
        exit = slideOutVertically(targetOffsetY = { it }) + fadeOut(),
        modifier = modifier
    ) {
        if (currentTerm == null) return@AnimatedVisibility

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 8.dp)
                .shadow(16.dp, RoundedCornerShape(24.dp)),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A)), // Clinical surgical dark slate
            border = androidx.compose.foundation.BorderStroke(1.5.dp, Color(0xFF00897B).copy(alpha = 0.5f))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Header: Module & Chapter Badge + Close
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(28.dp)
                                .clip(CircleShape)
                                .background(Color(0xFF004D40)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Headphones,
                                contentDescription = "Studio Prononciation",
                                tint = Color(0xFF80CBC4),
                                modifier = Modifier.size(16.dp)
                            )
                        }

                        Column {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Text(
                                    text = "STUDIO PRONONCIATION",
                                    color = Color(0xFF80CBC4),
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = 1.sp
                                )
                                if (isContinuousActive) {
                                    Surface(
                                        shape = RoundedCornerShape(8.dp),
                                        color = Color(0xFFF59E0B)
                                    ) {
                                        Text(
                                            text = "ÉCOUTE CONTINUE",
                                            color = Color(0xFF78350F),
                                            fontSize = 9.sp,
                                            fontWeight = FontWeight.ExtraBold,
                                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                        )
                                    }
                                }
                            }
                            Text(
                                text = "${currentTerm.module} • ${currentTerm.chapter}",
                                color = Color.White.copy(alpha = 0.6f),
                                fontSize = 11.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }

                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier.size(28.dp),
                        colors = IconButtonDefaults.iconButtonColors(contentColor = Color.White.copy(alpha = 0.7f))
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Fermer le studio",
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }

                // Term Pronunciation Focus Card
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(
                            Brush.linearGradient(
                                colors = listOf(Color(0xFF1E293B), Color(0xFF0F172A))
                            )
                        )
                        .border(1.dp, Color(0xFF334155), RoundedCornerShape(16.dp))
                        .padding(14.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // English Primary Term
                        Text(
                            text = currentTerm.termEn,
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )

                        // IPA Phonetic Transcription
                        if (currentTerm.ipaPhonetic.isNotBlank()) {
                            Text(
                                text = currentTerm.ipaPhonetic,
                                color = Color(0xFF2DD4BF),
                                fontSize = 13.sp,
                                fontFamily = FontFamily.Monospace,
                                modifier = Modifier.padding(top = 2.dp)
                            )
                        }

                        // Translations (FR & AR)
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "🇫🇷 ${currentTerm.termFr}",
                                color = Color(0xFFE2E8F0),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = "🇸🇦 ${currentTerm.termAr}",
                                color = Color(0xFFFDE68A),
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }

                        // Animated Audio Waveform when active
                        AnimatedWaveform(isSpeaking = isSpeaking)
                    }
                }

                // Language Selectors (EN / FR / AR)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Langue voix :",
                        color = Color.White.copy(alpha = 0.7f),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium
                    )

                    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        LanguageVoiceChip(
                            label = "🇬🇧 Anglais",
                            isSelected = currentLanguageCode.lowercase().startsWith("en"),
                            onClick = { onChangeLanguage("en") }
                        )
                        LanguageVoiceChip(
                            label = "🇫🇷 Français",
                            isSelected = currentLanguageCode.lowercase().startsWith("fr"),
                            onClick = { onChangeLanguage("fr") }
                        )
                        LanguageVoiceChip(
                            label = "🇸🇦 العربية",
                            isSelected = currentLanguageCode.lowercase().startsWith("ar"),
                            onClick = { onChangeLanguage("ar") }
                        )
                    }
                }

                // Controls Row: Prev, Play/Pause, Next, Continuous Loop, Speed
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Continuous mode toggle
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(if (isContinuousActive) Color(0xFF00796B) else Color(0xFF1E293B))
                            .clickable { onToggleContinuous() }
                            .padding(horizontal = 10.dp, vertical = 8.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Repeat,
                                contentDescription = "Boucle",
                                tint = if (isContinuousActive) Color.White else Color.White.copy(alpha = 0.6f),
                                modifier = Modifier.size(16.dp)
                            )
                            Text(
                                text = "Auto",
                                color = if (isContinuousActive) Color.White else Color.White.copy(alpha = 0.7f),
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    // Playback Transport Buttons
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        IconButton(
                            onClick = onPrevious,
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(Color(0xFF1E293B)),
                            colors = IconButtonDefaults.iconButtonColors(contentColor = Color.White)
                        ) {
                            Icon(
                                imageVector = Icons.Default.SkipPrevious,
                                contentDescription = "Précédent",
                                modifier = Modifier.size(20.dp)
                            )
                        }

                        // Main Big Play/Pause Button
                        Box(
                            modifier = Modifier
                                .size(54.dp)
                                .clip(CircleShape)
                                .background(
                                    Brush.linearGradient(
                                        colors = listOf(Color(0xFF00897B), Color(0xFF004D40))
                                    )
                                )
                                .clickable { onPlayPause() },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = if (isSpeaking) Icons.Default.Pause else Icons.Default.PlayArrow,
                                contentDescription = if (isSpeaking) "Pause" else "Lire",
                                tint = Color.White,
                                modifier = Modifier.size(28.dp)
                            )
                        }

                        IconButton(
                            onClick = onNext,
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(Color(0xFF1E293B)),
                            colors = IconButtonDefaults.iconButtonColors(contentColor = Color.White)
                        ) {
                            Icon(
                                imageVector = Icons.Default.SkipNext,
                                contentDescription = "Suivant",
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }

                    // Speed Selector Menu
                    SpeedSelectorPill(
                        currentSpeed = speechRate,
                        onSpeedSelected = onChangeSpeed
                    )
                }
            }
        }
    }
}

@Composable
private fun LanguageVoiceChip(
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier.clickable { onClick() },
        shape = RoundedCornerShape(10.dp),
        color = if (isSelected) Color(0xFF00695C) else Color(0xFF1E293B),
        border = if (isSelected) androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF26A69A)) else null
    ) {
        Text(
            text = label,
            color = if (isSelected) Color.White else Color.White.copy(alpha = 0.7f),
            fontSize = 11.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 5.dp)
        )
    }
}

@Composable
private fun SpeedSelectorPill(
    currentSpeed: Float,
    onSpeedSelected: (Float) -> Unit
) {
    val speeds = listOf(0.75f, 0.9f, 1.0f, 1.25f)
    val nextSpeed = when {
        currentSpeed <= 0.75f -> 0.9f
        currentSpeed <= 0.9f -> 1.0f
        currentSpeed <= 1.0f -> 1.25f
        else -> 0.75f
    }

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFF1E293B))
            .clickable { onSpeedSelected(nextSpeed) }
            .padding(horizontal = 10.dp, vertical = 8.dp)
    ) {
        Text(
            text = "${currentSpeed}x",
            color = Color(0xFF38BDF8),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun AnimatedWaveform(isSpeaking: Boolean) {
    val infiniteTransition = rememberInfiniteTransition(label = "WaveTransition")
    val h1 by infiniteTransition.animateFloat(
        initialValue = 4f,
        targetValue = if (isSpeaking) 22f else 6f,
        animationSpec = infiniteRepeatable(tween(250), RepeatMode.Reverse),
        label = "h1"
    )
    val h2 by infiniteTransition.animateFloat(
        initialValue = 18f,
        targetValue = if (isSpeaking) 6f else 8f,
        animationSpec = infiniteRepeatable(tween(200), RepeatMode.Reverse),
        label = "h2"
    )
    val h3 by infiniteTransition.animateFloat(
        initialValue = 8f,
        targetValue = if (isSpeaking) 26f else 5f,
        animationSpec = infiniteRepeatable(tween(300), RepeatMode.Reverse),
        label = "h3"
    )
    val h4 by infiniteTransition.animateFloat(
        initialValue = 14f,
        targetValue = if (isSpeaking) 8f else 6f,
        animationSpec = infiniteRepeatable(tween(220), RepeatMode.Reverse),
        label = "h4"
    )

    Row(
        modifier = Modifier
            .padding(top = 10.dp)
            .height(26.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        WaveBar(height = h1, isSpeaking = isSpeaking)
        WaveBar(height = h2, isSpeaking = isSpeaking)
        WaveBar(height = h3, isSpeaking = isSpeaking)
        WaveBar(height = h4, isSpeaking = isSpeaking)
        WaveBar(height = h2, isSpeaking = isSpeaking)
        WaveBar(height = h1, isSpeaking = isSpeaking)
    }
}

@Composable
private fun WaveBar(height: Float, isSpeaking: Boolean) {
    Box(
        modifier = Modifier
            .width(4.dp)
            .height(height.dp)
            .clip(RoundedCornerShape(2.dp))
            .background(if (isSpeaking) Color(0xFF2DD4BF) else Color(0xFF475569))
    )
}
