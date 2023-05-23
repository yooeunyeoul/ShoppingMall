package com.sample.network.remote

import com.sample.domain.util.CategoryType
import com.sample.network.base.CommonListResponse
import com.sample.network.base.CommonPagingListResponse
import com.sample.network.model.BannerDto
import com.sample.network.model.CategoryDto
import com.sample.network.model.FeedDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ShoppingApi {
    @GET("banner_list/bannerlist.json")
    suspend fun getBannerList(
    ): CommonListResponse<BannerDto>

    @GET("product_list/{category}/page{pageNum}.json")
    suspend fun getCategoryList(
        @Path("category") category: CategoryType,
        @Path("pageNum") pageNum: Int
    ): CommonPagingListResponse<CategoryDto>

    @GET("feed_list/page{pageNum}.json")
    suspend fun getFeedList(
        @Path("pageNum") pageNum: Int
    ): CommonPagingListResponse<FeedDto>
}