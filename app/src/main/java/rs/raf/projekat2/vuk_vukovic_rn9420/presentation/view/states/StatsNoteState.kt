package rs.raf.projekat2.vuk_vukovic_rn9420.presentation.view.states

import rs.raf.projekat2.vuk_vukovic_rn9420.data.models.note.Note

sealed class StatsNoteState {
    object Neutral: StatsNoteState()
    data class Success(val notes: List<Note>): StatsNoteState()
    data class Error(val message: String): StatsNoteState()
}