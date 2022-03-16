package com.setesh.domain.photos

import com.setesh.commons.response.MyResult
import com.setesh.commons.response.UiApiError
import kotlinx.coroutines.flow.Flow

interface PhotosRepository {
    fun getPhotos(): Flow<List<PhotoModel>>
    suspend fun fetchMorePhotos(): MyResult<Unit, UiApiError>
}