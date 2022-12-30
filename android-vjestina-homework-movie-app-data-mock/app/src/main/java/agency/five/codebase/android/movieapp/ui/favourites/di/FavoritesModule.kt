package agency.five.codebase.android.movieapp.ui.favourites.di

import agency.five.codebase.android.movieapp.ui.favourites.FavoritesViewModel
import agency.five.codebase.android.movieapp.ui.favourites.mapper.FavoritesMapper
import agency.five.codebase.android.movieapp.ui.favourites.mapper.FavoritesMapperImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val favoritesModule = module {
    viewModel {
        FavoritesViewModel(
            movieRepository = get(),
            favoritesMapper = get()
        )
    }
    single<FavoritesMapper> { FavoritesMapperImpl() }
}