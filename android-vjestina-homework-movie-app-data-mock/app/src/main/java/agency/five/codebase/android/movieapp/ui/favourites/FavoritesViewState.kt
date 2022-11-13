package agency.five.codebase.android.movieapp.ui.favourites

data class FavoritesMovieViewState(
    val id: Int,
    val imageUrl: String?,
    val isFavorite: Boolean
)

data class FavoritesViewState(
    val favoriteMovies: List<FavoritesMovieViewState>
)