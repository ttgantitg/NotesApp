package com.ttgantitg.trykotlin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ttgantitg.trykotlin.data.NotesRepository

class MainViewModel: ViewModel() {

    private val viewStateLiveData: MutableLiveData<MainViewState> = MutableLiveData()

    init {
        viewStateLiveData.value = MainViewState(NotesRepository.getNotes())
    }

    fun viewState(): LiveData<MainViewState> = viewStateLiveData

}