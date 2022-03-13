package com.setesh.data.photos

import com.setesh.commons.response.map
import com.setesh.commons.response.onSuccess
import com.setesh.commons.utils.dateTimeToMiliseconds
import com.setesh.data.database.photo.PhotoDao
import com.setesh.data.database.photo.PhotoEntity
import com.setesh.data.database.photo.UrlsEmbedded
import com.setesh.domain.photos.PhotoDataSource
import com.setesh.domain.photos.PhotoModel
import com.setesh.domain.photos.PhotosRepository
import kotlinx.coroutines.flow.Flow

class PhotosRepositoryImpl(
    private val photosDataSource: PhotoDataSource,
    private val photoDao: PhotoDao,
) : PhotosRepository {
    override fun getPhotos(): Flow<List<PhotoModel>> =
        photoDao.getAllPhotos()

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
        createDate = it.createDate.dateTimeToMiliseconds(),
        urls = UrlsEmbedded(
            raw = it.urls.raw,
            full = it.urls.full,
            regular = it.urls.regular,
            small = it.urls.small,
            thumb = it.urls.thumb,
        )
    )
}
