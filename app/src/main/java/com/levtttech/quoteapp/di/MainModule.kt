package com.levtttech.quoteapp.di

import com.levtttech.quoteapp.quotes.data.BaseQuoteRepository
import com.levtttech.quoteapp.quotes.data.QuoteData
import com.levtttech.quoteapp.quotes.data.QuoteDataToDomain
import com.levtttech.quoteapp.quotes.data.cloud.QuotesCloudDataSource
import com.levtttech.quoteapp.quotes.domain.HandleRequest
import com.levtttech.quoteapp.quotes.domain.QuoteDomain
import com.levtttech.quoteapp.quotes.domain.QuoteInteractor
import com.levtttech.quoteapp.quotes.domain.QuoteResult
import com.levtttech.quoteapp.quotes.domain.Repository
import com.levtttech.quoteapp.quotes.presentation.DispatchersList
import com.levtttech.quoteapp.quotes.presentation.ProgressCommunication
import com.levtttech.quoteapp.quotes.presentation.QuoteHandleRequest
import com.levtttech.quoteapp.quotes.presentation.QuotesCommunication
import com.levtttech.quoteapp.quotes.presentation.QuotesCommunications
import com.levtttech.quoteapp.quotes.presentation.QuotesResultMapper
import com.levtttech.quoteapp.quotes.presentation.StateCommunication
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface MainModule {
    @Binds
    fun bindQuoteInteractor(
        base: QuoteInteractor.Base
    ): QuoteInteractor

    @Binds
    fun bindRepository(impl: BaseQuoteRepository): Repository

    @Binds
    fun bindHandleRequest(
        impl: HandleRequest.Base
    ): HandleRequest

    @Binds
    fun bindQuotesCloudDataSource(
        impl: QuotesCloudDataSource.Base
    ): QuotesCloudDataSource


    @Binds
    fun bindDataToDomainMapper(
        impl: QuoteDataToDomain
    ): QuoteData.Mapper<QuoteDomain>

    @Binds
    fun bindDispatchers(
        impl: DispatchersList.Base
    ): DispatchersList
}