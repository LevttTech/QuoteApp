package com.levtttech.quoteapp.quotes.data

import com.google.gson.annotations.SerializedName

data class QuoteData(@SerializedName("quote") val quote: String) {

    fun <T> map(mapper: Mapper<T>): T = mapper.map(quote)
    interface Mapper<T> {
        fun map(quote: String): T
    }
}