package com.ttgantitg.trykotlin.data

import com.ttgantitg.trykotlin.data.entity.Note
import com.ttgantitg.trykotlin.data.provider.RemoteDataProvider

class NotesRepository(private val remoteProvider: RemoteDataProvider) {
    fun getNotes() = remoteProvider.subscribeToAllNotes()
    fun saveNote(note: Note) = remoteProvider.saveNote(note)
    fun getNoteById(id: String) = remoteProvider.getNoteById(id)
    fun getCurrentUser() = remoteProvider.getCurrentUser()
    fun deleteNote(id: String) = remoteProvider.deleteNote(id)
}