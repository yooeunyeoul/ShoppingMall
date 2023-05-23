package com.sample.localdata.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        BannerEntity::class, CategoryEntity::class, ImageRemoteKeysEntity::class, FeedEntity::class],
    version = 1
)
abstract class ShoppingMallDatabase : RoomDatabase() {
    abstract fun imageDao(): ImageDao

    abstract fun feedDao(): FeedDao

    abstract fun imageRemoteKeysDao(): ImageRemoteKeysDao

}