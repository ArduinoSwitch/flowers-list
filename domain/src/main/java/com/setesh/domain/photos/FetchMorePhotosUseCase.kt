package com.setesh.domain.photos

import com.setesh.commons.response.MyResult
import com.setesh.commons.response.UiApiError
import com.setesh.commons.usecase.UseCaseSuspend

typealias FetchMorePhotosUseCaseT = UseCaseSuspend<Unit, MyResult<Unit, UiApiError>>

class FetchMorePhotosUseCase(
    private val photosRepository: PhotosRepository,
): FetchMorePhotosUseCaseT {
    override suspend fun invoke(params: Unit): MyResult<Unit, UiApiError> =
        photosRepository.fetchMorePhotos()
}