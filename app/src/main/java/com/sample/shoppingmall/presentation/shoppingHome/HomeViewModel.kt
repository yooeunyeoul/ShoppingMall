package com.sample.shoppingmall.presentation.shoppingHome

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.sample.common_utils.PreferencesKeys
import com.sample.domain.usecase.CategoryUpdateFavoriteCase
import com.sample.domain.usecase.GetBannerListUseCase
import com.sample.domain.usecase.GetCategoryListUseCase
import com.sample.domain.usecase.GetFeedListUseCase
import com.sample.domain.util.CategoryType
import com.sample.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalPagerApi::class)
@HiltViewModel
class HomeViewModel @Inject constructor(
    getBannerListUseCase: GetBannerListUseCase,
    getCategoryListUseCase: GetCategoryListUseCase,
    private val categoryUpdateUseCase: CategoryUpdateFavoriteCase,
    getFeedListUseCase: GetFeedListUseCase,
    private val dataStore: DataStore<Preferences>

) : ViewModel() {


    var pagerState: PagerState = PagerState()

    init {
        viewModelScope.launch {
            pagerState = dataStore.data.map { preferences ->
                val tabNumber = preferences[PreferencesKeys.SAVED_TAB_INDEX] ?: 0
                PagerState(currentPage = tabNumber)
            }.first()
        }
    }

    val bannerList = getBannerListUseCase().stateIn(
        viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = Resource.Loading()
    )

    val womenList =
        getCategoryListUseCase(categoryType = CategoryType.WOMEN).flowOn(Dispatchers.IO)
            .stateIn(
                viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = PagingData.empty()
            ).cachedIn(viewModelScope)

    val menList =
        getCategoryListUseCase(categoryType = CategoryType.MEN).flowOn(Dispatchers.IO).stateIn(
            viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = PagingData.empty()
        ).cachedIn(viewModelScope)

    val techList =
        getCategoryListUseCase(categoryType = CategoryType.TECH).flowOn(Dispatchers.IO).stateIn(
            viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = PagingData.empty()
        ).cachedIn(viewModelScope)

    val homeList =
        getCategoryListUseCase(categoryType = CategoryType.HOME).flowOn(Dispatchers.IO).stateIn(
            viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = PagingData.empty()
        ).cachedIn(viewModelScope)

    val feedList =
        getFeedListUseCase().flowOn(Dispatchers.IO).stateIn(
            viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = PagingData.empty()
        ).cachedIn(viewModelScope)

    fun onEvent(events: ShoppingHomeEvents) {
        viewModelScope.launch {
            when (events) {
                is ShoppingHomeEvents.AddFavorite -> {
                    categoryUpdateUseCase(events.category.copy(isFavorite = true))
                }

                is ShoppingHomeEvents.DeleteFavorite -> {
                    categoryUpdateUseCase(events.category.copy(isFavorite = false))
                }
            }
        }
    }

    fun saveTabIndex() {
        viewModelScope.launch {
            dataStore.edit { preferences ->
                preferences[PreferencesKeys.SAVED_TAB_INDEX] = pagerState.currentPage
            }
        }
    }
}