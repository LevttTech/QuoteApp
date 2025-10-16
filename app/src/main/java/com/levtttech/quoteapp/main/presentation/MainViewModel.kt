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
): ViewModel(), Communication.Observe<Screen> {
    override fun observe(
        owner: LifecycleOwner,
            observer: Observer<Screen>,
    ) {
        Log.d("MainViewModel","observe")
        navigationCommunication.observe(owner, observer)
    }
}