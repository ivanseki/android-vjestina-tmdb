package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.mock.MoviesMock
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

data class MovieCardViewState(
    val id: Int,
    val imageUrl: String?,
    val isFavorite: Boolean
)

@Composable
fun MovieCard(
    item: MovieCardViewState,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    onFavouriteButtonClick: (Int) -> Unit
) {
    Card(
        modifier = modifier
            .clickable { onClick() },
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Box {
            AsyncImage(
                model = item.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )

            FavoriteButton(
                modifier = Modifier
                    .padding(9.dp)
                    .size(30.dp),
                isFavourite = item.isFavorite,
                onClick = { onFavouriteButtonClick }
            )
        }
    }
}

@Preview
@Composable
private fun MovieCardPreview() {
    val movie = MoviesMock.getMoviesList()[0]
    val movieCardViewState =
        MovieCardViewState(id = movie.id, imageUrl = movie.imageUrl, isFavorite = movie.isFavorite)

    val movieCardModifier = Modifier
        .width(122.dp)
        .height(179.dp)

    MovieCard(item = movieCardViewState,
        modifier = movieCardModifier,
        onClick = { },
        onFavouriteButtonClick = { })
}