package com.sample.data_paging

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.sample.data_paging.mappers.toCategoryEntity
import com.sample.domain.response.NetworkResult
import com.sample.domain.util.CategoryType
import com.sample.localdata.local.CategoryEntity
import com.sample.localdata.local.ImageRemoteKeysEntity
import com.sample.localdata.local.ShoppingMallDatabase
import com.sample.network.remote.ShoppingApi
import com.sample.network.response.toNetworkResult

@OptIn(ExperimentalPagingApi::class)
class CategoryListRemoteMediator(
    private val api: ShoppingApi,
    private val db: ShoppingMallDatabase,
    private val categoryType: CategoryType
) : RemoteMediator<Int, CategoryEntity>() {

    private val categoryDao = db.categoryDao()
    private val imageRemoteKeysDao = db.imageRemoteKeysDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CategoryEntity>
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
                        imageRemoteKeysDao.getImageRemoteKey()
                    }
                    if (imageRemoteKeysEntity?.nextPage == null) {
                        return MediatorResult.Success(endOfPaginationReached = true)
                    }
                    imageRemoteKeysEntity.nextPage
                }
            }
            var isPagingEnd = false
            var categoryList = listOf<CategoryEntity>()
            page?.let { pageNum ->
                when (val networkResponse =
                    api.getCategoryList(category = categoryType, pageNum = pageNum)
                        .toNetworkResult()) {
                    is NetworkResult.Success -> {
                        val responseSize = networkResponse.data.size
                        isPagingEnd = responseSize < 20
                        categoryList =
                            networkResponse.data.map { categoryDto -> categoryDto.toCategoryEntity() }
                    }

                    is NetworkResult.Fail -> {
                        isPagingEnd = true
                    }
                }

                db.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        categoryDao.clearAll()
                        imageRemoteKeysDao.deleteAllImageRemoteKeys()
                    }
                    val nextPage = pageNum + 1
                    val prevPage: Int? = if (pageNum <= 1) null else pageNum

                    val keys = categoryList.map { ImageEntity ->
                        ImageRemoteKeysEntity(
                            id = ImageEntity.itemNo,
                            prevPage = prevPage,
                            nextPage = nextPage
                        )
                    }
                    imageRemoteKeysDao.addAllImageRemoteKeys(imageRemoteKeys = keys)
                    categoryDao.upsertAll(images = categoryList)
                }
            }

            MediatorResult.Success(endOfPaginationReached = isPagingEnd)
        } catch (e: Exception) {
            Log.e("Error", e.message ?: "")
            return MediatorResult.Error(e)
        }
    }
}