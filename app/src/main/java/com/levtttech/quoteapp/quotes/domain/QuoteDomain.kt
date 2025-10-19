package com.levtttech.quoteapp.quotes.domain

data class QuoteDomain(
    val id: Int,
    val quote: String,
    val author: String,
    val category: String,
) {
    fun <T> map(mapper: Mapper<T>): T = mapper.map(id, quote, author, category)

    interface Mapper<T> {
        fun map(id: Int, quote: String, author: String, category: String): T
    }
}