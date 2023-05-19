package com.sample.network.remote

import com.sample.network.model.BannerResponse
import com.sample.network.model.CategoryResponse
import com.sample.network.model.FeedResponse
import com.sample.network.util.CategoryType
import retrofit2.http.GET
import retrofit2.http.Path

interface ShoppingApi {
    @GET("banner_list/bannerlist.json")
    suspend fun getBannerList(
    ): BannerResponse

    @GET("product_list/{category}/page{page}.json")
    suspend fun getCategoryList(
        @Path("category") category: CategoryType,
        @Path("pageNum") pageNum: Int
    ): CategoryResponse

    @GET("banner_list/feed_list/page{page}.json")
    suspend fun getFeedList(
        @Path("pageNum") pageNum: Int
    ): FeedResponse
}