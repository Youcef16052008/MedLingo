package com.example

import androidx.test.core.app.ApplicationProvider
import com.example.data.illustrations.TermIllustrations
import com.example.data.initial.InitialData
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class TermIllustrationsTest {
    @Test fun curatedTermsHaveBundledDrawableAndCaption() {
        val resources = ApplicationProvider.getApplicationContext<android.content.Context>().resources
        listOf("Femur", "Neuron").forEach { name ->
            val term = InitialData.terms.single { it.module == "Anatomie" && it.termEn == name }
            val illustration = TermIllustrations.forTerm(term)
            assertTrue("No image for $name", illustration != null)
            assertEquals("drawable", resources.getResourceTypeName(illustration!!.drawable))
            assertTrue(illustration.captionFr.isNotBlank())
            assertTrue(illustration.captionEn.isNotBlank())
            assertTrue(illustration.captionAr.isNotBlank())
            assertTrue(illustration.credit.isNotBlank())
        }
    }

    @Test fun unrelatedTermsNeverShowInventedOrMissingImages() {
        val femur = InitialData.terms.single { it.module == "Anatomie" && it.termEn == "Femur" }
        assertNull(TermIllustrations.forTerm(femur.copy(termEn = "Tibia")))
        assertNull(TermIllustrations.forTerm(femur.copy(module = "Histologie")))
        assertFalse("Seed still points to non-existent assets", InitialData.terms.any { it.imageAsset.isNotBlank() })
    }
}
