package com.levtttech.quoteapp.quotes.presentation

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.TextView
import androidx.transition.Visibility
import org.w3c.dom.Text

sealed class UiState {

    abstract fun show(textView: TextView)

    data class Success(private val text: String) : TextState(text)

    data class Error(private val message: String) : TextState(message)


    abstract class TextState(
        private val text: String,
    ) : UiState() {
        override fun show(textView: TextView) {
           textView.text = text
        }
    }

    class ClearText : TextState("")

}