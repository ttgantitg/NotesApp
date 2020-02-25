package com.ttgantitg.trykotlin.data.provider

import com.ttgantitg.trykotlin.data.entity.Note
import com.ttgantitg.trykotlin.data.entity.User
import com.ttgantitg.trykotlin.data.model.NoteResult
import kotlinx.coroutines.channels.ReceiveChannel

interface RemoteDataProvider {
    fun subscribeToAllNotes(): ReceiveChannel<NoteResult>
    suspend fun getNoteById(id: String): Note
    suspend fun saveNote(note: Note): Note
    suspend fun getCurrentUser(): User?
    suspend fun deleteNote(noteId: String)
}