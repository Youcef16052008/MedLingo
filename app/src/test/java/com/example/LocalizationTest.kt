package com.example

import com.example.localization.Language
import org.junit.Assert.assertEquals
import org.junit.Test

class LanguageTest {

    @Test
    fun fromCode_reads_persisted_language() {
        assertEquals(Language.FRENCH, Language.fromCode("fr"))
        assertEquals(Language.ENGLISH, Language.fromCode("en"))
        assertEquals(Language.ARABIC, Language.fromCode("ar"))
    }

    @Test
    fun fromCode_defaults_to_french_for_unknown_or_blank() {
        assertEquals(Language.FRENCH, Language.fromCode("de"))
        assertEquals(Language.FRENCH, Language.fromCode(""))
    }

    @Test
    fun fromCode_is_case_insensitive() {
        assertEquals(Language.ARABIC, Language.fromCode("AR"))
        assertEquals(Language.ENGLISH, Language.fromCode("En"))
    }
}
