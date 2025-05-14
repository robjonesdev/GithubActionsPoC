package com.allegion.githubactionspoc.ui.viewmodel.factories

import androidx.lifecycle.ViewModel
import com.allegion.githubactionspoc.data.SampleDataRepository
import com.allegion.githubactionspoc.ui.viewmodel.MainActivityViewModel
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class ViewModelFactoryTest {
    @Mock
    private lateinit var repository: SampleDataRepository

    private lateinit var factory: ViewModelFactory

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        factory = ViewModelFactory(repository)
    }

    @Test
    fun `create returns MainActivityViewModel when requested`() {
        val viewModel = factory.create(MainActivityViewModel::class.java)
        assertTrue(viewModel is MainActivityViewModel)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `create throws exception for unknown ViewModel class`() {
        factory.create(TestViewModel::class.java)
    }

    private class TestViewModel : ViewModel()
}
