package com.levtttech.quoteapp.quotes.presentation

data class QuoteUi(private val quote: String){
    fun <T> map(mapper: Mapper<T>): T = mapper.map(quote)
    interface Mapper<T>{

        fun <T> map(quote: String): T
    }
}
