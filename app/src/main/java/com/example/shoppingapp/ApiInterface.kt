package com.example.shoppingapp

import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("products")
    suspend fun getProductData(): Response<MyData>
}