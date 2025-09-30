package com.levtttech.quoteapp.quotes.data.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.io.path.ExperimentalPathApi

@Entity(tableName = "quotes_table")
data class QuoteCache(
    @PrimaryKey(autoGenerate = true) @ColumnInfo (name = "id") val id: Int,
    @ColumnInfo(name = "quote") val quote: String
)
