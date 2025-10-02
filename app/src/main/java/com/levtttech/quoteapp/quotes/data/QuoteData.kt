package com.levtttech.quoteapp.quotes.data

import com.google.gson.annotations.SerializedName

data class QuoteData(private val id: Int,
                     @SerializedName("quote") private val quote: String
) {

    fun <T> map(mapper: Mapper<T>): T = mapper.map(id, quote)
    interface Mapper<T> {
        fun map(id:Int, quote: String): T
    }
}