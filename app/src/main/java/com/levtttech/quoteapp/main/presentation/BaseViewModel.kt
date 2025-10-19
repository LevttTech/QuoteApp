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

abstract class BaseViewModel() : ViewModel()