package com.sample.data.di

import com.sample.data.repository.datasource.ShoppingMallLocalDataSource
import com.sample.data.repository.datasourceImpl.ShoppingMallLocalDataSourceImpl
import com.sample.localdata.local.ShoppingMallDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LocalDataModule {
    @Provides
    fun provideShoppingLocalDataSource(
        db: ShoppingMallDatabase
    ): ShoppingMallLocalDataSource = ShoppingMallLocalDataSourceImpl(db)
}