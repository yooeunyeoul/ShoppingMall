package com.sample.domain.usecase

import androidx.paging.PagingData
import com.sample.domain.model.Category
import com.sample.domain.repository.ShoppingMallRepository
import com.sample.domain.util.CategoryType
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoryListUseCase @Inject constructor(
    private val repository: ShoppingMallRepository
) {
    operator fun invoke(categoryType: CategoryType): Flow<PagingData<Category>> {
        return repository.getCategoryList(categoryType)
    }
}