package com.levtttech.quoteapp.quotes.data

import com.levtttech.quoteapp.quotes.data.cache.QuoteCache
import javax.inject.Inject

class QuoteDataToCacheMapper @Inject constructor(
) : QuoteData.Mapper<QuoteCache>{
    override fun map(id: Int, quote: String): QuoteCache = QuoteCache(id, quote)
}