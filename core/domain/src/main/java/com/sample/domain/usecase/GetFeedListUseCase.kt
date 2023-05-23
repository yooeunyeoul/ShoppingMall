package com.sample.domain.usecase

import androidx.paging.PagingData
import com.sample.domain.model.Feed
import com.sample.domain.repository.ShoppingMallRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFeedListUseCase @Inject constructor(
    private val repository: ShoppingMallRepository
) {
    operator fun invoke(): Flow<PagingData<Feed>> {
        return repository.getFeedList()
    }
}