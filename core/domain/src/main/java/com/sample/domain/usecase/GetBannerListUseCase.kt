package com.sample.domain.usecase

import com.sample.domain.model.Banner
import com.sample.domain.repository.ShoppingMallRepository
import com.sample.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBannerListUseCase @Inject constructor(
    private val repository: ShoppingMallRepository
) {
    operator fun invoke(): Flow<Resource<List<Banner>>> {
        return repository.getBannerList()
    }
}