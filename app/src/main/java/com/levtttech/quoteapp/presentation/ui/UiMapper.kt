package com.levtttech.quoteapp.presentation.ui

import com.levtttech.quoteapp.domain.LoadResult

class UiMapper : LoadResult.Mapper<UiState> {
    override fun mapSuccess(quote: String): UiState {
        return UiState.Success(quote)
    }

    override fun mapError(error: String): UiState {
        return UiState.Error(error)
    }
}