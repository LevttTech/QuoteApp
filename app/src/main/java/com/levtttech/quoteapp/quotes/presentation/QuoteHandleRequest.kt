package com.levtttech.quoteapp.quotes.presentation

import com.levtttech.quoteapp.quotes.domain.QuoteResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

interface QuoteHandleRequest {

    fun handle(
        coroutineScope: CoroutineScope,
        block: suspend () -> QuoteResult
    )

    class Base(
        private val mapper: QuoteResult.Mapper<Unit>
    ): QuoteHandleRequest {
        override fun handle(
            coroutineScope: CoroutineScope,
            block: suspend () -> QuoteResult
        ) {
            coroutineScope.launch(Dispatchers.IO) {
                val result = block.invoke()
                withContext(Dispatchers.Main) {
                    //SHOW PROGRESS
                    result.map(mapper)
                }
            }
        }
    }
}