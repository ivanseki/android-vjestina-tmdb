package agency.five.codebase.android.movieapp.data.di

import agency.five.codebase.android.movieapp.data.repository.FakeMovieRepository
import agency.five.codebase.android.movieapp.data.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val dataModule = module {
    single<MovieRepository> {
        FakeMovieRepository(ioDispatcher = Dispatchers.IO)
    }
}