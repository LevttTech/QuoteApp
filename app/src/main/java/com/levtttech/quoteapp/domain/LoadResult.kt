package com.levtttech.quoteapp.domain

interface LoadResult {

    fun <T : Any> map(mapper: Mapper<T>): T

    interface Mapper<T : Any> {
        fun mapSuccess(quote: String): T

        fun mapError(error: String): T
    }

    data class Success(val quote: String) : LoadResult {
        override fun <T : Any> map(mapper: Mapper<T>): T {
            return mapper.mapSuccess(quote)
        }
    }

    data class Error(val error: String) : LoadResult {
        override fun <T : Any> map(mapper: Mapper<T>): T {
            return mapper.mapError(error)
        }
    }
}