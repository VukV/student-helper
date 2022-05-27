package rs.raf.projekat2.vuk_vukovic_rn9420.presentation.view.recycler.subject

import androidx.recyclerview.widget.RecyclerView
import rs.raf.projekat2.vuk_vukovic_rn9420.data.models.Subject
import rs.raf.projekat2.vuk_vukovic_rn9420.databinding.ItemSubjectBinding

class SubjectViewHolder(private val itemSubjectBinding: ItemSubjectBinding) : RecyclerView.ViewHolder(itemSubjectBinding.root) {

    fun bind(subject: Subject){
        (subject.name + " - " + subject.type).also { itemSubjectBinding.subjectTextView.text = it }
        itemSubjectBinding.profTextView.text = subject.prof
        itemSubjectBinding.classromTextView.text = subject.classroom
        itemSubjectBinding.groupTextView.text = subject.groups

        itemSubjectBinding.dayTextView.text = subject.day
        itemSubjectBinding.timeTextView.text = subject.time
    }
}