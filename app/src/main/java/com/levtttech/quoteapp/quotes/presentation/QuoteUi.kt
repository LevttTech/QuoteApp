package com.levtttech.quoteapp.quotes.presentation

import android.widget.TextView

data class QuoteUi(private val quote: String){
    fun <T> map(mapper: Mapper<T>): T = mapper.map(quote)
    interface Mapper<T>{

        fun map(quote: String): T
    }
}


class ListItemUi(
    private val textView: TextView
) : QuoteUi.Mapper<Unit> {
    override fun map(quote: String) {
        textView.text = quote
    }
}