package com.ttgantitg.trykotlin.ui.splash

import com.ttgantitg.trykotlin.ui.base.BaseViewState

class SplashViewState(authenticated: Boolean? = null, error: Throwable? = null) :
    BaseViewState<Boolean?>(authenticated, error)