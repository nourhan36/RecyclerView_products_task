package com.example.recyclerviewproductstask.repository

import com.example.recyclerviewproductstask.api.WebServices
import com.example.recyclerviewproductstask.api.model.ProductResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepository @Inject constructor(
    private val webServices: WebServices
) {
    suspend fun getProducts(): ProductResponse {
        return webServices.getProducts()
    }
}