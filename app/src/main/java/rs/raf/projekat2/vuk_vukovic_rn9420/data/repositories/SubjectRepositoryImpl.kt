package rs.raf.projekat2.vuk_vukovic_rn9420.data.repositories

import io.reactivex.Observable
import rs.raf.projekat2.vuk_vukovic_rn9420.data.datasources.local.SubjectDao
import rs.raf.projekat2.vuk_vukovic_rn9420.data.datasources.remote.SubjectService
import rs.raf.projekat2.vuk_vukovic_rn9420.data.models.subject.Subject
import rs.raf.projekat2.vuk_vukovic_rn9420.data.models.subject.SubjectResource

class SubjectRepositoryImpl(
    private val localDataSource: SubjectDao,
    private val remoteDataSource: SubjectService
) : SubjectRepository {

    override fun fetchAll(): Observable<SubjectResource<Unit>> {
        TODO("Not yet implemented")
    }

    override fun getAll(): Observable<List<Subject>> {
        TODO("Not yet implemented")
    }

    override fun getAllByNameOrProfessor(searchTag: String): Observable<List<Subject>> {
        TODO("Not yet implemented")
    }

    override fun getAllByGroup(group: String): Observable<List<Subject>> {
        TODO("Not yet implemented")
    }

    override fun getAllByDay(day: String): Observable<List<Subject>> {
        TODO("Not yet implemented")
    }

    override fun getAllByNameOrProfessorAndGroup(
        searchTag: String,
        group: String
    ): Observable<List<Subject>> {
        TODO("Not yet implemented")
    }

    override fun getAllByNameOrProfessorAndDay(
        searchTag: String,
        day: String
    ): Observable<List<Subject>> {
        TODO("Not yet implemented")
    }

    override fun getAllByGroupAndDay(day: String, group: String): Observable<List<Subject>> {
        TODO("Not yet implemented")
    }

    override fun getAllByAllFilters(
        searchTag: String,
        group: String,
        day: String
    ): Observable<List<Subject>> {
        TODO("Not yet implemented")
    }
}