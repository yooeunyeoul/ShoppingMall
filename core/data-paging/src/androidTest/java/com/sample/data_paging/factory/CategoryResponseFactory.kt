package com.sample.data_paging.factory

import com.sample.common_utils.Constants.API_SUCCESS
import com.sample.network.base.CommonPagingListResponse
import com.sample.network.model.CategoryDto
import com.sample.network.model.SaleInfoDto

class CategoryResponseFactory {

    fun createCategoryResponse(
        apiResultListCount: Int,
        resultMessage: String = API_SUCCESS,
        apiTotalListCount: Int
    ): CommonPagingListResponse<CategoryDto> {
        val mockCategoryDtoList = mutableListOf<CategoryDto>()
        repeat(apiResultListCount) { index ->
            mockCategoryDtoList.add(
                CategoryDto(
                    brand_name = "브랜드네임",
                    heart_count = index * 10,
                    image_url = "ddd",
                    item_name = "아이템 네임",
                    item_no = index,
                    review_average_point = 1.1,
                    review_count = 1,
                    sale_info = SaleInfoDto(
                        coupon_sale_rate = 1,
                        consumer_price = 1,
                        is_coupon = false,
                        sale_rate = 5,
                        total_sale_rate = 5,
                        sell_price = 1,
                        total_sell_price = 1
                    )
                )
            )
        }
        return CommonPagingListResponse(
            error_code = null,
            list = mockCategoryDtoList,
            result = resultMessage,
            total_count = apiTotalListCount
        )
    }
}