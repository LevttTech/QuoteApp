package com.levtttech.quoteapp.quotes.presentation

import com.levtttech.quoteapp.quotes.domain.QuoteDomain
import javax.inject.Inject

class QuoteUiMapper @Inject constructor() : QuoteDomain.Mapper<QuoteUi> {
    override fun map(quote: String): QuoteUi {
        return QuoteUi(quote)
    }
}