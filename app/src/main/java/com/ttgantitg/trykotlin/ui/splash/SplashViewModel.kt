package com.ttgantitg.trykotlin.ui.splash

import com.ttgantitg.trykotlin.data.NotesRepository
import com.ttgantitg.trykotlin.data.errors.NoAuthException
import com.ttgantitg.trykotlin.ui.base.BaseViewModel

class SplashViewModel(private val notesRepository: NotesRepository) : BaseViewModel<Boolean?, SplashViewState>() {

    fun requestUser() {
        notesRepository.getCurrentUser().observeForever {
            viewStateLiveData.value = it?.let {
                SplashViewState(authenticated = true)
            } ?: SplashViewState(error = NoAuthException())
        }
    }
}