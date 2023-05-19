package com.sample.localdata.di

import android.content.Context
import androidx.room.Room
import com.sample.localdata.local.ShoppingMallDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Singleton
    @Provides
    fun provideLocalDatabase(@ApplicationContext context: Context): ShoppingMallDatabase {
        return Room.databaseBuilder(
            context,
            ShoppingMallDatabase::class.java,
            "shopping_mall_db"
        ).build()
    }

}