package com.example.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.ui.unit.sp

/**
 * Anneau d'objectif quotidien (arc `sweepAngle`) — spec §5.1/§5.2.
 * Miroir de `web-react/src/components/GoalRing.tsx` : grand dans l'en-tête du
 * Parcours, petit dans le hub Révision.
 */
@Composable
fun GoalRing(
    pct: Int,
    done: Boolean,
    size: Dp = 74.dp,
    modifier: Modifier = Modifier
) {
    val clamped = pct.coerceIn(0, 100)
    val track = Color(0xFFE0E0E0)
    val fill = if (done) Color(0xFFFFC800) else Color(0xFF58CC02)

    Box(modifier = modifier.size(size), contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier.size(size)) {
            val stroke = Stroke(width = size.toPx() * 0.12f, cap = StrokeCap.Round)
            val inset = stroke.width / 2f
            val arcSize = Size(this.size.width - stroke.width, this.size.height - stroke.width)
            drawArc(
                color = track,
                startAngle = -90f,
                sweepAngle = 360f,
                useCenter = false,
                topLeft = Offset(inset, inset),
                size = arcSize,
                style = stroke
            )
            drawArc(
                color = fill,
                startAngle = -90f,
                sweepAngle = 360f * clamped / 100f,
                useCenter = false,
                topLeft = Offset(inset, inset),
                size = arcSize,
                style = stroke
            )
        }
        Text(
            text = if (done) "🎯" else "⭐",
            fontSize = if (size <= 56.dp) 16.sp else 24.sp,
            modifier = Modifier.padding(0.dp)
        )
    }
}
