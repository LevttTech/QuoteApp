package com.levtttech.quoteapp.di

import android.content.Context
import com.levtttech.quoteapp.details.data.QuoteDetailsRepository
import com.levtttech.quoteapp.main.presentation.NavigationCommunication
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
import com.levtttech.quoteapp.quotes.domain.Repository
import com.levtttech.quoteapp.quotes.domain.SaveDetailsUseCase
import com.levtttech.quoteapp.quotes.presentation.Details
import com.levtttech.quoteapp.quotes.presentation.DispatchersList
import com.levtttech.quoteapp.quotes.presentation.QuoteDetailsMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MainModule {
    @Provides
    @Singleton
    fun provideQuoteDetailsRepository(): QuoteDetailsRepository.Base {
        return QuoteDetailsRepository.Base()
    }
    @Provides
    @Singleton
    fun provideQuoteDetailsSave(repository: QuoteDetailsRepository.Base): QuoteDetailsRepository.Save {
        return repository
    }

    @Provides
    @Singleton
    fun provideQuoteDetailsRead(repository: QuoteDetailsRepository.Base): QuoteDetailsRepository.Read {
        return repository
    }

    @Provides
    fun provideQuoteInteractor(
        repository: Repository,
        handleRequest: HandleRequest,
        useCase: QuoteDetailsRepository.Save,
    ): QuoteInteractor {
        return QuoteInteractor.Base(repository, handleRequest, useCase)
    }

    @Provides
    fun provideRepository(
        cloudDataSource: QuotesCloudDataSource,
        cacheDataSource: QuotesCacheDataSource,
        handleDataRequest: HandleDataRequest,
        mapper: QuoteData.Mapper<QuoteDomain>,
    ): Repository {
        return BaseQuoteRepository(cloudDataSource, cacheDataSource, mapper, handleDataRequest)
    }

    @Provides
    fun provideHandleRequest(
        repository: Repository,
        handleError: HandleError<String>,
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
        impl: QuotesService,
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

    @Provides
    @Singleton
    fun provideNavigation(): NavigationCommunication.Base {
        return NavigationCommunication.Base()
    }

    @Provides
    @Singleton
    fun provideMapper(@ApplicationContext context: Context): QuoteDetailsMapper {
        return QuoteDetailsMapper(context)
    }

    @Provides
    @Singleton
    fun provideDetails(
        mapper: QuoteDetailsMapper,
        navigationCommunication: NavigationCommunication.Base,
        useCase: QuoteInteractor
    ): Details {
        return Details.Base(navigationCommunication, mapper, useCase)
    }


}