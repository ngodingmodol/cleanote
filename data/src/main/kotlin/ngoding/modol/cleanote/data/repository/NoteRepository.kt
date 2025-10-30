package ngoding.modol.cleanote.data.repository

import kotlinx.coroutines.flow.Flow
import ngoding.modol.cleanote.data.dao.NoteDao
import ngoding.modol.cleanote.data.entity.Note

interface NoteRepository {
    fun getAll(): Flow<List<Note>>
    suspend fun get(id: Int): Note?
    suspend fun create(title: String, body: String)
    suspend fun update(id: Int, title: String, body: String)
    suspend fun delete(id: Int)
}
class LocalNoteRepository(
    private val noteDao: NoteDao
): NoteRepository {

    override fun getAll(): Flow<List<Note>> {
        return noteDao.getAll()
    }

    override suspend fun get(id: Int): Note? {
        return noteDao.findById(id)
    }

    override suspend fun create(title: String, body: String) {
        noteDao.insert(
            Note(
                title = title,
                body = body,
            )
        )
    }

    override suspend fun update(id: Int, title: String, body: String) {
        noteDao.updateNote(
            Note(
                uid = id,
                title = title,
                body = body,
            )
        )
    }

    override suspend fun delete(id: Int) {
        noteDao.delete(id)
    }
}