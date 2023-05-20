package com.sample.data.repository.datasourceImpl

import androidx.paging.PagingData
import com.sample.data.mappers.toBanner
import com.sample.data.repository.datasource.ShoppingMallRemoteDataSource
import com.sample.domain.model.Banner
import com.sample.domain.model.Feed
import com.sample.localdata.local.ShoppingMallDatabase
import com.sample.network.remote.ShoppingApi
import com.sample.network.response.NetworkResult
import com.sample.network.response.toNetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class ShoppingMallRemoteDataSourceImpl(
    private val api: ShoppingApi,
    private val db: ShoppingMallDatabase
) : ShoppingMallRemoteDataSource {
    override suspend fun getBannerResponse(): Flow<NetworkResult<List<Banner>>> {
        val networkResponse = api.getBannerList().baseListResponse.toNetworkResult()
        return when (networkResponse) {
            is NetworkResult.Success -> {
                flow { NetworkResult.Success(data = networkResponse.data.map { it.toBanner() }) }
            }

            is NetworkResult.Fail -> {
                flow {
                    NetworkResult.Fail(
                        data = db.imageDao().getBannerList().map { it.map { it.toBanner() } },
                        networkResponse.message
                    )
                }
            }
        }
    }


    override fun getCategoryList(): Flow<PagingData<List<Banner>>> {
        TODO("Not yet implemented")
    }

    override fun getFeedList(): Flow<PagingData<List<Feed>>> {
        TODO("Not yet implemented")
    }

}