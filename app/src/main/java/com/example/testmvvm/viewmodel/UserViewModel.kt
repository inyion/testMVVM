package com.example.testmvvm.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testmvvm.RxEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class UserViewModel: ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String> = _userName

    fun init(name: String) {
        _userName.value = name

        addDisposable(
            RxEvent.bus
                .observeOn(Schedulers.newThread())
                .subscribe { str ->
                    try {
                        Thread.sleep(3000)
                    } catch (e: Exception) {

                    }
                    Log.i("test", _userName.toString())
                    _userName.postValue("hello $str")
                    Log.i("test", _userName.hasActiveObservers().toString())

                }
        )
    }

    fun hello() {
        RxEvent.sendEvent(userName.value!!)
    }

    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    fun onDispose() {
        compositeDisposable.dispose()
    }
}