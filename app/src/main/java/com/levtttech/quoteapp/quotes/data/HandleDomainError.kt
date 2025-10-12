package com.levtttech.quoteapp.quotes.data

import android.util.Log
import com.levtttech.quoteapp.quotes.domain.DomainException
import com.levtttech.quoteapp.quotes.domain.DomainException.*
import com.levtttech.quoteapp.quotes.domain.HandleError
import java.net.UnknownHostException
import javax.inject.Inject

class HandleDomainError @Inject constructor() : HandleError<Exception> {
    override fun handle(exception: Exception): Exception {
        Log.d("HandleDomainError", "Transforming ${exception.javaClass.simpleName} to DomainException")
        return when(exception) {
            is UnknownHostException -> NoInternetConnectionException()
            else -> ServiceUnavaliableException()
        }
    }
}