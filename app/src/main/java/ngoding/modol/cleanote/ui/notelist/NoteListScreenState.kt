package ngoding.modol.cleanote.ui.notelist

data class NoteListScreenState(
    val notes: List<Note> = emptyList()
) {
    data class Note(
        val id: Int,
        val title: String,
        val body: String
    )
}