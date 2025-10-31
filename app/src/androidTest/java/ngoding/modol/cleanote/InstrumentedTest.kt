package ngoding.modol.cleanote

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Rule

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class InstrumentedTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun createEditDelete() {
        // Create note
        composeTestRule.onNodeWithTag("Create Button")
            .assertIsDisplayed()
            .performClick()
        composeTestRule.onNodeWithTag("textFieldTitle")
            .assertIsDisplayed()
            .performTextInput("Title 1")
        composeTestRule.onNodeWithTag("textFieldBody")
            .assertIsDisplayed()
            .performTextInput("Body 1")
        composeTestRule.onNodeWithTag("buttonSave")
            .assertIsDisplayed()
            .performClick()
        composeTestRule.onNodeWithText("Title 1")
            .assertIsDisplayed()

        // Edit note
        composeTestRule.onNodeWithContentDescription("Note Item Option")
            .assertIsDisplayed()
            .performClick()
        composeTestRule.onNodeWithTag("optionEdit")
            .assertIsDisplayed()
            .performClick()
        composeTestRule.onNodeWithTag("textFieldTitle")
            .assertIsDisplayed()
            .performTextInput("Edited")
        composeTestRule.onNodeWithTag("buttonSave")
            .assertIsDisplayed()
            .performClick()
        composeTestRule.onNodeWithText("EditedTitle 1")
            .assertIsDisplayed()

        // Delete note
        composeTestRule.onNodeWithContentDescription("Note Item Option")
            .assertIsDisplayed()
            .performClick()
        composeTestRule.onNodeWithTag("optionDelete")
            .assertIsDisplayed()
            .performClick()
        composeTestRule.onNodeWithText("EditedTitle 1")
            .assertDoesNotExist()
    }
}