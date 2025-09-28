package com.levtttech.quoteapp.quotes.presentation

import android.util.Log
import com.levtttech.quoteapp.quotes.domain.QuoteDomain
import com.levtttech.quoteapp.quotes.domain.QuoteResult
import javax.inject.Inject

class QuotesResultMapper @Inject constructor(
) : QuoteResult.Mapper<UiState> {
    override fun map(errorMessage: String): UiState {
        Log.d("RETROFITERROS",errorMessage)
        return UiState.Error(errorMessage)
    }

    override fun map(quoteDomain: QuoteDomain): UiState {
        return UiState.Success(quoteDomain.quote)
    }
}