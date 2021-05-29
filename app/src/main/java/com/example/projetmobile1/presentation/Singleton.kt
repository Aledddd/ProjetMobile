package com.example.projetmobile1.presentation

import com.example.projetmobile1.presentation.api.CarsApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Singleton {
    companion object{

        val carsApi: CarsApi = Retrofit.Builder()
                .baseUrl("https://private-anon-a858ac5986-carsapi1.apiary-mock.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CarsApi::class.java)
        }

    }