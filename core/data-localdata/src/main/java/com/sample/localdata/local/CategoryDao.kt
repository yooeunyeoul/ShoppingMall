package com.sample.localdata.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.sample.common_utils.utils.CategoryType

@Dao
interface CategoryDao {

    @Upsert
    suspend fun upsertAll(images: List<CategoryEntity>)

    @Query("SELECT * FROM category_entity WHERE categoryType =:categoryType")
    fun pagingSource(categoryType: CategoryType): PagingSource<Int, CategoryEntity>

    @Query("SELECT * FROM category_entity WHERE categoryType =:categoryType")
    suspend fun getFavoriteImagesOfCategoryType(categoryType: CategoryType): List<CategoryEntity>

    @Query("DELETE FROM category_entity WHERE categoryType =:categoryType")
    suspend fun clearAll(categoryType: CategoryType)

    @Query("UPDATE category_entity SET isFavorite =:isFavorite WHERE itemNo=:itemNo ")
    suspend fun categoryFavoriteUpdate(itemNo: Int, isFavorite: Boolean)


}