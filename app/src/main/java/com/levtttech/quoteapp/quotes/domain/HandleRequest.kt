package com.levtttech.quoteapp.quotes.domain

import javax.inject.Inject

interface HandleRequest {
    suspend fun handle(block: suspend() -> Unit): QuoteResult

    class Base @Inject constructor(
        private val repository: Repository,
        private val errorHandler: HandleError<String>
    ) : HandleRequest {
        override suspend fun handle(block: suspend () -> Unit): QuoteResult {
            return try {
                block.invoke()
                QuoteResult.Success(repository.allQuotes())
            } catch(e: Exception) {
                QuoteResult.Failure(errorHandler.handle(e))
            }
        }
    }
}