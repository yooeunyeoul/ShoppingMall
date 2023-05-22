package com.sample.data.repository.datasource

import androidx.paging.PagingData
import com.sample.domain.model.Banner
import com.sample.domain.model.Category
import com.sample.domain.model.Feed
import com.sample.domain.util.CategoryType
import com.sample.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface ShoppingMallRemoteDataSource {
    fun getBannerResponse(): Flow<Resource<List<Banner>>>
    fun getCategoryList(categoryType: CategoryType): Flow<PagingData<Category>>
    fun getFeedList(): Flow<PagingData<Feed>>
}