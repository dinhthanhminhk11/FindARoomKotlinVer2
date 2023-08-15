package com.example.findaroomkotlinver2.data.model

import com.google.gson.annotations.SerializedName

class Message(
    @SerializedName("status") val status: Boolean, @SerializedName("message") val message: String
)