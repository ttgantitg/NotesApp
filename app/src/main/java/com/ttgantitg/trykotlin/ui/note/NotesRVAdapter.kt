package com.ttgantitg.trykotlin.ui.note

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ttgantitg.trykotlin.R
import com.ttgantitg.trykotlin.data.entity.Note
import kotlinx.android.synthetic.main.item_note.view.*
import java.text.SimpleDateFormat
import java.util.*

class NotesRVAdapter(val onItemViewClick : ((note: Note) -> Unit)? = null) :
    RecyclerView.Adapter<NotesRVAdapter.ViewHolder> () {

    var notes: List<Note> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_note,
                parent,
                false
            )
        )

    override fun getItemCount() = notes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(notes[position])

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(note: Note) = with(itemView){
            tv_title.text = note.title
            tv_text.text = note.text
            tv_date.text = SimpleDateFormat(NoteActivity.DATE_TIME_FORMAT, Locale.getDefault()).format(note.lastChanged)
            itemView.setOnClickListener { onItemViewClick?.invoke(note) }
        }
    }
}