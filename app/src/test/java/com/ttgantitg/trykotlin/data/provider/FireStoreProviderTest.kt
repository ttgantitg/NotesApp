package com.ttgantitg.trykotlin.data.provider

import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.*
import com.ttgantitg.trykotlin.data.entity.Note
import com.ttgantitg.trykotlin.data.errors.NoAuthException
import com.ttgantitg.trykotlin.data.model.NoteResult
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.Assert.*

class FireStoreProviderTest {

    @ExperimentalCoroutinesApi
    private  val testDispatcher =  TestCoroutineDispatcher()

    private val mockDB = mockk<FirebaseFirestore>()
    private val mockAuth = mockk<FirebaseAuth>()
    private val mockResultCollection = mockk<CollectionReference>()
    private val mockUser = mockk<FirebaseUser>()

    private val mockDocument1 = mockk<DocumentSnapshot>()
    private val mockDocument2 = mockk<DocumentSnapshot>()
    private val mockDocument3 = mockk<DocumentSnapshot>()

    private val testError = mockk<FirebaseFirestoreException>()

    private val testNotes = listOf(Note("1"), Note("2"), Note("3"))
    private val provider = FireStoreProvider(mockAuth, mockDB)

    companion object {
        @BeforeClass
        fun setupClass() {

        }

        @AfterClass
        fun tearDownClass() {

        }
    }

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        clearAllMocks()
        every { mockAuth.currentUser } returns mockUser
        every { mockUser.uid } returns ""
        every { mockDB.collection(any()).document(any()).collection(any()) } returns mockResultCollection
        every { mockDocument1.toObject(Note::class.java) } returns testNotes[0]
        every { mockDocument2.toObject(Note::class.java) } returns testNotes[1]
        every { mockDocument3.toObject(Note::class.java) } returns testNotes[2]
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        clearAllMocks()
        unmockkAll()
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines ()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `should throw NoAuthException if no auth`() {
        var result: Any? = null
        every { mockAuth.currentUser } returns null

        MainScope().launch {
            provider.subscribeToAllNotes().consumeEach {
                if(it is NoteResult.Error){
                    result = it.error
                }
            }
        }
        assertTrue(result is NoAuthException)
    }

    @Test
    fun `saveNote calls set`() {
        val mockDocumentReference = mockk<DocumentReference>()
        every { mockResultCollection.document(testNotes[0].id) } returns mockDocumentReference
        MainScope().launch {
            provider.saveNote(testNotes[0])
        }
        verify(exactly = 1) { mockDocumentReference.set(testNotes[0]) }
    }

    @Test
    fun `subscribe to all notes returns notes`() {
        var result: List<Note>? = null
        val mockSnapshot = mockk<QuerySnapshot>()
        val slot = slot<EventListener<QuerySnapshot>>()

        every { mockSnapshot.documents } returns listOf(mockDocument1, mockDocument2, mockDocument3)
        every { mockResultCollection.addSnapshotListener(capture(slot)) } returns mockk()

        MainScope().launch {
            provider.subscribeToAllNotes().consumeEach {
                result = (it as? NoteResult.Success<List<Note>>)?.data
            }
        }
        slot.captured.onEvent(mockSnapshot, null)
        assertEquals(testNotes, result)
    }

    @Test
    fun `subscribe to all notes return error`() {
        var result: Throwable? = null
        val slot = slot<EventListener<QuerySnapshot>>()

        every { mockResultCollection.addSnapshotListener(capture(slot)) } returns mockk()

        MainScope().launch {
            provider.subscribeToAllNotes().consumeEach {
                result = (it as NoteResult.Error).error
            }
        }
        slot.captured.onEvent(null, testError)
        assertEquals(testError, result)
    }

    @Test
    fun `deleteNote calls document delete`() {
        val mockDocumentReference = mockk<DocumentReference>()
        every { mockResultCollection.document(testNotes[0].id) } returns mockDocumentReference
        MainScope().launch {
            provider.deleteNote(testNotes[0].id)
        }
        verify(exactly = 1) { mockDocumentReference.delete() }
    }

    //TODO: getNoteById
    @Test
    fun `get note by id`(){
        val slot = slot<OnSuccessListener<DocumentSnapshot>>()
        var result: Note? = null
        val id = "1"

        every {
            mockResultCollection
                .document(any())
                .get()
                .addOnSuccessListener(capture(slot))
                .addOnFailureListener(capture(slot()))} returns mockk()

        MainScope().launch {
            result = provider.getNoteById(id)
        }
        slot.captured.onSuccess(mockDocument1)
        assertNotNull(result)
        assertEquals(testNotes[0], result)
        verify(exactly = 1) { mockResultCollection.document(any()).get() }
    }
}