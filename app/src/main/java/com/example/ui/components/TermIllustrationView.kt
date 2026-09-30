package com.example.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ZoomIn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.data.illustrations.TermIllustration
import com.example.data.illustrations.TermIllustrations
import com.example.data.local.entity.MedicalTermEntity
import com.example.localization.Language

/** Only rendered for terms that have a real packaged illustration. No broken-image placeholders. */
@Composable
fun TermIllustrationView(
    term: MedicalTermEntity,
    language: Language,
    modifier: Modifier = Modifier,
    height: Dp = 150.dp
) {
    val illustration = TermIllustrations.forTerm(term) ?: return
    val caption = when (language) {
        Language.ARABIC -> illustration.captionAr
        Language.ENGLISH -> illustration.captionEn
        Language.FRENCH -> illustration.captionFr
    }
    var zoomed by remember(term.module, term.chapter, term.termEn) { mutableStateOf(false) }

    Column(modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier.fillMaxWidth().height(height)
                .clip(RoundedCornerShape(12.dp)).background(Color(0xFFF0F7F1)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(illustration.drawable),
                contentDescription = caption,
                contentScale = ContentScale.Fit,
                modifier = Modifier.fillMaxSize().padding(8.dp)
            )
            IconButton(
                onClick = { zoomed = true },
                modifier = Modifier.align(Alignment.TopEnd).testTag("zoom_illustration_${term.id}")
            ) {
                Icon(Icons.Default.ZoomIn, contentDescription = when (language) {
                    Language.ARABIC -> "تكبير الرسم"
                    Language.ENGLISH -> "Enlarge diagram"
                    Language.FRENCH -> "Agrandir le schéma"
                })
            }
        }
        Text(caption, color = Color(0xFF334155), textAlign = TextAlign.Center)
        Text(illustration.credit, color = Color(0xFF64748B), textAlign = TextAlign.Center)
    }

    if (zoomed) {
        IllustrationDialog(illustration, caption, language, onClose = { zoomed = false })
    }
}

@Composable
private fun IllustrationDialog(
    illustration: TermIllustration,
    caption: String,
    language: Language,
    onClose: () -> Unit
) {
    var scale by remember(illustration.drawable) { mutableFloatStateOf(1f) }
    var pan by remember(illustration.drawable) { mutableStateOf(Offset.Zero) }
    Dialog(onDismissRequest = onClose, properties = DialogProperties(usePlatformDefaultWidth = false)) {
        Surface(modifier = Modifier.fillMaxSize(), color = Color(0xFF0F172A)) {
            Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    IconButton(onClick = onClose) {
                        Icon(Icons.Default.Close, contentDescription = when (language) {
                            Language.ARABIC -> "إغلاق"
                            Language.ENGLISH -> "Close diagram"
                            Language.FRENCH -> "Fermer le schéma"
                        }, tint = Color.White)
                    }
                }
                BoxWithConstraints(
                    modifier = Modifier.weight(1f).fillMaxWidth().clipToBounds()
                        .background(Color.White, RoundedCornerShape(12.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    val widthPx = with(LocalDensity.current) { maxWidth.toPx() }
                    val heightPx = with(LocalDensity.current) { maxHeight.toPx() }
                    val gestureState = rememberTransformableState { zoomChange, offsetChange, _ ->
                        scale = (scale * zoomChange).coerceIn(1f, 5f)
                        // Limit panning to the scaled viewport; reset position at 1x.
                        pan = Offset(
                            (pan.x + offsetChange.x).coerceIn(-widthPx * (scale - 1f) / 2f, widthPx * (scale - 1f) / 2f),
                            (pan.y + offsetChange.y).coerceIn(-heightPx * (scale - 1f) / 2f, heightPx * (scale - 1f) / 2f)
                        )
                    }
                    Image(
                        painter = painterResource(illustration.drawable),
                        contentDescription = caption,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.fillMaxSize().padding(16.dp)
                            .graphicsLayer {
                                scaleX = scale
                                scaleY = scale
                                translationX = pan.x
                                translationY = pan.y
                            }.transformable(gestureState)
                    )
                }
                Text(caption, modifier = Modifier.fillMaxWidth().padding(top = 12.dp),
                    color = Color.White, textAlign = TextAlign.Center)
                Text(illustration.credit, modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
                    color = Color(0xFFCBD5E1), textAlign = TextAlign.Center)
            }
        }
    }
}
