package rs.raf.projekat2.vuk_vukovic_rn9420.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.vuk_vukovic_rn9420.data.datasources.local.NoteDao
import rs.raf.projekat2.vuk_vukovic_rn9420.data.models.note.Note
import rs.raf.projekat2.vuk_vukovic_rn9420.data.models.note.NoteEntity
import timber.log.Timber

class NoteRepositoryImpl(private val localDataSource: NoteDao) : NoteRepository {

    override fun getAll(): Observable<List<Note>> {
        return localDataSource.getAll()
            .map {
                it.map {
                    Note(it.id!!, it.title, it.content, it.archived)
                }
            }
    }

    override fun getAllByTitleOrContent(searchTag: String, archivedSearch: Boolean): Observable<List<Note>> {
        if(archivedSearch){
            return localDataSource.getByTitleOrContent(searchTag)
                .map {
                    it.map {
                        Note(it.id!!, it.title, it.content, it.archived)
                    }
                }
        }
        else{
            return localDataSource.getByTitleOrContentUnarchived(searchTag)
                .map {
                    it.map {
                        Note(it.id!!, it.title, it.content, it.archived)
                    }
                }
        }
    }

    override fun getOnlyUnarchived(): Observable<List<Note>> {
        return localDataSource.getAllUnarchived()
            .map {
                it.map {
                    Note(it.id!!, it.title, it.content, it.archived)
                }
            }
    }

    override fun insert(title: String, content: String): Completable {
        val noteEntity = NoteEntity(
            title = title,
            content = content,
            archived = false
        )
        return localDataSource.insert(noteEntity)
    }

    override fun update(id: Int, title: String, content: String, archived: Boolean): Completable {
        val noteEntity = NoteEntity(
            id = id,
            title = title,
            content = content,
            archived = false
        )

        return localDataSource.update(noteEntity)
    }


    override fun deleteById(id: Int): Completable {
        return localDataSource.deleteById(id)
    }
}