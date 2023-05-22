package com.sample.localdata.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ImageRemoteKeysDao {

    @Query("SELECT * FROM image_remote_keys_entity ORDER BY nextPage DESC LIMIT 1")
    suspend fun getImageRemoteKey(): ImageRemoteKeysEntity?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addAllImageRemoteKeys(imageRemoteKeys: List<ImageRemoteKeysEntity>)

    @Query("DELETE FROM image_remote_keys_entity")
    suspend fun deleteAllImageRemoteKeys()
}