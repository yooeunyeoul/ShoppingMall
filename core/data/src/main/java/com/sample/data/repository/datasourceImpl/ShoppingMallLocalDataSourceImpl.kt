package com.sample.data.repository.datasourceImpl

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.sample.common_utils.PreferencesKeys
import com.sample.data.repository.datasource.ShoppingMallLocalDataSource
import com.sample.domain.model.Category
import com.sample.localdata.local.ShoppingMallDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ShoppingMallLocalDataSourceImpl(
    private val db: ShoppingMallDatabase,
    private val dataStore: DataStore<Preferences>
) : ShoppingMallLocalDataSource {
    override suspend fun categoryFavoriteUpdateToDB(category: Category) {
        db.categoryDao().categoryFavoriteUpdate(category.itemNo, category.isFavorite)
    }

    override suspend fun saveTabIndexToDataStore(index: Int) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.SAVED_TAB_INDEX] = index
        }
    }

    override fun getSavedTabIndex(): Flow<Int> {
        val tabNumber = dataStore.data.map { preferences ->
            val tabNumber = preferences[PreferencesKeys.SAVED_TAB_INDEX] ?: 0
            tabNumber
        }
        return tabNumber
    }

}