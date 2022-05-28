package rs.raf.projekat2.vuk_vukovic_rn9420.presentation.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import rs.raf.projekat2.vuk_vukovic_rn9420.data.repositories.SubjectRepository
import rs.raf.projekat2.vuk_vukovic_rn9420.presentation.contract.SubjectContract

class SubjectViewModel(
    private val subjectRepository: SubjectRepository
) :ViewModel(), SubjectContract.ViewModel {

    private val subscriptions = CompositeDisposable()

    override fun fetchSchedule() {
        TODO("Not yet implemented")
    }

    override fun getAllSubjects() {
        TODO("Not yet implemented")
    }

    override fun filterSubjects() {
        TODO("Not yet implemented")
    }
}