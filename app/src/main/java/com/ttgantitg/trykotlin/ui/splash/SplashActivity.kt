package com.ttgantitg.trykotlin.ui.splash

import android.os.Handler
import com.ttgantitg.trykotlin.ui.base.BaseActivity
import com.ttgantitg.trykotlin.ui.main.MainActivity
import org.koin.android.viewmodel.ext.android.viewModel

class SplashActivity : BaseActivity<Boolean?, SplashViewState>() {

    override val model: SplashViewModel by viewModel()

    override val layoutRes: Int? = null

    override fun onResume() {
        super.onResume()
        Handler().postDelayed( {model.requestUser()}, 500)
    }

    override fun renderData(data: Boolean?) {
        data?.takeIf { it }?.let {
            startMainActivity()
        }
    }

    private fun startMainActivity() {
        MainActivity.start(this)
        finish()
    }
}