package com.levtttech.quoteapp.quotes.presentation

import com.levtttech.quoteapp.quotes.domain.QuoteDomain
import com.levtttech.quoteapp.quotes.domain.QuoteResult
import javax.inject.Inject

class QuotesResultMapper @Inject constructor(
    private val communications: QuotesCommunications,
    private val mapper: QuoteDomain.Mapper<QuoteUi>,
) : QuoteResult.Mapper<Unit> {
    override fun map(errorMessage: String) {
        communications.showState(UiState.Error(errorMessage))
    }

    override fun map(list: List<QuoteDomain>) {
        if (list.isNotEmpty()) {
            communications.showState(UiState.Success(list.last().quote))
            communications.showQuotes(list.map { it.map(mapper) })
        } else {
            map("Quote is empty!")
        }
    }
}


