package com.example.findaroomkotlinver2.data.model.post

import com.google.gson.annotations.SerializedName

data class Supplement(
    @SerializedName("_id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("iconImage") val iconImage: String
) {
    var checker: Boolean = false
}