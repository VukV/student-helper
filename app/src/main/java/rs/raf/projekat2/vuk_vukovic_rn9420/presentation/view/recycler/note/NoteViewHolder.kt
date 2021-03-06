package rs.raf.projekat2.vuk_vukovic_rn9420.presentation.view.recycler.note

import androidx.recyclerview.widget.RecyclerView
import rs.raf.projekat2.vuk_vukovic_rn9420.R
import rs.raf.projekat2.vuk_vukovic_rn9420.data.models.note.Note
import rs.raf.projekat2.vuk_vukovic_rn9420.databinding.ItemNoteBinding
import rs.raf.projekat2.vuk_vukovic_rn9420.presentation.view.recycler.note.callback.NoteCallbackAction
import rs.raf.projekat2.vuk_vukovic_rn9420.presentation.view.recycler.note.callback.NoteCallbackInfo

class NoteViewHolder(
    private val itemNoteBinding: ItemNoteBinding,
    onNoteClicked: (NoteCallbackInfo) -> Unit
) : RecyclerView.ViewHolder(itemNoteBinding.root) {

    init {
        itemNoteBinding.editButton.setOnClickListener {
            val noteInfo = NoteCallbackInfo(
                position = absoluteAdapterPosition,
                action = NoteCallbackAction.EDIT
            )
            onNoteClicked(noteInfo)
        }

        itemNoteBinding.deleteButton.setOnClickListener {
            val noteInfo = NoteCallbackInfo(
                position = absoluteAdapterPosition,
                action = NoteCallbackAction.DELETE
            )
            onNoteClicked(noteInfo)
        }

        itemNoteBinding.archiveButton.setOnClickListener {
            val noteInfo = NoteCallbackInfo(
                position = absoluteAdapterPosition,
                action = NoteCallbackAction.ARCHIVE
            )
            onNoteClicked(noteInfo)
        }
    }

    fun bind(note: Note){
        itemNoteBinding.noteTitleTextView.text = note.title
        itemNoteBinding.noteContentTextView.text = note.content

        if (!note.archived){
            itemNoteBinding.archiveButton.setImageResource(R.drawable.ic_archive)
        }else{
            itemNoteBinding.archiveButton.setImageResource(R.drawable.ic_unarchive)
        }
    }
}