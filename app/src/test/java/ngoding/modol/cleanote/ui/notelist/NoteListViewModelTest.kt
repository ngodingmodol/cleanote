package ngoding.modol.cleanote.ui.notelist

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import ngoding.modol.cleanote.Dispatcher
import ngoding.modol.cleanote.data.entity.Note
import ngoding.modol.cleanote.data.repository.NoteRepository
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class NoteListViewModelTest {
    lateinit var noteRepositoryMock: NoteRepository
    lateinit var dispatcher: Dispatcher

    @Before
    fun setup() {
        noteRepositoryMock = mockk<NoteRepository>(relaxed = true)
        dispatcher = Dispatcher(
            main = UnconfinedTestDispatcher(),
            io = UnconfinedTestDispatcher()
        )
    }

    @Test
    fun `when init, should load correct data`() {
        // Given
        val dummyNotes = List(3) {
            Note(
                uid = it,
                title = "Title $it",
                body = "Body $it"
            )
        }
        coEvery { noteRepositoryMock.getAll() } returns flowOf(dummyNotes)

        val sut = NoteListViewModel(
            dispatcher = dispatcher,
            noteRepository = noteRepositoryMock,
        )

        Assert.assertArrayEquals(
            dummyNotes.toTypedArray(),
            sut.state.value.notes.map { Note(it.id, it.title, it.body) }.toTypedArray(),
        )
    }

    @Test
    fun `when deleteNote, should call noteRepository delete`() {
        // Given
        val idToDelete = 0
        val sut = NoteListViewModel(
            dispatcher = dispatcher,
            noteRepository = noteRepositoryMock,
        )
        // When
        sut.deleteNote(idToDelete)

        // Then
        coVerify { noteRepositoryMock.delete(idToDelete) }
    }
}