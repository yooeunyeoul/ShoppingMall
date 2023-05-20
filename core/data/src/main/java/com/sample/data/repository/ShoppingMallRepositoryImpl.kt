package com.sample.data.repository

import androidx.paging.PagingData
import com.sample.domain.model.Banner
import com.sample.domain.model.Category
import com.sample.domain.model.Feed
import com.sample.domain.repository.ShoppingMallRepository
import kotlinx.coroutines.flow.Flow

class ShoppingMallRepositoryImpl() : ShoppingMallRepository {
    override fun getBannerList(): List<Banner> {
        TODO("Not yet implemented")
    }

    override fun getProductList(): Flow<PagingData<Category>> {
        TODO("Not yet implemented")
    }

    override fun getFeedList(): Flow<PagingData<Feed>> {
        TODO("Not yet implemented")
    }
}