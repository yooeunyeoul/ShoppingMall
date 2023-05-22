package com.sample.localdata.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface ImageDao {

    @Upsert
    suspend fun upsertAll(images: List<CategoryEntity>)

    @Query("SELECT * FROM category_entity")
    fun pagingSource(): PagingSource<Int, CategoryEntity>

    @Query("DELETE FROM banner_entity")
    suspend fun clearAll()


    @Query("SELECT * FROM banner_entity")
    fun getBannerList(): Flow<List<BannerEntity>>

}