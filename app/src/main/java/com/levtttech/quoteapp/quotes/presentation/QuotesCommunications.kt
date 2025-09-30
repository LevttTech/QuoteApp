package com.levtttech.quoteapp.quotes.presentation

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import javax.inject.Inject

interface QuotesCommunications : ObserveQuotes {
    fun showProgress(show: Int)
    fun showState(uiState: UiState)
    fun showQuote(quoteUi: QuoteUi)


    class Base @Inject constructor(
        private val progress: ProgressCommunication,
        private val state: StateCommunication,
        private val quote: QuotesCommunication
    ) : QuotesCommunications {
        override fun showProgress(show: Int) {
            progress.map(show)
        }

        override fun showState(uiState: UiState) {
            state.map(uiState)
        }

        override fun showQuote(quoteUi: QuoteUi) {
            quote.map(quoteUi)
        }

        override fun observeProgress(
            owner: LifecycleOwner,
            observer: Observer<Int>
        ) {
            progress.observe(owner, observer)
        }

        override fun observeState(
            owner: LifecycleOwner,
            observer: Observer<UiState>
        ) {
            state.observe(owner, observer)
        }

        override fun observeQuote(
            owner: LifecycleOwner,
            observer: Observer<QuoteUi>
        ) {
            quote.observe(owner,observer)

        }
    }
}

interface ObserveQuotes {
    fun observeProgress(owner: LifecycleOwner, observer: Observer<Int>)
    fun observeState(owner: LifecycleOwner, observer: Observer<UiState>)
    fun observeQuote(owner: LifecycleOwner, observer: Observer<QuoteUi>)
}

interface ProgressCommunication : Communication.Mutable<Int> {
    class Base @Inject constructor(): Communication.Ui<Int>(), ProgressCommunication {
        init {
            Log.d("FRAGMENT", "ProgressCommunication.Base created: ${hashCode()}")
        }
    }
}

interface StateCommunication: Communication.Mutable<UiState> {
    class Base @Inject constructor(): Communication.Ui<UiState>(), StateCommunication {
        init {
            Log.d("FRAGMENT", "StateCommunication.Base created: ${hashCode()}")
        }
    }
}

interface QuotesCommunication: Communication.Mutable<QuoteUi> {
    class Base @Inject constructor() : Communication.Ui<QuoteUi>(), QuotesCommunication
}