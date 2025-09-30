package com.levtttech.quoteapp.quotes.presentation

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.levtttech.quoteapp.main.presentation.BaseViewModel
import com.levtttech.quoteapp.quotes.domain.HandleRequest
import com.levtttech.quoteapp.quotes.domain.QuoteInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QuotesViewModel @Inject constructor(
    private val interactor: QuoteInteractor,
    private val mapper: QuotesResultMapper,
    private val communications: QuotesCommunications,
    private val handle: QuoteHandleRequest
) : BaseViewModel(), ObserveQuotes, FetchQuote {
    override fun observeProgress(
        owner: LifecycleOwner, observer: Observer<Int>
    ) {
        communications.observeProgress(owner, observer)
    }

    override fun observeState(
        owner: LifecycleOwner, observer: Observer<UiState>
    ) {
        communications.observeState(owner, observer)
    }

    override fun observeQuote(
        owner: LifecycleOwner, observer: Observer<QuoteUi>
    ) {
        communications.observeQuote(owner, observer)
    }

    override fun fetchQuote() {
        this.handle.handle(
            viewModelScope
        ) { interactor.quote() }
    }
}

interface FetchQuote {
    fun fetchQuote()
}