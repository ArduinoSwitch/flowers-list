package com.setesh.domain.photos

data class PhotoModel(
    val id: String,
    val width: Long,
    val height: Long,
    val color: String,
    val likes: Long,
    val description: String,
    val urls: UrlsModel,
)

data class UrlsModel (
    val raw: String,
    val full: String,
    val regular: String,
    val small: String,
    val thumb: String
)
