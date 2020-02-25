package com.ttgantitg.trykotlin.data

import com.ttgantitg.trykotlin.data.entity.Note
import com.ttgantitg.trykotlin.data.provider.RemoteDataProvider

class NotesRepository(private val remoteProvider: RemoteDataProvider) {
    fun getNotes() = remoteProvider.subscribeToAllNotes()
    suspend fun saveNote(note: Note) = remoteProvider.saveNote(note)
    suspend fun getNoteById(id: String) = remoteProvider.getNoteById(id)
    suspend fun getCurrentUser() = remoteProvider.getCurrentUser()
    suspend fun deleteNote(id: String) = remoteProvider.deleteNote(id)
}