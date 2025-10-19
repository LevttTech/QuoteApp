package com.levtttech.quoteapp.main.presentation

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.levtttech.quoteapp.quotes.presentation.Communication
import com.levtttech.quoteapp.quotes.presentation.Init
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val navigationCommunication: NavigationCommunication.Base
): ViewModel(), Communication.Observe<NavigationStrategy>, Init {
    override fun observe(
        owner: LifecycleOwner,
        observer: Observer<NavigationStrategy>,
    ) {
        Log.d("MainViewModel","observe")
        navigationCommunication.observe(owner, observer)
    }

    override fun init(isFirstRun: Boolean) {
        if (isFirstRun) {
            Log.d("QuotesFragment","init")
            navigationCommunication.map(NavigationStrategy.Replace(Screen.Quotes()))
        }
    }
}