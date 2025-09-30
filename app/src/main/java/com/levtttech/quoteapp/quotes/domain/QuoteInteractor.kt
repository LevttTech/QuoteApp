package com.levtttech.quoteapp.quotes.domain

import javax.inject.Inject

interface QuoteInteractor: QuoteInitialUseCase, QuoteUseCase {

    class Base @Inject constructor(
        private val repository: Repository,
        private val handleRequest: HandleRequest
    ) : QuoteInteractor {
        override suspend fun init(): QuoteResult = handleRequest.handle{
            repository.allQuotes()
        }

        override suspend fun quote(): QuoteResult = handleRequest.handle {
            repository.loadQuote()
        }
    }
}

interface QuoteInitialUseCase {
    suspend fun init() : QuoteResult
}

interface QuoteUseCase {
    suspend fun quote(): QuoteResult
}