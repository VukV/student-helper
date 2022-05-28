package rs.raf.projekat2.vuk_vukovic_rn9420.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

import rs.raf.projekat2.vuk_vukovic_rn9420.data.datasources.local.Database
import rs.raf.projekat2.vuk_vukovic_rn9420.data.datasources.remote.SubjectService
import rs.raf.projekat2.vuk_vukovic_rn9420.data.repositories.SubjectRepository
import rs.raf.projekat2.vuk_vukovic_rn9420.data.repositories.SubjectRepositoryImpl
import rs.raf.projekat2.vuk_vukovic_rn9420.presentation.viewmodel.SubjectViewModel

val subjectModule = module {

    viewModel { SubjectViewModel(subjectRepository = get()) }
    single<SubjectRepository> { SubjectRepositoryImpl(localDataSource = get(), remoteDataSource = get()) }
    single { get<Database>().getSubjectDao() }
    single<SubjectService> { create(retrofit = get()) }
}