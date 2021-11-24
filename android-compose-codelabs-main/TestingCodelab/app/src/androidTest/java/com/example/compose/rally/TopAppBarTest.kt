package com.example.compose.rally

import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import com.example.compose.rally.ui.components.RallyTopAppBar
import org.junit.Rule
import org.junit.Test

class TopAppBarTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun rallyTopAppBarTest() {
        composeTestRule.setContent {
            RallyTopAppBar(
                allScreens = RallyScreen.values().toList(),
                onTabSelected = {},
                currentScreen = RallyScreen.Accounts,
            )
        }

        composeTestRule
            .onNodeWithContentDescription(RallyScreen.Accounts.name)
            .assertExists()
    }

    @Test
    fun rallyTopAppBarTest_currentLabelExists() {
        val allScreens = RallyScreen.values().toList()
        composeTestRule.setContent {
            RallyTopAppBar(
                allScreens = allScreens,
                onTabSelected = { },
                currentScreen = RallyScreen.Accounts
            )
        }

        composeTestRule.onRoot(useUnmergedTree = true).printToLog("currentLabelExists")


    }
}
