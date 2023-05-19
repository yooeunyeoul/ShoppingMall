package com.sample.network.remote

import com.sample.network.model.BannerResponse
import retrofit2.http.GET

interface ShoppingApi {
    @GET("banner_list/bannerlist.json")
    suspend fun getBannerList(
    ): BannerResponse
}