package com.sample.network.model

import com.sample.network.base.BaseListResponse

data class BannerResponse(
    val baseListResponse: BaseListResponse<BannerDto>
)