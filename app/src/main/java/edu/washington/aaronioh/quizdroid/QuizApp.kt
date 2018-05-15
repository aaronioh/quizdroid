package edu.washington.aaronioh.quizdroid

import android.app.Application
import android.util.Log

class QuizApp : Application() {

    companion object {
        lateinit var instance : OnlineRepository
            private set
    }

    override fun onCreate() {
        super.onCreate()

        instance = OnlineRepository()

        Log.i("QuizApp", "QuizApp onCreate running")
    }
}


