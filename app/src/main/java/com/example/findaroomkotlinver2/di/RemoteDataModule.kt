package com.example.findaroomkotlinver2.di

import com.example.findaroomkotlinver2.data.api.APIService
import com.example.findaroomkotlinver2.data.repository.dataSource.RemoteDataSource
import com.example.findaroomkotlinver2.data.repository.dataSourceImpl.RemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RemoteDataModule {
    @Singleton
    @Provides
    fun provideRemoteDataSource(
        apiService: APIService
    ): RemoteDataSource {
        return RemoteDataSourceImpl(apiService)
    }
}