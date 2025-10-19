package com.levtttech.quoteapp.details.presentation

import android.widget.TextView
import com.levtttech.quoteapp.quotes.presentation.QuoteUi

sealed class UiState {
    abstract fun show(header: TextView, category: TextView, author: TextView)
    data class TextState(private val item: DetailsUi) : UiState() {
        override fun show(
            header: TextView,
            category: TextView,
            author: TextView,
        ) {
            header.text = item.quote
            category.text = item.category
            author.text = item.author
        }
    }
}

