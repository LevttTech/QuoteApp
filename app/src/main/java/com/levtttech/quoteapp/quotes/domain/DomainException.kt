package com.levtttech.quoteapp.quotes.domain


sealed class DomainException(private val exception: Exception? = null) : Exception() {
    class NoInternetConnectionException() : DomainException()
    class ServiceUnavaliableException() : DomainException()
}
