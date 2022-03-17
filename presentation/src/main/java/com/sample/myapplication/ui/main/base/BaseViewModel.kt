package com.sample.myapplication.ui.main.base

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel() : ViewModel() {

    private val _toastMessage: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val toastMessage: LiveData<String> get() = _toastMessage

    private val _loader: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    lateinit var connectivityLiveData: ConnectionLiveData

    val loader: LiveData<Boolean> get() = _loader

    fun showLoader() {
        _loader.postValue(true)
    }

    fun hideLoader() {
        _loader.postValue(false)
    }

    fun showToast(error: String) {
        _toastMessage.postValue(error)
    }

    fun initialize(context: Context) {
        connectivityLiveData = ConnectionLiveData(context)
    }
}