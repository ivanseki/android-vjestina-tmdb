package agency.five.codebase.android.movieapp.ui.favourites.mapper

import agency.five.codebase.android.movieapp.model.Movie
import agency.five.codebase.android.movieapp.ui.favourites.FavoritesViewState

interface FavoritesMapper {
    fun toFavoritesState(favoriteMovies: List<Movie>): FavoritesViewState
}