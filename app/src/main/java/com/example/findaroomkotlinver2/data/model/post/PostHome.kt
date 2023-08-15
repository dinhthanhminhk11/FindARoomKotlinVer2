package com.example.findaroomkotlinver2.data.model.post

import com.example.findaroomkotlinver2.data.model.Message
import com.google.gson.annotations.SerializedName

data class PostHome(
    @SerializedName("data") val data: List<Post>,
    @SerializedName("message") val message: Message
)