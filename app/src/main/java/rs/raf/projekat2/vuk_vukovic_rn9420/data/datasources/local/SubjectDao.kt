package rs.raf.projekat2.vuk_vukovic_rn9420.data.datasources.local

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.vuk_vukovic_rn9420.data.models.subject.SubjectEntity

@Dao
abstract class SubjectDao {

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insertAll(entities: List<SubjectEntity>): Completable

    @Query("SELECT * FROM subjects")
    abstract fun getAll(): Observable<List<SubjectEntity>>

    @Query("DELETE FROM subjects")
    abstract fun deleteAll()

    @Transaction
    open fun deleteAndInsertAll(entities: List<SubjectEntity>) {
        deleteAll()
        insertAll(entities).blockingAwait()
    }

    @Query("SELECT * FROM subjects WHERE name LIKE '%' || :searchTag || '%' OR prof LIKE '%' || :searchTag || '%'")
    abstract fun getByNameOrProfessor(searchTag: String): Observable<List<SubjectEntity>>

    @Query("SELECT * FROM subjects WHERE groups LIKE '%' || :group || '%'")
    abstract fun getByGroup(group: String): Observable<List<SubjectEntity>>

    @Query("SELECT * FROM subjects WHERE day == :day")
    abstract fun getByDay(day: String): Observable<List<SubjectEntity>>

    @Query("SELECT * FROM subjects WHERE (name LIKE '%' || :searchTag || '%' OR prof LIKE '%' || :searchTag || '%') AND groups LIKE '%' || :group || '%'")
    abstract fun getByNameOrProfessorAndGroup(searchTag: String, group: String): Observable<List<SubjectEntity>>

    @Query("SELECT * FROM subjects WHERE (name LIKE '%' || :searchTag || '%' OR prof LIKE '%' || :searchTag || '%') AND day == :day")
    abstract fun getByNameOrProfessorAndDay(searchTag: String, day: String): Observable<List<SubjectEntity>>

    @Query("SELECT * FROM subjects WHERE day == :day AND groups LIKE '%' || :group || '%'")
    abstract fun getByGroupAndDay(group: String, day: String): Observable<List<SubjectEntity>>

    @Query("SELECT * FROM subjects WHERE (name LIKE '%' || :searchTag || '%' OR prof LIKE '%' || :searchTag || '%') AND groups LIKE '%' || :group || '%' AND day == :day")
    abstract fun getByAllFilters(searchTag: String, group: String, day: String): Observable<List<SubjectEntity>>
}