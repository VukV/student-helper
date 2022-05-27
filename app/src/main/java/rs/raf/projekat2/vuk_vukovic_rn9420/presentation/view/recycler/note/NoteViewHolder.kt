package rs.raf.projekat2.vuk_vukovic_rn9420.presentation.view.recycler.note

import android.content.res.Resources
import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.RecyclerView
import rs.raf.projekat2.vuk_vukovic_rn9420.R
import rs.raf.projekat2.vuk_vukovic_rn9420.data.models.Note
import rs.raf.projekat2.vuk_vukovic_rn9420.databinding.ItemNoteBinding

class NoteViewHolder(private val itemNoteBinding: ItemNoteBinding) : RecyclerView.ViewHolder(itemNoteBinding.root) {

    fun bind(note: Note){
        itemNoteBinding.noteTitleTextView.text = note.title
        itemNoteBinding.noteContentTextView.text = note.content

        if (note.archived){
            itemNoteBinding.archiveButton.setImageResource(R.drawable.ic_archive)
        }else{
            itemNoteBinding.archiveButton.setImageResource(R.drawable.ic_unarchive)
        }
    }
}