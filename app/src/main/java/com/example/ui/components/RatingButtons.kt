package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.localization.Language
import com.example.localization.Strings

@Composable
fun RatingButtons(
    onRated: (Int) -> Unit,
    currentLanguage: Language,
    modifier: Modifier = Modifier
) {
    val ratings = listOf(
        RatingItem(1, "😫", Strings.get("rating_forgot", currentLanguage), Color(0xFFD32F2F)),
        RatingItem(2, "😕", Strings.get("rating_hard", currentLanguage), Color(0xFFF57C00)),
        RatingItem(3, "🤔", Strings.get("rating_medium", currentLanguage), Color(0xFFFFA000)),
        RatingItem(4, "😊", Strings.get("rating_easy", currentLanguage), Color(0xFF689F38)),
        RatingItem(5, "🤩", Strings.get("rating_perfect", currentLanguage), Color(0xFF2E7D32))
    )

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = Strings.get("how_well_remembered", currentLanguage),
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF455A64)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ratings.forEach { item ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .clip(RoundedCornerShape(14.dp))
                        .clickable { onRated(item.quality) }
                        .padding(horizontal = 4.dp, vertical = 2.dp)
                        .testTag("rate_btn_${item.quality}")
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(item.color.copy(alpha = 0.12f))
                            .border(1.5.dp, item.color, RoundedCornerShape(12.dp))
                            .padding(vertical = 8.dp, horizontal = 10.dp)
                    ) {
                        Text(text = item.emoji, fontSize = 24.sp)
                    }
                    Text(
                        text = item.label,
                        fontSize = 10.sp,
                        color = item.color,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        }
    }
}

private data class RatingItem(
    val quality: Int,
    val emoji: String,
    val label: String,
    val color: Color
)
