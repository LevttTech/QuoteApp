package com.levtttech.quoteapp.quotes.data.cloud

import com.levtttech.quoteapp.quotes.data.QuoteData
import retrofit2.http.GET

interface QuotesService {

    @GET("quotes")
    suspend fun loadQuote(): List<QuoteData>
}