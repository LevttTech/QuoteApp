package com.levtttech.quoteapp.details.presentation


import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.levtttech.quoteapp.details.data.QuoteDetailsRepository
import com.levtttech.quoteapp.main.presentation.BaseViewModel
import com.levtttech.quoteapp.quotes.presentation.Init
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val communication: DetailsCommunications,
    private val quoteDetails: QuoteDetailsRepository.Read
) : BaseViewModel(), ObserveDetails, Init {

    override fun observe(
        owner: LifecycleOwner,
        observer: Observer<UiState>,
    ) {
        communication.observe(owner, observer)
    }

    override fun init(isFirstRun: Boolean) {
        if (isFirstRun) {
            communication.showDetails(UiState.TextState(quoteDetails.read()))
        }
    }
}

