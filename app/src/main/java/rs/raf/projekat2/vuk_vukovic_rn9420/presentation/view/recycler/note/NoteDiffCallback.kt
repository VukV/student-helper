package rs.raf.projekat2.vuk_vukovic_rn9420.presentation.view.recycler.note

import androidx.recyclerview.widget.DiffUtil
import rs.raf.projekat2.vuk_vukovic_rn9420.data.models.note.Note

class NoteDiffCallback : DiffUtil.ItemCallback<Note>(){
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.title == newItem.title &&
                oldItem.content == newItem.content
    }
}