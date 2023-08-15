package com.example.findaroomkotlinver2.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.findaroomkotlinver2.data.model.post.Post
import com.example.findaroomkotlinver2.data.util.StringListConverter

@Database(
    entities = [Post::class], version = 1, exportSchema = false
)
@TypeConverters(StringListConverter::class)
abstract class PostDatabase : RoomDatabase() {
    abstract fun getPostDAO(): PostDAO
}