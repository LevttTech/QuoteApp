package com.levtttech.quoteapp.details.presentation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailsUi(
    val id: Int,
    val quote: String,
    val category: String,
    val author: String,
) : Parcelable {
    fun <T> map(mapper: Mapper<T>): T = mapper.map(id, quote, category, author)

    interface Mapper<T> {
        fun map(id: Int, quote: String, category: String, author: String): T
    }
}

