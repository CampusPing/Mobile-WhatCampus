package com.campus.whatcampus

import android.app.Application
import core.di.KoinInitializer

class WhatcamApp : Application() {

    override fun onCreate() {
        super.onCreate()
        KoinInitializer(applicationContext).init()
    }
}
