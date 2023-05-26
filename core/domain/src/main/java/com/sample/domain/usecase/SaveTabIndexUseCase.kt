package com.sample.domain.usecase

import com.sample.domain.repository.ShoppingMallRepository
import javax.inject.Inject

class SaveTabIndexUseCase @Inject constructor(
    private val repository: ShoppingMallRepository
) {
    suspend operator fun invoke(index:Int) {
        return repository.saveTabIndex(index)
    }
}