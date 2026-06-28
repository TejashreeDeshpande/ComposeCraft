package com.example.composecraft.features.vehicle.common.progress

import app.cash.turbine.test
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ProgressBarViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var viewModel: ProgressBarViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = ProgressBarViewModel()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `startTrip updates distance and progress over time`() = runTest(testDispatcher) {
        // Collect progress to trigger the WhileSubscribed stateIn
        val collectJob = backgroundScope.launch { viewModel.progress.collect {} }

        viewModel.startTrip(10.0f)

        assertEquals(0.0f, viewModel.currentDistance.value)
        assertEquals(10.0f, viewModel.targetDistance.value)
        assertEquals(0.0f, viewModel.progress.value)

        advanceTimeBy(1001) // After 1 second delay
        assertEquals(1.0f, viewModel.currentDistance.value)
        assertEquals(0.1f, viewModel.progress.value)

        advanceTimeBy(5000) // 5 more seconds
        assertEquals(6.0f, viewModel.currentDistance.value)
        assertEquals(0.6f, viewModel.progress.value)

        advanceTimeBy(4000) // 4 more seconds, total 10
        assertEquals(10.0f, viewModel.currentDistance.value)
        assertEquals(1.0f, viewModel.progress.value)
    }

    @Test
    fun `stop cancels the trip updates`() = runTest(testDispatcher) {
        viewModel.startTrip(10.0f)
        advanceTimeBy(2001)
        assertEquals(2.0f, viewModel.currentDistance.value)

        viewModel.stop()
        advanceTimeBy(5000)
        assertEquals(2.0f, viewModel.currentDistance.value) // Should not have increased
    }

    @Test
    fun `progress is calculated correctly with turbine`() = runTest(testDispatcher) {
        viewModel.progress.test {
            assertEquals(0.0f, awaitItem()) // Initial value

            viewModel.startTrip(5.0f)
            // advanceTimeBy(0) // Start trip updates distance to 0, target to 5
            // But progress is a StateFlow combined from current and target.
            // It might emit 0.0 again if it recomputes.

            advanceTimeBy(1001)
            assertEquals(0.2f, awaitItem())

            advanceTimeBy(1000)
            assertEquals(0.4f, awaitItem())

            advanceTimeBy(3000)
            assertEquals(0.6f, awaitItem())
            assertEquals(0.8f, awaitItem())
            assertEquals(1.0f, awaitItem())
        }
    }
}
