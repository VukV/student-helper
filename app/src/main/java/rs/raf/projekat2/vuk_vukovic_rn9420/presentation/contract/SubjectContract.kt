package rs.raf.projekat2.vuk_vukovic_rn9420.presentation.contract

import androidx.lifecycle.LiveData
import rs.raf.projekat2.vuk_vukovic_rn9420.presentation.view.states.SubjectsState

interface SubjectContract {

    interface ViewModel{

        val subjectsState: LiveData<SubjectsState>

        fun fetchSchedule()
        fun getAllSubjects()

        fun getAllByNameOrProfessor(searchTag: String)
        fun getAllByGroup(group: String)
        fun getAllByDay(day: String)

        fun getAllByNameOrProfessorAndGroup(searchTag: String, group: String)
        fun getAllByNameOrProfessorAndDay(searchTag: String, day: String)
        fun getAllByGroupAndDay(day: String, group: String)

        fun getAllByAllFilters(searchTag: String, group: String, day: String)
    }
}