package com.levtttech.quoteapp.quotes.presentation

import android.util.Log
import com.levtttech.quoteapp.quotes.domain.QuoteDomain
import com.levtttech.quoteapp.quotes.domain.QuoteResult
import javax.inject.Inject
import kotlin.math.log

class QuotesResultMapper @Inject constructor(
    private val communications: QuotesCommunications
) : QuoteResult.Mapper<Unit> {
    override fun map(errorMessage: String) {
        Log.d("FRAGMENT",errorMessage)
        communications.showState(UiState.Error(errorMessage))
    }

    override fun map(quoteDomain: QuoteDomain) {
        communications.showState(UiState.Success(quoteDomain.quote))
    }
}