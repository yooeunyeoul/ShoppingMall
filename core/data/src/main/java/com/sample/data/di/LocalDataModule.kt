package com.sample.data.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
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
        db: ShoppingMallDatabase,
        dataStore: DataStore<Preferences>
    ): ShoppingMallLocalDataSource = ShoppingMallLocalDataSourceImpl(db,dataStore)
}