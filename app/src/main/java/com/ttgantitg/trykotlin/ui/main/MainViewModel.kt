package com.ttgantitg.trykotlin.ui.main

import androidx.annotation.VisibleForTesting
import com.ttgantitg.trykotlin.data.NotesRepository
import com.ttgantitg.trykotlin.data.entity.Note
import com.ttgantitg.trykotlin.data.model.NoteResult
import com.ttgantitg.trykotlin.ui.base.BaseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class MainViewModel(notesRepository: NotesRepository) : BaseViewModel<List<Note>?>() {

    private val notesChannel = notesRepository.getNotes()

    init {
        launch {
            notesChannel.consumeEach {
                when(it){
                    is NoteResult.Success<*> -> setData(it.data as? List<Note>)
                    is NoteResult.Error -> setError(it.error)
                }
            }
        }
    }

    @VisibleForTesting
    public override fun onCleared() {
        notesChannel.cancel()
        super.onCleared()
    }
}