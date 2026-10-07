package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.localization.Language
import com.example.localization.Strings

/** Humeur de Medi 🩺 (mascotte du compagnon d'étude). */
enum class MediMood { HAPPY, MOTIVATING, CELEBRATING, FRUSTRATED }

private fun moodIcon(mood: MediMood): String = when (mood) {
    MediMood.HAPPY -> "✨"
    MediMood.MOTIVATING -> "💪"
    MediMood.CELEBRATING -> "🎉"
    MediMood.FRUSTRATED -> "😅"
}

private fun moodKey(mood: MediMood): String = when (mood) {
    MediMood.HAPPY -> "medi_happy"
    MediMood.MOTIVATING -> "medi_motivating"
    MediMood.CELEBRATING -> "medi_celebrating"
    MediMood.FRUSTRATED -> "medi_frustrated"
}

private fun moodColor(mood: MediMood): Color = when (mood) {
    MediMood.HAPPY -> Color(0xFF58CC02)
    MediMood.MOTIVATING -> Color(0xFF1CB0F6)
    MediMood.CELEBRATING -> Color(0xFFFFC800)
    MediMood.FRUSTRATED -> Color(0xFFFF4B4B)
}

/**
 * Medi 🩺 — bulle de dialogue contextuelle de la mascotte.
 * Miroir de `web-react/src/components/Medi.tsx` (avatar emoji + bulle).
 */
@Composable
fun Medi(
    mood: MediMood = MediMood.HAPPY,
    message: String? = null,
    lang: Language = Language.FRENCH,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(52.dp)
                .clip(CircleShape)
                .background(Color(0xFFE8F5E9)),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "🩺", fontSize = 26.sp)
            Text(
                text = moodIcon(mood),
                fontSize = 12.sp,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(2.dp)
            )
        }

        Box(
            modifier = Modifier
                .padding(start = 10.dp)
                .clip(RoundedCornerShape(14.dp))
                .background(moodColor(mood).copy(alpha = 0.12f))
                .padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            Text(
                text = message ?: Strings.get(moodKey(mood), lang),
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF1E293B)
            )
        }
    }
}
