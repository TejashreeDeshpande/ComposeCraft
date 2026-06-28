package com.example.composecraft.features.vehicle.common.progress

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

class CircularProgressScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun progressPercentageIsDisplayed() {
        composeTestRule.setContent {
            CircularProgressContent(
                progress = 0.45f,
                currentDistance = 4.5f,
                targetDistance = 10.0f,
                onClickStart = {},
                onClickStop = {}
            )
        }

        composeTestRule.onNodeWithText("45%").assertIsDisplayed()
        composeTestRule.onNodeWithText("4.5 / 10.0 km").assertIsDisplayed()
        composeTestRule.onNodeWithText("Vehicle Trip Progress").assertIsDisplayed()
    }

    @Test
    fun startTripButtonIsDisplayed() {
        composeTestRule.setContent {
            CircularProgressContent(
                progress = 0f,
                currentDistance = 0f,
                targetDistance = 0f,
                onClickStart = {},
                onClickStop = {}
            )
        }

        composeTestRule.onNodeWithText("Start Trip").assertIsDisplayed()
    }
}
