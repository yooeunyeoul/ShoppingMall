package com.sample.data.di

import com.sample.data.repository.datasource.ShoppingMallRemoteDataSource
import com.sample.data.repository.datasourceImpl.ShoppingMallRemoteDataSourceImpl
import com.sample.localdata.local.ShoppingMallDatabase
import com.sample.network.remote.ShoppingApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataModule {
    @Provides
    fun provideImagesRemoteDataSource(
        api: ShoppingApi,
        db: ShoppingMallDatabase,
    ): ShoppingMallRemoteDataSource =
        ShoppingMallRemoteDataSourceImpl(api, db)

}