package com.example.data.initial

import com.example.data.local.entity.ExerciseEntity
import com.example.data.local.entity.MedicalTermEntity
import com.example.data.terms.AnatomieTerms
import com.example.data.terms.AnatomiePathologiqueTerms
import com.example.data.terms.AnglaisMedicalTerms
import com.example.data.terms.BiochimieTerms
import com.example.data.terms.BiophysiqueTerms
import com.example.data.terms.CytologieTerms
import com.example.data.terms.EmbryologieTerms
import com.example.data.terms.GenetiqueTerms
import com.example.data.terms.HistologieTerms
import com.example.data.terms.InformatiqueMedicaleTerms
import com.example.data.terms.MicrobiologieTerms
import com.example.data.terms.PharmacologieTerms
import com.example.data.terms.PhysiologieTerms
import com.example.data.terms.SemiologieMedicaleTerms
import com.example.data.terms.TerminologieMedicaleTerms

/**
 * Point d'entrée unique pour les données de l'application.
 *
 * Les termes sont organisés dans `data/terms/` — un fichier par module,
 * consolidé depuis les anciens fichiers fragmentés de `data/initial/`.
 *
 * Total : 4 512 termes répartis sur 15 modules (ids 1..4512, uniques globalement).
 */
object InitialData {

    /** Tous les termes médicaux, groupés par module. */
    val terms: List<MedicalTermEntity> =
        AnatomieTerms.terms +
            PhysiologieTerms.terms +
            BiochimieTerms.terms +
            HistologieTerms.terms +
            BiophysiqueTerms.terms +
            GenetiqueTerms.terms +
            TerminologieMedicaleTerms.terms +
            AnglaisMedicalTerms.terms +
            CytologieTerms.terms +
            InformatiqueMedicaleTerms.terms +
            EmbryologieTerms.terms +
            MicrobiologieTerms.terms +
            PharmacologieTerms.terms +
            SemiologieMedicaleTerms.terms +
            AnatomiePathologiqueTerms.terms

    /** 6 niveaux progressifs : Vocabulaire → Collocations → Phrases Simples → Phrases Complexes → Paragraphes → Cas Cliniques. */
    val exercises = LearningExercisesData.exercises

    /** Index module → termes (correspondance exacte sur le champ `module`). */
    private val termsByModule: Map<String, List<MedicalTermEntity>> = terms.groupBy { it.module }

    /** Termes d'un module, par correspondance exacte sur le champ `module`. */
    fun termsOfModule(moduleTitle: String): List<MedicalTermEntity> =
        termsByModule[moduleTitle].orEmpty()

    /** Chapitres réels et distincts présents dans le seed pour le module donné. */
    fun chaptersOfModule(moduleTitle: String): List<String> =
        termsOfModule(moduleTitle).map { it.chapter }.distinct().sorted()

    data class ModuleInfo(
        val id: String,
        val icon: String,
        val titleFr: String,
        val titleEn: String,
        val titleAr: String,
        val colorHex: Long,
        val chaptersCount: Int,
        val estimatedSizeMb: Double,
        val progress: Float
    )

    /** Les 15 modules PCM1, dans l'ordre d'affichage. */
    val modulesList = listOf(
        ModuleInfo("anat", "🦴", "Anatomie", "Anatomy", "علم التشريح البشري", 0xFF1B5E20, chaptersOfModule("Anatomie").size, OfflineContentSize.forModule("Anatomie"), 0f),
        ModuleInfo("physio", "❤️", "Physiologie", "Physiology", "علم وظائف الأعضاء", 0xFF00695C, chaptersOfModule("Physiologie").size, OfflineContentSize.forModule("Physiologie"), 0f),
        ModuleInfo("biochim", "🧬", "Biochimie", "Biochemistry", "الكيمياء الحيوية الطبية", 0xFF1565C0, chaptersOfModule("Biochimie").size, OfflineContentSize.forModule("Biochimie"), 0f),
        ModuleInfo("histo", "🔬", "Histologie", "Histology", "علم الأنسجة العام", 0xFF6A1B9A, chaptersOfModule("Histologie").size, OfflineContentSize.forModule("Histologie"), 0f),
        ModuleInfo("biophys", "🧪", "Biophysique", "Biophysics", "الفيزياء الحيوية الطبية", 0xFFE65100, chaptersOfModule("Biophysique").size, OfflineContentSize.forModule("Biophysique"), 0f),
        ModuleInfo("genet", "🧬", "Génétique", "Medical Genetics", "علم الوراثة الطبية", 0xFF004D40, chaptersOfModule("Génétique").size, OfflineContentSize.forModule("Génétique"), 0f),
        ModuleInfo("termino", "📙", "Terminologie Médicale", "Medical Terminology", "المصطلحات الطبية اليونانية واللاتينية", 0xFFE65100, chaptersOfModule("Terminologie Médicale").size, OfflineContentSize.forModule("Terminologie Médicale"), 0f),
        ModuleInfo("clinical_en", "🩺", "Anglais Médical", "Clinical Medical English", "الإنجليزية الطبية السريرية", 0xFF00695C, chaptersOfModule("Anglais Médical").size, OfflineContentSize.forModule("Anglais Médical"), 0f),
        ModuleInfo("cytol", "🧫", "Cytologie", "Cell Biology & Cytology", "علم الأحياء الخلوية", 0xFF00796B, chaptersOfModule("Cytologie").size, OfflineContentSize.forModule("Cytologie"), 0f),
        ModuleInfo("info_med", "💻", "Informatique Médicale", "Medical Informatics & Biostats", "المعلوماتية الطبية والإحصاء الحيوي", 0xFF1976D2, chaptersOfModule("Informatique Médicale").size, OfflineContentSize.forModule("Informatique Médicale"), 0f),
        ModuleInfo("embryo", "👶", "Embryologie", "Medical Embryology", "علم الأجنة البشرية", 0xFFC2185B, chaptersOfModule("Embryologie").size, OfflineContentSize.forModule("Embryologie"), 0f),
        ModuleInfo("microbio", "🦠", "Microbiologie", "Medical Microbiology", "علم الأحياء الدقيقة الطبية", 0xFF00897B, chaptersOfModule("Microbiologie").size, OfflineContentSize.forModule("Microbiologie"), 0f),
        ModuleInfo("pharmaco", "💊", "Pharmacologie", "Medical Pharmacology", "علم الأدوية والعقاقير الطبية", 0xFF7B1FA2, chaptersOfModule("Pharmacologie").size, OfflineContentSize.forModule("Pharmacologie"), 0f),
        ModuleInfo("semio", "🩺", "Sémiologie Médicale", "Clinical Semiology", "علم الأعراض والتشخيص السريري", 0xFF0288D1, chaptersOfModule("Sémiologie Médicale").size, OfflineContentSize.forModule("Sémiologie Médicale"), 0f),
        ModuleInfo("anapath", "🫀", "Anatomie Pathologique", "Anatomic Pathology", "علم الأمراض التشريحي", 0xFFC2185B, chaptersOfModule("Anatomie Pathologique").size, OfflineContentSize.forModule("Anatomie Pathologique"), 0f)
    )
}
