package rs.raf.projekat2.vuk_vukovic_rn9420.data.repositories

import io.reactivex.Observable
import rs.raf.projekat2.vuk_vukovic_rn9420.data.models.subject.Subject
import rs.raf.projekat2.vuk_vukovic_rn9420.data.models.subject.SubjectResource

interface SubjectRepository {

    fun fetchAll(): Observable<SubjectResource<Unit>>
    fun getAll(): Observable<List<Subject>>

    fun getAllByNameOrProfessor(searchTag: String): Observable<List<Subject>>
    fun getAllByGroup(group: String): Observable<List<Subject>>
    fun getAllByDay(day: String): Observable<List<Subject>>

    fun getAllByNameOrProfessorAndGroup(searchTag: String, group: String): Observable<List<Subject>>
    fun getAllByNameOrProfessorAndDay(searchTag: String, day: String): Observable<List<Subject>>
    fun getAllByGroupAndDay(day: String, group: String): Observable<List<Subject>>

    fun getAllByAllFilters(searchTag: String, group: String, day: String): Observable<List<Subject>>
}