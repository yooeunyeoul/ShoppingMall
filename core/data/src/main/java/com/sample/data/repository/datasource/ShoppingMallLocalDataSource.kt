package com.sample.data.repository.datasource

import com.sample.domain.model.Category
import kotlinx.coroutines.flow.Flow

interface ShoppingMallLocalDataSource {
    suspend fun categoryFavoriteUpdateToDB(category: Category)
    suspend fun saveTabIndexToDataStore(index:Int)
    fun getSavedTabIndex(): Flow<Int>
}