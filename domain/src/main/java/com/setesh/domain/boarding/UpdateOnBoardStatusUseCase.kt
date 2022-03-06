package com.setesh.domain.boarding

import com.setesh.commons.preferences.AppPreferencesDataSource
import com.setesh.commons.usecase.UseCaseSuspend

typealias UpdateOnBoardStatusUseCaseT = UseCaseSuspend<Unit, Unit>

class UpdateOnBoardStatusUseCase(
    private val appPreferencesDataSource: AppPreferencesDataSource,
): UpdateOnBoardStatusUseCaseT {
    override suspend fun invoke(params: Unit) {
        appPreferencesDataSource.updateOnBoardStatus()
    }
}