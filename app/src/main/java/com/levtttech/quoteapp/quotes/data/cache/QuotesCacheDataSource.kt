package com.levtttech.quoteapp.quotes.data.cache

import com.levtttech.quoteapp.quotes.data.QuoteData
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject

interface QuotesCacheDataSource {

    suspend fun allQuotes() : List<QuoteData>
    suspend fun insert(quote: QuoteData)

    class Base @Inject constructor(
        private val dao: QuotesDao,
        private val dataToCache:QuoteData.Mapper<QuoteCache>
    ) : QuotesCacheDataSource {

        val mutex = Mutex()

        override suspend fun allQuotes(): List<QuoteData> = mutex.withLock{
            return dao.allQuotes().map { QuoteData(it.quote) }
        }

        override suspend fun insert(quote: QuoteData) = mutex.withLock{
            dao.insert(quote.map(dataToCache))
        }
    }
}