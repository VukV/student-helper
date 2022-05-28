package rs.raf.projekat2.vuk_vukovic_rn9420.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rs.raf.projekat2.vuk_vukovic_rn9420.data.datasources.local.Database
import rs.raf.projekat2.vuk_vukovic_rn9420.data.repositories.NoteRepository
import rs.raf.projekat2.vuk_vukovic_rn9420.data.repositories.NoteRepositoryImpl
import rs.raf.projekat2.vuk_vukovic_rn9420.data.repositories.SubjectRepository
import rs.raf.projekat2.vuk_vukovic_rn9420.data.repositories.SubjectRepositoryImpl
import rs.raf.projekat2.vuk_vukovic_rn9420.presentation.viewmodel.NoteViewModel
import rs.raf.projekat2.vuk_vukovic_rn9420.presentation.viewmodel.SubjectViewModel

val noteModule = module {
    viewModel { NoteViewModel(noteRepository = get()) }
    single<NoteRepository> { NoteRepositoryImpl(localDataSource = get()) }
    single { get<Database>().getNoteDao() }
}