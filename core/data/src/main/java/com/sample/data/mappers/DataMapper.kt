package com.sample.data.mappers

import com.sample.domain.model.Banner
import com.sample.domain.model.Category
import com.sample.domain.model.Feed
import com.sample.domain.model.SaleInfo
import com.sample.localdata.local.BannerEntity
import com.sample.localdata.local.CategoryEntity
import com.sample.localdata.local.FeedEntity
import com.sample.network.model.BannerDto

fun BannerDto.toBanner(): Banner {
    return Banner(bannerNo = banner_no, imageUrl = image_url)
}

fun BannerDto.toBannerEntity(): BannerEntity {
    return BannerEntity(bannerNo = banner_no, imageUrl = image_url)
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
        isFavorite = isFavorite,
        saleInfo = SaleInfo(
            consumerPrice = consumerPrice,
            couponSaleRate = couponSaleRate,
            isCoupon = isCoupon,
            saleRate = saleRate,
            sellPrice = sellPrice,
            totalSellPrice = totalSellPrice,
            totalSaleRate = totalSaleRate
        )
    )
}

fun FeedEntity.toFeed(): Feed {
    return Feed(
        feedNo = feedNo,
        feedContents = feedContents,
        feedTitle = feedTitle,
        imageUrl = imageUrl
    )
}