package com.sample.data_paging.fake

import com.sample.domain.util.CategoryType
import com.sample.network.base.CommonListResponse
import com.sample.network.base.CommonPagingListResponse
import com.sample.network.model.BannerDto
import com.sample.network.model.CategoryDto
import com.sample.network.model.FeedDto
import com.sample.network.remote.ShoppingApi
import java.io.IOException

class MockShoppingApi : ShoppingApi {
    val bannerList = CommonListResponse<BannerDto>(list = listOf(), result = "Success")

    var categoryList =
        CommonPagingListResponse<CategoryDto>(list = listOf(), result = "Success", total_count = 0)

    val feedList =
        CommonPagingListResponse<FeedDto>(list = listOf(), result = "Success", total_count = 0)

    var failureMsg: String? = null

    override suspend fun getBannerList(): CommonListResponse<BannerDto> {
        return bannerList
    }

    override suspend fun getCategoryList(
        category: CategoryType,
        pageNum: Int
    ): CommonPagingListResponse<CategoryDto> {
        failureMsg?.let { errorMessage ->
            throw IOException(errorMessage)
        }
        return categoryList
    }

    override suspend fun getFeedList(pageNum: Int): CommonPagingListResponse<FeedDto> {
        return feedList
    }

    fun setCategoryCommonListResponse(mockCategoryList: CommonPagingListResponse<CategoryDto>) {
        categoryList = mockCategoryList
    }
}