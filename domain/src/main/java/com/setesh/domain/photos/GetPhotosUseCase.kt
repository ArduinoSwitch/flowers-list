package com.setesh.domain.photos

import com.setesh.commons.usecase.UseCase
import kotlinx.coroutines.flow.Flow

typealias GetPhotosUseCaseT = UseCase<Unit, Flow<List<PhotoModel>>>

class GetPhotosUseCase(
    private val photoDataSource: PhotosRepository,
):GetPhotosUseCaseT {
    override fun invoke(params: Unit): Flow<List<PhotoModel>> =
        photoDataSource.getPhotos()
}