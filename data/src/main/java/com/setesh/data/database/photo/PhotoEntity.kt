package com.setesh.data.database.photo

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "photo")
data class PhotoEntity(
    @PrimaryKey
    val id: String,
    val width: Long,
    val height: Long,
    val color: String,
    val likes: Long,
    val description: String,
    val createDate: Long,
    @Embedded val urls: UrlsEmbedded,
)

data class UrlsEmbedded(
    val raw: String,
    val full: String,
    val regular: String,
    val small: String,
    val thumb: String
)
