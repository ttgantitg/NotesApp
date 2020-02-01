package com.ttgantitg.trykotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    lateinit var adapter: NotesRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        rv_notes.layoutManager = LinearLayoutManager(this)
        adapter = NotesRVAdapter()
        rv_notes.adapter = adapter

        viewModel.viewState().observe(this, Observer {
            it?.let {
                adapter.notes = it.notes
            }
        })
    }
}
