package com.levtttech.quoteapp.quotes.presentation

import android.os.Parcelable
import android.widget.TextView
import kotlinx.parcelize.Parcelize

@Parcelize
data class QuoteUi(
    private val id: Int,
    private val quote: String,
    private val author: String,
    private val category: String,
) : Mapper<Boolean, QuoteUi>, Parcelable {
    fun <T> map(mapper: Mapper<T>): T = mapper.map(id, quote, author, category)
    interface Mapper<T> {
        fun map(id: Int, quote: String, author: String, category: String): T
    }

    override fun map(source: QuoteUi): Boolean = source.id == id
}

class ListItemUi(
    private val textView: TextView,
) : QuoteUi.Mapper<Unit> {
    override fun map(id: Int, quote: String, author: String, category: String) {
        textView.text = "${id}. $quote"
    }
}