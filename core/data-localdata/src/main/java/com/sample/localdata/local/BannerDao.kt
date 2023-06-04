package com.sample.localdata.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface BannerDao {

    @Upsert
    suspend fun upsertAll(images: List<BannerEntity>)

    @Query("DELETE FROM banner_entity")
    suspend fun clearAll()

    @Query("SELECT * FROM banner_entity ORDER BY bannerNo DESC ")
    fun getBannerList(): Flow<List<BannerEntity>>

}