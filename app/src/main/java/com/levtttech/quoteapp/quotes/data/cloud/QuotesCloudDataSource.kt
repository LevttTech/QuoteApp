package com.levtttech.quoteapp.quotes.data.cloud

import com.levtttech.quoteapp.quotes.data.HandleDataRequest
import com.levtttech.quoteapp.quotes.data.QuoteData
import com.levtttech.quoteapp.quotes.domain.Repository
import javax.inject.Inject

interface QuotesCloudDataSource {
    suspend fun quote(): QuoteData

    class Base @Inject constructor(
        private val service: QuotesService,
    ): QuotesCloudDataSource {
        override suspend fun quote(): QuoteData = service.loadQuote()[0]

    }
}