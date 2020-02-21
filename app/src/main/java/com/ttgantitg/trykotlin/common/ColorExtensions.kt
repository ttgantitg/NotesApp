package com.ttgantitg.trykotlin.common

import android.content.Context
import androidx.core.content.ContextCompat
import com.ttgantitg.trykotlin.R
import com.ttgantitg.trykotlin.data.entity.Note

fun Note.Color.getColorInt(context: Context): Int =
    ContextCompat.getColor(
        context, when (this) {
            Note.Color.PURPLE -> R.color.purple
            Note.Color.INDIGO -> R.color.indigo
            Note.Color.YELLOW -> R.color.yellow
            Note.Color.RED -> R.color.red
            Note.Color.PINK -> R.color.pink
            Note.Color.GREEN -> R.color.green
            Note.Color.BLUE -> R.color.blue
            Note.Color.BROWN -> R.color.brown
        }
    )


fun Note.Color.getColorRes(): Int = when (this) {
    Note.Color.PURPLE -> R.color.purple
    Note.Color.INDIGO -> R.color.indigo
    Note.Color.YELLOW -> R.color.yellow
    Note.Color.RED -> R.color.red
    Note.Color.PINK -> R.color.pink
    Note.Color.GREEN -> R.color.green
    Note.Color.BLUE -> R.color.blue
    Note.Color.BROWN -> R.color.brown
}