package com.sample.common_utils.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.sample.common_utils.Constants


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = Constants.SHOPPING_MALL_PREFERENCES)