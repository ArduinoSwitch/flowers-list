package com.setesh.flowers.data

import com.setesh.commons.response.MyResult
import com.setesh.commons.response.UiApiError
import com.setesh.domain.photos.PhotoModel
import com.setesh.domain.photos.PhotosRepository
import com.setesh.domain.photos.UrlsModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakePhotosRepository: PhotosRepository {

    private val _photos = MutableStateFlow(photoMockList)

    private val photos = _photos

    override fun getPhotos(): Flow<List<PhotoModel>> =
        photos

    override suspend fun fetchMorePhotos(): MyResult<Unit, UiApiError> {
        val currentList = _photos.value
        _photos.value = currentList + photoMockList
        return MyResult.Ok(Unit)
    }
}

private val photoMockList = listOf(
    PhotoModel(
        id = "",
        width = 0L,
        height = 0L,
        color = "",
        likes = 0L,
        description = "",
        createDate = "",
        urls = UrlsModel(
            raw = "",
            full = "",
            regular = "",
            small = "",
            thumb = "",
        ),
    ),
    PhotoModel(
        id = "",
        width = 0L,
        height = 0L,
        color = "",
        likes = 0L,
        description = "",
        createDate = "",
        urls = UrlsModel(
            raw = "",
            full = "",
            regular = "",
            small = "",
            thumb = "",
        ),
    )
)
