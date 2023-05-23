package com.sample.shoppingmall.presentation.shoppingHome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sample.domain.usecase.GetBannerListUseCase
import com.sample.domain.usecase.GetCategoryListUseCase
import com.sample.domain.usecase.GetFeedListUseCase
import com.sample.domain.util.CategoryType
import com.sample.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    bannerUseCase: GetBannerListUseCase,
    categoryUseCase: GetCategoryListUseCase,
    feedUseCase: GetFeedListUseCase,

) : ViewModel() {

    val bannerList = bannerUseCase().stateIn(
        viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = Resource.Loading()
    )

    val womenList =
        categoryUseCase(categoryType = CategoryType.WOMEN).flowOn(Dispatchers.IO)
            .cachedIn(viewModelScope).stateIn(
                viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = PagingData.empty()
            )

    val menList =
        categoryUseCase(categoryType = CategoryType.MEN).flowOn(Dispatchers.IO).cachedIn(viewModelScope).stateIn(
            viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = PagingData.empty()
        )
    val techList =
        categoryUseCase(categoryType = CategoryType.TECH).flowOn(Dispatchers.IO).cachedIn(viewModelScope).stateIn(
            viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = PagingData.empty()
        )

    val homeList =
        categoryUseCase(categoryType = CategoryType.HOME).flowOn(Dispatchers.IO).cachedIn(viewModelScope).stateIn(
            viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = PagingData.empty()
        )

    val feedList =
        feedUseCase().flowOn(Dispatchers.IO).cachedIn(viewModelScope).stateIn(
            viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = PagingData.empty()
        )

}