package com.levtttech.quoteapp.quotes.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [QuoteCache::class], version = 3, exportSchema = false)
abstract class QuotesDataBase : RoomDatabase() {
    abstract fun quotesDao(): QuotesDao
}