package rs.raf.projekat2.vuk_vukovic_rn9420.presentation.view.states

sealed class AddNoteState{
    object Neutral: AddNoteState()
    object Success: AddNoteState()
    data class Error(val message: String): AddNoteState()
}
