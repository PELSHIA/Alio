package com.project.alio.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BaseViewModel: ViewModel() {
    protected val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun loading() {
        _isLoading.value = true
    }

    fun stopLoading() {
        _isLoading.value = false
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

}