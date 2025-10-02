package com.levtttech.quoteapp.quotes.domain

data class QuoteDomain (private val id: Int, val quote: String) {
    fun <T> map(mapper:Mapper<T>): T = mapper.map(id, quote)

    interface Mapper<T> {
        fun map(id: Int, quote: String): T
    }
}