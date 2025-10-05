package com.levtttech.quoteapp.di

import com.levtttech.quoteapp.quotes.data.BaseQuoteRepository
import com.levtttech.quoteapp.quotes.data.HandleDataRequest
import com.levtttech.quoteapp.quotes.data.HandleDomainError
import com.levtttech.quoteapp.quotes.data.QuoteData
import com.levtttech.quoteapp.quotes.data.QuoteDataToDomain
import com.levtttech.quoteapp.quotes.data.cache.QuotesCacheDataSource
import com.levtttech.quoteapp.quotes.data.cloud.QuotesCloudDataSource
import com.levtttech.quoteapp.quotes.data.cloud.QuotesService
import com.levtttech.quoteapp.quotes.domain.HandleError
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
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class MainModule {

    @Provides
    fun provideQuoteInteractor(
        repository: Repository,
        handleRequest: HandleRequest
    ): QuoteInteractor {
        return QuoteInteractor.Base(repository, handleRequest)
    }

    @Provides
    fun provideRepository(
        cloudDataSource: QuotesCloudDataSource,
        cacheDataSource: QuotesCacheDataSource,
        handleDataRequest: HandleDataRequest,
        mapper: QuoteData.Mapper<QuoteDomain>
    ): Repository {
        return BaseQuoteRepository(cloudDataSource, cacheDataSource, mapper, handleDataRequest)
    }

    @Provides
    fun provideHandleRequest(
        repository: Repository,
        handleError: HandleError<String>
    ): HandleRequest {
        return HandleRequest.Base(repository, handleError)
    }

    @Provides
    fun provideHandleDomainError(): HandleError<Exception> {
        return HandleDomainError()
    }

    @Provides
    fun provideHandleErrorString(): HandleError<String> {
        return HandleError.Base()
    }

    @Provides
    fun provideQuotesCloudDataSource(
        impl: QuotesService
    ): QuotesCloudDataSource {
        return QuotesCloudDataSource.Base(impl)
    }

    @Provides
    fun provideDataToDomainMapper(): QuoteData.Mapper<QuoteDomain> {
        return QuoteDataToDomain()
    }

    @Provides
    fun provideDispatchers(): DispatchersList {
        return DispatchersList.Base()
    }

}