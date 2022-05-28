package rs.raf.projekat2.vuk_vukovic_rn9420.data.repositories

import io.reactivex.Observable
import rs.raf.projekat2.vuk_vukovic_rn9420.data.datasources.local.SubjectDao
import rs.raf.projekat2.vuk_vukovic_rn9420.data.datasources.remote.SubjectService
import rs.raf.projekat2.vuk_vukovic_rn9420.data.models.subject.Subject
import rs.raf.projekat2.vuk_vukovic_rn9420.data.models.subject.SubjectEntity
import rs.raf.projekat2.vuk_vukovic_rn9420.data.models.subject.SubjectResource

class SubjectRepositoryImpl(
    private val localDataSource: SubjectDao,
    private val remoteDataSource: SubjectService
) : SubjectRepository {

    override fun fetchAll(): Observable<SubjectResource<Unit>> {
        return remoteDataSource
            .getSchedule()
            .doOnNext {
                val subjects = it.map {
                    SubjectEntity(
                        name = it.name,
                        type = it.type,
                        prof = it.prof,
                        groups = it.groups,
                        day = it.day,
                        time = it.time,
                        classroom = it.classroom,
                    )
                }
                localDataSource.deleteAndInsertAll(subjects)
            }
            .map {
                SubjectResource.Success(Unit)
            }
    }

    override fun getAll(): Observable<List<Subject>> {
        return localDataSource.getAll()
            .map {
                it.map {
                    Subject(it.id!!, it.name, it.type, it.prof, it.groups, it.day, it.time, it.classroom)
                }
            }
    }

    override fun getAllByNameOrProfessor(searchTag: String): Observable<List<Subject>> {
        return localDataSource.getByNameOrProfessor(searchTag)
            .map {
                it.map {
                    Subject(it.id!!, it.name, it.type, it.prof, it.groups, it.day, it.time, it.classroom)
                }
            }
    }

    override fun getAllByGroup(group: String): Observable<List<Subject>> {
        return localDataSource.getByGroup(group)
            .map {
                it.map {
                    Subject(it.id!!, it.name, it.type, it.prof, it.groups, it.day, it.time, it.classroom)
                }
            }
    }

    override fun getAllByDay(day: String): Observable<List<Subject>> {
        return localDataSource.getByDay(day)
            .map {
                it.map {
                    Subject(it.id!!, it.name, it.type, it.prof, it.groups, it.day, it.time, it.classroom)
                }
            }
    }

    override fun getAllByNameOrProfessorAndGroup(searchTag: String, group: String): Observable<List<Subject>> {
        return localDataSource.getByNameOrProfessorAndGroup(searchTag, group)
            .map {
                it.map {
                    Subject(it.id!!, it.name, it.type, it.prof, it.groups, it.day, it.time, it.classroom)
                }
            }
    }

    override fun getAllByNameOrProfessorAndDay(searchTag: String, day: String): Observable<List<Subject>> {
        return localDataSource.getByNameOrProfessorAndDay(searchTag, day)
            .map {
                it.map {
                    Subject(it.id!!, it.name, it.type, it.prof, it.groups, it.day, it.time, it.classroom)
                }
            }
    }

    override fun getAllByGroupAndDay(day: String, group: String): Observable<List<Subject>> {
        return localDataSource.getByGroupAndDay(group, day)
            .map {
                it.map {
                    Subject(it.id!!, it.name, it.type, it.prof, it.groups, it.day, it.time, it.classroom)
                }
            }
    }

    override fun getAllByAllFilters(searchTag: String, group: String, day: String): Observable<List<Subject>> {
        return localDataSource.getByAllFilters(searchTag, group, day)
            .map {
                it.map {
                    Subject(it.id!!, it.name, it.type, it.prof, it.groups, it.day, it.time, it.classroom)
                }
            }
    }
}