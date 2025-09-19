package com.levtttech.quoteapp.domain

interface Repository {

    suspend fun loadQuote() : LoadResult
}