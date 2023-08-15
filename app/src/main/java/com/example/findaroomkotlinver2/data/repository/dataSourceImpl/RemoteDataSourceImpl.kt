package com.example.findaroomkotlinver2.data.repository.dataSourceImpl

import com.example.findaroomkotlinver2.data.api.APIService
import com.example.findaroomkotlinver2.data.model.post.PostHome
import com.example.findaroomkotlinver2.data.repository.dataSource.RemoteDataSource
import retrofit2.Response

class RemoteDataSourceImpl(private val apiService: APIService) : RemoteDataSource {
    override suspend fun getListPost(): Response<PostHome> = apiService.getListPost()

    override suspend fun getListPostHomeAds(): Response<PostHome> = apiService.getListPostHomeAds()
}