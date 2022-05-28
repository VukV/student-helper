package rs.raf.projekat2.vuk_vukovic_rn9420.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.vuk_vukovic_rn9420.data.datasources.local.NoteDao
import rs.raf.projekat2.vuk_vukovic_rn9420.data.models.note.Note

class NoteRepositoryImpl(private val localDataSource: NoteDao) : NoteRepository {

    override fun getAll(): Observable<List<Note>> {
        TODO("Not yet implemented")
    }

    override fun getAllByTitleOrContent(searchTag: String): Observable<List<Note>> {
        TODO("Not yet implemented")
    }

    override fun insert(title: String, content: String): Completable {
        TODO("Not yet implemented")
    }

    override fun updateById(id: Long, title: String, content: String): Completable {
        TODO("Not yet implemented")
    }

    override fun updateArchivedById(id: Long, archived: Boolean): Completable {
        TODO("Not yet implemented")
    }

    override fun deleteById(id: Long): Completable {
        TODO("Not yet implemented")
    }
}