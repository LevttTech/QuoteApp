package com.levtttech.quoteapp.quotes.data.cache

import com.levtttech.quoteapp.quotes.data.QuoteData

interface QuotesCacheDataSource {

    suspend fun allQuotes() : List<QuoteData>

    class Base(

    ) : QuotesCacheDataSource {
        override suspend fun allQuotes(): List<QuoteData> {

        }
    }
}