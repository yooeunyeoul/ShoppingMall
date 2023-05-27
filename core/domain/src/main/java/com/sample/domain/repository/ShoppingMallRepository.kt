package com.sample.domain.repository

import androidx.paging.PagingData
import com.sample.common_utils.utils.CategoryType
import com.sample.common_utils.utils.Resource
import com.sample.domain.model.Banner
import com.sample.domain.model.Category
import com.sample.domain.model.Feed
import kotlinx.coroutines.flow.Flow

interface ShoppingMallRepository {
    fun getBannerList(): Flow<Resource<List<Banner>>>
    fun getCategoryList(categoryType: CategoryType): Flow<PagingData<Category>>
    fun getFeedList(): Flow<PagingData<Feed>>
    suspend fun updateCategoryFavorite(category: Category)
    fun getSavedTabIndex(): Flow<Int>
    suspend fun saveTabIndex(index: Int)
}