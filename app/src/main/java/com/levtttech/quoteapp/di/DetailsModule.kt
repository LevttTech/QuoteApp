package com.levtttech.quoteapp.di

import com.levtttech.quoteapp.details.presentation.Details
import com.levtttech.quoteapp.details.presentation.DetailsCommunications
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ViewModelComponent::class)
class DetailsModule {

    @Provides
    fun provideDetails(): Details {
        return Details.Base()
    }

    @Provides
    fun provideCommunications(details: Details): DetailsCommunications {
        return DetailsCommunications.Base(details)
    }
}