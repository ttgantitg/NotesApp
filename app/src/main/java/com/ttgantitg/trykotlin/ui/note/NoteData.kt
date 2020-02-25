package com.ttgantitg.trykotlin.ui.note

import com.ttgantitg.trykotlin.data.entity.Note

data class NoteData(val isDeleted: Boolean = false, val note: Note? = null)