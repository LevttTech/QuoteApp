package com.levtttech.quoteapp.quotes.data.cloud

import com.levtttech.quoteapp.quotes.data.QuoteData
import retrofit2.http.GET

interface QuotesService {

    @GET("https://api.api-ninjas.com/v1/quotes")
    fun loadQuote(): QuoteData
}