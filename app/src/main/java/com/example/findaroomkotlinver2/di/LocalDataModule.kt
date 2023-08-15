package com.example.findaroomkotlinver2.di

import com.example.findaroomkotlinver2.data.db.PostDAO
import com.example.findaroomkotlinver2.data.repository.dataSource.LocalDataSource
import com.example.findaroomkotlinver2.data.repository.dataSourceImpl.LocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {
    @Singleton
    @Provides
    fun provideLocalDataSource(articleDAO: PostDAO): LocalDataSource {
        return LocalDataSourceImpl(articleDAO)
    }
}