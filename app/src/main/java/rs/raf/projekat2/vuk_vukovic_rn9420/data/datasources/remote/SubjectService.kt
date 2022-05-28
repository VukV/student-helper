package rs.raf.projekat2.vuk_vukovic_rn9420.data.datasources.remote

import io.reactivex.Observable
import retrofit2.http.GET
import rs.raf.projekat2.vuk_vukovic_rn9420.data.models.subject.SubjectResponse

interface SubjectService {

    @GET
    fun getSchedule(): Observable<List<SubjectResponse>>
}