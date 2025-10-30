package ngoding.modol.cleanote.ui.notelist

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import ngoding.modol.cleanote.ui.theme.CleanoteTheme

@Composable
fun NoteListScreen(
    viewModel: NoteListViewModel,
    event: (NoteListScreenEvent) -> Unit,
) {
   val state by viewModel.state.collectAsState()

    NoteListScreen(
        state = state,
        event = event,
    )
}

@Composable
fun NoteListScreen(
    state: NoteListScreenState,
    event: (NoteListScreenEvent) -> Unit,
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    event(NoteListScreenEvent.CreateNoteClick)
                },
                modifier = Modifier.testTag("Create Button")
            ) {
                Icon(Icons.Filled.Add, null)
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            items(
                count = state.notes.size,
                key = { state.notes.getOrNull(it)?.id ?: 0 }
            ) {
                val note = state.notes.getOrNull(it) ?: return@items
                NoteListItem(
                    note = note,
                    onEdit = {
                        event(NoteListScreenEvent.EditNoteClick(note.id))
                    },
                    onDelete = {
                        event(NoteListScreenEvent.DeleteNoteClick(note.id))
                    },
                )
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun NoteListScreenPreview() {
    CleanoteTheme {
        NoteListScreen(
            state = NoteListScreenState(
                notes = List(100) {
                    NoteListScreenState.Note(
                        id = it,
                        title = "Note Title $it",
                        body = "Note Body $it"
                    )
                }
            ),
            event = {}
        )
    }
}