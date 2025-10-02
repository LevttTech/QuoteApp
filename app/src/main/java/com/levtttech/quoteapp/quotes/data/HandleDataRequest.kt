package com.levtttech.quoteapp.quotes.data

import android.util.Log
import com.levtttech.quoteapp.quotes.data.cache.QuotesCacheDataSource
import com.levtttech.quoteapp.quotes.domain.QuoteDomain
import javax.inject.Inject

interface HandleDataRequest {
    suspend fun handle(block: suspend() -> QuoteData): QuoteDomain

    class Base @Inject constructor(
        private val cacheDataSource: QuotesCacheDataSource,
        private val mapper: QuoteData.Mapper<QuoteDomain>
    ): HandleDataRequest {
        override suspend fun handle(block: suspend () -> QuoteData): QuoteDomain {
            return try {
                val result: QuoteData = block.invoke()
                cacheDataSource.insert(result)
                result.map(mapper)
            } catch (e: Exception) {
                throw Exception(e)
            }
        }
    }
}