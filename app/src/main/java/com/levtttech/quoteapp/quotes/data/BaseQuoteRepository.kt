package com.levtttech.quoteapp.quotes.data

import com.levtttech.quoteapp.quotes.data.cloud.QuotesCloudDataSource
import com.levtttech.quoteapp.quotes.domain.QuoteDomain
import com.levtttech.quoteapp.quotes.domain.Repository
import javax.inject.Inject

class BaseQuoteRepository @Inject constructor(
    private val cloudDataSource: QuotesCloudDataSource,
    private val mapper: QuoteData.Mapper<QuoteDomain>
): Repository {
    override suspend fun loadQuote(): QuoteDomain {
        return cloudDataSource.quote().map(mapper)
    }

    override suspend fun allQuotes(): List<QuoteDomain> {
        TODO("Not yet implemented")
    }
}