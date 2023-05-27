package com.sample.localdata.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FeedRemoteKeysDao {

    @Query("SELECT * FROM feed_remote_keys_entity ORDER BY nextPage DESC LIMIT 1")
    suspend fun getImageRemoteKey(): FeedRemoteKeysEntity?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addAllImageRemoteKeys(imageRemoteKeys: List<FeedRemoteKeysEntity>)

    @Query("DELETE FROM feed_remote_keys_entity")
    suspend fun deleteAllImageRemoteKeys()
}