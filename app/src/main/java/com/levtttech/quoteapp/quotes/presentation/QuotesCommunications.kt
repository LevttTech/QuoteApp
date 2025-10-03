package com.levtttech.quoteapp.quotes.presentation

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import javax.inject.Inject

interface QuotesCommunications : ObserveQuotes {
    fun showProgress(show: Int)
    fun showState(uiState: UiState)
    fun showQuotes(listQuotes: List<QuoteUi>)


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

        override fun showQuotes(listQuotes: List<QuoteUi>) {
            quote.map(listQuotes)
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

        override fun observeQuotes(
            owner: LifecycleOwner,
            observer: Observer<List<QuoteUi>>
        ) {
            quote.observe(owner,observer)

        }
    }
}

interface ObserveQuotes {
    fun observeProgress(owner: LifecycleOwner, observer: Observer<Int>)
    fun observeState(owner: LifecycleOwner, observer: Observer<UiState>)
    fun observeQuotes(owner: LifecycleOwner, observer: Observer<List<QuoteUi>>)
}

interface ProgressCommunication : Communication.Mutable<Int> {
    class Base @Inject constructor(): Communication.Ui<Int>(), ProgressCommunication {
    }
}

interface StateCommunication: Communication.Mutable<UiState> {
    class Base @Inject constructor(): Communication.Ui<UiState>(), StateCommunication {
    }
}

interface QuotesCommunication: Communication.Mutable<List<QuoteUi>> {
    class Base @Inject constructor() : Communication.Ui<List<QuoteUi>>(), QuotesCommunication
}