package com.setesh.commons.usecase

interface UseCaseSuspend<Params, Return> {
    suspend operator fun invoke(params: Params): Return
}
