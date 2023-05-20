package com.sample.data.repository.datasourceImpl

import android.util.Log
import androidx.paging.PagingData
import com.sample.data.mappers.toBanner
import com.sample.data.repository.datasource.ShoppingMallRemoteDataSource
import com.sample.domain.model.Banner
import com.sample.domain.model.Feed
import com.sample.domain.response.NetworkResult
import com.sample.domain.util.Resource
import com.sample.localdata.local.ShoppingMallDatabase
import com.sample.network.remote.ShoppingApi
import com.sample.network.response.toNetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import java.lang.Exception

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
                        emit(Resource.Error(message = networkResponse.message ?: "", data = bannerList))
                    }
                }
            } catch (e: Exception) {
                val bannerEntityList = db.imageDao().getBannerList().first()
                val bannerList = bannerEntityList.map { it.toBanner() }
                emit(Resource.Error(message = e.message ?: "", data = bannerList))
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