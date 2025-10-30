package ngoding.modol.cleanote.ui.notelist

import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
object NoteListRoute

fun NavGraphBuilder.noteListScreenRoute(
    onNavigateToNoteDetail: (noteId: Int?) -> Unit
) {
    composable<NoteListRoute> {
        val viewModel = hiltViewModel<NoteListViewModel>()
        NoteListScreen(
            viewModel = viewModel,
            event = {
                when (it) {
                    NoteListScreenEvent.CreateNoteClick -> {
                        onNavigateToNoteDetail(null)
                    }

                    is NoteListScreenEvent.DeleteNoteClick -> {
                        viewModel.deleteNote(it.id)
                    }

                    is NoteListScreenEvent.EditNoteClick -> {
                        onNavigateToNoteDetail(it.id)
                    }
                }
            }
        )
    }
}