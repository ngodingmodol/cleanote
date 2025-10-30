@file:OptIn(ExperimentalCoroutinesApi::class)

package ngoding.modol.cleanote.ui.notedetail

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import ngoding.modol.cleanote.Dispatcher
import ngoding.modol.cleanote.data.entity.Note
import ngoding.modol.cleanote.data.repository.NoteRepository
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class NoteDetailViewModelTest {

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
    fun `given noteId is not null and data is exist, when init, should load correct data`() {
        // Given
        val noteId = 0
        val noteDummy = Note(
            uid = 0,
            title = "title 0",
            body = "body 0"
        )
        coEvery { noteRepositoryMock.get(noteId) } returns noteDummy

        // When
        val sut = NoteDetailViewModel(
            noteId = noteId,
            dispatcher = dispatcher,
            noteRepository = noteRepositoryMock,
        )

        // Then
        Assert.assertEquals(noteDummy.title, sut.state.value.title)
        Assert.assertEquals(noteDummy.body, sut.state.value.body)
    }

    @Test
    fun `given noteId is null, when init, should not update`() {
        // given
        val noteId = null

        // when
        NoteDetailViewModel(
            noteId = noteId,
            dispatcher = dispatcher,
            noteRepository = noteRepositoryMock,
        )

        // Then
        coVerify(exactly = 0) { noteRepositoryMock.get(any()) }
    }

    @Test
    fun `given noteId is not null and data is not exist, when init, should not update`() {
        // given
        val noteId = 0
        coEvery { noteRepositoryMock.get(noteId) } returns null

        // when
        val sut = NoteDetailViewModel(
            noteId = noteId,
            dispatcher = dispatcher,
            noteRepository = noteRepositoryMock,
        )

        // Then
        coVerify(exactly = 1) { noteRepositoryMock.get(noteId) }
        Assert.assertEquals("", sut.state.value.title)
        Assert.assertEquals("", sut.state.value.body)
    }

    @Test
    fun `when changeTitle, should change title`() {
        val titleDummy = "ngoding"
        val sut = NoteDetailViewModel(
            noteId = null,
            dispatcher = dispatcher,
            noteRepository = noteRepositoryMock,
        )

        sut.changeTitle(titleDummy)

        Assert.assertEquals(titleDummy, sut.state.value.title)
    }

    @Test
    fun `when changeBody, should change body`() {
        val bodyDummy = "modol"
        val sut = NoteDetailViewModel(
            noteId = null,
            dispatcher = dispatcher,
            noteRepository = noteRepositoryMock,
        )

        sut.changeBody(bodyDummy)

        Assert.assertEquals(bodyDummy, sut.state.value.body)
    }

    @Test
    fun `given noteId is not null, when save, should update`() {
        // Given
        val noteId = 0
        val sut = NoteDetailViewModel(
            noteId = noteId,
            dispatcher = dispatcher,
            noteRepository = noteRepositoryMock,
        )

        // When
        sut.save()

        // Then
        coVerify { noteRepositoryMock.update(noteId, "", "") }
    }

    @Test
    fun `given noteId is null, when save, should create`() {
        // Given
        val sut = NoteDetailViewModel(
            noteId = null,
            dispatcher = dispatcher,
            noteRepository = noteRepositoryMock,
        )

        // When
        sut.save()

        // Then
        coVerify { noteRepositoryMock.create( "", "") }
    }
}