package com.sample.data.repository.datasource

import androidx.paging.PagingData
import com.sample.domain.model.Banner
import com.sample.domain.model.Feed
import com.sample.network.response.NetworkResult
import kotlinx.coroutines.flow.Flow

interface ShoppingMallRemoteDataSource {
    suspend fun getBannerResponse(): Flow<NetworkResult<List<Banner>>>
    fun getCategoryList(): Flow<PagingData<List<Banner>>>
    fun getFeedList(): Flow<PagingData<List<Feed>>>
}