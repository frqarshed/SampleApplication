package com.sample.myapplication.core.di

import com.sample.data.core.dao.ClassifiedListingDao
import com.sample.data.core.impl.ClassifiedListingRepositoryImpl
import com.sample.data.core.network.NetworkDataSource
import com.sample.domain.repository.ClassifiedListingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Provides
    @Singleton
    internal fun provideClassifiedListingRepository(
        dao: ClassifiedListingDao
    ): ClassifiedListingRepository =
        ClassifiedListingRepositoryImpl(dao)

    @Provides
    @Singleton
    internal fun provideClassifiedListingDao(
        dataSource: NetworkDataSource
    ): ClassifiedListingDao =
        ClassifiedListingDao(dataSource)
}
