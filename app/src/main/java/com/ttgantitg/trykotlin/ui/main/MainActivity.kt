package com.ttgantitg.trykotlin.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ttgantitg.trykotlin.R
import com.ttgantitg.trykotlin.data.entity.Note
import com.ttgantitg.trykotlin.ui.base.BaseActivity
import com.ttgantitg.trykotlin.ui.note.NoteActivity
import com.ttgantitg.trykotlin.ui.note.NotesRVAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<List<Note>?, MainViewState>() {

    override val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override val layoutRes = R.layout.activity_main
    private lateinit var adapter: NotesRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        //check theme
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.AppDarkTheme)
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        rv_notes.layoutManager = LinearLayoutManager(this)

        adapter = NotesRVAdapter {
            NoteActivity.start(this, it.id)
        }
        rv_notes.adapter = adapter

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

    override fun renderData(data: List<Note>?) {
        data?.let {
            adapter.notes = it

            if (adapter.notes.isEmpty()) {
                rv_notes.visibility = View.GONE
                empty_view.visibility = View.VISIBLE

            } else {
                rv_notes.visibility = View.VISIBLE
                empty_view.visibility = View.GONE
            }
        }
    }

    private fun restartApp() {
        val i = Intent(applicationContext, javaClass)
        startActivity(i)
        finish()
    }
}
