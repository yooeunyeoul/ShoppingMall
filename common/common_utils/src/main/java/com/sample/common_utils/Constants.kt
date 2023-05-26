package com.sample.common_utils

import androidx.datastore.preferences.core.intPreferencesKey

object Constants {
    const val SHOPPING_MALL_PREFERENCES = "shopping_mall_preferences"
    const val CATEGORY_PER_PAGE_SIZE = 20
    const val FEED_PER_PAGE_SIZE = 10
    const val PRE_LOAD_SIZE = 10

}
object PreferencesKeys {
    val SAVED_TAB_INDEX = intPreferencesKey("tab_index")

}