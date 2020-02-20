package com.ttgantitg.trykotlin.ui.note

import com.ttgantitg.trykotlin.data.entity.Note
import com.ttgantitg.trykotlin.ui.base.BaseViewState

class NoteViewState(data: Data = Data(), error: Throwable? = null) : BaseViewState<NoteViewState.Data>(data, error) {
    data class Data(val isDeleted: Boolean = false, val note: Note? = null)
}