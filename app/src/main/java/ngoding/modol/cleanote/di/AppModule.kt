package ngoding.modol.cleanote.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import ngoding.modol.cleanote.CleanoteDatabase
import ngoding.modol.cleanote.Dispatcher
import ngoding.modol.cleanote.data.dao.NoteDao
import ngoding.modol.cleanote.data.repository.LocalNoteRepository
import ngoding.modol.cleanote.data.repository.NoteRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDispatcher(): Dispatcher {
        return Dispatcher(
            main = Dispatchers.Main,
            io = Dispatchers.IO
        )
    }

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext applicationContext: Context,
    ): CleanoteDatabase {
        return Room.databaseBuilder(
            applicationContext,
            CleanoteDatabase::class.java, "cleanote-database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteDao(
        database: CleanoteDatabase
    ): NoteDao {
       return database.noteDao()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(
        noteDao: NoteDao
    ): NoteRepository {
        return LocalNoteRepository(noteDao)
    }

}