package com.sample.domain.repository

import androidx.paging.PagingData
import com.sample.domain.model.Banner
import com.sample.domain.model.Category
import com.sample.domain.model.Feed
import kotlinx.coroutines.flow.Flow

interface ShoppingMallRepository {
    fun getBannerList(): List<Banner>
    fun getProductList(): Flow<PagingData<Category>>
    fun getFeedList(): Flow<PagingData<Feed>>
}