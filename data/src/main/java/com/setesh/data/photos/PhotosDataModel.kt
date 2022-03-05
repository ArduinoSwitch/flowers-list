package com.setesh.data.photos

import kotlinx.serialization.Serializable

@Serializable
data class UnsplashDataModel(
    val results: List<PhotoDataModel>
)

@Serializable
data class PhotoDataModel(
    val id: String,
)
