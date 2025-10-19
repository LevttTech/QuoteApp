package com.levtttech.quoteapp.quotes.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.levtttech.quoteapp.main.presentation.BaseViewModel
import com.levtttech.quoteapp.main.presentation.NavigationCommunication
import com.levtttech.quoteapp.main.presentation.NavigationStrategy
import com.levtttech.quoteapp.main.presentation.Screen
import com.levtttech.quoteapp.quotes.domain.QuoteInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QuotesViewModel @Inject constructor(
    private val interactor: QuoteInteractor,
    private val communications: QuotesCommunications,
    private val handle: QuoteHandleRequest,
    private val details: Details,
) : BaseViewModel(), ObserveQuotes, FetchQuote, Init, ClearText, Details {
    override fun observeProgress(
        owner: LifecycleOwner, observer: Observer<Int>,
    ) {
        communications.observeProgress(owner, observer)
    }

    override fun observeState(
        owner: LifecycleOwner, observer: Observer<UiState>,
    ) {
        communications.observeState(owner, observer)
    }

    override fun observeQuotes(
        owner: LifecycleOwner, observer: Observer<List<QuoteUi>>,
    ) {
        communications.observeQuotes(owner, observer)
    }

    override fun fetchQuote() {
        this.handle.handle(
            viewModelScope, {
                interactor.quote()
            })
    }

    override fun init(isFirstRun: Boolean) {
        if (isFirstRun) {
            this.handle.handle(viewModelScope, {
                interactor.init()
            }, {
                clearText()
            })
        }
    }

    override fun clearText() {
        communications.showState(UiState.ClearText())
    }

    override fun details(item: QuoteUi) = details.details(item)
}

interface FetchQuote {
    fun fetchQuote()
}

interface Init {
    fun init(isFirstRun: Boolean)
}

interface ClearText {
    fun clearText()
}

interface Details {
    fun details(item: QuoteUi)

    class Base @Inject constructor(
        private val navigationCommunication: NavigationCommunication.Base,
        private val mapper: QuoteDetailsMapper,
    ) : Details {
        override fun details(item: QuoteUi) {
            navigationCommunication.map(
                NavigationStrategy.Replace(
                    Screen.Details(item.map(mapper))
                )
            )
        }
    }
}