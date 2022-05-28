package rs.raf.projekat2.vuk_vukovic_rn9420.presentation.viewmodel

import android.widget.Toast
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import rs.raf.projekat2.vuk_vukovic_rn9420.data.repositories.NoteRepository
import rs.raf.projekat2.vuk_vukovic_rn9420.presentation.contract.NoteContract
import timber.log.Timber
import java.util.concurrent.TimeUnit

class NoteViewModel(
    private val noteRepository: NoteRepository
) : ViewModel(), NoteContract.ViewModel{

    private val subscriptions = CompositeDisposable()
    private val publishSubject: PublishSubject<String> = PublishSubject.create()

    init {
        val subscription = publishSubject
            .debounce(200, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .switchMap {
                noteRepository
                    .getAllByTitleOrContent(it)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError {
                        Timber.e(it)
                    }
            }
            .subscribe(
                {
                    //todo add state
                },
                {
                    //todo add state
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
                    //todo add state
                },
                {
                    //todo add state
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
                    //todo add state
                },
                {
                    //todo add state
                }
            )
        subscriptions.add(subscription)
    }

    override fun getByTitleOrContent(searchTag: String) {
        publishSubject.onNext(searchTag)
    }
}