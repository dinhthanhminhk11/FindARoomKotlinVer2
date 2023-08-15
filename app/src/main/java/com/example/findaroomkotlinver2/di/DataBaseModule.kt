package com.example.findaroomkotlinver2.di

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import com.example.findaroomkotlinver2.data.db.PostDAO
import com.example.findaroomkotlinver2.data.db.PostDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {
    @Singleton
    @Provides
    fun providePostDatabase(app: Application): PostDatabase {
        return Room.databaseBuilder(app, PostDatabase::class.java, "find_a_room_db")
            .fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun providePostDao(postDatabase: PostDatabase): PostDAO {
        return postDatabase.getPostDAO()
    }
}