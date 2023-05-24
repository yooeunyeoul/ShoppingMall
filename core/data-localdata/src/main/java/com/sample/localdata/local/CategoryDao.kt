package com.sample.localdata.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface CategoryDao {

    @Upsert
    suspend fun upsertAll(images: List<CategoryEntity>)

    @Query("SELECT * FROM category_entity")
    fun pagingSource(): PagingSource<Int, CategoryEntity>

    @Query("DELETE FROM category_entity")
    suspend fun clearAll()


}