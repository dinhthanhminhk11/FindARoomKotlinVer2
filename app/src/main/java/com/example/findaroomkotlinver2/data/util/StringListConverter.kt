package com.example.findaroomkotlinver2.data.util

import androidx.room.TypeConverter
import com.example.findaroomkotlinver2.data.model.post.Supplement
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class StringListConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromStringList(list: List<String>?): String? {
        return gson.toJson(list)
    }

    @TypeConverter
    fun toStringList(json: String?): List<String>? {
        val type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromSupplementList(list: List<Supplement>?): String? {
        return gson.toJson(list)
    }

    @TypeConverter
    fun toSupplementList(json: String?): List<Supplement>? {
        val type = object : TypeToken<List<Supplement>>() {}.type
        return gson.fromJson(json, type)
    }
}