package agency.five.codebase.android.movieapp.ui.home

import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.mock.MoviesMock
import agency.five.codebase.android.movieapp.model.MovieCategory
import agency.five.codebase.android.movieapp.ui.component.MovieCard
import agency.five.codebase.android.movieapp.ui.component.MovieCardViewState
import agency.five.codebase.android.movieapp.ui.component.MovieCategoryLabel
import agency.five.codebase.android.movieapp.ui.component.MovieCategoryLabelViewState
import agency.five.codebase.android.movieapp.ui.home.mapper.HomeScreenMapperImpl
import agency.five.codebase.android.movieapp.ui.home.mapper.HomeScreenMapper
import agency.five.codebase.android.movieapp.ui.theme.MovieAppTheme
import agency.five.codebase.android.movieapp.ui.theme.spacing
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

private val homeScreenMapper: HomeScreenMapper = HomeScreenMapperImpl()

val popularCategoryViewState = homeScreenMapper.toHomeMovieCategoryViewState(
    movieCategories = listOf(
        MovieCategory.POPULAR_STREAMING,
        MovieCategory.POPULAR_ON_TV,
        MovieCategory.POPULAR_FOR_RENT,
        MovieCategory.POPULAR_IN_THEATRES
    ),
    selectedMovieCategory = MovieCategory.POPULAR_STREAMING,
    movies = MoviesMock.getMoviesList()
)

val nowPlayingCategoryViewState = homeScreenMapper.toHomeMovieCategoryViewState(
    movieCategories = listOf(
        MovieCategory.NOW_PLAYING_MOVIES,
        MovieCategory.NOW_PLAYING_TV
    ),
    selectedMovieCategory = MovieCategory.NOW_PLAYING_MOVIES,
    movies = MoviesMock.getMoviesList()
)

val upcomingCategoryViewState = homeScreenMapper.toHomeMovieCategoryViewState(
    movieCategories = listOf(
        MovieCategory.UPCOMING_TODAY,
        MovieCategory.UPCOMING_THIS_WEEK
    ),
    selectedMovieCategory = MovieCategory.UPCOMING_TODAY,
    movies = MoviesMock.getMoviesList()
)

@Composable
fun HomeRoute(
    onNavigateToMovieDetails: (HomeMovieViewState) -> Unit
) {
    var popularViewState by remember { mutableStateOf(popularCategoryViewState) }
    var nowPlayingViewState by remember { mutableStateOf(nowPlayingCategoryViewState) }
    var upcomingViewState by remember { mutableStateOf(upcomingCategoryViewState) }

    HomeScreen(
        popularCategoryViewState = popularViewState,
        nowPlayingCategoryViewState = nowPlayingViewState,
        upcomingCategoryViewState = upcomingViewState,
        onNavigateToMovieDetails = onNavigateToMovieDetails,
        onCategoryClick = {
            when (it.itemId) {
                0, 1, 2, 3 -> popularViewState =
                    homeScreenMapper.toHomeMovieCategoryViewState(
                        movieCategories = listOf(
                            MovieCategory.POPULAR_STREAMING,
                            MovieCategory.POPULAR_ON_TV,
                            MovieCategory.POPULAR_FOR_RENT,
                            MovieCategory.POPULAR_IN_THEATRES
                        ),
                        selectedMovieCategory = MovieCategory.values()[it.itemId],
                        movies = MoviesMock.getMoviesList()
                    )
                4, 5 -> nowPlayingViewState =
                    homeScreenMapper.toHomeMovieCategoryViewState(
                        movieCategories = listOf(
                            MovieCategory.NOW_PLAYING_MOVIES,
                            MovieCategory.NOW_PLAYING_TV
                        ),
                        selectedMovieCategory = MovieCategory.values()[it.itemId],
                        movies = MoviesMock.getMoviesList()
                    )
                else -> upcomingViewState =
                    homeScreenMapper.toHomeMovieCategoryViewState(
                        movieCategories = listOf(
                            MovieCategory.UPCOMING_TODAY,
                            MovieCategory.UPCOMING_THIS_WEEK
                        ),
                        selectedMovieCategory = MovieCategory.values()[it.itemId],
                        movies = MoviesMock.getMoviesList()
                    )
            }
        }

    )
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    popularCategoryViewState: HomeMovieCategoryViewState,
    nowPlayingCategoryViewState: HomeMovieCategoryViewState,
    upcomingCategoryViewState: HomeMovieCategoryViewState,
    onCategoryClick: (MovieCategoryLabelViewState) -> Unit,
    onNavigateToMovieDetails: (HomeMovieViewState) -> Unit
) {
    Column(
        modifier = modifier
            .verticalScroll(state = rememberScrollState())
    ) {
        Spacer(
            modifier = Modifier
                .height(MaterialTheme.spacing.medium)
        )

        HomeScreenCategory(
            homeMovieCategoryViewState = popularCategoryViewState,
            categoryName = R.string.whats_popular,
            onCategoryClick = onCategoryClick,
            onNavigateToMovieDetails = onNavigateToMovieDetails
        )

        Spacer(
            modifier = Modifier
                .height(MaterialTheme.spacing.large)
        )

        HomeScreenCategory(
            homeMovieCategoryViewState = nowPlayingCategoryViewState,
            categoryName = R.string.now_playing,
            onCategoryClick = onCategoryClick,
            onNavigateToMovieDetails = onNavigateToMovieDetails
        )

        Spacer(
            modifier = Modifier
                .height(MaterialTheme.spacing.large)
        )

        HomeScreenCategory(
            homeMovieCategoryViewState = upcomingCategoryViewState,
            categoryName = R.string.upcoming,
            onCategoryClick = onCategoryClick,
            onNavigateToMovieDetails = onNavigateToMovieDetails
        )
    }
}

@Composable
fun HomeScreenCategory(
    modifier: Modifier = Modifier,
    homeMovieCategoryViewState: HomeMovieCategoryViewState,
    categoryName: Int,
    onCategoryClick: (MovieCategoryLabelViewState) -> Unit,
    onNavigateToMovieDetails: (HomeMovieViewState) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = MaterialTheme.spacing.medium, end = MaterialTheme.spacing.medium)
    ) {
        Text(
            text = stringResource(id = categoryName),
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth(),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier
                .height(MaterialTheme.spacing.medium)
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
            userScrollEnabled = true
        ) {
            items(
                items = homeMovieCategoryViewState.movieCategories,
                key = { category -> category.itemId }
            ) { category ->
                MovieCategoryLabel(
                    item = MovieCategoryLabelViewState(
                        itemId = category.itemId,
                        isSelected = category.isSelected,
                        categoryText = category.categoryText
                    ),
                    onClick = { onCategoryClick(category) })
            }
        }

        Spacer(
            modifier = Modifier
                .height(MaterialTheme.spacing.medium)
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
            userScrollEnabled = true
        ) {
            items(
                items = homeMovieCategoryViewState.movies,
                key = { movie -> movie.id }
            ) { movie ->
                MovieCard(
                    item = MovieCardViewState(
                        id = movie.id,
                        imageUrl = movie.imageUrl,
                        isFavorite = movie.isFavorite
                    ),
                    onClick = { onNavigateToMovieDetails(movie) }
                ) { }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    MovieAppTheme() {
        HomeRoute(onNavigateToMovieDetails = { })
    }
}