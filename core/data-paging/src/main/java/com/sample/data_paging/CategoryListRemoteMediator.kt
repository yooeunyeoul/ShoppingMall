package com.sample.data_paging

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.sample.common_utils.Constants.CATEGORY_PER_PAGE_SIZE
import com.sample.common_utils.utils.CategoryType
import com.sample.data_paging.mappers.toCategoryEntity
import com.sample.data_paging.mappers.toMap
import com.sample.domain.response.NetworkResult
import com.sample.localdata.local.CategoryEntity
import com.sample.localdata.local.CategoryRemoteKeysEntity
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
    private val categoryRemoteKeysDao = db.categoryRemoteKeysDao()
    lateinit var favoriteCategoryMap: Map<Int, Boolean>

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CategoryEntity>
    ): MediatorResult {

        if (!::favoriteCategoryMap.isInitialized) {
            favoriteCategoryMap = categoryDao.getFavoriteImagesOfCategoryType(categoryType).toMap()
        }
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
                        categoryRemoteKeysDao.getImageRemoteKey(categoryType)
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
                val networkResponse =
                    api.getCategoryList(category = categoryType, pageNum = pageNum)
                when (val networkResult = networkResponse.toNetworkResult()) {
                    is NetworkResult.Success -> {
                        isPagingEnd = networkResponse.total_count <= CATEGORY_PER_PAGE_SIZE * pageNum
                        categoryList =
                            networkResult.data.map { categoryDto ->
                                categoryDto.toCategoryEntity(
                                    categoryType, favoriteCategoryMap
                                )
                            }
                    }

                    is NetworkResult.Fail -> {
                        isPagingEnd = true
                    }
                }

                db.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        categoryDao.clearAll(categoryType)
                        categoryRemoteKeysDao.deleteAllImageRemoteKeys(categoryType)
                    }
                    val nextPage = pageNum + 1
                    val prevPage: Int? = if (pageNum <= 1) null else pageNum

                    val keys = categoryList.map { categoryEntity ->
                        CategoryRemoteKeysEntity(
                            id = categoryEntity.itemNo,
                            prevPage = prevPage,
                            nextPage = nextPage,
                            categoryType = categoryType
                        )
                    }
                    categoryRemoteKeysDao.addAllImageRemoteKeys(imageRemoteKeys = keys)
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