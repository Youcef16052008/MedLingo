package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.R
import kotlinx.coroutines.delay

private const val INTRO_DURATION_MS = 1500L
private const val FADE_MS = 350

/**
 * Full-screen intro shown on every cold start. The artwork is chosen from the system theme:
 * [intro_dark] on dark, [intro_light] on light, so the artwork background always matches the UI.
 */
@Composable
fun AppIntroOverlay(
    darkTheme: Boolean,
    onTimeout: () -> Unit,
    modifier: Modifier = Modifier
) {
    val artwork = if (darkTheme) R.drawable.intro_dark else R.drawable.intro_light
    var visible by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        delay(INTRO_DURATION_MS)
        visible = false
        delay(FADE_MS.toLong())
        onTimeout()
    }

    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(tween(FADE_MS)),
        exit = fadeOut(tween(FADE_MS)),
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(if (darkTheme) Color(0xFF1C2125) else Color.White)
        ) {
            Image(
                painter = painterResource(artwork),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
