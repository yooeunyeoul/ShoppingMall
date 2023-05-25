package com.sample.data_paging

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.sample.data_paging.mappers.toFeedEntity
import com.sample.domain.response.NetworkResult
import com.sample.localdata.local.FeedEntity
import com.sample.localdata.local.FeedRemoteKeysEntity
import com.sample.localdata.local.ShoppingMallDatabase
import com.sample.network.remote.ShoppingApi
import com.sample.network.response.toNetworkResult

@OptIn(ExperimentalPagingApi::class)
class FeedListRemoteMediator(
    private val api: ShoppingApi,
    private val db: ShoppingMallDatabase
) : RemoteMediator<Int, FeedEntity>() {

    private val feedDao = db.feedDao()
    private val feedRemoteKeysDao = db.feedRemoteKeysDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, FeedEntity>
    ): MediatorResult {
        return try {

            val page = when (loadType) {
                LoadType.REFRESH -> {
                    1
                }

                LoadType.PREPEND -> {
                    return MediatorResult.Success(endOfPaginationReached = true)
                }

                LoadType.APPEND -> {
                    val imageRemoteKeysEntity = db.withTransaction {
                        feedRemoteKeysDao.getImageRemoteKey()
                    }
                    if (imageRemoteKeysEntity?.nextPage == null) {
                        return MediatorResult.Success(endOfPaginationReached = true)
                    }
                    imageRemoteKeysEntity.nextPage
                }
            }
            var isPagingEnd = false
            var feedList = listOf<FeedEntity>()
            page?.let { pageNum ->
                when (val networkResponse =
                    api.getFeedList(pageNum = pageNum)
                        .toNetworkResult()) {
                    is NetworkResult.Success -> {
                        val responseSize = networkResponse.data.size
                        isPagingEnd = responseSize < 10
                        feedList =
                            networkResponse.data.map { feedDto -> feedDto.toFeedEntity() }
                    }

                    is NetworkResult.Fail -> {
                        isPagingEnd = true
                    }
                }

                db.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        feedDao.clearAll()
                        feedRemoteKeysDao.deleteAllImageRemoteKeys()
                    }
                    val nextPage = pageNum + 1
                    val prevPage: Int? = if (pageNum <= 1) null else pageNum

                    val keys = feedList.map { feedEntity ->
                        FeedRemoteKeysEntity(
                            id = feedEntity.feedNo,
                            prevPage = prevPage,
                            nextPage = nextPage
                        )
                    }
                    feedRemoteKeysDao.addAllImageRemoteKeys(imageRemoteKeys = keys)
                    feedDao.upsertAll(images = feedList)
                }
            }

            MediatorResult.Success(endOfPaginationReached = isPagingEnd)
        } catch (e: Exception) {
            Log.e("Error", e.message ?: "")
            return MediatorResult.Error(e)
        }
    }
}