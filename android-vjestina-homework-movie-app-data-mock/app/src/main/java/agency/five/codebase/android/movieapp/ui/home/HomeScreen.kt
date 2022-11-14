package agency.five.codebase.android.movieapp.ui.home

import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.mock.MoviesMock
import agency.five.codebase.android.movieapp.model.MovieCategory
import agency.five.codebase.android.movieapp.ui.component.MovieCard
import agency.five.codebase.android.movieapp.ui.component.MovieCardViewState
import agency.five.codebase.android.movieapp.ui.component.MovieCategoryLabel
import agency.five.codebase.android.movieapp.ui.component.MovieCategoryLabelViewState
import agency.five.codebase.android.movieapp.ui.home.mapper.HomeScreenMapperImpl
import agency.five.codebase.android.movieapp.ui.home.mapper.IHomeScreenMapper
import agency.five.codebase.android.movieapp.ui.theme.spacing
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

private val homeScreenMapper: IHomeScreenMapper = HomeScreenMapperImpl()

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
        MovieCategory.NOW_PLAYING_TV,
    ),
    selectedMovieCategory = MovieCategory.NOW_PLAYING_MOVIES,
    movies = MoviesMock.getMoviesList()
)

val upcomingCategoryViewState = homeScreenMapper.toHomeMovieCategoryViewState(
    movieCategories = listOf(
        MovieCategory.UPCOMING_TODAY,
        MovieCategory.UPCOMING_THIS_WEEK,
    ),
    selectedMovieCategory = MovieCategory.UPCOMING_TODAY,
    movies = MoviesMock.getMoviesList()
)

@Composable
fun HomeScreenRoute() {

}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    popularCategoryViewState: HomeMovieCategoryViewState,
    nowPlayingCategoryViewState: HomeMovieCategoryViewState,
    upcomingCategoryViewState: HomeMovieCategoryViewState,
) {
    Column(
        modifier = modifier
            .verticalScroll(state = rememberScrollState())
    ) {
        HomeScreenCategory(
            homeMovieCategoryViewState = popularCategoryViewState,
            categoryTitle = R.string.whats_popular
        )

        Spacer(
            modifier = Modifier
                .height(MaterialTheme.spacing.large)
        )

        HomeScreenCategory(
            homeMovieCategoryViewState = nowPlayingCategoryViewState,
            categoryTitle = R.string.now_playing
        )

        Spacer(
            modifier = Modifier
                .height(MaterialTheme.spacing.large)
        )

        HomeScreenCategory(
            homeMovieCategoryViewState = upcomingCategoryViewState,
            categoryTitle = R.string.upcoming
        )
    }
}

@Composable
fun HomeScreenCategory(
    modifier: Modifier = Modifier,
    homeMovieCategoryViewState: HomeMovieCategoryViewState,
    categoryTitle: Int
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = MaterialTheme.spacing.medium, end = MaterialTheme.spacing.medium)
    ) {
        Text(
            text = stringResource(id = categoryTitle),
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
            userScrollEnabled = false
        ) {
            items(
                items = homeMovieCategoryViewState.movieCategories,
                key = { category -> category.itemId }
            ) { category -> MovieCategoryLabel(
                item = MovieCategoryLabelViewState(
                    itemId = category.itemId,
                    isSelected = category.isSelected,
                    categoryText = category.categoryText
                ),
                onClick = { /*TODO*/ })
            }
        }

        Spacer(
            modifier = Modifier
                .height(MaterialTheme.spacing.medium)
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
        ) {
            items(
                items = homeMovieCategoryViewState.movies,
                key = { movie -> movie.id }
            ) { movie -> MovieCard(
                item = MovieCardViewState(
                    imageUrl = homeMovieCategoryViewState.movies[movie.id - 1].imageUrl,
                    isFavorite = homeMovieCategoryViewState.movies[movie.id - 1].isFavorite
                )
            ) { }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen(
        popularCategoryViewState = popularCategoryViewState,
        nowPlayingCategoryViewState = nowPlayingCategoryViewState,
        upcomingCategoryViewState = upcomingCategoryViewState
    )
}