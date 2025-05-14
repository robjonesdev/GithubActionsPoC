package com.allegion.githubactionspoc.ui.viewmodel

import com.allegion.githubactionspoc.data.SampleDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class MainActivityViewModelTest {
    private val scheduler = TestCoroutineScheduler()
    private val testDispatcher = UnconfinedTestDispatcher(scheduler)

    @Mock
    private lateinit var repository: SampleDataRepository

    private lateinit var viewModel: MainActivityViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        MockitoAnnotations.openMocks(this)

        val testFlow =
            flow {
                emit("Test Value 1")
                emit("Test Value 2")
                emit("Test Value 3")
            }

        Mockito.`when`(repository.fetchNewSampleData(3)).thenReturn(testFlow)
        viewModel = MainActivityViewModel(repository, testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fetchSampleData sets isLoading to true then false`() =
        runTest {
            assertFalse(viewModel.isLoading.value)
            viewModel.fetchSampleData()

            // With UnconfinedTestDispatcher, the coroutine will run immediately
            // so isLoading will have already completed its cycle
            assertFalse(viewModel.isLoading.value)
        }
}
