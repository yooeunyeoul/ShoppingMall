package com.sample.localdata.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        BannerEntity::class, CategoryEntity::class, FeedRemoteKeysEntity::class, FeedEntity::class,CategoryRemoteKeysEntity::class],
    version = 1
)
abstract class ShoppingMallDatabase : RoomDatabase() {
    abstract fun bannerDao(): BannerDao

    abstract fun categoryDao(): CategoryDao

    abstract fun feedDao(): FeedDao

    abstract fun feedRemoteKeysDao(): FeedRemoteKeysDao

    abstract fun categoryRemoteKeysDao(): CategoryRemoteKeysDao



}