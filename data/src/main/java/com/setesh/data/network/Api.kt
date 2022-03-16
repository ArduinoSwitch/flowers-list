package com.setesh.data.network

import com.setesh.data.photos.UnsplashDataModel
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface Api {
    @GET("search/photos")
    suspend fun getPhotos(@QueryMap params: Map<String, String>): UnsplashDataModel
}
