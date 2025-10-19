package com.levtttech.quoteapp.details.presentation

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.levtttech.quoteapp.main.presentation.BaseViewModel
import com.levtttech.quoteapp.main.presentation.NavigationCommunication
import com.levtttech.quoteapp.quotes.presentation.QuoteUi
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val communication: DetailsCommunications
) : BaseViewModel(), ObserveDetails, InitDetails {

    override fun observe(
        owner: LifecycleOwner,
        observer: Observer<UiState>,
    ) {
        communication.observe(owner,observer)
    }

    override fun init(isFirstRun: Boolean, item: DetailsUi) {
        if (isFirstRun) {
            communication.showDetails(UiState.TextState(item))
        }
    }
}

interface InitDetails {
    fun init(isFirstRun: Boolean, item: DetailsUi)
}
