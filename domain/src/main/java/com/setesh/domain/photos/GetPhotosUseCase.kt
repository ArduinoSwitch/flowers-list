package com.setesh.domain.photos

import com.setesh.commons.response.MyResult
import com.setesh.commons.response.UiApiError
import com.setesh.commons.usecase.UseCaseSuspend

typealias GetPhotosUseCaseT = UseCaseSuspend<Unit, MyResult<List<PhotoModel>, UiApiError>>

class GetPhotosUseCase(
    private val photoDataSource: PhotoDataSource,
):GetPhotosUseCaseT {
    override suspend fun invoke(params: Unit): MyResult<List<PhotoModel>, UiApiError> =
        photoDataSource.getPhotos()
}