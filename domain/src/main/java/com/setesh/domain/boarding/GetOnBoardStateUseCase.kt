package com.setesh.domain.boarding

import com.setesh.commons.preferences.AppPreferencesDataSource
import com.setesh.commons.usecase.UseCaseSuspend

typealias GetOnBoardStateUseCaseT = UseCaseSuspend<Unit, Boolean>

class GetOnBoardStateUseCase(
    private val appPreferencesDataSource: AppPreferencesDataSource,
): GetOnBoardStateUseCaseT {
    override suspend  fun invoke(params: Unit): Boolean =
        appPreferencesDataSource.getOnBoardStatus()
}