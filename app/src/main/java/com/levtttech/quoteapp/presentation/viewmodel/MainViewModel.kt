package com.levtttech.quoteapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.levtttech.quoteapp.data.QuoteService
import com.levtttech.quoteapp.domain.LoadResult
import com.levtttech.quoteapp.domain.Repository
import com.levtttech.quoteapp.presentation.ui.UiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: Repository,
    private val mapper: LoadResult.Mapper<UiState>
) : ViewModel() {
    private val _liveData = MutableLiveData<UiState>()
    val liveData: LiveData<UiState>
        get() = _liveData

    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    fun load() {
        viewModelScope.launch {
            val loadResult = repository.loadQuote()
            val uiState = loadResult.map(mapper)
            _liveData.value = uiState
        }
    }
}