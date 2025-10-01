package com.levtttech.quoteapp.quotes.domain

import android.os.Message

interface QuoteResult {

    fun <T> map(mapper: Mapper<T>): T

    interface Mapper<T> {
        fun map(errorMessage: String): T
        fun map(list: List<QuoteDomain>): T
    }

    data class Success(private val list: List<QuoteDomain>): QuoteResult {
        override fun <T> map(mapper: Mapper<T>): T = mapper.map(list)
    }

    data class Failure(private val message: String): QuoteResult {
        override fun <T> map(mapper: Mapper<T>): T = mapper.map(message)
    }
}