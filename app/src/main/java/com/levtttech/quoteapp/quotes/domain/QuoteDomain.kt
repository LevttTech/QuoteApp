package com.levtttech.quoteapp.quotes.domain

data class QuoteDomain(private val quote: String) {
    fun <T> map(mapper:Mapper<T>): T = mapper.map(quote)

    interface Mapper<T> {
        fun map(quote: String): T
    }
}