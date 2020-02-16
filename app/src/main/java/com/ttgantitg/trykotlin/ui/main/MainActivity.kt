package com.ttgantitg.trykotlin.ui.main

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.firebase.ui.auth.AuthUI
import com.ttgantitg.trykotlin.R
import com.ttgantitg.trykotlin.data.entity.Note
import com.ttgantitg.trykotlin.ui.base.BaseActivity
import com.ttgantitg.trykotlin.ui.note.NoteActivity
import com.ttgantitg.trykotlin.ui.note.NotesRVAdapter
import com.ttgantitg.trykotlin.ui.splash.SplashActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<List<Note>?, MainViewState>(), LogoutDialog.LogoutListener {

    companion object {
        fun start(context: Context) = Intent(context, MainActivity::class.java).apply {
            context.startActivity(this)
        }
    }

    val APP_PREFERENCES = "appsettings"
    val APP_PREFERENCES_THEME = "theme"
    private lateinit var pref: SharedPreferences

    override val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override val layoutRes = R.layout.activity_main
    private lateinit var adapter: NotesRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        //check theme
        pref = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE)
        when (pref.getInt(APP_PREFERENCES_THEME, 0)) {
            0 -> setTheme(R.style.AppLightTheme)
            1 -> setTheme(R.style.AppDarkTheme)
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

        listOf<String>().forEach {
            if(it.isEmpty()){
                return@forEach
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?) =
        MenuInflater(this).inflate(R.menu.menu_main, menu).let { true }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
       return when (item.itemId) {
           R.id.theme_item -> {
            setPrefTheme(item.title as String)
            restartApp()
            true
           }
           R.id.logout_item -> showLogoutDialog().let { true }
           else -> false
       }
    }

    private fun showLogoutDialog() {
        supportFragmentManager.findFragmentByTag(LogoutDialog.TAG) ?:
            LogoutDialog.createInstance().show(supportFragmentManager, LogoutDialog.TAG)
    }

    override fun onLogout() {
        AuthUI.getInstance()
            .signOut(this)
            .addOnCompleteListener {
                startActivity(Intent(this, SplashActivity::class.java))
                finish()
            }
    }

    private fun setPrefTheme(themeId: String) {
        val editor = pref.edit()
        when (themeId) {
            "Светлая тема" -> {
                editor.putInt(APP_PREFERENCES_THEME, 0)
                editor.apply()
            }
            "Темная тема" -> {
                editor.putInt(APP_PREFERENCES_THEME, 1)
                editor.apply()
            }
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

    private fun restartApp() {
        val i = Intent(applicationContext, javaClass)
        startActivity(i)
        finish()
    }
}
