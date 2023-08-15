package com.example.findaroomkotlinver2.data.repository

import com.example.findaroomkotlinver2.data.model.post.PostHome
import com.example.findaroomkotlinver2.data.util.Resource
import retrofit2.Response

interface Repository {
    suspend fun getListPost(): Resource<PostHome>
    suspend fun getListPostHomeAds(): Resource<PostHome>
}