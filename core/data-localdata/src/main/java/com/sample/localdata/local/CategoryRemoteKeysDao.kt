package com.sample.localdata.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sample.domain.util.CategoryType

@Dao
interface CategoryRemoteKeysDao {

    @Query("SELECT * FROM category_remote_keys_entity WHERE categoryType=:categoryType ORDER BY nextPage DESC LIMIT 1")
    suspend fun getImageRemoteKey(categoryType: CategoryType): CategoryRemoteKeysEntity?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addAllImageRemoteKeys(imageRemoteKeys: List<CategoryRemoteKeysEntity>)

    @Query("DELETE FROM category_remote_keys_entity WHERE categoryType =:categoryType")
    suspend fun deleteAllImageRemoteKeys(categoryType: CategoryType)
}