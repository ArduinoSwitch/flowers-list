package com.setesh.domain.photos

import com.setesh.commons.response.MyResult
import com.setesh.commons.response.UiApiError

interface PhotoDataSource {
    suspend fun getPhotos(): MyResult<List<PhotoModel>,  UiApiError>
}