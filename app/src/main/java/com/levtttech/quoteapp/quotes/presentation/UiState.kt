package com.levtttech.quoteapp.quotes.presentation

import android.widget.TextView

sealed class UiState {

    abstract fun show(view: TextView)

    data class Success(private val text: String) : UiState(){
        override fun show(view: TextView) {
            view.text = text
        }
    }

    abstract class Error(
        private val message: String
    ) : UiState() {
        override fun show(view: TextView) {
            view.text = message
        }
    }

}