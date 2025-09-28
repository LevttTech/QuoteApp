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
import com.levtttech.quoteapp.quotes.presentation.QuoteUi
import com.levtttech.quoteapp.quotes.presentation.QuotesResultMapper
import com.levtttech.quoteapp.quotes.presentation.UiState
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface MainModule {
    @Binds
    abstract fun bindQuoteInteractor(
        base: QuoteInteractor.Base
    ): QuoteInteractor
    @Binds
    fun bindRepository(impl: BaseQuoteRepository): Repository
    @Binds
    abstract fun bindHandleRequest(
        impl: HandleRequest.Base
    ): HandleRequest

    @Binds
    abstract fun bindQuotesCloudDataSource(
        impl: QuotesCloudDataSource.Base
    ): QuotesCloudDataSource

    @Binds
    abstract fun bindQuoteMapper(
        impl: QuotesResultMapper // Предполагаемая реализация
    ): QuoteResult.Mapper<UiState>

    @Binds
    abstract fun bindDataToDomainMapper(

        impl: QuoteDataToDomain
    ): QuoteData.Mapper<QuoteDomain>
}