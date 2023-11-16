package com.kedijik.littlelemon.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Serializable
data class MenuNetwork (
    @SerialName("menu") val items: List<MenuItemNetwork>
        )
