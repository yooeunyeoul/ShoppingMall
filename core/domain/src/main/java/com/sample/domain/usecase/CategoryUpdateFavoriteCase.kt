package com.sample.domain.usecase

import com.sample.domain.model.Category
import com.sample.domain.repository.ShoppingMallRepository
import javax.inject.Inject

class CategoryUpdateFavoriteCase @Inject constructor(
    private val repository: ShoppingMallRepository
) {
    suspend operator fun invoke(category: Category) {
        return repository.updateCategoryFavorite(category)
    }
}