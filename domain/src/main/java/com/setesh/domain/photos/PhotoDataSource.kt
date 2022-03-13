package com.setesh.domain.photos

import com.setesh.commons.response.MyResult
import com.setesh.commons.response.UiApiError

interface PhotoDataSource {
    suspend fun getPhotos(page: String): MyResult<List<PhotoModel>,  UiApiError>
}