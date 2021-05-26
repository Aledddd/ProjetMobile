package com.example.projetmobile1

import com.example.projetmobile1.presentation.api.CarsApi
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

class Singleton {
    companion object{
        val carsApi: CarsApi = Retrofit.Builder()
            .baseUrl("https://private-anon-a858ac5986-carsapi1.apiary-mock.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CarsApi::class.java)

    }
}