//package com.ttgantitg.trykotlin.ui.main
//
//import androidx.arch.core.executor.testing.InstantTaskExecutorRule
//import androidx.lifecycle.MutableLiveData
//import com.ttgantitg.trykotlin.data.NotesRepository
//import com.ttgantitg.trykotlin.data.entity.Note
//import com.ttgantitg.trykotlin.data.model.NoteResult
//import com.ttgantitg.trykotlin.ui.note.NoteViewModel
//import com.ttgantitg.trykotlin.ui.note.NoteViewState
//import io.mockk.*
//import org.junit.Assert.*
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//
//class NoteViewModelTest {
//
//    @get:Rule
//    val taskExecutorRule = InstantTaskExecutorRule()
//
//    private val mockRepository = mockk<NotesRepository>()
//    private val notesLiveData = MutableLiveData<NoteResult>()
//
//    private val testNote = Note("1", "title", "text")
//
//    private lateinit var viewModel: NoteViewModel
//
//    @Before
//    fun setup() {
//        clearMocks(mockRepository)
//        every { mockRepository.getNoteById(testNote.id) } returns notesLiveData
//        every { mockRepository.deleteNote(testNote.id) } returns notesLiveData
//        every { mockRepository.saveNote(testNote) } returns notesLiveData
//        viewModel = NoteViewModel(mockRepository)
//    }
//
//    @Test
//    fun `loadNote should return NoteViewState Data`() {
//        var result: NoteViewState.Data? = null
//        val testData = NoteViewState.Data(false, testNote)
//        viewModel.getViewState().observeForever {
//            result = it.data
//        }
//        viewModel.loadNote(testNote.id)
//        notesLiveData.value = NoteResult.Success(testNote)
//        assertEquals(testData, result)
//    }
//
//    @Test
//    fun `loadNote should return error`() {
//        var result: Throwable? = null
//        val testData = Throwable("error")
//        viewModel.getViewState().observeForever {
//            result = it.error
//        }
//        viewModel.loadNote(testNote.id)
//        notesLiveData.value = NoteResult.Error(error = testData)
//        assertEquals(testData, result)
//    }
//
//    @Test
//    fun `deleteNote should return NoteViewState Data with isDeleted`() {
//        var result: NoteViewState.Data? = null
//        val testData = NoteViewState.Data(true, null)
//        viewModel.getViewState().observeForever {
//            result = it.data
//        }
//        viewModel.save(testNote)
//        viewModel.deleteNote()
//        notesLiveData.value = NoteResult.Success(null)
//        assertEquals(testData, result)
//    }
//
//    @Test
//    fun `deleteNote should return error`() {
//        var result: Throwable? = null
//        val testData = Throwable("error")
//        viewModel.getViewState().observeForever {
//            result = it.error
//        }
//        viewModel.save(testNote)
//        viewModel.deleteNote()
//        notesLiveData.value = NoteResult.Error(error = testData)
//        assertEquals(testData, result)
//    }
//
//    @Test
//    fun `should save changes`() {
//        viewModel.save(testNote)
//        viewModel.onCleared()
//        verify { mockRepository.saveNote(testNote) }
//    }
//}