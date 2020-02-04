package com.ttgantitg.trykotlin.ui.note

import androidx.lifecycle.ViewModel
import com.ttgantitg.trykotlin.data.NotesRepository
import com.ttgantitg.trykotlin.data.entity.Note

class NoteViewModel: ViewModel() {

    private var pendingNote: Note? = null

    fun save(note: Note) {
        pendingNote = note
    }

    override fun onCleared() {
        pendingNote?.let { NotesRepository.saveNote(it) }
    }
}