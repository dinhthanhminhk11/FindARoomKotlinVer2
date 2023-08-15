package com.example.findaroomkotlinver2.data.api

import com.example.findaroomkotlinver2.data.model.post.PostHome
import retrofit2.Response
import retrofit2.http.GET

interface APIService {
    @GET("postHome")
    suspend fun getListPost(): Response<PostHome>

    @GET("postHomeAds")
    suspend fun getListPostHomeAds(): Response<PostHome>
}