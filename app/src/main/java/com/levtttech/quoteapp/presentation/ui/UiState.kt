package com.levtttech.quoteapp.presentation.ui

import android.widget.TextView
import com.levtttech.quoteapp.domain.LoadResult

interface UiState {

    fun show(textView: TextView)

    data class Success(val text: String): UiState {
        override fun show(textView: TextView) {
            textView.text = text
        }
    }

    data class Error(val text: String): UiState {
        override fun show(textView: TextView) {
            textView.text = text
        }
    }
}