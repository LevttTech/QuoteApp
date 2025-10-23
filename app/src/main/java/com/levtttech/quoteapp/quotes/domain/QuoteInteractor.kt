package com.levtttech.quoteapp.quotes.domain

import com.levtttech.quoteapp.details.data.QuoteDetailsRepository
import com.levtttech.quoteapp.details.presentation.DetailsUi
import com.levtttech.quoteapp.quotes.presentation.QuoteUi
import javax.inject.Inject

interface QuoteInteractor : QuoteInitialUseCase, QuoteUseCase, SaveDetailsUseCase {

    class Base @Inject constructor(
        private val repository: Repository,
        private val handleRequest: HandleRequest,
        private val quoteDetails: QuoteDetailsRepository.Save
    ) : QuoteInteractor {
        override fun saveDetails(item: DetailsUi) = quoteDetails.save(item)

        override suspend fun init(): QuoteResult = handleRequest.handle()

        override suspend fun quote(): QuoteResult = handleRequest.handle {
            repository.loadQuote()
        }
    }
}

interface QuoteInitialUseCase {
    suspend fun init(): QuoteResult
}

interface QuoteUseCase {
    suspend fun quote(): QuoteResult
}

interface SaveDetailsUseCase {
    fun saveDetails(item: DetailsUi)
}