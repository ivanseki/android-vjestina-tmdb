package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.mock.MoviesMock
import agency.five.codebase.android.movieapp.model.Movie
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun Moviecard(
    item: Movie,
    modifier: Modifier = Modifier,
    onMovieItemClick: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .width(122.dp)
            .height(179.dp)
            .clickable { onMovieItemClick() },
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Box {
            AsyncImage(
                model = item.imageUrl,
                contentDescription = item.title,
                contentScale = ContentScale.Crop
            )

            FavoriteButton(
                modifier = modifier
                    .padding(9.dp)
            )
        }
    }
}

@Preview
@Composable
private fun MovieCardPreview() {
    val movie = MoviesMock.getMoviesList()[0]
    Moviecard(item = movie)
}