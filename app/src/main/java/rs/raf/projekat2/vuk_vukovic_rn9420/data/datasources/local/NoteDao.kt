package rs.raf.projekat2.vuk_vukovic_rn9420.data.datasources.local


import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.vuk_vukovic_rn9420.data.models.note.NoteEntity


@Dao
abstract class NoteDao {

    @Query("SELECT * FROM notes")
    abstract fun getAll(): Observable<List<NoteEntity>>

    @Query("SELECT * FROM notes WHERE id == :id")
    abstract fun getById(id: Int): NoteEntity

    @Query("SELECT * FROM notes WHERE archived == 0")
    abstract fun getAllUnarchived(): Observable<List<NoteEntity>>

    @Query("SELECT * FROM notes WHERE title LIKE '%' || :searchTag || '%' OR content LIKE '%' || :searchTag || '%'")
    abstract fun getByTitleOrContent(searchTag: String): Observable<List<NoteEntity>>

    @Query("SELECT * FROM notes WHERE (title LIKE '%' || :searchTag || '%' OR content LIKE '%' || :searchTag || '%') AND archived == 0")
    abstract fun getByTitleOrContentUnarchived(searchTag: String): Observable<List<NoteEntity>>

    @Insert
    abstract fun insert(noteEntity: NoteEntity): Completable

    @Update
    abstract fun update(noteEntity: NoteEntity): Completable

    @Transaction
    open fun updateById(id: Int, title: String, content: String) {
        val note = getById(id)
        val updatedNote = note.copy(
            title = title,
            content = content
        )
        return update(updatedNote).blockingAwait()
    }

    @Transaction
    open fun updateArchivedById(id: Int, archived: Boolean){
        val note = getById(id)
        val updatedNote = note.copy(
            archived = archived
        )
        return update(updatedNote).blockingAwait()
    }

    @Delete
    abstract fun delete(noteEntity: NoteEntity): Completable

    @Query("DELETE FROM notes WHERE id == :id")
    abstract fun deleteById(id: Int): Completable
}