package com.ttgantitg.trykotlin.data

import com.ttgantitg.trykotlin.data.entity.Note

object NotesRepository {

    private val notes: List<Note>

    init {
        notes = listOf(
            Note(
                "Заметка №1",
                "Текст заметки №1. ЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫ",
                0xfff06292.toInt()
            ),
            Note(
                "Заметка №2",
                "Текст заметки №2. ЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫ",
                0xff9575cd.toInt()
            ),
            Note(
                "Заметка №3",
                "Текст заметки №3. ЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫ",
                0xff64b5f6.toInt()
            ),
            Note(
                "Заметка №4",
                "Текст заметки №4. ЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫ",
                0xff4db6ac.toInt()
            ),
            Note(
                "Заметка №5",
                "Текст заметки №5. ЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫ",
                0xffb2ff59.toInt()
            ),
            Note(
                "Заметка №6",
                "Текст заметки №6. ЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫ",
                0xffffeb3a.toInt()
            ),
            Note(
                "Заметка №7",
                "Текст заметки №7. ЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫ",
                0xffffeb3b.toInt()
            )
        )
    }

    fun getNotes(): List<Note> {
        return notes
    }
}