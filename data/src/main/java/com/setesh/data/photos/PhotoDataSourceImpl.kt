package com.setesh.data.photos

import com.setesh.commons.response.*
import com.setesh.data.network.Api
import com.setesh.domain.photos.PhotoDataSource
import com.setesh.domain.photos.PhotoModel
import com.setesh.domain.photos.UrlsModel

private const val QUERY = "query"
private const val QUERY_SUBJECT = "flower"
private const val PAGE = "page"

class PhotoDataSourceImpl(
    private val api: Api
): PhotoDataSource {
    override suspend fun getPhotos(page: String): MyResult<List<PhotoModel>, UiApiError> =
        ApiErrorHandling.run {
            api.getPhotos(
                mapOf(
                    QUERY to QUERY_SUBJECT,
                    PAGE to page
                )
            ).results.toDomain()
        }.mapError {
            it.toBasicUi()
        }
}

private fun List<PhotoDataModel>.toDomain() = map {
    PhotoModel(
        id = it.id,
        width = it.width,
        height = it.height,
        color = it.color,
        likes = it.likes,
        description = it.description ?: "Sin descripción",
        urls = UrlsModel(
            raw = it.urls.raw,
            full = it.urls.full,
            regular = it.urls.regular,
            small = it.urls.small,
            thumb = it.urls.thumb,
        ),
        createDate = it.createdAt,
    )
}
