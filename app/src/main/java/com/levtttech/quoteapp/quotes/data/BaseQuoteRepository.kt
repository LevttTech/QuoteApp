package com.levtttech.quoteapp.quotes.data

import com.levtttech.quoteapp.quotes.data.cache.QuotesCacheDataSource
import com.levtttech.quoteapp.quotes.data.cloud.QuotesCloudDataSource
import com.levtttech.quoteapp.quotes.domain.QuoteDomain
import com.levtttech.quoteapp.quotes.domain.Repository
import javax.inject.Inject

class BaseQuoteRepository @Inject constructor(
    private val cloudDataSource: QuotesCloudDataSource,
    private val cacheDataSource: QuotesCacheDataSource,
    private val mapper: QuoteData.Mapper<QuoteDomain>,
    private val handleDataRequest: HandleDataRequest
): Repository {
    override suspend fun loadQuote(): QuoteDomain {
        return handleDataRequest.handle { cloudDataSource.quote() }
    }

    override suspend fun allQuotes(): List<QuoteDomain> {
        return cacheDataSource.allQuotes().map { it.map(mapper) }
    }
}