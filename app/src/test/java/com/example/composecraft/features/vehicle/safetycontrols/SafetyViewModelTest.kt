package com.example.composecraft.features.vehicle.safetycontrols

import app.cash.turbine.test
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SafetyViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var viewModel: SafetyViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = SafetyViewModel()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `initial state is Idle`() {
        assertEquals(SafetyUiState.Idle, viewModel.uiState.value.safetyState)
        assertEquals(0f, viewModel.uiState.value.holdProgress)
    }

    @Test
    fun `onSafetyAction PULL_OVER changes state to ConfirmPullOver`() {
        viewModel.onSafetyAction(SafetyActionType.PULL_OVER)
        assertEquals(SafetyUiState.ConfirmPullOver, viewModel.uiState.value.safetyState)
    }

    @Test
    fun `onSosHoldStart increases progress over time and triggers emergency stop`() = runTest {
        viewModel.uiState.test {
            assertEquals(SafetyUiState.Idle, awaitItem().safetyState)

            viewModel.onSafetyAction(SafetyActionType.EMERGENCY_STOP)
            assertEquals(SafetyUiState.ConfirmEmergencyStop, awaitItem().safetyState)

            viewModel.onSosHoldStart()
            
            // Initial progress after first delay (50ms)
            advanceTimeBy(51)
            assertTrue(awaitItem().holdProgress > 0f)

            // Advance to the end
            advanceTimeBy(3000)
            
            // Collect all intermediate progress updates until EmergencyStopped
            var lastState: SafetyUiState = SafetyUiState.ConfirmEmergencyStop
            while(lastState !is SafetyUiState.EmergencyStopped) {
                lastState = awaitItem().safetyState
            }
            assertEquals(SafetyUiState.EmergencyStopped, lastState)
        }
    }

    @Test
    fun `onSosHoldEnd resets progress`() = runTest {
        viewModel.onSosHoldStart()
        advanceTimeBy(1000)
        
        assertTrue(viewModel.uiState.value.holdProgress > 0f)

        viewModel.onSosHoldEnd()
        assertEquals(0f, viewModel.uiState.value.holdProgress)
    }
}
