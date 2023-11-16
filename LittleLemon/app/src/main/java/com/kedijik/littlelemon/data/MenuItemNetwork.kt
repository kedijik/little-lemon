package com.kedijik.littlelemon.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MenuItemNetwork (
    @SerialName("id") val id:Int,
    @SerialName("title") val title: String,
    @SerialName("description") val desc: String,
    @SerialName("price") val price: Double,
    @SerialName("image") val imgURL: String,
    @SerialName("category") val category: String
        )