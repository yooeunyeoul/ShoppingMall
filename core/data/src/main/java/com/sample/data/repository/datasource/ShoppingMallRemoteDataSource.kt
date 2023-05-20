package com.sample.data.repository.datasource

import androidx.paging.PagingData
import com.sample.domain.model.Banner
import com.sample.domain.model.Feed
import com.sample.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface ShoppingMallRemoteDataSource {
    fun getBannerResponse(): Flow<Resource<List<Banner>>>
    fun getCategoryList(): Flow<PagingData<List<Banner>>>
    fun getFeedList(): Flow<PagingData<List<Feed>>>
}