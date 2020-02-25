package com.ttgantitg.trykotlin.ui.note

import androidx.annotation.VisibleForTesting
import com.ttgantitg.trykotlin.data.NotesRepository
import com.ttgantitg.trykotlin.data.entity.Note
import com.ttgantitg.trykotlin.ui.base.BaseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

class NoteViewModel(private val notesRepository: NotesRepository): BaseViewModel<NoteData>() {

    @ExperimentalCoroutinesApi
    private val pendingNote: Note?
        get() = getViewState().poll()?.note

    @ExperimentalCoroutinesApi
    fun save(note: Note) {
        setData(NoteData(note = note))
    }

    @ExperimentalCoroutinesApi
    fun loadNote(noteId: String) {
        launch {
            try {
                setData(NoteData(note = notesRepository.getNoteById(noteId)))
            } catch (e: Throwable) {
                setError(e)
            }
        }
    }

    @ExperimentalCoroutinesApi
    fun deleteNote() {
        pendingNote?.let { note ->
            launch {
                try {
                    notesRepository.deleteNote(note.id)
                    setData(NoteData(isDeleted = true))
                } catch (e: Throwable) {
                    setError(e)
                }
            }
        }
    }

    @ExperimentalCoroutinesApi
    @VisibleForTesting
    public override fun onCleared() {
        launch {
            pendingNote?.let {
                try {
                    notesRepository.saveNote(it)
                } catch (e: Throwable) {
                    setError(e)
                }
            }
            super.onCleared()
        }
    }
}