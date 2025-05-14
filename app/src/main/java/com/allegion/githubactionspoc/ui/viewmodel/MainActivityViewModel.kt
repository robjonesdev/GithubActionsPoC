package com.allegion.githubactionspoc.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.allegion.githubactionspoc.data.SampleDataRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainActivityViewModel(
    val sampleDataRepository: SampleDataRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean>
        get() {
            return _isLoading
        }

    fun fetchSampleData() {
        viewModelScope.launch(dispatcher) {
            _isLoading.value = true
            sampleDataRepository.fetchNewSampleData(3).collect {
                println("Fetched data [$it]")
            }
            _isLoading.value = false
        }
    }
}