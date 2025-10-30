package ngoding.modol.cleanote.ui.notedetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ngoding.modol.cleanote.Dispatcher
import ngoding.modol.cleanote.data.repository.NoteRepository


@HiltViewModel(assistedFactory = NoteDetailViewModel.Factory::class)
class NoteDetailViewModel @AssistedInject constructor(
    @Assisted private val noteId: Int?,
    private val dispatcher: Dispatcher,
    private val noteRepository: NoteRepository
) : ViewModel() {

    private val _state = MutableStateFlow(NoteDetailScreenState())
    val state = _state.asStateFlow()

    init {
        noteId?.let { noteId ->
            viewModelScope.launch(dispatcher.io) {
                noteRepository.get(noteId)?.let { note ->
                    withContext(dispatcher.main) {
                        _state.update {
                            it.copy(
                                title = note.title,
                                body = note.body,
                            )
                        }
                    }
                }
            }
        }
    }

    fun changeTitle(title: String) {
        _state.update { it.copy(title = title) }
    }

    fun changeBody(body: String) {
        _state.update { it.copy(body = body) }
    }

    fun save() {
        viewModelScope.launch(dispatcher.io) {
            if (noteId != null) {
                noteRepository.update(
                    id = noteId,
                    title = _state.value.title,
                    body = _state.value.body,
                )
            } else {
                noteRepository.create(
                    title = _state.value.title,
                    body = state.value.body,
                )
            }
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(noteId: Int?): NoteDetailViewModel
    }
}


