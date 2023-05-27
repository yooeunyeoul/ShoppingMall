package com.sample.shoppingmall.presentation.shoppingHome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.sample.common_utils.utils.CategoryType
import com.sample.common_utils.utils.Resource
import com.sample.domain.usecase.CategoryUpdateFavoriteCase
import com.sample.domain.usecase.GetBannerListUseCase
import com.sample.domain.usecase.GetCategoryListUseCase
import com.sample.domain.usecase.GetFeedListUseCase
import com.sample.domain.usecase.GetSavedTabIndexUseCase
import com.sample.domain.usecase.SaveTabIndexUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalPagerApi::class)
@HiltViewModel
class HomeViewModel @Inject constructor(
    getBannerListUseCase: GetBannerListUseCase,
    getCategoryListUseCase: GetCategoryListUseCase,
    private val categoryUpdateUseCase: CategoryUpdateFavoriteCase,
    getFeedListUseCase: GetFeedListUseCase,
    private val getSavedTabIndexUseCase: GetSavedTabIndexUseCase,
    private val saveTabIndexUseCase: SaveTabIndexUseCase

) : ViewModel() {


    private val _pagerState = MutableStateFlow(PagerState())
    val pagerState = _pagerState.asStateFlow()

    init {
        getSavedTabIndex()
    }

    private fun getSavedTabIndex() {
        viewModelScope.launch {
            getSavedTabIndexUseCase().map { index ->
                PagerState(index)
            }.collect{pagerState->
                _pagerState.update { pagerState }
            }
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

                is ShoppingHomeEvents.SaveTabIndex -> {
                    saveTabIndexUseCase(pagerState.value.currentPage)
                }
            }
        }
    }
}