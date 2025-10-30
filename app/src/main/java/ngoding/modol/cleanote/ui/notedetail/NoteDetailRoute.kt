package ngoding.modol.cleanote.ui.notedetail

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

@Serializable
data class NoteDetailRoute(val noteId: Int?)

fun NavController.navigateToNoteDetail(noteId: Int?) {
    navigate(
        route = NoteDetailRoute(noteId)
    )
}

fun NavGraphBuilder.noteDetailScreenRoute(
    onNavigateBack: () -> Unit
) {
    composable<NoteDetailRoute> { backstackEntry ->
        val route: NoteDetailRoute = backstackEntry.toRoute()
        val viewModel = hiltViewModel<NoteDetailViewModel, NoteDetailViewModel.Factory>(
            creationCallback = { it.create(route.noteId) }
        )

        val state by viewModel.state.collectAsState()

        NoteDetailScreen(
            state = state,
            onTitleChange = viewModel::changeTitle,
            onBodyChange = viewModel::changeBody,
            onSaveClick = {
                viewModel.save()
                onNavigateBack()
            }
        )
    }
}

