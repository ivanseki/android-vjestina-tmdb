package agency.five.codebase.android.movieapp.ui.favourites.mapper

import agency.five.codebase.android.movieapp.model.Movie
import agency.five.codebase.android.movieapp.ui.component.MovieCardViewState
import agency.five.codebase.android.movieapp.ui.favourites.FavoritesViewState

class FavoritesMapperImpl : FavoritesMapper {
    override fun toFavoritesState(favoriteMovies: List<Movie>): FavoritesViewState {
        return FavoritesViewState(favoriteMovies.map { movie ->
            MovieCardViewState(
                id = movie.id,
                imageUrl = movie.imageUrl,
                isFavorite = movie.isFavorite
            )
        }
        )
    }
}