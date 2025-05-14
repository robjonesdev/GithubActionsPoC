package com.allegion.githubactionspoc.ui.viewmodel.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.allegion.githubactionspoc.data.SampleDataRepository
import com.allegion.githubactionspoc.ui.viewmodel.MainActivityViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class ViewModelFactory(
    private val sampleDataRepository: SampleDataRepository = SampleDataRepository(),
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
            return MainActivityViewModel(sampleDataRepository, dispatcher) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}