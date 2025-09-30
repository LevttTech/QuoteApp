package com.levtttech.quoteapp.main.presentation

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.levtttech.quoteapp.quotes.presentation.Communication
import com.levtttech.quoteapp.quotes.presentation.ProgressCommunication
import com.levtttech.quoteapp.quotes.presentation.QuotesCommunications
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class BaseViewModel() : ViewModel(), Handle {
    override fun <T> handle(
        block: suspend () -> T,
        ui: (T) -> Unit
    ): Job = viewModelScope.launch( Dispatchers.IO ) {
        val result = block.invoke()
        withContext(Dispatchers.Main) {
            ui.invoke(result)
        }
    }
}
interface Handle {
    fun <T> handle(
        block: suspend () -> T,
        ui: (T) -> Unit
    ): Job
}