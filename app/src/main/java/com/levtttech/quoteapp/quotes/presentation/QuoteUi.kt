package com.levtttech.quoteapp.quotes.presentation

import android.widget.TextView

data class QuoteUi(private val id: Int, private val quote: String){
    fun <T> map(mapper: Mapper<T>): T = mapper.map(id, quote)
    interface Mapper<T>{

        fun map(id: Int, quote: String): T
    }
}


class ListItemUi(
    private val textView: TextView
) : QuoteUi.Mapper<Unit> {
    override fun map(id: Int, quote: String) {
        textView.text = "${id}. $quote"
    }
}