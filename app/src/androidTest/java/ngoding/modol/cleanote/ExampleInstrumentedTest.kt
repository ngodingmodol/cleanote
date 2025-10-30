package ngoding.modol.cleanote

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import ngoding.modol.cleanote.ui.notelist.NoteListScreen
import ngoding.modol.cleanote.ui.notelist.NoteListScreenEvent
import ngoding.modol.cleanote.ui.notelist.NoteListScreenState

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun createButtonClick_shouldSendCreateEvent() {
        var event: NoteListScreenEvent? = null
        composeTestRule.setContent {
            NoteListScreen(
                state = NoteListScreenState(emptyList()),
                event = { event = it}
            )
        }

        composeTestRule.onNodeWithTag("Create Button")
            .assertIsDisplayed()
            .performClick()

        assertEquals(NoteListScreenEvent.CreateNoteClick, event)
    }


    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("ngoding.modol.cleanote", appContext.packageName)
    }
}