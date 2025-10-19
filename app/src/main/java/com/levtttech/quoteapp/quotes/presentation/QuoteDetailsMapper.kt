package com.levtttech.quoteapp.quotes.presentation

import android.content.Context
import com.levtttech.quoteapp.R
import com.levtttech.quoteapp.details.presentation.DetailsUi
import javax.inject.Inject

class QuoteDetailsMapper @Inject constructor(
    private val context: Context,
) : QuoteUi.Mapper<DetailsUi> {
    override fun map(
        id: Int,
        quote: String,
        author: String,
        category: String,
    ): DetailsUi = DetailsUi(
        id,
        quote,
        context.getString(R.string.category_s, category),
        context.getString(R.string.author_s, author)
    )
}