package agency.five.codebase.android.movieapp.ui.moviedetails

import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.mock.MoviesMock
import agency.five.codebase.android.movieapp.ui.component.*
import agency.five.codebase.android.movieapp.ui.moviedetails.mapper.MovieDetailsMapper
import agency.five.codebase.android.movieapp.ui.moviedetails.mapper.MovieDetailsMapperImpl
import agency.five.codebase.android.movieapp.ui.theme.spacing
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
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

private val movieDetailsMapper: MovieDetailsMapper = MovieDetailsMapperImpl()

val movieDetailsViewState = movieDetailsMapper.toMovieDetailsViewState(MoviesMock.getMovieDetails())

@Composable
fun MovieDetailsRoute() {
    val movieDetailsViewState by remember { mutableStateOf(movieDetailsViewState) }

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
            imageUrl = movieDetailsViewState.imageUrl,
            voteAverage = movieDetailsViewState.voteAverage,
            title = movieDetailsViewState.title,
            isFavorite = movieDetailsViewState.isFavorite
        )

        Spacer(
            modifier = Modifier
                .height(MaterialTheme.spacing.medium)
        )

        MovieDetailsOverview(
            overview = movieDetailsViewState.overview
        )

        Spacer(
            modifier = Modifier
                .height(MaterialTheme.spacing.medium)
        )

        MovieDetailsCrew(
            crew = movieDetailsViewState.crew
        )

        Spacer(
            modifier = Modifier
                .height(MaterialTheme.spacing.medium)
        )

        MovieDetailsCast(
            cast = movieDetailsViewState.cast
        )

        Spacer(
            modifier = Modifier
                .height(MaterialTheme.spacing.medium)
        )
    }
}

@Composable
fun MovieDetailsHeader(
    modifier: Modifier = Modifier
        .fillMaxWidth(),
    imageUrl: String?,
    voteAverage: Float,
    title: String,
    isFavorite: Boolean
) {
    Box(
        modifier = modifier,
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
                .align(Alignment.TopCenter)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.BottomCenter)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = MaterialTheme.spacing.medium),
                verticalAlignment = Alignment.CenterVertically
            ) {
                UserScoreProgressBar(
                    score = voteAverage
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
                modifier = Modifier
                    .padding(start = MaterialTheme.spacing.medium),
                text = title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(
                modifier = Modifier
                    .height(MaterialTheme.spacing.medium)
            )

            FavoriteButton(
                modifier = Modifier
                    .padding(
                        start = MaterialTheme.spacing.medium,
                        bottom = MaterialTheme.spacing.medium
                    )
                    .size(30.dp),
                isFavourite = isFavorite,
                onClick = { }
            )
        }
    }
}

@Composable
fun MovieDetailsOverview(
    modifier: Modifier = Modifier,
    overview: String
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = MaterialTheme.spacing.medium, end = MaterialTheme.spacing.medium)
    ) {
        MovieDetailsSectionHeader(
            textId = R.string.overview
        )

        Spacer(
            modifier = Modifier
                .height(MaterialTheme.spacing.small)
        )

        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = overview,
            maxLines = 7,
            fontSize = 14.sp
        )
    }
}

@Composable
fun MovieDetailsCrew(
    modifier: Modifier = Modifier,
    crew: List<CrewmanViewState>
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        MovieDetailsCrewColumn(
            crewman1 = crew[0],
            crewman2 = crew[1]
        )

        MovieDetailsCrewColumn(
            crewman1 = crew[2],
            crewman2 = crew[3]
        )

        MovieDetailsCrewColumn(
            crewman1 = crew[4],
            crewman2 = crew[5]
        )
    }
}

@Composable
fun MovieDetailsCrewColumn(
    crewman1: CrewmanViewState,
    crewman2: CrewmanViewState
) {
    Column(
        Modifier
            .padding(start = MaterialTheme.spacing.medium, end = MaterialTheme.spacing.large)
    ) {
        CrewItem(
            item = CrewItemViewState(
                name = crewman1.name,
                job = crewman1.job
            )
        )

        Spacer(Modifier.height(MaterialTheme.spacing.medium))

        CrewItem(
            item = CrewItemViewState(
                name = crewman2.name,
                job = crewman2.job
            )
        )
    }
}

@Composable
fun MovieDetailsCast(
    modifier: Modifier = Modifier,
    cast: List<ActorCardViewState>
) {
    Column(
        modifier = modifier
            .padding(start = MaterialTheme.spacing.medium, end = MaterialTheme.spacing.medium)
    ) {
        MovieDetailsSectionHeader(
            textId = R.string.top_billed_cast
        )

        Spacer(
            modifier = Modifier
                .height(MaterialTheme.spacing.small)
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
            userScrollEnabled = true
        ) {
            items(
                items = cast,
                key = { actor -> actor.id }
            ) { actor ->
                ActorCard(
                    item = actor,
                    modifier = Modifier
                        .size(
                            width = 125.dp,
                            height = 209.dp
                        )
                )
            }
        }
    }
}

@Composable
fun MovieDetailsSectionHeader(
    modifier: Modifier = Modifier,
    textId: Int
) {
    Text(
        modifier = modifier
            .fillMaxWidth(),
        text = stringResource(id = textId),
        maxLines = 1,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold
    )
}

@Preview(showBackground = false)
@Composable
private fun MovieDetailsScreenPreview() {
    MovieDetailsScreen(movieDetailsViewState = movieDetailsViewState)
}