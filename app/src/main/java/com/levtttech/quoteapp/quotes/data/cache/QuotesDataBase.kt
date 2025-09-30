package com.levtttech.quoteapp.quotes.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import javax.inject.Inject

@Database(entities = [QuoteCache::class], version = 2,exportSchema = false)
abstract class QuotesDataBase : RoomDatabase() {
    abstract fun quotesDao() : QuotesDao
}