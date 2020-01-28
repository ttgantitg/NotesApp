package com.ttgantitg.trykotlin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    private val viewStateLiveData: MutableLiveData<String> = MutableLiveData()

    init {
        viewStateLiveData.value = "0"
    }

    fun getViewStateLiveData(): LiveData<String> = viewStateLiveData

    fun generateNewNumber() {
        viewStateLiveData.value = (Math.random() * 10).toInt().toString()
    }
}