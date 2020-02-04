package com.ttgantitg.trykotlin.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ttgantitg.trykotlin.data.entity.Note

object NotesRepository {

    private val notesLiveData = MutableLiveData<List<Note>>()
    private val notes: MutableList<Note> = mutableListOf()

    init {
        notesLiveData.value = notes
    }

    fun saveNote(note: Note) {
        addOrReplace(note)
        notesLiveData.value = notes
    }

    private fun addOrReplace(note: Note) {
        for (i in notes.indices) {
            if (notes[i] == note) {
                notes[i] = note
                return
            }
        }
        notes.add(note)
    }

    fun getNotes(): LiveData<List<Note>> {
        return notesLiveData
    }
}