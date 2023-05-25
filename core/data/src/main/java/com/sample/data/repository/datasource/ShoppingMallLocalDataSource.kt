package com.sample.data.repository.datasource

import com.sample.domain.model.Category

interface ShoppingMallLocalDataSource {
    suspend fun categoryFavoriteUpdateToDB(category: Category)
}