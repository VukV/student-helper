package rs.raf.projekat2.vuk_vukovic_rn9420.presentation.view.states

sealed class DeleteNoteState{
    object Success: DeleteNoteState()
    data class Error(val message: String): DeleteNoteState()
}
