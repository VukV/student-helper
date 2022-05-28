package rs.raf.projekat2.vuk_vukovic_rn9420.presentation.view.states

import rs.raf.projekat2.vuk_vukovic_rn9420.data.models.subject.Subject

sealed class SubjectsState{
    object Loading: SubjectsState()
    object DataFetched: SubjectsState()
    data class Success(val subjects: List<Subject>): SubjectsState()
    data class Error(val message: String): SubjectsState()
}
