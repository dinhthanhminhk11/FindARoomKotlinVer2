package com.example.findaroomkotlinver2.data.model.post

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(
    tableName = "post"
)
data class Post(
    @PrimaryKey(autoGenerate = true) val idLocal: Int? = null,
    val idUser: String,
    val nameCategory: String,
    val title: String,
    val images: List<String>,
    val cty: String,
    val district: String,
    val wards: String,
    val street: String,
    val address: String,
    val acreage: Int,
    val deposit: Int,
    val bedroom: Int,
    val bathroom: Int,
    val countPerson: Int,
    val startDate: String,
    val price: Int,
    val electricityPrice: Int,
    val waterPrice: Int,
    val wifi: Int,
    val describe: String,
    val phone: String,
    val supplements: List<Supplement>,
    val time: String,
    val date: String,
    val statusConfirm: Boolean,
    val messageConfirm: String,
    val textConfirm: String,
    val statusRoom: Boolean,
    val messageRoom: String,
    val statusEdit: Boolean,
    val timeEdit: String,
    val dateEdit: String,
    val _id: String,
    val __v: Int,
    val advertisement: Boolean,
    val timeAdvertisement: Int,
    val priceAll: Int,
    val timeLong: Long,
    val viewsCount: Int
) : Serializable