package com.levtttech.quoteapp.quotes.presentation

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.levtttech.quoteapp.main.presentation.BaseViewModel
import com.levtttech.quoteapp.main.presentation.NavigationCommunication
import com.levtttech.quoteapp.main.presentation.Screen
import com.levtttech.quoteapp.quotes.domain.QuoteInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QuotesViewModel @Inject constructor(
    private val interactor: QuoteInteractor,
    private val communications: QuotesCommunications,
    private val handle: QuoteHandleRequest,
    private val details: Details
) : BaseViewModel(), ObserveQuotes, FetchQuote, Init, ClearText, Details {

    private companion object {
        const val TAG = "QuotesViewModel"
    }

    init {
        Log.d(TAG, "ViewModel created")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "ViewModel cleared/destroyed")
    }

    override fun observeProgress(
        owner: LifecycleOwner, observer: Observer<Int>,
    ) {
        Log.d(TAG, "observeProgress called")
        communications.observeProgress(owner, observer)
    }

    override fun observeState(
        owner: LifecycleOwner, observer: Observer<UiState>,
    ) {
        Log.d(TAG, "observeState called")
        communications.observeState(owner, observer)
    }

    override fun observeQuotes(
        owner: LifecycleOwner, observer: Observer<List<QuoteUi>>,
    ) {
        Log.d(TAG, "observeQuotes called")
        communications.observeQuotes(owner, observer)
    }

    override fun fetchQuote() {
        Log.d(TAG, "fetchQuote called")
        this.handle.handle(
            viewModelScope, {
                Log.d(TAG, "Interactor quote() called")
                interactor.quote()
            }
        )
    }

    override fun init(isFirstRun: Boolean) {
        Log.d(TAG, "init called with isFirstRun: $isFirstRun")
        if (isFirstRun) {
            Log.d(TAG, "First run - handling initialization")
            this.handle.handle(
                viewModelScope,
                {
                    Log.d(TAG, "Interactor init() called")
                    interactor.init()
                },
                {
                    Log.d(TAG, "Clear text callback executed")
                    clearText()
                }
            )
        } else {
            Log.d(TAG, "Not first run - skipping initialization")
        }
    }

    override fun clearText() {
        Log.d(TAG, "clearText called")
        communications.showState(UiState.ClearText())
    }

    override fun details() = details.details()
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
    fun details()

    class Base @Inject constructor(
        private val navigationCommunication: NavigationCommunication.Base
    ) : Details {
        override fun details() {
            navigationCommunication.map(Screen.Details)
        }
    }
}