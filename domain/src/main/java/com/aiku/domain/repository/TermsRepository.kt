package com.aiku.domain.repository

interface TermsRepository {
    fun getTerms(resId: Int): String
}
