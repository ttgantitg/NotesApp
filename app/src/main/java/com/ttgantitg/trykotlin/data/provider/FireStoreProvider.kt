package com.ttgantitg.trykotlin.data.provider

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.ttgantitg.trykotlin.data.entity.Note
import com.ttgantitg.trykotlin.data.entity.User
import com.ttgantitg.trykotlin.data.errors.NoAuthException
import com.ttgantitg.trykotlin.data.model.NoteResult

class FireStoreProvider(private val firebaseAuth: FirebaseAuth, private val firestore: FirebaseFirestore) : RemoteDataProvider {

    companion object {
        private const val NOTES_COLLECTION = "notes"
        private const val USERS_COLLECTION = "users"
    }

    private val currentUser
        get() = firebaseAuth.currentUser

    private val userNotesCollection: CollectionReference
        get() = currentUser?.let {
                firestore.collection(USERS_COLLECTION).document(it.uid).collection(NOTES_COLLECTION)
            } ?: throw NoAuthException()

    override fun getCurrentUser() = MutableLiveData<User?>().apply {
        value = currentUser?.let {
            User(it.displayName ?: "", it.email ?: "")
        }
    }

    override fun subscribeToAllNotes() = MutableLiveData<NoteResult>().apply {
        try {
            userNotesCollection.addSnapshotListener { snapshot, e ->
                e?.let {
                    throw it
                } ?: let {
                    snapshot?.let { snapshot ->
                        value = NoteResult.Success(snapshot.documents.map { it.toObject(Note::class.java) })
                    }
                }
            }
        } catch (e: Throwable) {
            value = NoteResult.Error(e)
        }
    }

    override fun getNoteById(id: String) = MutableLiveData<NoteResult>().apply {
        try {
            userNotesCollection.document(id).get()
                .addOnSuccessListener { snapshot ->
                    value = NoteResult.Success(snapshot.toObject(Note::class.java))
                }
                .addOnFailureListener {
                    throw it
                }
        } catch (e: Throwable) {
            value = NoteResult.Error(e)
        }
    }

    override fun saveNote(note: Note)= MutableLiveData<NoteResult>().apply {
        try {
            userNotesCollection.document(note.id).set(note)
                .addOnSuccessListener {
                    value = NoteResult.Success(note)
                }
                .addOnFailureListener {
                    throw it
                }
        } catch (e: Throwable) {
            value = NoteResult.Error(e)
        }
    }

    override fun deleteNote(noteId: String): LiveData<NoteResult> = MutableLiveData<NoteResult>().apply {
        try {
            userNotesCollection.document(noteId).delete()
                .addOnSuccessListener {
                    value = NoteResult.Success(null)
                }
                .addOnFailureListener {
                    throw it
                }
        } catch (e: Throwable) {
            value = NoteResult.Error(e)
        }
    }
}