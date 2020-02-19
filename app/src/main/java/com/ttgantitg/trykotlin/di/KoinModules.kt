package com.ttgantitg.trykotlin.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.ttgantitg.trykotlin.data.NotesRepository
import com.ttgantitg.trykotlin.data.provider.FireStoreProvider
import com.ttgantitg.trykotlin.data.provider.RemoteDataProvider
import com.ttgantitg.trykotlin.ui.main.MainViewModel
import com.ttgantitg.trykotlin.ui.note.NoteViewModel
import com.ttgantitg.trykotlin.ui.splash.SplashViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val appModule = module {
    single { FirebaseAuth.getInstance() }
    single { FirebaseFirestore.getInstance() }
    single { FireStoreProvider(get(), get()) } bind RemoteDataProvider::class
    single { NotesRepository(get()) }
}

val splashModule = module {
    viewModel { SplashViewModel(get()) }
}

val mainModule = module {
    viewModel { MainViewModel(get()) }
}

val noteModule = module {
    viewModel { NoteViewModel(get()) }
}