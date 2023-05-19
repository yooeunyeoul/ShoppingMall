package com.sample.localdata.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        SampleEntity::class], version = 1
)
abstract class ShoppingMallDatabase : RoomDatabase() {
    abstract fun imageDao(): ImageDao

}