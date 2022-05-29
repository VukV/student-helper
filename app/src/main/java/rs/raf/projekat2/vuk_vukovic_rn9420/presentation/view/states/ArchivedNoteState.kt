package rs.raf.projekat2.vuk_vukovic_rn9420.presentation.view.states

sealed class ArchivedNoteState {
    data class Success(val pos: Int): ArchivedNoteState()
    data class Error(val message: String): ArchivedNoteState()
}