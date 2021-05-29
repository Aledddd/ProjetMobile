package com.example.projetmobile1.presentation

import com.example.projetmobile1.presentation.CarApplication.Companion.context
import com.example.projetmobile1.presentation.api.CarsApi
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

class Singleton {
    companion object{
       /* private var cache = Cache(File(context?.cacheDir,"responses"),10 * 1024 * 1024)

        private var okHttpClient: OkHttpClient = OkHttpClient().newBuilder()
            .cache(cache)
            .build()*/

        val carsApi: CarsApi = Retrofit.Builder()
                .baseUrl("https://private-anon-a858ac5986-carsapi1.apiary-mock.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CarsApi::class.java)
        }

    }