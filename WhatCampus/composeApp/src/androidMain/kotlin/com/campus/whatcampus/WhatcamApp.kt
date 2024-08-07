package com.campus.whatcampus

import android.app.Application
import core.di.KoinInitializer
import feature.app.NotifierInitializer
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

class WhatcamApp : Application() {

    override fun onCreate() {
        super.onCreate()
        NotifierInitializer.onApplicationStart()
        Napier.base(DebugAntilog())
        KoinInitializer(applicationContext).init()
    }
}
