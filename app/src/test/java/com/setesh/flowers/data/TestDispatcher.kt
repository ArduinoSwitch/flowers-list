package com.setesh.flowers.data

import com.setesh.commons.di.BackDispatchers
import com.setesh.commons.di.DomainDispatcher
import com.setesh.commons.di.FrontDispatchers
import kotlinx.coroutines.CoroutineDispatcher

class TestDispatcher(
    override val ui: CoroutineDispatcher,
    override val io: CoroutineDispatcher,
    override val cpu: CoroutineDispatcher,
): DomainDispatcher, FrontDispatchers, BackDispatchers {
    constructor(dispatcher: CoroutineDispatcher): this(dispatcher, dispatcher, dispatcher)
}