package rs.raf.projekat2.vuk_vukovic_rn9420.data.datasources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import rs.raf.projekat2.vuk_vukovic_rn9420.data.models.note.NoteEntity
import rs.raf.projekat2.vuk_vukovic_rn9420.data.models.subject.SubjectEntity

@Database(
    entities = [SubjectEntity::class, NoteEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters
abstract class Database : RoomDatabase(){
    abstract fun getSubjectDao(): SubjectDao
    abstract fun getNoteDao(): NoteDao
}