package com.example.findaroomkotlinver2.data.repository.dataSource

import com.example.findaroomkotlinver2.data.model.post.PostHome
import retrofit2.Response
import retrofit2.http.GET

interface RemoteDataSource {
    suspend fun getListPost(): Response<PostHome>
    suspend fun getListPostHomeAds(): Response<PostHome>
}