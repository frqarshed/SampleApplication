package com.sample.myapplication.core.di

import com.sample.data.core.Constants.BASE_URL
import com.sample.data.core.network.NetworkDataSource
import com.sample.data.core.network.RestApiBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Provides
    @Singleton
    internal fun provideRetrofit() =
        RestApiBuilder(BASE_URL).build()

    @Provides
    @Singleton
    internal fun provideNetworkDataSource(retrofit: Retrofit): NetworkDataSource =
        NetworkDataSource(retrofit)
}
