package com.example.findaroomkotlinver2.di

import com.example.findaroomkotlinver2.data.repository.Repository
import com.example.findaroomkotlinver2.data.repository.RepositoryImpl
import com.example.findaroomkotlinver2.data.repository.dataSource.LocalDataSource
import com.example.findaroomkotlinver2.data.repository.dataSource.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource
    ): Repository {
        return  RepositoryImpl(
            remoteDataSource,
            localDataSource
        )
    }
}
