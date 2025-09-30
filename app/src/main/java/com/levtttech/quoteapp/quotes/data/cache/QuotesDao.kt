package com.levtttech.quoteapp.quotes.data.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface QuotesDao {
    @Query("SELECT * FROM quotes_table")
    fun allQuotes(): List<QuoteCache>

    @Insert
    fun insert(quote: QuoteCache)

}