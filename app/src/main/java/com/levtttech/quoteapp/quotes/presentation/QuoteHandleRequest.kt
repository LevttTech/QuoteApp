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
        block: suspend () -> QuoteResult,
        onComplete: (() -> Unit)? = null
    )

    class Base @Inject constructor(
        private val mapper: QuoteResult.Mapper<Unit>,
        private val communications: QuotesCommunications,
        private val dispatchersList: DispatchersList
    ) : QuoteHandleRequest {
        override fun handle(
            coroutineScope: CoroutineScope,
            block: suspend () -> QuoteResult,
            onComplete: (() -> Unit)?
        ) {
            communications.showProgress(View.VISIBLE)
            coroutineScope.launch(dispatchersList.io()) {
                val result = block.invoke()
                withContext(dispatchersList.ui()) {
                    communications.showProgress(View.GONE)
                    result.map(mapper)
                    onComplete?.invoke()
                }
            }
        }

    }
}
