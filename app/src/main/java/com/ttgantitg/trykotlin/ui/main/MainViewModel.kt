package com.ttgantitg.trykotlin.ui.main

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.Observer
import com.ttgantitg.trykotlin.data.NotesRepository
import com.ttgantitg.trykotlin.data.entity.Note
import com.ttgantitg.trykotlin.data.model.NoteResult
import com.ttgantitg.trykotlin.ui.base.BaseViewModel

class MainViewModel(notesRepository: NotesRepository) : BaseViewModel<List<Note>?, MainViewState>() {

    private val notesObserver = object : Observer<NoteResult> {
        override fun onChanged(t: NoteResult?) {
            t ?: return

            when(t) {
                is NoteResult.Success<*> -> {
                    viewStateLiveData.value = MainViewState(notes = t.data as? List<Note>)
                }
                is NoteResult.Error -> {
                    viewStateLiveData.value = MainViewState(error = t.error)
                }
            }
        }
    }

    private val repositoryNotes = notesRepository.getNotes()

    init {
        viewStateLiveData.value = MainViewState()
        repositoryNotes.observeForever(notesObserver)
    }

    @VisibleForTesting
    public override fun onCleared() {
        repositoryNotes.removeObserver(notesObserver)
        super.onCleared()
    }
}