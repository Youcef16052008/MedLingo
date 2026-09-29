package com.example.data.initial

import com.example.data.local.entity.MedicalTermEntity

class AnatomyDatabase {
    companion object {
        fun getAllTerms(): List<MedicalTermEntity> {
            return osteologyTerms + arthrologyTerms + myologyTerms + neurologyTerms
        }
    }
}
