package com.example.testmvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testmvvm.RxEvent

class UserViewModel {
    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String> = _userName

    fun init(name: String) {
        _userName.value = name
    }

    fun hello() {
        RxEvent.sendEvent(userName.value!!)
    }
}