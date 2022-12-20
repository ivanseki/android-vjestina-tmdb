package agency.five.codebase.android.movieapp

import agency.five.codebase.android.movieapp.data.di.dataModule
import agency.five.codebase.android.movieapp.data.di.databaseModule
import agency.five.codebase.android.movieapp.data.di.networkModule
import agency.five.codebase.android.movieapp.ui.favourites.di.favoritesModule
import agency.five.codebase.android.movieapp.ui.home.di.homeModule
import agency.five.codebase.android.movieapp.ui.moviedetails.di.movieDetailsModule
import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MovieApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MovieApp)
            modules(
                dataModule,
                favoritesModule,
                movieDetailsModule,
                homeModule,
                databaseModule,
                networkModule,
            )
        }
    }
}
