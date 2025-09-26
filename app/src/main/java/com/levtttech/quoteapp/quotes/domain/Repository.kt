package com.levtttech.quoteapp.quotes.domain

interface Repository {

    suspend fun loadQuote(): Unit
}