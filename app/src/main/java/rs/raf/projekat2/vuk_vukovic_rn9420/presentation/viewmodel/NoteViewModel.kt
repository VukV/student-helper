package rs.raf.projekat2.vuk_vukovic_rn9420.presentation.viewmodel

import android.icu.text.StringSearch
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import rs.raf.projekat2.vuk_vukovic_rn9420.data.repositories.NoteRepository
import rs.raf.projekat2.vuk_vukovic_rn9420.presentation.contract.NoteContract
import rs.raf.projekat2.vuk_vukovic_rn9420.presentation.view.states.*
import timber.log.Timber
import java.util.concurrent.TimeUnit

class NoteViewModel(
    private val noteRepository: NoteRepository
) : ViewModel(), NoteContract.ViewModel{

    private val subscriptions = CompositeDisposable()
    private val publishSubject: PublishSubject<String> = PublishSubject.create()

    override val notesState: MutableLiveData<NotesState> = MutableLiveData()
    override val addNoteState: MutableLiveData<AddNoteState> = MutableLiveData()
    override val editNoteState: MutableLiveData<EditNoteState> = MutableLiveData()
    override val deleteNoteState: MutableLiveData<DeleteNoteState> = MutableLiveData()

    private var archivedSearch: Boolean = true

    init {
        val subscription = publishSubject
            .debounce(200, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .switchMap {
                noteRepository
                    .getAllByTitleOrContent(it, archivedSearch)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError {
                        Timber.e(it)
                    }
            }
            .subscribe(
                {
                    notesState.value = NotesState.Success(it)
                },
                {
                    notesState.value = NotesState.Error("Greška sa bazom podataka")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getAllNotes() {
        val subscription = noteRepository
            .getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    notesState.value = NotesState.Success(it)
                },
                {
                    notesState.value = NotesState.Error("Greška sa bazom podataka")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getOnlyUnarchivedNotes() {
        val subscription = noteRepository
            .getOnlyUnarchived()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    notesState.value = NotesState.Success(it)
                },
                {
                    notesState.value = NotesState.Error("Greška sa bazom podataka")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getByTitleOrContent(searchTag: String, archivedSearch: Boolean) {
        this.archivedSearch = archivedSearch
        publishSubject.onNext(searchTag)
    }

    override fun insert(title: String, content: String) {
        val subscription = noteRepository
            .insert(title, content)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    addNoteState.value = AddNoteState.Success
                },
                {
                    addNoteState.value = AddNoteState.Error("Greška sa bazom podataka")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun updateById(id: Long, title: String, content: String) {
        val subscription = noteRepository
            .updateById(id, title, content)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    editNoteState.value = EditNoteState.Success
                },
                {
                    editNoteState.value = EditNoteState.Error("Greška sa bazom podataka")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun updateArchivedById(id: Long, archived: Boolean) {
        val subscription = noteRepository
            .updateArchivedById(id, archived)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    editNoteState.value = EditNoteState.Success
                },
                {
                    editNoteState.value = EditNoteState.Error("Greška sa bazom podataka")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun deleteById(id: Long) {
        val subscription = noteRepository
            .deleteById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    deleteNoteState.value = DeleteNoteState.Success
                },
                {
                    deleteNoteState.value = DeleteNoteState.Error("Greška sa bazom podataka")
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