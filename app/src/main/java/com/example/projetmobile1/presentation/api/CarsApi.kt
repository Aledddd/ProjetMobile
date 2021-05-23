package com.example.projetmobile1.presentation.api

import retrofit2.Call
import retrofit2.http.GET

interface CarsApi {
    @GET("cars")
    fun getCars() : Call<List<CarsResponse>>
}