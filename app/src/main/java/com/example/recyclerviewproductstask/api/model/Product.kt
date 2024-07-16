package com.example.recyclerviewproductstask.api.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Product(
    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("title")
    val name: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("price")
    val price: Double? = null,

    @field:SerializedName("discountPercentage")
    val discountPercentage: Double? = null,

    @field:SerializedName("rating")
    val rating: Float? = null,

    @field:SerializedName("images")
    val imageUrl: List<String?>? = null,
) : Parcelable
