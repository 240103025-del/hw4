package com.example.hw4

import androidx.compose.ui.test.junit4.createComposeRule
import com.example.hw4.ui.theme.Hw4Theme
import org.junit.Rule
import org.junit.Test

class TipUITests {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun calculate_20_percent_tip() {
        composeTestRule.setContent {
            Hw4Theme {
                TipTimeLayout()
            }
        }
    }
}