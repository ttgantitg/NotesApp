//package com.ttgantitg.trykotlin.ui.main
//
//import androidx.arch.core.executor.testing.InstantTaskExecutorRule
//import androidx.lifecycle.MutableLiveData
//import com.ttgantitg.trykotlin.data.NotesRepository
//import com.ttgantitg.trykotlin.data.entity.Note
//import com.ttgantitg.trykotlin.data.model.NoteResult
//import io.mockk.*
//import org.junit.Assert.*
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//
//class MainViewModelTest {
//
//    @get:Rule
//    val taskExecutorRule = InstantTaskExecutorRule()
//
//    private val mockRepository = mockk<NotesRepository>()
//    private val notesLiveData = MutableLiveData<NoteResult>()
//
//    private lateinit var viewModel: MainViewModel
//
//    @Before
//    fun setup() {
//        clearMocks(mockRepository)
//        every { mockRepository.getNotes() } returns notesLiveData
//        viewModel = MainViewModel(mockRepository)
//    }
//
//    @Test
//    fun `should call getNotes`() {
//        verify(exactly = 1) { mockRepository.getNotes() }
//    }
//
//    @Test
//    fun `should return notes`() {
//        var result: List<Note>? = null
//        val testData = listOf(Note("1"), Note("2"))
//        viewModel.getViewState().observeForever {
//            result = it.data
//        }
//        notesLiveData.value = NoteResult.Success(testData)
//        assertEquals(testData, result)
//    }
//
//    @Test
//    fun `should return error`() {
//        var result: Throwable? = null
//        val testData = Throwable("error")
//        viewModel.getViewState().observeForever {
//            result = it?.error
//        }
//        notesLiveData.value = NoteResult.Error(error = testData)
//        assertEquals(testData, result)
//    }
//
//    @Test
//    fun `should remove observer`() {
//        viewModel.onCleared()
//        assertFalse(notesLiveData.hasObservers())
//    }
//}