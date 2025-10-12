package com.levtttech.quoteapp.quotes.domain


sealed class DomainException : Exception() {
    class NoInternetConnectionException() : DomainException()
    class ServiceUnavaliableException() : DomainException()
}
