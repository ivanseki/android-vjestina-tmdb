package agency.five.codebase.android.movieapp.ui.favourites

import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.mock.MoviesMock.getMoviesList
import agency.five.codebase.android.movieapp.ui.component.MovieCard
import agency.five.codebase.android.movieapp.ui.component.MovieCardViewState
import agency.five.codebase.android.movieapp.ui.favourites.mapper.FavoritesMapperImpl
import agency.five.codebase.android.movieapp.ui.favourites.mapper.FavoritesMapper
import agency.five.codebase.android.movieapp.ui.theme.MovieAppTheme
import agency.five.codebase.android.movieapp.ui.theme.spacing
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.getViewModel

@Composable
fun FavoritesRoute(
    viewModel: FavoritesViewModel,
    onNavigateToMovieDetails: (MovieCardViewState) -> Unit
) {
    val favoritesViewState: FavoritesViewState by viewModel.favoritesViewState.collectAsState()

    FavoritesScreen(
        favoritesViewState = favoritesViewState,
        onNavigateToMovieDetails = onNavigateToMovieDetails,
        onFavoriteButtonClick = { movieId -> viewModel.toggleFavorite(movieId) }
    )
}

@Composable
fun FavoritesScreen(
    modifier: Modifier = Modifier,
    favoritesViewState: FavoritesViewState,
    onNavigateToMovieDetails: (MovieCardViewState) -> Unit,
    onFavoriteButtonClick: (Int) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(horizontal = MaterialTheme.spacing.medium),
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
    ) {
        item(
            span = { GridItemSpan(maxLineSpan) }
        ) {
            Text(
                text = stringResource(R.string.favorites),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = MaterialTheme.spacing.medium
                    ),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        items(
            items = favoritesViewState.favoriteMovies,
            key = { movie -> movie.id }
        ) { movie ->
            MovieCard(
                item = MovieCardViewState(
                    id = movie.id,
                    imageUrl = movie.imageUrl,
                    isFavorite = movie.isFavorite
                ),
                onClick = { onNavigateToMovieDetails(movie) },
                onFavouriteButtonClick = onFavoriteButtonClick
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FavouritesScreenPreview() {
    MovieAppTheme {
        FavoritesRoute(
            viewModel = getViewModel(),
            onNavigateToMovieDetails = {return@FavoritesRoute} )
    }
}