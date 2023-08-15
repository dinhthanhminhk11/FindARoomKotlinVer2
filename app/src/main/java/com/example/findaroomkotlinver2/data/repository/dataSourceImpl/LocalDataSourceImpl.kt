package com.example.findaroomkotlinver2.data.repository.dataSourceImpl

import com.example.findaroomkotlinver2.data.db.PostDAO
import com.example.findaroomkotlinver2.data.repository.dataSource.LocalDataSource

class LocalDataSourceImpl(private val postDAO: PostDAO) : LocalDataSource {
}