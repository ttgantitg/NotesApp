package com.ttgantitg.trykotlin.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.firebase.ui.auth.AuthUI
import com.ttgantitg.trykotlin.R
import com.ttgantitg.trykotlin.data.entity.Note
import com.ttgantitg.trykotlin.ui.base.BaseActivity
import com.ttgantitg.trykotlin.ui.note.NoteActivity
import com.ttgantitg.trykotlin.ui.note.NotesRVAdapter
import com.ttgantitg.trykotlin.ui.splash.SplashActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.alert
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<List<Note>?, MainViewState>() {

    companion object {
        fun start(context: Context) = Intent(context, MainActivity::class.java).apply {
            context.startActivity(this)
        }
    }

    override val model: MainViewModel by viewModel()

    override val layoutRes = R.layout.activity_main
    private lateinit var adapter: NotesRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

    override fun onCreateOptionsMenu(menu: Menu?) =
        menuInflater.inflate(R.menu.menu_main, menu).let { true }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
       return when (item.itemId) {
           R.id.logout_item -> showLogoutDialog().let { true }
           else -> false
       }
    }

    private fun showLogoutDialog() {
        alert {
            titleResource = R.string.log_out
            messageResource = R.string.logout_dialog_message
            positiveButton(R.string.logout_dialog_yes) { onLogout() }
            negativeButton(R.string.logout_dialog_no) { dialog -> dialog.dismiss() }
        }.show()
    }

    private fun onLogout() {
        AuthUI.getInstance()
            .signOut(this)
            .addOnCompleteListener {
                startActivity(Intent(this, SplashActivity::class.java))
                finish()
            }
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
}
