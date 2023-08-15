package com.example.findaroomkotlinver2.data.repository

import com.example.findaroomkotlinver2.data.model.post.PostHome
import com.example.findaroomkotlinver2.data.repository.dataSource.LocalDataSource
import com.example.findaroomkotlinver2.data.repository.dataSource.RemoteDataSource
import com.example.findaroomkotlinver2.data.util.Resource
import retrofit2.Response

class RepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : Repository {
    override suspend fun getListPost(): Resource<PostHome> =
        responseToResource(remoteDataSource.getListPost())

    override suspend fun getListPostHomeAds(): Resource<PostHome> =
        responseToResource(remoteDataSource.getListPostHomeAds())


    private fun <T> responseToResource(response: Response<T>): Resource<T> {
        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }
}