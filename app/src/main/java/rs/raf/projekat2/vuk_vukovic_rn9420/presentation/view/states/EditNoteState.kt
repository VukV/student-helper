package rs.raf.projekat2.vuk_vukovic_rn9420.presentation.view.states

sealed class EditNoteState{
    object Success: EditNoteState()
    data class Error(val message: String): EditNoteState()
}
