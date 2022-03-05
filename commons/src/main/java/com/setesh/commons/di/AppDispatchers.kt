package com.setesh.commons.di

import kotlinx.coroutines.CoroutineDispatcher

data class AppDispatchers(
    override val ui: CoroutineDispatcher,
    override val cpu: CoroutineDispatcher,
    override val io: CoroutineDispatcher,
) : DomainDispatcher, FrontDispatchers, BackDispatchers

interface FrontDispatchers {
    val ui: CoroutineDispatcher
    val cpu: CoroutineDispatcher
}

interface BackDispatchers {
    val cpu: CoroutineDispatcher
    val io: CoroutineDispatcher
}

interface DomainDispatcher {
    val cpu: CoroutineDispatcher
}
