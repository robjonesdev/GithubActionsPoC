package com.allegion.githubactionspoc.data

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class SampleDataRepositoryTest {
    private lateinit var repository: SampleDataRepository

    @Before
    fun setup() {
        repository = SampleDataRepository()
    }

    @Test
    fun `verify repository emits correct number of items`() =
        runTest {
            val repository = SampleDataRepository()
            val fetchAmount = 3

            val results = repository.fetchNewSampleData(fetchAmount).take(fetchAmount).toList()

            assertEquals(fetchAmount, results.size)
        }

    @Test
    fun `verify repository emits values in expected format`() =
        runTest {
            val repository = SampleDataRepository()
            val fetchAmount = 2

            val results = repository.fetchNewSampleData(fetchAmount).take(fetchAmount).toList()

            for (i in 0 until fetchAmount) {
                assertEquals("New Value: $i", results[i])
            }
        }

    @Test
    fun `verify repository with zero fetchAmount emits zero items`() =
        runTest {
            val repository = SampleDataRepository()
            val fetchAmount = 0

            val results = repository.fetchNewSampleData(fetchAmount)

            assertTrue(results.toList().isEmpty())
        }

    @Test
    fun `verify repository with negative fetchAmount emits nothing`() =
        runTest {
            val repository = SampleDataRepository()
            val fetchAmount = -1

            val results = repository.fetchNewSampleData(fetchAmount).take(1).toList()

            assertTrue(results.isEmpty())
        }

    @Test
    fun `fetchNewSampleData emits correct number of items`() =
        runTest {
            val fetchAmount = 3

            val results = repository.fetchNewSampleData(fetchAmount).take(fetchAmount).toList()

            assertEquals(fetchAmount, results.size)
        }

    @Test
    fun `fetchNewSampleData emits values in expected format`() =
        runTest {
            val fetchAmount = 3

            val results = repository.fetchNewSampleData(fetchAmount).take(fetchAmount).toList()

            for (i in 0 until fetchAmount) {
                assertEquals("New Value: $i", results[i])
            }
        }
}
