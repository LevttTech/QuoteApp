package com.levtttech.quoteapp.quotes.data

import com.levtttech.quoteapp.quotes.domain.QuoteDomain
import javax.inject.Inject

class QuoteDataToDomain @Inject constructor() : QuoteData.Mapper<QuoteDomain>{
    override fun map(id: Int, quote: String): QuoteDomain {
        return QuoteDomain(id,quote)
    }
}