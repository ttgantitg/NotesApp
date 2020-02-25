package com.ttgantitg.trykotlin.ui.splash

import com.ttgantitg.trykotlin.data.NotesRepository
import com.ttgantitg.trykotlin.data.errors.NoAuthException
import com.ttgantitg.trykotlin.ui.base.BaseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

class SplashViewModel(private val notesRepository: NotesRepository) : BaseViewModel<Boolean?>() {

    @ExperimentalCoroutinesApi
    fun requestUser() {
        launch {
            notesRepository.getCurrentUser()?.let {
                setData(true)
            } ?: setError(NoAuthException())
        }
    }
}