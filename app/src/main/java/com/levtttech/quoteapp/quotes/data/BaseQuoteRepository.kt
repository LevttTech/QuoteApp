package com.levtttech.quoteapp.quotes.data

import com.levtttech.quoteapp.quotes.data.cloud.QuotesCloudDataSource
import com.levtttech.quoteapp.quotes.domain.QuoteDomain
import com.levtttech.quoteapp.quotes.domain.Repository

class BaseQuoteRepository(
    cloudDataSource: QuotesCloudDataSource
): Repository {
    override suspend fun loadQuote(): QuoteDomain {
        TODO("Not yet implemented")
    }

    override suspend fun allQuotes(): List<QuoteDomain> {
        TODO("Not yet implemented")
    }
}