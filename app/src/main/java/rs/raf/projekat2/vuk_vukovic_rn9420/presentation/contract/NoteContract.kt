package rs.raf.projekat2.vuk_vukovic_rn9420.presentation.contract

import androidx.lifecycle.LiveData
import rs.raf.projekat2.vuk_vukovic_rn9420.presentation.view.states.*
import java.util.*

interface NoteContract {

    interface ViewModel{

        val notesState: LiveData<NotesState>
        val addNoteState: LiveData<AddNoteState>
        val editNoteState: LiveData<EditNoteState>
        val deleteNoteState: LiveData<DeleteNoteState>
        val statsNoteState: LiveData<StatsNoteState>
        val archivedNoteState: LiveData<ArchivedNoteState>

        fun getAllNotes()
        fun getOnlyUnarchivedNotes()
        fun getByTitleOrContent(searchTag: String, archivedSearch: Boolean)
        fun getByTitleOrContentSwitch(searchTag: String, archivedSearch: Boolean)

        fun insert(title: String, content: String)
        fun update(id: Int, title: String, content: String, archived: Boolean, date: Date)
        fun updateArchived(id: Int, title: String, content: String, archived: Boolean, date: Date, recyclerPosition: Int)
        fun deleteById(id: Int)

        fun getLastFiveDays()

        fun setNeutral()
        fun clearStats()
    }
}