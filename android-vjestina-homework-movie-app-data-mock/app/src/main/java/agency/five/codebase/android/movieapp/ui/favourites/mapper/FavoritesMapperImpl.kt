package agency.five.codebase.android.movieapp.ui.favourites.mapper

import agency.five.codebase.android.movieapp.model.Movie
import agency.five.codebase.android.movieapp.ui.favourites.FavoritesMovieViewState
import agency.five.codebase.android.movieapp.ui.favourites.FavoritesViewState

class FavoritesMapperImpl : IFavoritesMapper {
    override fun toFavoritesState(favoriteMovies: List<Movie>): FavoritesViewState {
        return FavoritesViewState(favoriteMovies.map {
                movie -> FavoritesMovieViewState(
                    id = movie.id,
                    imageUrl = movie.imageUrl,
                    isFavorite = movie.isFavorite)}
        )
    }
}