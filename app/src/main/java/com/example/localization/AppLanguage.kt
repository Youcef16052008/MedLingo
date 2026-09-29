package com.example.localization

enum class Language(val code: String, val flag: String, val displayName: String) {
    ARABIC("ar", "🇸🇦", "العربية"),
    FRENCH("fr", "🇫🇷", "Français"),
    ENGLISH("en", "🇬🇧", "English");

    val isRtl: Boolean get() = this == ARABIC
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
        )
    )

    fun get(key: String, language: Language): String {
        return translations[key]?.get(language.code) ?: translations[key]?.get("fr") ?: key
    }
}
