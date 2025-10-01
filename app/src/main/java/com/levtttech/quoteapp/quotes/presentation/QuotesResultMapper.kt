package com.levtttech.quoteapp.quotes.presentation

import android.util.Log
import com.levtttech.quoteapp.quotes.domain.QuoteDomain
import com.levtttech.quoteapp.quotes.domain.QuoteResult
import javax.inject.Inject
import kotlin.math.log

class QuotesResultMapper @Inject constructor(
    private val communications: QuotesCommunications,
    private val mapper: QuoteDomain.Mapper<QuoteUi>
) : QuoteResult.Mapper<Unit> {
    override fun map(errorMessage: String) {
        Log.d("FRAGMENT",errorMessage)
        communications.showState(UiState.Error(errorMessage))
    }

    override fun map(list: List<QuoteDomain>) {
        if (list.isNotEmpty())
            communications.showQuotes(list.map { it.map(mapper) })
        communications.showState(UiState.Success(list.last().quote))
    }
}