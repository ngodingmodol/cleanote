package ngoding.modol.cleanote.ui.notelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ngoding.modol.cleanote.Dispatcher
import ngoding.modol.cleanote.data.repository.NoteRepository
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val dispatcher: Dispatcher,
    private val noteRepository: NoteRepository
) : ViewModel() {

    private val _state = MutableStateFlow(NoteListScreenState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch(dispatcher.main) {
            noteRepository.getAll().onEach { notes ->
                _state.update { state ->
                    state.copy(notes = notes.map {
                        NoteListScreenState.Note(
                            id = it.uid,
                            title = it.title,
                            body = it.body,
                        )
                    })
                }
            }.launchIn(this)
        }
    }

    fun deleteNote(id: Int) {
        viewModelScope.launch(dispatcher.io) {
            noteRepository.delete(id)
        }
    }
}