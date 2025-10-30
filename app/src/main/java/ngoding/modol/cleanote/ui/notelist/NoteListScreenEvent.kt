package ngoding.modol.cleanote.ui.notelist

sealed interface NoteListScreenEvent {
    object CreateNoteClick: NoteListScreenEvent
    data class EditNoteClick(val id: Int): NoteListScreenEvent
    data class DeleteNoteClick(val id: Int): NoteListScreenEvent
}