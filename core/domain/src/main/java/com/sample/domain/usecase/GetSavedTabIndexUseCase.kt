package com.sample.domain.usecase

import com.sample.domain.repository.ShoppingMallRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSavedTabIndexUseCase @Inject constructor(
    private val repository: ShoppingMallRepository
) {
    operator fun invoke(): Flow<Int> {
        return repository.getSavedTabIndex()
    }
}