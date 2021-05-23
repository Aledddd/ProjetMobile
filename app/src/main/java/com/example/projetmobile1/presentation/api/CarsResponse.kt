package com.example.projetmobile1.presentation.api

data class CarsResponse(
    val years: Int,
    val id: Int,
    val horsepower: Int,
    val make: String,
    val model: String,
    val price: Int,
    val image: String
)