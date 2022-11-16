package agency.five.codebase.android.movieapp.ui.moviedetails

import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.mock.MoviesMock
import agency.five.codebase.android.movieapp.ui.component.*
import agency.five.codebase.android.movieapp.ui.moviedetails.mapper.IMovieDetailsMapper
import agency.five.codebase.android.movieapp.ui.moviedetails.mapper.MovieDetailsMapperImpl
import agency.five.codebase.android.movieapp.ui.theme.spacing
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

private val movieDetailsMapper: IMovieDetailsMapper = MovieDetailsMapperImpl()

val movieDetailsViewState = movieDetailsMapper.toMovieDetailsViewState(MoviesMock.getMovieDetails())

@Composable
fun MovieDetailsRoute() {
    //val movieDetailsViewState by remember { mutableStateOf(movieDetailsViewState) }
    val movieDetailsViewState: MovieDetailsViewState = movieDetailsViewState

    MovieDetailsScreen(
        movieDetailsViewState = movieDetailsViewState
    )
}

@Composable
fun MovieDetailsScreen(
    modifier: Modifier = Modifier,
    movieDetailsViewState: MovieDetailsViewState
) {
    Column(
        modifier = modifier
            .verticalScroll(state = rememberScrollState())
    ) {
        MovieDetailsHeader(
            movieDetailsViewState = movieDetailsViewState
        )

        Spacer(
            modifier = Modifier
                .height(MaterialTheme.spacing.medium)
        )

        MovieDetailsOverview(
            movieDetailsViewState = movieDetailsViewState
        )

        Spacer(
            modifier = Modifier
                .height(MaterialTheme.spacing.medium)
        )

        MovieDetailsCrew(
            movieDetailsViewState = movieDetailsViewState
        )

        Spacer(
            modifier = Modifier
                .height(MaterialTheme.spacing.medium)
        )

        MovieDetailsCast(
            movieDetailsViewState = movieDetailsViewState
        )
    }
}

@Composable
fun MovieDetailsHeader(
    modifier: Modifier = Modifier
        .fillMaxWidth(),
    movieDetailsViewState: MovieDetailsViewState
) {
    Box(
        modifier = modifier,
    ) {
        AsyncImage(
            model = movieDetailsViewState.imageUrl,
            contentDescription = movieDetailsViewState.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = MaterialTheme.spacing.medium),
                verticalAlignment = Alignment.CenterVertically
            ) {
                UserScoreProgressBar(
                    score = movieDetailsViewState.voteAverage
                )

                Text(
                    text = stringResource(id = R.string.user_score),
                    modifier = Modifier
                        .padding(start = MaterialTheme.spacing.small),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Spacer(
                modifier = Modifier
                    .height(MaterialTheme.spacing.medium)
            )

            Text(
                text = movieDetailsViewState.title,
                modifier = Modifier
                    .padding(start = MaterialTheme.spacing.medium),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(
                modifier = Modifier
                    .height(MaterialTheme.spacing.medium)
            )

            FavoriteButton(
                isFavourite = movieDetailsViewState.isFavorite,
                modifier = Modifier
                    .padding(
                        start = MaterialTheme.spacing.medium,
                        bottom = MaterialTheme.spacing.medium
                    )
                    .size(30.dp),
                onClick = { }
            )
        }
    }
}

@Composable
fun MovieDetailsOverview(
    modifier: Modifier = Modifier,
    movieDetailsViewState: MovieDetailsViewState
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = MaterialTheme.spacing.medium, end = MaterialTheme.spacing.medium)
    ) {
        Text(
            text = stringResource(id = R.string.overview),
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth(),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier
                .height(MaterialTheme.spacing.small)
        )

        Text(
            text = movieDetailsViewState.overview,
            maxLines = 7,
            modifier = Modifier
                .fillMaxWidth(),
            fontSize = 14.sp
        )
    }
}

@Composable
fun MovieDetailsCrew(
    modifier: Modifier = Modifier,
    movieDetailsViewState: MovieDetailsViewState
) {
    Column(
        modifier = modifier
            .heightIn(min = 50.dp, max = 400.dp)
            .padding(start = MaterialTheme.spacing.medium, end = MaterialTheme.spacing.medium)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
            userScrollEnabled = false
        ) {
            items(
                items = movieDetailsViewState.crew,
                key = { crewman -> crewman.id }
            ) { crewman ->
                CrewItem(
                    item = CrewItemViewState(
                        crewman.name,
                        crewman.job
                    )
                )
            }
        }
    }
}

@Composable
fun MovieDetailsCast(
    modifier: Modifier = Modifier,
    movieDetailsViewState: MovieDetailsViewState
) {
    Column(
        modifier = modifier
            .padding(start = MaterialTheme.spacing.medium, end = MaterialTheme.spacing.medium)
    ) {
        Text(
            text = stringResource(id = R.string.top_billed_cast),
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth(),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier
                .height(MaterialTheme.spacing.small)
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
        ) {
            items(
                items = movieDetailsViewState.cast,
                key = { actor -> actor.id }
            ) { actor ->
                ActorCard(
                    item = ActorCardViewState(
                        actor.name,
                        actor.character,
                        actor.imageUrl
                    )
                )
            }
        }
    }
}

@Preview(showBackground = false)
@Composable
private fun MovieDetailsScreenPreview() {
    MovieDetailsScreen(movieDetailsViewState = movieDetailsViewState)

    //MovieDetailsHeader(movieDetailsViewState = movieDetailsViewState)

    //MovieDetailsOverview(movieDetailsViewState = movieDetailsViewState)

    //MovieDetailsCrew(movieDetailsViewState = movieDetailsViewState)

    //MovieDetailsCast(movieDetailsViewState = movieDetailsViewState)
}