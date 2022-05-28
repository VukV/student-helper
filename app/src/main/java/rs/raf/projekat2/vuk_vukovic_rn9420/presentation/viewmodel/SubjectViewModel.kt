package rs.raf.projekat2.vuk_vukovic_rn9420.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import rs.raf.projekat2.vuk_vukovic_rn9420.data.models.subject.SubjectResource
import rs.raf.projekat2.vuk_vukovic_rn9420.data.repositories.SubjectRepository
import rs.raf.projekat2.vuk_vukovic_rn9420.presentation.contract.SubjectContract
import rs.raf.projekat2.vuk_vukovic_rn9420.presentation.view.states.SubjectsState
import timber.log.Timber

class SubjectViewModel(
    private val subjectRepository: SubjectRepository
) :ViewModel(), SubjectContract.ViewModel {

    private val subscriptions = CompositeDisposable()
    override val subjectsState: MutableLiveData<SubjectsState> = MutableLiveData()

    override fun fetchSchedule() {
        val subscription = subjectRepository
            .fetchAll()
            .startWith(SubjectResource.Loading())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    when(it){
                        is SubjectResource.Loading -> subjectsState.value = SubjectsState.Loading
                        is SubjectResource.Success -> subjectsState.value = SubjectsState.DataFetched
                        is SubjectResource.Error -> subjectsState.value = SubjectsState.Error("Greška na serveru")
                    }
                },
                {
                    subjectsState.value = SubjectsState.Error("Greška na serveru")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getAllSubjects() {
        val subscription = subjectRepository
            .getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    subjectsState.value = SubjectsState.Success(it)
                },
                {
                    subjectsState.value = SubjectsState.Error("Greška sa bazom podataka")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getAllByNameOrProfessor(searchTag: String) {
        val subscription = subjectRepository
            .getAllByNameOrProfessor(searchTag)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    subjectsState.value = SubjectsState.Success(it)
                },
                {
                    subjectsState.value = SubjectsState.Error("Greška sa bazom podataka")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getAllByGroup(group: String) {
        val subscription = subjectRepository
            .getAllByGroup(group)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    subjectsState.value = SubjectsState.Success(it)
                },
                {
                    subjectsState.value = SubjectsState.Error("Greška sa bazom podataka")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getAllByDay(day: String) {
        val subscription = subjectRepository
            .getAllByDay(day)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    subjectsState.value = SubjectsState.Success(it)
                },
                {
                    subjectsState.value = SubjectsState.Error("Greška sa bazom podataka")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getAllByNameOrProfessorAndGroup(searchTag: String, group: String) {
        val subscription = subjectRepository
            .getAllByNameOrProfessorAndGroup(searchTag, group)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    subjectsState.value = SubjectsState.Success(it)
                },
                {
                    subjectsState.value = SubjectsState.Error("Greška sa bazom podataka")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getAllByNameOrProfessorAndDay(searchTag: String, day: String) {
        val subscription = subjectRepository
            .getAllByNameOrProfessorAndDay(searchTag, day)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    subjectsState.value = SubjectsState.Success(it)
                },
                {
                    subjectsState.value = SubjectsState.Error("Greška sa bazom podataka")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getAllByGroupAndDay(day: String, group: String) {
        val subscription = subjectRepository
            .getAllByGroupAndDay(day, group)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    subjectsState.value = SubjectsState.Success(it)
                },
                {
                    subjectsState.value = SubjectsState.Error("Greška sa bazom podataka")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getAllByAllFilters(searchTag: String, group: String, day: String) {
        val subscription = subjectRepository
            .getAllByAllFilters(searchTag, group, day)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    subjectsState.value = SubjectsState.Success(it)
                },
                {
                    subjectsState.value = SubjectsState.Error("Greška sa bazom podataka")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.dispose()
    }
}