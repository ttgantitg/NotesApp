package com.ttgantitg.trykotlin.data

import com.ttgantitg.trykotlin.data.entity.Note
import com.ttgantitg.trykotlin.data.provider.FireStoreProvider
import com.ttgantitg.trykotlin.data.provider.RemoteDataProvider

object NotesRepository {

    private val remoteProvider: RemoteDataProvider = FireStoreProvider()

    fun getNotes() = remoteProvider.subscribeToAllNotes()
    fun saveNote(note: Note) = remoteProvider.saveNote(note)
    fun getNoteById(id: String) = remoteProvider.getNoteById(id)
    fun getCurrentUser() = remoteProvider.getCurrentUser()
}