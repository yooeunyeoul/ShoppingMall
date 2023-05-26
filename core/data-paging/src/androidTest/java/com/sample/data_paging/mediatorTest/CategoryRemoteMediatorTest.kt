package com.sample.data_paging.mediatorTest

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingConfig
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sample.common_utils.Constants
import com.sample.data_paging.CategoryListRemoteMediator
import com.sample.data_paging.factory.CategoryResponseFactory
import com.sample.data_paging.fake.MockShoppingApi
import com.sample.domain.util.CategoryType
import com.sample.localdata.local.CategoryEntity
import com.sample.localdata.local.ShoppingMallDatabase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith


@ExperimentalPagingApi
@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class CategoryRemoteMediatorTest {

    private val mockApi = MockShoppingApi()
    private val mockDb = Room.inMemoryDatabaseBuilder(
        ApplicationProvider.getApplicationContext(), ShoppingMallDatabase::class.java
    ).build()

    private val categoryFactory = CategoryResponseFactory()

    @Test
    fun `카테고리_API_리턴_total_count가_50이고_리턴_listCount가_20일때_페이징_refresh를_호출했을때_pagingState_가_success이고_페이징의_끝에_도달값_false_인지`() =
        runTest {
            val categoryType = CategoryType.HOME
            mockApi.setCategoryCommonListResponse(
                categoryFactory.createCategoryResponse(
                    apiResultListCount = Constants.CATEGORY_PER_PAGE_SIZE,
                    apiTotalListCount = 50
                )
            )
            val remoteMediator = CategoryListRemoteMediator(
                mockApi,
                mockDb,
                categoryType
            )


            val pagingState = PagingState<Int, CategoryEntity>(
                listOf(),
                null,
                PagingConfig(10),
                10
            )
            val result = remoteMediator.load(LoadType.REFRESH, pagingState)
            assertTrue(result is RemoteMediator.MediatorResult.Success)
            val successResult = result as RemoteMediator.MediatorResult.Success
            assertFalse(successResult.endOfPaginationReached)
        }

    @Test
    fun `카테고리_API_리턴_total_count가_20이고_리턴_listCount가_20일때_페이징_refresh를_호출했을때_pagingState_가_success이고_페이징의_끝에_도달값_true_인지`() =
        runTest {
            val categoryType = CategoryType.HOME
            mockApi.setCategoryCommonListResponse(
                categoryFactory.createCategoryResponse(
                    apiResultListCount = Constants.CATEGORY_PER_PAGE_SIZE,
                    apiTotalListCount = 20
                )
            )
            val remoteMediator = CategoryListRemoteMediator(
                mockApi,
                mockDb,
                categoryType
            )


            val pagingState = PagingState<Int, CategoryEntity>(
                listOf(),
                null,
                PagingConfig(10),
                10
            )
            val result = remoteMediator.load(LoadType.REFRESH, pagingState)
            assertTrue(result is RemoteMediator.MediatorResult.Success)
            val successResult = result as RemoteMediator.MediatorResult.Success
            assertTrue(successResult.endOfPaginationReached)
        }

    @Test
    fun `카테고리_api_호출에서_exception_이_발생했을때_paging_state_가_error로_표시되는지`() =
        runTest {
            val categoryType = CategoryType.HOME
            mockApi.failureMsg = "Throw test failure"
            val remoteMediator = CategoryListRemoteMediator(
                mockApi,
                mockDb,
                categoryType
            )
            val pagingState = PagingState<Int, CategoryEntity>(
                listOf(),
                null,
                PagingConfig(10),
                10
            )
            val result = remoteMediator.load(LoadType.REFRESH, pagingState)
            assertTrue(result is RemoteMediator.MediatorResult.Error)
        }


    @After
    fun tearDown() {
        mockDb.clearAllTables()
    }


}