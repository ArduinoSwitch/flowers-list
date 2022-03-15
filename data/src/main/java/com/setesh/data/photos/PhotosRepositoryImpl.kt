package com.setesh.data.photos

import com.setesh.commons.response.map
import com.setesh.commons.response.onSuccess
import com.setesh.commons.utils.dateTimeToMilliseconds
import com.setesh.commons.utils.toDateFormatString
import com.setesh.data.database.photo.PhotoDao
import com.setesh.data.database.photo.PhotoEntity
import com.setesh.data.database.photo.UrlsEmbedded
import com.setesh.domain.photos.PhotoDataSource
import com.setesh.domain.photos.PhotoModel
import com.setesh.domain.photos.PhotosRepository
import com.setesh.domain.photos.UrlsModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PhotosRepositoryImpl(
    private val photosDataSource: PhotoDataSource,
    private val photoDao: PhotoDao,
) : PhotosRepository {
    override fun getPhotos(): Flow<List<PhotoModel>> =
        photoDao.getAllPhotos().map {
            it.toDomain()
        }

    override suspend fun fetchMorePhotos() =
        photosDataSource.getPhotos((photoDao.getPhotoCount() + 1).toString()).onSuccess {
            photoDao.insertList(it.toEntity())
        }.map { Unit }
}

private fun List<PhotoModel>.toEntity() = map {
    PhotoEntity(
        id = it.id,
        width = it.width,
        height = it.height,
        color = it.color,
        likes = it.likes,
        description = it.description,
        createDate = it.createDate.dateTimeToMilliseconds(),
        urls = UrlsEmbedded(
            raw = it.urls.raw,
            full = it.urls.full,
            regular = it.urls.regular,
            small = it.urls.small,
            thumb = it.urls.thumb,
        )
    )
}

private fun List<PhotoEntity>.toDomain() = map {
    PhotoModel(
        id = it.id,
        width = it.width,
        height = it.height,
        color = it.color,
        likes = it.likes,
        description = it.description,
        createDate = it.createDate.toDateFormatString(),
        urls = UrlsModel(
            raw = it.urls.raw,
            full = it.urls.full,
            regular = it.urls.regular,
            small = it.urls.small,
            thumb = it.urls.thumb,
        ),
    )
}
