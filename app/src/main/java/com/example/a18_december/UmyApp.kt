package com.example.a18_december

import android.app.Application
import com.example.a18_december.dependeciesinjection.ContainerApp

class UmyApp : Application() {
    lateinit var containerApp: ContainerApp
    override fun onCreate() {
        super.onCreate()
        containerApp = ContainerApp(this)
    }
}