package com.example.testmvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    private val _mainTextData = MutableLiveData<String>()
    val mainTextData:LiveData<String> = _mainTextData

}