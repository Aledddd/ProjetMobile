package com.example.projetmobile1.presentation

import android.app.Application
import android.content.Context

class CarApplication: Application() {

    companion object{
        var context: Context? = null
    }

    override fun onCreate(){
        super.onCreate()
        context = this
    }
}