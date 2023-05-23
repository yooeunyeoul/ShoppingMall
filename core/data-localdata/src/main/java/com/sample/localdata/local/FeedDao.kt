package com.sample.localdata.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface FeedDao {

    @Upsert
    suspend fun upsertAll(images: List<FeedEntity>)

    @Query("SELECT * FROM feed_entity")
    fun pagingSource(): PagingSource<Int, FeedEntity>

    @Query("DELETE FROM banner_entity")
    suspend fun clearAll()


    @Query("SELECT * FROM banner_entity")
    fun getBannerList(): Flow<List<BannerEntity>>

}