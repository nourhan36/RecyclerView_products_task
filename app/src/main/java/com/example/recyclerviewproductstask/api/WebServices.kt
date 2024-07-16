package com.example.recyclerviewproductstask.api

import com.example.recyclerviewproductstask.api.model.ProductResponse
import retrofit2.http.GET

interface WebServices {
    @GET("/products")
    suspend fun getProducts(): ProductResponse
}