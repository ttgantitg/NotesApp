package com.ttgantitg.trykotlin.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ttgantitg.trykotlin.ui.note.NotesRVAdapter
import com.ttgantitg.trykotlin.R
import com.ttgantitg.trykotlin.ui.note.NoteActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: NotesRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.AppDarkTheme)
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        rv_notes.layoutManager = LinearLayoutManager(this)

        adapter = NotesRVAdapter {
            NoteActivity.start(this, it)
        }
        rv_notes.adapter = adapter

        viewModel.viewState().observe(this, Observer {
            it?.let { adapter.notes = it.notes }
        })

        fab.setOnClickListener{
            NoteActivity.start(this)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val item = menu!!.findItem(R.id.switch_item)
        item!!.setActionView(R.layout.switch_layout)

        val mSwitch = item.actionView.findViewById<SwitchCompat>(R.id.theme_switch)
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            mSwitch.isChecked = true
        }
        mSwitch.setOnCheckedChangeListener { _, isChecked ->
            when (isChecked) {
                true -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    restartApp()
                }
                false -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    restartApp()
                }
            }
        }
        return true
    }

    private fun restartApp() {
        val i = Intent(applicationContext, javaClass)
        startActivity(i)
        finish()
    }
}
