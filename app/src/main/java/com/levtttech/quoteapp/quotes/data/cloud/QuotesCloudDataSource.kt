package com.levtttech.quoteapp.quotes.data.cloud

import com.levtttech.quoteapp.quotes.data.QuoteData
import com.levtttech.quoteapp.quotes.domain.Repository

interface QuotesCloudDataSource {
    suspend fun quote(): QuoteData

    class Base(
        private val service: QuotesService,
    ): QuotesCloudDataSource {
        override suspend fun quote(): QuoteData = service.loadQuote()

    }
}