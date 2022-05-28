package rs.raf.projekat2.vuk_vukovic_rn9420.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.vuk_vukovic_rn9420.data.datasources.local.NoteDao
import rs.raf.projekat2.vuk_vukovic_rn9420.data.models.note.Note
import rs.raf.projekat2.vuk_vukovic_rn9420.data.models.note.NoteEntity

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

    override fun updateById(id: Long, title: String, content: String): Completable {
        return localDataSource.updateById(id, title, content)
    }

    override fun updateArchivedById(id: Long, archived: Boolean): Completable {
        return localDataSource.updateArchivedById(id, archived)
    }

    override fun deleteById(id: Long): Completable {
        return localDataSource.deleteById(id)
    }
}