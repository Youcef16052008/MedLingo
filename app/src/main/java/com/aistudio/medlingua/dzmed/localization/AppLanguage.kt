package com.aistudio.medlingua.dzmed.localization

enum class Language(val code: String, val flag: String, val displayName: String) {
    ARABIC("ar", "🇸🇦", "العربية"),
    FRENCH("fr", "🇫🇷", "Français"),
    ENGLISH("en", "🇬🇧", "English");

    val isRtl: Boolean get() = this == ARABIC

    companion object {
        fun fromCode(code: String): Language = when (code.lowercase()) {
            "ar" -> ARABIC
            "en" -> ENGLISH
            else -> FRENCH
        }
    }

    /**
     * Single source of truth to turn an app language into a platform locale, for the
     * few APIs that need a java.util.Locale (month names, TTS, ...).
     */
    fun toLocale(): java.util.Locale = when (this) {
        ARABIC -> java.util.Locale("ar")
        FRENCH -> java.util.Locale.FRENCH
        ENGLISH -> java.util.Locale.ENGLISH
    }
}

/** Alias kept as a named helper so call sites read as intent, not conversion. */
object LanguageLocale {
    fun forLanguage(language: Language): java.util.Locale = language.toLocale()
}

object Strings {
    private val translations = mapOf(
        "app_title" to mapOf(
            "ar" to "MedLingua DZ",
            "fr" to "MedLingua DZ",
            "en" to "MedLingua DZ"
        ),
        "app_subtitle" to mapOf(
            "ar" to "الإنجليزية الطبية الأكاديمية والسريرية لطلبة الطب",
            "fr" to "L'anglais médical académique et clinique pour les carabins",
            "en" to "Academic & Clinical Medical English for Medical Students"
        ),
        "nav_home" to mapOf("ar" to "الرئيسية", "fr" to "Accueil", "en" to "Home"),
        "nav_modules" to mapOf("ar" to "الوحدات", "fr" to "Modules", "en" to "Modules"),
        "nav_flashcards" to mapOf("ar" to "البطاقات", "fr" to "Flashcards", "en" to "Flashcards"),
        "nav_quiz" to mapOf("ar" to "التعلم المرحلي", "fr" to "Niveaux", "en" to "Levels"),
        "nav_profile" to mapOf("ar" to "الملف & الإحصائيات", "fr" to "Profil & Progrès", "en" to "Profile & Stats"),
        "greeting" to mapOf("ar" to "مرحباً زميلنا الطبيب 👋", "fr" to "Bonjour futur(e) Dr 👋", "en" to "Welcome future Dr 👋"),
        "online" to mapOf("ar" to "متصل", "fr" to "En ligne", "en" to "Online"),
        "offline" to mapOf("ar" to "بدون إنترنت", "fr" to "Hors-ligne", "en" to "Offline"),
        "offline_ready" to mapOf("ar" to "جاهز بدون إنترنت", "fr" to "Mode hors-ligne actif", "en" to "Offline mode ready"),
        "streak_days" to mapOf("ar" to "أيام متتالية", "fr" to "Jours d'affilée", "en" to "Day Streak"),
        "learned_terms" to mapOf("ar" to "مصطلحات مدروسة", "fr" to "Termes maîtrisés", "en" to "Mastered Terms"),
        "total_points" to mapOf("ar" to "مجموع النقاط", "fr" to "Points totaux", "en" to "Total Points"),
        "accuracy" to mapOf("ar" to "نسبة الدقة", "fr" to "Taux de réussite", "en" to "Accuracy"),
        "daily_review_title" to mapOf("ar" to "📋 المراجعة اليومية المجدولة", "fr" to "📋 Révision espacée du jour", "en" to "📋 Scheduled Daily Review"),
        "flashcards_due" to mapOf("ar" to "بطاقات مستحقة للمراجعة الآن", "fr" to "flashcards prêtes pour révision", "en" to "flashcards due for review"),
        "review_now" to mapOf("ar" to "بدء المراجعة", "fr" to "Réviser", "en" to "Review Now"),
        "my_modules_title" to mapOf("ar" to "📚 مقررات السنة الأولى طب (PCEM1)", "fr" to "📚 Modules Médicaux (PCEM1)", "en" to "📚 Medical Curriculum (Year 1)"),
        "tap_to_flip" to mapOf("ar" to "👆 إضغط لقلب البطاقة ومعاينة الشرح", "fr" to "👆 Tap pour retourner la carte", "en" to "👆 Tap to flip card"),
        "french_term" to mapOf("ar" to "المصطلح بالفرنسية", "fr" to "Terme en français", "en" to "French Term"),
        "english_term" to mapOf("ar" to "المصطلح بالإنجليزية", "fr" to "Terme en anglais", "en" to "English Term"),
        "classical_arabic" to mapOf("ar" to "الترجمة الطبية المعتمدة", "fr" to "Arabe médical officiel", "en" to "Standard Medical Arabic"),
        "etymology" to mapOf("ar" to "🏛️ الأصل اللغوي والاشتقاق", "fr" to "🏛️ Étymologie & Racines", "en" to "🏛️ Etymology & Roots"),
        "clinical_pearl" to mapOf("ar" to "🩺 فائدة سريرية هامة (Clinical Pearl)", "fr" to "🩺 Perle clinique & Pathologie", "en" to "🩺 High-Yield Clinical Pearl"),
        "mnemonic_tip" to mapOf("ar" to "💡 وسيلة تذكّر طبية (Mnemonic)", "fr" to "💡 Moyen mnémotechnique", "en" to "💡 Medical Mnemonic"),
        "how_well_remembered" to mapOf("ar" to "ما مدى دقة استحضارك للمصطلح السريري؟", "fr" to "Évaluez votre rappel mémoriel :", "en" to "Assess your clinical recall accuracy:"),
        "rating_forgot" to mapOf("ar" to "نسيت ❌", "fr" to "Oublié ❌", "en" to "Forgot ❌"),
        "rating_hard" to mapOf("ar" to "صعب ⚠️", "fr" to "Difficile ⚠️", "en" to "Hard ⚠️"),
        "rating_medium" to mapOf("ar" to "متوسط ⏳", "fr" to "Moyen ⏳", "en" to "Good ⏳"),
        "rating_easy" to mapOf("ar" to "جيد ✅", "fr" to "Bien ✅", "en" to "Easy ✅"),
        "rating_perfect" to mapOf("ar" to "ممتاز! ⭐", "fr" to "Parfait! ⭐", "en" to "Mastered! ⭐"),
        "next_review" to mapOf("ar" to "المراجعة القادمة بعد:", "fr" to "Prochaine révision dans :", "en" to "Next review scheduled in:"),
        "days" to mapOf("ar" to "أيام", "fr" to "jours", "en" to "days"),
        "question" to mapOf("ar" to "السؤال", "fr" to "Question", "en" to "Question"),
        "no_exercises" to mapOf("ar" to "لا توجد تمارين لهذا الدرس بعد", "fr" to "Aucun exercice pour cette leçon pour le moment", "en" to "No exercises for this lesson yet"),
        "next_question" to mapOf("ar" to "السؤال التالي ←", "fr" to "Question suivante →", "en" to "Next Question →"),
        "finish_quiz" to mapOf("ar" to "إنهاء الاختبار وعرض النتيجة", "fr" to "Terminer et voir le bilan", "en" to "Finish & View Results"),
        "quiz_score" to mapOf("ar" to "النتيجة الإجمالية", "fr" to "Score obtenu", "en" to "Score Achieved"),
        "good_job" to mapOf("ar" to "أحسنت! إتقان سريري ممتاز 🩺", "fr" to "Félicitations confrère ! 🩺", "en" to "Outstanding Clinical Recall! 🩺"),
        "play_again" to mapOf("ar" to "إعادة الاختبار", "fr" to "Recommencer l'évaluation", "en" to "Retake Assessment"),
        "search_hint" to mapOf("ar" to "بحث في المصطلحات (الإنجليزية، الفرنسية، العربية)...", "fr" to "Rechercher un terme (EN, FR, AR)...", "en" to "Search medical terms (EN, FR, AR)..."),
        "listen_pronunciation" to mapOf("ar" to "استمع للنطق الصوتي الطبي", "fr" to "Écouter la prononciation", "en" to "Listen to medical pronunciation"),
        "download_all" to mapOf("ar" to "تحميل للمطالعة بدون إنترنت", "fr" to "Télécharger hors-ligne", "en" to "Download for offline use"),
        "downloaded" to mapOf("ar" to "تم التحميل محلياً", "fr" to "Téléchargé (local)", "en" to "Downloaded (local)"),
        "filter_module" to mapOf("ar" to "تصفية حسب المقرر", "fr" to "Filtrer par module", "en" to "Filter by module"),
        "all_modules" to mapOf("ar" to "جميع المقررات", "fr" to "Tous les modules", "en" to "All modules"),
        "match_pairs_instruction" to mapOf(
            "ar" to "اربط المصطلح الطبي الإنجليزي بالتسمية الفرنسية المعتمدة",
            "fr" to "Associez chaque terme médical anglais à sa nomenclature officielle",
            "en" to "Match each English clinical term with its anatomical nomenclature"
        ),
        "fill_blank_instruction" to mapOf(
            "ar" to "أكمل الفراغ بالمصطلح التشريحي الإنجليزي الدقيق",
            "fr" to "Complétez avec le terme anatomique anglais exact",
            "en" to "Fill in the precise English anatomical term"
        ),
        "clinical_explanation" to mapOf(
            "ar" to "🩺 الشرح والتعليل السريري الأكاديمي:",
            "fr" to "🩺 Explication & Justification Clinique :",
            "en" to "🩺 Academic Clinical Rationale & Explanation:"
        ),
        // Gamification & League Strings
        "league_title" to mapOf(
            "ar" to "🏆 Liga {tier}",
            "fr" to "🏆 Ligue {tier}",
            "en" to "🏆 League {tier}"
        ),
        "close" to mapOf("ar" to "إغلاق", "fr" to "Fermer", "en" to "Close"),
        "gems" to mapOf("ar" to "💎 جواهر", "fr" to "💎 Gemmes", "en" to "💎 Gems"),
        "streak" to mapOf("ar" to "🔥 سلسلة", "fr" to "🔥 Série", "en" to "🔥 Streak"),
        "weekly_xp" to mapOf("ar" to "⭐ XP أسبوعي", "fr" to "⭐ XP Hebdo", "en" to "⭐ Weekly XP"),
        "super_medlingo" to mapOf(
            "ar" to "⭐ MedLinguo الفائق",
            "fr" to "⭐ Super MedLingua",
            "en" to "⭐ Super MedLingua"
        ),
        "buy_super" to mapOf(
            "ar" to "اشترِ MedLinguo الفائق",
            "fr" to "Acheter Super MedLingua",
            "en" to "Buy Super MedLingua"
        ),
        "promotion" to mapOf("ar" to "🎉 ترقية!", "fr" to "🎉 Promotion !", "en" to "🎉 Promotion!"),
        "demotion" to mapOf("ar" to "⬇️ هبوط", "fr" to "⬇️ Relégation", "en" to "⬇️ Demotion"),
        "no_change" to mapOf("ar" to "بقاء", "fr" to "Maintien", "en" to "No change"),
        "rank" to mapOf("ar" to "الترتيب", "fr" to "Rang", "en" to "Rank"),
        "top_10" to mapOf("ar" to "أفضل 10", "fr" to "Top 10", "en" to "Top 10"),
        "bottom_5" to mapOf("ar" to "أسفل 5", "fr" to "Bottom 5", "en" to "Bottom 5"),
        "perfect_bonus" to mapOf(
            "ar" to "✨ مكافأة الكمال",
            "fr" to "✨ Bonus Parfait",
            "en" to "✨ Perfect Bonus"
        ),
        // 6 Progressive Levels System Strings
        "learning_pyramid_title" to mapOf(
            "ar" to "🧠 هرم التعلّم الطبي التدريجي (6 مستويات)",
            "fr" to "🧠 Pyramide d'Apprentissage (6 Niveaux)",
            "en" to "🧠 Progressive Learning Pyramid (6 Levels)"
        ),
        "learning_pyramid_desc" to mapOf(
            "ar" to "يتطلب فتح كل مستوى تحقيق نسبة نجاح 70% على الأقل في المستوى السابق 🔒",
            "fr" to "Chaque niveau se débloque après 70% de réussite au niveau précédent 🔒",
            "en" to "Each level unlocks upon achieving at least 70% in the preceding level 🔒"
        ),
        "level_locked_info" to mapOf(
            "ar" to "🔒 مغلق (يتطلب 70% في المستوى السابق)",
            "fr" to "🔒 Verrouillé (70% requis au niveau précédent)",
            "en" to "🔒 Locked (Requires 70% in previous level)"
        ),
        "level_unlocked_ready" to mapOf(
            "ar" to "🔓 متاح للتدريب الآن",
            "fr" to "🔓 Débloqué — Prêt pour l'entraînement",
            "en" to "🔓 Unlocked — Ready for practice"
        ),
        "start_training" to mapOf(
            "ar" to "بدء التدريب",
            "fr" to "S'entraîner",
            "en" to "Start Practice"
        ),
        "back_to_pyramid" to mapOf(
            "ar" to "العودة إلى هرم المستويات",
            "fr" to "Retour à la Pyramide",
            "en" to "Back to Pyramid"
        ),
        "congrats_level_passed" to mapOf(
            "ar" to "🎉 تهانينا! اجتزت هذا المستوى بنجاح",
            "fr" to "🎉 Félicitations ! Niveau validé avec succès",
            "en" to "🎉 Congratulations! Level completed successfully"
        ),
        "next_level_unlocked_msg" to mapOf(
            "ar" to "تم فتح المستوى التالي! واصل التقدم نحو التمكن السريري التام 🩺",
            "fr" to "Le niveau suivant est désormais débloqué ! Poursuivez votre ascension 🩺",
            "en" to "Next level is now unlocked! Keep advancing toward clinical mastery 🩺"
        ),
        "score_needed_retry" to mapOf(
            "ar" to "حصلت على نسبة أقل من 70%. راجع المصطلحات وحاول مجدداً لفتح المستوى التالي!",
            "fr" to "Score inférieur à 70%. Révisez vos termes et réessayez pour débloquer la suite !",
            "en" to "Score below 70%. Review terms and retake the test to unlock the next level!"
        ),
        "reorder_instruction" to mapOf(
            "ar" to "رتب الكلمات لتكوين جملة طبية إنجليزية سليمة:",
            "fr" to "Ordonnez les mots pour former la phrase médicale correcte :",
            "en" to "Arrange the words to build the correct clinical sentence:"
        ),
        "clinical_case_title" to mapOf(
            "ar" to "🏥 الحالة السريرية:",
            "fr" to "🏥 Cas Clinique & Dossier Patient :",
            "en" to "🏥 Clinical Case Vignette:"
        ),
        "reading_passage_title" to mapOf(
            "ar" to "📄 النص الطبي التحليلي:",
            "fr" to "📄 Extrait Médical & Analyse :",
            "en" to "📄 Medical Passage & Analysis:"
        ),
        "clear_words" to mapOf(
            "ar" to "مسح الكلمات",
            "fr" to "Effacer",
            "en" to "Clear"
        ),
        "check_sentence" to mapOf(
            "ar" to "تحقق من الجملة",
            "fr" to "Vérifier la phrase",
            "en" to "Verify Sentence"
        ),
        // Profil / stats screen
        "medical_level_line" to mapOf(
            "ar" to "المستوى الطبي: {level}",
            "fr" to "Niveau Médical : {level}",
            "en" to "Medical Level: {level}"
        ),
        "cat_title" to mapOf(
            "ar" to "التشخيص التكيفي (CAT)",
            "fr" to "DIAGNOSTIC ADAPTATIF (CAT)",
            "en" to "ADAPTIVE DIAGNOSTIC (CAT)"
        ),
        "cat_eval" to mapOf(
            "ar" to "تقييم مستواي الطبي",
            "fr" to "Évaluer mon niveau médical",
            "en" to "Evaluate my medical level"
        ),
        "cat_desc" to mapOf(
            "ar" to "اختبار مخصص من 5 إلى 10 أسئلة",
            "fr" to "Test personnalisé de 5 à 10 questions",
            "en" to "Personalized test of 5 to 10 questions"
        ),
        "cat_recommended" to mapOf(
            "ar" to "موصى به: {module} • إعادة",
            "fr" to "Recommandé : {module} • Refaire",
            "en" to "Recommended: {module} • Retry"
        ),
        "cat_level_result" to mapOf(
            "ar" to "المستوى: {level} ({score}/100)",
            "fr" to "Niveau : {level} ({score}/100)",
            "en" to "Level: {level} ({score}/100)"
        ),
        "start_label" to mapOf("ar" to "ابدأ", "fr" to "Démarrer", "en" to "Start"),
        "retry_label" to mapOf("ar" to "إعادة", "fr" to "Refaire", "en" to "Retry"),
        "app_language_title" to mapOf(
            "ar" to "🌐 لغة التطبيق",
            "fr" to "🌐 Langue de l'application",
            "en" to "🌐 App language"
        ),
        "app_language_list" to mapOf(
            "ar" to "العربية • الفرنسية • الإنجليزية",
            "fr" to "Arabe • Français • Anglais",
            "en" to "Arabic • French • English"
        ),
        "offline_mode_title" to mapOf(
            "ar" to "📴 وضع عدم الاتصال (Hors-ligne)",
            "fr" to "📴 Mode Hors-Ligne (Offline)",
            "en" to "📴 Offline Mode"
        ),
        "local_content" to mapOf(
            "ar" to "المحتوى المحلي: {size} م.ب ({count} وحدات)",
            "fr" to "Contenu local : {size} MB ({count} modules)",
            "en" to "Local content: {size} MB ({count} modules)"
        ),
        "sqlite_active" to mapOf(
            "ar" to "SQLite نشط ✅",
            "fr" to "SQLite Actif ✅",
            "en" to "SQLite Active ✅"
        ),
        "offline_content_desc" to mapOf(
            "ar" to "المحتوى لكل وحدة (مصطلحات + تمارين)، متوفر مسبقاً دون اتصال:",
            "fr" to "Contenu par module (termes + exercices), déjà disponible hors-ligne :",
            "en" to "Content per module (terms + exercises), already available offline:"
        ),
        "download" to mapOf("ar" to "تحميل", "fr" to "Télécharger", "en" to "Download"),
        "notifications_card_title" to mapOf(
            "ar" to "🔔 الإشعارات والتذكيرات",
            "fr" to "🔔 Notifications & Rappels",
            "en" to "🔔 Notifications & Reminders"
        ),
        "notifications_card_desc" to mapOf(
            "ar" to "تذكير يومي بالمراجعة المتباعدة (SM-2)",
            "fr" to "Rappels quotidiens de répétition espacée",
            "en" to "Daily spaced repetition reminders"
        ),
        "active_label" to mapOf("ar" to "نشط ✅", "fr" to "Actif ✅", "en" to "Active ✅"),
        "enable_notifications" to mapOf(
            "ar" to "تفعيل 🔔",
            "fr" to "Activer 🔔",
            "en" to "Enable 🔔"
        ),
        "sm2_reminder_title" to mapOf(
            "ar" to "مراجعة البطاقات المستحقة (SM-2)",
            "fr" to "Révision espacée quotidienne",
            "en" to "Daily spaced review"
        ),
        "sm2_reminder_desc" to mapOf(
            "ar" to "تنبيه عند حلول موعد تكرار المصطلحات",
            "fr" to "Alerte quand des flashcards arrivent à échéance",
            "en" to "Alert when flashcards come due"
        ),
        "streak_reminder_title" to mapOf(
            "ar" to "حماية سلسلة الأيام (Streak)",
            "fr" to "Alerte de maintien de série",
            "en" to "Streak reminder"
        ),
        "streak_reminder_desc" to mapOf(
            "ar" to "تذكير للحفاظ على سلسلتك ({n} أيام)",
            "fr" to "Rappel pour préserver votre série de {n} jours",
            "en" to "Reminder to keep your {n}-day streak"
        ),
        "pearl_reminder_title" to mapOf(
            "ar" to "فائدة سريرية يومية (Clinical Pearl)",
            "fr" to "Perle clinique quotidienne",
            "en" to "Daily clinical pearl"
        ),
        "pearl_reminder_desc" to mapOf(
            "ar" to "مصطلح وملاحظة طبية ذات أهمية سريرية",
            "fr" to "Terme et mnémotechnique médicale à haut rendement",
            "en" to "High-yield term and medical mnemonic"
        ),
        "reminder_time_label" to mapOf(
            "ar" to "⏰ توقيت التذكير اليومي المفضل :",
            "fr" to "⏰ Heure de révision programmée :",
            "en" to "⏰ Scheduled review time:"
        ),
        "time_morning" to mapOf("ar" to "08:00 (صباحاً)", "fr" to "08:00 (Matin)", "en" to "08:00 (Morning)"),
        "time_noon" to mapOf("ar" to "13:00 (ظهراً)", "fr" to "13:00 (Midi)", "en" to "13:00 (Noon)"),
        "time_evening" to mapOf("ar" to "20:00 (مساءً)", "fr" to "20:00 (Soir)", "en" to "20:00 (Evening)"),
        "test_notif_label" to mapOf(
            "ar" to "🧪 تجربة إشعار فوري على جهازك الآن :",
            "fr" to "🧪 Tester l'envoi d'une notification :",
            "en" to "🧪 Test sending a notification:"
        ),
        "notif_review" to mapOf("ar" to "📋 مراجعة مستحقة", "fr" to "📋 Révision due", "en" to "📋 Review due"),
        "notif_streak" to mapOf(
            "ar" to "🔥 السلسلة ({n} ي)",
            "fr" to "🔥 Série ({n}j)",
            "en" to "🔥 Streak ({n}d)"
        ),
        "notif_pearl" to mapOf("ar" to "🩺 فائدة سريرية", "fr" to "🩺 Perle clinique", "en" to "🩺 Clinical pearl"),
        "notif_level" to mapOf("ar" to "🏆 المستوى {n}", "fr" to "🏆 Niveau {n}", "en" to "🏆 Level {n}"),
        "learning_report_title" to mapOf(
            "ar" to "📊 تقرير التعلم",
            "fr" to "📊 Bilan d'apprentissage",
            "en" to "📊 Learning report"
        ),
        "metric_questions" to mapOf(
            "ar" to "أسئلة تمت الإجابة عنها",
            "fr" to "Questions Répondues",
            "en" to "Questions Answered"
        ),
        "metric_quizzes" to mapOf("ar" to "اختبارات مكتملة", "fr" to "Quiz Complétés", "en" to "Quizzes Completed"),
        "metric_correct" to mapOf("ar" to "إجابات صحيحة", "fr" to "Réponses Correctes", "en" to "Correct Answers"),
        "metric_accuracy" to mapOf("ar" to "الدقة الإجمالية", "fr" to "Précision Globale", "en" to "Overall Accuracy"),
        // === Duolingo Lot 2 : onglets, Parcours, Révision, Récompenses, Trophées ===
        "nav_path" to mapOf("ar" to "المسار", "fr" to "Parcours", "en" to "Path"),
        "nav_practice" to mapOf("ar" to "المراجعة", "fr" to "Révision", "en" to "Practice"),
        "nav_leagues" to mapOf("ar" to "الدوريات", "fr" to "Ligues", "en" to "Leagues"),
        "greet_morning" to mapOf("ar" to "صباح الخير", "fr" to "Bonjour", "en" to "Good morning"),
        "greet_afternoon" to mapOf("ar" to "مساء الخير", "fr" to "Bon après-midi", "en" to "Good afternoon"),
        "greet_evening" to mapOf("ar" to "مساء الخير", "fr" to "Bonsoir", "en" to "Good evening"),
        "path_title" to mapOf("ar" to "المسار", "fr" to "Parcours", "en" to "Path"),
        "path_sub" to mapOf(
            "ar" to "١٥ وحدة · دروس من ٣ مصطلحات + مراجعات",
            "fr" to "15 unités · leçons de 3 termes + révisions",
            "en" to "15 units · 3-term lessons + reviews"
        ),
        "lesson_lbl" to mapOf("ar" to "درس", "fr" to "Leçon", "en" to "Lesson"),
        "lesson_one_lbl" to mapOf("ar" to "درس", "fr" to "leçon", "en" to "lesson"),
        "review_one_lbl" to mapOf("ar" to "مراجعة", "fr" to "révision", "en" to "review"),
        "term_one_lbl" to mapOf("ar" to "مصطلح", "fr" to "terme", "en" to "term"),
        "review_lbl" to mapOf("ar" to "مراجعة", "fr" to "Révision", "en" to "Review"),
        "reviews_lbl" to mapOf("ar" to "مراجعات", "fr" to "révisions", "en" to "reviews"),
        "review_required_lbl" to mapOf(
            "ar" to "إلزامية",
            "fr" to "obligatoire",
            "en" to "required"
        ),
        "review_optional_lbl" to mapOf(
            "ar" to "اختيارية",
            "fr" to "facultative",
            "en" to "optional"
        ),
        "terms_lbl" to mapOf("ar" to "مصطلحات", "fr" to "termes", "en" to "terms"),
        "average_lbl" to mapOf("ar" to "المعدل", "fr" to "moyenne", "en" to "average"),
        "chapter_locked_hint" to mapOf(
            "ar" to "أكمل الفصل السابق بمعدل ٥٠٪ على الأقل",
            "fr" to "Termine le chapitre précédent avec 50 % de moyenne",
            "en" to "Finish the previous chapter with a 50% average"
        ),
        "lessons_lbl" to mapOf("ar" to "دروس", "fr" to "leçons", "en" to "lessons"),
        "lesson_locked_lbl" to mapOf("ar" to "مقفل", "fr" to "verrouillée", "en" to "locked"),
        "lesson_available_lbl" to mapOf("ar" to "متاح", "fr" to "disponible", "en" to "available"),
        "chapters_lbl" to mapOf("ar" to "فصول", "fr" to "chapitres", "en" to "chapters"),
        "chapter_one_lbl" to mapOf("ar" to "فصل", "fr" to "chapitre", "en" to "chapter"),
        "path_locked_text" to mapOf(
            "ar" to "أكمل الدرس السابق أولاً",
            "fr" to "Termine d'abord la leçon précédente",
            "en" to "Finish the previous lesson first"
        ),
        "continue_lbl" to mapOf("ar" to "متابعة", "fr" to "Continuer", "en" to "Continue"),
        "module_lbl" to mapOf("ar" to "الوحدة", "fr" to "Module", "en" to "Module"),
        "change_lbl" to mapOf("ar" to "تغيير", "fr" to "Changer", "en" to "Change"),
        "search_chapter" to mapOf(
            "ar" to "ابحث عن فصل...",
            "fr" to "Rechercher un chapitre...",
            "en" to "Search a chapter..."
        ),
        "choose_chapter_hint" to mapOf(
            "ar" to "اختر فصلاً لبدء دروسه",
            "fr" to "Choisis un chapitre",
            "en" to "Pick a chapter"
        ),
        "all_chapters" to mapOf("ar" to "كل الفصول", "fr" to "Tous les chapitres", "en" to "All chapters"),
        "no_chapter_match" to mapOf(
            "ar" to "لا يوجد فصل مطابق",
            "fr" to "Aucun chapitre trouvé",
            "en" to "No chapter found"
        ),
        "next_chapter_lbl" to mapOf("ar" to "الفصل التالي", "fr" to "Chapitre suivant", "en" to "Next chapter"),
        "chapter_lessons_lbl" to mapOf("ar" to "دروس الفصل", "fr" to "Leçons du chapitre", "en" to "Chapter lessons"),
        "path_done_all" to mapOf(
            "ar" to "أكملت كل الدروس 🎉",
            "fr" to "Tous les chapitres sont terminés 🎉",
            "en" to "All chapters complete 🎉"
        ),
        "stat_xp" to mapOf("ar" to "XP", "fr" to "XP", "en" to "XP"),
        "stat_streak" to mapOf("ar" to "يوم متتالٍ", "fr" to "Série", "en" to "Streak"),
        "stat_gems" to mapOf("ar" to "جواهر", "fr" to "Gemmes", "en" to "Gems"),
        "cards_due" to mapOf("ar" to "بطاقة مستحقة", "fr" to "cartes dues", "en" to "cards due"),
        "freeze_buy" to mapOf("ar" to "تجميد", "fr" to "Congeler", "en" to "Freeze"),
        "reward_title" to mapOf("ar" to "المكافآت", "fr" to "RÉCOMPENSES", "en" to "REWARDS"),
        "reward_goal" to mapOf("ar" to "هدف اليوم", "fr" to "Objectif du jour", "en" to "Daily goal"),
        "reward_trophy" to mapOf("ar" to "trophy جديد", "fr" to "Nouveau trophée !", "en" to "New trophy!"),
        "reward_chest" to mapOf("ar" to "صندوق", "fr" to "Caisse", "en" to "Chest"),
        "reward_continue" to mapOf("ar" to "متابعة", "fr" to "Continuer", "en" to "Continue"),
        "reward_open_chest" to mapOf("ar" to "افتح الصندوق", "fr" to "Ouvrir la caisse", "en" to "Open the chest"),
        "medi_happy" to mapOf(
            "ar" to "✨ مستعد للتعلّم؟ خطوة كل مرة!",
            "fr" to "✨ Prêt à apprendre ? Un pas à la fois !",
            "en" to "✨ Ready to learn? One step at a time!"
        ),
        "medi_motivating" to mapOf(
            "ar" to "💪 نواصل، أنت تتقدّم!",
            "fr" to "💪 On continue, tu progresses !",
            "en" to "💪 Keep going, you're improving!"
        ),
        "medi_celebrating" to mapOf(
            "ar" to "🎉 أحسنت!",
            "fr" to "🎉 Belle performance !",
            "en" to "🎉 Great job!"
        ),
        "medi_frustrated" to mapOf(
            "ar" to "😅 نجتّد في المرة القادمة",
            "fr" to "😅 On retente à la prochaine !",
            "en" to "😅 Let's get it next time!"
        ),
        "practice_sub" to mapOf("ar" to "🏋️ اختر تدريبك", "fr" to "🏋️ Choisis ton entraînement", "en" to "🏋️ Choose your practice"),
        "practice_title" to mapOf("ar" to "المراجعة", "fr" to "Révision", "en" to "Practice"),
        "practice_due" to mapOf("ar" to "مراجعة اليوم", "fr" to "Révision du jour", "en" to "Review of the day"),
        "practice_due_desc" to mapOf(
            "ar" to "البطاقات التي حان وقتها حسب أولوية الاسترجاع",
            "fr" to "Les cartes qui arrivent à échéance, par ordre d'urgence.",
            "en" to "Cards coming due, ordered by urgency."
        ),
        "practice_quiz" to mapOf("ar" to "اختبار حسب المستوى", "fr" to "Quiz par niveau", "en" to "Quiz by level"),
        "practice_quiz_desc" to mapOf(
            "ar" to "التمارين التسعون المتدرجة من المستوى ١ إلى ٦",
            "fr" to "Les exercices progressifs, du niveau 1 au niveau 6.",
            "en" to "Progressive exercises, from level 1 to level 6."
        ),
        "practice_weak" to mapOf("ar" to "المصطلحات الضعيفة", "fr" to "Termes faibles", "en" to "Weak terms"),
        "practice_weak_desc" to mapOf(
            "ar" to "فقط البطاقات المتأخرة : هنا يهمّ الأمر",
            "fr" to "Uniquement les cartes en retard : c'est là que ça compte.",
            "en" to "Only overdue cards: this is where it counts."
        ),
        "practice_exam" to mapOf("ar" to "اختبار شامل", "fr" to "Examen blanc", "en" to "Mock exam"),
        "practice_exam_desc" to mapOf(
            "ar" to "أسئلة مختلطة على المستويات الستة",
            "fr" to "Questions mélangées sur les 6 niveaux.",
            "en" to "Mixed questions across the 6 levels."
        ),
        "goal_title" to mapOf("ar" to "الهدف اليومي", "fr" to "Objectif quotidien", "en" to "Daily goal"),
        "trophies_title" to mapOf("ar" to "الجوائز", "fr" to "Trophées", "en" to "Trophies"),
        "pearl_title" to mapOf("ar" to "لفائدة اليوم", "fr" to "Perle clinique du jour", "en" to "Clinical pearl of the day"),
        "pearl_lbl" to mapOf("ar" to "فائدة", "fr" to "Perle", "en" to "Pearl"),
        "trophy_first_lesson" to mapOf("ar" to "الدرس الأول", "fr" to "Première leçon", "en" to "First lesson"),
        "trophy_first_lesson_d" to mapOf(
            "ar" to "أكمل درسك الأول",
            "fr" to "Terminer sa première leçon",
            "en" to "Complete your first lesson"
        ),
        "trophy_first_perfect" to mapOf("ar" to "بدون خطأ", "fr" to "Sans faute", "en" to "Flawless"),
        "trophy_first_perfect_d" to mapOf("ar" to "١٠٠٪ في درس واحد", "fr" to "100 % sur une leçon", "en" to "100 % on a lesson"),
        "trophy_streak_7" to mapOf("ar" to "أسبوع من النار", "fr" to "Semaine de feu", "en" to "On fire week"),
        "trophy_streak_7_d" to mapOf("ar" to "٧ أيام متتالية", "fr" to "7 jours de série", "en" to "7 day streak"),
        "trophy_streak_30" to mapOf("ar" to "ثلاثون يوماً", "fr" to "Trente jours", "en" to "Thirty days"),
        "trophy_streak_30_d" to mapOf("ar" to "٣٠ يوماً متتالياً", "fr" to "30 jours de série", "en" to "30 day streak"),
        "trophy_xp_1000" to mapOf("ar" to "١٠٠٠ XP", "fr" to "1 000 XP", "en" to "1,000 XP"),
        "trophy_xp_1000_d" to mapOf("ar" to "اجمع ١٠٠٠ نقطة", "fr" to "Atteindre 1 000 XP", "en" to "Reach 1,000 XP"),
        "trophy_xp_5000" to mapOf("ar" to "٥٠٠٠ XP", "fr" to "5 000 XP", "en" to "5,000 XP"),
        "trophy_xp_5000_d" to mapOf("ar" to "اجمع ٥٠٠٠ نقطة", "fr" to "Atteindre 5 000 XP", "en" to "Reach 5,000 XP"),
        "trophy_cards_100" to mapOf("ar" to "مئة بطاقة", "fr" to "Cent cartes", "en" to "Hundred cards"),
        "trophy_cards_100_d" to mapOf("ar" to "راجع ١٠٠ بطاقة", "fr" to "Revoir 100 flashcards", "en" to "Review 100 flashcards"),
        "trophy_quizzes_10" to mapOf("ar" to "عشرة اختبارات", "fr" to "Dix quiz", "en" to "Ten quizzes"),
        "trophy_quizzes_10_d" to mapOf("ar" to "أكمل ١٠ اختبارات", "fr" to "Terminer 10 quiz", "en" to "Complete 10 quizzes"),
        "trophy_goal_hit_7" to mapOf("ar" to "هدف ×٧", "fr" to "Objectif ×7", "en" to "Goal ×7"),
        "trophy_goal_hit_7_d" to mapOf(
            "ar" to "٧ أيام بلغت فيها الهدف",
            "fr" to "7 jours d'objectif atteint",
            "en" to "7 days hitting the goal"
        ),
        "trophy_module_master" to mapOf("ar" to "خبير الوحدة", "fr" to "Maître de module", "en" to "Module master"),
        "trophy_module_master_d" to mapOf(
            "ar" to "٩٠٪ فأعلى في مستوى ما",
            "fr" to "≥ 90 % sur un niveau",
            "en" to "≥ 90 % on a level"
        ),
        "trophy_league_promoted" to mapOf("ar" to "ترقية", "fr" to "Promotion", "en" to "Promotion"),
        "trophy_league_promoted_d" to mapOf(
            "ar" to "غادر البرونزية",
            "fr" to "Quitter le Bronze",
            "en" to "Leave Bronze"
        ),
        "trophy_chests_5" to mapOf("ar" to "خمسة صناديق", "fr" to "Cinq caisses", "en" to "Five chests"),
        "trophy_chests_5_d" to mapOf("ar" to "افتح ٥ صناديق", "fr" to "Ouvrir 5 caisses", "en" to "Open 5 chests"),

        // === PHASE 3 #11 : i18n des 4 notifications ===
        // Avant, les corps/titres étaient des chaînes FR en dur dans
        // NotificationHelper — un utilisateur en arabe ou anglais recevait une
        // notification en français, alors que tout le reste de l'app suit sa
        // langue. {n} = paramètre remplacé à l'envoi (dueCount, streak, niveau).
        "notif_review_title" to mapOf(
            "ar" to "📋 {n} بطاقة جاهزة للمراجعة!",
            "fr" to "📋 {n} flashcards prêtes pour révision !",
            "en" to "📋 {n} flashcards ready for review!"
        ),
        "notif_review_body" to mapOf(
            "ar" to "جلسة التكرار المتباعد (SM-2) جاهزة. عزّز ذاكرتك السريرية.",
            "fr" to "Votre session de répétition espacée (SM-2) est prête. Consolidez votre mémoire clinique.",
            "en" to "Your spaced-repetition session (SM-2) is ready. Consolidate your clinical memory."
        ),
        "notif_review_big" to mapOf(
            "ar" to "وصلت {n} مصطلحات طبية إلى موعد التكرار المتباعد اليوم وفق خوارزمية SM-2.\n\nخصّص 3 دقائق لتقييم استرجاعك بالإنجليزية والفرنسية!",
            "fr" to "{n} termes médicaux sont arrivés à échéance de répétition espacée aujourd'hui selon l'algorithme SM-2.\n\nPrenez 3 minutes pour évaluer votre rappel en anglais et français !",
            "en" to "{n} medical terms are due for spaced repetition today per the SM-2 algorithm.\n\nTake 3 minutes to test your recall in English and French!"
        ),
        "notif_review_action" to mapOf(
            "ar" to "راجع الآن 📚",
            "fr" to "Réviser maintenant 📚",
            "en" to "Review now 📚"
        ),
        "notif_streak_title" to mapOf(
            "ar" to "🔥 لا تكسر سلسلتك المتواصلة لمدة {n} يومًا!",
            "fr" to "🔥 Ne brisez pas votre série de {n} jours !",
            "en" to "🔥 Don't break your {n}-day streak!"
        ),
        "notif_streak_body" to mapOf(
            "ar" to "أنت على وتيرة ممتازة! جلسة قصيرة اليوم تحافظ على انتظامك.",
            "fr" to "Vous êtes sur une excellente lancée ! Une courte session aujourd'hui maintient votre régularité.",
            "en" to "You're on a great run! A short session today keeps your consistency."
        ),
        "notif_streak_big" to mapOf(
            "ar" to "🔥 السلسلة الحالية: {n} يومًا متتاليًا من اللغة الطبية!\n\nكل مراجعة يومية تحوّل المفردات السلبية إلى رد فعل سريري نشط. واصل!",
            "fr" to "🔥 Série actuelle : {n} jours consécutifs d'anglais médical !\n\nChaque révision quotidienne transforme le vocabulaire passif en réflexe clinique actif. Gardez le cap confrère !",
            "en" to "🔥 Current streak: {n} consecutive days of medical English!\n\nDaily review turns passive vocabulary into active clinical reflexes. Keep it up!"
        ),
        "notif_pearl_title" to mapOf(
            "ar" to "🩺 فائدة سريرية: {fr} ({en})",
            "fr" to "🩺 Perle Clinique : {fr} ({en})",
            "en" to "🩺 Clinical Pearl: {fr} ({en})"
        ),
        "notif_pearl_big" to mapOf(
            "ar" to "{pearl}\n\n💡 تذكر: {mnemo}\n\n📖 الوحدة: {module} — {chapter}",
            "fr" to "{pearl}\n\n💡 Mnémotechnique : {mnemo}\n\n📖 Module : {module} — {chapter}",
            "en" to "{pearl}\n\n💡 Mnemonic: {mnemo}\n\n📖 Module: {module} — {chapter}"
        ),
        "notif_pearl_action" to mapOf(
            "ar" to "استكشف الوحدة 📖",
            "fr" to "Explorer le module 📖",
            "en" to "Explore module 📖"
        ),
        "notif_level_title" to mapOf(
            "ar" to "🏆 تم فتح المستوى {n}!",
            "fr" to "🏆 Niveau {n} débloqué !",
            "en" to "🏆 Level {n} unlocked!"
        ),
        "notif_level_body" to mapOf(
            "ar" to "تهانينا! لقد بلغت المستوى: {name}.",
            "fr" to "Félicitations ! Vous avez atteint le niveau : {name}.",
            "en" to "Congratulations! You have reached level: {name}."
        ),
        "notif_level_big" to mapOf(
            "ar" to "🏆 تهانينا يا دكتور المستقبل! لقد تجاوزت المستوى السابق بنجاح بدقة ≥70%.\n\nأصبح المستوى {n} ({name}) متاحًا الآن في هرم تعلّمك.",
            "fr" to "🏆 Félicitations futur Dr ! Vous venez de franchir avec succès le palier précédent avec ≥70% de précision.\n\nLe Niveau {n} ({name}) est maintenant accessible dans votre Pyramide d'apprentissage.",
            "en" to "🏆 Congratulations, future Dr! You cleared the previous tier with ≥70% accuracy.\n\nLevel {n} ({name}) is now available in your learning pyramid."
        ),

        // Noms et descriptions des 4 canaux (réglages Android → langue de l'app)
        "channel_reviews_name" to mapOf(
            "ar" to "تذكيرات المراجعة اليومية (SM-2)",
            "fr" to "Rappels de Révision Quotidienne (SM-2)",
            "en" to "Daily Review Reminders (SM-2)"
        ),
        "channel_reviews_desc" to mapOf(
            "ar" to "إشعارات للبطاقات الجاهزة للتكرار المتباعد",
            "fr" to "Notifications pour les flashcards prêtes pour la répétition espacée",
            "en" to "Notifications for flashcards ready for spaced repetition"
        ),
        "channel_streaks_name" to mapOf(
            "ar" to "الحفاظ على السلسلة (Streak)",
            "fr" to "Maintien de la Série (Streak)",
            "en" to "Streak Maintenance"
        ),
        "channel_streaks_desc" to mapOf(
            "ar" to "تنبيهات للحفاظ على سلسلة أيام الدراسة المتتالية",
            "fr" to "Alertes pour préserver votre série de jours d'étude consécutifs",
            "en" to "Alerts to preserve your consecutive study-day streak"
        ),
        "channel_pearls_name" to mapOf(
            "ar" to "الفائدة السريرية اليومية",
            "fr" to "Perle Clinique du Jour",
            "en" to "Daily Clinical Pearl"
        ),
        "channel_pearls_desc" to mapOf(
            "ar" to "مفاهيم سريرية وتذكرية طبية عالية المردود",
            "fr" to "Notions cliniques et mnémotechniques médicales à haut rendement",
            "en" to "High-yield clinical concepts and medical mnemonics"
        ),
        "channel_levels_name" to mapOf(
            "ar" to "فتح المستويات والإنجازات",
            "fr" to "Déblocage de Niveaux & Succès",
            "en" to "Level Unlocks & Achievements"
        ),
        "channel_levels_desc" to mapOf(
            "ar" to "الاحتفاء بالمراحل المتجاوزة في هرم التعلّم",
            "fr" to "Célébration des paliers franchis dans la pyramide d'apprentissage",
            "en" to "Celebrating tiers cleared in the learning pyramid"
        ),
// ---- Mode histoire (guide d'integration, phase 2 Android) ----
        "story_card_title" to mapOf("ar" to "جلسة اليوم", "fr" to "Ma séance d'aujourd'hui", "en" to "My session today"),
        "story_card_sub" to mapOf("ar" to "قصة · استماع · أسئلة", "fr" to "Histoire · écoute · questions", "en" to "Story · listening · questions"),
        "story_start" to mapOf("ar" to "ابدأ", "fr" to "Commencer", "en" to "Start"),
        "story_resume" to mapOf("ar" to "استأنف جلستي", "fr" to "Reprendre ma séance", "en" to "Resume my session"),
        "story_minutes" to mapOf("ar" to "دقائق", "fr" to "minutes", "en" to "minutes"),
        "story_not_found" to mapOf("ar" to "هذه الجلسة غير موجودة", "fr" to "Cette séance n'existe pas.", "en" to "This session doesn't exist."),
        "story_step_review" to mapOf("ar" to "أسترجع تعبيراتي", "fr" to "Je retrouve mes expressions", "en" to "I recall my expressions"),
        "story_step_listen1" to mapOf("ar" to "أستمع وأكتشف", "fr" to "J'écoute et je découvre", "en" to "I listen and discover"),
        "story_step_listen2" to mapOf("ar" to "أفهم بمساعدة", "fr" to "Je comprends avec l'aide", "en" to "I understand with help"),
        "story_step_listen3" to mapOf("ar" to "أتعرف دون مساعدة", "fr" to "Je reconnais sans aide", "en" to "I recognise without help"),
        "story_step_questions" to mapOf("ar" to "أجيب", "fr" to "Je réponds", "en" to "I answer"),
        "story_step_drill" to mapOf("ar" to "أبني الجمل", "fr" to "Je construis", "en" to "I build sentences"),
        "story_step_retell" to mapOf("ar" to "أعيد الصياغة", "fr" to "Je reformule", "en" to "I retell"),
        "story_step_transfer" to mapOf("ar" to "أعيد الاستخدام", "fr" to "Je réutilise", "en" to "I reuse"),
        "story_play" to mapOf("ar" to "استمع", "fr" to "Écouter", "en" to "Listen"),
        "story_slow" to mapOf("ar" to "أبطأ", "fr" to "Plus lentement", "en" to "Slower"),
        "story_show_text" to mapOf("ar" to "أظهر النص", "fr" to "Voir le texte", "en" to "Show text"),
        "story_hide_text" to mapOf("ar" to "أخفِ النص", "fr" to "Masquer le texte", "en" to "Hide text"),
        "story_help" to mapOf("ar" to "مساعدة", "fr" to "Aide", "en" to "Help"),
        "story_your_answer" to mapOf("ar" to "إجابتك بالإنجليزية…", "fr" to "Ta réponse en anglais…", "en" to "Your answer in English…"),
        "story_check" to mapOf("ar" to "تحقق", "fr" to "Vérifier", "en" to "Check"),
        "story_dont_know" to mapOf("ar" to "لا أعرف بعد", "fr" to "Je ne sais pas encore", "en" to "I don't know yet"),
        "story_correct" to mapOf("ar" to "صحيح!", "fr" to "Correct !", "en" to "Correct!"),
        "story_model" to mapOf("ar" to "الإجابة النموذجية", "fr" to "Réponse modèle", "en" to "Model answer"),
        "story_continue" to mapOf("ar" to "متابعة", "fr" to "Continuer", "en" to "Continue"),
        "story_remember" to mapOf("ar" to "أتذكر", "fr" to "Bon", "en" to "Good"),
        "story_forgot" to mapOf("ar" to "نسيت", "fr" to "Oublié", "en" to "Forgot"),
        "story_i_did_it" to mapOf("ar" to "حاولت", "fr" to "J'ai essayé", "en" to "I tried"),
        "story_skip" to mapOf("ar" to "تخطي", "fr" to "Passer", "en" to "Skip"),
        "story_self_reported" to mapOf("ar" to "تقييم ذاتي — ليست درجة نطق.", "fr" to "Autoévalué — ce n'est pas une note de prononciation.", "en" to "Self-assessed — this is not a pronunciation score."),
        "story_summary_title" to mapOf("ar" to "انتهت الجلسة!", "fr" to "Séance terminée !", "en" to "Session complete!"),
        "story_summary_first_try" to mapOf("ar" to "صحيحة من المحاولة الأولى", "fr" to "Réussies au 1er essai", "en" to "First-try correct"),
        "story_summary_helped" to mapOf("ar" to "بمساعدة", "fr" to "Avec aide", "en" to "With help"),
        "story_summary_mistakes" to mapOf("ar" to "للمراجعة", "fr" to "À revoir", "en" to "To review"),
        "story_summary_production" to mapOf("ar" to "محاولة إنتاج", "fr" to "Production essayée", "en" to "Production attempted"),
        "story_summary_phrases" to mapOf("ar" to "تعبيرات لإعادة العمل", "fr" to "Expressions à retravailler", "en" to "Expressions to rework"),
        "story_quit_resume" to mapOf("ar" to "تم حفظ الجلسة: يمكنك استئنافها.", "fr" to "La séance est enregistrée : tu pourras la reprendre.", "en" to "Your session is saved — you can resume it."),
        // === Exploration multi-modules + correction d'écriture / vocal ===
        "explore_title" to mapOf("ar" to "استكشاف المعجم الطبي", "fr" to "Explorer le lexique", "en" to "Explore the lexicon"),
        "explore_close" to mapOf("ar" to "إغلاق", "fr" to "Fermer", "en" to "Close"),
        "explore_back" to mapOf("ar" to "رجوع", "fr" to "Retour", "en" to "Back"),
        "explore_modules_short" to mapOf("ar" to "مقرر", "fr" to "module(s)", "en" to "module(s)"),
        "explore_term_fr" to mapOf("ar" to "المصطلح بالفرنسية", "fr" to "Terme en français", "en" to "French term"),
        "explore_write_hint" to mapOf(
            "ar" to "اكتب المصطلح بالإنجليزية قبل قلب البطاقة",
            "fr" to "Écris le mot en anglais avant de retourner la carte",
            "en" to "Write the English word before flipping the card"
        ),
        "explore_write_placeholder" to mapOf("ar" to "المصطلح بالإنجليزية…", "fr" to "Mot anglais…", "en" to "English word…"),
        "explore_voice" to mapOf("ar" to "إملاء صوتي", "fr" to "Dicter", "en" to "Dictate"),
        "explore_voice_prompt" to mapOf("ar" to "قل المصطلح بالإنجليزية", "fr" to "Dis le mot en anglais", "en" to "Say the English word"),
        "explore_voice_listening" to mapOf("ar" to "🎙️ جاري الاستماع…", "fr" to "🎙️ Écoute en cours…", "en" to "🎙️ Listening…"),
        "explore_voice_unavailable" to mapOf(
            "ar" to "التعرّف الصوتي غير متوفر على هذا الجهاز — اكتب المصطلح.",
            "fr" to "Reconnaissance vocale indisponible sur cet appareil — écris le mot.",
            "en" to "Voice recognition is unavailable on this device — type the word."
        ),
        "explore_voice_denied" to mapOf(
            "ar" to "تم رفض الميكروفون — اسمح به أو اكتب المصطلح.",
            "fr" to "Micro refusé — autorise le micro ou écris le mot.",
            "en" to "Microphone denied — allow it or type the word."
        ),
        "explore_voice_nothing" to mapOf(
            "ar" to "لم يُسمع شيء — أعد المحاولة أو اكتب المصطلح.",
            "fr" to "Rien entendu — réessaie ou écris le mot.",
            "en" to "Nothing heard — try again or type the word."
        ),
        "explore_reveal" to mapOf("ar" to "إظهار الإجابة", "fr" to "Voir la réponse", "en" to "Show answer"),
        "explore_check" to mapOf("ar" to "تحقق", "fr" to "Vérifier", "en" to "Check"),
        "explore_verdict_exact" to mapOf("ar" to "مطابق تماماً!", "fr" to "Exact !", "en" to "Exact!"),
        "explore_verdict_accent" to mapOf("ar" to "شبه مثالي (حركات/حالة الأحرف)", "fr" to "Presque parfait (accent/casse)", "en" to "Almost perfect (accent/case)"),
        "explore_verdict_near" to mapOf("ar" to "قريب — بعض الأحرف", "fr" to "Presque — quelques lettres", "en" to "Close — a few letters"),
        "explore_verdict_wrong" to mapOf("ar" to "ليس بعد", "fr" to "Pas encore", "en" to "Not yet"),
        "explore_verdict_empty" to mapOf("ar" to "إجابة فارغة", "fr" to "Réponse vide", "en" to "Empty answer"),
        "explore_verdict_revealed" to mapOf("ar" to "تم إظهار الإجابة", "fr" to "Réponse révélée", "en" to "Answer revealed"),
        "explore_expected" to mapOf("ar" to "الإجابة المتوقعة", "fr" to "Réponse attendue", "en" to "Expected answer"),
        "explore_issues" to mapOf("ar" to "ما رصده المصحّح", "fr" to "Ce que le correcteur a vu", "en" to "What the corrector found"),
        "explore_suggested_quality" to mapOf("ar" to "الدرجة المقترحة: {q}/5", "fr" to "Note proposée : {q}/5", "en" to "Suggested grade: {q}/5"),
        "explore_issue_empty" to mapOf("ar" to "لا توجد إجابة", "fr" to "Aucune réponse", "en" to "No answer"),
        "explore_issue_accent" to mapOf("ar" to "حركة ناقصة أو زائدة", "fr" to "Accent manquant ou en trop", "en" to "Missing or extra accent"),
        "explore_issue_case" to mapOf("ar" to "حالة الأحرف", "fr" to "Majuscule/minuscule", "en" to "Letter case"),
        "explore_issue_article" to mapOf("ar" to "أداة التعريف (the/a/an)", "fr" to "Article (the/a/an)", "en" to "Article (the/a/an)"),
        "explore_issue_plural" to mapOf("ar" to "الجمع (-s)", "fr" to "Pluriel (-s)", "en" to "Plural (-s)"),
        "explore_issue_hyphen" to mapOf("ar" to "الشرطة/الفراغ", "fr" to "Trait d'union / espace", "en" to "Hyphen / space"),
        "explore_issue_missing" to mapOf("ar" to "أحرف ناقصة", "fr" to "Lettre(s) manquante(s)", "en" to "Missing letter(s)"),
        "explore_issue_extra" to mapOf("ar" to "أحرف زائدة", "fr" to "Lettre(s) en trop", "en" to "Extra letter(s)"),
        "explore_issue_substituted" to mapOf("ar" to "أحرف مبدّلة", "fr" to "Lettre(s) remplacée(s)", "en" to "Substituted letter(s)"),
        "explore_issue_word_order" to mapOf("ar" to "ترتيب الكلمات", "fr" to "Ordre des mots", "en" to "Word order"),
        "explore_issue_wrong_language" to mapOf("ar" to "كلمة فرنسية بدل الإنجليزية", "fr" to "Mot français au lieu de l'anglais", "en" to "French word instead of English"),
        "explore_issue_meaning" to mapOf("ar" to "المعنى غير مستحضر", "fr" to "Sens non retrouvé", "en" to "Meaning not recalled"),
        "explore_rate" to mapOf(
            "ar" to "تقييمك (يحدّث المراجعة المتباعدة)",
            "fr" to "Ta note (met à jour la révision espacée)",
            "en" to "Your grade (updates spaced repetition)"
        ),
        "explore_summary_title" to mapOf("ar" to "انتهت الجلسة", "fr" to "Session terminée", "en" to "Session complete"),
        "explore_summary_sub" to mapOf("ar" to "تم إنجاز {n} بطاقة", "fr" to "{n} cartes travaillées", "en" to "{n} cards completed"),
        "explore_summary_average" to mapOf("ar" to "المعدل /100", "fr" to "Score moyen /100", "en" to "Average score /100"),
        "explore_summary_correct" to mapOf("ar" to "ناجحة", "fr" to "Réussies", "en" to "Correct"),
        "explore_summary_restart" to mapOf("ar" to "سحب جديد", "fr" to "Nouveau tirage", "en" to "New draw"),
        "explore_summary_exit" to mapOf("ar" to "خروج", "fr" to "Quitter", "en" to "Quit"),
        "explore_empty_title" to mapOf("ar" to "لا توجد بطاقات لهذا الاختيار", "fr" to "Aucune carte pour ce choix", "en" to "No cards for this selection"),
        "explore_empty_hint" to mapOf(
            "ar" to "غيّر الوضع أو المقررات: «للمراجعة» يحتوي فقط على البطاقات التي أخطأت فيها، و«تم إنجازها» على ما درسته.",
            "fr" to "Change de mode ou de modules : « À revoir » ne contient que les cartes ratées, « Déjà faits » celles déjà étudiées.",
            "en" to "Change the mode or modules: “To review” only holds missed cards, “Done” only studied ones."
        ),
        "explore_max_hint" to mapOf("ar" to "الحد الأقصى {n} بطاقة في الجلسة", "fr" to "Maximum {n} cartes par session", "en" to "Maximum {n} cards per session"),
        "explore_panel_title" to mapOf("ar" to "استكشاف متعدد المقررات", "fr" to "Exploration multi-modules", "en" to "Multi-module exploration"),
        "explore_panel_sub" to mapOf(
            "ar" to "اختر عدة مقررات ووضعاً وكمية (حتى 300).",
            "fr" to "Choisis plusieurs modules, un mode et une quantité (max 300).",
            "en" to "Pick several modules, a mode and a quantity (max 300)."
        ),
        "explore_panel_open" to mapOf("ar" to "إعداد الاستكشاف", "fr" to "Configurer l'exploration", "en" to "Set up exploration"),
        "explore_panel_hide" to mapOf("ar" to "إخفاء", "fr" to "Masquer", "en" to "Hide"),
        "explore_modules_label" to mapOf("ar" to "المقررات (اختيار متعدد)", "fr" to "Modules (plusieurs choix)", "en" to "Modules (multi-select)"),
        "explore_count_label" to mapOf("ar" to "الكمية", "fr" to "Quantité", "en" to "Quantity"),
        "explore_available" to mapOf("ar" to "{n} بطاقة متاحة", "fr" to "{n} cartes disponibles", "en" to "{n} cards available"),
        "explore_launch" to mapOf("ar" to "ابدأ الاستكشاف", "fr" to "Lancer l'exploration", "en" to "Start exploring"),
        "explore_module_action" to mapOf("ar" to "استكشف", "fr" to "Explorer", "en" to "Explore"),
        "explore_mode_all" to mapOf("ar" to "الكل", "fr" to "Tous", "en" to "All"),
        "explore_mode_random" to mapOf("ar" to "عشوائي", "fr" to "Au hasard", "en" to "Random"),
        "explore_mode_done" to mapOf("ar" to "تم إنجازها", "fr" to "Déjà faits", "en" to "Already done"),
        "explore_mode_review" to mapOf("ar" to "للمراجعة", "fr" to "À revoir", "en" to "To review")
    )

    fun get(key: String, language: Language): String {
        return translations[key]?.get(language.code) ?: translations[key]?.get("fr") ?: key
    }

    /**
     * Libellé accordé en nombre : `oneKey` pour 1, `manyKey` sinon.
     * « 1 leçon + 0 révision » et non « 1 leçons + 0 révisions ».
     */
    fun count(count: Int, oneKey: String, manyKey: String, language: Language): String =
        get(if (count == 1) oneKey else manyKey, language)
}
