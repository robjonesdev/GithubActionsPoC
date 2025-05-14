package com.allegion.githubactionspoc.data

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlin.random.Random

class SampleDataRepository {
    fun fetchNewSampleData(fetchAmount: Int): Flow<String> {
        if (fetchAmount < 1) {
            return flowOf()
        } else {
            val dataFlow =
                flow<String> {
                    for (i in 0 until fetchAmount) {
                        // Simulated Delay
                        delay(Random.nextInt(5000).toLong())
                        emit("New Value: $i")
                    }
                }
            return dataFlow
        }
    }
}
