package com.levtttech.quoteapp.quotes.data

import com.google.gson.annotations.SerializedName

data class QuoteData(
    private val id: Int,
    @SerializedName("quote") private val quote: String,
    @SerializedName("author") private val author: String,
    @SerializedName("category") private val category: String,
) {

    fun <T> map(mapper: Mapper<T>): T = mapper.map(id, quote, author, category)
    interface Mapper<T> {
        fun map(id: Int, quote: String, author: String, category: String): T
    }
}