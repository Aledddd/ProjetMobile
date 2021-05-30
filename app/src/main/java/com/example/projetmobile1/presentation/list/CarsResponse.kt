package com.example.projetmobile1.presentation.list

data class CarsResponse(
    val years: Int,
    val id: Int,
    val horsepower: Int,
    val make: String,
    val model: String,
    val price: Int,
    val img_url: String
)