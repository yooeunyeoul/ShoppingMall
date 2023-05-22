@file:OptIn(ExperimentalPagingApi::class)

package com.sample.data.repository.datasourceImpl

import androidx.paging.ExperimentalPagingApi
import androidx.paging.map
import com.sample.data.mappers.toCategory
import com.sample.localdata.local.CategoryEntity
import kotlinx.coroutines.flow.map
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sample.data.mappers.toBanner
import com.sample.data.repository.datasource.ShoppingMallRemoteDataSource
import com.sample.data_paging.CategoryListRemoteMediator
import com.sample.domain.model.Banner
import com.sample.domain.model.Category
import com.sample.domain.model.Feed
import com.sample.domain.response.NetworkResult
import com.sample.domain.util.CategoryType
import com.sample.domain.util.Resource
import com.sample.localdata.local.ShoppingMallDatabase
import com.sample.network.remote.ShoppingApi
import com.sample.network.response.toNetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class ShoppingMallRemoteDataSourceImpl(
    private val api: ShoppingApi,
    private val db: ShoppingMallDatabase
) : ShoppingMallRemoteDataSource {
    override fun getBannerResponse(): Flow<Resource<List<Banner>>> {
        return flow {
            try {
                when (val networkResponse = api.getBannerList().toNetworkResult()) {
                    is NetworkResult.Success -> {
                        emit(Resource.Success(data = networkResponse.data.map { it.toBanner() }))
                    }

                    is NetworkResult.Fail -> {
                        val bannerEntityList = db.imageDao().getBannerList().first()
                        val bannerList = bannerEntityList.map { it.toBanner() }
                        emit(
                            Resource.Error(
                                message = networkResponse.message ?: "",
                                data = bannerList
                            )
                        )
                    }
                }
            } catch (e: Exception) {
                val bannerEntityList = db.imageDao().getBannerList().first()
                val bannerList = bannerEntityList.map { it.toBanner() }
                emit(Resource.Error(message = e.message ?: "", data = bannerList))
            }
        }
    }

    @OptIn(ExperimentalPagingApi::class)
    override fun getCategoryList(categoryType: CategoryType): Flow<PagingData<Category>> {
        val pagingSourceFactory = { db.imageDao().pagingSource() }
        return Pager(
            config = PagingConfig(pageSize = 10),
            remoteMediator = CategoryListRemoteMediator(
                api = api,
                db = db,
                categoryType = categoryType
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow.map { value: PagingData<CategoryEntity> ->
            value.map { categoryEntity -> categoryEntity.toCategory() }
        }
    }

    override fun getFeedList(): Flow<PagingData<Feed>> {
        TODO("Not yet implemented")
    }

}