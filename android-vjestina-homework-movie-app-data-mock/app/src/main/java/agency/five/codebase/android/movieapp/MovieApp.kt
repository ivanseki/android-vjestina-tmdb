package agency.five.codebase.android.movieapp

import agency.five.codebase.android.movieapp.data.di.dataModule
import agency.five.codebase.android.movieapp.ui.favourites.di.favoritesModule
import agency.five.codebase.android.movieapp.ui.home.di.homeModule
import agency.five.codebase.android.movieapp.ui.moviedetails.di.movieDetailsModule
import android.app.Application
import android.util.Log
import org.koin.core.context.GlobalContext.startKoin

class MovieApp : Application() {
    override fun onCreate() {
        super.onCreate()

        Log.d("MovieApp", "App started")

        startKoin {
            modules(
                dataModule,
                favoritesModule,
                movieDetailsModule,
                homeModule
            )
        }
    }
}
