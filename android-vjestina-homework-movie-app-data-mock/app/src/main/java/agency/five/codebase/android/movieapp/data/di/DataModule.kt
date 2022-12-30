package agency.five.codebase.android.movieapp.data.di

import agency.five.codebase.android.movieapp.data.repository.MovieRepository
import agency.five.codebase.android.movieapp.data.repository.MovieRepositoryImpl
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val dataModule = module {
    single<MovieRepository> {
        MovieRepositoryImpl(
            movieService = get(),
            movieDao = get(),
            bgDispatcher = Dispatchers.IO
        )
    }
}