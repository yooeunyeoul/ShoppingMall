package com.sample.domain.repository

import androidx.paging.PagingData
import com.sample.domain.model.Banner
import com.sample.domain.model.Category
import com.sample.domain.model.Feed
import com.sample.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface ShoppingMallRepository {
    fun getBannerList(): Flow<Resource<List<Banner>>>
    fun getCategoryList(): Flow<PagingData<Category>>
    fun getFeedList(): Flow<PagingData<Feed>>
}