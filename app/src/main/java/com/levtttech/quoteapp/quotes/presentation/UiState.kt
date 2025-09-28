package com.levtttech.quoteapp.quotes.presentation

import android.widget.TextView

sealed class UiState {

    abstract fun show(view: TextView)

    data class Success(private val text: String) : TextState(text)

    data class Error(private val message: String) : TextState(message)

    abstract class TextState(
        private val text: String
    ) : UiState() {
        override fun show(view: TextView) {
            view.text = text
        }
    }

}