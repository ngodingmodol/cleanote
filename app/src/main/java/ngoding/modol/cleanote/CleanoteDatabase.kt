package ngoding.modol.cleanote

import androidx.room.Database
import androidx.room.RoomDatabase
import ngoding.modol.cleanote.data.dao.NoteDao
import ngoding.modol.cleanote.data.entity.Note

@Database(entities = [Note::class], version = 1)
abstract class CleanoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}