package com.levtttech.quoteapp.quotes.presentation

import android.view.View
import com.levtttech.quoteapp.quotes.domain.QuoteResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface QuoteHandleRequest {

    fun handle(
        coroutineScope: CoroutineScope,
        block: suspend () -> QuoteResult
    )

    class Base @Inject constructor(
        private val mapper: QuoteResult.Mapper<Unit>,
        private val communications: QuotesCommunications
    ): QuoteHandleRequest {
        override fun handle(
            coroutineScope: CoroutineScope,
            block: suspend () -> QuoteResult
        ) {
            communications.showProgress(View.VISIBLE)
            coroutineScope.launch(Dispatchers.IO) {
                val result = block.invoke()
                withContext(Dispatchers.Main) {
                    communications.showProgress(View.GONE)
                    result.map(mapper)
                }
            }
        }
    }
}