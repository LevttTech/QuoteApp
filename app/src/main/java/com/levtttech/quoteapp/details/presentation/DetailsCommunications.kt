package com.levtttech.quoteapp.details.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.levtttech.quoteapp.quotes.presentation.Communication
import javax.inject.Inject

interface DetailsCommunications : ObserveDetails {
    fun showDetails(uiState: UiState)
    
    class Base @Inject constructor(
        private val details: Details
    ): DetailsCommunications {
        override fun showDetails(uiState: UiState) {
            details.map(uiState)
        }

        override fun observe(
            owner: LifecycleOwner,
            observer: Observer<UiState>,
        ) {
            details.observe(owner, observer)
        }
    }
}

interface ObserveDetails {
    fun observe(owner: LifecycleOwner, observer: Observer<UiState>)
}

interface Details : Communication.Mutable<UiState> {
    class Base @Inject constructor(): Communication.Ui<UiState>(), Details
}