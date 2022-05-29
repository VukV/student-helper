package rs.raf.projekat2.vuk_vukovic_rn9420.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.vuk_vukovic_rn9420.data.models.note.Note
import java.util.*

interface NoteRepository {

    fun getAll(): Observable<List<Note>>
    fun getAllByTitleOrContent(searchTag: String, archivedSearch: Boolean): Observable<List<Note>>
    fun getOnlyUnarchived(): Observable<List<Note>>
    fun insert(title: String, content: String): Completable
    fun update(id: Int, title: String, content: String, archived: Boolean, date:Date): Completable
    fun deleteById(id: Int): Completable
    fun getLastFiveDays(): Observable<List<Note>>
}