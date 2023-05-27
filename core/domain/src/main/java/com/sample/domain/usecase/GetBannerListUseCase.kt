package com.sample.domain.usecase

import com.sample.common_utils.utils.Resource
import com.sample.domain.model.Banner
import com.sample.domain.repository.ShoppingMallRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBannerListUseCase @Inject constructor(
    private val repository: ShoppingMallRepository
) {
    operator fun invoke(): Flow<Resource<List<Banner>>> {
        return repository.getBannerList()
    }
}