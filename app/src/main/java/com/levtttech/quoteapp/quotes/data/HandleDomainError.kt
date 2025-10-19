package com.levtttech.quoteapp.quotes.data

import com.levtttech.quoteapp.quotes.domain.DomainException.NoInternetConnectionException
import com.levtttech.quoteapp.quotes.domain.DomainException.ServiceUnavaliableException
import com.levtttech.quoteapp.quotes.domain.HandleError
import java.net.UnknownHostException
import javax.inject.Inject

class HandleDomainError @Inject constructor() : HandleError<Exception> {
    override fun handle(exception: Exception): Exception {
        return when (exception) {
            is UnknownHostException -> NoInternetConnectionException()
            else -> ServiceUnavaliableException()
        }
    }
}