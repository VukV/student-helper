package rs.raf.projekat2.vuk_vukovic_rn9420.presentation.view.states

import rs.raf.projekat2.vuk_vukovic_rn9420.data.models.note.Note

sealed class NotesState{
    data class Success(val notes: List<Note>): NotesState()
    data class Error(val message: String): NotesState()
}
