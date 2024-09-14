package com.aiku.domain.usecase

import com.aiku.domain.repository.TermsRepository
import javax.inject.Inject


class ReadTermsUseCase @Inject constructor(
    private val termsRepository: TermsRepository
) {
    fun execute(resId: Int): String {
        return termsRepository.getTerms(resId)
    }
}
