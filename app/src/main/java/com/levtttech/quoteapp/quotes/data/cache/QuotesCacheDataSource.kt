package com.levtttech.quoteapp.quotes.data.cache

import com.levtttech.quoteapp.quotes.data.QuoteData

interface QuotesCacheDataSource {

    suspend fun allQuotes() : List<QuoteCache>

    class Base(
        private val dao: QuotesDao
    ) : QuotesCacheDataSource {
        override suspend fun allQuotes(): List<QuoteCache> {
            return dao.allQuotes()
        }
    }
}