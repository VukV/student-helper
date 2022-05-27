package rs.raf.projekat2.vuk_vukovic_rn9420.presentation.view.recycler.subject

import androidx.recyclerview.widget.DiffUtil
import rs.raf.projekat2.vuk_vukovic_rn9420.data.models.Subject

class SubjectDiffCallback : DiffUtil.ItemCallback<Subject>() {

    override fun areItemsTheSame(oldItem: Subject, newItem: Subject): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Subject, newItem: Subject): Boolean {
        return oldItem.name == newItem.name &&
                oldItem.type == newItem.type &&
                oldItem.prof == newItem.prof &&
                oldItem.classroom == newItem.classroom &&
                oldItem.groups == newItem.groups &&
                oldItem.day == newItem.day &&
                oldItem.time == newItem.time
    }
}