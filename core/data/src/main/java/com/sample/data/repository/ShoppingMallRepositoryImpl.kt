package com.sample.data.repository

import androidx.paging.PagingData
import com.sample.data.repository.datasource.ShoppingMallLocalDataSource
import com.sample.data.repository.datasource.ShoppingMallRemoteDataSource
import com.sample.domain.model.Banner
import com.sample.domain.model.Category
import com.sample.domain.model.Feed
import com.sample.domain.repository.ShoppingMallRepository
import com.sample.domain.util.CategoryType
import com.sample.domain.util.Resource
import kotlinx.coroutines.flow.Flow

class ShoppingMallRepositoryImpl(
    private val dataSource: ShoppingMallRemoteDataSource,
    private val localDataSource: ShoppingMallLocalDataSource
) : ShoppingMallRepository {
    override fun getBannerList(): Flow<Resource<List<Banner>>> {
        return dataSource.getBannerResponse()
    }

    override fun getCategoryList(categoryType: CategoryType): Flow<PagingData<Category>> {
        return dataSource.getCategoryList(categoryType)
    }

    override fun getFeedList(): Flow<PagingData<Feed>> {
        return dataSource.getFeedList()
    }

    override suspend fun updateCategoryFavorite(category: Category) {
        localDataSource.categoryFavoriteUpdateToDB(category)
    }

    override fun getSavedTabIndex(): Flow<Int> {
        return localDataSource.getSavedTabIndex()
    }

    override suspend fun saveTabIndex(index:Int) {
        localDataSource.saveTabIndexToDataStore(index)
    }
}