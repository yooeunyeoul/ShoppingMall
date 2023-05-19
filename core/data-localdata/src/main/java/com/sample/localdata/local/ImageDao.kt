package com.sample.localdata.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface ImageDao {

    @Upsert
    suspend fun upsertAll(images: List<SampleEntity>)

    @Query("SELECT * FROM image_entity")
    fun pagingSource(): PagingSource<Int, SampleEntity>

    @Query("DELETE FROM image_entity")
    suspend fun clearAll()

    @Query("SELECT * FROM image_entity WHERE isFavorite = 1")
    fun getFavoriteImages(): Flow<List<SampleEntity>>

    @Query("UPDATE image_entity SET isFavorite =:isFavorite WHERE id=:id ")
    suspend fun imageLikeUpdate(id: String, isFavorite: Boolean)
}