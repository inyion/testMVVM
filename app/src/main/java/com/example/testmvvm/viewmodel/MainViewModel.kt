package com.example.testmvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testmvvm.RxEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class MainViewModel: ViewModel() {
    private val _mainTextData = MutableLiveData<String>()
    val mainTextData:LiveData<String> = _mainTextData
    val userViewModel1 = UserViewModel()
    val userViewModel2 = UserViewModel()
    val userViewModel3 = UserViewModel()

    fun init() {
        addDisposable(
            RxEvent.bus
                .subscribe { str ->
                    _mainTextData.value = "hello $str"
                }
        )
        userViewModel1.init("Android")
        userViewModel2.init("Kotlin")
        userViewModel3.init("MVVM")
    }

    private val compositeDisposable = CompositeDisposable()

    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    fun onDispose() {
        compositeDisposable.dispose()
    }
}