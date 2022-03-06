package com.setesh.data.photos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UnsplashDataModel(
    val results: List<PhotoDataModel>
)

@Serializable
data class PhotoDataModel(
    val id: String,
    val width: Long,
    val height: Long,
    val color: String,
    val likes: Long,
    val description: String? = null,
    val urls: Urls,
    @SerialName("created_at") val createdAt: String,
)

@Serializable
data class Urls (
    val raw: String,
    val full: String,
    val regular: String,
    val small: String,
    val thumb: String
)
