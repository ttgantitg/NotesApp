package com.ttgantitg.trykotlin.data

import com.ttgantitg.trykotlin.data.entity.Note

object NotesRepository {

    private val notes: List<Note> = listOf(
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
            0xffb777777.toInt()
        ),
        Note(
            "Заметка №7",
            "Текст заметки №7. ЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫ",
            0xffffeb3b.toInt()
        ),
        Note(
            "Заметка №8",
            "Текст заметки №8. ЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫ",
            0xfff06292.toInt()
        ),
        Note(
            "Заметка №9",
            "Текст заметки №9. ЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫ",
            0xff9575cd.toInt()
        ),
        Note(
            "Заметка №10",
            "Текст заметки №10. ЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫ",
            0xff64b5f6.toInt()
        ),
        Note(
            "Заметка №11",
            "Текст заметки №11. ЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫ",
            0xff4db6ac.toInt()
        ),
        Note(
            "Заметка №12",
            "Текст заметки №12. ЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫ",
            0xffb2ff59.toInt()
        ),
        Note(
            "Заметка №13",
            "Текст заметки №13. ЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫ",
            0xffb777777.toInt()
        ),
        Note(
            "Заметка №14",
            "Текст заметки №14. ЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫ",
            0xffffeb3b.toInt()
        )
    )

    fun getNotes(): List<Note> {
        return notes
    }
}