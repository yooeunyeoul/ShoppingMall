package com.sample.data.fake

import androidx.paging.PagingData
import com.sample.data.repository.datasource.ShoppingMallRemoteDataSource
import com.sample.domain.model.Banner
import com.sample.domain.model.Category
import com.sample.domain.model.Feed
import com.sample.common_utils.utils.CategoryType
import com.sample.common_utils.utils.Resource
import kotlinx.coroutines.flow.Flow

class FakeShoppingMallRemoteDataSourceImpl:ShoppingMallRemoteDataSource {
    override fun getBannerResponse(): Flow<Resource<List<Banner>>> {
        TODO("Not yet implemented")

    }

    override fun getCategoryList(categoryType: CategoryType): Flow<PagingData<Category>> {
        TODO("Not yet implemented")
    }

    override fun getFeedList(): Flow<PagingData<Feed>> {
        TODO("Not yet implemented")
    }
}