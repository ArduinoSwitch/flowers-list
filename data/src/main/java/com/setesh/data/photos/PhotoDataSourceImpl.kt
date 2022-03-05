package com.setesh.data.photos

import com.setesh.commons.response.*
import com.setesh.data.network.Api
import com.setesh.domain.photos.PhotoDataSource
import com.setesh.domain.photos.PhotoModel

class PhotoDataSourceImpl(
    private val api: Api
): PhotoDataSource {
    override suspend fun getPhotos(): MyResult<List<PhotoModel>, UiApiError> =
        ApiErrorHandling.run {
            api.getPhotos(mapOf("query" to "flower")).results.toDomain()
        }.mapError {
            it.toBasicUi()
        }
}

private fun List<PhotoDataModel>.toDomain() = map {
    PhotoModel(
        id = it.id,
    )
}
