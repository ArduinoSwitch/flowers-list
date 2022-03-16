package com.setesh.flowers.feature.main.ui

import com.setesh.commons.navigation.Navigator
import com.setesh.domain.photos.*
import com.setesh.flowers.data.FakePhotosRepository
import com.setesh.flowers.data.TestDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import org.junit.Assert.assertEquals
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

@ExperimentalCoroutinesApi
class MainViewModelTest {

    private val testDispatcher = TestCoroutineDispatcher()
    private val appDispatcher = TestDispatcher(testDispatcher)
    private val fakePhotosRepository = FakePhotosRepository()

    @Test
    fun `when observe, the flowersUiList returns the same as the UseCase`() =
        runBlockingTest {
            val vm = buildViewModel()
            val result = vm.flowersUiList.first()
            val expected = GetPhotosUseCase(fakePhotosRepository).invoke(Unit).first()
            assertEquals(result, expected)
        }

    private fun buildViewModel() =
        MainViewModel(
            navigator = Navigator(),
            dispatchers = appDispatcher,
            getPhotosUseCase = GetPhotosUseCase(fakePhotosRepository),
            fetchMorePhotosUseCase = FetchMorePhotosUseCase(fakePhotosRepository)
        )
}
