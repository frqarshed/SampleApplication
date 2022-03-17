package com.sample.myapplication.ui.main.listing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sample.domain.interactor.GetAllAdsUseCase
import com.sample.domain.model.Ad
import com.sample.domain.model.Ads
import com.sample.domain.model.OrderType
import com.sample.domain.model.Result
import com.sample.myapplication.ui.main.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdListingViewModel @Inject constructor(
    private val getAllAdsUseCase: GetAllAdsUseCase
) : BaseViewModel() {

    private var sort: OrderType = OrderType.Ascending

    private val _ads: MutableLiveData<List<Ad>> by lazy {
        MutableLiveData<List<Ad>>()
    }

    val ads: LiveData<List<Ad>> get() = _ads

    fun loadData() {
        viewModelScope.launch {
            showLoader()
            val result = getAllAdsUseCase.execute(GetAllAdsUseCase.Params(sort))
            hideLoader()
            when (result) {
                is Result.Success<Ads> -> {
                    val allAds = result.data.ads
                    if (allAds.isNotEmpty()) {
                        _ads.postValue(allAds)
                    }
                }
                is Result.Error -> {
                    result.exception.message?.let { showToast(it) }
                }
            }
        }
    }

    fun sort() {
        sort = when(sort) {
            OrderType.Ascending -> {
                OrderType.Descending
            }
            OrderType.Descending -> {
                OrderType.Ascending
            }
        }
        val currentList = _ads.value!!
        val sortedList = when (sort) {
            OrderType.Ascending -> currentList.sortedBy { it.name }
            OrderType.Descending -> currentList.sortedByDescending { it.name }
        }
        _ads.postValue(sortedList)
    }

}