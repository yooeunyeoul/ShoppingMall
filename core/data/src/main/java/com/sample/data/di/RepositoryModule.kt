package com.sample.data.di

import com.sample.data.repository.ShoppingMallRepositoryImpl
import com.sample.data.repository.datasource.ShoppingMallRemoteDataSource
import com.sample.domain.repository.ShoppingMallRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideShoppingRepository(
        remoteDataSource: ShoppingMallRemoteDataSource
    ): ShoppingMallRepository {
        return ShoppingMallRepositoryImpl(remoteDataSource)
    }
}