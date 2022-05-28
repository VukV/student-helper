package rs.raf.projekat2.vuk_vukovic_rn9420.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.vuk_vukovic_rn9420.data.models.note.Note

interface NoteRepository {

    fun getAll(): Observable<List<Note>>
    fun getAllByTitleOrContent(searchTag: String): Observable<List<Note>>
    fun insert(title: String, content: String): Completable
    fun updateById(id: Long, title: String, content: String): Completable
    fun updateArchivedById(id: Long, archived: Boolean): Completable
    fun deleteById(id: Long): Completable
}