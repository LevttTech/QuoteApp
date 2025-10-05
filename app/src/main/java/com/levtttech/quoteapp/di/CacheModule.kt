package com.levtttech.quoteapp.di

import android.content.Context
import androidx.room.Room
import com.levtttech.quoteapp.quotes.data.HandleDataRequest
import com.levtttech.quoteapp.quotes.data.QuoteData
import com.levtttech.quoteapp.quotes.data.QuoteDataToCacheMapper
import com.levtttech.quoteapp.quotes.data.cache.QuoteCache
import com.levtttech.quoteapp.quotes.data.cache.QuotesCacheDataSource
import com.levtttech.quoteapp.quotes.data.cache.QuotesDao
import com.levtttech.quoteapp.quotes.data.cache.QuotesDataBase
import com.levtttech.quoteapp.quotes.domain.HandleError
import com.levtttech.quoteapp.quotes.domain.QuoteDomain
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class CacheModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): QuotesDataBase {
        return Room.databaseBuilder(
            context, QuotesDataBase::class.java, "quotes_database"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideDao(db: QuotesDataBase): QuotesDao = db.quotesDao()

    @Provides
    fun provideCacheDataSource(
        dao: QuotesDao, dataToCache: QuoteData.Mapper<QuoteCache>
    ): QuotesCacheDataSource = QuotesCacheDataSource.Base(
        dao, dataToCache
    )

    @Provides
    fun provideMapper(): QuoteData.Mapper<QuoteCache> = QuoteDataToCacheMapper()


    @Provides
    fun provideHandleDataRequest(
        cacheDataSource: QuotesCacheDataSource, mapper: QuoteData.Mapper<QuoteDomain>,
        handleDomainError: HandleError<Exception>
    ): HandleDataRequest = HandleDataRequest.Base(
        cacheDataSource, mapper, handleDomainError
    )

}