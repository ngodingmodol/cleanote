package ngoding.modol.cleanote.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import ngoding.modol.cleanote.data.entity.Note

@Dao
interface NoteDao {
    @Query("SELECT * FROM note")
    fun getAll(): Flow<List<Note>>

    @Query("SELECT * FROM note WHERE uid LIKE :id LIMIT 1")
    suspend fun findById(id: Int): Note?

    @Update
    suspend fun updateNote(note: Note)

    @Insert
    fun insertAll(vararg notes: Note)

    @Insert
    suspend fun insert(note: Note)

    @Query("DELETE FROM Note WHERE uid = :id")
    suspend fun delete(id: Int)
}