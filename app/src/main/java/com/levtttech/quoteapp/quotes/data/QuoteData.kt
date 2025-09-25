package com.levtttech.quoteapp.quotes.data

data class QuoteData(private val quote: String) {

    fun <T> map(mapper: Mapper<T>): T = mapper.map(quote)
    interface Mapper<T> {
        fun map(quote: String): T
    }
}