package rs.raf.projekat2.vuk_vukovic_rn9420.presentation.contract

import androidx.lifecycle.LiveData
import rs.raf.projekat2.vuk_vukovic_rn9420.presentation.view.states.AddNoteState
import rs.raf.projekat2.vuk_vukovic_rn9420.presentation.view.states.DeleteNoteState
import rs.raf.projekat2.vuk_vukovic_rn9420.presentation.view.states.EditNoteState
import rs.raf.projekat2.vuk_vukovic_rn9420.presentation.view.states.NotesState

interface NoteContract {

    interface ViewModel{

        val notesState: LiveData<NotesState>
        val addNoteState: LiveData<AddNoteState>
        val editNoteState: LiveData<EditNoteState>
        val deleteNoteState: LiveData<DeleteNoteState>

        fun getAllNotes()
        fun getOnlyUnarchivedNotes()
        fun getByTitleOrContent(searchTag: String, archivedSearch: Boolean)
        fun getByTitleOrContentSwitch(searchTag: String, archivedSearch: Boolean)

        fun insert(title: String, content: String)
        fun update(id: Int, title: String, content: String, archived: Boolean)
        fun deleteById(id: Int)

        fun setNeutral()
    }
}