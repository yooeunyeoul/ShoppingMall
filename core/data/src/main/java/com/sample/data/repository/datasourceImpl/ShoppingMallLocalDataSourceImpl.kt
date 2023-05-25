package com.sample.data.repository.datasourceImpl

import com.sample.data.repository.datasource.ShoppingMallLocalDataSource
import com.sample.domain.model.Category
import com.sample.localdata.local.ShoppingMallDatabase

class ShoppingMallLocalDataSourceImpl(
    private val db: ShoppingMallDatabase
) : ShoppingMallLocalDataSource {
    override suspend fun categoryFavoriteUpdateToDB(category: Category) {
        db.categoryDao().categoryFavoriteUpdate(category.itemNo, category.isFavorite)
    }


}