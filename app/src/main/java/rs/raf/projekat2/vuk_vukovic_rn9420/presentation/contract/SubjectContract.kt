package rs.raf.projekat2.vuk_vukovic_rn9420.presentation.contract

interface SubjectContract {

    interface ViewModel{
        //todo add states

        fun fetchSchedule()
        fun getAllSubjects()
        fun filterSubjects()
    }
}