package com.example.localization

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
            "ar" to "١٥ وحدة · ٦ دروس لكل واحدة",
            "fr" to "15 unités · 6 leçons chacune",
            "en" to "15 units · 6 lessons each"
        ),
        "lesson_lbl" to mapOf("ar" to "درس", "fr" to "Leçon", "en" to "Lesson"),
        "path_locked_text" to mapOf(
            "ar" to "مطلوب ٧٠٪ في الدرس السابق",
            "fr" to "70 % requis sur la leçon précédente",
            "en" to "70 % required on the previous lesson"
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
        "trophy_chests_5_d" to mapOf("ar" to "افتح ٥ صناديق", "fr" to "Ouvrir 5 caisses", "en" to "Open 5 chests")
    )

    fun get(key: String, language: Language): String {
        return translations[key]?.get(language.code) ?: translations[key]?.get("fr") ?: key
    }
}
