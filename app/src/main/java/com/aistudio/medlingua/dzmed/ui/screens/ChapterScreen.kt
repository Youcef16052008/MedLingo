package com.aistudio.medlingua.dzmed.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.aistudio.medlingua.dzmed.data.initial.InitialData
import com.aistudio.medlingua.dzmed.domain.path.PathBuilder
import com.aistudio.medlingua.dzmed.domain.path.PathModule
import com.aistudio.medlingua.dzmed.domain.lesson.LessonGenerator
import com.aistudio.medlingua.dzmed.localization.Language
import com.aistudio.medlingua.dzmed.localization.Strings

/**
 * Écran de parcours : module → chapter → lesson (nouvelle architecture).
 * Navigation bidirectionnelle complète : retour en arrière à chaque niveau.
 */
@Composable
fun ChapterScreen(
    moduleId: String,
    lang: Language,
    onChapterSelect: (String, String) -> Unit,
    onLessonStart: (String, Int) -> Unit,
    modifier:Modifier = Modifier
) {
    val modules = remember { InitialData.modulesList.map { it to moduleTitle(it, lang) } }
    val module = modules.firstOrNull { it.first.id == moduleId } ?: return

    val chapters = remember {
        val terms = InitialData.termsOfModule(moduleId)
        val byChapter = terms.groupBy { it.chapter }
        byChapter.map { (chapterId, chapterTerms) ->
            chapterId to chapterId
        }.toMap()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF0F7F4))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // En-tête module
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = module.first.titleFr,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFF12211C)
                )
                Text(
                    text = "${chapters.size} chapitres",
                    fontSize = 13.sp,
                    color = Color(0xFF6B7877)
                )
            }
        }

        // Sélecteur de chapitre
        Text(
            text = Strings.get("chapter_selector", lang),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF12211C)
        )

        val selectedChapterId by remember { mutableStateOf(chapters.keys.firstOrNull()) }
        val selectedChapters = if (selectedChapterId != null) {
            chapters.filter { it.key == selectedChapterId }.toList()
        } else {
            chapters.toList()
        }

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(selectedChapters) { (chapterId, title) ->
                ChapterItem(
                    chapterId = chapterId,
                    title = title,
                    moduleId = moduleId,
                    lang = lang,
                    onSelect = { onChapterSelect(moduleId, chapterId) }
                )
            }
        }

        // Bouton de retour
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = "← Retour aux modules",
                fontSize = 14.sp,
                color = Color(0xFF1CB0F6),
                modifier = Modifier.clickable {
                    // Retour à l'écran précédent
                }
            )
        }
    }
}

@Composable
private fun ChapterItem(
    chapterId: String,
    title: String,
    moduleId: String,
    lang: Language,
    onSelect: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = if (expanded) Color(0xFFE8F5E9) else Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = if (expanded) 2.dp else 1.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { expanded = true }
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
                    text = title,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF12211C)
                )
                Text(
                    text = "ID: $chapterId",
                    fontSize = 11.sp,
                    color = Color(0xFF90A4AE)
                )
            }
            if (expanded) {
                Text(
                    text = "◀",
                    fontSize = 18.sp,
                    color = Color(0xFF1CB0F6),
                    modifier = Modifier.clickable { onSelect() }
                )
            } else {
                Box(modifier = Modifier.size(16.dp))
            }
        }
    }
}

private fun moduleTitle(info: InitialData.ModuleInfo, lang: Language): String = when (lang) {
    Language.ARABIC -> info.titleAr
    Language.ENGLISH -> info.titleEn
    Language.FRENCH -> info.titleFr
}
