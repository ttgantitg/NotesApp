package com.ttgantitg.trykotlin.data.provider

import androidx.lifecycle.LiveData
import com.ttgantitg.trykotlin.data.entity.Note
import com.ttgantitg.trykotlin.data.entity.User
import com.ttgantitg.trykotlin.data.model.NoteResult

interface RemoteDataProvider {

    fun subscribeToAllNotes(): LiveData<NoteResult>
    fun getNoteById(id: String): LiveData<NoteResult>
    fun saveNote(note: Note): LiveData<NoteResult>
    fun getCurrentUser(): LiveData<User?>
    fun deleteNote(noteId: String): LiveData<NoteResult>
}