package rs.raf.projekat2.vuk_vukovic_rn9420.presentation.view.recycler.subject


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.projekat2.vuk_vukovic_rn9420.data.models.Subject
import rs.raf.projekat2.vuk_vukovic_rn9420.databinding.ItemSubjectBinding

class SubjectAdapter : ListAdapter<Subject, SubjectViewHolder>(SubjectDiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val itemSubjectBinding = ItemSubjectBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SubjectViewHolder(itemSubjectBinding)
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}