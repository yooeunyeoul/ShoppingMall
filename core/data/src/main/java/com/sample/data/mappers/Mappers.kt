package com.sample.data.mappers

import com.sample.domain.model.Banner
import com.sample.domain.model.Category
import com.sample.domain.model.SaleInfo
import com.sample.localdata.local.BannerEntity
import com.sample.localdata.local.CategoryEntity
import com.sample.network.model.BannerDto
import com.sample.network.model.CategoryDto

fun BannerDto.toBanner(): Banner {
    return Banner(bannerNo = banner_no, imageUrl = image_url)
}

fun BannerEntity.toBanner(): Banner {
    return Banner(bannerNo = bannerNo, imageUrl = imageUrl)
}

fun CategoryEntity.toCategory(): Category {
    return Category(
        brandName = brandName,
        heartCount = heartCount,
        imageUrl = imageUrl,
        itemName = itemName,
        itemNo = itemNo,
        reviewAveragePoint = reviewAveragePoint,
        reviewCount = reviewCount,
        saleInfo = SaleInfo()
    )
}