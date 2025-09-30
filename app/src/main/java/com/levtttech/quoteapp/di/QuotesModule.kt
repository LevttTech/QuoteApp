package com.levtttech.quoteapp.di

import com.levtttech.quoteapp.quotes.domain.QuoteResult
import com.levtttech.quoteapp.quotes.presentation.ProgressCommunication
import com.levtttech.quoteapp.quotes.presentation.QuoteHandleRequest
import com.levtttech.quoteapp.quotes.presentation.QuotesCommunication
import com.levtttech.quoteapp.quotes.presentation.QuotesCommunications
import com.levtttech.quoteapp.quotes.presentation.QuotesResultMapper
import com.levtttech.quoteapp.quotes.presentation.StateCommunication
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
interface QuotesModule {
    @Binds
    @ViewModelScoped
    fun bindQuotesCommunications(impl: QuotesCommunications.Base): QuotesCommunications

    @Binds
    @ViewModelScoped
    fun bindProgressCommunication(impl: ProgressCommunication.Base): ProgressCommunication

    @Binds
    @ViewModelScoped
    fun bindStateCommunication(impl: StateCommunication.Base): StateCommunication

    @Binds
    @ViewModelScoped
    fun bindQuotesCommunication(impl: QuotesCommunication.Base): QuotesCommunication

    @Binds
    @ViewModelScoped
    fun bindQuoteMapper(impl: QuotesResultMapper): QuoteResult.Mapper<Unit>

    @Binds
    @ViewModelScoped
    fun bindHandleRequest(impl: QuoteHandleRequest.Base): QuoteHandleRequest
}