package com.levtttech.quoteapp.quotes.domain

data class QuoteDomain (private val id: Int, val quote: String, private val author: String, private val category: String) {
    fun <T> map(mapper:Mapper<T>): T = mapper.map(id, quote,author,category)

    interface Mapper<T> {
        fun map(id: Int, quote: String, author: String, category: String): T
    }
}