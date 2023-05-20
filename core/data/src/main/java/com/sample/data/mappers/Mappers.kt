package com.sample.data.mappers

import com.sample.domain.model.Banner
import com.sample.localdata.local.BannerEntity
import com.sample.network.model.BannerDto

fun BannerDto.toBanner(): Banner {
    return Banner(bannerNo = banner_no, imageUrl = image_url)
}

fun BannerEntity.toBanner(): Banner {
    return Banner(bannerNo = bannerNo, imageUrl = imageUrl)
}