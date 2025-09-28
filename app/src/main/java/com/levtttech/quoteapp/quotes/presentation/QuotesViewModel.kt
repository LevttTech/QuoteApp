package com.levtttech.quoteapp.quotes.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.levtttech.quoteapp.quotes.domain.QuoteDomain
import com.levtttech.quoteapp.quotes.domain.QuoteInteractor
import com.levtttech.quoteapp.quotes.domain.QuoteResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class QuotesViewModel @Inject constructor(
    private val interactor: QuoteInteractor,
    private val mapper: QuotesResultMapper
): ViewModel() {
    private val _liveData: MutableLiveData<UiState> = MutableLiveData()
    val liveData: LiveData<UiState> = _liveData

    fun loadQuote() {
        viewModelScope.launch {
            val result = interactor.quote()
            withContext(Dispatchers.Main) {
                _liveData.value = result.map(mapper)
            }
        }

    }

}