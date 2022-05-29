package rs.raf.projekat2.vuk_vukovic_rn9420.presentation.viewmodel

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

    override fun getByTitleOrContentSwitch(searchTag: String, archivedSearch: Boolean) {
        val subscription = noteRepository
            .getAllByTitleOrContent(searchTag, archivedSearch)
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

    override fun update(id: Int, title: String, content: String, archived: Boolean) {
        val subscription = noteRepository
            .update(id, title, content, archived)
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

    override fun deleteById(id: Int) {
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

    override fun setNeutral() {
        addNoteState.value = AddNoteState.Neutral
        editNoteState.value = EditNoteState.Neutral
    }
}