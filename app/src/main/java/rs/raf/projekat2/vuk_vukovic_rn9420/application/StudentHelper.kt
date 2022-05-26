package rs.raf.projekat2.vuk_vukovic_rn9420.application

import android.app.Application
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
        //TODO
    }
}