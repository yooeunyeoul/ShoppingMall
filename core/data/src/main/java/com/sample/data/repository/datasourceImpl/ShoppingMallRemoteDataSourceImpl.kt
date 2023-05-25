@file:OptIn(ExperimentalPagingApi::class, ExperimentalPagingApi::class)

package com.sample.data.repository.datasourceImpl

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import androidx.room.withTransaction
import com.sample.data.mappers.toBanner
import com.sample.data.mappers.toBannerEntity
import com.sample.data.mappers.toCategory
import com.sample.data.mappers.toFeed
import com.sample.data.repository.datasource.ShoppingMallRemoteDataSource
import com.sample.data_paging.CategoryListRemoteMediator
import com.sample.data_paging.FeedListRemoteMediator
import com.sample.domain.model.Banner
import com.sample.domain.model.Category
import com.sample.domain.model.Feed
import com.sample.domain.response.NetworkResult
import com.sample.domain.util.CategoryType
import com.sample.domain.util.Resource
import com.sample.localdata.local.BannerEntity
import com.sample.localdata.local.CategoryEntity
import com.sample.localdata.local.FeedEntity
import com.sample.localdata.local.ShoppingMallDatabase
import com.sample.network.remote.ShoppingApi
import com.sample.network.response.toNetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

@OptIn(ExperimentalPagingApi::class)
class ShoppingMallRemoteDataSourceImpl(
    private val api: ShoppingApi,
    private val db: ShoppingMallDatabase
) : ShoppingMallRemoteDataSource {
    override fun getBannerResponse(): Flow<Resource<List<Banner>>> {
        return flow {
            try {
                when (val networkResponse = api.getBannerList().toNetworkResult()) {
                    is NetworkResult.Success -> {
                        var bannerEntityList = listOf<BannerEntity>()
                        db.withTransaction {
                            db.bannerDao().clearAll()
                            db.bannerDao()
                                .upsertAll(networkResponse.data.map { bannerDto -> bannerDto.toBannerEntity() })
                            bannerEntityList = db.bannerDao().getBannerList().first()
                        }
                        emit(Resource.Success(data = bannerEntityList.map { bannerEntity -> bannerEntity.toBanner() }))
                    }

                    is NetworkResult.Fail -> {
                        throw Exception(networkResponse.message)
                    }
                }
            } catch (e: Exception) {
                val bannerEntityList = db.bannerDao().getBannerList().first()
                val bannerList = bannerEntityList.map { it.toBanner() }
                emit(Resource.Error(message = e.message ?: "", data = bannerList))
            }
        }
    }

    @OptIn(ExperimentalPagingApi::class)
    override fun getCategoryList(categoryType: CategoryType): Flow<PagingData<Category>> {
        val pagingSourceFactory = { db.categoryDao().pagingSource(categoryType) }
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
        val pagingSourceFactory = { db.feedDao().pagingSource() }
        return Pager(
            config = PagingConfig(pageSize = 10),
            remoteMediator = FeedListRemoteMediator(
                api = api,
                db = db
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow.map { value: PagingData<FeedEntity> ->
            value.map { feedEntity -> feedEntity.toFeed() }
        }
    }

}