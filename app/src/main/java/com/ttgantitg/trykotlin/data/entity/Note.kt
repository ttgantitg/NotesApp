package com.ttgantitg.trykotlin.data.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Note (
    val id: String,
    val title: String = "",
    val text: String = "",
    val color: Color = generateColor(),
    val lastChanged: Date = Date()
): Parcelable {
    companion object{
        private fun generateColor(): Color {
            return when((Math.random() * 7).toInt()) {
                0 -> Color.PURPLE
                1 -> Color.YELLOW
                2 -> Color.GREEN
                3 -> Color.BLUE
                4 -> Color.RED
                5 -> Color.TEAL
                6 -> Color.PINK
                else -> Color.GREEN
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Note

        if (id != other.id) return false
        return true
    }

    enum class Color{
        PURPLE,
        YELLOW,
        GREEN,
        BLUE,
        RED,
        TEAL,
        PINK
    }
}