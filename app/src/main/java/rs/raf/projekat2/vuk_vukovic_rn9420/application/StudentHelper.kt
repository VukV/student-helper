package rs.raf.projekat2.vuk_vukovic_rn9420.application

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import rs.raf.projekat2.vuk_vukovic_rn9420.modules.coreModule
import rs.raf.projekat2.vuk_vukovic_rn9420.modules.noteModule
import rs.raf.projekat2.vuk_vukovic_rn9420.modules.subjectModule
import timber.log.Timber

class StudentHelper : Application() {

    override fun onCreate() {
        super.onCreate()

        setupTimber()
        setupKoin()
    }


    private fun setupTimber(){
        Timber.plant(Timber.DebugTree())
    }

    private fun setupKoin(){
        val modules = listOf(coreModule, subjectModule, noteModule)

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@StudentHelper)
            androidFileProperties()
            fragmentFactory()
            modules(modules)
        }
    }
}