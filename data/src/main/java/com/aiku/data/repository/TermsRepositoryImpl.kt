package com.aiku.data.repository

import com.aiku.data.source.local.TermsLocalDataSource
import com.aiku.domain.repository.TermsRepository
import javax.inject.Inject

class TermsRepositoryImpl @Inject constructor(
    private val termsLocalDataSource: TermsLocalDataSource
) : TermsRepository {
    override fun getTerms(resId: Int): String {
        return termsLocalDataSource.readTermsFromRaw(resId)
    }
}
