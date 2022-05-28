package rs.raf.projekat2.vuk_vukovic_rn9420.data.models.subject

sealed class SubjectResource<out T>{
    data class Success<out T>(val data: T) : SubjectResource<T>()
    data class Loading<out T>(val message: String = "") : SubjectResource<T>()
    data class Error<out T>(val error: Throwable = Throwable(), val data: T? = null): SubjectResource<T>()
}
