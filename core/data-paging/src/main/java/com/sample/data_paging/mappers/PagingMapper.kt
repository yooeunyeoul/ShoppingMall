package com.sample.data_paging.mappers

import com.sample.domain.util.CategoryType
import com.sample.localdata.local.CategoryEntity
import com.sample.localdata.local.FeedEntity
import com.sample.network.model.CategoryDto
import com.sample.network.model.FeedDto

fun CategoryDto.toCategoryEntity(categoryType: CategoryType,favoriteCategoryMap: Map<Int, Boolean>): CategoryEntity {
    return CategoryEntity(
        itemNo = item_no,
        brandName = brand_name,
        heartCount = heart_count,
        imageUrl = image_url,
        itemName = item_name,
        reviewAveragePoint = review_average_point,
        reviewCount = review_count,
        isFavorite = favoriteCategoryMap[item_no] ?:false,
        categoryType = categoryType
    )
}

fun FeedDto.toFeedEntity(): FeedEntity {
    return FeedEntity(
        feedNo = feed_no,
        feedContents = feed_contents,
        feedTitle = feed_title,
        imageUrl = image_url
    )
}

fun List<CategoryEntity>.toMap(): Map<Int, Boolean> {
    return associate { categoryEntity -> categoryEntity.itemNo to categoryEntity.isFavorite }
}