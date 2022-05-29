package rs.raf.projekat2.vuk_vukovic_rn9420.presentation.view.recycler.note

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.projekat2.vuk_vukovic_rn9420.data.models.note.Note
import rs.raf.projekat2.vuk_vukovic_rn9420.databinding.ItemNoteBinding
import rs.raf.projekat2.vuk_vukovic_rn9420.presentation.view.recycler.note.callback.NoteCallbackInfo

class NoteAdapter(
    noteDiffCallback: NoteDiffCallback,
    private val onNoteClicked: (NoteCallbackInfo) -> Unit
) : ListAdapter<Note, NoteViewHolder>(noteDiffCallback){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val itemNoteBinding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(itemNoteBinding){
            val note = getItem(it.position)
            it.noteId = note.id
            it.noteTile = note.title
            it.noteContent = note.content
            it.noteArchive = note.archived

            onNoteClicked.invoke(it)
        }
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


}