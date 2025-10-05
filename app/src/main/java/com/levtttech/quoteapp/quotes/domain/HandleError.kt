package com.levtttech.quoteapp.quotes.domain

import com.levtttech.quoteapp.quotes.domain.DomainException.NoInternetConnectionException
import com.levtttech.quoteapp.quotes.domain.DomainException.ServiceUnavaliableException
import javax.inject.Inject

interface HandleError<T> {
    fun handle(exception: Exception): T
    class Base @Inject constructor(): HandleError<String> {
        override fun handle(exception: Exception): String {
            return when(exception as DomainException) {
                is NoInternetConnectionException -> "no internet connection"
                is ServiceUnavaliableException -> "Service unavailable"
            }
        }
    }
}