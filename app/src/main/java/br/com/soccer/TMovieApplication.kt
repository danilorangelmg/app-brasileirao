package br.com.soccer

import android.app.Application
import br.com.soccer.repositorie.di.repositoryModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TMovieApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TMovieApplication)
            modules(listOf(appModule).plus(repositoryModules))
        }
    }
}